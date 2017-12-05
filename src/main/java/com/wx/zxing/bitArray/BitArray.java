package com.wx.zxing.bitArray;

/**
 * 用 int 来储存 boolean 数组
 */
public class BitArray {

    private int[] bits;
    /** size 不是 bits 的大小, 而是被保存的数组大小 */
    private int size;


    public BitArray() {
        this.size = 0;
        this.bits = new int[1];
    }

    public BitArray(int size) {
        this.size = 0;
        this.bits = new int[(size + 31) / 32];
    }

    public void encode(boolean bit){
        if (bit){
            bits[size / 32] |= 1 << (size & 0x1F);
            System.out.println(bits[size / 32] + " % " + size);
        }
        size++;
    }

    public boolean get(int index){
        return (bits[index / size] & (1 << (index & 0x1F))) != 0;
    }

    public int[] getBitArray() {
        return bits;
    }

    public int getSize() {
        return size;
    }

}
