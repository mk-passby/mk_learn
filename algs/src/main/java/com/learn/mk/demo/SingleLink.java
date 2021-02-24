package com.learn.mk.demo;

/**
 * @program: learning-demo
 * @description:单链表反转
 * @author: mk_passby
 * @create: 2020-11-10 21:51
 **/
public class SingleLink {
    public static void main(String[] args) {
        Node1 temp = null;
        for (int i = 0; i < 10; i++) {
            Node1 parm = new Node1(temp, i);
            temp = parm;
        }
        Node1 result = convertNode1(temp);
    }

    private static Node1 convertNode1(Node1 param) {
        Node1 result = null;
        Node1 temp = param;
        while (temp != null) {
            result = new Node1(result, temp.getValue());
            temp = temp.getNext();
        }
        return result;
    }
}

class Node1 {
    private Node1 next;
    private int value;
    public Node1(Node1 next, int value) {
        this.next = next;
        this.value = value;
    }

    public Node1 getNext() {
        return next;
    }

    public int getValue() {
        return value;
    }
}
