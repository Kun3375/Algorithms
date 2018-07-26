package com.kun.graph.path;

import com.kun.graph.weighted.DenseWeightedGraph;
import com.kun.graph.weighted.Edge;
import com.kun.graph.weighted.SparseWeightedGraph;
import com.kun.graph.weighted.WeightedGraph;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author CaoZiye
 * @version 1.0 2018/3/17 14:11
 */
public class TestShortestPath {

    private static WeightedGraph<Double> g1;
    private static WeightedGraph<Double> g2;

    @SuppressWarnings("unchecked")
    @BeforeClass
    public static void init() {
        g1 = new SparseWeightedGraph<>(5, false);
        g1.addEdge(new Edge(0, 1, 5));
        g1.addEdge(new Edge(0, 2, 2));
        g1.addEdge(new Edge(0, 3, 6));
        g1.addEdge(new Edge(1, 4, 1));
        g1.addEdge(new Edge(2, 1, 1));
        g1.addEdge(new Edge(2, 4, 5));
        g1.addEdge(new Edge(2, 3, 3));
        g1.addEdge(new Edge(3, 4, 2));
        g2 = new DenseWeightedGraph<>(5, true);
        g2.addEdge(new Edge(0, 1, 5));
        g2.addEdge(new Edge(0, 2, 2));
        g2.addEdge(new Edge(0, 3, 6));
        g2.addEdge(new Edge(1, 2, -4));
        g2.addEdge(new Edge(1, 4, 2));
        g2.addEdge(new Edge(2, 4, 5));
        g2.addEdge(new Edge(2, 3, 3));
        g2.addEdge(new Edge(4, 3, -3));
    }

    @Test
    public void testDijkstra() {
        DijkstraPath<Double> dijkstraPath = new DijkstraPath<>(g1, 0);
        for (int i = 1; i < g1.getVertices(); i++) {
            System.out.println(dijkstraPath.shortestPathTo(i));
            dijkstraPath.printShortestPath(i);
        }
    }

    @Test
    public void testBellmanFord() {
        BellmanFordPath<Double> bellmanFordPath = new BellmanFordPath<>(g2, 0);
        System.out.println("has negative cycle ? " + bellmanFordPath.isHasNegativeCycle());
        for (int i = 1; i < g2.getVertices(); i++) {
            System.out.println(bellmanFordPath.shortestPathTo(i));
            bellmanFordPath.printShortestPath(i);
        }
    }

}
