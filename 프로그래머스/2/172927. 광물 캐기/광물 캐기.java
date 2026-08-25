class Solution {
    private static final int D = 0, I = 1, S = 2;
    private final int[][] cost = {
        //다,철,돌
        {1, 1, 1},  // 다이아
        {5, 1, 1,}, // 철
        {25, 5, 1}  // 돌
    };
    private int minCost = Integer.MAX_VALUE;
    private int n;
    
    public int solution(int[] picks, String[] minerals) {
        n = minerals.length;
        int[] types = new int[n];
        
        for (int i=0; i<n; i++) {
            if (minerals[i].equals("diamond")) types[i] = D;
            else if (minerals[i].equals("iron")) types[i] = I;
            else types[i] = S;
        }
        
        dfs(picks[D], picks[I], picks[S], 0, 0, types);
        return minCost;
    }
    
    private void dfs(int dia, int iron, int stone, 
                     int idx, int totalCost, int[] types) {
        if (totalCost >= minCost) return;
        // 광물 다 캤거나 곡괭이 다 쓴 경우
        if (idx >= n || (dia == 0 && iron == 0 && stone == 0)) {
            minCost = Math.min(minCost, totalCost);
            return;
        }
        
        // 3종류 곡괭이 각각 사용해보기
        for (int i=0; i<3; i++) {
            if (i == D && dia == 0) continue;
            if (i == I && iron == 0) continue;
            if (i == S && stone == 0) continue;
            
            int curCost = totalCost;
            int nIdx = Math.min(idx + 5, n);
            
            // 현재 곡괭이로 최대 5개의 광물 캐기
            for (int j=idx; j<nIdx; j++) {
                int t = types[j];
                curCost += cost[i][t];
            }
            
            if (i == D) dfs(dia-1, iron, stone, nIdx, curCost, types);
            else if (i == I) dfs(dia, iron-1, stone, nIdx, curCost, types);
            else dfs(dia, iron, stone-1, nIdx, curCost, types);
        }
    }
    
}