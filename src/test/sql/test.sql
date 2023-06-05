SELECT *
FROM post
WHERE memberId = 4
LIMIT 2 offset 8;

# 커서 예시 (key로 id를 썼다. 키 값 1000 이상으로 주어져서 offset 기반보다 이득이 발생한다.)
SELECT  *
FROM post
WHERE memberId = 4 and id > 1000;