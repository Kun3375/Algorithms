package com.kun.graph.mst;

import com.kun.graph.weighted.Edge;
import com.kun.graph.weighted.WeightedGraph;
import com.kun.heap.MinHeap;

import java.util.ArrayList;
import java.util.List;

/**
 * 求图的最小生成树
 * Lazy Prim 算法
 * 基于堆实现 O(ELogE)
 *
 * @author CaoZiye
 * @version 1.0 2018/3/11 20:47
 */
public class LazyPrimMST<W extends Number & Comparable<W>> {
    
    /**
     * 要处理的图的引用
     */
    private WeightedGraph<W> graph;
    /**
     * 一个优先队列（最小堆实现），用来存放和选取权重最小的切边
     */
    private MinHeap<Edge<W>> priorityQueue;
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
    
    private LazyPrimMST() {
    }
    
    @SuppressWarnings("unchecked")
    public LazyPrimMST(WeightedGraph<W> graph) {
        this.graph = graph;
        // 无法构造 MinHeap<Edge<W>>，也无法从 MinHeap<Edge> -> MinHeap<Edge<W>>
        // 先擦出泛型再强转...这波操作...
        MinHeap temp = new MinHeap<>(Edge.class, graph.getEdges());
        this.priorityQueue = (MinHeap<Edge<W>>) temp;
        this.marked = new boolean[graph.getVertices()];
        this.mst = new ArrayList<>();
        
        marked[0] = true;
        graph.getAdjacencyVertices(0).forEach(priorityQueue::add);
        
        while (!priorityQueue.isEmpty()) {
            // 从最小堆中拿出一次切分情况下的最小权重边
            Edge<W> minEdge = priorityQueue.pop();
            // 若果这条边的两端节点都已经被访问过，舍弃
            if (marked[minEdge.getTo()]) {
                continue;
            }
            // 符合要求
            mst.add(minEdge);
            visit(minEdge.getTo());
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
        this.graph.getAdjacencyVertices(vertex).forEach(e -> {
            if (!marked[e.getTo()]) {
                this.priorityQueue.add(e);
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
