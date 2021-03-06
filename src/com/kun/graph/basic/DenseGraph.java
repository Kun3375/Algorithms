package com.kun.graph.basic;

import java.util.ArrayList;

/**
 * 使用邻接矩阵 adjacency matrix 实现图
 * 适合稠密图的场合
 *
 * @author CaoZiye
 * @version 1.0 2018/3/8 22:50
 */
public class DenseGraph implements Graph {

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
    private boolean[][] matrix;

    public DenseGraph(int vertices, boolean directed) {
        assert vertices >= 0;
        this.vertices = vertices;
        this.edges = 0;
        this.directed = directed;
        this.matrix = new boolean[vertices][vertices];
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
        if (hasEdge(i, j)) {
            return;
        }
        edges++;
        matrix[i][j] = true;
        if (!directed) {
            matrix[j][i] = true;
        }
    }

    @Override
    public boolean hasEdge(int i, int j) {
        assert i >= 0 && i < vertices;
        assert j >= 0 && j < vertices;

        return matrix[i][j];
    }

    @Override
    public Iterable<Integer> getAdjacencyVertices(int i) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int j = 0; j < matrix[i].length; j++) {
            if (matrix[i][j]) {
                list.add(j);
            }
        }
        return list;
    }

    @Override
    public void show() {

        System.out.print("   ");
        for (int i = 0; i < vertices; i++) {
            System.out.print(String.format("% 4d", i));
        }
        System.out.println();

        for (int i = 0; i < matrix.length; i++) {
            System.out.print(String.format("% 3d", i));
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(String.format("% 4d", (matrix[i][j] ? 1 : 0)));
            }
            System.out.println();
        }
    }


}





