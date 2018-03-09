package com.kun.uf;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;

/**
 * @author CaoZiye
 * @version 1.0 2018/3/7 23:19
 */
public class UnionFindTest {
    
    private static int size = 1000;
    private static QuickFind quickFind = new QuickFind(size);
    private static OptimizedUnionFind optimizedUF = new OptimizedUnionFind(size);
    private static PathCompressionUnionFind pcUF = new PathCompressionUnionFind(size);
    private Instant instant;
    private String ufName;
    
    @Before
    public void init() {
        instant = Instant.now();
    }
    
    @After
    public void tearDown() {
        System.out.println(ufName + "   " + Duration.between(instant, Instant.now()));
    }
    
    @Test
    public void testQuickFind() {
        ufName = "QuickFind";
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            quickFind.union(random.nextInt(size), random.nextInt(size));
        }
        for (int i = 0; i < size; i++) {
            quickFind.isConnected(random.nextInt(size), random.nextInt(size));
        }
    }
    
    @Test
    public void testOptimizedUF() {
        ufName = "OptimizedUnionFind";
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            optimizedUF.union(random.nextInt(size), random.nextInt(size));
        }
        for (int i = 0; i < size; i++) {
            optimizedUF.isConnected(random.nextInt(size), random.nextInt(size));
        }
    }
    
    @Test
    public void testPcUF() {
        ufName = "PathCompressionUnionFind";
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            pcUF.union(random.nextInt(size), random.nextInt(size));
        }
        for (int i = 0; i < size; i++) {
            pcUF.isConnected(random.nextInt(size), random.nextInt(size));
        }
    }
    
}
