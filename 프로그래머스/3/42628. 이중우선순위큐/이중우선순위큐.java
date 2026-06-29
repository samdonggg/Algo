import java.util.Collections;
import java.util.LinkedList;

class Solution {
    
    public int[] solution(String[] operations) {
        int[] answer = {0, 0};   
        boolean check = false;
        LinkedList<Integer> dq = new LinkedList<>();        

        for(String o : operations) {
            if (o.charAt(0) == 'I') {
                dq.offer(Integer.parseInt(o.substring(2)));
                check = true;
            } else {
                if (check) {
                    Collections.sort(dq);
                    check = false;
                }
                if (!dq.isEmpty()) {
                    if (o.charAt(2) == '-') dq.poll();
                    else  dq.pollLast();
                }
            }
        }

        if (!dq.isEmpty()) {
            if (check) Collections.sort(dq);
            answer[0] = dq.peekLast();
            answer[1] = dq.peek();
        }

        return answer;
    }
    
}