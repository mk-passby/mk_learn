package com.learn.mk.guigu.sparsearray;

/**
 * @program: learning-demo
 * @description: 稀疏数组
 * @author: mk_passby
 * @create: 2020-11-10 22:32
 **/
public class SparseArray {

    public static void main(String[] args) {
        int[][] chessArray = new int[11][11];
        chessArray[1][2] = 1;
        chessArray[2][3] = 2;
        System.out.println("输出原始数组:");
        for (int[] row : chessArray) {
            for (int i : row) {
                System.out.printf("%d\t", i);
            }
            System.out.println();
        }

        System.out.println("组装稀疏数组中");
        int sum = 0;
        //遍历，得到非0个数
        for (int[] row : chessArray) {
            for (int i : row) {
                if (i != 0) {
                    sum++;
                }
            }
        }
        //构建结果数组
        int[][] sparseArr = new int[sum + 1][3];
        //组装结果数组
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;
        int temp = 0;
        for (int i = 0; i < chessArray.length; i++) {
            for (int j = 0; j < chessArray[i].length; j++) {
                if (chessArray[i][j] != 0) {
                    temp++;
                    sparseArr[temp][0] = i;
                    sparseArr[temp][1] = j;
                    sparseArr[temp][2] = chessArray[i][j];
                }
            }
        }
        System.out.println("输出稀疏数组");
        //遍历输出结果数组
        for (int[] row : sparseArr) {
            for (int i : row) {
                System.out.printf("%d\t", i);
            }
            System.out.println();
        }
        System.out.println("还原稀疏数组");
        //确保原始数组有值
        if (sparseArr != null && sparseArr.length == 3) {
            int[][] chessArrResult = new int[sparseArr[0][0]][sparseArr[0][1]];
            int chessArrResultSum = sparseArr[0][2];
            for (int i = 1; i <= chessArrResultSum; i++) {
                chessArrResult[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
            }

            System.out.println("输出从稀疏数组还原的数据:");
            for (int[] row : chessArrResult) {
                for (int i : row) {
                    System.out.printf("%d\t", i);
                }
                System.out.println();
            }
        }
    }

}
