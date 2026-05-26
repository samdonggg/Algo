import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/468370
class Solution {
    private static final char blur = '*';
    
    public int solution(String message, int[][] spoiler_ranges) {
        // 스포 방지 처리 
        String blurredString = blurMessage(message, spoiler_ranges);
        
        // 단어 배열로 분리
        String[] originWords = message.split(" "); // 기존 단어
        String[] blurredWords = blurredString.split(" "); // 스포 방지 후 단어

        // 중요한 단어 개수 구하기
        int answer = calculate(originWords, blurredWords);
        return answer;
    }//solution
    
    private int calculate(String[] originWords, String[] blurredWords) {
        Set<String> words = new HashSet<>(); // 일반 단어
        Set<String> spoilerWords = new HashSet<>(); // 스포 방지 단어
        
        int len = blurredWords.length;
        for(int i=0; i<len; i++) {
            // 스포 방지 단어가 아님
            if (blurredWords[i].indexOf(blur) == -1) {
                words.add(originWords[i]);
            } else { // 스포 방지 단어임
                spoilerWords.add(originWords[i]);
            }
        }
        
        // 스포 방지 단어 중 일반 단어로 등장했던 단어들 제거
        spoilerWords.removeAll(words);
        
        return spoilerWords.size();
    }//saveWord
    
    private String blurMessage(String message, int[][] spoiler_ranges) {
        StringBuilder copy = new StringBuilder(message);
            
        for(int[] range : spoiler_ranges) {
            int start = range[0]; // 스포 방지 시작
            int end = range[1]; // 스포 방지 종료
            
            for(int i=start; i<=end; i++) {
                if (message.charAt(i) != ' ') {
                    copy.setCharAt(i, blur);
                }
            }
        }
        
        return copy.toString();
    }//blurMessage
    
}//class