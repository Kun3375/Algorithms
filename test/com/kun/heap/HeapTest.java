package com.kun.heap;

import org.junit.After;
import org.junit.Test;

import java.util.Random;

/**
 * @author CaoZiye
 * @version 1.0 2018/2/25 15:53
 */
public class HeapTest {
    
    private int capacity = 10000;
    private MaxHeap maxHeap;
    
    @After
    public void printHeap() {
        for (int i = 0; i < capacity; i++) {
            System.out.print(maxHeap.pop() + ",");
        }
        System.out.println();
    }
    
    @Test
    public void generateHeapByCapacity() {
        maxHeap = new MaxHeap(capacity);
        Random random = new Random();
        for (int i = 0; i < capacity; i++) {
            maxHeap.add(random.nextInt(capacity));
        }
    }
    
    @Test
    public void generateHeapByHeapify() {
        int[] ints = new int[capacity];
        Random random = new Random();
        for (int i = 0; i < ints.length; i++) {
            ints[i] = random.nextInt(capacity);
        }
        maxHeap = new MaxHeap(ints);
    }
    
}
