import java.util.*;

class Solution {
    private List<List<Integer>> winList; // 승리 정보
    private List<List<Integer>> loseList; // 패배 정보
    
    public int solution(int n, int[][] results) {
        init(n, results);
        
        int answer = 0;
        
        for (int i=1; i<=n; i++) {
            int winCnt = dfs(i, winList, new boolean[n+1]);
            int loseCnt = dfs(i, loseList, new boolean[n+1]);

            if (winCnt + loseCnt == n-1) {
                answer++;
            }
        }
        
        return answer;
    }//solution
    
    private int dfs(int cur, List<List<Integer>> list, boolean[] visited) {
        visited[cur] = true;
        
        int cnt = 0;
        for (int next : list.get(cur)) {
            if (visited[next]) continue;
            cnt += dfs(next, list, visited) + 1;
        }
        
        return cnt;
    }//dfs
    
    private void init(int n, int[][] results) {
        this.winList = new ArrayList<>(n+1);
        this.loseList = new ArrayList<>(n+1);
        
        for (int i=0; i<=n; i++) {
            winList.add(new ArrayList<>());
            loseList.add(new ArrayList<>());
        }
        
        for (int[] result : results) {
            int a = result[0];
            int b = result[1];
            
            winList.get(a).add(b); // a가 b를 이김
            loseList.get(b).add(a); // b가 a한테 졌음
        }
    }//init
    
}