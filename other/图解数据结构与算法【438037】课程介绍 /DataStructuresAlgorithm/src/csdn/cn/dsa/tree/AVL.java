package csdn.cn.dsa.tree;

import java.util.*;

public class AVL<E extends Comparable<E>> {

    private class Node{
        public E e;
        public Node left;
        public Node right;
        public int height;

        public Node(E e){
            this.e = e;
            this.left = null;
            this.right = null;
            this.height = 1;
        }
        public String toString(){
            return "it is node value is::"+e;
        }

    }
    public Node root;
    private int size;
    public int size(){
        return  size;
    }

    //判断是不是二叉搜索树
    public boolean isBST(){
        ArrayList<E> eles = new ArrayList<>();
        midOrder(eles);
        for(int i=1;i<eles.size();i++){
            if(eles.get(i-1).compareTo(eles.get(i))>0){
                return false;
            }
        }
        return true;
    }

  public boolean isBalanced(){
        return isBalanced(root);
  }

    private boolean isBalanced(Node node) {
       if(node==null) return true;
       int bf = getBalancedFactor(node);
       if(Math.abs(bf)>1) return false;
       return isBalanced(node.left)&&isBalanced(node.right);
    }

    private int getBalancedFactor(Node node) {
        if (node ==null) return 0;
        return getHeight(node.left)-getHeight(node.right);
    }

    private int getHeight(Node node) {

        if(node==null) return 0;
        return node.height;
    }

    public void addEle(E e){
         root = addEle(root,e);
    }

