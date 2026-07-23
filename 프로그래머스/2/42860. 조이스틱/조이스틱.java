class Solution {
    
    public int solution(String name) {
        int answer = 0;
        char[] arr = name.toCharArray();
        int n = arr.length; // 이름 길이
        int minMove = n - 1; // 좌우로 움직이는 최소 횟수
        
        for (int i=0; i<n; i++) {
            // 상하 이동
            char c = arr[i];
            answer += Math.min(c - 'A', 'Z' - c + 1);
            
            // 좌우 이동
            int next = i+1;
            // 다음 문자가 A면 어디까지 연속됐는지 찾음
            while (next < n && arr[next] == 'A') {
                next++;
            }

            // i까지 왔다가 뒤로 돌아가는 경우 (우 -> 좌)
            minMove = Math.min(minMove, (i * 2) + n - next);
            // 뒤에서부터 먼저 확인하고 다시 앞으로 오는 경우 (좌 -> 우)
            minMove = Math.min(minMove, (n - next) * 2 + i);
        }
        
        answer += minMove;
        return answer;
    }
    
}