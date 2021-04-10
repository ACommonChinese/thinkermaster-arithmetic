package csdn.cn.dsa.tree;

import javax.sound.midi.Soundbank;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        BST bst = new BST();
        bst.addEle(7);
        bst.addEle(3);
        bst.addEle(10);
        bst.addEle(1);
        bst.addEle(5);
        bst.addEle(9);
        bst.addEle(12);
        System.out.println(bst.size());
        System.out.println(bst.search(5));
        System.out.println(bst.searchParent(5));
        System.out.println("-------------");
//        bst.delNode(5);
        bst.root = bst.removeNode(bst.root,5);
        bst.levelOrder();
        System.out.println("-------------");
//        bst.delNode(3);
        bst.root = bst.removeNode(bst.root,3);
        bst.levelOrder();
        System.out.println("-------------");
//        bst.delNode(7);
        bst.root = bst.removeNode(bst.root,7);
        bst.levelOrder();

    }
}
