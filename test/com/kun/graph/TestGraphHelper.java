package com.kun.graph;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author CaoZiye
 * @version 1.0 2018/3/10 13:57
 */
public class TestGraphHelper {
    
    private static Graph DG1 = new DenseGraph(13, false);
    private static Graph DG2 = new DenseGraph(6, false);
    private static Graph SG1 = new SparseGraph(13, false);
    private static Graph SG2 = new SparseGraph(6, false);
    
    @BeforeClass
    public static void init() {
        DG1.addEdge(0, 5);
        DG1.addEdge(4, 3);
        DG1.addEdge(0, 1);
        DG1.addEdge(9, 12);
        DG1.addEdge(6, 4);
        DG1.addEdge(5, 4);
        DG1.addEdge(0, 2);
        DG1.addEdge(11, 12);
        DG1.addEdge(9, 10);
        DG1.addEdge(0, 6);
        DG1.addEdge(7, 8);
        DG1.addEdge(9, 11);
        DG1.addEdge(5, 3);
        SG1.addEdge(0, 5);
        SG1.addEdge(4, 3);
        SG1.addEdge(0, 1);
        SG1.addEdge(9, 12);
        SG1.addEdge(6, 4);
        SG1.addEdge(5, 4);
        SG1.addEdge(0, 2);
        SG1.addEdge(11, 12);
        SG1.addEdge(9, 10);
        SG1.addEdge(0, 6);
        SG1.addEdge(7, 8);
        SG1.addEdge(9, 11);
        SG1.addEdge(5, 3);
        
        DG2.addEdge(0, 1);
        DG2.addEdge(0, 2);
        DG2.addEdge(0, 5);
        DG2.addEdge(1, 2);
        DG2.addEdge(1, 3);
        DG2.addEdge(1, 4);
        DG2.addEdge(3, 4);
        DG2.addEdge(3, 5);
        SG2.addEdge(0, 1);
        SG2.addEdge(0, 2);
        SG2.addEdge(0, 5);
        SG2.addEdge(1, 2);
        SG2.addEdge(1, 3);
        SG2.addEdge(1, 4);
        SG2.addEdge(3, 4);
        SG2.addEdge(3, 5);
    }
    
    @Test
    public void testCount() {
        GraphCountHelper helper1 = GraphCountHelper.build(SG1);
        System.out.println(helper1.getCount());
        GraphCountHelper helper2 = GraphCountHelper.build(DG2);
        System.out.println(helper2.getCount());
        System.out.println(helper1.isConnected(5, 6));
        System.out.println(helper1.isConnected(5, 7));
    }
    
    @Test
    public void testPath() {
        GraphPathHelper helper = GraphPathHelper.build(SG2, 0);
        System.out.println(helper.hasPath(5));
        helper.printPath(5);
        helper.getPath(5).forEach(System.out::print);
    }
    
    @Test
    public void testShortestPath() {
        GraphShortestPathHelper helper = GraphShortestPathHelper.build(DG2, 0);
        System.out.println(helper.getDistance(5));
        helper.printPath(5);
    }
    
    
}
