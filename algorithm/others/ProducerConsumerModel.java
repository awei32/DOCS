package algorithm.others;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.random.RandomGenerator;

public class ProducerConsumerModel {
    // 1. 缓冲区（存储产品，用队列实现）
    static class Buffer {
        private final Queue<Integer> queue; // 存储产品的队列
        private final int maxCapacity;      // 缓冲区最大容量
        private final Lock lock;            // 控制线程同步的锁
        private final Condition notFull;    // 缓冲区未满的条件（唤醒生产者）
        private final Condition notEmpty;   // 缓冲区未空的条件（唤醒消费者）

        // 初始化缓冲区
        public Buffer(int maxCapacity) {
            this.queue = new LinkedList<>();
            this.maxCapacity = maxCapacity;
            this.lock = new ReentrantLock();
            this.notFull = lock.newCondition();
            this.notEmpty = lock.newCondition();
        }

        // 生产产品
        public void produce(int product) throws InterruptedException {
            lock.lock();
            try {
                // 缓冲区满时，生产者阻塞，等待"notFull"信号
                while (queue.size() == maxCapacity) {
                    System.out.println("缓冲区已满，生产者" + Thread.currentThread().getName() + "等待...");
                    notFull.await(); // 释放锁，进入等待状态
                }

                queue.offer(product);
                System.out.println("生产者" + Thread.currentThread().getName() + "生产：" + product + 
                                   "，当前缓冲区大小：" + queue.size());

                // 生产后唤醒消费者
                notEmpty.signalAll();
            } finally {
                lock.unlock();  
            }
        }

        // 消费产品（从缓冲区取出）
        public int consume() throws InterruptedException {
            lock.lock(); 
            try {
                // 缓冲区空时，消费者阻塞，等待"notEmpty"信号
                while (queue.isEmpty()) {
                    System.out.println("缓冲区为空，消费者" + Thread.currentThread().getName() + "等待...");
                    notEmpty.await(); // 释放锁，进入等待状态
                }

                // 消费产品
                int product = queue.poll();
                System.out.println("消费者" + Thread.currentThread().getName() + "消费：" + product + 
                                   "，当前缓冲区大小：" + queue.size());

                // 消费后唤醒生产者
                notFull.signalAll();
                return product;
            } finally {
                lock.unlock();
            }
        }
    }

    // 2. 生产者线程（循环生产产品）
    static class Producer implements Runnable {
        private final Buffer buffer;
        private int productId; 

        public Producer(Buffer buffer, int startProductId) {
            this.buffer = buffer;
            this.productId = startProductId;
        }

        @Override
        public void run() {
            while (true) { // 循环生产（实际场景可加终止条件）
                try {
                    buffer.produce(productId++); // 生产产品
                    Thread.sleep(RandomGenerator.getDefault().nextInt(1000,5000)); // 1000ms-5000ms
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }

    // 3. 消费者线程（循环消费产品）
    static class Consumer implements Runnable {
        private final Buffer buffer;

        public Consumer(Buffer buffer) {
            this.buffer = buffer;
        }

        @Override
        public void run() {
            while (true) { // 循环消费
                try {
                    buffer.consume(); // 消费产品
                    Thread.sleep(RandomGenerator.getDefault().nextInt(1000,5000)); // 随机时间
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }

    // 4. 测试主方法
    public static void main(String[] args) {
        Buffer buffer = new Buffer(5); // 缓冲区最大容量为5
        
        // 线程池
        ExecutorService executor = Executors.newCachedThreadPool();

        for(int i=0;i<10;i++){
            switch (i%2) {
                case 0:
                    executor.execute(new Producer(buffer, i));
                    break;
                case 1:
                    executor.execute(new Consumer(buffer));
                    break;
                default:
                    break;
            }
            
        }
    }
}