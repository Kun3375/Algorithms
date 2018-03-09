package com.kun.uf;

/**
 * 并查集基础实现
 * 查询元素所属集（find），O(1) 速度
 * 合并操作性能糟糕，O(n)
 *
 * @author CaoZiye
 * @version 1.0 2018/3/7 22:39
 */
public class QuickFind implements UnionFind {
    
    private int[] id;
    
    public QuickFind(int size) {
        assert size >= 0;
        id = new int[size];
        // 初始化元素所属集为元素索引，各不相同
        for (int i = 0; i < id.length; i++) {
            id[i] = i;
        }
    }
    
    @Override
    public int find(int index) {
        assert index >= 0 && index < id.length;
        return  id[index];
    }
    
    @Override
    public void union(int i, int j) {
        assert i >= 0 && j < id.length;
        assert j >= 0 && j < id.length;
        
        int iId = id[i];
        for (int index = 0; index < id.length; index++) {
            if (id[index] == iId) {
                id[index] = id[j];
            }
        }
    }
    
}
