package com.everrover.leetcode;

import java.util.Arrays;

public class MaximumXORWithElementFromArray {
    public static class Trie{
        public static class TrieNode{
            TrieNode []children;
            int number;

            public TrieNode() {
                this.children = new TrieNode[2];
                this.number = -1;
            }
            public TrieNode(int number ) {
                this.children = new TrieNode[2];
                this.number = number;
            }
        }
        public TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insertNumber(int number){
            TrieNode crawler = this.root;
            for(int b=31; b>=0; b--){
                int bit = (number>>b)&1;
                if(crawler.children[bit] == null){
                    crawler.children[bit] = new TrieNode();
                }
                crawler = crawler.children[bit];
            }
            crawler.number = number;
            return;
        }

        public int findXor(int number){
            TrieNode crawler = this.root;
            for(int b=31; b>=0; b--){
                int bit = ((number>>b)&1) == 1? 0: 1;
                int revBit = bit == 0? 1: 0;
                if(crawler.children[bit] != null){
                    crawler = crawler.children[bit];
                }else{
                    crawler = crawler.children[revBit];
                }
            }
            return crawler.number ^ number;
        }
    }
    public int[] maximizeXor(int[] nums, int[][] queries) {
        int[] ans = new int[queries.length];
        int [][]queriesAlpha = new int[queries.length][3];
        for(int i=0; i<queries.length; i++){
            queriesAlpha[i][0] = queries[i][0];
            queriesAlpha[i][1] = queries[i][1];
            queriesAlpha[i][2] = i;
        }
        Arrays.sort(queriesAlpha, (a, b)->(a[1]-b[1]));
        Arrays.sort(nums);
        Trie trie = new Trie();
        int i=0;
        for(int []query: queriesAlpha){
            while (i<nums.length && nums[i] <= query[1]){
                trie.insertNumber(nums[i++]);
            }
            if(i != 0){
                ans[query[2]] = trie.findXor(query[0]);
            }else{
                ans[query[2]] = -1;
            }
        }

        return ans;
    }
}
