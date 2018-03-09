package com.kun.uf;

/**
 * @author CaoZiye
 * @version 1.0 2018/3/7 23:08
 */
public class PathCompressionUnionFind extends OptimizedUnionFind {
    
    public PathCompressionUnionFind(int size) {
        super(size);
    }
    
    /**
     * 在 find 操作时候使用路径压缩
     * @param index 元素索引
     * @return 所属集编号
     */
    @Override
    public int find(int index) {
        assert index >= 0 && index < parent.length;
        
        // 路径压缩的递归实现，可以实现 rank === 1
//        if (parent[index] != index) {
//            parent[index] = find(parent[index]);
//        }
//        return parent[index];
        
        // 路径压缩基本实现，跳级
        while (parent[index] != index) {
            parent[index] = parent[parent[index]];
            index = parent[index];
        }
        return index;
    }
}
