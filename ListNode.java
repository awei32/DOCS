package algorithm.utils;

public class ListNode {

    public int val;
    public ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    // 数组转链表
    public ListNode(int[] arr) {
        this.val = arr[0];
        ListNode n = this;
        for (int i = 1; i < arr.length; i++) {
            n.next = new ListNode(arr[i]);
            n = n.next;
        }
    }
    // 可视化链表

    public void print() {
        ListNode cur = this;
        StringBuilder builder = new StringBuilder();
        while (cur != null) {
            builder.append(cur.val).append("->");
            cur = cur.next;
        }
        builder.append("null");
        System.out.println(builder.toString());
    }
}
