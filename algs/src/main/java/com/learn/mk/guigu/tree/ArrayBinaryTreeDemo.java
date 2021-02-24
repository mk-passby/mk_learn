package com.learn.mk.guigu.tree;
/*
顺序存储二叉树
  1
 2 3
4 5 6
 */
public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] param={1,2,3,4,5,6};
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(param);
        System.out.println("前序遍历");
        arrayBinaryTree.preOrder();
        System.out.println("中序遍历");
        arrayBinaryTree.middleOrder();
        System.out.println("后序遍历");
        arrayBinaryTree.middleOrder();
    }

}
class ArrayBinaryTree{
    private int[] arr;

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    public void preOrder(){
        preOrder(0);
    }

    //二叉树的前序遍历
    private void preOrder(int index){
        if (arr==null|| index>arr.length){
            return;
        }
        System.out.println(arr[index]);
        //向左遍历
        if ((index*2+1)<arr.length){
            preOrder(1+index*2);
        }
        //向右遍历
        if ((index*2+2)<arr.length){
            preOrder(2+index*2);
        }
    }

    public void middleOrder() {
        middleOrder(0);
    }

    //二叉树的中序遍历
    private void middleOrder(int index){
        if (arr==null|| index>arr.length){
            return;
        }
        //向左遍历
        if ((index*2+1)<arr.length){
            middleOrder(1+index*2);
        }
        System.out.println(arr[index]);
        //向右遍历
        if ((index*2+2)<arr.length){
            middleOrder(2+index*2);
        }
    }

    public void afterOrder() {
        middleOrder(0);
    }

    //二叉树的后序遍历
    private void afterOrder(int index){
        if (arr==null|| index>arr.length){
            return;
        }
        //向左遍历
        if ((index*2+1)<arr.length){
            afterOrder(1+index*2);
        }
        System.out.println(arr[index]);
        //向右遍历
        if ((index*2+2)<arr.length){
            afterOrder(2+index*2);
        }
    }
}
