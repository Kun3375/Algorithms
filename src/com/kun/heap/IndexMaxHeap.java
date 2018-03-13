package com.kun.heap;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author CaoZiye
 * @version 1.0 2018/3/3 11:35
 */
public class IndexMaxHeap<E extends Comparable<E>> implements IndexHeap<E> {
    
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
    public IndexMaxHeap(Class<E> clazz, int capacity) {
        assert capacity >= 0;
        this.data = (E[]) Array.newInstance(clazz, capacity);
        this.indexes = new int[capacity];
        this.reverse = new int[capacity];
        for (int i = 0; i < this.reverse.length; i++) {
            this.reverse[i] = this.indexes[i] -1;
        }
        this.capacity = capacity;
        this.count = 0;
    }
    
    /**
     * 使用现成的数组构造一个最大堆
     *
     * @param data 堆元素
     */
    public IndexMaxHeap(E[] data) {
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
    private static <E> void swap(E[] array, int i, int j) {
        E temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    
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
    public void insert(E datum, int index) {
        assert count < capacity && reverse[index] == -1;
        data[index] = datum;
        
        indexes[count] = index;
        reverse[index] = count;
        shiftUp(count++);
    }
    
    /**
     * 向堆中添加一个元素
     *
     * @param datum 新元素
     */
    @Override
    public void add(E datum) {
        assert count < capacity;
        // 该位置没用过，需要找空位
        if (indexes[count] == -1) {
            for (int i = 0; i < reverse.length; i++) {
                if (reverse[i] == -1) {
                    data[i] = datum;
                    indexes[count] = i;
                    reverse[i] = count;
                    shiftUp(count++);
                    return;
                }
            }
        }
        // 该位置之前使用过，可以快速定位到空闲的坑
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
    public E remove(int index) {
        assert reverse[index] != -1;
        E e = data[index];
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
    public E pop() {
        assert !isEmpty();
        E max = data[indexes[0]];
        // reverse中标记该位置元素已删除
        reverse[indexes[0]] = -1;
        // 交换索引，为了使count位确定为被移除的元素，这样add时候不需要遍历可以直接定位
        swap(indexes, 0, --count);
        // 把实际索引挂载到reverse上
        reverse[indexes[0]] = 0;
        // indexes[0]使最小元素需要shiftDown
        shiftDown(0);
        return max;
    }
    
    /**
     * 弹出最大值在原数组中的索引
     *
     * @return 最大值在原数组中的索引
     */
    @Override
    public int popIndex() {
        assert !isEmpty();
        int index = indexes[0];
        // reverse中标记该位置元素已删除
        reverse[indexes[0]] = -1;
        // 交换索引，为了使count位确定为被移除的元素，这样add时候不需要遍历可以直接定位
        swap(indexes, 0, --count);
        // 把实际索引挂载到reverse上
        reverse[indexes[0]] = 0;
        // indexes[0]使最小元素需要shiftDown
        shiftDown(0);
        return index;
    }
    
    
    /**
     * 获取最大元素值而不弹出该值
     *
     * @return 最大元素值
     */
    @Override
    public E peek() {
        return data[indexes[0]];
    }
    
    /**
     * 查看指定位置的元素
     * @param index 指定索引
     * @return 原数组中的元素
     */
    @Override
    public E peekIndex(int index) {
        return data[index];
    }
    
    /**
     * 返回最大元素的索引
     *
     * @return 最大元素的索引
     */
    public int getMaxIndex() {
        return indexes[0];
    }
    
    @Override
    public boolean contain(int index) {
        assert index >= 0 && index < capacity;
        return reverse[index] != -1;
    }
    
    /**
     * 改变原数组中的一个元素，重新堆化
     * 需要保证原索引处有值
     *
     * @param index  要改变的索引
     * @param newOne 新元素
     */
    @Override
    public void change(int index, E newOne) {
        assert contain(index);
        
        data[index] = newOne;
        shiftDown(reverse[index]);
        shiftUp(reverse[index]);
    }
    
    /**
     * 元素上移操作，调整至堆中合理位置
     *
     * @param index 元素当前索引
     */
    private void shiftUp(int index) {
        int parentIndex;
        int i = indexes[index];
        E e = data[i];
        while (index > 0) {
            // 如果子节点更大
            parentIndex = (index - 1) / 2;
            if (e.compareTo(data[indexes[parentIndex]]) > 0) {
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
        E e = data[i];
        // 最大堆的性质，后一半索引区域的元素为叶子节点
        // 索引在前半区的时候需要判断
        while (index < count >> 1) {
            // 判断有没有右边的子节点
            if ((childIndex = (index + 1) << 1) < count) {
                childIndex = data[indexes[childIndex]].compareTo(data[indexes[childIndex - 1]]) > 0 ?
                        childIndex : childIndex - 1;
            } else {
                childIndex -= 1;
            }
            // 如果子节点更大
            if (e.compareTo(data[indexes[childIndex]]) < 0) {
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
    
    
    @SuppressWarnings("unchecked")
    @Override
    public E[] sort() {
        E[] sortedData =(E[]) Array.newInstance(data.getClass().getComponentType(), count);
        for (int i = 0; i < count; i++) {
            sortedData[i] = data[indexes[i]];
        }
        for (int i = sortedData.length - 1; i > 0; i--) {
            // 当前堆最大值放到后面
            swap(sortedData, 0, i);
            // 新上来的值进行下移
            shiftDown(sortedData, i, 0);
        }
        return sortedData;
    }
    
}
