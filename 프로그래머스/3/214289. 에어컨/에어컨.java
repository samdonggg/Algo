import java.util.*;

class Solution {
    private static final int MAX = 111_111, MAX_TEMP = 50;
    
    public int solution(int temperature, int t1, int t2, 
                        int a, int b, int[] onboard) {
        int time = onboard.length;        
        // 온도 0부터 시작하도록 설정
        temperature += 10; 
        t1 += 10;
        t2 += 10;
        
        // i분에 j도 최소 소비 전력
        int[][] dp = new int[time][MAX_TEMP+1]; 
        for (int i=0; i<time; i++) {
            Arrays.fill(dp[i], MAX);
        }
        
        dp[0][temperature] = 0; // 0분에는 실외온도와 동일
        
        for (int i=0; i<time-1; i++) {
            boolean isOnboard = onboard[i] == 1; // 현재 승객 탑승 여부
            for (int j=0; j<=MAX_TEMP; j++) {
                if (dp[i][j] == MAX) continue;
                // 승객 탑승 시점에 현재 온도가 적정 온도가 아니라면
                if (isOnboard && (j < t1 || t2 < j)) {
                    continue;
                }
                
                // --- 에어컨 on: 전력 소비 o ---
                // 온도 유지: 전력 b만큼 소비
                dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j] + b);
                // 온도 상승, 하강: 전력 a만큼 소비
                if (j < MAX_TEMP) dp[i+1][j+1] = Math.min(dp[i+1][j+1], dp[i][j] + a);
                if (j > 0) dp[i+1][j-1] = Math.min(dp[i+1][j-1], dp[i][j] + a);
                
                // --- 에어컨 off: 전력 소비 x ---
                if (temperature == j) { // 온도 유지
                    dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j]);
                } else if (j < temperature && j < MAX_TEMP) { // 온도 상승
                    dp[i+1][j+1] = Math.min(dp[i+1][j+1], dp[i][j]);
                } else if (temperature < j && 0 < j) { // 온도 하강
                    dp[i+1][j-1] = Math.min(dp[i+1][j-1], dp[i][j]);
                }
            }
        }
        
        int answer = MAX;
        boolean isOnboardAtLast = onboard[time-1] == 1;
        
        for (int temp=0; temp<=MAX_TEMP; temp++) {
            if (isOnboardAtLast && (temp < t1 || t2 < temp)) continue;
            answer = Math.min(answer, dp[time-1][temp]);
        }
        
        return answer;
    }
    
}