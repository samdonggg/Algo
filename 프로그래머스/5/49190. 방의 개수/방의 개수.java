import java.util.*;

class Solution {
    private static final int[] DX = {-1, -1, 0, 1, 1, 1, 0, -1};
    private static final int[] DY = {0, 1, 1, 1, 0, -1, -1, -1};
    
    public int solution(int[] arrows) {
        Map<Point, Set<Point>> visited = new HashMap<>();
        int x = 0, y = 0;
        int answer = 0;
        
        Point cur = new Point(x, y); // 현재 지점
        visited.put(cur, new HashSet<>());
        
        for (int arrow : arrows) {
            for (int i=0; i<2; i++) {
                int nx = cur.x + DX[arrow];
                int ny = cur.y + DY[arrow];

                Point next = new Point(nx, ny);
                
                if (!visited.containsKey(next)) { // 다음 지점 방문 처음
                    visited.put(next, new HashSet<>());
                } else if (!visited.get(cur).contains(next)) {
                    // 다음 지점 방문한 적 있고 길은 처음이면 방 생성
                    answer++;
                }

                // 방문 표시
                visited.get(cur).add(next);
                visited.get(next).add(cur);

                // 지점 이동
                cur = next;                
            }
        }
        
        return answer;
    }
    
    private class Point {
        int x;
        int y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            
            Point p = (Point) o;
            return this.x == p.x && this.y == p.y;
        }
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }//Point
    
}