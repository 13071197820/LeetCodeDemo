package com.tech.haoran.LeetCodeDemo.business.leetcode.tree;

import java.util.*;

class TreeNode{
    Integer val;
    TreeNode left;
    TreeNode right;
}

public class Tree {
    /**
     * 层次遍历
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
        public static List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> result = new LinkedList<>();
            if(root==null){
                return result;
            }
            Queue<TreeNode> queue = new LinkedList<TreeNode>();
            queue.offer(root);
            while(!queue.isEmpty()){
                int count = queue.size();
                List<Integer> tempNode = new LinkedList<Integer>();
                while(count>0){
                    TreeNode node = queue.poll();
                    if(node==null){
                        continue;
                    }
                    tempNode.add(node.val);

                    if (node.left!=null){
                        queue.offer(node.left);
                    }
                    if (node.right!=null){
                        queue.offer(node.right);
                    }
                    count--;
                }
                result.add(tempNode);

            }
            return result;
        }

    /**
     * 前序遍历
      * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode node = root;
        while(node!=null ||!stack.isEmpty()){
            if(node!=null){
                result.add(node.val);
                stack.push(node);
                node = node.left;
            }else{
                TreeNode nodeTemp = stack.pop();
                node = nodeTemp.right;
            }
        }
        return result;

    }

    /**
     * 中序遍历
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode node = root;
        while(node!=null ||!stack.isEmpty()){
            if(node!=null){
                stack.push(node);
                node = node.left;
            }else{
                result.add(node.val);
                TreeNode nodeTemp = stack.pop();
                node = nodeTemp.right;
            }
        }
        return result;
    }

    /**
     * 后序遍历
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        if(root==null){
            return result;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        TreeNode preNode = null;
        while(!stack.isEmpty()){
            TreeNode currentNode = stack.peek();
            if(
                    (currentNode.left==null && currentNode.right==null)
                    ||
                    ( preNode!=null &&
                            (currentNode.left==preNode||currentNode.right==preNode)
                    )
            ){
                result.add(currentNode.val);
                preNode = currentNode;
                stack.pop();
            }else{
                if(currentNode.right!=null){
                    stack.push(currentNode.right);
                }
                if(currentNode.left!=null){
                    stack.push(currentNode.left);
                }


            }
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode();
        treeNode.val=1;
        TreeNode right = new TreeNode();
        right.val=2;
        treeNode.right=right;
        List<List<Integer>> result = levelOrder(treeNode);
        System.out.println(Arrays.toString(result.toArray()));
    }


}
