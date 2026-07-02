import java.util.*;

class Solution {
    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};
    private static final int WALL = 0;
    
    public int solution(int[][] maps) {
        // 지도의 크기 n, m
        int n = maps.length;
        int m = maps[0].length;
        
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        
        int r = 0, c = 0, cnt = 1;
        visited[r][c] = true;
        q.offer(new int[] {r, c, cnt});
        
        int[] cur;
        while (!q.isEmpty()) {
            cur = q.poll();
            r = cur[0];
            c = cur[1];
            cnt = cur[2];
            
            if (r == n-1 && c == m-1) {
                return cnt;
            }
            
            for (int i=0; i<4; i++) {
                int nr = r + DR[i];
                int nc = c + DC[i];
                
                if (nr < 0 || nc < 0 || nr >= n || nc >= m) continue;
                if (visited[nr][nc] || maps[nr][nc] == WALL) continue;
                
                visited[nr][nc] = true;
                q.offer(new int[] {nr, nc, cnt+1});
            }
        }
        
        // 상대 팀 진영에 도착할 수 없을 때는 -1을 return
        return -1;
    }
    
}