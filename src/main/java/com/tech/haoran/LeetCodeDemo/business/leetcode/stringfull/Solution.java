package com.tech.haoran.LeetCodeDemo.business.leetcode.stringfull;

import java.util.ArrayList;
import java.util.List;

class Solution {
    //1. 尽可能多地往每行中放置单词
    //2. 空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。
    //3. 均匀分配单词间的空格数量  左侧放置的空格数要多于右侧
    //4. 最后一行应为左对齐.
    public List<String> fullJustify(String[] words, int maxWidth) {
        //候选对象集合
        List<String> result = new ArrayList<String>();
        int totalWords = words.length;

        int index = 0;
        while (index<totalWords){
            StringBuilder targetLine = new StringBuilder();
            int varLength = 0;
            ArrayList<String> wordsLen = new ArrayList<>();
            //丢弃选出的候选对像后，遍历输入参数
            for (int i=index;i<totalWords;i++){
                String word = words[i];
                if(varLength+word.length()+wordsLen.size()-1 >= maxWidth){
                    break;
                }
                index=index+1;
                varLength = varLength+word.length();
                wordsLen.add(word);
            }
            int totalWhiteLen = maxWidth - varLength;
            int whiteNum = wordsLen.size()-1==0?1:wordsLen.size()-1;
            int whiteLen = totalWhiteLen/(wordsLen.size()==1?1:whiteNum);
            int notExact = totalWhiteLen%(wordsLen.size()==1?1:whiteNum);

            //组装候选对象
            for (int i=0;i<wordsLen.size();i++){
                targetLine.append(wordsLen.get(i));
                //非末尾行逻辑
                if(index!=totalWords){
                    if(whiteNum==0){
                        break;
                    }
                    targetLine.append(getWhiteNum(whiteLen));
                    whiteNum -=1;
                    if (notExact!=0){
                        targetLine.append(' ');
                        notExact -=1;
                    }
                }else{
                    targetLine.append(i==wordsLen.size()-1?getWhiteNum(totalWhiteLen-wordsLen.size()+1):' ');
                }
            }
            result.add(targetLine.toString());
        }
        return result;
    }

    public String getWhiteNum(int num){
        StringBuilder sb = new StringBuilder();
        for (int i=0 ;i<num;i++){
            sb.append(' ');
        }
        return sb.toString();
    }
}