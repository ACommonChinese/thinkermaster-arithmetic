package csdn.cn.dsa.algorithm;

public class Hanoitower {

    public static void main(String[] args) {
        hanoitower(64,'A','B','C');
    }


    /**
     *
     * @param num
     * @param a
     * @param b
     * @param c
     */
    private static void hanoitower(int num,char a,char b,char c){

        if(num==1){
            System.out.println("第1个盘从 "+a+"->"+c);
        }else {
            //把上面的num-1个盘子A-B
            hanoitower(num-1,a,c,b);
            System.out.println("第"+num+"个盘子从 "+a+"->"+c);
            //将B上所有的盘子挪到C
            hanoitower(num-1,b,a,c);

        }

    }
}
