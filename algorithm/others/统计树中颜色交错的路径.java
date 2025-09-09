package algorithm.others;

import java.util.*;
import java.util.Scanner;

public class 统计树中颜色交错的路径 {
    class Tree {
        Tree left;
        Tree right;
        int val;

        public Tree(int val) {
            this.val = val;
        }
    }

    static int RED = 0;
    static int BLACK = 1;

    class solution{
        Scanner sc = new Scanner(System.in);
        Map<Integer,Tree> map = new HashMap<>();
        Tree root;
        int n;
        n = sc.nextInt();

        for(int i=0;i<n;i++){
            int t1 = sc.nextInt();
            int t2 = sc.nextInt();
            if(!map.containsKey(t1)){
                map.put(t1,new Tree());
            }
            if(!map.containsKey(t2)){
                map.put(t2,new Tree());
            }
            Tree tree1 = map.get(t1);
            Tree tree2 = map.get(t2);
            if(tree1.left == null){
                tree1.left = tree2;
            }else{
                tree1.right = tree2;
            }
        }
        
        String color = sc.next();

        for(int i=1;i<=n;i++){
            Tree tree = map.get(i);
            tree.val = color.charAt(i-1) == 'R' ? RED : BLACK;
        }

        int dp[][] = new int[n+1][2];

        int parent_color = map.get(1).val;
        for(int i=1;i<=n;i++){
            Tree tree = map.get(i);
            
        }
    }
}