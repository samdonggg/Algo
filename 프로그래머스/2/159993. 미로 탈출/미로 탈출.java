import java.util.*;

class Solution {
    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};
    private static final int X = -1, L = 1, E = 2;
    
    private int[][] map; // 미로 지도
    private int R, C; // 미로 크기
    private int sr, sc, lr, lc; // 출발, 레버 위치
    
    public int solution(String[] maps) {
        init(maps);
        
        int toL = bfs(sr, sc, L); // 출발 -> 레버
        int toE = bfs(lr, lc, E); // 레버 -> 출구
        
        if (toL == -1 || toE == -1) {
            return -1;
        }
        
        return toL + toE;
    }
    
    private int bfs(int r, int c, int target) {
        boolean[][] visited = new boolean[R][C];
        Queue<int[]> q = new LinkedList<>();
        int cnt = 0;
        
        visited[r][c] = true;
        q.offer(new int[] {r, c, cnt});
        
        int[] cur;
        while (!q.isEmpty()) {
            cur = q.poll();
            r = cur[0];
            c = cur[1];
            cnt = cur[2];
            
            // 목적지 도착
            if (map[r][c] == target) {
                return cnt;
            }
            
            for (int i=0; i<4; i++) {
                int nr = r + DR[i];
                int nc = c + DC[i];
                if (!inRange(nr, nc) || visited[nr][nc]) continue;
                
                visited[nr][nc] = true;
                q.offer(new int[] {nr, nc, cnt+1});
            }
        }
        
        // 목적지 도달 불가능
        return -1;
    }
    
    private boolean inRange(int r, int c) {
        return r >= 0 && c >= 0 && r < R && c < C && map[r][c] != X;
    }
    
    private void init(String[] maps) {
        R = maps.length;
        C = maps[0].length();
        map = new int[R][C];
        
        for (int r=0; r<R; r++) {
            for (int c=0; c<C; c++) {
                char cur = maps[r].charAt(c);
                
                if (cur == 'S') {
                    sr = r;
                    sc = c;
                } else if (cur == 'E') {
                    map[r][c] = E;
                } else if (cur == 'L') {
                    lr = r;
                    lc = c;
                    map[r][c] = L;
                } else if (cur == 'X') {
                    map[r][c] = X;
                }
            }
        }
    }
    
}