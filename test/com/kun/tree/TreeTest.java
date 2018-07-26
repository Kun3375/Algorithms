package com.kun.tree;

import org.junit.Test;

/**
 * @author CaoZiye
 * @version 1.0 2018/3/4 13:45
 */
public class TreeTest {

    @Test
    public void testTree() {
        BinarySearchTree<Integer, String> tree = new BinarySearchTree<>();
        tree.put(4, "D");
        tree.put(1, "A");
        tree.put(7, "G");
        tree.put(3, "C");
        tree.put(5, "E");
        tree.put(9, "I");
        System.out.println(tree.size());
        tree.preOrder(System.out::println);
        System.out.println();
        tree.inOrder(System.out::println);
        System.out.println();
        tree.postOrder(System.out::println);
        System.out.println();
        tree.levelOrder(System.out::println);
        System.out.println();
        System.out.println(tree.contain(5));
        System.out.println(tree.get(7));
        System.out.println();
        tree.removeMax();
        tree.inOrder(System.out::println);
        System.out.println();
        tree.remove(4);
        tree.inOrder(System.out::println);
        System.out.println();
    }

}
