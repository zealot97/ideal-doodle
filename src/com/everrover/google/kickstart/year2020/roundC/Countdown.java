package com.everrover.google.kickstart.year2020.roundC;

import java.util.Arrays;
import java.util.Scanner;

public class Countdown {
    private static int solution(int k, int arr[]){
        int ans = 0;
        int i=0;
        while(i<arr.length){
            if(arr[i]==k){
                int seq = k-1;
                i++;
                while(i<arr.length && (arr[i]+1) == arr[i-1] && seq > 0){
                    seq--;i++;
                }
                if(seq == 0) ans++;
            }else {
                i++;
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        int k = t;
        while(t-->0){
            int n = scanner.nextInt();
            int K = scanner.nextInt();
            int []arr = new int[n];
            for(int i=0; i<n; i++){
                arr[i] = scanner.nextInt();
            }
            System.out.println("Case #"+(k-t)+": "+solution(K, arr));
        }
    }
}
