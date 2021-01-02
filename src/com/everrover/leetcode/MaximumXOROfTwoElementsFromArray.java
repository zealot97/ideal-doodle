package com.everrover.leetcode;

import java.util.Arrays;

public class MaximumXOROfTwoElementsFromArray {
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
            this.root = new TrieNode();
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

        public int findMaxXor(int number){
            TrieNode crawler = this.root;
            for(int b=31; b>=0; b--){
                int bit = (number>>b)&1;
                int revBit = bit ^ 1;
                if(crawler.children[revBit] != null){
                    crawler = crawler.children[revBit];
                }else{
                    crawler = crawler.children[bit];
                }
            }
            return crawler.number^number;
        }
    }
    public int findMaximumXOR(int[] nums) {
        Trie trie = new Trie();
        int ans = Integer.MIN_VALUE;
        for(int num: nums){
            trie.insertNumber(num);
        }
        int maxXor = Integer.MIN_VALUE;
        for(int num: nums){
            ans = Integer.max(ans, trie.findMaxXor(num));
        }
        return ans;
    }
}
