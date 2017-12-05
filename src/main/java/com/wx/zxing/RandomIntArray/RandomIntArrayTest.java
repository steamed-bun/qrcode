package com.wx.zxing.RandomIntArray;

import java.util.Random;

/**
 * 测试使用 bit 位生成随机0、1矩阵
 */
public class RandomIntArrayTest {

    public static void main(String[] args) {
        int LWidthNum = 200;
        int LHeightNum = 100;
        Random random = new Random();
        int test = 0;
        long start = System.currentTimeMillis();
        int[][] input = new int[LWidthNum][LHeightNum];
        for (int i = 0; i < input.length; i++){
            for (int j = 0; j < input[0].length; j++){
                input[i][j] = random.nextInt(2);
            }
        }
//        模拟使用
        for (int i = 0; i < input.length; i++){
            for (int j = 0; j < input[0].length; j++){
                test = input[i][j];
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("一般方法时间：" + (end - start));

        long start1 = System.currentTimeMillis();
        int length = LHeightNum * LWidthNum;
        int num = (length / 32) + 1;
        int[] temp = new int[num];
        for (int j = 0; j < num; j++){
            temp[j] = random.nextInt(Integer.MAX_VALUE);
        }
//        模拟使用
        int size = 0;
        for (int i = 0; i < LWidthNum; i++){
            for (int j = 0; j < LHeightNum; j++){
                test = ((temp[size / 32] & (1 << ( size & 0x1F))) != 0 ? 1 : 0);
                size++;
            }
        }
        long end1 = System.currentTimeMillis();
        System.out.println("新方法时间：" + (end1 - start1));
    }

}
