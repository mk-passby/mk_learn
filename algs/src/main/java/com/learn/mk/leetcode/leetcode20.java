package com.learn.mk.leetcode;

import java.util.Stack;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class leetcode20 {

    public boolean isValid(String s) {
        char[] chars = s.toCharArray();
        Stack<Character> stack=new Stack<>();
        for (char aChar : chars) {
            if (aChar=='{'||aChar=='['||aChar=='('){
                stack.push(aChar);
            }else if (stack.size()==0){
                //stack中没有数据，但是还在循环，说明进入的元素是 }]）
                return false;
            } else {
                if (stack.peek()=='{'&&aChar=='}'){
                    stack.pop();
                    continue;
                }
                if (stack.peek()=='['&&aChar==']'){
                    stack.pop();
                    continue;
                }
                if (stack.peek()=='('&&aChar==')'){
                    stack.pop();
                    continue;
                }
                //不匹配
                return false;
            }
        }
        return stack.size()==0;
    }

    public static void main(String[] args) {
        leetcode20 leetcode20=new leetcode20();
        leetcode20.isValid("]");

    }
}
