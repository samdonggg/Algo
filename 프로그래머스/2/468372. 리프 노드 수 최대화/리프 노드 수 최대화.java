class Solution {
    private int splitLimit; // 리프 노드의 분배도
    
    public int solution(int dist_limit, int split_limit) {
        splitLimit = split_limit; // 리프 노드의 최대 분배도
        
        // 확장 가능한 노드 수, 남은 분배 노드, 현재 누적 분배도
        return (int) dfs(1, dist_limit, 1);
    }
    
    /*
     cur: 현재 깊이에서 분배 가능한 노드 수
     leftDist: 앞으로 쓸 수 있는 분배 노드 수
     split: 현재 깊이까지의 분배도
    */
    private long dfs(long cur, int leftDist, long split) {
        // 분배 노드를 다 썼거나 최소 분할(2분할)도 불가능할 때
        if (leftDist == 0 || split * 2 > splitLimit) {
            return cur; // 남은 노드들이 전부 리프 노드가 됨
        }
        
        // 리프 노드 수의 최댓값
        long max = cur;
        // 이번 깊이에서 사용할 노드 개수
        long used = Math.min(cur, leftDist);
        // 이번 깊이 리프 노드 개수
        long stopped = cur - used;
        
        // 현재 깊이의 노드들을 3분할
        if (split * 3 <= splitLimit) {
            long next = used * 3;
            // 다음 깊이로 넘어가서 탐색
            long leavesFromSubTree = dfs(next, leftDist - (int) used, split * 3);
            
            max = Math.max(max, stopped + leavesFromSubTree);
        }
        
        // 현재 깊이의 노드들을 2분할
        if (split * 2 <= splitLimit) {
            long next = used * 2;
            // 다음 깊이로 넘어가서 탐색
            long leavesFromSubTree = dfs(next, leftDist - (int) used, split * 2);
            
            max = Math.max(max, stopped + leavesFromSubTree);
        }
        
        return max;
    }
    
}