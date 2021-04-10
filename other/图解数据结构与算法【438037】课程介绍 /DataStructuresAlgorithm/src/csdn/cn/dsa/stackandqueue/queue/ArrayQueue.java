package csdn.cn.dsa.stackandqueue.queue;

import com.sun.deploy.uitoolkit.impl.awt.AWTAppletAdapter;
import csdn.cn.dsa.array.Array;
import jdk.management.resource.internal.inst.SocketOutputStreamRMHooks;

public class ArrayQueue<E> implements Queue<E>{

    private Array<E> array;

    public ArrayQueue(int capacity){
        array = new Array<>(capacity);
    }
    public ArrayQueue(){
        array = new Array<>();
    }

    @Override
    public void enqueue(E e) {
        array.addLast(e);
    }

    @Override
    public E dequeue() {
        return array.removeFirst();
    }

    @Override
    public E getFront() {
        return array.getFirst();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("Queue: front [");
        for(int i=0;i<array.getSize();i++){
            builder.append(array.get(i));
            if(i!=array.getSize()-1){
                builder.append(",");
            }
        }
        builder.append("] tail");
        return builder.toString();
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue<>();
        for(int i=0;i<8;i++){
            queue.enqueue(i);
            System.out.println(queue);
            if(i%3==0){
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }
}
