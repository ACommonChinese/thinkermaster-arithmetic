package csdn.cn.dsa.tree;

public class RBTMain {
    public static void main(String[] args) {
        RBT rbt = new RBT();

        rbt.addEle(42);
        rbt.addEle(37);
        rbt.addEle(40);
        rbt.levelOrder();

    }
}
