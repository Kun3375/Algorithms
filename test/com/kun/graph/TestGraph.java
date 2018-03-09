package com.kun.graph;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Random;

/**
 * @author CaoZiye
 * @version 1.0 2018/3/9 23:30
 */
public class TestGraph {
    
    private static int vertex = 20;
    private static int edge = 100;
    private static DenseGraph denseGraph = new DenseGraph(vertex, false);
    private static SparseGraph sparseGraph = new SparseGraph(vertex, false);
    
    @BeforeClass
    public static void init() {
        initEdge(denseGraph);
        initEdge(sparseGraph);
    }
    
    @Test
    public void testSparsePrintAdjacencyList() {
        // 测试迭代
        for (int i = 0; i < vertex; i++) {
            System.out.print(i + ": ");
            Iterable<Integer> vertices = sparseGraph.getAdjacencyVertices(i);
            vertices.forEach(v -> System.out.print(v + " "));
            System.out.println();
        }
    }

    @Test
    public void testDensePrintAdjacencyList() {
        // 测试迭代
        for (int i = 0; i < vertex; i++) {
            System.out.print(i + ": ");
            Iterable<Integer> vertices = denseGraph.getAdjacencyVertices(i);
            vertices.forEach(v -> System.out.print(v + " "));
            System.out.println();
        }
    }
    
    private static void initEdge(Graph graph) {
        Random random = new Random();
        // 连接
        for (int i = 0; i < edge; i++) {
            graph.addEdge(random.nextInt(vertex), random.nextInt(vertex));
        }
    }

}
