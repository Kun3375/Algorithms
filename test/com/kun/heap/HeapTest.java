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
    private static Integer[] array = new Integer[capacity];

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
        IndexMaxHeap<Integer> indexMaxHeap = new IndexMaxHeap<>(array);
        printSort(indexMaxHeap);
        printHeap(indexMaxHeap);
        printIndexAndReverse(indexMaxHeap);
        System.out.println("===================================");
        indexMaxHeap.remove(4);
        printSort(indexMaxHeap);
        printHeap(indexMaxHeap);
        printIndexAndReverse(indexMaxHeap);
        System.out.println("===================================");
        indexMaxHeap.insert(9, 4);
        printSort(indexMaxHeap);
        printHeap(indexMaxHeap);
        printIndexAndReverse(indexMaxHeap);
        System.out.println("===================================");
        indexMaxHeap.pop();
        printSort(indexMaxHeap);
        printHeap(indexMaxHeap);
        printIndexAndReverse(indexMaxHeap);
        System.out.println("===================================");
        while (!indexMaxHeap.isEmpty()) {
            System.out.print(indexMaxHeap.pop() + " ");
        }
        System.out.println();
        printHeap(indexMaxHeap);
        printIndexAndReverse(indexMaxHeap);
        System.out.println("===================================");
        indexMaxHeap.add(15);
        printSort(indexMaxHeap);
        printHeap(indexMaxHeap);
        printIndexAndReverse(indexMaxHeap);
        System.out.println("===================================");
        indexMaxHeap.insert(17, 7);
        printSort(indexMaxHeap);
        printHeap(indexMaxHeap);
        printIndexAndReverse(indexMaxHeap);
    }

    @Test
    public void testPop() {
        IndexMaxHeap<Integer> indexMaxHeap = new IndexMaxHeap<>(array);
        int count = 5;
        while (count-- > 0) {
            System.out.print(indexMaxHeap.pop() + " ");
        }
        System.out.println();
        indexMaxHeap.add(7);
        indexMaxHeap.add(2);
        while (!indexMaxHeap.isEmpty()) {
            System.out.print(indexMaxHeap.pop() + " ");
        }
        System.out.println();
    }

    private <E extends Comparable<E>> void printSort(Heap<E> heap) {
        E[] sortedData = heap.sort();
        for (int i = 0; i < sortedData.length; i++) {
            System.out.print(sortedData[i] + " ");
        }
        System.out.println();
    }

    private <E extends Comparable<E>> void printHeap(IndexMaxHeap<E> indexMaxHeap) {
        for (E datum : indexMaxHeap.data) {
            System.out.print(datum + " ");
        }
        System.out.println();
    }

    private void printIndexAndReverse(IndexMaxHeap indexMaxHeap) {
        for (int index : indexMaxHeap.indexes) {
            System.out.print(index + " ");
        }
        System.out.println();
        for (int reverse : indexMaxHeap.reverse) {
            System.out.print(reverse + " ");
        }
        System.out.println();
    }

}
