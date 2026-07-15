import java.util.*;

class Solution {
    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};
    
    private List<List<Position>> emptyList, puzzleList;
    private boolean[] used; // 퍼즐 사용 여부
    private int N; // 보드 크기 
    private int P; // 퍼즐 개수
    
    public int solution(int[][] game_board, int[][] table) {
        init(game_board, table);
        
        int answer = 0;
        
        for (List<Position> positions : emptyList) {
            // 현재 자리에 맞는 퍼즐이 있다면 칸 수 증가
            if (hasMatchingPuzzle(positions)) {
                answer += positions.size();
            }
        }
        
        return answer;
    }//solution
    
    /* 현재 칸에 맞는 퍼즐이 있는지 확인 */
    private boolean hasMatchingPuzzle(List<Position> empty) {
        for (int i=0; i<P; i++) {
            // 퍼즐 사용 여부, 퍼즐이랑 빈칸의 크기 확인
            if (used[i] || empty.size() != puzzleList.get(i).size()) continue;
            
            // 현재 퍼즐
            List<Position> puzzle = puzzleList.get(i);
            for (int k=0; k<4; k++) {
                // 현재 빈칸에 퍼즐을 채울 수 있는지 확인
                if (isEqual(empty, puzzle)) {
                    used[i] = true;
                    return true;
                }
                // 회전
                puzzle = rotate(puzzle);
            }
        }
        
        return false;
    }//hasMatchingPuzzle
    
    private List<Position> rotate(List<Position> block) {
        List<Position> rotated = new ArrayList<>();
        
        for (Position pos : block) {
            rotated.add(new Position(pos.c, -pos.r));
        }
        
        return normalizeBlock(rotated);
    }//rotate
    
    /* 두 좌표가 동일한지 확인 */
    private boolean isEqual(List<Position> a, List<Position> b) {
        int size = a.size();
        
        for (int i=0; i<size; i++) {
            if (a.get(i).r != b.get(i).r || a.get(i).c != b.get(i).c) {
                return false;
            }
        }
        
        return true;
    }//isEqaul
    
    private List<Position> normalizeBlock(List<Position> block) {
        int minR = Integer.MAX_VALUE;
        int minC = Integer.MAX_VALUE;
        
        for (Position pos : block) {
            minR = Math.min(minR, pos.r);
            minC = Math.min(minC, pos.c);
        }
        
        List<Position> normalized = new ArrayList<>();
        for (Position pos : block) {
            normalized.add(new Position(pos.r - minR, pos.c - minC));
        }
        
        Collections.sort(normalized);
        return normalized;
    }//normalizeBlock
    
    private void bfs(int status, int[][] map, boolean[][] visited, 
                     Queue<Position> q, List<Position> list) {
        Position cur;
        int r, c;
        
        while (!q.isEmpty()) {
            cur = q.poll();
            r = cur.r;
            c = cur.c;
            
            list.add(new Position(r, c));
            
            for (int i=0; i<4; i++) {
                int nr = r + DR[i];
                int nc = c + DC[i];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                if (visited[nr][nc] || map[nr][nc] != status) continue;
                
                visited[nr][nc] = true;
                q.offer(new Position(nr, nc));
            }
        }
    }//bfs
    
    private void init(int[][] gameBoard, int[][] table) {
        this.emptyList = new ArrayList<>(); // 빈칸 저장
        this.puzzleList = new ArrayList<>(); // 퍼즐 저장
        this.N = table.length;
        
        boolean[][] visited = new boolean[N][N];
        Queue<Position> q = new LinkedList<>();
        
        for (int r=0; r<N; r++) {
            for (int c=0; c<N; c++) {
                if (gameBoard[r][c] == 0 && !visited[r][c]) {
                    emptyList.add(new ArrayList<>());
                    visited[r][c] = true;
                    q.offer(new Position(r, c));
                    int idx = emptyList.size() - 1;
                    
                    bfs(0, gameBoard, visited, q, emptyList.get(idx));
                }
            }
        }
        
        visited = new boolean[N][N];
        for (int r=0; r<N; r++) {
            for (int c=0; c<N; c++) {
                if (table[r][c] == 1 && !visited[r][c]) {
                    puzzleList.add(new ArrayList<>());
                    visited[r][c] = true;
                    q.offer(new Position(r, c));
                    int idx = puzzleList.size() - 1;
                    
                    bfs(1, table, visited, q, puzzleList.get(idx));
                }
            }
        }
        
        this.P = puzzleList.size(); // 퍼즐 개수
        used = new boolean[P]; // 퍼즐 사용 여부

        // 좌표 조정
        moveToTorigin(emptyList);
        moveToTorigin(puzzleList);
    }//init
    
    /* (0,0) 기준으로 좌표 조정 */
    private void moveToTorigin(List<List<Position>> list) {
        for (int i = 0; i < list.size(); i++) {
            list.set(i, normalizeBlock(list.get(i)));
        }
    }//moveToTorigin
    
    private class Position implements Comparable<Position> {
        int r;
        int c;
        Position(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public int compareTo(Position o) {
            if (this.r == o.r) {
                return Integer.compare(this.c, o.c); // 행이 같으면 열 기준 오름차순
            }
            return Integer.compare(this.r, o.r); // 행 기준 오름차순
        }
        
        @Override
        public String toString() {
            return this.r + "," + this.c;
        }
    }//Position
    
}//Solution