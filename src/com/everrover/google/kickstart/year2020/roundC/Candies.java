package com.everrover.google.kickstart.year2020.roundC;

import java.util.Scanner;

public class Candies {

    private static class QueryObject{
//        public int prefixSum[], multiplierSum[], arr[];
//
//        public QueryObject(int []arr){
//            this.arr = arr;
//            this.prefixSum = new int[arr.length+1];
//            this.multiplierSum = new int[arr.length+1];
//
//            for(int i=1; i<prefixSum.length; i++){
//                prefixSum[i] = prefixSum[i-1]+i*arr[i-1]*(int)Math.pow(-1, i-1);
//                multiplierSum[i] = multiplierSum[i-1]+arr[i-1]*(int)Math.pow(-1, i-1);
//            }
//        }
//
//        public boolean update(int pos, int val){
//            arr[pos] = val;
//            for(int i=pos+1; i<prefixSum.length; i++){
//                prefixSum[i] = prefixSum[i-1]+i*arr[i-1]*(int)Math.pow(-1, i-1);
//                multiplierSum[i] = multiplierSum[i-1]+arr[i-1]*(int)Math.pow(-1, i-1);
//            }
//            return true;
//        }
//
//        public int query(int left, int right){
//            int multiplier = (int)Math.pow(-1, left);
//            int qS = (prefixSum[right+1]-prefixSum[left]);
//            int qB = left*(multiplierSum[right+1]-multiplierSum[left]);
//            return (qS-qB)*multiplier;
//        }

        public int prefixSum[], multiplierSum[], arr[];

        public QueryObject(int []arr){
            this.arr = arr;
            final int m = 2*(int)Math.pow(2, (int)Math.ceil(Math.log(arr.length)/Math.log(2)));
            this.prefixSum = new int[m];
            this.multiplierSum = new int[m];

            constructSTUtil(0, arr.length-1, 0);
        }

        public void constructSTUtil(int s, int e, int index){
            if(s == e){
                int mul = (int)Math.pow(-1, s);
                prefixSum[index] = mul*(s+1)*arr[s];
                multiplierSum[index] = mul*arr[s];
                return;
            }

            int mid = (s+e)/2, l = index*2+1, r = index*2+2;

            constructSTUtil(s, mid, l);
            constructSTUtil(mid+1, e, r);

            prefixSum[index] = prefixSum[l] + prefixSum[r];
            multiplierSum[index] = multiplierSum[l] + multiplierSum[r];

            return;
        }

        public void updateUtil(int pos, int index, int s, int e){
            if(s == e){
                int mul = (int)Math.pow(-1, s);
                prefixSum[index] = mul*(s+1)*arr[s];
                multiplierSum[index] = mul*arr[s];
                return;
            }
            if(s > pos || e < pos){
                return;
            }
            int mid = (s+e)/2, l=2*index+1, r=2*index+2;

            updateUtil(pos, l, s, mid);
            updateUtil(pos, r, mid+1, e);

            prefixSum[index] = prefixSum[l] + prefixSum[r];
            multiplierSum[index] = multiplierSum[l] + multiplierSum[r];

        }

        public boolean update(int pos, int val){
            arr[pos] = val;
            updateUtil(pos, 0, 0, arr.length-1);
            return true;
        }

        public void queryUtil(int ans[], int s, int e, int qs, int qe, int index){
            if(qs<=s && qe>=e){ // complete overlap
                ans[0] = prefixSum[index];
                ans[1] = multiplierSum[index];
                return;
            }
            if(qs > e || qe < s){
                ans[0] = ans[1] = 0;
                return;
            }
            int lArr[] = new int[2], rArr[] = new int[2];
            int l = 2*index+1, r = 2*index+2, mid=(s+e)/2;
            queryUtil(lArr, s, mid, qs, qe, l);
            queryUtil(rArr, mid+1, e, qs, qe, r);
            ans[0] = lArr[0]+rArr[0];
            ans[1] = lArr[1]+rArr[1];
            return;
        }

        public int query(int left, int right){
            int multiplier = (int)Math.pow(-1, left);
            int ans[] = new int[2];
            queryUtil(ans, 0, arr.length-1, left, right, 0);
            return (ans[0]-ans[1]*left)*multiplier;
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
