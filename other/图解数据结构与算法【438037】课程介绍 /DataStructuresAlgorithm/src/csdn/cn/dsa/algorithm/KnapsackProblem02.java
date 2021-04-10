package csdn.cn.dsa.algorithm;

public class KnapsackProblem02 {
    public static void main(String[] args) {
        int[] w = {1,2,3}; //物品重量
        int[] val={6,10,12}; //物品价值
        int m=5;//背包容量
        int n = val.length;//物品数量

        //创建二维数组，
        //v[i][j] 表示在前i个物品中能够装入容量为j的背包中的最大价值
        int[][] v = new int[n+1][m+1];

        //为了记录放入商品的情况，我们定一个二维数组
        int[][] path = new int[n+1][m+1];


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
//                    v[i][j] = Math.max(v[i - 1][j], val[i - 1] + v[i - 1][j - w[i - 1]]);
                    if(v[i - 1][j]< val[i - 1] + v[i - 1][j - w[i - 1]]){
                        v[i][j] =val[i - 1] + v[i - 1][j - w[i - 1]];
                        path[i][j] = 1;// 新放入了商品
                    }else {
                        v[i][j] =v[i - 1][j];
                    }
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

        //从path中获取输出放入了哪些商品
        int i= path.length-1; //行的下标
        int j = path[0].length-1; //列的下标
        while (i>0&&j>0){
            if(path[i][j]==1){
                System.out.println("第"+i+"个商品放入到背包");
                j -= w[i-1];//w[i-1] 表示的是放入到背包中的商品的重量，更新容量
            }
            i--;
        }





    }
}
