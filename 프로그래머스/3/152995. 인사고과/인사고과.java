import java.util.*;

class Solution {
    
    public int solution(int[][] scores) {
        // 완호 점수
        int scoreA = scores[0][0];
        int scoreB = scores[0][1];
        int sum = scoreA + scoreB;
        
        // 정렬
        Arrays.sort(scores, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            return o2[0] - o1[0];
        });
        
        int maxB = 0;
        int rank = 1;
        
        for (int[] score : scores) {
            int curA = score[0];
            int curB = score[1];
            
            // 현재 사람이 이전 사람보다 A점수도 낮고 B점수도 낮음
            // 인센티브 못받음
            if (curB < maxB) {
                // 그게 완호
                if (curA == scoreA && curB == scoreB) {
                    return -1;
                }
            } else {
                maxB = Math.max(maxB, curB);
                // 완호보다 총점이 높은 사람이 있으면 등수 증가
                if (curA + curB > sum) {
                    rank++;
                }
            }
        }
        
        return rank;
    }
    
}