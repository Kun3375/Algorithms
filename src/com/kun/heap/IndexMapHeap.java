package com.kun.heap;

import java.util.Arrays;

/**
 * @author CaoZiye
 * @version 1.0 2018/3/3 11:35
 */
public class IndexMapHeap implements Heap {
    
    /**
     * 堆化的索引数组
     */
    protected int[] indexes;
    
    /**
     * 反向查找数组，用来检索原索引在堆中（index 数组中）的位置
     */
    protected int[] reverse;
    
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
    public IndexMapHeap(int capacity) {
        assert capacity >= 0;
        this.data = new int[capacity];
        this.indexes = new int[capacity];
        for (int i = 0; i < this.reverse.length; i++) {
            this.reverse[i] = -1;
        }
        this.capacity = capacity;
        this.count = 0;
    }
    
    /**
     * 使用现成的数组构造一个最大堆
     *
     * @param data 堆元素
     */
    public IndexMapHeap(int[] data) {
        assert data != null;
        this.data = Arrays.copyOf(data, data.length);
        this.indexes = new int[data.length];
        this.reverse = new int[data.length];
        for (int i = 0; i < this.data.length; i++) {
            this.reverse[i] = this.indexes[i] = i;
        }
        this.capacity = data.length;
        this.count = capacity;
        // 最大堆性质，后半区没有字节点
        for (int i = (count >> 1) - 1; i >= 0; i--) {
            shiftDown(i);
        }
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
     * 在指定索引处添加元素，不允许索引处存在旧值
     *
     * @param datum 新元素
     * @param index 目标索引
     */
    public void insert(int datum, int index) {
        assert count < capacity && reverse[index] == -1;
        data[index] = datum;
        
        for (int i = count; ; i++) {
            if (indexes[i] == index) {
                // 交换indexes
                int temp = indexes[count];
                indexes[count] = index;
                indexes[i] = temp;
                reverse[index] = count;
                shiftUp(count++);
                break;
            }
        }
        
    }
    
    /**
     * 向堆中添加一个元素
     *
     * @param datum 新元素
     */
    @Override
    public void add(int datum) {
        assert count < capacity;
        data[indexes[count]] = datum;
        reverse[indexes[count]] = count;
        shiftUp(count++);
    }
    
    /**
     * 删除原数组中指定位置的元素，仍然需要构成最大堆
     * 对于索引堆，增删元素不仅需要维护 indexes 以及 reverse
     * indexes 在 count 范围之外的元素需要保留，便于 add 操作快速定位空缺位置
     *
     * @param index 指定索引
     * @return 指定索引处被移除的元素
     */
    public int remove(int index) {
        assert reverse[index] != -1;
        int e = data[index];
        count--;
        // 如果取出的元素使最小元素，不需要shiftDown和调整indexes的操作
        if (index == indexes[count]) {
            reverse[index] = -1;
            return e;
        }
        // 调整indexes交换索引，调整reverses
        indexes[reverse[index]] = indexes[count];
        reverse[indexes[count]] = index;
        
        indexes[count] = index;
        reverse[index] = -1;
        shiftDown(index);
        return e;
    }
    
    /**
     * 弹出堆中的最大值
     *
     * @return 堆中的最大元素
     */
    @Override
    public int pop() {
        assert !isEmpty();
        int max = data[indexes[0]];
        // reverse中标记该位置元素已删除
        reverse[indexes[0]] = -1;
        // 交换索引，为了使count位确定为被移除的元素，这样add时候不需要遍历可以直接定位
        int temp = indexes[0];
        indexes[0] = indexes[--count];
        indexes[count] = temp;
        // 把实际索引挂载到reverse上
        reverse[indexes[0]] = 0;
        // indexes[0]使最小元素需要shiftDown
        shiftDown(0);
        return max;
    }
    
    /**
     * 获取最大元素值而不弹出该值
     *
     * @return 最大元素值
     */
    @Override
    public int peek() {
        return data[indexes[0]];
    }
    
    /**
     * 返回最大元素的索引
     *
     * @return 最大元素的索引
     */
    public int getMaxIndex() {
        return indexes[0];
    }
    
    /**
     * 元素上移操作，调整至堆中合理位置
     *
     * @param index 元素当前索引
     */
    private void shiftUp(int index) {
        int parentIndex;
        int i = indexes[index];
        int e = data[i];
        while (index > 0) {
            // 如果子节点更大
            parentIndex = (index - 1) / 2;
            if (e > data[indexes[parentIndex]]) {
                indexes[index] = indexes[parentIndex];
                reverse[indexes[index]] = index;
                index = parentIndex;
                continue;
            }
            // 如果父节点大于或者等于子节点，终止操作
            break;
        }
        indexes[index] = i;
        reverse[indexes[index]] = index;
    }
    
    /**
     * 元素下移操作，调整至队中合理位置
     *
     * @param index 元素当前索引
     */
    private void shiftDown(int index) {
        int childIndex;
        int i = indexes[index];
        int e = data[i];
        // 最大堆的性质，后一半索引区域的元素为叶子节点
        // 索引在前半区的时候需要判断
        while (index < count >> 1) {
            // 判断有没有右边的子节点
            if ((childIndex = (index + 1) << 1) < count) {
                childIndex = data[indexes[childIndex]] > data[indexes[childIndex - 1]] ? childIndex : childIndex - 1;
            } else {
                childIndex -= 1;
            }
            // 如果子节点更大
            if (e < data[indexes[childIndex]]) {
                indexes[index] = indexes[childIndex];
                reverse[indexes[index]] = index;
                index = childIndex;
                continue;
            }
            // 没有子节点更大，终止操作
            break;
        }
        indexes[index] = i;
        // 修复最后一次 pop 操作会导致索引不更新成 -1
        reverse[indexes[index]] = count == 0 ? -1 : index;
    }
    
    
    @Override
    public int[] sort() {
        int[] sortedData = new int[count];
        for (int i = 0; i < count; i++) {
            sortedData[i] = data[indexes[i]];
        }
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