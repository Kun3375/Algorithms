package com.kun;

/**
 * @author CaoZiye
 * @version 1.0 2018/2/16 19:04
 */
public class Sort {
    
    /**
     * 选择排序
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
     * 插入排序
     * 对于原数列近乎有序的情况下，十分迅速，这种情况下第一选择
     *
     * @param array 原被排序数组
     */
    public static void insertionSort(int[] array) {
        // 1.从头遍历数组
        for (int i = 0; i < array.length; i++) {
            // 2.将当前值保存
            int temp = array[i];
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
     * 冒泡排序
     * 对于原数列近乎有序的情况下，变现比乱序数列的情况好
     * 由于交换元素的次数频繁，效率相对其他数列差，仅在原数列近乎有序的情况下，接近选择排序，远远不及插入排序
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
    
}
