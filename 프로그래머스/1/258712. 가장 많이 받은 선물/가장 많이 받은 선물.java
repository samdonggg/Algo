import java.util.*;

class Solution {
    private Map<String, Integer> ids;
    private int[][] sentHistory;
    private int[] sentCnt, receivedCnt;
    private int N;
    
    public int solution(String[] friends, String[] gifts) {
        init(friends, gifts);
        
        int answer = 0;
        
        for (int a=0; a<N; a++) {
            int gift = 0;
            int score = sentCnt[a] - receivedCnt[a];
            for (int b=0; b<N; b++) {
                if (a == b) continue;
                // 두 사람이 선물을 주고받은 기록이 있음
                if (sentHistory[a][b] != sentHistory[b][a]) {
                    // 더 많은 선물을 준 사람이 다음 달에 선물을 하나 받음
                    if (sentHistory[a][b] > sentHistory[b][a]) {
                        gift++;
                    } 
                } else { 
                    // 두 사람이 주고받은 기록이 없거나 주고받은 수가 같으면
                    // 선물 지수가 더 큰 사람이 선물을 하나 받음
                    int bScore = sentCnt[b] - receivedCnt[b];
                    if (score > bScore) {
                        gift++;
                    }
                }
            }
            
            answer = Math.max(answer, gift);
        }
        
        return answer;
    }//solution
    
    private void init(String[] friends, String[] gifts) {
        this.ids = new HashMap<>();
        this.N = friends.length;
        this.sentHistory = new int[N][N];
        this.sentCnt = new int[N];
        this.receivedCnt = new int[N];
        
        for (int i=0; i<N; i++) {
            ids.put(friends[i], i);
        }
        
        for (String gift : gifts) {
            String[] names = gift.split(" ");
            // a가 b한테 선물을 줬다는 의미
            int a = ids.get(names[0]);
            int b = ids.get(names[1]);
            sentCnt[a]++;
            receivedCnt[b]++;
            sentHistory[a][b]++;
        }
        
    }//init
    
}//class