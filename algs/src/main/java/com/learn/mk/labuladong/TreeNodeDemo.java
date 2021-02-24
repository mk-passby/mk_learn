package com.learn.mk.labuladong;

import java.util.Arrays;

public class TreeNodeDemo {
    public static void main(String[] args) {
        TreeNodeDemo treeNodeDemo=new TreeNodeDemo();
        int[] param={3,2,1,6,0,5};
        TreeNode treeNode = treeNodeDemo.constructMaximumBinaryTree(param);
        System.out.println(treeNode);
    }
    TreeNode constructMaximumBinaryTree(int[] nums){
        if (nums.length==0){
            return null;
        }
        if (nums.length==1){
            return new TreeNode(nums[0]);
        }

        int maxValueIndex=0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i]>nums[maxValueIndex]){
                maxValueIndex=i;
            }
        }
        TreeNode treeNode = new TreeNode(nums[maxValueIndex]);
        treeNode.left=
                constructMaximumBinaryTree(
                        Arrays.copyOf(nums,maxValueIndex));
        treeNode.right=
                constructMaximumBinaryTree(
                        Arrays.copyOfRange(nums,maxValueIndex+1,nums.length));
        return treeNode;
    }
}

class TreeNode{
    int value;
    TreeNode left;
    TreeNode right;

    public TreeNode(int value) {
        this.value = value;
    }
}
