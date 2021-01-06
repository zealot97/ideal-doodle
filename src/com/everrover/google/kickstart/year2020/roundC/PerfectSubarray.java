package com.everrover.google.kickstart.year2020.roundC;

import java.util.Scanner;

public class PerfectSubarray {
    private static long solution(int []arr, int left, int right){
        long[] map = new long[right+left+1];
        map[left]++;
        int prefixSum = 0;
        long res[] = new long[arr.length];
        for(int j=0; j<arr.length; j++){
            prefixSum += arr[j];
            int index = prefixSum+left;
            for(int i=0; index-i*i>=0 && i*i<=right; i++){
                res[j] += map[index-i*i];
            }
            map[index]++;
        }
        long ans = 0;
        for(long r: res){
            ans += r;
        }
        return ans;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        int k = t;
        while(t-->0){
            final int n = scanner.nextInt();
            final int arr[] = new int[n];
            int left = 0, right = 0;

            int max_A = Integer.MIN_VALUE;
            for(int i=0; i<n; i++){
                arr[i] = scanner.nextInt();
                if(arr[i]>0) right += arr[i];
                else left -= arr[i];
            }
            System.out.println("Case #"+(k-t)+": "+solution(arr, left, right));
        }
    }
}
