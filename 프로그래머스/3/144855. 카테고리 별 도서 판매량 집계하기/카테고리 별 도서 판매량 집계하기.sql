-- 2022년 1월의 카테고리 별 도서 판매량을 합산하고,
-- 카테고리, 총 판매량 리스트를 출력 (카테고리명을 기준으로 오름차순 정렬)
SELECT 
    B.CATEGORY, 
    SUM(SALES) AS TOTAL_SALES 
FROM 
    BOOK B 
JOIN 
    BOOK_SALES BS ON B.BOOK_ID = BS.BOOK_ID 
WHERE 
    BS.SALES_DATE >= '2022-01-01' AND BS.SALES_DATE < '2022-02-01' 
GROUP BY 
    B.CATEGORY 
ORDER BY 
    CATEGORY ASC;