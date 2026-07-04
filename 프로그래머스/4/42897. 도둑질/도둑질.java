class Solution {
    
    public int solution(int[] money) {
        int n = money.length; // 집의 수
        int[] first = new int[n]; // 1번집부터
        int[] second = new int[n]; // 2번집부터
        
        first[0] = first[1] = money[0];
        second[1] = money[1];
        
        for (int i=2; i<n; i++) {
            // 현재집 안 털기 vs 현재집 털기
            first[i] = Math.max(first[i-1], first[i-2] + money[i]);
            second[i] = Math.max(second[i-1], second[i-2] + money[i]);
        }
        
        // 1번집 털면 마지막 집 x, 2번집은 마지막집 o
        int answer = Math.max(first[n-2], second[n-1]);
        return answer;
    }
    
}