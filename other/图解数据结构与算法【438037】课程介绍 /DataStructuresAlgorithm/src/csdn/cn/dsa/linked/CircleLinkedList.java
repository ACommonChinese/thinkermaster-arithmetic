package csdn.cn.dsa.linked;

import com.sun.corba.se.pept.transport.InboundConnectionCache;
import sun.reflect.generics.tree.VoidDescriptor;

import javax.sound.midi.Soundbank;

public class CircleLinkedList<E> {
    private class Node<E>{
        public E e;
        private Node next;
        private Node(E e,Node next){
            this.e =e;
            this.next= next;
        }
        public Node(E e){
            this(e,null);
        }
        public Node(){
            this(null,null);
        }
        @Override
        public String toString(){
            return e.toString();
        }
    }

    //头节点和尾节点
    private Node head = null;
    private Node tail = null;

    public CircleLinkedList(int nums){
        if(nums<1){
            throw  new RuntimeException("nums < 1");
        }
        for(int i=1;i<=nums;i++){
            Node node = new Node(i);
            if(i==1){
                head = node;
                head.next = head;
                tail = head;
            }else{
                tail.next = node;
                node.next=head;
                tail = node;
            }
        }
    }

    public CircleLinkedList(){
        this(5);
    }

    /**
     *
     * @param startNo 从第几个开始数
     * @param countNum 数几下
     * @param nums 最开始的node数
     */
    public void exitCircle(int startNo, int countNum, int nums){
        //数据的校验
        if(head==null||startNo<1||startNo>nums){
            throw  new RuntimeException("参数非法");
        }

        for(int i=0;i<startNo-1;i++){
            head = head.next;
            tail = tail.next;
        }
        while (true) {
            if (tail == head) {
                break;
            }
            for(int i = 0; i < countNum - 1; i++){
                head = head.next;
                tail = tail.next;
            }
            System.out.println(head.e+"元素出圈");
            head = head.next;
            tail.next = head;
            System.out.println("最后留在圈内的元素是："+head.e);


        }
    }







//            for(int i=0;i<countNum-1;i++){
//                head = head.next;
//                tail = tail.next;
//            }
//            System.out.println(head.e+" 元素出圈");
//            head = head.next;
//            tail.next = head;
//        }
//        System.out.println("最后留在圈内的元素是："+head.e);
//
//    }

    public static void main(String[] args) {
        CircleLinkedList<Integer> circleLinkedList = new CircleLinkedList<>();
        circleLinkedList.exitCircle(1,2,5);
    }

}
