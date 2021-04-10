package csdn.cn.dsa.algorithm;

public class KnapsackProblem01 {
    public static void main(String[] args) {
        int[] w = {1,2,3}; //物品重量
        int[] val={6,10,12}; //物品价值
        int m=5;//背包容量
        int n = val.length;//物品数量

        //创建二维数组，
        //v[i][j] 表示在前i个物品中能够装入容量为j的背包中的最大价值
        int[][] v = new int[n+1][m+1];
       //将二维数组的第一行和第一列赋值为0
        for(int i = 0; i < v.length; i++) {
            v[i][0] = 0; //将第一列设置为0
        }
        for(int i=0; i < v[0].length; i++) {
            v[0][i] = 0; //将第一行设置0
        }

        //根据公式动态处理背包问题
        for(int i=1;i<v.length;i++){
            for(int j=1;j<v[0].length;j++){
                if(w[i-1]>j){
                    v[i][j] = v[i-1][j];
                }else{
                    v[i][j] = Math.max(v[i - 1][j], val[i - 1] + v[i - 1][j - w[i - 1]]);
                }
            }
        }

        //输出二维数组
        for(int i=0;i<v.length;i++){
            for(int j=0;j<v[i].length;j++){
                System.out.print(v[i][j]+" ");
            }
            System.out.println();
        }




    }
}