    /**
     * 将元素E添加到以node为根节点的树上
     * 返回的就是新插入节点后的子树的根
     * @param node
     * @param e
     */
    private Node addEle(Node node, E e) {

        if(node == null){
            size++;
           return new Node(e);
        }
        if(e.compareTo(node.e)<0){
            node.left =  addEle(node.left,e);
        }else{
            node.right =  addEle(node.right,e);
        }
        // 更新height
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        // 计算平衡因子
        int balanceFactor = getBalancedFactor(node);
        //LL 不平衡节点的左侧的左侧 LL，右旋平衡
        if(balanceFactor>1&&getBalancedFactor(node.left)>=0){
//            System.out.println("LL 需要右旋来完成平衡");
            return rightRotate(node);
        }

        //RR 左旋
        if(balanceFactor<-1&&getBalancedFactor(node.right)<=0){
            return leftRotate(node);
        }

        //LR
        if(balanceFactor>1&&getBalancedFactor(node.left)<0){
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        //RL
        if(balanceFactor<-1&&getBalancedFactor(node.right)>0){
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }




        return node;
    }


    // 对节点n进行向左旋转操作，返回旋转后新的根节点r
    //    n                             r
    //  /  \                          /   \
    // T1   r      向左旋转 (n)       n     a
    //     / \   - - - - - - - ->   / \   / \
    //   T2  a                     T1 T2 T3 T4
    //      / \
    //     T3 T4
    private Node leftRotate(Node node) {

        Node r = node.right;
        Node T2 = r.left;
        //左旋
        r.left=node;
        node.right=T2;
        //重新计算高度
        node.height=Math.max(getHeight(node.left),getHeight(node.right))+1;
        r.height=Math.max(getHeight(r.left),getHeight(r.right))+1;
        return r;
    }

    // n是不平衡节点，r是新的根节点，a是符合avl的子树的根节点
    // 对节点n进行向右旋转操作，返回旋转后新的根节点r
    //        n                              r
    //       / \                           /   \
    //      r   T4     向右旋转 (n)        a     n
    //     / \       - - - - - - - ->    / \   / \
    //    a   T3                       T1  T2 T3 T4
    //   / \
    // T1   T2
    private Node rightRotate(Node node) {
        Node r = node.left;
        Node T3 = r.right;
        //右旋转
        r.right = node;
        node.left = T3;
        //维护高度
        node.height=Math.max(getHeight(node.left),getHeight(node.right))+1;
        r.height=Math.max(getHeight(r.left),getHeight(r.right))+1;

        return r;
    }


    public Node search(E e){
        return search(root,e);
    }

    /**
     * 在以node为根的树上去查找元素e所在的节点
     */
    private Node search(Node node, E e) {
        if(node==null){
            return null;
        }
        if(e.compareTo(node.e)==0){
            return node;
        }else if(e.compareTo(node.e)<0){
            return search(node.left,e);
        }else {
            return search(node.right,e);
        }
    }
    public Node searchParent(E e){
        return searchParent(root,e);
    }

    /**
     * 从以node为根的树种查找元素e的所在节点的父节点
     * @param node
     * @param e
     * @return
     */
    private Node searchParent(Node node, E e) {

        if(node == null){
            return null;
        }
        if((node.left!=null&&e.compareTo(node.left.e)==0)||(node.right!=null&&e.compareTo(node.right.e)==0)){
            return node;
        }else {
            if(node.left!=null&&e.compareTo(node.e)<0){
                return searchParent(node.left,e);
            }else if(node.right!=null&e.compareTo(node.e)>0){
                return searchParent(node.right,e);
            }else {
                return null;
            }
        }


    }


    //前序遍历
    public  void preOrder(){
        preOrder(root);
    }

    /**
     * 前序遍历以node为根的bst
     * @param node
     */
    private void preOrder(Node node) {
        if (node==null){
            return;
        }
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    public void midOrder(List eles){
        midOrder(root,eles);
    }

    private void midOrder(Node node,List eles) {
        if (node==null){
            return;
        }

        midOrder(node.left,eles);
        eles.add(node.e);
        midOrder(node.right,eles);
    }

    /**
     * 非递归的像是前序遍历
     */
    public void perOrderNR(){

        if(root == null){
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            Node curNode = stack.pop();
            System.out.println(curNode.e);
            if(curNode.right!=null){
                stack.push(curNode.right);
            }
            if (curNode.left!=null){
                stack.push(curNode.left);
            }
        }
    }

    public void levelOrder(){
        if(root ==null){
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            Node curNode = queue.remove();
            System.out.println(curNode.e);
            if(curNode.left!=null){
                queue.add(curNode.left);
            }
            if(curNode.right!=null){
                queue.add(curNode.right);
            }
        }
    }
    /**
     * 删除掉以node为根的二分搜索树中值为e的节点, 递归算法
     * 返回删除节点后新的二分搜索树的根
     *   node是叶子节点
     *          p
     *           \
     *            n
     *   node只有右子树删除n节点
     *          n
     *           \
     *            r
     *  node只有左子树 删除n节点
     *             n
     *           /
     *          l
     *  node既有左子树又有右子树删除n节点
     *              p                              p
     *            /  \                          /   \
     *           T1   n      m 是min节点      T1     m
     *               / \   - - - - - - - ->         /  \
     *             T2  a                           T2  a
     *                / \                              \
     *              m   T3                              T3
     */
    public void delNode(E e){
        if(root == null) return;
        Node targetNode = search(e);
        if(targetNode == null) return;
        Node parent = searchParent(e);

        //叶子节点的情况
        if(targetNode.left==null && targetNode.right==null){
            if(parent.left!=null&&e.compareTo(parent.left.e)==0){
                parent.left=null;
                size--;
            }
            if(parent.right!=null&&e.compareTo(parent.right.e)==0){
                parent.right=null;
                size--;
            }
        }else if(targetNode.left!=null&&targetNode.right!=null){ //有两棵子树
            //找出右子树的最小节点
           Node minNode = minNode(targetNode.right);
           delNode(minNode.e);
           targetNode.e = minNode.e;

           size--;
        }else{ //只有一棵子树 TODO 未考虑的情况 根节点只有一棵子树
            if(targetNode.left!=null){ //只有左子树
                if(e.compareTo(parent.left.e)==0){
                    parent.left=targetNode.left;
                    size--;
                }else {
                    parent.right=targetNode.right;
                    size--;
                }
            }else{ //要删除的节点有右子树
                if(e.compareTo(parent.left.e)==0){
                    parent.left=targetNode.right;
                    size--;
                }else {
                    parent.right=targetNode.right;
                    size--;
                }
            }
        }
    }

    private Node minNode(Node node) {
        if(node.left == null) return node;
        return  minNode(node.left);
    }

    // 从以node为根的树中删除e元素所在的对节点，并将删除e节点后生成的新的子树的根返回
    public Node removeNode(Node node,E e){
        if(node == null){
            return  null;
        }

        Node retNode;
        if(e.compareTo(node.e)<0){
            node.left = removeNode(node.left, e);
            retNode = node;
        }else if(e.compareTo(node.e)>0){
            node.right = removeNode(node.right, e);
            retNode = node;
        }else {
            if(node.left==null){ //包含了叶子节点的删除和只有一棵子树的删除操作
               Node rightNode = node.right;
               node.right=null;
               size--;
                retNode = rightNode;
            }
            if(node.right==null){
                Node leftNode = node.left;
                node.left = null;
                size--;
                retNode = leftNode;
            }

            Node minNode = minNode(node.right);
            minNode.right = removeNode(node.right,minNode.e);
            minNode.left = node.left;
            node.left=node.right=null;
            retNode =  minNode;

        }
        if (retNode == null) return null;
        // 更新height
        retNode.height = 1 + Math.max(getHeight(retNode.left), getHeight(retNode.right));

        // 计算平衡因子
        int balanceFactor = getBalancedFactor(retNode);
        //LL 不平衡节点的左侧的左侧 LL，右旋平衡
        if(balanceFactor>1&&getBalancedFactor(retNode.left)>=0){
//            System.out.println("LL 需要右旋来完成平衡");
            return rightRotate(retNode);
        }

        //RR 左旋
        if(balanceFactor<-1&&getBalancedFactor(retNode.right)<=0){
            return leftRotate(retNode);
        }

        //LR
        if(balanceFactor>1&&getBalancedFactor(retNode.left)<0){
            retNode.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }
        //RL
        if(balanceFactor<-1&&getBalancedFactor(retNode.right)>0){
            retNode.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }

        return retNode;
    }

}
