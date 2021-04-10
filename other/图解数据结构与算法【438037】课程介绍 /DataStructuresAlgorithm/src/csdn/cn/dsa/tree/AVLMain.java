package csdn.cn.dsa.tree;

public class AVLMain {
    public static void main(String[] args) {
        AVL avl = new AVL();
        avl.addEle(20);
        avl.addEle(25);
        avl.addEle(22);


        boolean bst = avl.isBST();
        System.out.println(bst);

        System.out.println(avl.isBalanced());
//        avl.levelOrder();



    }
}
