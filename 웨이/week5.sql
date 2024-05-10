/*진행 중인 미션*/
select * 
from mission 
where userId = 1 and status = true --접속한 사용자 id가 1이라 가정
order by createdAt desc
limit 10 offset 0;

/*진행 완료한 미션*/
select * 
from mission 
where userId = 1 and status = false --접속한 사용자 id가 1이라 가정
order by createdAt desc
limit 10 offset 0;

/*
  리뷰 ID는 자동으로 생성되는 일련번호를 따르도록 한다
  → 데이터베이스 시스템이 부여
*/
INSERT INTO review (reviewer, content, rate, createdAt, updatedAt, userId, shopId)
VALUES ('닉네임1234', '음 너무 맛있어요...', 5.0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 100);

/*선택 지역(안암동)에서 미션 조회*/
select m.*
from mission as m join shop as s on m.shopId = s.id
where s.province = "서울특별시" and s.section = "안암동"
order by m.id
limit 10 offset 0;

/*마이 페이지 화면 구성*/
select name, email, phone, mypoint
from user
where id = 1;
