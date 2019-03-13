package com.tech.haoran.LeetCodeDemo.business.leetcode.listnode;

public class ListNode {

      int val;

      ListNode next;

      ListNode(int x) {
            val = x;
      }

      ListNode(int x,int y) {
            val = x;
            next = new ListNode(y);
      }

      ListNode(int x,int y,int z) {
            val = x;
            next = new ListNode(y);
            next.next = new ListNode(z);
      }
}
