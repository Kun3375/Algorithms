package com.kun.graph.mst;

import com.kun.graph.weighted.Edge;
import com.kun.graph.weighted.WeightedGraph;
import com.kun.heap.IndexMinHeap;

import java.util.ArrayList;
import java.util.List;

/**
 * 求图的最小生成树
 * 改进的 Prim 算法
 * 由堆辅助实现改进为索引堆实现 O(ELogV)
 * 使用索引堆的优势：可以按节点索引，不用再添加重复的边；基于索引堆，动态刷新节点的最小边候选，效率高
 *
 * @author CaoZiye
 * @version 1.0 2018/3/11 20:47
 */
public class PrimMST<W extends Number & Comparable<W>> {

    /**
     * 要处理的图的引用
     */
    private WeightedGraph<W> graph;
    /**
     * 一个优先队列（最小堆实现），用来存放和选取权重最小的切边
     */
    private IndexMinHeap<Edge<W>> priorityQueue;
    /**
     * 用来标记相应的节点是否被访问过
     */
    private boolean[] marked;
    /**
     * 记录下最小生成树的所有边
     */
    private List<Edge<W>> mst;
    /**
     * 最小生成树的总权重
     */
    private Number totalWeight;

    private PrimMST() {
    }

    @SuppressWarnings("unchecked")
    public PrimMST(WeightedGraph<W> graph) {
        this.graph = graph;
        // 使用索引堆的实现
        IndexMinHeap temp = new IndexMinHeap<>(Edge.class, graph.getVertices());
        this.priorityQueue = (IndexMinHeap<Edge<W>>) temp;
        this.marked = new boolean[graph.getVertices()];
        this.mst = new ArrayList<>();

        visit(0);

        while (!priorityQueue.isEmpty()) {
            Edge<W> edge = priorityQueue.pop();
            mst.add(edge);
            visit(edge.getTo());
        }

        // 计算权重
        this.totalWeight = mst.stream()
                .map(e -> (Number) e.getWeight())
                .reduce(0, (a, b) -> a.doubleValue() + b.doubleValue());
    }

    /**
     * 访问节点的必要操作
     *
     * @param vertex 访问的节点
     */
    private void visit(int vertex) {
        this.marked[vertex] = true;
        // 取得 v 节点所有的边
        this.graph.getAdjacencyVertices(vertex).forEach(e -> {
            int to = e.getTo();
            // 判断对面定点是否被访问过，访问过了就不需要处理了，因为这个边不是横切边了
            if (!marked[to]) {
                // 这个对节点没有最小边的记录，就新增记录
                if (!priorityQueue.contain(to)) {
                    priorityQueue.insert(e, to);
                } else if (priorityQueue.peekIndex(to).getWeight().compareTo(e.getWeight()) > 0) {
                    // 如果新发现的边比曾经记录的权值更小，需要更新
                    priorityQueue.change(to, e);
                }
            }
        });
    }

    public List<Edge<W>> getMst() {
        return mst;
    }

    public Number getTotalWeight() {
        return totalWeight;
    }

}
