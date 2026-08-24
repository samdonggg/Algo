import java.util.function.Function;

class Solution {
    private static final int MAX = Integer.MAX_VALUE;
    
    public int solution(int[] depth, int money, 
                        Function<Integer, Integer> excavate) {
        int w = depth.length;
        // cost[i][j] = 보물이 i~j 열 안에 있고 확실히 찾을 수 있는 최대 비용의 최솟값 
        int[][] cost = new int[w+2][w+2];
        // pick[i][j] = 그때 처음 파야 하는 열
        int[][] pick = new int[w+2][w+2];
        
        // 길이 1 구간
        for (int i=1; i<=w; i++) {
            cost[i][i] = depth[i-1];
            pick[i][i] = i;
        }
        
        // 구간 길이 늘려가며 채우기
        for (int len = 2; len<=w; len++) {
            for (int l=1; l<=w-len+1; l++) {
                int r = l + len - 1;
                int bestCost = MAX;
                int bestPick = l;
                
                for (int m=l; m<=r; m++) {
                    int left = cost[l][m-1];
                    int right = cost[m+1][r];
                    int curCost = depth[m-1] + Math.max(left, right);
                    if (curCost < bestCost) {
                        bestCost = curCost;
                        bestPick = m;
                    }
                }
                
                cost[l][r] = bestCost;
                pick[l][r] = bestPick;
            }
        }
        
        int l = 1, r = w;
        while (l <= r) {
            int cur = pick[l][r]; // 현재 구간에서 파낼 열 
            int result = excavate.apply(cur);
            
            if (result == 0) return cur;
            else if (result == -1) {
                r = cur - 1; // 왼쪽 방향에 있음
            } else {
                l = cur + 1; // 오른쪽 방향에 있음
            }
        }
        
        return 0;
    }
    
}