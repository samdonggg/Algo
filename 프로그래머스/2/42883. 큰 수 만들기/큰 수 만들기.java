class Solution {
    
    public String solution(String number, int k) {
        StringBuilder ans = new StringBuilder();
        int len = number.length();
        
        for (int i=0; i<len; i++) {
            char cur = number.charAt(i);
            
            while (k > 0 && ans.length() > 0 && ans.charAt(ans.length()-1) < cur) {
                k--;
                ans.deleteCharAt(ans.length()-1);
            }
            
            ans.append(cur);
        }
        
        return ans.substring(0, ans.length() - k);
    }
    
}