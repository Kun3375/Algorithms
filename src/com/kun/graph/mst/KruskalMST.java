package com.kun.graph.mst;

import com.kun.graph.weighted.Edge;
import com.kun.graph.weighted.WeightedGraph;
import com.kun.heap.IndexMinHeap;
import com.kun.uf.PathCompressionUnionFind;
import com.kun.uf.UnionFind;

import java.util.ArrayList;
import java.util.List;

/**
 * 基于 Kruskal 算法的最小生成树求解方案
 *
 * @author CaoZiye
 * @version 1.0 2018/3/13 0:53
 */
public class KruskalMST<W extends Number & Comparable<W>> {
    
    private List<Edge<W>> mst;
    
    private Number totalWeight;
    
    @SuppressWarnings("unchecked")
    public KruskalMST (WeightedGraph<W> graph) {
        mst = new ArrayList<>();
        // 按权值进行一次堆排序
        IndexMinHeap<Edge<W>> heap = (IndexMinHeap<Edge<W>>) new IndexMinHeap(Edge.class, graph.getEdges());
        for (int i = 0; i < graph.getVertices(); i++) {
            graph.getAdjacencyVertices(i).forEach(e -> {
                if (e.getTo() > e.getFrom()) {
                    heap.add(e);
                }
            });
        }
        // 按节点作一个并查集
        UnionFind unionFind = new PathCompressionUnionFind(graph.getVertices());
        // 取出最小的边，只要不构成环，即是最小生成树的一部分
        // 是否构成环，使用并查集判断
        while (!heap.isEmpty()) {
            Edge<W> edge = heap.pop();
            if (!unionFind.isConnected(edge.getFrom(), edge.getTo())) {
                mst.add(edge);
                unionFind.union(edge.getFrom(), edge.getTo());
            }
        }
    
        // 计算权重
        this.totalWeight = mst.stream()
                .map(e -> (Number) e.getWeight())
                .reduce(0, (a, b) -> a.doubleValue() + b.doubleValue());
    }
    
    public List<Edge<W>> getMst() {
        return mst;
    }
    
    public Number getTotalWeight() {
        return totalWeight;
    }
    
}
