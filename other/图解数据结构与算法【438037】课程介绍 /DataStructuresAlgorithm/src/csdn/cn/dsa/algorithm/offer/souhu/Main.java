package csdn.cn.dsa.algorithm.offer.souhu;

import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        System.out.println(simplefyPath(str));
    }


    private static String simplefyPath(String path) {
        Stack<String> stack = new Stack<>();
        String[] paths = path.split("/");

        for(String s:paths){
            if(s.equals("..")){
                if(!stack.isEmpty()) stack.pop();
            }else if(!s.equals(".")&&!s.equals("")){
                stack.push(s);
            }
        }
        String res = "";
        while (!stack.isEmpty()){
            res = "/" + stack.pop() + res;  //由于栈是先进后出，所以每次res都得追加在后面
        }
        if(res.length()==0){
          return "/"  ;
        }
        return res;
    }
}
