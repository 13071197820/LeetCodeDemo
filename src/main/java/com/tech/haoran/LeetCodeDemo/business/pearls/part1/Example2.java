package com.tech.haoran.LeetCodeDemo.business.pearls.part1;

/**
 * 如何使用位逻辑运算（如 与、或、移位）来实现位向量
 */
public class Example2 {
//    public BitSet vector(){
//
//    }
    int length = 10000000;
    byte[] value = new byte[(length+1)/8];
    /**
     * 位置从1开始编码 位图中八位代表一个字节
     * 1000 0000
     * 0100 0000
     * 0010 0000
     * 0001 0000
     * 0000 1000
     * 0000 0100
     * 0000 0010
     * 0000 0001
     * */
    private static byte[] matrix = {
            (byte) 0x80, (byte) 0x40, (byte) 0x20,(byte) 0x10,
            (byte) 0x08, (byte) 0x04, (byte) 0x02, (byte) 0x01 };

    /**
     * 设置当前位值
     */
    public void set(int number){
        value[(number+1)/8]= (byte) (value[(number+1)/8] | matrix[number%8]);
    }

    /**
     * 判断是否存在该值
     * @return
     */
    public int has(int number){
        return value[(number+1)/8] & matrix[number%8];
    }

    /**
     * 清除当前值
     * @return
     */
    public byte clear(int number){
        return (byte) ((byte)value[(number+1)/8] &(~matrix[number%8]));
    }

}
