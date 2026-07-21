class Solution {
    private static final int MAX = Integer.MAX_VALUE;
    private static final int MIN = Integer.MIN_VALUE;
    
    public int solution(String arr[]) {
        int n = arr.length / 2 + 1; // 숫자 개수
        int[][] minDp = new int[n][n]; // i~j 까지의 최솟값
        int[][] maxDp = new int[n][n]; // i~j 까지의 최댓값
        
        // 초기화
        for (int i=0; i<n; i++) {
            int num = Integer.parseInt(arr[i*2]);
            minDp[i][i] = maxDp[i][i] = num;
        }
        
        for (int len=1; len<n; len++) { // 구간 길이
            for (int s=0; s<n-len; s++) { // 구간 시작
                int e = s + len; // 구간 끝
                
                minDp[s][e] = MAX;
                maxDp[s][e] = MIN;
                
                for (int m=s; m<e; m++) {
                    String op = arr[m*2+1]; // 연산자
                    int min, max;
                    
                    if (op.equals("+")) { // 덧셈
                        min = minDp[s][m] + minDp[m+1][e];
                        max = maxDp[s][m] + maxDp[m+1][e];
                    } else { // 뺄셈
                        min = minDp[s][m] - maxDp[m+1][e];
                        max = maxDp[s][m] - minDp[m+1][e];
                    }
                    
                    minDp[s][e] = Math.min(minDp[s][e], min);
                    maxDp[s][e] = Math.max(maxDp[s][e], max);
                }
            }
        }
        
        return maxDp[0][n-1];
    }//solution
    
}//class