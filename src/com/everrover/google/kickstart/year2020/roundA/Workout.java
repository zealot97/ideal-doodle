package com.everrover.google.kickstart.year2020.roundA;

import java.util.Scanner;

public class Workout {
    private static int solution(int []arr, int K){
        int []diff = new int[arr.length-1];
        int max=0;
        for(int i=0; i<diff.length; i++){
            diff[i] = arr[i+1]-arr[i];
            max = Integer.max(max, diff[i]);
        }
//        for(int i=1; i<=max; i++){ // linear search
//            int kSum = 0;
//            for(int num: diff){
//                kSum += Math.ceil((double)num/i)-1;
//            }
//            if(kSum<=K) return i;
//        }
        int l=0, r=max, mid, ans=1;
        while(l<=r){
            mid = (l+r)/2;
            int kSum = 0;
            for(int num: diff){
                kSum += Math.ceil((double)num/mid)-1;
            }
            if(kSum>K){
                l = mid+1;
            }else if(kSum<=K){
                r = mid-1;
                ans = mid;
            }
        }
        return ans;
    }
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        int k = t;
        while(t-->0){
            final int n = scanner.nextInt();
            final int num = scanner.nextInt();
            final int arr[] = new int[n];

            for(int i=0; i<n; i++){
                arr[i] = scanner.nextInt();
            }
//            solution(matrix, num, 0, 0);
            System.out.println("Case #"+(k-t)+": "+solution(arr, num));
//            System.out.println("Case #"+(k-t)+": "+ans);
        }
    }
}
