package algorithm.leetcode;

import algorithm.utils.Print;

public class DistinctSubsequences {
    class Solution {
        public int numDistinct(String s, String t) {
            int[][] dp=new int[t.length()+1][s.length()+1];
            for (int i = 1; i <= t.length(); i++) {
                for (int j = 1; j <= s.length(); j++) {
                    if(s.charAt(j-1) == t.charAt(i-1)){
                        if(i==1){
                            dp[i][j]=1+dp[i][j-1];
                        }else{
                            dp[i][j]=dp[i-1][j-1]+dp[i][j-1];
                        }
                    }else{
                        dp[i][j]=dp[i][j-1];
                    }
                    System.out.println("i:"+i+" j:"+j);
                    Print.print(dp);
                    System.out.println();
                }
            }
            
            return dp[t.length()][s.length()];
        }
    }
    public static void main(String[] args) {
        DistinctSubsequences distinctSubsequences=new DistinctSubsequences();
        Solution solution=distinctSubsequences.new Solution();
        int i = solution.numDistinct("babgbag", "bag");
        System.out.println("结果:"+i);
    }
}
