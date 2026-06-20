class Solution {
    
    public int solution(int[][] signals) {
        // 모든 신호등이 처음 상태로 돌아오는 전체 주기 구하기
        long maxTime = 1;
        for (int[] s : signals) {
            long total = s[0] + s[1] + s[2];
            maxTime = lcm(maxTime, total);
        }
        
        // 1초부터 전체 주기까지 모든 신호등이 노란불이 되는 가장 빠른 시각 탐색 
        for (long t=1; t<=maxTime; t++) {
            boolean allYellow = true;
            
            for (int[] s : signals) {
                int g = s[0]; // 초록불
                int y = s[1]; // 노란불
                long total = g + y + s[2]; // 해당 신호등의 주기 
                long mod = (t-1) % total;
                
                // 노란불 구간에 속하지 않는 신호등이 하나라도 있으면 실패
                if (mod < g || mod >= g+y) {
                    allYellow = false;
                    break;
                }
            }
            
            if (allYellow) {
                return (int) t;
            }
        }
        return -1;
    }
    
    // 최대공약수
    private long gcd(long a, long b) {
        while (b != 0) {
            long tmp = b;
            b = a % b;
            a = tmp;
        }
        
        return a;
    }
    
    // 최소공배수
    private long lcm(long a, long b) {
        return (a * b) / gcd(a, b);
    }
    
}