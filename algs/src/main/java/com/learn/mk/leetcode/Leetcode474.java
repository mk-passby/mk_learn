package com.learn.mk.leetcode;

/*
给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
请你找出并返回 strs 的最大子集的大小，该子集中 最多 有 m 个 0 和 n 个 1 。
如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/ones-and-zeroes
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Leetcode474 {
    public static void main(String[] args) {
        Leetcode474 leetcode474 = new Leetcode474();
        String[] strs = new String[]{"10", "0001", "111001", "1", "0"};
        int m = 5;
        int n = 3;
        System.out.println(leetcode474.findMaxForm(strs, m, n));
    }

    public int findMaxForm(String[] strs, int m, int n) {
        int[][][] dp = new int[strs.length + 1][m + 1][n + 1];
        for (int i = 1; i <= strs.length; i++) {
            int[] count = getCounts(strs[i - 1]);
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    //如果当前字符串长度大于目标长度，持有上一个结果的结果
                    if (j < count[0] || k < count[1]) {
                        dp[i][j][k] = dp[i - 1][j][k];
                    } else {
                        dp[i][j][k] = Math.max(dp[i - 1][j - count[0]][k - count[1]] + 1, dp[i - 1][j][k]);
                    }
                }
            }
        }
        return dp[strs.length][m][n];


    }

    private int[] getCounts(String str) {
        int[] result = new int[2];
        for (char c : str.toCharArray()) {
            result[c - '0']++;
        }
        return result;
    }
}
