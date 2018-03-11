package com.kun.graph.weighted;

/**
 * 带权图，每个边含有自己的权重
 *
 * @author CaoZiye
 * @version 1.0 2018/3/11 11:21
 */
public interface WeightedGraph<W extends Number & Comparable<W>> {
    
    int getVertices();
    
    int getEdges();
    
    void addEdge(Edge<W> edge);
    
    boolean hasEdge(int i, int j);
    
    Iterable<Edge<W>> getAdjacencyVertices(int i);
    
    void show();
}
