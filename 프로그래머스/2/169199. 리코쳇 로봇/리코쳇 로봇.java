import java.util.*;

class Solution {
    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};
    
    private int n, m; // 게임판 크기
    private char[][] map; // 게임판
    
    public int solution(String[] board) {
        n = board.length;
        m = board[0].length();
        map = new char[n][m];
        
        int r = 0, c = 0;
        
        for (int i=0; i<n; i++) {
            map[i] = board[i].toCharArray();
            for (int j=0; j<m; j++) {
                // 로봇 첫 위치
                if (map[i][j] == 'R') {
                    r = i;
                    c = j;
                }
            }
        }
        
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        
        q.add(new int[] {r, c, 0});
        visited[r][c] = true;
        
        int[] cur;
        int cnt;
        while (!q.isEmpty()) {
            cur = q.poll();
            r = cur[0];
            c = cur[1];
            cnt = cur[2];
            
            // 목표위치에 도달
            if (map[r][c] == 'G') {
                return cnt;
            }
            
            for (int i=0; i<4; i++) {
                int nr = r;
                int nc = c;
                // 장애물이나 게임판 가장자리까지 부딪힐 때까지 미끄러져 움직임
                while (isAvailable(nr+DR[i], nc+DC[i])) {
                    nr += DR[i];
                    nc += DC[i];
                }
                
                if (!visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q.add(new int[] {nr, nc, cnt+1});
                }
            }
        }
        
        return -1;
    }
    
    private boolean isAvailable(int r, int c) {
        return  r >= 0 && c >= 0 && r < n && c < m && map[r][c] != 'D';
    }
    
}