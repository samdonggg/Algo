import java.util.Arrays;

class Solution {
    private static final int MAX = 22222;
    
    public int solution(int[][] info, int n, int m) {
        int total = info.length; // 물건 개수
        // dp[i][j]: i번 물건까지 훔쳤을 때 B의 누적 흔적이 j일 때 A의 최소 흔적
        int[][] dp = new int[total+1][m]; 
        
        for (int i=0; i<=total; i++) {
            Arrays.fill(dp[i], MAX);
        }
        
        // 아무것도 훔치지 않은 초기 상태
        dp[0][0] = 0;
        
        for (int i=1; i<=total; i++) {
            int a = info[i-1][0]; // A에 대한 흔적
            int b = info[i-1][1]; // B에 대한 흔적
            
            for (int j=0; j<m; j++) {
                if (dp[i-1][j] != MAX) {
                    // A가 훔치는 경우
                    if (dp[i-1][j] + a < n) {
                        dp[i][j] = Math.min(dp[i][j], dp[i-1][j] + a);
                    }
                    
                    // B가 훔치는 경우
                    if (j + b < m) {
                        dp[i][j+b] = Math.min(dp[i][j+b], dp[i-1][j]);
                    }
                }
            }
        }
        
        int answer = MAX;
        for (int b=0; b<m; b++) {
            answer = Math.min(answer, dp[total][b]);
        }
        
        // A도둑이 남긴 흔적의 누적 개수의 최솟값을 return
        // 두 도둑 모두 경찰에 붙잡히지 않게 할 수 없다면 -1을 return
        return answer != MAX ? answer : -1;
    }
    
}