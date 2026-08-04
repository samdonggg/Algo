import java.util.*;

class Solution {
    private List<List<Integer>> lines; // 전선 연결 정보
    
    public int solution(int n, int[][] wires) {
        init(n, wires);
        
        int answer = n;
        
        for (int[] wire : wires) {
            int v1 = wire[0];
            int v2 = wire[1];
            
            // v1 v2 전선을 끊음
            int a = dfs(v1, v1, v2, new boolean[n+1]); // a그룹
            int b = n - a; // b그룹
            
            // 두 전력망이 가지고 있는 송전탑 개수의 차이(절대값)
            answer = Math.min(answer, Math.abs(a - b));
        }
        
        return answer;
    }
    
    private int dfs(int cur, int v1, int v2, boolean[] visited) {
        visited[cur] = true;
        
        int cnt = 1; // 연결된 송전탑 개수
        
        for (int next : lines.get(cur)) {
            if (visited[next]) continue;
            if ((cur == v1 && next == v2) || (cur == v2 && next == v1)) {
                continue;
            }
            
            cnt += dfs(next, v1, v2, visited);
        }
        
        return cnt;
    }
    
    private void init(int n, int[][] wires) {
        lines = new ArrayList<>(n+1);
        
        for (int i=0; i<=n; i++) {
            lines.add(new ArrayList<>());
        }
        
        for (int[] wire : wires) {
            int v1 = wire[0];
            int v2 = wire[1];
            // v1번 송전탑과 v2번 송전탑이 전선으로 연결되어 있다는 것을 의미
            lines.get(v1).add(v2);
            lines.get(v2).add(v1);
        }
    }
    
}