package com.everrover.google.kickstart.year2020.roundC;

import java.util.Scanner;

public class Candies {

    private static class QueryObject{
        public int prefixSum[], multiplierSum[], arr[];

        public QueryObject(int []arr){
            this.arr = arr;
            this.prefixSum = new int[arr.length+1];
            this.multiplierSum = new int[arr.length+1];

            for(int i=1; i<prefixSum.length; i++){
                prefixSum[i] = prefixSum[i-1]+i*arr[i-1]*(int)Math.pow(-1, i-1);
                multiplierSum[i] = multiplierSum[i-1]+arr[i-1]*(int)Math.pow(-1, i-1);
            }
        }

        public boolean update(int pos, int val){
            arr[pos] = val;
            for(int i=pos+1; i<prefixSum.length; i++){
                prefixSum[i] = prefixSum[i-1]+i*arr[i-1]*(int)Math.pow(-1, i-1);
                multiplierSum[i] = multiplierSum[i-1]+arr[i-1]*(int)Math.pow(-1, i-1);
            }
            return true;
        }

        public int query(int left, int right){
            int multiplier = (int)Math.pow(-1, left);
            int qS = (prefixSum[right+1]-prefixSum[left]);
            int qB = left*(multiplierSum[right+1]-multiplierSum[left]);
            return (qS-qB)*multiplier;
        }
    }
    private static int solution(int arr[], int queries[][]){
        QueryObject qo = new QueryObject(arr);
        int ans = 0;
        for(int[] query: queries){
            if(query[0] == 0){ // it's an update
                qo.update(query[1]-1, query[2]);
            }else { // it's a query
                ans += qo.query(query[1]-1, query[2]-1);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        int k = t;
        while(t-->0){
            final int N = scanner.nextInt();
            final int K = scanner.nextInt();
            int arr[] = new int[N], queries[][] = new int[K][3];

            for(int i=0; i<N; i++){
                arr[i] = scanner.nextInt();
            }
            for(int i=0; i<K; i++){
                queries[i][0] = scanner.next().charAt(0) == 'U'? 0: 1;
                queries[i][1] = scanner.nextInt();
                queries[i][2] = scanner.nextInt();
            }

            System.out.println("Case #"+(k-t)+": "+solution(arr, queries));
        }
    }
}
