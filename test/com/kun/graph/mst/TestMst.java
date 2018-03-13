package com.kun.graph.mst;

import com.kun.graph.weighted.Edge;
import com.kun.graph.weighted.SparseWeightedGraph;
import org.junit.Test;

/**
 * @author CaoZiye
 * @version 1.0 2018/3/11 22:12
 */
public class TestMst {

    private SparseWeightedGraph<Double> graph = new SparseWeightedGraph<>(8, false);
    
    {
        graph.addEdge(new Edge<>(4, 5, .35));
        graph.addEdge(new Edge<>(4, 7, .37));
        graph.addEdge(new Edge<>(5, 7, .28));
        graph.addEdge(new Edge<>(0, 7, .16));
        graph.addEdge(new Edge<>(1, 5, .32));
        graph.addEdge(new Edge<>(0, 4, .38));
        graph.addEdge(new Edge<>(2, 3, .17));
        graph.addEdge(new Edge<>(1, 7, .19));
        graph.addEdge(new Edge<>(0, 2, .26));
        graph.addEdge(new Edge<>(1, 2, .36));
        graph.addEdge(new Edge<>(1, 3, .29));
        graph.addEdge(new Edge<>(2, 7, .34));
        graph.addEdge(new Edge<>(6, 2, .40));
        graph.addEdge(new Edge<>(3, 6, .52));
        graph.addEdge(new Edge<>(6, 0, .58));
        graph.addEdge(new Edge<>(6, 4, .93));
    }
    
    @Test
    public void testLazyPrimMst() {
        LazyPrimMST<Double> mst = new LazyPrimMST<>(graph);
        mst.getMst().forEach(System.out::println);
        Number totalWeight = mst.getTotalWeight();
        System.out.println("totalWeight = " + totalWeight);
    }
    
    @Test
    public void testPrimMst() {
        PrimMST<Double> mst = new PrimMST<>(graph);
        mst.getMst().forEach(System.out::println);
        Number totalWeight = mst.getTotalWeight();
        System.out.println("totalWeight = " + totalWeight);
    }
    
    @Test
    public void testKruskal() {
        KruskalMST<Double> mst = new KruskalMST<>(graph);
        mst.getMst().forEach(System.out::println);
        Number totalWeight = mst.getTotalWeight();
        System.out.println("totalWeight = " + totalWeight);
    }
}
