# 1은 데이터 매우 적고, 3은 백만개, 4는 2백만개

## explain 사용해보기
explain select count(*) from POST;

## POST 형태
SELECT memberId, count(id)
FROM POST
GROUP BY memberId;

SELECT createdDate, count(id)
FROM POST
GROUP BY createdDate
ORDER BY  2 DESC;

## POST__index_member_id

### 1의 경우 빠르다.
SELECT createdDate, memberId, count(id)
FROM POST use index (POST__index_member_id)
WHERE memberId = 1 and createdDate between '1900-01-01' and '2023-01-01'
GROUP BY memberId, createdDate;

### 4의 경우 느리다.
SELECT createdDate, memberId, count(id)
FROM POST use index (POST__index_member_id)
WHERE memberId = 4 and createdDate between '1900-01-01' and '2023-01-01'
GROUP BY memberId, createdDate;

## POST__index_created_date

### 1의 경우 매우 느려진다.
SELECT createdDate, memberId, count(id)
FROM POST use index (POST__index_created_date)
WHERE memberId = 1 and createdDate between '1900-01-01' and '2023-01-01'
GROUP BY memberId, createdDate;

### 4의 경우 약간 빨라진다.
SELECT createdDate, memberId, count(id)
FROM POST use index (POST__index_created_date)
WHERE memberId = 4 and createdDate between '1900-01-01' and '2023-01-01'
GROUP BY memberId, createdDate;

## 아무것도 없으면 자동으로 적절한 index 사용
## 이 경우는 POST_index_member_id_created_date 를 사용하게 된다.

SELECT createdDate, memberId, count(id)
FROM POST
WHERE memberId = 1 and createdDate between '1900-01-01' and '2023-01-01'
GROUP BY memberId, createdDate;

SELECT createdDate, memberId, count(id)
FROM POST
WHERE memberId = 4 and createdDate between '1900-01-01' and '2023-01-01'
GROUP BY memberId, createdDate;

