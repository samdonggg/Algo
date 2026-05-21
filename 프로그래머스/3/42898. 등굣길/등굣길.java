// https://school.programmers.co.kr/learn/courses/30/lessons/42898

class Solution {
    private static final int MOD = 1_000_000_007;
    
    public int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[n+1][m+1]; // 최단경로의 개수
        
        int len = puddles.length;
        for(int i=0; i<len; i++) {
            int r = puddles[i][1];
            int c = puddles[i][0];
            dp[r][c] = -1; // 물에 잠김
        }
        
        dp[1][1] = 1; // 출발점
        for(int r=1; r<=n; r++) {
            for(int c=1; c<=m; c++) {
                if (r == 1 && c == 1) continue;
                
                if (dp[r][c] == -1) { // 물웅덩이는 경로 0개
                    dp[r][c] = 0;
                } else {
                    // 오른쪽과 아래쪽으로만 움직임
                    int top = (r > 1) ? dp[r-1][c] : 0;
                    int left = (c > 1) ? dp[r][c-1] : 0;
                    dp[r][c] = (top + left) % MOD;                
                }
            }
        }
        
        return dp[n][m];
    }//solution
    
}//class