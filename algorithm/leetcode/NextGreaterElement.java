package algorithm.leetcode;

import java.util.Arrays;
import java.util.Map;
import java.util.Stack;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] result = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums2.length; i++) {
            while (!stack.isEmpty() && nums2[i] > stack.peek()) {
                map.put(stack.pop(), nums2[i]);
            }
            stack.push(nums2[i]);
        }

        for (int j = 0; j < n; j++) {
            result[j] = map.getOrDefault(nums1[j], -1);
        }
        return result;
    }
}