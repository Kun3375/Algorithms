package com.kun.heap;

/**
 * @author CaoZiye
 * @version 1.0 2018/3/3 14:13
 */
public interface Heap<E extends Comparable<E>> {
    
    int size();
    
    int capacity();
    
    boolean isEmpty();
    
    E pop();
    
    E peek();
    
    void add(E e);
    
    E[] sort();
    
}
