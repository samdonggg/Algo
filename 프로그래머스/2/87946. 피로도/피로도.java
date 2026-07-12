class Solution {
    private int N; // 던전 수
    private int max; // 탐험할수 있는 최대 던전 수
    
    public int solution(int k, int[][] dungeons) {
        N = dungeons.length;
        dfs(k, 0, 0, dungeons);
        
        return max;
    }//solution
    
    private void dfs(int power, int cnt, int visited, int[][] dungeons) {
        max = Math.max(max, cnt);
        
        for (int i=0; i<N; i++) {
            if ((visited & (1 << i)) != 0) continue;
            if (power < dungeons[i][0]) continue;
            
            dfs(power - dungeons[i][1], cnt+1, visited | (1 << i), dungeons);
        }
    }//dfs
    
}