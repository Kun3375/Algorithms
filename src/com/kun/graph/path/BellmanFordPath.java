package com.kun.graph.path;

import com.kun.graph.weighted.Edge;
import com.kun.graph.weighted.WeightedGraph;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

/**
 * Bellman-Ford 算法求最短路径
 * 优势：可以处理存在负权边的图
 * 劣势：不能处理负权环的图，所以相应的也不能处理无向图，除非该无向图没有负权边（不过算法可以判断图中是否存在环）
 * 最短路径最多会经过 V 个定点，V - 1 条边，如果一个节点经过两次，那么必然有负权环的存在
 * so，将每个定点进行 V - 1 次松弛操作即可
 * 时间复杂度 O(EV)
 *
 * @author CaoZiye
 * @version 1.0 2018/3/17 14:30
 */
public class BellmanFordPath<W extends Number & Comparable<W>> {

    /**
     * 保存一个图的引用
     */
    private WeightedGraph<W> graph;
    /**
     * 记录起始点
     */
    private int start;
    /**
     * 从起始点开始到某点的路径长度
     */
    private Number[] distance;
    /**
     * 记录单条路径的源，可以逆向追溯整条最短路径
     */
    private Edge<W>[] from;
    /**
     * 是否存在负权环
     */
    private boolean hasNegativeCycle;

    @SuppressWarnings("unchecked")
    public BellmanFordPath(WeightedGraph<W> graph, int start) {
        // 初始化
        assert start >= 0 && start < graph.getVertices();
        this.graph = graph;
        this.start = start;
        this.distance = new Number[graph.getVertices()];
        this.from = (Edge<W>[]) new Edge[graph.getVertices()];

        // 初始化源点信息，保证源点可达（不然一切都没了意义）
        distance[start] = 0;
        from[start] = new Edge<>(start, start, (W) (Number) 0);

        // Bellman-Ford 算法十分暴力（愚蠢），直接对所有顶点尝试循环 V 次松弛
        // v - 1 次确定最短路径，第 V 次判断负权环的存在
        for (int i = 1; i < graph.getVertices(); i++) {
            // 松弛：每个节点 j，找到它的相邻定点 to，判断经由 j 到达 to 的路径是否更短
            // 每次需要判断 j 本身是否可达
            // 所以相当于第一次循环（i）时只有 j == start 节点有效，找到邻接源点的点而已
            // 之后的每一次完整遍历（i）相当于从源点延伸
            for (int j = 0; j < graph.getVertices(); j++) {
                // j 需要可达
                if (from[j] == null) {
                    continue;
                }
                graph.getAdjacencyVertices(j).forEach(this::relaxation);
            }
        }

        // 再执行第 v 次，判断是否有负权环
        judge:
        for (int i = 0; i < graph.getVertices(); i++) {
            if (from[i] == null) {
                continue;
            }
            for (Edge<W> wEdge : graph.getAdjacencyVertices(i)) {
                if (relaxation(wEdge)) {
                    hasNegativeCycle = true;
                    break judge;
                }
            }
        }
    }

    /**
     * 松弛操作
     *
     * @param edge 通向尝试松弛点的边
     * @return 是否松弛成功
     */
    private boolean relaxation(Edge<W> edge) {
        W w = edge.getWeight();
        int to = edge.getTo();
        if (from[to] == null
                || distance[to].doubleValue() > distance[edge.getFrom()].doubleValue() + w.doubleValue()) {
            distance[to] = distance[edge.getFrom()].doubleValue() + w.doubleValue();
            from[to] = edge;
            return true;
        }
        return false;
    }

    /**
     * 是否存在负权环
     *
     * @return 是否存在负权环
     */
    public boolean isHasNegativeCycle() {
        return hasNegativeCycle;
    }

    /**
     * 获取目标点到源点的最短路径长度
     *
     * @param dest 目标点索引
     * @return 最短路径长度
     */
    public Number shortestPathTo(int dest) {
        assert hasPath(dest);
        return distance[dest];
    }

    /**
     * 判断目标点与源点之间是否存在路径
     *
     * @param dest 目标点索引
     * @return 最短路径长度
     */
    public boolean hasPath(int dest) {
        assert !isHasNegativeCycle();
        assert dest >= 0 && dest < graph.getVertices();
        return from[dest] != null;
    }

    /**
     * 迭代到目标点的最短路径
     *
     * @param dest 目标点
     * @return 路径迭代
     */
    public Iterable<Edge<W>> getShortestPath(int dest) {
        assert !isHasNegativeCycle();
        assert dest >= 0 && dest < graph.getVertices();
        Deque<Edge<W>> deque = new ArrayDeque<>();
        Edge<W> e;
        while ((e = from[dest]).getFrom() != dest) {
            deque.addFirst(e);
            dest = e.getFrom();
        }
        return deque;
    }

    public void printShortestPath(int dest) {
        System.out.print(start);
        getShortestPath(dest).forEach(e ->
            System.out.print(" -> " + e.getTo())
        );
        System.out.println();
    }


}
