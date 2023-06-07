SELECT *
FROM post
WHERE memberId = 4
LIMIT 2 offset 8;

# 커서 예시 (key로 id를 썼다. 키 값 1000 이상으로 주어져서 offset 기반보다 이득이 발생한다.)
SELECT  *
FROM post
WHERE memberId = 4 and id > 1000;


# 트랜잭션

START TRANSACTION;

# 여러 개의 SQL 문들
# 여러 개의 SQL 문들
# 여러 개의 SQL 문들

COMMIT ;

# 롤백할거면 COMMIT 대신

ROLLBACK ;

# Lock

SELECT *
FROM post
WHERE memberId = 1 and contents = 'string';

START TRANSACTION ;

SELECT *
FROM post
WHERE memberId = 1
FOR UPDATE;

COMMIT;

## Transaction 확인
SELECT *
FROM information_schema.INNODB_TRX;

## Lock 확인
SELECT *
FROM performance_schema.data_locks
WHERE LOCK_TYPE = 'RECORD';