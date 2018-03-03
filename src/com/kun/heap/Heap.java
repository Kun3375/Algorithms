package com.kun.heap;

/**
 * @author CaoZiye
 * @version 1.0 2018/3/3 14:13
 */
public interface Heap {
    
    int size();
    
    int capacity();
    
    boolean isEmpty();
    
    int pop();
    
    int peek();
    
    void add(int e);
    
    int[] sort();
    
}
