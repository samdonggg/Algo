class Solution {
    private int n; // 퍼즐 개수
    
    public int solution(int[] diffs, int[] times, long limit) {
        this.n = diffs.length;
        
        int maxDiff = 0; // 퍼즐 최고 난이도
        for (int diff : diffs) {
            maxDiff = Math.max(maxDiff, diff);
        }
        
        int answer = 0;
        int start = 1;
        int end = maxDiff;
        int mid;
        
        while (start <= end) {
            mid = (start + end) / 2;
            
            if (isPossible(mid, diffs, times, limit)) {
                end = mid - 1;
                answer = mid;
            } else {
                start = mid + 1;
            }
        }
        
        return answer;
    }//solution
    
    private boolean isPossible(int level, int[] diffs, int[] times, long limit) {
        long totalTime = times[0]; // 모든 퍼즐을 해결한 시간
        
        for (int i=1; i<n; i++) {
            totalTime += times[i];
            
            // 현재 퍼즐의 난이도가 숙련도보다 높으면 cnt만큼 퍼즐 틀림
            if (diffs[i] > level) {
                long time = times[i] + times[i-1];
                int cnt = diffs[i] - level;
                totalTime += time * cnt;
            }
            
            if (totalTime > limit) {
                return false;
            }
        }
        
        return true;
    }//isPossible
    
}//class