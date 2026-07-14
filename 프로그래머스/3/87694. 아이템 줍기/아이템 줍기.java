import java.util.*;

class Solution {
    private static final int[] DX = {-1, 1, 0, 0};
    private static final int[] DY = {0, 0, -1, 1};
    private static final int BORDER = 1, INSIDE = -1;
    private static final int N = 101;
    
    public int solution(int[][] rectangle, int characterX, int characterY, 
                        int itemX, int itemY) {
        int[][] map = getMap(rectangle); // 사각형을 그린 지도
        
        int cx = characterX * 2, cy = characterY * 2;
        int ix = itemX * 2, iy = itemY * 2;
        
        int answer = bfs(cx, cy, ix, iy, map);
        
        return answer / 2;
    }//solution
    
    private int bfs(int cx, int cy, int ix, int iy, int[][] map) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        
        int x = cx, y = cy, dist = 0;
        q.offer(new int[] {x, y, dist});
        visited[x][y] = true;
        
        int[] cur;
        while (!q.isEmpty()) {
            cur = q.poll();
            x = cur[0];
            y = cur[1];
            dist = cur[2];
            
            if (x == ix && y == iy) {
                return dist;
            }
            
            for (int i=0; i<4; i++) {
                int nx = x + DX[i];
                int ny = y + DY[i];
                // 범위, 방문 체크
                if (!inRange(nx, ny) || visited[nx][ny]) continue;
                
                // 다음 위치가 테두리면 이동
                if (map[nx][ny] == BORDER) {
                    q.offer(new int[] {nx, ny, dist + 1});
                }
                visited[nx][ny] = true;
            }
        }
        
        return dist;
    }//bfs
    
    private boolean inRange(int x, int y) {
        return 0 < x && x < N && 0 < y && y < N;
    }//inRange
    
    private int[][] getMap(int[][] rectangle) {
        int[][] map = new int[N][N];
        
        for (int[] pos : rectangle) {
            int x1 = pos[0] * 2;
            int y1 = pos[1] * 2;
            int x2 = pos[2] * 2;
            int y2 = pos[3] * 2;
            
            for (int x=x1; x<=x2; x++) {
                for (int y=y1; y<=y2; y++) {
                    // 현재 위치가 사각형 내부인 경우
                    if (x1 < x && x < x2 && y1 < y && y < y2) {
                        map[x][y] = INSIDE;
                    } else {
                        // 테두리 표시
                        if (map[x][y] != INSIDE) {
                            map[x][y] = BORDER;
                        }
                    }
                }
            }
        }
        
        return map;
    }//getMap
    
}