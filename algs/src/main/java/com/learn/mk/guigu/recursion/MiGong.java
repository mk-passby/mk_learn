package com.learn.mk.guigu.recursion;

/**
 * @program: learning-demo
 * @description:
 * @author: mk_passby
 * @create: 2020-11-29 16:02
 **/
public class MiGong {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //地图
        int[][] map = new int[8][7];
        //上下全部置1
        for(int i = 0 ; i<7;i++){
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //左右全部置1
        for (int i = 0; i< 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //设置挡板
        map[3][1] = 1;
        map[3][2] = 1;
        //打印地图
        for(int i = 0; i< 8; i++) {
            for (int j=0;j <7;j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        setWay2(map, 1, 1);
        //打印新地图
        System.out.println("递归回溯后~~~");
        for(int i = 0; i< 8; i++) {
            for (int j=0;j <7;j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }


    }

    // 使用递归回溯来找路
    // 1. map 表示地图
    // 2. i j 是指定从地图的哪个点开始出发 (1,1)
    // 3. 约定 元素的值 0: 可以走还没有走 1 : 墙 2: 表示可以走 3 : 表示已经走过，但是是死路
    // 确定一个策略: 下=》右=》上=》左
    // 修改策略 上=》右=》下=》左

    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) { // 表示路已经找到了
            return true;
        } else {
            if (map[i][j] == 0) { // 0: 可以走还没有走
                // 这里开始递归回溯
                map[i][j] = 2; // 认为该点是可以走通,但是不一定
                if (setWay(map, i + 1, j)) { // 下找
                    return true;
                } else if (setWay(map, i, j + 1)) { // 右
                    return true;
                } else if (setWay(map, i - 1, j)) { // 上
                    return true;
                } else if (setWay(map, i, j - 1)) { // 左
                    return true;
                } else {
                    // 走不通
                    map[i][j] = 3;
                    return false;
                }
            } else { // 如果map(i)(j)!=0 //则值 1,2,3
                return false;
            }
        }
    }

    //修改策略 上=》右=》下=》左, 注意修改的地方
    //1. if (setWay2(map, i - 1, j)) { // 上找 [setWay2 和 i - 1]
    //2. } else if (setWay2(map, i + 1, j)) { // 下 [setWay2 和 i + 1]
    public static boolean setWay2(int[][] map, int i, int j) {
        if (map[6][5] == 2) { // 表示路已经找到了
            return true;
        } else {
            if (map[i][j] == 0) { // 0: 可以走还没有走
                // 这里开始递归回溯
                map[i][j] = 2; // 认为该点是可以走通,但是不一定
                if (setWay2(map, i - 1, j)) { // 上找
                    return true;
                } else if (setWay2(map, i, j + 1)) { // 右
                    return true;
                } else if (setWay2(map, i + 1, j)) { // 下
                    return true;
                } else if (setWay2(map, i, j - 1)) { // 左
                    return true;
                } else {
                    // 走不通
                    map[i][j] = 3;
                    return false;
                }
            } else { // 如果map(i)(j)!=0 //则值 1,2,3
                return false;
            }
        }
    }

}
