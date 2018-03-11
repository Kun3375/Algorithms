package com.kun.heap;

/**
 * @author CaoZiye
 * @version 1.0 2018/3/11 22:41
 */
public interface IndexHeap<E extends Comparable<E>> extends Heap<E> {
    
    boolean contain(int index);
    
    void change(int index, E newOne);
    
}
