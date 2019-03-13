package com.tech.haoran.LeetCodeDemo.business.leetcode.interlivestring;


import com.sun.jdi.PathSearchingVirtualMachine;

public class Solution {

    /**
     *  初始状态→│决策１│→│决策２│→…→│决策ｎ│→结束状态
     *   (1)划分阶段
     *   字符数组1，2，3   3可能为交错字符串
     *   (2)确定状态和状态变量
     *   A 字符数组1下一个元素  B 字符数组2下一个元素 C 无效元素
     *   (3)确定决策并写出状态转移方程
     *   如果初始状态为 A 则下一个状态为A B C 中的一个
     *   (4)寻找边界条件
     *   字符数组3循环完成，状态均为A或B。 出现无效元素C
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        if("".equals(s1)&&"".equals(s2)&&"".equals(s3)){
            return true;
        }
        if(s1.length()+s2.length()!=s3.length()){
            return false;
        }
        char[] charactersArray_1;
        char[] charactersArray_2;
        char[] charactersArray_3;

        boolean[][] result = new boolean[s1.length()+1][s2.length()+1];

        charactersArray_1 = s1.toCharArray();
        charactersArray_2 = s2.toCharArray();
        charactersArray_3 = s3.toCharArray();


        int index_1 =0;
        int index_2 =0;

        for (int i =0;i<charactersArray_3.length;i++){

            char temp = charactersArray_3[i];
            //判断状态A
            if(charactersArray_1.length>0 && temp == charactersArray_1[index_1]){
                index_1 = (index_1==charactersArray_1.length-1) ? 0 : index_1+1;
                continue;
            }
            //判断状态B
            if(charactersArray_2.length>0 && temp == charactersArray_2[index_2]){
                index_2 = (index_2==charactersArray_2.length-1) ? 0 : index_2+1;
                continue;
            }
            //判断状态C
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        s.isInterleave("aabcc","dbbca","aadbbcbcac");
    }
}
