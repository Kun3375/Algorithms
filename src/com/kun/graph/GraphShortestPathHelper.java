package com.kun.graph;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author CaoZiye
 * @version 1.0 2018/3/10 12:27
 */
public class GraphShortestPathHelper {
    
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
    
    /**
     * 记录每个节点到初始化节点的距离
     */
    private int[] distance;
    
    private GraphShortestPathHelper() {
    }
    
    public static GraphShortestPathHelper build(Graph graph, int vertex) {
        GraphShortestPathHelper helper = new GraphShortestPathHelper();
        helper.graph = graph;
        helper.visited = new boolean[graph.getVertex()];
        helper.from = new int[graph.getVertex()];
        helper.distance = new int[graph.getVertex()];
        for (int i = 0; i < helper.from.length; i++) {
            helper.from[i] = -1;
            helper.distance[i] = -1;
        }
        helper.bfs(vertex);
        return helper;
    }
    
    /**
     * 广度优先遍历方案，需要额外维护一个队列，但是可以记录最短路径（之一）
     *
     * @param vertex 节点
     */
    private void bfs(int vertex) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(vertex);
        visited[vertex] = true;
        distance[vertex] = 0;
        
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            graph.getAdjacencyVertices(poll).forEach(v -> {
                if (!visited[v]) {
                    queue.offer(v);
                    from[v] = poll;
                    visited[v] = true;
                    distance[v] = distance[poll] + 1;
                }
            });
        }
    }
    
    /**
     * 取得初始点到目标点的最短距离
     *
     * @param dest 目标点索引
     * @return 返回最短距离，路径不存在时返回 -1
     */
    public int getDistance(int dest) {
        assert dest >= 0 && dest < graph.getVertex();
        return distance[dest];
    }
    
    /**
     * 判断从初始化点到目标点是否存在路径
     *
     * @param dest 目标点索引
     * @return 存在路径返回 true；否则返回 false
     */
    public boolean hasPath(int dest) {
        assert dest >= 0 && dest < graph.getVertex();
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
     *
     * @param dest 目标点索引
     */
    public void printPath(int dest) {
        StringBuilder sb = new StringBuilder();
        getPath(dest).forEach(v -> sb.append(v).append(" -> "));
        sb.delete(sb.length() - 4, sb.length());
        System.out.println(sb);
    }
    
}
