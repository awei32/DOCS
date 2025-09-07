package algorithm.utils;

import java.util.LinkedList;
import java.util.Queue;

public class ConvertUtils {
    // 格式为"[1,2,3,4,5,6,7]"的字符串转为数组
    public static int[] strToIntArray(String str){
        str = str.substring(1, str.length()-1);
        String[] arr = str.split(",");
        int[] res = new int[arr.length];
        for(int i = 0; i < arr.length; i++){
            res[i] = Integer.parseInt(arr[i]);
        }
        return res;
    }
    // 格式为"[1,2,3,4,5,6,7]"的字符串转为链表
    public static ListNode strToListNode(String str){
        int[] arr = strToIntArray(str);
        ListNode head = new ListNode(arr[0]);
        ListNode n = head;
        for(int i = 1; i < arr.length; i++){
            n.next = new ListNode(arr[i]);
            n = n.next;
        }
        return head;
    }
    // 格式为"[1,2,3,4,5,6,7]"的字符串转为二叉树
    public static TreeNode strToTreeNode(String str){
        int[] arr = strToIntArray(str);
        TreeNode root = new TreeNode(arr[0]);
        TreeNode n = root;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(n);
        int i = 1;
        while(!queue.isEmpty() && i < arr.length-1){
            n = queue.poll();
            if("null".equals(arr[i])){
                n.left = null;
            }else{
                n.left = new TreeNode(arr[i]);
                queue.offer(n.left);
            }
            if("null".equals(arr[i+1])){
                n.right = null;
            }else{
                n.right = new TreeNode(arr[i+1]);
                queue.offer(n.right);
            }
            i += 2;
        }
        return root;
    }

}
