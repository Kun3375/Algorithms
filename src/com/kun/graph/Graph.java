package com.kun.graph;

/**
 * @author CaoZiye
 * @version 1.0 2018/3/9 23:54
 */
public interface Graph {
    
    int getVertex();
    
    int getEdge();
    
    void addEdge(int i, int j);
    
    boolean hasEdge(int i, int j);
    
    Iterable<Integer> getAdjacencyVertices(int i);
    
    void show();
    
}
