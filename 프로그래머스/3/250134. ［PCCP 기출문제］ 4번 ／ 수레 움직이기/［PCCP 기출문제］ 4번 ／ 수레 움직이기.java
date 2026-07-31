class Solution {
    private static final int EMPTY = 0, WALL = 5, MAX = 100;
    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};
    
    private boolean[][] redVisited, blueVisited; // 빨간 수레, 파란 수레 방문 체크
    private int redEr, redEc, blueEr, blueEc; // 빨간 수레, 파란 수레 도착 칸
    private int n, m; // 미로 크기
    private int minCnt = MAX; // 퍼즐을 푸는데 필요한 턴의 최솟값
    
    public int solution(int[][] maze) {
        n = maze.length;
        m = maze[0].length;
        
        // 빨간 수레, 파란 수레 방문 체크
        redVisited = new boolean[n][m];
        blueVisited = new boolean[n][m];
        
        // 빨간 수레 , 파란 수레 시작 칸
        int redSr = 0, redSc = 0;
        int blueSr = 0, blueSc = 0;
        
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                if (maze[i][j] == 1) { // 빨간 수레의 시작 칸
                    redSr = i;
                    redSc = j;
                    maze[i][j] = EMPTY;
                } else if (maze[i][j] == 2) { // 파란 수레의 시작 칸
                    blueSr = i;
                    blueSc = j;
                    maze[i][j] = EMPTY;
                } else if (maze[i][j] == 3) { // 빨간 수레의 도착 칸
                    redEr = i;
                    redEc = j;
                    maze[i][j] = EMPTY;
                } else if (maze[i][j] == 4) { // 파란 수레의 도착 칸
                    blueEr = i;
                    blueEc = j;
                    maze[i][j] = EMPTY;
                }
            }
        }

        redVisited[redSr][redSc] = true;
        blueVisited[blueSr][blueSc] = true;
        
        dfs(redSr, redSc, blueSr, blueSc, 0, maze);
        
        return minCnt == MAX ? 0 : minCnt;
    }
    
    private void dfs(int rr, int rc, int br, int bc, int move, int[][] maze) {
        if (move > minCnt) return;
        
        // 둘 다 도착함
        if (rr == redEr && rc == redEc && br == blueEr && bc == blueEc) {
            minCnt = Math.min(minCnt, move);
            return;
        }
        
        if (rr == redEr && rc == redEc) { // 빨간 수레 도착
            // 파란 수레만 이동
            for (int i=0; i<4; i++) {
                int nr = br + DR[i];
                int nc = bc + DC[i];
                if (!inRange(nr, nc, maze) || blueVisited[nr][nc]) continue;
                if (rr == nr && rc == nc) continue;
                
                blueVisited[nr][nc] = true;
                dfs(rr, rc, nr, nc, move + 1, maze);
                blueVisited[nr][nc] = false;
            }
        } else if (br == blueEr && bc == blueEc) { // 파란 수레 도착
            // 빨간 수레만 이동
            for (int i=0; i<4; i++) {
                int nr = rr + DR[i];
                int nc = rc + DC[i];
                if (!inRange(nr, nc, maze) || redVisited[nr][nc]) continue;
                if (br == nr && bc == nc) continue;
                
                redVisited[nr][nc] = true;
                dfs(nr, nc, br, bc, move + 1, maze);
                redVisited[nr][nc] = false;
            }
        } else { // 둘 다 도착 x
            for (int i=0; i<4; i++) {
                int nrr = rr + DR[i];
                int nrc = rc + DC[i];
                if (!inRange(nrr, nrc, maze) || redVisited[nrr][nrc]) continue;
                
                redVisited[nrr][nrc] = true;
                
                for (int j=0; j<4; j++) {
                    int nbr = br + DR[j];
                    int nbc = bc + DC[j];
                    if (!inRange(nbr, nbc, maze) || blueVisited[nbr][nbc]) continue;
                    if (nrr == nbr && nrc == nbc) continue;
                    // 서로 위치를 바꾸면서 움직일 수 없음
                    if (nrr == br && nrc == bc && nbr == rr && nbc == rc) continue;
                    
                    blueVisited[nbr][nbc] = true;
                    dfs(nrr, nrc, nbr, nbc, move + 1, maze);
                    blueVisited[nbr][nbc] = false;
                }
                
                redVisited[nrr][nrc] = false;
            }
        }
        
    }
    
    private boolean inRange(int r, int c, int[][] maze) {
        return r >= 0 && c >= 0 && r < n && c < m && maze[r][c] != WALL;
    }
    
}