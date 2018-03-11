package com.kun.graph.weighted;

/**
 * @author CaoZiye
 * @version 1.0 2018/3/11 11:22
 */
public class Edge<W extends Comparable<W>> implements Comparable<Edge<W>> {
    
    /**
     * 记录两边节点索引，作有向图时，顺序由要求
     */
    private int i, j;
    
    private W weight;
    
    public Edge() {
    }
    
    public Edge(int i, int j, W weight) {
        this.i = i;
        this.j = j;
        this.weight = weight;
    }
    
    public int getFrom() {
        return i;
    }
    
    public int getTo() {
        return j;
    }
    
    public int getOther(int one) {
        assert one == i || one == j;
        return one == i ? j : i;
    }
    
    public W getWeight() {
        return weight;
    }
    
    @Override
    public String toString() {
        return i + "->" + j + ":" + weight;
    }
    
    @Override
    public int compareTo(Edge<W> edge) {
        return this.weight.compareTo(edge.getWeight());
    }
}
