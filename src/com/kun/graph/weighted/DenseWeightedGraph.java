package com.kun.graph.weighted;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 使用邻接矩阵 adjacency matrix 实现图
 * 适合稠密图的场合
 *
 * @author CaoZiye
 * @version 1.0 2018/3/8 22:50
 */
public class DenseWeightedGraph<W extends Number & Comparable<W>> implements WeightedGraph<W> {
    
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
     * 邻接矩阵
     * 使用布尔，可以直接过滤掉平行边的情况
     * 如果需要考虑平行边，可以使用整型
     */
    private Edge<W>[][] matrix;
    
    public DenseWeightedGraph(int vertices, boolean directed) {
        assert vertices >= 0;
        this.vertices = vertices;
        this.edges = 0;
        this.directed = directed;
        //noinspection unchecked
        this.matrix = new Edge[vertices][vertices];
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
        
        // 新的重复的边的添加，覆盖原来的
        
        edges++;
        matrix[f][t] = edge;
        if (f != t && !directed) {
            matrix[t][f] = new Edge<>(t, f, edge.getWeight());
        }
    }
    
    @Override
    public boolean hasEdge(int i, int j) {
        assert i >= 0 && i < vertices;
        assert j >= 0 && j < vertices;
        
        return matrix[i][j] != null;
    }
    
    @Override
    public Iterable<Edge<W>> getAdjacencyVertices(int i) {
        ArrayList<Integer> list = new ArrayList<>();
        return Arrays.stream(matrix[i]).filter(Objects::nonNull).collect(Collectors.toList());
    }
    
    @Override
    public void show() {
        for (int i = 0; i < matrix.length; i++) {
            System.out.print(String.format("% 3d    ", i));
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] == null ? "          \t" : matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }
    
    
}





