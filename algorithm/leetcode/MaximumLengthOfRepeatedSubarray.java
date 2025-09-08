package algorithm.leetcode;
import algorithm.utils.Print;
import algorithm.utils.ConvertUtils;
public class MaximumLengthOfRepeatedSubarray {
    class Solution {
        public int findLength(int[] nums1, int[] nums2) {
            int[][] dp = new int[nums1.length + 1][nums2.length + 1];
            for(int i=1;i<=nums1.length;i++){
                for(int j=1;j<=nums2.length;j++){
                    if(nums1[i-1]==nums2[j-1]){
                        dp[i][j] = dp[i-1][j-1] + 1;
                    }
                    Print.print(dp);
                    System.out.println();
                }
            }
            int res = 0;
            for(int i=1;i<=nums1.length;i++){
                for(int j=1;j<=nums2.length;j++){
                    res = Math.max(res,dp[i][j]);
                }
            }
            System.out.println("最终结果:");
            Print.print(dp);
            return res;
        }
    }
    public static void main(String[] args) {
        int[] nums1 = ConvertUtils.strToIntArray("[1,2,3,2,1]");
        int[] nums2 = ConvertUtils.strToIntArray("[3,2,1,4,7]");
        Solution solution = new MaximumLengthOfRepeatedSubarray().new Solution();
        int res = solution.findLength(nums1, nums2);
        System.out.println(res);
        // 打印dp数组
        // Print.print(dp);
    }
}
