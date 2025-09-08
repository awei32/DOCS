package algorithm.utils;

public class Print {

    /**
     * 二维DP可视化
     */
    public static void print(int[][] dp) {
        if (dp.length == 0) {
            System.out.println("[]");
            return;
        }
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println("");
        }
    }

    
}
