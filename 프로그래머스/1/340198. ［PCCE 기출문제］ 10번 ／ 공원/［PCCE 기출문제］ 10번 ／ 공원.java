class Solution {
    private static final int MAX_SIZE = 20; // 돗자리 최대 크기
    private static final String EMPTY = "-1";
    
    public int solution(int[] mats, String[][] park) {
        boolean[] owned = new boolean[MAX_SIZE + 1]; // 갖고 있는 돗자리 체크
        
        for (int mat : mats) {
            owned[mat] = true;
        }
        
        int answer = -1; // 지민이가 깔 수 있는 가장 큰 돗자리의 한 변 길이
        int r = park.length;
        int c = park[0].length;
        int[][] dp = new int[r+1][c+1];
            
        for (int i=1; i<=r; i++) {
            for (int j=1; j<=c; j++) {
                // 이 자리에 돗자리가 깔려있으면 불가능
                if (!park[i-1][j-1].equals(EMPTY)) continue;
                
                int top = dp[i-1][j];
                int left = dp[i][j-1];
                // 현재 자리에 깔 수 있는 돗자리 크기 갱신
                dp[i][j] = Math.min(dp[i-1][j-1], Math.min(top, left)) + 1;
                
                // 해당 크기의 돗자리가 있다면 크기 비교 후 갱신
                if (dp[i][j] <= MAX_SIZE && owned[dp[i][j]]) {
                    answer = Math.max(answer, dp[i][j]);
                }
            }
        }
        
        return answer;
    }
    
}