# 二叉搜索树_删除节点

二叉搜索树删除节点的操作可分为3种情况讨论, 以下面的二叉树(7, 3, 10, 5, 1, 9, 12)为例

1. 删除叶子点, 比如删除节点5, 直接删除即可, 让3.rightChild = null
2. 删除只有一棵子树的节点, 比如删掉节点5之后的节点3, 此时3只有左子节点1, 要删除节点3, 需要删除3的同时让3的左子节点1上移给7, 即: 7.leftChild = 1, 
3. 删除有两棵子树的节点, 比如删除7, 根据二叉树左 < 父 < 右的特点, 需要:
- 寻找到右子树的最小叶子节点, 并替换即可
- 比如删除7, 7比它的右子树所有的节点都要小, 因此需要找到7的右子树的最小节点9, 并让9替换7即可, 9替换7之后, 同样满足9的右子树所有的节点都大于9
- 对于删除7, 也可以找它的左子树的最大节点, 并替换7, 但这种方式稍微复杂一些, 此处暂不使用这种方式

为了说明逻辑流程, 下面先使用非常笨拙的`deleteNode`方法写出二叉搜索树删除节点的过程:

### 仅说明如何实现功能的代码
```java
package com.daliu.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 自定义二叉搜索树
 * 二叉搜索树也叫做二叉排序树, 任何一个非叶子节点, 要求左子节点的值比当前节点的值小, 右子节点的值比当前节点的值大
 * 由于二叉搜索树的结点有对比操作, 即结点间是可比较的, 因此实现Comparable接口
 * (7,3,10,5,1,9,12)
 */
public class BST <E extends Comparable<E>> {
    // 二分搜索树的节点
    private class Node {
        E e;
        Node left;
        Node right;

        public Node(E e) {
            this.e = e;
            this.left = null;
            this.right = null;
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
        if (root == null) return;

        Node targetNode = search(e);
        if (targetNode == null) return;

        Node parent = searchParent(e);
        if (parent == null) { // 如果删除的是根节点, 则直接root=null
            root = null;
            size = 0;
            return;
        }
        // 情况1: 删除叶子节点
        if (targetNode.left == null && targetNode.right == null) {
            if (parent.left != null && e.compareTo(parent.left.e) == 0) {
                parent.left = null;
                size--;
            }
            if (parent.right != null && e.compareTo(parent.right.e) == 0) {
                parent.right = null;
                size--;
            }
        } else if (targetNode.left != null && targetNode.right != null) { // 情况2: 删除有两棵子树的节点
            // 找出目标节点右子树的最小节点并替换要删除的节点, 这个右子树上的最小节点一定是叶子节点
            Node minNode = minNode(targetNode.right);
            deleteNode(minNode.e);
            targetNode.e = minNode.e;
            size--;
        } else {
            // 情况3: 删除只有一棵子树的节点
            // 因此一直删除会报空指针异常
            if (targetNode.left != null) { //只有左子树
                if (e.compareTo(parent.left.e) == 0) {
                    parent.left = targetNode.left;
                    size--;
                } else {
                    parent.right = targetNode.left;
                    size--;
                }
            } else { // 只有右子树
                if (e.compareTo(parent.left.e) == 0) {
                    parent.left = targetNode.right;
                    size--;
                } else {
                    parent.right = targetNode.right;
                    size--;
                }
            }
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
        BST bst = new BST<>();
        bst.addElement(7);
        bst.addElement(3);
        bst.addElement(10);
        bst.addElement(5);
        bst.addElement(1);
        bst.addElement(9);
        bst.addElement(12);

        bst.levelOrder();

        // 删除叶子节点5
        System.out.println("--------delete 5---------");
        bst.deleteNode(5); // 7 3 10 1 9 12
        bst.levelOrder();
        System.out.println("--------delete 3---------");
        bst.deleteNode(3);
        bst.levelOrder(); // 7 1 10 9 12
        System.out.println("--------delete 10---------");
        bst.deleteNode(10);
        bst.levelOrder(); // 7 1 12 9
        System.out.println("--------delete 1---------");
        bst.deleteNode(1);
        bst.levelOrder(); // 7 12 9
        System.out.println("--------delete 9---------");
        bst.deleteNode(9);
        bst.levelOrder(); // 7 12
        System.out.println("--------delete 7---------");
        bst.deleteNode(12);
        bst.levelOrder(); // 7
    }
}
```

### 二叉搜索树删除节点的递归做法

![](./images/9.png)

这个思路的是:
1. 退出递归条件
- 利用每个叶子节点的左右子节点都是null, 递归到这个null, 即删无可删时递归结束
2. 自己调用自己
- 比如删除节点5时, 5在根节点7的左边, 于是去7的左子树上删除
- 去7的左子树(以3为根)上删除时再次递归, 发现5在以3为根的左子树的右边, 于是往3的右子树上找
- ... 递归

完整代码示例, 删除方法: removeNode()

```java
package com.daliu.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 自定义二叉搜索树
 * 二叉搜索树也叫做二叉排序树, 任何一个非叶子节点, 要求左子节点的值比当前节点的值小, 右子节点的值比当前节点的值大
 * 由于二叉搜索树的结点有对比操作, 即结点间是可比较的, 因此实现Comparable接口
 * (7,3,10,5,1,9,12)
 */
public class BST <E extends Comparable<E>> {
    // 二分搜索树的节点
    private class Node {
        E e;
        Node left;
        Node right;

        public Node(E e) {
            this.e = e;
            this.left = null;
            this.right = null;
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
        BST bst = new BST<>();
        bst.addElement(7);
        bst.addElement(3);
        bst.addElement(10);
        bst.addElement(5);
        bst.addElement(1);
        bst.addElement(9);
        bst.addElement(12);

        bst.levelOrder();

        // 删除叶子节点5
        System.out.println("--------delete 5---------");
        bst.deleteNode(5); // 7 3 10 1 9 12
        bst.levelOrder();
        System.out.println("--------delete 3---------");
        bst.deleteNode(3);
        bst.levelOrder(); // 7 1 10 9 12
        System.out.println("--------delete 10---------");
        bst.deleteNode(10);
        bst.levelOrder(); // 7 1 12 9
        System.out.println("--------delete 1---------");
        bst.deleteNode(1);
        bst.levelOrder(); // 7 12 9
        System.out.println("--------delete 9---------");
        bst.deleteNode(9);
        bst.levelOrder(); // 7 12
        System.out.println("--------delete 7---------");
        bst.deleteNode(12);
        bst.levelOrder(); // 7
    }
}
```