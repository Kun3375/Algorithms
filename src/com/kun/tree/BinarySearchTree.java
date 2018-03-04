package com.kun.tree;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Consumer;

/**
 * 基本二叉搜索树（典型不平衡树）
 *
 * @author CaoZiye
 * @version 1.0 2018/3/4 11:31
 */
public class BinarySearchTree<K extends Comparable<K>, V> {
    
    private Node<K, V> root;
    
    private int count;
    
    public int size() {
        return count;
    }
    
    public boolean isEmpty() {
        return count == 0;
    }
    
    /**
     * 向树中添加键值对，key 不允许 null，key 相等时将更新 value
     * key 必须重写 {@link Object#equals(Object)} 方法，并推荐使用不可变对象类型
     *
     * @param key   键
     * @param value 新值
     */
    public void put(K key, V value) {
        root = put(root, key, value);
    }
    
    /**
     * 判断树中是否包含该键
     *
     * @param key 键
     * @return true if contains, otherwise false;
     */
    public boolean contain(K key) {
        return contain(root, key);
    }
    
    /**
     * 取得树中该键的对应值
     *
     * @param key 键
     * @return key 所对应的值
     */
    public V get(K key) {
        return get(root, key);
    }
    
    /**
     * 前序遍历
     *
     * @param consumer 遍历中针对每个 node 进行的操作
     */
    public void preOrder(Consumer<Node<K, V>> consumer) {
        // root null 判断上提，避免在递归过程中过多判断
        if (root != null) {
            preOrder(root, consumer);
        }
    }
    
    /**
     * 中序遍历
     *
     * @param consumer 遍历中针对每个 node 进行的操作
     */
    public void inOrder(Consumer<Node<K, V>> consumer) {
        if (root != null) {
            inOrder(root, consumer);
        }
    }
    
    /**
     * 后序遍历
     *
     * @param consumer 遍历中针对每个 node 进行的操作
     */
    public void postOrder(Consumer<Node<K, V>> consumer) {
        if (root != null) {
            postOrder(root, consumer);
        }
    }
    
    /**
     * 广度优先，层级遍历
     *
     * @param consumer
     */
    public void levelOrder(Consumer<Node<K, V>> consumer) {
        if (root != null) {
            LinkedBlockingQueue<Node<K, V>> queue = new LinkedBlockingQueue<>();
            queue.offer(root);
            levelOrder(consumer, queue);
        }
    }
    
    /**
     * 获取最大键的 Node
     *
     * @return 拥有最大键的节点
     */
    public Node<K, V> getMax() {
        return root == null ? null : getMax(root);
    }
    
    /**
     * 获取最小键的 Node
     *
     * @return 拥有最小键的节点
     */
    public Node<K, V> getMin() {
        return root == null ? null : getMin(root);
    }
    
    /**
     * 删除最大的键的节点
     */
    public void removeMax() {
        if (root == null) {
            return;
        }
        root = removeMax(root);
    }
    
    /**
     * 删除最小的键的节点
     */
    public void removeMin() {
        if (root == null) {
            return;
        }
        root = removeMin(root);
    }
    
    /**
     * 删除指定 key 的节点
     *
     * @param key 要删除节点的 key
     */
    public void remove(K key) {
        root = remove(root, key);
    }
    
    /**
     * 返回小于等于该 key 且最接近该 key 值的节点
     * @param key 搜索 key
     * @return floor node
     */
    public Node<K, V> floor(K key) {
        // TODO
        return null;
    }
    
    /**
     * 返回大于等于该 key 且最结金该 key 值的节点
     * @param key 搜索 key
     * @return ceil node
     */
    public Node<K, V> ceil(K key) {
        // TODO
        return null;
    }
    
    /**
     * 返回指定键值的节点在序列中的位置，即第几个
     * @param key 搜索 key
     * @return key 在顺序序列中的位置，不存在返回 0
     */
    public int rank(K key) {
        // TODO
        return 0;
    }
    
    /**
     * 返回指定次序点的节点
     * @param rank 指定位置
     * @return 指定位置的元素，如果 rank 过大或者过小返回 null
     */
    public Node<K, V> select (int rank) {
        // TODO
        return null;
    }
    
