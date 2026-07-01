import java.util.*;

class Solution {
    private static final int R = 101, C = 101; // 물류 센터 크기
    private int n, x, m; // 포인트 개수, 로봇 개수, 운송 경로 길이   
    
    public int solution(int[][] points, int[][] routes) {
        this.n = points.length; // 포인트 개수
        this.x = routes.length; // 로봇 개수
        this.m = routes[0].length; // 운송 경로 길이
        
        // 로봇 이동 경로
        List<List<int[]>> robots = getRobotRoutes(points, routes);
        
        int answer = 0;
        // 최대 이동 시간
        int maxTime = robots.stream()
            .mapToInt(List::size)
            .max()
            .orElse(0);

        for (int t=0; t<maxTime; t++) {
            int[][] visited = new int[R][C];
            for (int i=0; i<x; i++) {
                // i번 로봇이 t초 이전에 이미 모든 이동이 끝났다면 패스
                if (robots.get(i).size() <= t) continue;
                // t초에 i번 로봇의 위치
                int r = robots.get(i).get(t)[0];
                int c = robots.get(i).get(t)[1];    
                // 충돌이 일어났다면 횟수 증가
                if (++visited[r][c] == 2) answer++;
            }
        }
        
        return answer;
    }
    
    // 로봇 이동 경로 구하기
    private List<List<int[]>> getRobotRoutes(int[][] points, int[][] routes) {
        List<List<int[]>> list = new ArrayList<>(x);
        
        for (int i=0; i<x; i++) {
            list.add(new ArrayList<>());
            int point = 0;
            int r = points[routes[i][point]-1][0];
            int c = points[routes[i][point]-1][1];
            list.get(i).add(new int[] {r, c});
            
            point++;
            int nr = points[routes[i][point]-1][0];
            int nc = points[routes[i][point]-1][1];
            
            while (point < m) {
                // r 좌표가 변하는 이동을 c 좌표가 변하는 이동보다 먼저 
                if (r != nr) {
                    if (r < nr) r++;
                    else r--;
                } else {
                    if (c < nc) c++;
                    else c--;
                }                
                list.get(i).add(new int[] {r, c});
                
                // 목적지에 도착했다면 다음 포인트로 이동
                if (r == nr && c == nc) {
                    if (++point >= m) break;
                    nr = points[routes[i][point]-1][0];
                    nc = points[routes[i][point]-1][1];
                }
            }
        }
        
        return list;
    }
    
}