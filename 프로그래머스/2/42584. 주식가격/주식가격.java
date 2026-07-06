import java.util.Queue;
import java.util.LinkedList;

class Solution {
    
    public int[] solution(int[] prices) {
        int size = prices.length;
        int[] answer = new int[size];        
        Queue<Integer> stock = new LinkedList<>();
        
        for(int p : prices) {
            stock.offer(p);
        }

        for(int i=1, s=0; i<size; i++) {
            s = 0;
            for(int j=i; j<size; j++) {
                s++;
                if(stock.peek() > prices[j]) break;
            }  
            answer[i-1] = s;
            stock.poll();
        }

        return answer;
    }
    
}