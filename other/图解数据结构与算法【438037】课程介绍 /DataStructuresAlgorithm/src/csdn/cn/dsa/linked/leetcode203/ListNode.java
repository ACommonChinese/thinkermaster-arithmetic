package csdn.cn.dsa.linked.leetcode203;

import javax.swing.*;
import java.util.List;

//Definition for singly-linked list.
public class ListNode {

    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    public ListNode(int[] arr) {
        if(arr == null || arr.length == 0) {
            throw new IllegalArgumentException("arr can not be empty");
        }
        this.val = arr[0];
        ListNode cur = this;
        for(int i=1;i<arr.length;i++){
          cur.next =  new ListNode(arr[i]);
          cur = cur.next;

        }



    }


















    // 以当前节点为头结点的链表信息字符串
    @Override
    public String toString(){

        StringBuilder s = new StringBuilder();
        ListNode cur = this;
        while(cur != null){
            s.append(cur.val + "->");
            cur = cur.next;
        }
        s.append("NULL");
        return s.toString();
    }
}