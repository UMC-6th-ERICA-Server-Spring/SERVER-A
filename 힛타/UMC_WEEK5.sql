/* 1 내가 진행 중, 진행 완료한 미션 모아서 보는 쿼리 */

/* 포인트, 가게이름, 최소주문금액 - 성공 부분은 완료면 그냥 성공으로 지칭되는 지 정확하게 모르겠음 */
SELECT m.point, s.name, m.condition
FROM Mission AS m
JOIN User_Mission As um ON um.mission_id = m.id
JOIN Store AS s ON s.id = m.store_id
WHERE um.user_id = {유저 id} AND um.status = TRUE /* (수행중이면 true, 완료했으면 false로 erd 설계했음) */
ORDER BY um.created_at DESC
LIMIT 15 OFFSET 0; /* (첫 페이지인 경우임 만약, 두번째 페이지라면 OFFSET (n-1) * 15 = 15) */

/* 2 리뷰 작성하는 쿼리 */
/* 유저와 점포 id를 1로 입력값 받았다는 가정했음 */
INSERT INTO review (user_id, store_id, body, star, createdat, updatedat) 
VALUES (1, 1, "음 너무 맛있어요 ..", 5.0, NOW(), NOW());

/* 3 홈 화면 쿼리 */
/* 3-1 미션 총 몇 개 달성했는 지 */
SELECT COUNT(*)
FROM User
JOIN User_Mission AS um ON um.user_id = User.id
WHERE um.status = FALSE; 

/* 3-2 현재 선택된 지역에서 도전이 가능한 미션 목록 페이징 */
SELECT s.name, m.deadline, m.point, m.condition
FROM Mission AS m
JOIN Store AS s ON m.store_id = s.id
WHERE s.address = "서울특별시 안암동" /* api마다 다르겠지만 아마 앞 주소, 뒤쪽 주소 반씩 나눠서 들어오는 것으로 알고 있음.. 이건 그거에 맞춰서 진행해야 할듯합니다. */
LIMIT 5 OFFSET 0; 

/* 4 마이 페이지 화면 쿼리 */
/* phone_number는 회원가입 시 필수 입력이 아니므로 null이면 프론트엔드에서 미인증으로 표시 */
SELECT nickname, email, phone_number, point
FROM User
WHERE id = {유저 id};