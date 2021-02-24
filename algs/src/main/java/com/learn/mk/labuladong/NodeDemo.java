package com.learn.mk.labuladong;

/*
链表用例测试
 */
public class NodeDemo {
    static Node temp;

    public static void main(String[] args) {

        Node node = getNodeParam();
        System.out.println("单链表。。。。" + node);
        System.out.println("反转整个单链表");
        System.out.println(reverse1(node));
        node = getNodeParam();
        System.out.println("反转单链表前n个元素");
        System.out.println(reverseN(node, 4));
        System.out.println("反转单链表区间[m,n]的元素");
        node = getNodeParam();
        System.out.println(reverseMN(node, 2, 4));

        System.out.println("k个一组反转链表");
        node = getNodeParam();
        System.out.println(reversePerK(node, 2));
    }

    //每k个一组反转链表
    private static Node reversePerK(Node node, int k) {
        if (node == null) {
            return node;
        }
        Node startNode = node;
        Node endNode = node;
        for (int i = 0; i < k; i++) {
            if (endNode == null) {
                return node;
            }
            endNode = node.next;
        }
        //反转startNode,endNode之间的元素,startNode为第一个元素
        Node newResult = reverse(startNode, endNode);
        node.next = reversePerK(endNode, k);
        return newResult;

    }

    private static Node reverse(Node startNode, Node endNode) {
        return null;
    }

    private static Node reverse1(Node node) {
        Node pre = null;
        Node current=node;
        while(current!=null){
            Node next=current.next;
            current.next=pre;
            pre=current;
            current=next;
        }

        return pre;
    }

    //反转mn区间的元素
    private static Node reverseMN(Node node, int m, int n) {
        if (m == 1) {
            return reverseN(node, n);
        }
        node.next = reverseMN(node.next, m - 1, n);
        return node;
    }


    //反转单链表前n个元素
    private static Node reverseN(Node node, int n) {

        if (n == 1) {
            temp = node.next;
            return node;

        }
        Node last = reverseN(node.next, n - 1);
        node.next.next = node;
        node.next = temp;
        return last;

    }

    //反转整个单链表
    private static Node getNodeParam() {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        return node1;
    }

    //反转单链表
    private static Node reverse(Node node) {
        if (node.next == null) {
            return node;
        }
        Node last = reverse(node.next);
        node.next.next = node;
        node.next = null;
        return last;
    }

}


class Node {
    public Node next;
    public int value;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "next=" + next +
                ", value=" + value +
                '}';
    }
}
