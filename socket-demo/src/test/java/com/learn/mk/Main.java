package com.learn.mk;

/**
 * @program: learning-demo
 * @description:
 * @author: mk_passby
 * @create: 2020-04-26 22:41
 **/
import java.util.*;
public class Main{
    public static void main(String[] args){
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

}