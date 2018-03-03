package com.kun.heap;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Random;

/**
 * @author CaoZiye
 * @version 1.0 2018/2/25 15:53
 */
public class HeapTest {
    
    private static int capacity = 10;
    private static int[] array = new int[capacity];
    
    @BeforeClass
    public static void init() {
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(array.length);
            System.out.print(array[i] + " ");
        }
        //        array = new int[] {8,9,2,5,4,8,9,3,2,0};
        System.out.println();
    }
    
    @Test
    public void testIndexMaxHeap() {
        IndexMapHeap indexMapHeap = new IndexMapHeap(array);
        printSort(indexMapHeap);
        printHeap(indexMapHeap);
        printIndexAndReverse(indexMapHeap);
        indexMapHeap.remove(4);
        printSort(indexMapHeap);
        printHeap(indexMapHeap);
        printIndexAndReverse(indexMapHeap);
        indexMapHeap.insert(9, 4);
        printSort(indexMapHeap);
        printHeap(indexMapHeap);
        printIndexAndReverse(indexMapHeap);
        indexMapHeap.pop();
        printSort(indexMapHeap);
        printHeap(indexMapHeap);
        printIndexAndReverse(indexMapHeap);
        while (!indexMapHeap.isEmpty()) {
            System.out.print(indexMapHeap.pop() + " ");
        }
        System.out.println();
        printHeap(indexMapHeap);
        printIndexAndReverse(indexMapHeap);
    }
    
    @Test
    public void testPop() {
        IndexMapHeap indexMapHeap = new IndexMapHeap(array);
        int count = 5;
        while (count-- > 0) {
            System.out.print(indexMapHeap.pop() + " ");
        }
        System.out.println();
        indexMapHeap.add(7);
        indexMapHeap.add(2);
        while (!indexMapHeap.isEmpty()) {
            System.out.print(indexMapHeap.pop() + " ");
        }
        System.out.println();
    }
    
    private void printSort(Heap heap) {
        int[] sortedData = heap.sort();
        for (int i = 0; i < sortedData.length; i++) {
            System.out.print(sortedData[i] + " ");
        }
        System.out.println();
    }
    
    private void printHeap(IndexMapHeap indexMapHeap) {
        for (int datum : indexMapHeap.data) {
            System.out.print(datum + " ");
        }
        System.out.println();
    }
    
    private void printIndexAndReverse(IndexMapHeap indexMapHeap) {
        for (int index : indexMapHeap.indexes) {
            System.out.print(index + " ");
        }
        System.out.println();
        for (int reverse : indexMapHeap.reverse) {
            System.out.print(reverse + " ");
        }
        System.out.println();
    }
    
}
