package com.kun.heap;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 最大堆的实现
 *
 * @author CaoZiye
 * @version 1.0 2018/2/25 14:53
 */
public class MaxHeap<E extends Comparable<E>> implements Heap<E> {
    
    /**
     * 使用数组容纳元素，每个子节点 n，父节点为 (n - 1) / 2
     */
    protected E[] data;
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
     * @param clazz 元素类型
     * @param capacity 堆的容量
     */
    @SuppressWarnings("unchecked")
    public MaxHeap(Class<E> clazz, int capacity) {
        assert capacity >= 0;
        this.data = (E[]) Array.newInstance(clazz, capacity);
        this.capacity = capacity;
        this.count = 0;
    }
    
    /**
     * 使用现成的数组构造一个最大堆
     *
     * @param data 堆元素
     */
    public MaxHeap(E[] data) {
        assert data != null;
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
    public static <E extends Comparable<E>> void heapSort(E[] array) {
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
    private static <E extends Comparable<E>> void shiftDown(E[] array, int heapSize, int index) {
        int childIndex;
        E e = array[index];
        while (index < heapSize >> 1) {
            if ((childIndex = (index + 1) << 1) < heapSize) {
                childIndex = array[childIndex].compareTo(array[childIndex - 1]) > 0 ? childIndex : childIndex - 1;
            } else {
                childIndex -= 1;
            }
            if (e.compareTo(array[childIndex]) < 0) {
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
    private static <E extends Comparable<E>> void swap(E[] array, int i, int j) {
        E temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    
    @Override
    public int size() {
        return count;
    }
    
    @Override
    public int capacity() {
        return capacity;
    }
    
    @Override
    public boolean isEmpty() {
        return count == 0;
    }
    
    /**
     * 向堆中添加一个元素
     *
     * @param datum 新元素
     */
    @Override
    public void add(E datum) {
        assert count < capacity;
        data[count] = datum;
        shiftUp(count++);
    }
    
    /**
     * 弹出堆中的最大值
     *
     * @return 堆中的最大元素
     */
    @Override
    public E pop() {
        assert !isEmpty();
        E max = data[0];
        data[0] = data[--count];
        shiftDown(0);
        return max;
    }
    
    /**
     * 获取最大元素值而不弹出该值
     *
     * @return 最大元素值
     */
    @Override
    public E peek() {
        return data[0];
    }
    
    /**
     * 元素上移操作，调整至堆中合理位置
     *
     * @param index 元素当前索引
     */
    private void shiftUp(int index) {
        int parentIndex;
        E e = data[index];
        while (index > 0) {
            // 如果子节点更大
            parentIndex = (index - 1) / 2;
            if (e.compareTo(data[parentIndex]) > 0) {
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
        E e = data[index];
        // 最大堆的性质，后一半索引区域的元素为叶子节点
        // 索引在前半区的时候需要判断
        while (index < count >> 1) {
            // 判断有没有右边的子节点
            if ((childIndex = (index + 1) << 1) < count) {
                childIndex = data[childIndex].compareTo(data[childIndex - 1]) > 0 ? childIndex : childIndex - 1;
            } else {
                childIndex -= 1;
            }
            // 如果子节点更大
            if (e.compareTo(data[childIndex]) < 0) {
                data[index] = data[childIndex];
                index = childIndex;
                continue;
            }
            // 没有子节点更大，终止操作
            break;
        }
        data[index] = e;
    }
    
    /**
     * 获得堆中元素排序后的数组
     *
     * @return 排序后数组
     */
    @Override
    public E[] sort() {
        E[] sortedData = Arrays.copyOf(data, count);
        for (int i = sortedData.length - 1; i > 1; i--) {
            // 当前堆最大值放到后面
            swap(sortedData, 0, i);
            // 新上来的值进行下移
            shiftDown(sortedData, i, 0);
        }
        swap(sortedData, 0, 1);
        return sortedData;
    }
    
}
