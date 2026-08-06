-- 생일이 3월인 여성 회원의 ID, 이름, 성별, 생년월일을 조회하는 SQL문을 작성
-- 전화번호가 NULL인 경우는 출력대상에서 제외, 결과는 회원ID를 기준으로 오름차순 정렬
SELECT 
    MEMBER_ID, MEMBER_NAME, GENDER, DATE_OF_BIRTH 
FROM  
    MEMBER_PROFILE 
WHERE 
    MONTH(DATE_OF_BIRTH) = 3 
    AND GENDER = 'w' 
    AND TLNO IS NOT NULL 
ORDER BY 
    MEMBER_ID;