package com.kun.uf;

/**
 * 并查集接口
 *
 * @author CaoZiye
 * @version 1.0 2018/3/7 22:53
 */
public interface UnionFind {
    
    /**
     * 查询所属集
     * @param index 元素索引
     * @return 所属集编号
     */
    int find(int index);
    
    /**
     * 判断两个元素是否相连（属于同一并查集）
     * @param i 元素一索引
     * @param j 元素二索引
     * @return 如果相连返回 true，否则返回 false
     */
    default boolean isConnected(int i, int j) {
        return find(i) == find(j);
    }
    
    /**
     * 将两个元素合并
     * @param i 元素一索引
     * @param j 元素二索引
     */
    void union(int i, int j);
    
}
