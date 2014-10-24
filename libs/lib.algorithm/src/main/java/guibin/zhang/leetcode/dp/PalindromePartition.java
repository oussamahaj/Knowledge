package guibin.zhang.leetcode.dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * 
 * Return all possible palindrome partitioning of s.
 * 
 * For example, given s = "aab",
 * Return
 * 
 *   [
 *     ["aa","b"],
 *     ["a","a","b"]
 *   ]
 * 
 * http://mattcb.blogspot.com/2013/03/palindrome-partitioning.html
 * 
 * http://besttony.wordpress.com/2013/04/21/leetcode-palindrome-partitioning/
 * http://n00tc0d3r.blogspot.com/2013/05/palindrome-partitioning.html
 * 
 * http://blog.sina.com.cn/s/blog_b9285de20101jbtl.html
 * http://discuss.leetcode.com/questions/1265/palindrome-partitioning
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class PalindromePartition {
    
    public List<List<String>> partition(String s) {
        
        int n = s.length();
        //isPalindrom[i][j] => s.substring(i, j+1) is palindrome
        boolean[][] isPalindrome = new boolean[n][n];
        //A Single character is palindrome
        for (int i = 0; i < n; i++)
            isPalindrome[i][i] = true;
        //Scan from end to start
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    //i and j are neighbor or the string between i and j is palindrome
                    if (i + 1 == j || isPalindrome[i + 1][j - 1])
                        isPalindrome[i][j] = true;
                }
            }
        }
        List<List<String>> result = new LinkedList<>();
        List<String> branch = new LinkedList<>();
        partition(s, 0, isPalindrome, branch, result);
        return result;
    }
    
    private void partition(String s, int start, boolean[][] isPalindrome, List<String> branch, List<List<String>> result) {
        if (start == s.length()) {
            List<String> newList = new LinkedList<>();
            newList.addAll(branch);
            result.add(newList);
            return;
        }
        for (int i = start; i < s.length(); i++) {
            if (isPalindrome[start][i]) {
                branch.add(s.substring(start, i + 1));
                partition(s, i + 1, isPalindrome, branch, result);
                branch.remove(branch.size() - 1);
            }
        }
    }
    
    
    
    Map<String, Boolean> stringToIsPalindrome = new HashMap<String, Boolean>();
    
    public ArrayList<ArrayList<String>> partition_v1(String s) {
        
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        doCombination(result, new ArrayList<String>(), s);
        
        return result;
    }
    
    public void doCombination(ArrayList<ArrayList<String>> result, ArrayList<String> branch, String s) {
        
        if(s.length() == 0) {
            result.add(new ArrayList<>(branch));
            return;
        }
        
        /**
         * Because i is used to split the string, so it should be less EQUAL to s.length
         */
        for(int i = 1; i <= s.length(); i ++) {
            String prefix = s.substring(0, i);
            if(isPalindrome(prefix)) {
                branch.add(prefix);
                //Here it is the standard combination. Use the remaining part of s is same with ++startId
                doCombination(result, branch, s.substring(i));
                branch.remove(branch.size() - 1);
            }
        }
    }
    
    public boolean isPalindrome(String s) {
        
        boolean flag = true;
        if(stringToIsPalindrome.containsKey(s)) {
            return stringToIsPalindrome.get(s);
        }
        
        int i = 0, j = s.length() - 1;
        while(i < j) {
            if(s.charAt(i) == s.charAt(j)) {
                i ++;
                j --;
            } else {
                flag = false;
                break;
            }
        }
        stringToIsPalindrome.put(s, flag);
        
        return flag;
    }
    
    public void printReulst(ArrayList<ArrayList<String>> result) {
        StringBuilder sb = new StringBuilder();
        
        for(ArrayList<String> list : result) {
            sb.append("[");
            for(String s: list) {
                sb.append(s).append(",");
            }
            sb.append("];");
        }
        
        System.out.println(sb.toString());
    }
    
    public static void main(String[] args) {
        PalindromePartition pp = new PalindromePartition();
        System.out.println("ab: " + pp.isPalindrome("ab"));
        System.out.println("aba: " + pp.isPalindrome("aba"));
        System.out.println("abba: " + pp.isPalindrome("abba"));
        System.out.println("abcba: " + pp.isPalindrome("abcba"));
        System.out.println("abcdba: " + pp.isPalindrome("abcdba"));
        System.out.println("---------------------");
        
        pp.printReulst(pp.partition_v1("a"));
        pp.printReulst(pp.partition_v1("ab"));
        pp.printReulst(pp.partition_v1("cdd"));
        pp.printReulst(pp.partition_v1("abbab"));
        pp.printReulst(pp.partition_v1("ccaacabacb"));
        pp.printReulst(pp.partition_v1("seeslaveidemonstrateyetartsnomedievalsees"));
    }
}
