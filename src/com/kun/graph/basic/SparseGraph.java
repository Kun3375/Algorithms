package com.kun.graph.basic;

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
public class SparseGraph implements Graph {

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
    private List<Integer>[] list;

    public SparseGraph(int vertices, boolean directed) {
        this.vertices = vertices;
        this.edges = 0;
        this.directed = directed;
        list = Stream
                .generate(ArrayList<Integer>::new)
                .limit(vertices)
                .toArray((IntFunction<List<Integer>[]>) ArrayList[]::new);
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
    public void addEdge(int i, int j) {
        assert i >= 0 && i < vertices;
        assert j >= 0 && j < vertices;

        // 由于 hasEdge() 复杂读较高，通常不使用它来避免平行边
        // 直接添加边，而对于平行边的情况，最后过滤

        edges++;
        list[i].add(j);
        // 注意自环边可能被重复添加
        if (i != j && !directed) {
            list[j].add(i);
        }

    }

    @Override
    public boolean hasEdge(int i, int j) {
        assert i >= 0 && i < vertices;
        assert j >= 0 && j < vertices;

        return list[i].stream().anyMatch(e -> e == j);
    }

    @Override
    public Iterable<Integer> getAdjacencyVertices(int i) {
        assert i >= 0 && i < vertices;
        return list[i];
    }

    @Override
    public void show() {
        for (int i = 0; i < list.length; i++) {
            System.out.print(String.format("% 3d", i) + ": ");
            list[i].forEach(j -> System.out.print(String.format("% 4d", j)));
            System.out.println();
        }
    }

}
