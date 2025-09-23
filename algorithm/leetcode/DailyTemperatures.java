package algorithm.leetcode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;
import java.util.ArrayDeque;

public class DailyTemperatures {
    class Solution {
        public int[] dailyTemperatures(int[] temperatures) {
            int[] res = new int[temperatures.length];
            Deque<Integer> stack = new LinkedList<>();
            for(int i = 0; i < temperatures.length; i++) {
                while(!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                    int index = stack.pop();
                    res[index] = i - index;
                }
                stack.push(i);
            }
            return res;
        }
    }
}
