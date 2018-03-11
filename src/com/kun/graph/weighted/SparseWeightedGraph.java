package com.kun.graph.weighted;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.Stream;

/**
 * 使用邻接表 adjacency list 实现图
 * 适合稀疏图的场合
 *
 * @author CaoZiye
 * @version 1.0 2018/3/8 23:11
 */
public class SparseWeightedGraph<W extends Comparable<W>> implements WeightedGraph<W> {
    
    /**
     * 顶点数
     */
    private int vertices;
    
    /**
     * 边数
     */
    private int edges;
    
    /**
     * 是否有向
     */
    private boolean directed;
    
    /**
     * 邻接表
     */
    private List<Edge<W>>[] list;
    
    public SparseWeightedGraph(int vertices, boolean directed) {
        this.vertices = vertices;
        this.edges = 0;
        this.directed = directed;
        list = Stream
                .generate(ArrayList<Edge<W>>::new)
                .limit(vertices)
                .toArray((IntFunction<List<Edge<W>>[]>) ArrayList[]::new);
    }
    
    @Override
    public int getVertices() {
        return vertices;
    }
    
    @Override
    public int getEdges() {
        return edges;
    }
    
    @Override
    public void addEdge(Edge<W> edge) {
        int f = edge.getFrom();
        int t = edge.getTo();
        assert f >= 0 && f < vertices;
        assert t >= 0 && t < vertices;
        
        // 邻接表实现，在添加边的时候不考虑平行边
        edges++;
        list[f].add(edge);
        if (t != f && !directed) {
            list[t].add(new Edge<>(t, f, edge.getWeight()));
        }
    }
    
    @Override
    public boolean hasEdge(int i, int j) {
        assert i >= 0 && i < vertices;
        assert j >= 0 && j < vertices;
        
        return list[i].stream().anyMatch(e -> e.getTo() == j);
    }
    
    @Override
    public Iterable<Edge<W>> getAdjacencyVertices(int i) {
        assert i >= 0 && i < vertices;
        return list[i];
    }
    
    @Override
    public void show() {
        for (int i = 0; i < list.length; i++) {
            System.out.print(String.format("% 3d", i) + ": ");
            list[i].forEach(e -> System.out.print(e + "  "));
            System.out.println();
        }
    }
    
}
