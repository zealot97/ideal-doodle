package com.everrover.google.kickstart.year2020.roundB;


import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class WanseringRobo {

    private static double solution(int w, int h, int l, int u, int r, int d){
        /***
         * Throws MLE on grid size >= 10^9... double array needs >= 8GB space in m/m
         */
        double [][]prob = new double[w][h];
        boolean [][]grid = new boolean[w][h];
        // block paths
        for(int i=l; i<=r; i++){
            for(int j=u; j<=d; j++){
                grid[i][j] = true;
            }
        }
        // Setup init known probability
        for(int i=0; i<w; i++){
            prob[i][h-1] = 1.0;
        }
        Arrays.fill(prob[w-1], 1.0);
        if(r == w-1){ // covers r == w-1 auto
            for(int i=d; i>=0; i--){
                prob[w-1][i] = 0;
            }
        }
        if(d == h-1){
            for(int i=r; i>=0; i--){
                prob[i][h-1] = 0;
            }
        }

        // dp sol
        for(int i=w-2; i>=0; i--){
            for(int j=h-2; j>=0; j--){
                if(!grid[i][j]) prob[i][j] = 0.5*prob[i+1][j]+0.5*prob[i][j+1];
                else prob[i][j] = 0;
            }
        }
        return prob[0][0];
    }

    private static double solution(int n, int l, int u, int r, int d){
        return 0;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        int k = t;
        while(t-->0){
            int w = scanner.nextInt();
            int h = scanner.nextInt();
            int l = scanner.nextInt();
            int u = scanner.nextInt();
            int r = scanner.nextInt();
            int d = scanner.nextInt();
            System.out.println("Case #"+(k-t)+": "+solution(w,h,l-1,u-1,r-1,d-1));
//            System.out.println("Case #"+(k-t)+": "+ans);
        }
    }
}
