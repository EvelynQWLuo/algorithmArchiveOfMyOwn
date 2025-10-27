package Trie;

/**
 * @author Evelyn
 * @version 1.0
 */
/*
每一个String都有一个children数组，来存放它的路径，不是公用的children数组
 */
public class L208_ImplementTries {
    class Trie {
        class TrieNode {
            int pass;
            int end;
            TrieNode[] children;

            public TrieNode() {
                pass = 0;
                end = 0;
                children = new TrieNode[26];
            }
        }

        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode curr = root;
            curr.pass++;
            int index = 0;
            for (int i = 0; i < word.length(); i++) {
                index = word.charAt(i) - 'a';
                if (curr.children[index] == null) {
                    curr.children[index] = new TrieNode();
                }
                curr = curr.children[index];
                curr.pass++;
            }
            curr.end++;

        }

        public boolean search(String word) {
            TrieNode curr = root;
            int index = 0;
            for (int i = 0; i < word.length(); i++) {
                index = word.charAt(i) - 'a';
                if (curr.children[index] == null) {
                    return false;
                }
                curr = curr.children[index];
            }
            return curr.end > 0;
        }

        public boolean startsWith(String prefix) {
            TrieNode curr = root;
            int index = 0;
            for (int i = 0; i < prefix.length(); i++) {
                index = prefix.charAt(i) - 'a';
                if (curr.children[index] == null) {
                    return false;
                }
                curr = curr.children[index];
            }
            return curr.pass > 0;
        }
    }

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
}
