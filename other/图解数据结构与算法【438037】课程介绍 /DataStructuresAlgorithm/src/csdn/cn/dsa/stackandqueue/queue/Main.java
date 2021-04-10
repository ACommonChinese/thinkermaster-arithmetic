package csdn.cn.dsa.stackandqueue.queue;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int opNum = 100000;
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        double time1 = testQueue(arrayQueue,opNum);
        System.out.println("ArrayQueue time::"+time1+"s");

        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        double time2 = testQueue(loopQueue,opNum);
        System.out.println("LoopQueue time::"+time2+"s");
    }

    private static double testQueue(Queue<Integer> queue, int opNum) {
        long startTime = System.nanoTime();
        Random random = new Random();
        for(int i=0;i<opNum;i++){
            queue.enqueue(random.nextInt(Integer.MAX_VALUE));
        }

        for(int i=0;i<opNum;i++){
            queue.dequeue();
        }

        long endTime = System.nanoTime();
        return (endTime-startTime)/1000000000.0;
    }
}
