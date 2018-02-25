package com.kun.heap;

import java.util.Arrays;

/**
 * 最大堆的实现
 *
 * @author CaoZiye
 * @version 1.0 2018/2/25 14:53
 */
public class MaxHeap {
    
    /**
     * 使用数组容纳元素，每个子节点 n，父节点为 (n - 1) / 2
     */
    protected int[] data;
    /**
     * 该堆的容量
     */
    protected int capacity;
    /**
     * 该堆当前元素个数
     */
    protected int count;
    
    /**
     * 指定堆容量，构造一个最大堆
     *
     * @param capacity 堆的容量
     */
    public MaxHeap(int capacity) {
        this.data = new int[capacity];
        this.capacity = capacity;
        this.count = 0;
    }
    
    /**
     * 使用现成的数组构造一个最大堆
     *
     * @param data 堆元素
     */
    public MaxHeap(int[] data) {
        this.data = Arrays.copyOf(data, data.length);
        this.capacity = data.length;
        this.count = capacity;
        // 最大堆性质，后半区没有字节点
        for (int i = (count >> 1) - 1; i >= 0; i--) {
            shiftDown(i);
        }
    }
    
    /**
     * 对数组进行堆排序
     * 如果需要对单个实例堆中的元素排序，只需要 pop 即可
     *
     * @param array 被排序数组
     * @see #pop() 可用于实例对象的排序
     */
    public static void heapSort(int[] array) {
        // 堆化
        for (int i = (array.length >> 1) - 1; i >= 0; i--) {
            shiftDown(array, array.length, i);
        }
        // 排序
        for (int i = array.length - 1; i > 1; i--) {
            // 当前堆最大值放到后面
            swap(array, 0, i);
            // 新上来的值进行下移
            shiftDown(array, i, 0);
        }
        swap(array, 0, 1);
    }
    
    /**
     * 为 shiftDown 提供的静态实现
     *
     * @param array    被排序数组
     * @param heapSize 堆结构大小
     * @param index    当前元素索引
     * @see #shiftDown(int)
     */
    private static void shiftDown(int[] array, int heapSize, int index) {
        int childIndex;
        int e = array[index];
        while (index < heapSize >> 1) {
            if ((childIndex = (index + 1) << 1) < heapSize) {
                childIndex = array[childIndex] > array[childIndex - 1] ? childIndex : childIndex - 1;
            } else {
                childIndex -= 1;
            }
            if (e < array[childIndex]) {
                array[index] = array[childIndex];
                index = childIndex;
                continue;
            }
            break;
        }
        array[index] = e;
    }
    
    /**
     * 交换数组中的两个元素
     *
     * @param array 被排序数组
     * @param i     索引一
     * @param j     索引二
     */
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    
    public int size() {
        return count;
    }
    
    public int getCapacity() {
        return capacity;
    }
    
    public boolean isEmpty() {
        return count == 0;
    }
    
    /**
     * 向堆中添加一个元素
     *
     * @param datum 新元素
     */
    public void add(int datum) {
        assert count < capacity;
        data[count] = datum;
        shiftUp(count++);
    }
    
    /**
     * 弹出堆中的最大值
     *
     * @return 堆中的最大元素
     */
    public int pop() {
        assert !isEmpty();
        int max = data[0];
        data[0] = data[--count];
        shiftDown(0);
        return max;
    }
    
    /**
     * 元素上移操作，调整至堆中合理位置
     *
     * @param index 元素当前索引
     */
    private void shiftUp(int index) {
        int parentIndex;
        int e = data[index];
        while (index > 0) {
            // 如果子节点更大
            parentIndex = (index - 1) / 2;
            if (e > data[parentIndex]) {
                data[index] = data[parentIndex];
                index = parentIndex;
                continue;
            }
            // 如果父节点大于或者等于子节点，终止操作
            break;
        }
        data[index] = e;
    }
    
    /**
     * 元素下移操作，调整至队中合理位置
     *
     * @param index 元素当前索引
     */
    private void shiftDown(int index) {
        int childIndex;
        int e = data[index];
        // 最大堆的性质，后一半索引区域的元素为叶子节点
        // 索引在前半区的时候需要判断
        while (index < count >> 1) {
            // 判断有没有右边的子节点
            if ((childIndex = (index + 1) << 1) < count) {
                childIndex = data[childIndex] > data[childIndex - 1] ? childIndex : childIndex - 1;
            } else {
                childIndex -= 1;
            }
            // 如果子节点更大
            if (e < data[childIndex]) {
                data[index] = data[childIndex];
                index = childIndex;
                continue;
            }
            // 没有子节点更大，终止操作
            break;
        }
        data[index] = e;
    }
    
}
