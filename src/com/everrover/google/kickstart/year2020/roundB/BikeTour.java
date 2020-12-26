package com.everrover.google.kickstart.year2020.roundB;

import java.util.Scanner;

public class BikeTour {

    private static int solution(int arr[]){
        if(arr.length<=2) return 0;
        int peaks = 0;
        for(int i=1; i<(arr.length-1); i++){
            if(arr[i]>arr[i-1] && arr[i]>arr[i+1]) peaks++;
        }
        return peaks;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        int k = t;
        while(t-->0){
            final int n = scanner.nextInt();
            int []arr = new int[n];
            for(int i=0; i<arr.length; i++){
                arr[i] = scanner.nextInt();
            }
//            solution(matrix, num, 0, 0);
            System.out.println("Case #"+(k-t)+": "+solution(arr));
//            System.out.println("Case #"+(k-t)+": "+ans);
        }
    }
}
