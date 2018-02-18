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
        // 1.从头遍历数组
        int temp;
        for (int i = 0; i < array.length; i++) {
            // 2.将当前值保存
            temp = array[i];
            // 3.记录当前索引
            int j = i;
            // 4.倒叙遍历之前的已排序数组，发现超过 temp 时向后顺移，腾出位置，同时索引减 1
            for (; j > 0 && array[j - 1] > temp; j--) {
                array[j] = array[j - 1];
            }
            // 5.若不在有更大项，将原保存的插入值插入
            array[j] = temp;
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
     * 归并排序，使用二分法拆分数组，时间复杂度 O(N*logN)
     *
     * @param array
     */
    public static void recursiveMergeSort(int[] array) {
        recursiveMergeSortPart(array, 0, array.length / 2);
        recursiveMergeSortPart(array, array.length / 2, array.length);
        recursiveMergeSortMerge(array, 0, array.length);
    }
    
    /**
     * 对需要排序的数组段落继续二分段，递归排序
     *
     * @param array 被排序数组段落
     * @param start 起始索引
     * @param end   结束索引（不包含）
     */
    private static void recursiveMergeSortPart(int[] array, int start, int end) {
        //        if (start + 1 == end) {
        //            return;
        //        }
        
        // 优化方案，需要排序的段落如果较为小时，使用插入排序
        if (end - start <= 16) {
            int temp;
            for (int i = start; i < end; i++) {
                temp = array[i];
                int j = i;
                for (; j > 0 && array[j - 1] > temp; j--) {
                    array[j] = array[j - 1];
                }
                array[j] = temp;
            }
            return;
        }
        
        // 递归步骤
        recursiveMergeSortPart(array, start, (end - start) / 2 + start);
        recursiveMergeSortPart(array, (end - start) / 2 + start, end);
        // 合并有序段
        recursiveMergeSortMerge(array, start, end);
    }
    
    private static void recursiveMergeSortMerge(int[] array, int start, int end) {
        
        // 1.将有序的两部分拷贝
        int[] part1 = Arrays.copyOfRange(array, start, (end - start) / 2 + start);
        int[] part2 = Arrays.copyOfRange(array, (end - start) / 2 + start, end);
        
        // 2.保存目标数组，拷贝数组的索引值，长度
        int index1 = 0, index2 = 0;
        int length1 = part1.length;
        int length2 = part2.length;
        
        // 3.依次比较拷贝数组，将较小值赋值给目标数组，同时增加索引值
        for (; index1 != length1 && index2 != length2; ) {
            if (part1[index1] < part2[index2]) {
                array[start++] = part1[index1++];
                continue;
            }
            array[start++] = part2[index2++];
        }
        
        // 4.当循环完毕，有一拷贝数组必然已整理完毕，循环整理剩下的数组
        while (index1 != length1) {
            array[start++] = part1[index1++];
        }
        while (index2 != length2) {
            array[start++] = part2[index2++];
        }
    }
    
}
