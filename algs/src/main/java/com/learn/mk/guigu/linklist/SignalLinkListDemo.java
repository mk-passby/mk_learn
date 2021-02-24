package com.learn.mk.guigu.linklist;

import java.time.temporal.Temporal;

/**
 * @program: learning-demo
 * @description:
 * @author: mk_passby
 * @create: 2020-11-15 20:20
 **/
public class SignalLinkListDemo {

    public static void main(String[] args) {
        // 测试单向链表的添加和遍历
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(3, "宋江3", "及时雨3");
        HeroNode hero3 = new HeroNode(4, "宋江4", "及时雨4");
        HeroNode hero4 = new HeroNode(2, "宋江2", "及时雨2");
// 创建一个单向链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
//        singleLinkedList.addHero(hero1);
//        singleLinkedList.addHero(hero2);
//        singleLinkedList.addHero(hero3);
//        singleLinkedList.addHero(hero4);
        singleLinkedList.addHeroByOrder(hero1);
        singleLinkedList.addHeroByOrder(hero2);
        singleLinkedList.addHeroByOrder(hero3);
        singleLinkedList.addHeroByOrder(hero4);
        singleLinkedList.showList();
        System.out.println("反转后");
        new Tools(singleLinkedList.getHeroNode()).reverseNode();
        singleLinkedList.showList();

    }
}

class SingleLinkedList {

    //头结点不动
    private HeroNode heroNode = new HeroNode(0, null, null);

    public HeroNode getHeroNode() {
        return heroNode;
    }

    public void addHero(HeroNode param) {
        HeroNode temp = heroNode;
        while (true) {
            if (temp.next == null) {
                temp.next = param;
                break;
            }
            temp = temp.next;
        }
    }

    public void addHeroByOrder(HeroNode param) {
        HeroNode temp = heroNode;
        HeroNode next = heroNode.next;
        while (true) {
            if (next == null) {
                temp.next = param;
                break;
            }
            if (param.num < next.num) {
                temp.next = param;
                param.next = next;
                break;
            }
            temp = temp.next;
            next = temp.next;
        }
    }

    public void showList() {
        if (heroNode.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = heroNode.next;
        while (true) {
            if (temp == null) {
                return;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

}


class Tools {

    private HeroNode heroNode;

    public Tools(HeroNode heroNode) {
        this.heroNode = heroNode;
    }

    /**
     * 获取单链表的长度
     */
    public int getCount() {
        if (heroNode.next == null) {
            System.out.println("链表为空");
            return 0;
        }
        int result = 0;
        HeroNode temp = heroNode;
        while (temp.next != null) {
            result++;
            temp = temp.next;
        }
        return result;
    }

    /**
     * 查找倒数第K个元素
     */
    public HeroNode findByIndex(int index) {
        //入参错误或者node为null，返回
        if (index <= 0 || heroNode.next == null) {
            return null;
        }
        int size = getCount();
        //入参错误返回
        if (index > size) {
            return null;
        }
        HeroNode current = heroNode.next;
        for (int i = 0; i < size - index; i++) {
            current = current.next;
        }
        return current;
    }

    /**
     * 反转单链表
     */
    public void reverseNode() {
        if (heroNode.next == null || heroNode.next.next == null) {
            return;
        }
        HeroNode current = heroNode.next;
        HeroNode next = null;
        HeroNode reverseHead = new HeroNode(0, null, null);
        while (current != null) {
            //缓存next,方便下一次遍历
            next = current.next;
            current.next = reverseHead.next;
            reverseHead.next = current;
            current = next;
        }
        heroNode.next = reverseHead.next;
    }
    

}

class HeroNode {

    public int num;
    public String name;
    public String nickName;
    public HeroNode next;

    public HeroNode(int num, String name, String nickName) {
        this.num = num;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
            "num=" + num +
            ", name='" + name + '\'' +
            ", nickName='" + nickName + '\'' +
            '}';
    }
}
