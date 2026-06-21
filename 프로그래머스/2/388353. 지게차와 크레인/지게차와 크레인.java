import java.util.*;

/*
 지게차: 4면 중 적어도 1면이 창고 외부와 연결된 컨테이너만 가능 
 크레인: 외부와 연결되지 않은 컨테이너도 가능 
*/
class Solution {
    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};
    private int n, m;
    private char[][] grid;

    public int solution(String[] storage, String[] requests) {
        n = storage.length;
        m = storage[0].length();
        
        // 테두리 포함
        grid = new char[n + 2][m + 2];
        
        // 전체를 빈 공간으로 초기화
        for (int i = 0; i < n + 2; i++) {
            Arrays.fill(grid[i], '.');
        }
        
        // 가운데 영역에 실제 창고 데이터 복사
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                grid[i + 1][j + 1] = storage[i].charAt(j);
            }
        }

        // 요청 처리
        for (String req : requests) {
            char target = req.charAt(0);
            
            if (req.length() == 1) {
                // 지게차: 외부와 연결된 target만 제거
                removeByForklift(target);
            } else {
                // 크레인: 창고 전체에서 target 전부 제거
                removeByCrane(target);
            }
        }

        // 남은 컨테이너 개수 카운트
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (grid[i][j] != '.') {
                    answer++;
                }
            }
        }
        
        return answer;
    }

    // 지게차: (0,0) 외부 공기에서 출발하는 BFS
    private void removeByForklift(char target) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[n + 2][m + 2];
        List<int[]> toRemove = new ArrayList<>();

        // 외부 공간
        q.offer(new int[]{0, 0});
        visited[0][0] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr < 0 || nr >= n + 2 || nc < 0 
                    || nc >= m + 2 || visited[nr][nc]) {
                    continue;
                }

                if (grid[nr][nc] == '.') {
                    // 빈 공간
                    visited[nr][nc] = true;
                    q.offer(new int[]{nr, nc});
                } else if (grid[nr][nc] == target) {
                    // 외부 공기와 맞닿은 타겟 컨테이너
                    visited[nr][nc] = true;
                    toRemove.add(new int[]{nr, nc});
                }
            }
        }

        // 이번 턴에 제거된 컨테이너들 빈칸 처리
        for (int[] pos : toRemove) {
            grid[pos[0]][pos[1]] = '.';
        }
    }

    // 크레인: 맵 전체를 뒤져서 target을 빈칸으로 일괄 변경
    private void removeByCrane(char target) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (grid[i][j] == target) {
                    grid[i][j] = '.';
                }
            }
        }
    }
    
}