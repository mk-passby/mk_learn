package com.learn.mk;

import java.util.Scanner;
import java.util.TreeSet;

/**
 * @program: learning-demo
 * @description:
 * @author: mk_passby
 * @create: 2020-04-23 22:40
 **/
public class Test {

    public static void main(String[] args) {

        Scanner scanner=new Scanner(System.in);
        if(scanner.hasNextInt()){
            int first=scanner.nextInt();
            TreeSet<Integer> set=new TreeSet();
            for(int i=0;i<first;i++){
                set.add(scanner.nextInt());
            }
            int second=scanner.nextInt();
            for(int j=0;j<second;j++){
                set.add(scanner.nextInt());
            }
            for(Integer result:set){
                System.out.println(result);
            }



        }
    }

    private static int[] sortArray(int[] a, int[] b) {
        if (a==null||b==null){
            return new int[0];
        }
        if (a==null||a.length==0){
            return b;
        }
        if (b==null||b.length==0){
            return a;
        }
        int[] result=new int[a.length+b.length];
        int indexA=0;
        int indexB=0;
        while (indexA<a.length&&indexB<b.length){
            if (a[indexA]<b[indexB]){
                result[indexA+indexB]=a[indexA];
                 indexA++;
            }
            else{
                result[indexA+indexB]=b[indexB];
                indexB++;
            }
        }
        if (indexA<a.length){
            System.arraycopy(a,indexA,result,indexA+indexB, a.length-indexA);
        }
        if (indexB<b.length){
            System.arraycopy(b,indexB,result,indexA+indexB, b.length-indexB);
        }

        return result;



    }


}
