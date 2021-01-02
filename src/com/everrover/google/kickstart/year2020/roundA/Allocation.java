package com.everrover.google.kickstart.year2020.roundA;

import java.util.Arrays;
import java.util.Scanner;

public class Allocation {
    private static int solution(int budget, int arr[]){
        Arrays.sort(arr);
        int i = 0;
        while(i<arr.length){
            if(arr[i]>budget){
                return i;
            }
            budget-=arr[i++];
        }
        return i;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        int k = t;
        while(t-->0){
            int n = scanner.nextInt();
            int budget = scanner.nextInt();
            int []arr = new int[n];
            for(int i=0; i<n; i++){
                arr[i] = scanner.nextInt();
            }
            System.out.println("Case #"+(k-t)+": "+solution(budget, arr));
        }
    }
}
