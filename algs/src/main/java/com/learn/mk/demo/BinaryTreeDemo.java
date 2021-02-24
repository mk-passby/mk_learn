package com.learn.mk.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * @program: learning-demo
 * @description:二叉树的遍历
 * @author: mk_passby
 * @create: 2020-11-10 21:53
 **/
public class BinaryTreeDemo {

    /*
  二叉树的遍历
       1
      / \
     2   3
    /\    \
   4  5    6
     /\
    7  8
  前序:根结点 ---> 左子树 ---> 右子树   12457836
  中序:左子树---> 根结点 ---> 右子树    42758136
  后序:左子树 ---> 右子树 ---> 根结点   47852631
  层次遍历：1  2  3  4  5  6  7 8
   */
    public static void main(String[] args) {
        TreeNode treeNode = getTreeNode();
//        System.out.println("前序递归遍历");
//        preOrderTraverse1(treeNode);
//        System.out.println("中序递归遍历");
//        middleOrderTraverse1(treeNode);
//        System.out.println("后序递归遍历");
//        afterOrderTraverse1(treeNode);
//------------------------------------------------
//        System.out.println("前序非递归遍历");
//        preOrderTraverse2(treeNode);
        System.out.println("中序非递归遍历");
        middleOrderTraverse2(treeNode);
        //afterOrderTraverse2(treeNode);
    }

    /*
    递归实现前序遍历
     */
    public static void preOrderTraverse1(TreeNode treeNode) {
        if (treeNode != null) {
            System.out.println(treeNode.value);
            preOrderTraverse1(treeNode.left);
            preOrderTraverse1(treeNode.right);
        }
    }

    /*
      递归实现中序遍历
       */
    public static void middleOrderTraverse1(TreeNode treeNode) {
        if (treeNode != null) {
            middleOrderTraverse1(treeNode.left);
            System.out.println(treeNode.value);
            middleOrderTraverse1(treeNode.right);
        }
    }

    /*
         递归实现后序遍历
          */
    public static void afterOrderTraverse1(TreeNode treeNode) {
        if (treeNode != null) {
            afterOrderTraverse1(treeNode.left);
            afterOrderTraverse1(treeNode.right);
            System.out.println(treeNode.value);
        }
    }

    public static void preOrderTraverse2(TreeNode treeNode) {
        Stack<TreeNode> stack = new Stack();
        TreeNode pNode = treeNode;
        while (pNode != null || !stack.isEmpty()) {
            if (pNode != null) {
                System.out.print(pNode.value + "  ");
                stack.push(pNode);
                pNode = pNode.left;
            }
            else { //pNode == null && !stack.isEmpty()
                TreeNode node = stack.pop();
                pNode = node.right;
            }
        }
    }

    public static void middleOrderTraverse2(TreeNode treeNode) {
        Stack<TreeNode> stack = new Stack();
        TreeNode pNode = treeNode;
        while (pNode != null || !stack.isEmpty()) {
            if (pNode != null) {
                stack.push(pNode);
                pNode = pNode.left;
            }
            else { //pNode == null && !stack.isEmpty()
                TreeNode node = stack.pop();
                System.out.print(node.value + "  ");
                pNode = node.right;
            }
        }
    }

    /*
    利用反转，为了得到"左->右->根"，先得到"根->右->左"
     */
    public static void afterOrderTraverse2(TreeNode treeNode) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack();
        stack.push(treeNode);
        while (!stack.isEmpty()) {
            TreeNode treeNodeStack = stack.pop();
            if (treeNodeStack != null) {
                System.out.println(treeNodeStack.value);
                list.add(treeNodeStack.value);
            }
            if (treeNodeStack.left != null) {
                stack.push(treeNodeStack.left);
            }
            if (treeNodeStack.right != null) {
                stack.push(treeNodeStack.right);
            }
        }
        Collections.reverse(list);
        System.out.println(Arrays.toString(list.toArray()));
    }


    /*
    非递归实现前序遍历
    根据前序遍历的顺序，优先访问根结点，然后在访问左子树和右子树。
    所以，对于任意结点node，遍历完左子树后马上需要遍历右子树，即右子树需要存储，且需要有先进后出的顺序(Stack)
    1.入参入栈
    2. while (!stack.isEmpty())
    3.取出当前节点
    4.如果当前节点不为null，直接输出内容
    5.如果右子树不为null，右子树入栈
    6.如果左子树不为null，左子树入栈
    注意第5点和第6点，因为要先遍历左子树，需要右子树先入栈

    //缺陷：只能处理前序，中序和后序很难处理
     */
    public static void preOrderTraverse3(TreeNode treeNode) {
        Stack<TreeNode> stack = new Stack();
        stack.push(treeNode);
        while (!stack.isEmpty()) {
            TreeNode treeNodeStack = stack.pop();
            if (treeNodeStack.right != null) {
                stack.push(treeNodeStack.right);
            }
            if (treeNodeStack != null) {
                System.out.println(treeNodeStack.value);
            }
            if (treeNodeStack.left != null) {
                stack.push(treeNodeStack.left);
            }
        }
    }

    private static TreeNode getTreeNode() {
        TreeNode treeNode8 = new TreeNode(null, null, 8);
        TreeNode treeNode7 = new TreeNode(null, null, 7);
        TreeNode treeNode6 = new TreeNode(null, null, 6);
        TreeNode treeNode5 = new TreeNode(treeNode7, treeNode8, 5);
        TreeNode treeNode4 = new TreeNode(null, null, 4);
        TreeNode treeNode3 = new TreeNode(null, treeNode6, 3);
        TreeNode treeNode2 = new TreeNode(treeNode4, treeNode5, 2);
        return new TreeNode(treeNode2, treeNode3, 1);
    }
}


class TreeNode {
    TreeNode left;
    TreeNode right;
    Integer value;

    public TreeNode(TreeNode left, TreeNode right, Integer value) {
        this.left = left;
        this.right = right;
        this.value = value;
    }
}
