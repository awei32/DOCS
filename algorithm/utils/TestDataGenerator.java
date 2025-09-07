package algorithm.utils;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;

public class TestDataGenerator {

    /**
     * 随机数组生成
     *
     * @param arrayMinSize 数组最小长度
     * @param arrayMaxSize 数组最大长度
     * @param min 数组最小值
     * @param max 数组最大值
     * @return 随机数组
     */
    public static int[] ArrayGenerator(int arrayMinSize, int arrayMaxSize, int min, int max) {
        Random random = new Random();
        int arraySize = random.nextInt(arrayMaxSize - arrayMinSize + 1) + arrayMinSize;
        int[] arr = new int[arraySize];
        // 随机生成arraySize个范围为min~max大小的数组
        for (int i = 0; i < arraySize; i++) {
            arr[i] = random.nextInt(max - min + 1) + min;
        }
        return arr;
    }

    /**
     * 随机树（字符串）生成
     *
     * @param min 树最小节点数量
     * @param max 树最大节点数量
     * @return 随机树（字符串）
     */
    public static String[] TreeGenerator(int min, int max) {
        Random random = new Random();
        int size = random.nextInt(max - min + 1) + min;
        String[] nodes = new String[size];
        // 以队列存储每一层的节点，每个节点左右子树随机存在
        Queue<Integer> queue = new ArrayDeque<>(size);
        queue.add(random.nextInt());
        int i = 0;
        while (!queue.isEmpty()) {
            // 如果节点数量已为size，则队列中的节点的子节点都为null
            if (nodes.length == size) {
                break;
            }

            boolean isNull = random.nextBoolean();
            if (queue.isEmpty() && nodes.length < size) {
                isNull = false;
            }

            nodes[i++] = isNull == false ? queue.poll().toString() : "null";
            if (isNull) {
                continue;
            }
            queue.add(random.nextInt());
            queue.add(random.nextInt());
        }
        return nodes;
    }
}
