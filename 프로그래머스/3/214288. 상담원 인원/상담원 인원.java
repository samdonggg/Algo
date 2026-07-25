import java.util.*;

class Solution {
    private int minTime = Integer.MAX_VALUE;
    private int k; // 상담 유형 개수
    private int[][] waitTimes; // [유형][멘토 수] = 해당 조건에서 대기 시간
    
    public int solution(int k, int n, int[][] reqs) {
        this.k = k; // 상담 유형 개수
        int extraMentors = n - k; // 남은 멘토 수
        
        // 유형별 상담 요청 분류
        List<List<int[]>> typeReqs = new ArrayList<>(k+1);
        for (int i=0; i<=k; i++) {
            typeReqs.add(new ArrayList<>());
        }    
        for (int[] req : reqs) {
            int a = req[0], b = req[1], c = req[2];
            // c번 유형을 원하는 참가자가 a분에 b분 동안의 상담을 요청함
            typeReqs.get(c).add(new int[] {a, b});
        }
        
        // 각 유형별로 배치된 멘토 수에 따른 대기 시간 계산
        waitTimes = new int[k+1][extraMentors+1];
        for (int i=1; i<=k; i++) {
            for (int j=0; j<=extraMentors; j++) {
                waitTimes[i][j] = calculate(typeReqs.get(i), j+1);
            }
        }
        
        // 남은 멘토를 분배하는 모든 경우의 수 탐색
        dfs(1, extraMentors, 0);
        
        return minTime;
    }//solution
    
    // 특정 상담 유형의 총 대기시간을 계산하는 메서드
    private int calculate(List<int[]> reqs, int mentors) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int total = 0;
        
        for (int[] req : reqs) {
            int start = req[0];
            int duration = req[1];
            
            // 상담이 비어있는 멘토가 있으면 바로 상담 시작
            if (pq.size() < mentors) {
                pq.offer(start + duration);
            } else {
                int minFinish = pq.poll(); // 가장 빨리 끝나는 멘토
                
                if (minFinish <= start) {
                    // 대기 없이 상담 바로 시작
                    pq.offer(start + duration);
                } else {
                    total += (minFinish - start);
                    pq.offer(minFinish + duration);
                }
            }
        }
        
        return total;
    }//calculate
    
    private void dfs(int type, int mentors, int totalTime) {
        if (type == k) {
            totalTime += waitTimes[k][mentors];
            minTime = Math.min(minTime, totalTime);
            
            return;
        }
        
        // 현재 유형에 멘토를 0명부터 남은 멘토 수까지 배정
        for (int i=0; i<=mentors; i++) {
            dfs(type + 1, mentors - i, totalTime + waitTimes[type][i]);
        }
    }//dfs
    
}//class