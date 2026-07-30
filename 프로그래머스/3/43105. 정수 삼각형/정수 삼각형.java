class Solution {
    
    public int solution(int[][] triangle) {
        int n = triangle.length;
        int[][] dp = new int[n][n];
        
        // 꼭대기 값 초기화
        dp[0][0] = triangle[0][0];
        
        for (int i=1; i<n; i++) {
            // 맨 왼쪽 가장자리
            dp[i][0] = dp[i-1][0] + triangle[i][0];
            
            // 중간 칸들
            for (int j=1; j<i; j++) {
                dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) + triangle[i][j];
            }
            
            // 맨 오른쪽 가장자리
            dp[i][i] = dp[i-1][i-1] + triangle[i][i];
        }
        
        // 바닥에서 가장 큰 값 찾기
        int answer = 0;
        for (int num : dp[n-1]) {
            answer = Math.max(answer, num);
        }
        
        return answer;
    }
    
}