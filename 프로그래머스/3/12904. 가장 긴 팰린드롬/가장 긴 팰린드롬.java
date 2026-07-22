class Solution {
    
    public int solution(String string) {
        int n = string.length(); // 문자열 길이
        boolean[][] dp = new boolean[n][n]; // 구간 i~j 팰린드롬 여부
        char[] str = string.toCharArray();
        int max = 1; // 가장 긴 팰린드롬 길이
        
        // 길이 1 - 자기 자신은 팰린드롬
        for (int i=0; i<n; i++) {
            dp[i][i] = true;
        }
        
        // 길이 2 - 인접한 구간
        for (int i=0; i<n-1; i++) {
            if (str[i] == str[i+1]) {
                dp[i][i+1] = true;
                max = 2;
            }
        }
        
        // 길이 3 이상
        for (int len=2; len<n; len++) {
            for (int s=0; s<n-len; s++) {
                int e = s + len;
                
                // 양쪽 문자가 같고 그 안쪽 구간도 팰린드롬이면 
                if (str[s] == str[e] && dp[s+1][e-1]) {
                    dp[s][e] = true;
                    max = len + 1;
                }
            }
        }
        
        return max;
    }//solution
    
}//class