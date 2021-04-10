package csdn.cn.dsa.array;

public class SparseArray {

    public static void main(String[] args) {
        // chessArr1 表示一个棋盘
        // 0表示没有落子, 1表示白子， 2表示黑子
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2]=1;
        chessArr1[2][3]=2;
        for(int[] row:chessArr1){
            for(int item:row){
                System.out.printf("%d\t",item);
            }
            System.out.println();
        }

        //计算有多少个非空的值
        int sum = 0;
        for(int i=0;i<chessArr1.length;i++){
            for(int j=0;j<chessArr1[0].length;j++){
                if(chessArr1[i][j]!=0){
                    sum++;
                }
            }
        }
        //创建稀疏数组
        int[][] sparseArr = new int[sum+1][3];
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        //补充数据到稀疏数组中
        int rowNum = 0;
        for(int i=0;i<chessArr1.length;i++){
            for(int j=0;j<chessArr1[0].length;j++){
                if(chessArr1[i][j]!=0){
                    rowNum++;
                    sparseArr[rowNum][0] = i;
                    sparseArr[rowNum][1] = j;
                    sparseArr[rowNum][2] = chessArr1[i][j];
                }
            }
        }
        //输出稀疏数组
        System.out.println("--------输出稀疏数组-----------");
        for(int i=0;i<sparseArr.length;i++){
            System.out.printf("%d\t%d\t%d\n", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
        }

        //将稀疏数组恢复为二维数组
        int[][] chessArr2 = new int[sparseArr[0][0]][sparseArr[0][1]];
        for(int i=1;i<sparseArr.length;i++){
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        System.out.println("--------恢复后的二维数组--------");
        for(int[] row:chessArr2){
            for(int item:row){
                System.out.printf("%d\t",item);
            }
            System.out.println();
        }

    }
}
