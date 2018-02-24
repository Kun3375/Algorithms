package com.kun;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Random;

/**
 * @author CaoZiye
 * @version 1.0 2018/2/16 19:17
 */
public class SortTest {
    
    private static int arrayLength;
    private static int[] oriNormalArray;
    private static int[] oriNearlyArray;
    private int[] normalArray;
    private int[] nearlyArray;
    private Instant startTime;
    
    @BeforeClass
    public static void init() {
        arrayLength = 50000;
        int start = 0;
//        int end = arrayLength;
        int end = 10;
        int swapTimes =  10;
        
        oriNormalArray = new int[arrayLength];
        Random random = new Random();
        for (int i = 0; i < arrayLength; i++) {
            oriNormalArray[i] = random.nextInt(end - start) + start;
        }
        
        oriNearlyArray = new int[arrayLength];
        for (int i = 0; i < oriNearlyArray.length; i++) {
            oriNearlyArray[i] = i;
        }
        do {
            int i = random.nextInt(arrayLength);
            int j;
            do {
                j = random.nextInt(arrayLength);
            } while (i == j);
            oriNearlyArray[i] = oriNearlyArray[i] ^ oriNearlyArray[j];
            oriNearlyArray[j] = oriNearlyArray[i] ^ oriNearlyArray[j];
            oriNearlyArray[i] = oriNearlyArray[i] ^ oriNearlyArray[j];
            swapTimes--;
        } while (swapTimes > 0);
    
//        for (int i : oriNormalArray) {
//            System.out.print(i);
//            System.out.print(", ");
//        }
//        System.out.println();
    }
    
    @Before
    public void setup() {
        normalArray = Arrays.copyOf(oriNormalArray, arrayLength);
        nearlyArray = Arrays.copyOf(oriNearlyArray, arrayLength);
        
    }
    
    @After
    public void tearDown() {
        for (int i : normalArray) {
            System.out.print(i);
            System.out.print(", ");
        }
        System.out.println();
        for (int i : nearlyArray) {
            System.out.print(i);
            System.out.print(", ");
        }
        System.out.println();
    }
    
    @Test
    public void selectionSort() {
        startTime = Instant.now();
        Sort.selectionSort(normalArray);
        System.out.println("selection sort spent：" + Duration.between(startTime, Instant.now()));
    
        startTime = Instant.now();
        Sort.selectionSort(nearlyArray);
        System.out.println("selection sort spent for nearly：" + Duration.between(startTime, Instant.now()));
    }
    
    @Test
    public void insertionSort() {
        startTime = Instant.now();
        Sort.insertionSort(normalArray);
        System.out.println("insertion sort spent: " + Duration.between(startTime, Instant.now()));
    
        startTime = Instant.now();
        Sort.insertionSort(nearlyArray);
        System.out.println("insertion sort spent for nearly：" + Duration.between(startTime, Instant.now()));
    }

    @Test
    public void bubbleSort() {
        startTime = Instant.now();
        Sort.bubbleSort(normalArray);
        System.out.println("bubble sort spent: " + Duration.between(startTime, Instant.now()));
    
        startTime = Instant.now();
        Sort.bubbleSort(nearlyArray);
        System.out.println("bubble sort spent for nearly：" + Duration.between(startTime, Instant.now()));
    }
    
    @Test
    public void recursiveMergeSort() {
        startTime = Instant.now();
        Sort.recursiveMergeSort(normalArray);
        System.out.println("recursive merge sort spent: " + Duration.between(startTime, Instant.now()));
        
        startTime = Instant.now();
        Sort.recursiveMergeSort(nearlyArray);
        System.out.println("recursive merge sort spent for nearly：" + Duration.between(startTime, Instant.now()));
    }
    
    @Test
    public void iterationMergeSort() {
        startTime = Instant.now();
        Sort.iterationMergeSort(normalArray);
        System.out.println("iteration merge sort spent: " + Duration.between(startTime, Instant.now()));
    
        startTime = Instant.now();
        Sort.iterationMergeSort(nearlyArray);
        System.out.println("iteration merge sort spent for nearly：" + Duration.between(startTime, Instant.now()));
    }
    
    @Test
    public void quickSortSingleWay() {
        startTime = Instant.now();
        Sort.quickSortSingleWay(normalArray);
        System.out.println("quick sort spent: " + Duration.between(startTime, Instant.now()));
    
        startTime = Instant.now();
        Sort.quickSortSingleWay(nearlyArray);
        System.out.println("quick sort spent for nearly：" + Duration.between(startTime, Instant.now()));
    }
    
    @Test
    public void quickSortDoubleWay() {
        startTime = Instant.now();
        Sort.quickSortDoubleWay(normalArray);
        System.out.println("quick sort double way spent: " + Duration.between(startTime, Instant.now()));
    
        startTime = Instant.now();
        Sort.quickSortDoubleWay(nearlyArray);
        System.out.println("quick sort double way spent for nearly：" + Duration.between(startTime, Instant.now()));
    }
    
}
