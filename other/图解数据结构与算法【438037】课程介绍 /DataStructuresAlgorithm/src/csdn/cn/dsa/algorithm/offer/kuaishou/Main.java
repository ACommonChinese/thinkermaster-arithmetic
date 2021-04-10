package csdn.cn.dsa.algorithm.offer.kuaishou;

import sun.rmi.runtime.Log;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        //获取输入参数
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        String[] line = bf.readLine().split(" ");
        int[] nums = new int[n];
        for(int i=0;i<n;i++){
            nums[i] = Integer.parseInt(line[i]);
        }

        int left = 0;
        int right = n-1;
        long leftSum = nums[left];
        long rightSum = nums[right];
        long maxSum = 0;

        while (left<right){
            if(leftSum==rightSum){
                maxSum = leftSum;
                leftSum += nums[++left];
                rightSum += nums[--right];
            }else if(leftSum>rightSum){
                right += nums[--right];
            }else {
                leftSum += nums[++left];
            }
        }
        System.out.println(maxSum);

    }
}
