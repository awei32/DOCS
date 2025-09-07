package algorithm.utils;

import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {

    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    // 字符串数组转二叉树
    public TreeNode(String[] arr) {
        this.val = Integer.parseInt(arr[0]);
        TreeNode n = this;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(n);
        int i = 1;
        while (!queue.isEmpty() && i < arr.length - 1) {
            n = queue.poll();
            if ("null".equals(arr[i])) {
                n.left = null;
            } else {
                n.left = new TreeNode(Integer.parseInt(arr[i]));
                queue.offer(n.left);
            }
            if ("null".equals(arr[i + 1])) {
                n.right = null;
            } else {
                n.right = new TreeNode(Integer.parseInt(arr[i + 1]));
                queue.offer(n.right);
            }
            i += 2;
        }
    }

    // 可视化二叉树
    public void print() {
        print(this, "", true);
    }

    private void print(TreeNode root, String prefix, boolean isLeft) {
        if (root == null) {
            System.out.println(prefix+(isLeft?"├──":"└──")+"null");
            return;
        }
        System.out.println(prefix + (isLeft ? "├──" : "└──") + root.val);
        print(root.left, prefix + (isLeft ? "│   " : "    "), true);
        print(root.right, prefix + (isLeft ? "│   " : "    "), false);
    }
}
