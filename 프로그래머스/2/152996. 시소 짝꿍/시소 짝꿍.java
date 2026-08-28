class Solution {
    private static final int MIN_W = 100, MAX_W = 1000;
    
    public long solution(int[] weights) {
        long answer = 0;
        long[] counts = new long[MAX_W + 1];
        // 각 몸무게별 인원수 카운트
        for (int w : weights) {
            counts[w]++;
        }
        
        // 몸무게를 기준으로 가능한 짝꿍 조합 구하기
        for (int w=MIN_W; w<=MAX_W; w++) {
            if (counts[w] == 0) continue;
            
            // 몸무게 같음 (1:1)
            answer += counts[w] * (counts[w] - 1) / 2;
            
            // 2:3
            if (w % 2 == 0 && (w * 3 / 2) <= MAX_W) {
                answer += counts[w] * counts[w * 3 / 2];
            }
            // 1:2
            if ((w * 2) <= MAX_W) {
                answer += counts[w] * counts[w * 2];
            }
            // 3:4
            if (w % 3 == 0 && (w * 4 / 3) <= MAX_W) {
                answer += counts[w] * counts[w * 4 / 3];
            }
        }
        
        return answer;
    }
    
}