package csdn.cn.dsa.tree;

import com.sun.xml.internal.bind.marshaller.NoEscapeHandler;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BST<E extends Comparable<E>> {

    private class Node{
        public E e;
        public Node left;
        public Node right;

        public Node(E e){
            this.e = e;
            this.left = null;
            this.right = null;
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
        return node;
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

    public void midOrder(){
        midOrder(root);
    }

    private void midOrder(Node node) {
        if (node==null){
            return;
        }

        midOrder(node.left);
        System.out.println(node.e);
        midOrder(node.right);
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

        if(e.compareTo(node.e)<0){
            node.left = removeNode(node.left, e);
            return node;
        }else if(e.compareTo(node.e)>0){
            node.right = removeNode(node.right, e);
            return node;
        }else {
            if(node.left==null){ //包含了叶子节点的删除和只有一棵子树的删除操作
               Node rightNode = node.right;
               node.right=null;
               size--;
               return rightNode;
            }
            if(node.right==null){
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            Node minNode = minNode(node.right);
            minNode.right = removeNode(node.right,minNode.e);
            minNode.left = node.left;
            node.left=node.right=null;
            return minNode;

        }
    }

}
