/*내가 진행중인 미션 모아서 보는 쿼리*/
SELECT m.point, m.deadline, s.name, m.price
FROM Mission AS m
JOIN UserMission AS um  ON um.mission_id = m.id
JOIN Store AS s ON s.id = m.store_id
WHERE um.status = '진행중' and um.user_id = 1
ORDER BY um.created_at DESC
LIMIT 15;

/*내가 진행 완료한 미션 모아서 보는 쿼리*/
SELECT m.point, '성공', s.name, m.price
FROM UserMission AS um
JOIN Mission AS m  ON um.mission_id = m.id
JOIN Store AS s ON m.store_id = s.id
WHERE um.status = '진행완료' and um.user_id = 1
ORDER BY um.created_at DESC
LIMIT 15;

/*리뷰 작성하는 쿼리*/
INSERT INTO UserReview(store_id,user_id,star,body,created_at,updated_at)
VALUES(1,1,5,'음 너무 맛있어요 포인트도 얻고 맛있는 맛집도 알게 된 것 같아 너무나도 행복한 식사였답니다. 다음에 또 올게요!!',now(),now());

/*홈 화면 쿼리*/
SELECT s.name, m.deadline, m.price, m.point
FROM Mission AS m
JOIN Store AS s ON m.store_id = s.id
WHERE s.region = '안암동' and m.id not in (
	SELECT mission_id
	FROM UserMission
)
ORDER BY m.deadline DESC
LIMIT 15;

/*마이 페이지 화면 쿼리*/
SELECT name, email, phone_number, total_point
FROM User
WHERE id = 1;