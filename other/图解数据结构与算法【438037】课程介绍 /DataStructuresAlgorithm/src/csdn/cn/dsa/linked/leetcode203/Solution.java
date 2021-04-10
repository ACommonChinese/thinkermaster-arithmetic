package csdn.cn.dsa.linked.leetcode203;


public class Solution {

    public ListNode removeElements(ListNode head, int val) {
        if(head==null){
            return head;
        }
        ListNode resNode = removeElements(head.next, val);
        if(head.val==val){
            return  resNode;
        }else {
            head.next=resNode;
            return head;
        }
    }
//    public ListNode removeElements(ListNode head, int val) {
//        //节点是不是就是头节点
//        while (head!=null&&head.val==val){
//            ListNode delNode = head;
//            head = head.next;
//            delNode.next=null;
//        }
//        if(head == null){
//            return head;
//        }
//        //待删除节点是中间的节点
//        ListNode prev = head;
//        while (prev.next!=null){
//            if(prev.next.val==val){
//              ListNode delNode = prev.next;
//              prev.next = delNode.next;
//              delNode.next = null;
//            }else {
//                prev = prev.next;
//            }
//        }
//        return head;
//
//    }


    public static void main(String[] args) {
        int[] nums = {1,2,6,3,4,5,6};
        int val = 6;
        ListNode head = new ListNode(nums);
        ListNode res = (new Solution()).removeElements(head, val);
        System.out.println(res);
    }
}
