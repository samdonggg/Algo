import java.util.*;

class Solution {
    private List<List<Integer>> graph; // 노드 연결 그래프
    private Map<Integer, Integer> cntMap; // 1번 노드와 key만큼 떨어진 노드 개수 저장
    private int nodeCnt;  // 노드의 개수
    private int max; // 가장 멀리 떨어진 거리
    
    public int solution(int n, int[][] edge) {
        init(n, edge);
        bfs();
        
        // 1번 노드로부터 가장 멀리 떨어진 노드가 몇 개인지를 return
        int answer = cntMap.get(max);
        return answer;
    }//solution
    
    private void bfs() {
        Queue<int[]> q = new LinkedList<>();
        boolean[] visited = new boolean[nodeCnt+1];
        
        int node = 1;
        int cnt = 0;
        q.offer(new int[] {node, cnt});
        visited[node] = true;
        
        int[] cur;
        while (!q.isEmpty()) {
            cur = q.poll();
            node = cur[0];
            cnt = cur[1];

            max = Math.max(max, cnt);
            cntMap.put(cnt, cntMap.getOrDefault(cnt, 0) + 1);
            
            for (int next : graph.get(node)) {
                if (visited[next]) continue;
                visited[next] = true;
                q.offer(new int[] {next, cnt + 1});
            }
        }
        
    }//bfs
    
    private void init(int n, int[][] edge) {
        this.nodeCnt = n; // 노드의 개수
        this.graph = new ArrayList<>(n+1); // 노드 연결 그래프
        this.cntMap = new HashMap<>(); // 1번 노드와 key만큼 떨어진 노드 개수 저장
        
        for (int i=0; i<=n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] e : edge) {
            int a = e[0];
            int b = e[1];
            // 간선은 양방향 [a, b]는 a번 노드와 b번 노드 사이에 간선이 있다는 의미
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
    }//init
    
}