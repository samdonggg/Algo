-- 'SUV'인 자동차들의 평균 일일 대여 요금을 출력하는 SQL문을 작성
-- 소수 첫 번째 자리에서 반올림하고, 컬럼명은 AVERAGE_FEE 로 지정
SELECT 
    ROUND(AVG(DAILY_FEE)) AS AVERAGE_FEE 
FROM 
    CAR_RENTAL_COMPANY_CAR 
WHERE 
    CAR_TYPE = 'SUV';