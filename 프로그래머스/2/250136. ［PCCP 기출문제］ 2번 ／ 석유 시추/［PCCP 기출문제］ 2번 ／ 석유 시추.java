import java.util.*;

class Solution {
    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};

    private int n, m; // 땅 크기
    private int[] oilSumByCol; // 각 열에서 얻을 수 있는 총 석유량
    
    public int solution(int[][] land) {
        n = land.length;
        m = land[0].length;
        oilSumByCol = new int[m];
        boolean[][] visited = new boolean[n][m];
        
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                if (land[i][j] == 0 || visited[i][j]) {
                    continue;
                }
                
                extractOilChunk(i, j, land, visited);
            }
        }
        
        int answer = 0; // 가장 많은 석유량
        for (int c=0; c<m; c++) {
            answer = Math.max(answer, oilSumByCol[c]);
        }
        
        return answer;
    }//solution
    
    private void extractOilChunk(int r, int c, int[][] land, boolean[][] visited) {
        Queue<int[]> q = new LinkedList<>();
        
        q.offer(new int[] {r, c});
        visited[r][c] = true;
        
        int size = 0; // 현재 석유 덩어리 크기
        int minC = c;
        int maxC = c;
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            r = cur[0];
            c = cur[1];
            
            size++;
            minC = Math.min(minC, c);
            maxC = Math.max(maxC, c);
            
            for (int i=0; i<4; i++) {
                int nr = r + DR[i];
                int nc = c + DC[i];
                
                if (nr < 0 || nc < 0 || nr >= n || nc >= m) continue;
                if (land[nr][nc] == 0 || visited[nr][nc]) continue;
                
                visited[nr][nc] = true;
                q.offer(new int[] {nr, nc});
            }
            
        }
        
        // 현재 석유 덩어리가 걸쳐있는 열에 석유 덩어리 크기 누적
        for (int i=minC; i<=maxC; i++) {
            oilSumByCol[i] += size;
        }
        
    }//extractOilChunk
    
}//class