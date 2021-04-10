package csdn.cn.dsa.linked;

import javax.sound.midi.Soundbank;

public class Main {
    public static void main(String[] args) {
        SingleLikedList<Integer> linkedList = new SingleLikedList<>();

        for(int i=0;i<5;i++){
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }
        linkedList.add(1,100);
        System.out.println(linkedList);
        linkedList.reverset();
        System.out.println(linkedList);

        System.out.println(linkedList.contains(100));
        System.out.println(linkedList.get(1));

        linkedList.set(1,101);
        System.out.println(linkedList);

        linkedList.remove(1);
        System.out.println(linkedList);

        linkedList.removeFirst();
        linkedList.removeLast();
        linkedList.removeElement(2);
        System.out.println(linkedList);
    }
}
