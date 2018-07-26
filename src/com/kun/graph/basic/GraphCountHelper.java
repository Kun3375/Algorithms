package com.kun.graph.basic;

/**
 * 对一个图进行深度优先遍历，
 * 可以记录图的联通分量
 * 也可以保存节点之间的联通关系
 *
 * @author CaoZiye
 * @version 1.0 2018/3/10 12:27
 */
public class GraphCountHelper {

    /**
     * 保存图的引用
     */
    private Graph graph;

    /**
     * 保存每个节点是否被遍历过
     */
    private boolean[] visited;

    /**
     * 联通分量
     */
    private int count;

    /**
     * 借鉴并查集，记录每个节点的所属集
     * 联通的节点都有相同的 id
     */
    private int[] id;

    private GraphCountHelper() {
    }

    public static GraphCountHelper build(Graph graph) {
        GraphCountHelper graphCountHelper = new GraphCountHelper();
        graphCountHelper.graph = graph;
        graphCountHelper.visited = new boolean[graph.getVertices()];
        graphCountHelper.count = 0;
        graphCountHelper.id = new int[graph.getVertices()];
        for (int i = 0; i < graphCountHelper.id.length; i++) {
            graphCountHelper.id[i] = i;
        }
        graphCountHelper.traverse();
        return graphCountHelper;
    }

    /**
     * 遍历入口
     */
    private void traverse() {
        for (int i = 0; i < graph.getVertices(); i++) {
            if (!visited[i]) {
                count++;
                dfs(i);
            }
        }
    }

    /**
     * depth first search 深度优先遍历方案
     *
     * @param vertex 节点
     */
    private void dfs(int vertex) {
        visited[vertex] = true;
        id[vertex] = count;
        graph.getAdjacencyVertices(vertex).forEach(v -> {
            if (!visited[v]) {
                dfs(v);
            }
        });
    }

    /**
     * 取得图的联通分量
     *
     * @return 联通分量
     */
    public int getCount() {
        return count;
    }

    /**
     * 判断图中两个节点是否联通
     *
     * @param i 节点索引
     * @param j 节点索引
     * @return 联通，返回 true；否则返回 false
     */
    public boolean isConnected(int i, int j) {
        assert i >= 0 && i < graph.getVertices();
        assert j >= 0 && j < graph.getVertices();
        return id[i] == id[j];
    }

}
