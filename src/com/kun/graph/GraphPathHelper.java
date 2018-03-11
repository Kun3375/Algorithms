package com.kun.graph;

import java.util.Deque;
import java.util.LinkedList;

/**
 *
 * @author CaoZiye
 * @version 1.0 2018/3/10 12:27
 */
public class GraphPathHelper {
    
    /**
     * 保存图的引用
     */
    private Graph graph;
    
    /**
     * 保存每个节点是否被遍历过
     */
    private boolean[] visited;
    
    /**
     * 记录该节点是从哪个节点连接而来
     * 可以用来取得节点路径
     */
    private int[] from;
    
    private GraphPathHelper() {
    }
    
    public static GraphPathHelper build(Graph graph, int vertex) {
        GraphPathHelper graphPathHelper = new GraphPathHelper();
        graphPathHelper.graph = graph;
        graphPathHelper.visited = new boolean[graph.getVertices()];
        graphPathHelper.from = new int[graph.getVertices()];
        for (int i = 0; i < graphPathHelper.from.length; i++) {
            graphPathHelper.from[i] = -1;
        }
        graphPathHelper.dfs(vertex);
        return graphPathHelper;
    }
    
    /**
     * depth first search 深度优先遍历方案
     * @param vertex 节点
     */
    private void dfs(int vertex) {
        visited[vertex] = true;
        graph.getAdjacencyVertices(vertex).forEach(v -> {
            if (!visited[v]) {
                from[v] = vertex;
                dfs(v);
            }
        });
    }
    
    /**
     * 判断从初始化点到目标点是否存在路径
     * @param dest 目标点索引
     * @return 存在路径返回 true；否则返回 false
     */
    public boolean hasPath(int dest) {
        assert dest >= 0  && dest < graph.getVertices();
        return visited[dest];
    }
    
    /**
     * 返回一个可迭代对象，描述从初始点到目标点的路径
     * 可迭代对象内包含路径上所有点的索引
     * 如果有多条路径，并不确定返回的路径
     *
     * @param dest 目标点索引
     * @return 路径迭代对象
     */
    public Iterable<Integer> getPath(int dest) {
        assert hasPath(dest);
    
        Deque<Integer> link = new LinkedList<>();
        link.add(dest);
        while ((dest = from[dest]) != -1) {
            link.addFirst(dest);
        }
        return link;
    }
    
    /**
     * 打印到目标点的路径
     * @param dest 目标点索引
     */
    public void printPath(int dest) {
        StringBuilder sb = new StringBuilder();
        getPath(dest).forEach(v -> sb.append(v).append(" -> "));
        sb.delete(sb.length() - 4, sb.length());
        System.out.println(sb);
    }
    
}
