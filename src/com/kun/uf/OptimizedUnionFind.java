package com.kun.uf;

/**
 * @author CaoZiye
 * @version 1.0 2018/3/7 22:56
 */
public class OptimizedUnionFind implements UnionFind {
    
    /**
     * 保存父节点的索引值
     * 元素所属集为根节点索引值
     */
    protected int[] parent;
    
    /**
     * 保存对应索引的元素下树形的最大层数
     */
    protected int[] rank;
    
    public OptimizedUnionFind(int size) {
        assert size >= 0;
        parent = new int[size];
        rank = new int[size];
        // 初始化所有元素的所属集，各不相同
        // 初始化旗下层数 1
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }
    
    @Override
    public int find(int index) {
        assert index >= 0 && index < parent.length;
        // 循环找到根节点
        while (parent[index] != index) {
            index = parent[index];
        }
        return index;
    }
    
    @Override
    public void union(int i, int j) {
        assert i >= 0 && j < parent.length;
        assert j >= 0 && j < parent.length;
        
        int iRoot = find(i);
        int jRoot = find(j);
        if (iRoot == jRoot) {
            return;
        }
        // 连接时，将层数少的接在层数多的后面
        if (rank[iRoot] < rank[jRoot]) {
            parent[iRoot] = jRoot;
            return;
        }
        if (rank[iRoot] > rank[jRoot]) {
            parent[jRoot] = iRoot;
            return;
        }
        parent[iRoot] = jRoot;
        rank[jRoot]++;
    }
}
