-- 7월 아이스크림 총 주문량과 상반기의 아이스크림 총 주문량을 
-- 더한 값이 큰 순서대로 상위 3개의 맛을 조회
SELECT F.FLAVOR 
FROM FIRST_HALF F 
JOIN (
    -- 7월은 같은 아이스크림이 여러 번 출하 가능, 맛별로 총 주문량 미리 합산
    SELECT FLAVOR, SUM(TOTAL_ORDER) AS TOTAL_ORDER 
    FROM JULY 
    GROUP BY FLAVOR 
) J ON F.FLAVOR = J.FLAVOR 
ORDER BY (F.TOTAL_ORDER + J.TOTAL_ORDER) DESC 
LIMIT 3;
