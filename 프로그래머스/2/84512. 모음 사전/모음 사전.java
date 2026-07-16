import java.util.*;

class Solution {
    private final char[] C = {'A', 'E', 'I', 'O', 'U'};
    private List<String> dictionary = new ArrayList<>();
    
    public int solution(String word) {
        dfs(0, "");
        
        return dictionary.indexOf(word);
    }//solution
    
    private void dfs(int depth, String word) {
        dictionary.add(word);
        if (depth == 5) return;
        
        for (char c : C) {
            dfs(depth + 1, word + c);
        }
    }//dfs
    
}//Solution