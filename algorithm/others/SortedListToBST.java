package algorithm.others;

import algorithm.utils.*;
import java.util.Arrays;

// 有序链表转排序二叉树
public class SortedListToBST {

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;
        ListNode pre = null;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        if (pre != null) {
            pre.next = null;
        }
        TreeNode root = new TreeNode(slow.val);

        root.left = pre != null ? sortedListToBST(head) : null;
        root.right = sortedListToBST(slow.next);
        return root;
    }

    public static void main(String[] args) {
        // 随机生成链表
        int n = 10;
        for (int i = 0; i < n; i++) {
            System.out.println("=====第" + (i + 1) + "条数据=====");

            int[] arr = TestDataGenerator.ArrayGenerator(0, 10, 0, 100);
            Arrays.sort(arr);
            ListNode list = new ListNode(arr);
            list.print();

            SortedListToBST sortedListToBST = new SortedListToBST();
            TreeNode root = sortedListToBST.sortedListToBST(list);
            root.print();
            System.out.println("=====第" + (i + 1) + "条数据=====");
            System.out.println();
        }
    }
}
