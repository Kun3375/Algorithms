package com.kun;

import java.util.Arrays;

/**
 * @author CaoZiye
 * @version 1.0 2018/2/16 19:04
 */
public class Sort {
    
    /**
     * 选择排序，时间复杂度稳定 O(n^2)
     * 对任何类型的数列，耗时稳定，每次需要完整遍历
     *
     * @param array 被排序数组
     */
    public static void selectionSort(int[] array) {
        // 1.从头遍历数组
        for (int i = 0; i < array.length - 1; i++) {
            // 2.需要记录最小值的索引，初始为当前遍历的首项
            int index = i;
            // 3.前面已排序的项将被跳过，遍历之后乱序的项
            for (int j = i + 1; j < array.length; j++) {
                // 4.如果发现更小的项，更新索引
                if (array[index] > array[j]) {
                    index = j;
                }
            }
            // 5.交换最小项至当前位置
            if (array[index] != array[i]) {
                array[index] = array[i] ^ array[index];
                array[i] = array[i] ^ array[index];
                array[index] = array[i] ^ array[index];
            }
        }
    }
    
    /**
     * 插入排序，时间复杂度 O(n^2)
     * 对于原数列近乎有序的情况下，十分迅速，趋向 O(n)，这种情况下为所有排序第一选择
     *
     * @param array 原被排序数组
     */
    public static void insertionSort(int[] array) {
        insertionSort(array, 0, array.length);
    }
    
    /**
     * 对数组部分区间进行插入排序
     * 可以适用于其他高级算法的优化
     *
     * @param array 被排序数组
     * @param start 排序起始索引
     * @param end   排序结束索引（不包含）
     */
    public static void insertionSort(int[] array, int start, int end) {
        int tempValue;
        int tempIndex;
        for (int index = start; index < end; index++) {
            // 1.将当前值保存
            tempValue = array[index];
            // 2.记录当前索引
            tempIndex = index;
            // 3.倒叙遍历之前的已排序数组，发现超过 tempValue 时向后顺移，腾出位置，同时索引减 1
            for (; tempIndex > start && array[tempIndex - 1] > tempValue; tempIndex--) {
                // 向后挪
                array[tempIndex] = array[tempIndex - 1];
            }
            // 4.插入
            array[tempIndex] = tempValue;
        }
    }
    
    /**
     * 冒泡排序，时间复杂度 O(n^2)
     * 对于原数列近乎有序的情况下，变现比乱序数列的情况好
     * 由于交换元素的次数频繁，效率相对其他数列差，仅在原数列近乎有序的情况下，接近选择排序，远远不及插入排序
     * 不考虑的算法
     *
     * @param array 被排序数组
     */
    public static void bubbleSort(int[] array) {
        // 1.遍历次数约数组长度 - 1 次
        for (int i = 0; i < array.length - 1; i++) {
            // 2.遍历前半段未排序部分
            for (int j = 0; j < array.length - i - 1; j++) {
                // 3.比较相邻值，顺序不对时需要换位，一次迭代至最后
                if (array[j] > array[j + 1]) {
                    array[j] = array[j] ^ array[j + 1];
                    array[j + 1] = array[j] ^ array[j + 1];
                    array[j] = array[j] ^ array[j + 1];
                }
            }
        }
    }
    
    /**
     * 归并排序，使用二分法拆分数组，时间复杂度 O(N*logN)，递归实现
     *
     * @param array 被排序数组
     */
    public static void recursiveMergeSort(int[] array) {
        recursiveMergeSortPart(array, 0, array.length >> 1);
        recursiveMergeSortPart(array, array.length >> 1, array.length);
        mergeSortMerge(array, 0, array.length >> 1, array.length);
    }
    
    /**
     * 对需要排序的数组段落继续二分段，递归排序
     *
     * @param array 被排序数组
     * @param start 起始索引
     * @param end   结束索引（不包含）
     */
    private static void recursiveMergeSortPart(int[] array, int start, int end) {
        
        // 递归深处的小数组使用插入排序优化
        if (start + 16 >= end) {
            insertionSort(array, start, end);
            return;
        }
        
        //        if (start + 1 == end) {
        //            return;
        //        }
        
        int middle = ((end - start) >> 1) + start;
        // 递归步骤
        recursiveMergeSortPart(array, start, middle);
        recursiveMergeSortPart(array, middle, end);
        // 合并有序段
        mergeSortMerge(array, start, middle, end);
    }
    
    /**
     * 对数组中区间内两个有序段落进行合并
     *
     * @param array 被排序数组
     * @param start 起始索引
     * @param end   结束索引（不包含）
     */
    private static void mergeSortMerge(int[] array, int start, int middle, int end) {
        
        // ************************** 优化 **************************
        // 两部分之间有有序性规律
        // 如果某一段起始值比另一段结束值更大，直接拼接
        // 第二段更大的情况，直接返回
        if (array[middle] > array[middle - 1]) {
            return;
        }
        // 第一段更大，对调两部分，为了通用，仅作两端对称情况下啊的优化
        if (array[start] > array[end - 1] && (middle - start == end - middle)) {
            
            for (int delta = 0; start + delta < middle; delta++) {
                int newStart = start + delta, newMiddle = middle + delta;
                array[newStart] = array[newStart] ^ array[newMiddle];
                array[newMiddle] = array[newStart] ^ array[newMiddle];
                array[newStart] = array[newStart] ^ array[newMiddle];
            }
            return;
        }
        // ********************************************************
        
        // 1.将需要排序的段落先复制一个副本
        int[] temp = Arrays.copyOfRange(array, start, end);
        // 2.这个副本有着有序的前后两个部分，记录下起始索引，中间索引（第二段的起始索引）
        int firStart = 0;
        middle -= start;
        int secStart = middle;
        
        // 3.只要需要排序的段落没有完全排序完，就执行循环
        for (; start < end; start++) {
            // 前半段完成
            if (firStart == middle) {
                array[start] = temp[secStart++];
                continue;
            }
            // 后半段完成
            if (secStart == temp.length) {
                array[start] = temp[firStart++];
                continue;
            }
            // 比较前后两值大小
            // 前半段较小
            if (temp[firStart] < temp[secStart]) {
                array[start] = temp[firStart++];
                continue;
            }
            // 后半段较小
            array[start] = temp[secStart++];
        }
    }
    
    /**
     * 归并排序，迭代实现
     *
     * @param array 被排序数组
     */
    public static void iterationMergeSort(int[] array) {
        
        int length = array.length;
        // 最小单元使用插入排序优化
        for (int i = 0; i < length; ) {
            insertionSort(array, i, (i += 16) < length ? i : length);
        }
        // 1，2，4，8... 依次归并
        for (int partitionSize = 16; partitionSize <= length; partitionSize <<= 1) {
            for (int index = 0; index < length - partitionSize; index += partitionSize << 1) {
                int middle = index + partitionSize;
                mergeSortMerge(array, index, middle, middle + partitionSize < length ? middle + partitionSize : length);
            }
        }
    }
    
}