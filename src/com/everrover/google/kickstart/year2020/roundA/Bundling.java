package com.everrover.google.kickstart.year2020.roundA;

import java.util.Scanner;

public class Bundling {
    private static class Trie{
        public static class Node{
            public int wordC;
            public boolean isWord;
            public Node map[];

            public Node() {
                this.wordC = 0;
                this.map = new Node[26];
                this.isWord = false;
            }

        }

        public Node root;

        /** Initialize your data structure here. */
        public Trie() {
            this.root = new Node();
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            Node crawler = this.root;
            for(char ch: word.toCharArray()){
                if(crawler.map[ch-'A'] == null){
                    crawler.map[ch-'A'] = new Node();
                }
                crawler = crawler.map[ch-'A'];
            }
            crawler.wordC++;
            crawler.isWord = true;
        }

//        /** Returns if the word is in the trie. */
//        public boolean search(String word) {
//            Node crawler = this.root;
//            for(char ch: word.toCharArray()){
//                if(crawler.map[ch-'A'] == null){
//                    return false;
//                }
//                crawler = crawler.map[ch-'A'];
//            }
//            return crawler.isWord == true;
//        }
//
//        /** Returns if there is any word in the trie that starts with the given prefix. */
//        public boolean startsWith(String prefix) {
//            Node crawler = this.root;
//            for(char ch: prefix.toCharArray()){
//                if(crawler.map[ch-'A'] == null){
//                    return false;
//                }
//                crawler = crawler.map[ch-'A'];
//            }
//            return true;
//        }
    }

    private static int ans = 0, K=1;

    private static int trieDFS(Trie.Node root, int score){
        if(root == null) return 0;
        int prefixedStrs = root.isWord? root.wordC: 0;
        for(int i=0; i<root.map.length; i++){
            Trie.Node node = root.map[i];
            if(node == null) continue;
            char ch = (char)('A'+i);
            prefixedStrs += trieDFS(node, score+1);
            
        }
        ans += (prefixedStrs/K)*score;
        return prefixedStrs%K;
    }

    private static int solution(Trie trie){
        ans = 0;
        trieDFS(trie.root, 0);
        return ans;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        int k = t;
        while(t-->0){
            final int N = scanner.nextInt();
            K = scanner.nextInt();
            final String strs[] = new String[N];
//            final boolean mark[] = new boolean[N];
            Trie trie = new Trie();

            for(int i=0; i<N; i++){
                strs[i] = scanner.next();
//                mark[i] = false;
//                trie.insert(strs[i]);
                trie.insert(strs[i]);
            }
            System.out.println("Case #"+(k-t)+": "+solution(trie));
        }
    }
}
