package com.tech.haoran.LeetCodeDemo.business.leetcode.listnode;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {
    static Comparator<ListNode> listNodeComparator = new Comparator<ListNode>() {
        @Override
        public int compare(ListNode o1, ListNode o2) {
            return o1.val-o2.val;
        }
    };

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode result = null;
        //优先队列。 长度
        Queue<ListNode> queue = new PriorityQueue<ListNode>(9,listNodeComparator);
        //循环源数据
        for (int i = 0 ;i<lists.length;i++){
            ListNode listNode = lists[i];
            while(listNode!=null){
                ListNode temp = listNode;
                listNode = listNode.next;
                temp.next = null;
                queue.add(temp);
            }
        }
        result = queue.poll();
        ListNode temp = result;
        //弹出优先队列
        while (!queue.isEmpty()){
            temp.next=queue.poll();
            temp = temp.next;
        }
        return result;
    }



    public static void main(String[] args) {
        ListNode a = new ListNode(1,4,5);
        ListNode b = new ListNode(1,3,4);
        ListNode c = new ListNode(2,6);
        ListNode[] lists = {a,b,c};
        Solution solution = new Solution();
        ListNode listNode = solution.mergeKLists(lists);

    }
}
