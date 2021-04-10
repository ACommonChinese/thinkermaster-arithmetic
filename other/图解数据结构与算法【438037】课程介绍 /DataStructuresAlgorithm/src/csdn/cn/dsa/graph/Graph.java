package csdn.cn.dsa.graph;

import java.util.ArrayList;
import java.util.Arrays;

public class Graph {

    //顶点的集合
    private ArrayList<String> vertexList;
    //邻接矩阵
    private int[][] edges;
    //边的数目
    private int numOfEdges;

    public Graph(int n){
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numOfEdges=0;
    }
    public Graph(){}

    //插入节点
    public void insertVertex(String vertex){
        vertexList.add(vertex);
    }
    //添加边
    public  void  insertEdge(int v1,int v2,int weight){
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

    public void showGraph() {
        for(int[] line:edges){
            System.out.println(Arrays.toString(line));
        }
    }

    //常用方法
    //返回结点的个数
    public int getNumOfVertex() {
        return vertexList.size();
    }

    //得到边的数目
    public int getNumOfEdges() {
        return numOfEdges;
    }

    //返回v1和v2的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    //返回结点i(下标)对应的数据 0->"A" 1->"B" 2->"C"
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }


    public static void main(String[] args) {
        int n=5;
        //创建图对象
        Graph graph = new Graph(5);

        //添加5个节点
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");
        graph.insertVertex("E");

        //添加边 //A-B A-C B-C B-D B-E
        graph.insertEdge(0,1,1); // AB
        graph.insertEdge(0,2,1); // AC
        graph.insertEdge(1,2,1); // BC
        graph.insertEdge(1,3,1); // BD
        graph.insertEdge(1,4,1); // BE
        graph.showGraph();
    }



}
