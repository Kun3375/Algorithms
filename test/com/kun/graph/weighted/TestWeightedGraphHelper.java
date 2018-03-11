package com.kun.graph.weighted;

import com.kun.graph.*;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author CaoZiye
 * @version 1.0 2018/3/10 13:57
 */
public class TestWeightedGraphHelper {
    
    private static DenseWeightedGraph<Double> DG = new DenseWeightedGraph<>(8, false);
    private static WeightedGraph<Double> SG = new SparseWeightedGraph<>(8, false);
    
    @BeforeClass
    public static void init() {
        DG.addEdge(new Edge<>(4, 5, 0.35));
        DG.addEdge(new Edge<>(4, 7, 0.37));
        DG.addEdge(new Edge<>(5, 7, 0.28));
        DG.addEdge(new Edge<>(0, 7, 0.16));
        DG.addEdge(new Edge<>(1, 5, 0.32));
        DG.addEdge(new Edge<>(0, 4, 0.38));
        DG.addEdge(new Edge<>(2, 3, 0.17));
        DG.addEdge(new Edge<>(1, 7, 0.19));
        DG.addEdge(new Edge<>(0, 2, 0.26));
        DG.addEdge(new Edge<>(1, 2, 0.36));
        DG.addEdge(new Edge<>(1, 3, 0.29));
        DG.addEdge(new Edge<>(2, 7, 0.34));
        DG.addEdge(new Edge<>(6, 2, 0.40));
        DG.addEdge(new Edge<>(3, 6, 0.52));
        DG.addEdge(new Edge<>(6, 0, 0.58));
        DG.addEdge(new Edge<>(6, 4, 0.93));
        SG.addEdge(new Edge<>(4, 5, 0.35));
        SG.addEdge(new Edge<>(4, 7, 0.37));
        SG.addEdge(new Edge<>(5, 7, 0.28));
        SG.addEdge(new Edge<>(0, 7, 0.16));
        SG.addEdge(new Edge<>(1, 5, 0.32));
        SG.addEdge(new Edge<>(0, 4, 0.38));
        SG.addEdge(new Edge<>(2, 3, 0.17));
        SG.addEdge(new Edge<>(1, 7, 0.19));
        SG.addEdge(new Edge<>(0, 2, 0.26));
        SG.addEdge(new Edge<>(1, 2, 0.36));
        SG.addEdge(new Edge<>(1, 3, 0.29));
        SG.addEdge(new Edge<>(2, 7, 0.34));
        SG.addEdge(new Edge<>(6, 2, 0.40));
        SG.addEdge(new Edge<>(3, 6, 0.52));
        SG.addEdge(new Edge<>(6, 0, 0.58));
        SG.addEdge(new Edge<>(6, 4, 0.93));
    }
    
    @Test
    public void test() {
        SG.show();
        System.out.println("=================");
        DG.show();
    }
    
    
}
