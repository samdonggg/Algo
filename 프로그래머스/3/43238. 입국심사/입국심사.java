import java.util.*;

class Solution {
    
    public long solution(int n, int[] times) {
        int m = times.length; // 심사관 수
        Arrays.sort(times); // 심사 시간 정렬
        
        long answer = 0;
        long s = 1;
        long e = (long) n * times[m-1];
        long mid;
        
        while (s <= e) {
            mid = (s + e) / 2;
            
            if (isAvailable(n, mid, times)) {
                e = mid - 1;
                answer = mid;
            } else {
                s = mid + 1;
            }
        }
        
        return answer;
    }
    
    private boolean isAvailable(int n, long totalTime, int[] times) {
        long cnt = 0; // 심사 완료
        
        for (int time : times) {
            cnt += totalTime / time;
            if (cnt >= n) return true;
        }
        
        return false;
    }
    
}