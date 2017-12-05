package com.wx.zxing.bitArray;

/**
 * 用来测试BitArray
 */
public class BitArrayTest {

    public static void main(String[] args) {
//        int bit = 0;
//        boolean[] array = new boolean[]{false,true,false,false,true,true,false,true,false,false,true,true,false,true,false,false,true,true,false,true,false,false,true,true,false,true,false,false,true,true,false,true,false,false,true,true,false,true,false,false,true,true,false,true,false,false,true,true};
        boolean[] array = new boolean[]{false,true,false,false,true,true};
//        boolean[] array = new boolean[]{true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true};
        System.out.println(array.length + "!!!!!!");
//  bit = encode1(bit,array);
//        System.out.println(bit);
//        decode(bit,array.length);
//        System.out.println();
//        int[] bits = new int[4];
//        bits = encode2(bits, array);
//        for (int b : bits){
//            System.out.print(b + " ");
//        }
//        System.out.println();
        int a = 3;
        a |= 1;
        System.out.println(a);

        BitArray bitArray = new BitArray(array.length);
        for (int i = 0; i < array.length; i++){
            bitArray.encode(array[i]);
        }
        int[] bits2 = bitArray.getBitArray();
        System.out.println("……………………………………");
        for (int b : bits2){
            System.out.print(b + " ");
        }
        System.out.println();

        for (int i = 0; i < bitArray.getSize(); i++){
            System.out.print(bitArray.get(i) + " ");
        }

    }

    /**
     * 将一个长度小于32的 boolean 数组用一个 int 保存起来
     * 最终将 bit 的二进制写出来 与数组的顺序是逆序的
     * e.g.
     * 需要被保存的数组 false,true,false,false,true
     * 最终结果是 18 二进制：10010
     * @param bit 保存的 int 值
     * @param array 需要被保存的 boolean 数组
     */
    public static int encode1(int bit, boolean[] array){
        if (array.length > 32){
            throw new IllegalArgumentException("数组太长...");
        }
        for (int i = 0; i < array.length; i++){
            if (array[i]){
                /*
                (i & 0x1F) : 0x1F 是31 二进制是 00011111, 与其相与,
                             确保 i 小于32, 即移位不超过31 bit 位
                 */
                bit |= 1 << (i & 0x1F);
            }
        }
        return bit;
    }

    public static int[] encode2(int[] bits, boolean[] array){
        if (array.length > 32){
            throw new IllegalArgumentException("数组太长...");
        }
        for (int i = 0; i < array.length; i++){
            if (array[i]){
                /*
                (i & 0x1F) : 0x1F 是31 二进制是 00011111, 与其相与,
                             确保 i 小于32, 即移位不超过31 bit 位
                 */
                bits[i / 32] |= 1 << (i & 0x1F);
            }
        }
        return bits;
    }

    public static void decode(int bit, int size){
        for (int i = 0; i < size; i++){
            System.out.print(((bit & (1 << (i & 0x1F))) != 0) + " ");
        }
    }

}
