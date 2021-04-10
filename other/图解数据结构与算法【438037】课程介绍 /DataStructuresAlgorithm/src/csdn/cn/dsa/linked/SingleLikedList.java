package csdn.cn.dsa.linked;

import java.lang.reflect.WildcardType;

public class SingleLikedList<E> {
    private class Node{
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

    //头节点指针
    private Node dummyHead;
    private int size;

    public SingleLikedList(){
        dummyHead = new Node(null,null);
        size = 0 ;
    }
    public int getSize(){return size;};
    public boolean isEmpty(){return  size==0;};

    public void addFirst(E e){
        add(0,e);
    }

    public void add(int index,E e){

        if(index<0||index>size){
            throw  new RuntimeException("add failed ,index illegal");
        }

        Node prev = dummyHead;
        for(int i=0;i<index;i++){
            prev = prev.next;
        }

//            Node node = new Node(e);
//            node.next = prev.next;
//            prev.next = node;
        prev.next =  new Node(e,prev.next);
        size++;
    }

    public void addLast(E e){
        add(size,e);
    }

    // 获得链表的第index(0-based)个位置的元素
    public E get(int index){
        if(index<0||index>size){
            throw  new RuntimeException("get failed ,Illegal index");
        }
        Node cur = dummyHead.next;
       for(int i=0;i<index;i++){
           cur = cur.next;
       }
       return cur.e;

    }
    public E getFirst(){
        return get(0);
    }
    public E getLast(){
        return get(size-1);
    }

    public void set(int index,E e){
        if(index<0||index>size){
            throw  new RuntimeException("set failed ,Illegal index");
        }

        Node cur = dummyHead.next;
        for(int i=0;i<index;i++){
            cur = cur.next;
        }
        cur.e = e;

    }

    public boolean contains(E e){
        Node cur = dummyHead.next;
        while (cur!=null){
            if(cur.e.equals(e)){
                return true;
            }
            cur = cur.next;
        }
        return false;
    }
    // 从链表中删除index位置的元素, 返回删除的元素
    public E remove(int index){

        if(index<0||index>size){
            throw  new RuntimeException("remove fail,index is illegal");
        }
        Node prev = dummyHead;
        for(int i=0;i<index;i++){
            prev = prev.next;
        }

        Node retNode = prev.next;
        prev.next = retNode.next;
        retNode.next=null;
        size--;
        return retNode.e;
    }

    public void removeElement(E e){
        Node prev = dummyHead;
        while (prev.next!=null){
            if(prev.next.e.equals(e)){
                break;
            }
            prev = prev.next;
        }

        if(prev.next!=null){
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next=null;
            size--;
        }
    }

    public E removeFirst(){return remove(0);}
    public E removeLast(){return remove(size-1);};

    public void reverset(){
        Node cur = dummyHead.next;
        Node next = cur.next;
        Node reverseHead = new Node();
        while (cur!=null){
            next = cur.next;
            cur.next = reverseHead.next;
            reverseHead.next = cur;
            cur = next;
        }
        dummyHead = reverseHead;

    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        Node cur = dummyHead.next;
        while (cur!=null){
            builder.append(cur+"->");
            cur = cur.next;
        }
        builder.append("NULL");
        return builder.toString();
    }

}
