package com.learn.mk.guigu.tree;

/*
线索化二叉树
    1
 3      6
8 10  14

middel:8 3 10 1 14 6
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        Node node0 = new Node(1);
        Node node1 = new Node(3);
        Node node2 = new Node(6);
        Node node3 = new Node(8);
        Node node4 = new Node(10);
        Node node5 = new Node(14);
        node0.left = node1;
        node0.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        BinaryTree binaryTree = new BinaryTree(node0);
        binaryTree.threadedNodes();
        System.out.println("left:" + node4.left);
        System.out.println("right:" + node4.right);
        System.out.println("中序遍历线索化二叉树");
        binaryTree.printList();
    }
}

class Node {
    Node left;
    Node right;
    int value;
    //0:左子树   1：指向前驱节点
    int leftType;
    //0：右子树  1：指向后驱节点
    int rightType;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}

//定义一个BinaryTree，实现线索化功能的二叉树
class BinaryTree {
    private Node root;
    private Node pre = null;

    public BinaryTree(Node root) {
        this.root = root;
    }

    //实现中序线索化的方法
    public void threadedNodes() {
        threadedNodes(root);
    }

    //实现中序线索化的方法
    public void threadedNodes(Node node) {
        if (node == null) {
            return;
        }
        //1.先线索化左子树
        threadedNodes(node.left);
        //2.线索化当前节点
        //处理当前节点的前驱节点
        if (node.left == null) {
            node.left = pre;
            //修改当前节点的左指针类型，1指向前驱节点
            node.leftType = 1;
        }
        //处理后继节点，通过下次的遍历通过pre，处理后继节点
        if (pre != null && pre.right == null) {
            pre.right = node;
            pre.rightType = 1;
        }
        //处理完后，node后移
        pre = node;
        //3.线索化右子树
        threadedNodes(node.right);
    }
    //线索化后的遍历
    public void printList(){
        printList(root);
    }

    //线索化后的遍历
    public void printList(Node node){
        Node temp=node;
        while(temp!=null){
            //第一步，将node定位到为8的节点，一直向左遍历，直到leftType==1
            while(temp.leftType==0){
                temp=temp.left;
            }
            System.out.println(temp);
            //如果是后继节点，就直接输出
            while(temp.rightType==1){
                temp = temp.right;
                System.out.println(temp);
            }
            //如果不是后继节点，替换继续遍历
            temp=temp.right;
        }
    }
}