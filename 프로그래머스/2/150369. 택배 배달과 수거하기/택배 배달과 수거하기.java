class Solution {
    
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        int delivery = 0;
        int pickup = 0;
        
        for (int i=n; i>0; i--) {
            // 현재 집에서 필요한 택배 배달, 수거 개수 누적
            delivery += deliveries[i-1];
            pickup += pickups[i-1];
            
            while (delivery > 0 || pickup > 0) {
                delivery -= cap;
                pickup -= cap;
                answer += (i * 2L);
            }
        }
        
        return answer;
    }//solution
    
}//class