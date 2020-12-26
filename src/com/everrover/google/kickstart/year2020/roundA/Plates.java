package com.everrover.google.kickstart.year2020.roundA;

import java.util.Arrays;
import java.util.Scanner;

public class Plates {
    private static int ans = Integer.MIN_VALUE, m=0, n=0;
    private static int solution(int matrix[][], int num, int row, int sum){
        if(row == matrix.length || num == 0) {
            return sum;
        }
        ans = Integer.max(solution(matrix, num, row+1, sum), ans);
        for(int i=0; i<matrix[row].length && num-i-1>=0; i++){
            ans = Integer.max(solution(matrix, num-i-1, row+1, sum+matrix[row][i]), ans);
        }
        return 0;
    }

    private static int solution(int matrix[][], int num){
        if(matrix.length == 0) return 0;
        int dp[][] = new int[m+1][num+1];
        int sum[][] = new int[m+1][n+1];
        for(int i=1; i<=m; i++){
            for(int j=1; j<=n; j++){
                sum[i][j] = sum[i][j-1]+matrix[i-1][j-1];
            }
        }
        for(int i=1; i<=m; i++){
            for(int j=0; j<=num; j++){
                for(int k=0; k<=j&&k<=n; k++){
                    dp[i][j] = Integer.max(dp[i][j], sum[i][k]+dp[i-1][j-k]);
                }
            }
        }
        return dp[m][num];
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        int k = t;
        while(t-->0){
            ans = Integer.MIN_VALUE;
            m = scanner.nextInt();
            n = scanner.nextInt();
            int num = scanner.nextInt();
            int [][]matrix = new int[m][n];
            for(int i=0; i<m; i++){
                matrix[i][0] = scanner.nextInt();
                for(int j=1; j<n; j++){
                    matrix[i][j] = scanner.nextInt();
                }
            }
//            solution(matrix, num, 0, 0);
            System.out.println("Case #"+(k-t)+": "+solution(matrix, num));
//            System.out.println("Case #"+(k-t)+": "+ans);
        }
    }
}
