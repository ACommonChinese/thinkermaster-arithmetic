package csdn.cn.dsa.stackandqueue.queue;

public class LoopQueue<E> implements Queue<E> {

    private E[] data;
    private int front,tail;
    private int size;


    public LoopQueue(int capacity){
        data = (E[]) new Object[capacity+1];
        front = tail = size =0;
    }
    public LoopQueue(){
        this(10);
    }


    @Override
    public void enqueue(E e) {
        if((tail+1)%data.length==front){
            resize(getCapacity()*2);
        }
        data[tail] = e;
        tail = (tail+1)%data.length;
        size++;

    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity + 1];
        for(int i=0;i<size;i++){
            newData[i] = data[(i+front)%data.length];
        }
        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public E dequeue() {
        if(isEmpty()){
            throw  new RuntimeException("empty queue cannot dequeue from queue");
        }
        E e = data[front];
        data[front] = null;
        front = (front+1)%data.length;
        size--;
        if(size==getCapacity()/4&&getCapacity()/2!=0){
            resize(getCapacity()/2);
        }

        return e;
    }

    @Override
    public E getFront() {
        if(isEmpty()){
            throw  new RuntimeException("empty queue !");
        }
        return data[front];
    }

    @Override
    public boolean isEmpty() {
        return front==tail;
    }

    @Override
    public int getSize() {
        return size;
    }
    public int getCapacity(){
        return data.length-1;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Queue: size = %d , capacity = %d\n", size, getCapacity()));
        builder.append("front [");
        for(int i=front;i!=tail;i=(i+1)%data.length){
            builder.append(data[i]);
            if((i+1)%data.length!=tail){
                builder.append(",");
            }
        }
        builder.append("]");
        return builder.toString();

    }

    public static void main(String[] args) {
        LoopQueue<Integer> quueue = new LoopQueue<>();
        for(int i=0;i<10;i++){
            quueue.enqueue(i);
            System.out.println(quueue);
            if(i%3==2){
                quueue.dequeue();
                System.out.println(quueue);
            }
        }
    }

}
