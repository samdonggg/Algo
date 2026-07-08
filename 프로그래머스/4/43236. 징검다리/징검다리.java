import java.util.Arrays;

class Solution {
    
    public int solution(int distance, int[] rocks, int n) {
        // 출발지점, 도착지점을 포함한 배열 생성
        int len = rocks.length + 2;
        int[] copy = Arrays.copyOf(rocks, len);
        
        copy[len-1] = distance;
        Arrays.sort(copy);
        
        int answer = 0;
        int s = 1;
        int e = distance;
        int mid;
        
        while (s <= e) {
            mid = (s + e) / 2;
            // 바위를 mid개 제거했을 때 n보다 작거나 같으면 거리 더 늘리기
            if (getRemovedCnt(mid, copy) <= n) {
                s = mid + 1;
                answer = mid;
            } else {
                // 많다면 거리 줄이기
                e = mid - 1;
            }
        }
        
        return answer;
    }//solution
    
    private int getRemovedCnt(int dist, int[] rocks) {
        int cnt = 0; // 제거한 바위 개수
        int len = rocks.length;
        int prev = rocks[0];
        
        for (int i=1; i<len; i++) {
            if (rocks[i] - prev < dist) {
                cnt++;
            } else {
                prev = rocks[i];
            }
        }
        
        return cnt;
    }//getRemovedCnt
    
}//class