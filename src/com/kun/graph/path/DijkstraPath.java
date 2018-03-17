package com.kun.graph.path;

import com.kun.graph.weighted.Edge;
import com.kun.graph.weighted.WeightedGraph;
import com.kun.heap.IndexMinHeap;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 求解单源最短路径问题
 * Dijkstra 算法，可以处理有向图以及无向图
 *
 * 缺陷：不能处理存在负权边的图
 * 时间复杂度：O(ELogV)
 *
 * @author CaoZiye
 * @version 1.0 2018/3/16 20:29
 */
public class DijkstraPath<W extends Number & Comparable<W>> {
    
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
     * 标记节点是否已经找到最短路径
     */
    private boolean[] marked;
    /**
     * 记录单条路径的源，可以逆向追溯整条最短路径
     */
    private Edge<W>[] from;
    
    @SuppressWarnings("unchecked")
    public DijkstraPath(WeightedGraph<W> graph, int start) {
        assert start >= 0 && start < graph.getVertices();
        // 初始化
        this.graph = graph;
        this.start = start;
        this.distance = new Number[graph.getVertices()];
        this.marked = new boolean[graph.getVertices()];
        this.from = (Edge<W>[]) new Edge[graph.getVertices()];
        
        IndexMinHeap<W> indexMinHeap = new IndexMinHeap(Comparable.class, graph.getVertices());
        // 源点的处理
        distance[start] = 0;
        from[start] = new Edge<>(start, start, (W) (Number) 0);
        marked[start] = true;
        indexMinHeap.insert((W) distance[start], start);
        
        while (!indexMinHeap.isEmpty()) {
            // 取出堆中最短的路径（这个节点没有更短的了）
            int index = indexMinHeap.popIndex();
            marked[index] = true;
            
            // 遍历该节点相邻的节点，查看相邻节点是否需要松弛操作
            graph.getAdjacencyVertices(index).forEach((e) -> {
                int to = e.getTo();
                // 该相邻点如果没有找到最短路径，继续
                if (!marked[to]) {
                    W weight = e.getWeight();
                    // 该节点首次路过 或者 新的路径更短
                    if (distance[to] == null ||
                            distance[to].doubleValue() > distance[index].doubleValue() + weight.doubleValue()) {
                        distance[to] = distance[index].doubleValue() + weight.doubleValue();
                        from[to] = e;
                        if (indexMinHeap.contain(to)) {
                            indexMinHeap.change(to, (W) distance[to]);
                            return;
                        }
                        indexMinHeap.insert((W) distance[to], to);
                    }
                }
            });
        }
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
        assert dest >= 0 && dest < graph.getVertices();
        return marked[dest];
    }
    
    /**
     * 迭代到目标点的最短路径
     *
     * @param dest 目标点
     * @return 路径迭代
     */
    public Iterable<Edge<W>> getShortestPath(int dest) {
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
        getShortestPath(dest).forEach(e -> {
            System.out.print(" -> " + e.getTo());
        });
        System.out.println();
    }
    
}
