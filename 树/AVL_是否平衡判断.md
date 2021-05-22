# AVL_是否平衡判断

在让一棵二叉搜索树变平衡之前, 首先要判断这棵树是不是二叉搜索树, 然后再判断它是否平衡, 下面提供方法: 
- isBST() 用于判断一棵二叉树是不是二叉搜索树, 其原理是使用中序遍历看遍历后的数组中的元素是不是有序递增的
- isBalanced() 用于判断是否平衡, 其原理是判断树上所有的节点的平衡因子有没有大于1的,如果有, 则不平衡

```java
package com.daliu.tree;

import java.util.*;

public class AVL <E extends Comparable<E>> {
    // 二分搜索树的节点
    private class Node {
        E e;
        Node left;
        Node right;
        private int height; //节点的高度

        public Node(E e) {
            this.e = e;
            this.left = null;
            this.right = null;
            this.height = 1;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "e=" + e +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    private Node root;
    private int size; // 节点总个数
    public int size() {
        return size;
    }

    // 判断是不是二叉搜索树
    public boolean isBST() {
        // 二叉搜索树中序遍历得到的节点的集合是有序的
        // 使用这个特性判断是不是二叉搜索树
        ArrayList<E> elements = new ArrayList<>();
        //midOrder(elements);
        for (int i = 1; i < elements.size(); i++) {
            if (elements.get(i-1).compareTo(elements.get(i)) > 0) {
                return false;
            }
        }
        return true;
    }

    // 判断是不是平衡的
    public boolean isBalanced() {
        return isBalanced(root);
    }

    // 判断以node为根节点的树是不是平衡的
    private boolean isBalanced(Node node) {
        if (node == null) {
            return true;
        }
        // 获取平衡因子, 如果平衡因子大于1, 则不是平衡的
        int balanceFactor = getBalancedFactor(node);
        if (Math.abs(balanceFactor) > 1) return false;
        return isBalanced(node.left) && isBalanced(node.right);
    }

    // 获取平衡因子
    private int getBalancedFactor(Node node) {
        // 平衡因子 = abs(左子树.height - 右子树.height)
        if (node == null) return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    // 获取以node为根节点的树的高度
    private int getHeight(Node node) {
        if (node == null) return 0;
        return node.height;
    }

    public void addElement(E e) {
        root = addElement(root, e);
    }

    /**
     * 将元素E添加到以node为父节点的树上, 并返回这个父节点, 如果node为null, 则返回这个节点本身
     * @param node 要将元素添加到的父节点
     * @param e 元素
     * @return 新插入节点后的子树的根
     *
     * 注: 此方法暂未考虑值相同的情况, 值相同的情况忽略
     */
    public Node addElement(Node node, E e) {
        if (node == null) { // 递归的结束条件是递归到了叶子节点的下一个元素
            size++;
            return new Node(e);
        }
        if (e.compareTo(node.e) < 0) {
            node.left = addElement(node.left, e); // 递归调用
        } else if (e.compareTo(node.e) > 0) {
            node.right = addElement(node.right, e); // 递归调用
        }
        // 更新节点的高度, 节点的高度 = max(左子树高, 右子树高) + 1
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        // 更新节点的平衡因子
        // int balanceFactor = getBalancedFactor(node);

        return node;
    }

    /**
     * 查找元素所在节点
     * @param e 元素
     * @return 元素所在节点
     */
    public Node search(E e) {
        return search(root, e);
    }

    /**
     * 在以node为根节点的树上查找元素e所在的节点
     * @param node 根节点
     * @param e 要查找的元素
     * @return e 所在的节点
     */
    private Node search(Node node, E e) {
        if (node == null) {
            return null;
        }
        if (e.compareTo(node.e) == 0) {
            return node;
        } else if (e.compareTo(node.e) < 0) { // 去左子树查找
            return search(node.left, e);
        } else { // 去右子树查找
            return search(node.right, e);
        }
    }

    /**
     * 查找指定元素的父节点
     * @param e 元素
     * @return 元素e的父节点
     */
    public Node searchParent(E e) {
        return searchParent(root, e);
    }

    /**
     * 以node为起始根节点查找元素e所在节点的父节点
     * @param node 起始根节点
     * @param e 元素
     * @return 元素e的父节点
     */
    public Node searchParent(Node node, E e) {
        if (node == null) {
            return null;
        }
        if ((node.left != null && e.compareTo(node.left.e) == 0) || (node.right != null && e.compareTo(node.right.e) == 0)) {
            return node;
        }
        if (e.compareTo(node.e) < 0){
            return searchParent(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            return searchParent(node.right, e);
        } else {
            return null; // 因为add添加元素时没有考虑元素相等的情况,因此这里可以直接返回null
        }
    }

    public void preOrder() {
        preOrder(root);
    }

    /**
     * 非递归方式前序遍历
     */
    public void preOrder(Node node) {
        if (node == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node curNode = stack.pop();
            System.out.println(curNode.e);
            // 出栈的同时把它的右孩子和左孩子入栈
            if (curNode.right != null) {
                stack.push(curNode.right);
            }
            if (curNode.left != null) {
                stack.push(curNode.left);
            }
        }
    }


    public void midOrder() {
        midOrder(root);
    }

    /**
     * 非递归方式中序遍历
     */
    public void midOrder(Node node) {
        if (node == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        Node curNode = root;
        while (curNode != null || !stack.isEmpty()) {
            if (curNode != null) {
                stack.push(curNode); // 左子不为空, 左子入栈
                curNode = curNode.left;
            } else {
                curNode = stack.pop(); // 左子为空, 栈顶出栈
                System.out.println(curNode.e);
                curNode = curNode.right;
            }
        }
    }

    // 中序遍历并把结果放在数组中
    public void midOrder(List elements) {
        midOrder(root, elements);
    }

    // 中序遍历并把结果放在数组中
    public void midOrder(Node node, List elements) {
        if (node == null) {
            return;
        }
        midOrder(node.left, elements);
        elements.add(node.e);
        midOrder(node.right, elements);
    }

    public void postOrder() {
        postOrder(root);
    }

    /**
     * 非递归后序遍历
     */
    public void postOrder(Node node) {
        if (root == null) {
            return;
        }
        Stack<Node> stackA = new Stack<>();
        Stack<Node> stackB = new Stack<>();
        Node curNode = node;
        stackA.push(curNode);
        while (!stackA.isEmpty()) {
            curNode = stackA.pop();
            stackB.push(curNode);
            if (curNode.left != null) {
                stackA.push(curNode.left);
            }
            if (curNode.right != null) {
                stackA.push(curNode.right);
            }
        }
        while (!stackB.isEmpty()) {
            System.out.println(stackB.pop().e);
        }
    }

    // 层级遍历
    public void levelOrder() {
        levelOrder(root);
    }

    public void levelOrder(Node node) {
        if (node == null) {
            return;
        }
        // 使用JDK的队列LinkedList(LinkedList实现了Deque接口)
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node curNode = queue.remove();
            System.out.println(curNode.e);
            if (curNode.left != null) {
                queue.add(curNode.left);
            }
            if (curNode.right != null) {
                queue.add(curNode.right);
            }
        }
    }

    public void deleteNode(E e) {
        removeNode(root, e);
    }

    // 从以node为根的树中删除元素e对应的节点
    // 并将删除e节点后生成的新根返回
    public Node removeNode(Node node, E e) {
        if (node == null) {
            return null;
        }
        if (e.compareTo(node.e) < 0) {
            // 去左子树上删除
            node.left = removeNode(node.left, e);
            return node; // 删除后新树的根节点
        } else if (e.compareTo(node.e) > 0) {
            // 去右子树上删除
            node.right = removeNode(node.right, e);
            return node;
        } else {
            if (node.left == null) {
                /**
                 node
                 /    \
                 null  r
                 */
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            if (node.right == null) {
                /**
                 node
                 /    \
                 null  r
                 */
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }
            // 上面两种情况包含了叶子节点的删除和只有一棵子树的删除操作
            // 下面是第三种情况: 删除有两棵子树的节点
            // 需要找到要删除节点右子树中最小的节点(min)并替换到要删除的节点(n)
            /**
             *  node既有左子树又有右子树删除n节点, 下面n是要删除的节点, m是min
             *              p                              p
             *            /  \                          /   \
             *           T1   n      m 是min节点      T1     m
             *               / \   - - - - - - - ->         /  \
             *             T2  a                           T2  a
             *                / \                              \
             *              m   T3                              T3
             */
            Node minNode = minNode(node.right);
            minNode.right = removeNode(node.right, minNode.e);
            minNode.left = node.left;
            node.left = node.right = null;
            return minNode;
        }
    }

    private Node minNode(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // 测试代码
    public static void main(String[] args) {
        AVL avl = new AVL();
        avl.addElement(7);
        avl.addElement(3);
        avl.addElement(10);
        avl.addElement(5);
        avl.addElement(1);
        avl.addElement(9);
        avl.addElement(12);
        avl.addElement(28);
        avl.addElement(38);
        avl.addElement(48);
        System.out.println(avl.isBST()); // true
        System.out.println(avl.isBalanced()); // false
    }
}
```