    private Node<K, V> put(Node<K, V> node, K key, V value) {
        // 递归到底
        if (node == null) {
            count++;
            return new Node<>(key, value);
        }
        // 比较 key，确定加入左子树还是右子树
        int compared = node.key.compareTo(key);
        if (compared > 0) {
            node.count++;
            node.left = put(node.left, key, value);
            return node;
        }
        if (compared < 0) {
            node.count++;
            node.right = put(node.right, key, value);
            return node;
        }
        // key 相等的情况，更新 value
        node.value = value;
        return node;
    }
    
    private boolean contain(Node<K, V> node, K key) {
        if (node == null) {
            return false;
        }
        int compared = node.key.compareTo(key);
        if (compared > 0) {
            return contain(node.left, key);
        }
        if (compared < 0) {
            return contain(node.right, key);
        }
        return true;
    }
    
    private V get(Node<K, V> node, K key) {
        if (node == null) {
            return null;
        }
        int compared = node.key.compareTo(key);
        if (compared > 0) {
            return get(node.left, key);
        }
        if (compared < 0) {
            return get(node.right, key);
        }
        return node.value;
    }
    
    private void preOrder(Node<K, V> node, Consumer<Node<K, V>> consumer) {
        consumer.accept(node);
        if (node.left != null) {
            preOrder(node.left, consumer);
        }
        if (node.right != null) {
            preOrder(node.right, consumer);
        }
    }
    
    private void inOrder(Node<K, V> node, Consumer<Node<K, V>> consumer) {
        if (node.left != null) {
            inOrder(node.left, consumer);
        }
        consumer.accept(node);
        if (node.right != null) {
            inOrder(node.right, consumer);
        }
    }
    
    private void postOrder(Node<K, V> node, Consumer<Node<K, V>> consumer) {
        if (node.left != null) {
            postOrder(node.left, consumer);
        }
        if (node.right != null) {
            postOrder(node.right, consumer);
        }
        consumer.accept(node);
    }
    
    private void levelOrder(Consumer<Node<K, V>> consumer, Queue<Node<K, V>> queue) {
        if (!queue.isEmpty()) {
            Node<K, V> node = queue.poll();
            consumer.accept(node);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            levelOrder(consumer, queue);
        }
    }
    
    private Node<K, V> getMax(Node<K, V> node) {
        return node.right == null ? node : getMax(node.right);
    }
    
    private Node<K, V> getMin(Node<K, V> node) {
        return node.left == null ? node : getMin(node.left);
    }
    
    private Node<K, V> removeMax(Node<K, V> node) {
        node.count--;
        if (node.right != null) {
            node.right = removeMax(node.right);
            return node;
        }
        if (node.left != null) {
            count--;
            return node.left;
        }
        count--;
        return null;
    }
    
    private Node<K, V> removeMin(Node<K, V> node) {
        node.count--;
        if (node.left != null) {
            node.left = removeMin(node.left);
            return node;
        }
        if (node.right != null) {
            count--;
            return node.right;
        }
        count--;
        return null;
    }
    
    private Node<K, V> remove(Node<K, V> node, K key) {
        // 递归到底，要删除的节点并不存在，返回 null
        if (node == null) {
            return null;
        }
        // 要删除的 key 更大，右子树递归
        node.count--;
        if (node.key.compareTo(key) < 0) {
            node.right = remove(node.right, key);
            return node;
        }
        // 要删除的 key 更小，左子树递归
        if (node.key.compareTo(key) > 0) {
            node.left = remove(node.left, key);
            return node;
        }
        // 该节点正是需要删除的节点
        // 左子树不存在，右节点代替本节点
        if (node.left == null) {
            count--;
            return node.right;
        }
        // 右子树不存在，左节点代替本节点
        if (node.right == null) {
            count--;
            return node.left;
        }
        // 当左右子树都存在的时候，使用 Hubbard Deletion
        // 取右子树的最小节点代替当前节点，然后使该节点引用原左子树
        Node<K, V> successor = getMin(node.right);
        // 内含 count--，并返回了 node.right 交给 successor.right
        successor.right = removeMin(node.right);
        successor.left = node.left;
        successor.count = successor.right == null ?
                successor.left.count + 1 : successor.left.count + successor.right.count + 1;
        // 返回这个原右子树的最小节点代替本节点
        return successor;
    }
    
    public static class Node<K extends Comparable<K>, V> {
        
        private K key;
        private V value;
        private Node<K, V> left;
        private Node<K, V> right;
        private int count;
        
        private Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.count = 1;
        }
        
        public K getKey() {
            return key;
        }
        
        public V getValue() {
            return value;
        }
        
        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    ", count=" + count +
                    '}';
        }
    }
    
    
}
