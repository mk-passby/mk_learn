package com.learn.mk.leetcode;

public class Leetcode1641 {

    //"a","e","i","o","u"
    public static void main(String[] args) {
        Leetcode1641 solution = new Leetcode1641();
        //int result = solution.creaseResult(33,5);
        int result= solution.getCount(33);
        System.out.println(result);
    }

    //index[5,4,3,2,1]
    public int getCount(int n){
       int[] dp=new int[]{1,1,1,1,1};
        for (int i = 2; i <= n; i++) {
            int sum=0;
            for (int j = 0; j < dp.length; j++) {
                sum +=dp[j];
                dp[j] = sum;
            }
        }

       int result=0;
        for (int i = 0; i < dp.length; i++) {
            result+=dp[i];
        }
        return result;
    }

    public int creaseResult(int m,int n){
       if (m<=0){
           return 1;
       }
       if (n<=0){
           return 0;
       }
        int result=0;
        for (int i = 0; i <= m; ++i) {
            result+=creaseResult(m-i,n-1);
        }
        return result;
    }
}
/*
1:1+1+1+1+1
2:5+4+3+2+1
3:(5+4+3+2+1)+
(4+3+2+1)+
....
4:(5+4+3+2+1)+(4+3+2+1)+....+
    (4+3+2+1)+....+
    (3+2+1)+....
 */