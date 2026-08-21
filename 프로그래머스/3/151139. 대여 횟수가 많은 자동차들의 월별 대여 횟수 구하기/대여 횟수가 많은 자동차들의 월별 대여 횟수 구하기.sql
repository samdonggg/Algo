-- 2022년 8 ~ 10월까지 총 대여 횟수가 5회 이상인 자동차들에 대해서 
-- 해당 기간 동안의 월별 자동차 ID 별 총 대여 횟수 리스트를 출력
-- 월을 기준으로 오름차순 정렬, 월이 같다면 자동차 ID를 기준으로 내림차순 
-- 특정 월의 총 대여 횟수가 0인 경우에는 결과에서 제외
SELECT 
    MONTH(START_DATE) AS MONTH, 
    CAR_ID, 
    COUNT(*) AS RECORDS 
FROM 
    CAR_RENTAL_COMPANY_RENTAL_HISTORY 
WHERE 
    START_DATE >= '2022-08-01' AND START_DATE < '2022-11-01'
    AND CAR_ID IN (
        -- 2022년 8 ~ 10월까지 총 대여 횟수가 5회 이상인 자동차
        SELECT CAR_ID 
        FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY 
        WHERE START_DATE >= '2022-08-01' AND START_DATE < '2022-11-01'
        GROUP BY CAR_ID 
        HAVING COUNT(*) >= 5
    )
GROUP BY 
    MONTH(START_DATE), CAR_ID
ORDER BY 
    MONTH ASC, CAR_ID DESC;