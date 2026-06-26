class Solution {
    private static final int MAX = 1_611_111;
    
    public int solution(int[][] cost, int[][] hint) {
        int n = cost.length;         // 스테이지 개수
        int maxStatus = 1 << (n-1); // 힌트 번들 구매 조합
        int minCost = MAX;          // 모든 스테이지를 해결하는데 필요한 최소 비용
        
        for (int status=0; status<maxStatus; status++) {
            int hintCost = 0; // 힌트 번들 비용
            int[] hintCounts = new int[n+1]; // 스테이지별 힌트 개수
            
            for (int i=0; i<n-1; i++) {
                // 현재 스테이지 힌트 구매한 상태임
                if ((status & (1 << i)) != 0) {
                    hintCost += hint[i][0];   // 힌트 비용 누적
                    int cnt = hint[i].length; // 힌트권 수
                    
                    // 힌트 번들에 포함된 힌트들 카운트
                    for (int h=1; h<cnt; h++) {
                        int stage = hint[i][h];
                        hintCounts[stage]++;
                    }
                }
            }
            
            int totalCost = hintCost; // 총 비용
            
            for (int i=0; i<n; i++) {
                int stage = i+1; // 현재 스테이지
                // 스테이지에서 사용할 수 있는 힌트의 최대 개수는 n-1개
                int cnt = Math.min(hintCounts[stage], n-1);
                
                totalCost += cost[i][cnt];
            }
            // 최소 비용 갱신
            minCost = Math.min(minCost, totalCost);
        }
        
        return minCost;
    }
    
}