import java.util.*;

class Solution {
    private List<List<int[]>> pipes; // 파이프 연결 정보
    private boolean[] infected; // 감염 여부
    private int n, k; // 배양체 수, 최대 행동 수
    private int max; // 감염된 배양체 개수의 최댓값
    
    public int solution(int n, int infection, int[][] edges, int k) {
        // 초기화
        this.n = n;
        this.k = k;
        this.max = 0;
        this.pipes = new ArrayList<>(n+1); // 파이프 연결 정보
        this.infected = new boolean[n+1]; // 감염 여부
        
        for(int i=0; i<=n; i++) {
            pipes.add(new ArrayList<>());
        }
        
        // 파이프 연결
        for(int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];
            int type = edge[2];
            pipes.get(x).add(new int[] {y, type});
            pipes.get(y).add(new int[] {x, type});
        }
        
        // 최초 감염
        infected[infection] = true;
        
        dfs(0, 1, -1);
        
        return max;
    }//solution
    
    private void dfs(int step, int total, int lastType) {
        max = Math.max(max, total);
        
        // k번 행동했거나 전부 감염됐으면 종료 
        if (step == k || max == n) return;
        
        // 3가지 파이프 각각 열어보기
        for(int t=1; t<=3; t++) {
            // 연속으로 같은 타입 열지 않기
            if (t == lastType) continue;
            
            // 감염 확산
            List<Integer> infectedList = bfs(t);
            
            // 새롭게 감염된 배양체가 있으면 다음 파이프 열기
            if (!infectedList.isEmpty()) {
                dfs(step + 1, total + infectedList.size(), t);
                // 감염된 배양체 원상 복구
                for(int node : infectedList) {
                    infected[node] = false;
                }
            }
        }
    }//dfs
    
    private List<Integer> bfs(int type) {
        List<Integer> result = new ArrayList<>(); // 감염된 배양체들
        Queue<Integer> q = new LinkedList<>();
        
        // 감염된 배양체 큐에 넣기
        for(int i=1; i<=n; i++) {
            if (infected[i]) {
                q.add(i);
            }
        }
        
        while(!q.isEmpty()) {
            int cur = q.poll();
            
            for(int[] edge : pipes.get(cur)) {
                int next = edge[0]; // 다음 배양체
                int nType = edge[1]; // 연결된 파이프 타입
                
                // 파이프 종류가 같고, 아직 감염이 안 됐으면 감염 진행 
                if (nType == type && !infected[next]) {
                    infected[next] = true;
                    q.add(next);
                    result.add(next);
                }
            }
        }
        
        return result;
    }//bfs
    
}//class