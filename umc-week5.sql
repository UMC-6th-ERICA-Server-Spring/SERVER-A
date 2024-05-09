//내 id가 1인 경우
select m.point, s.name
from (member_mission as my_mission natural join mission as m using region_id) join store as s
where my_mission.status = ‘진행 완료’ and my_mission.member_id = 1 
limit 10;

select m.point, s.name
from (member_mission as my_mission natural join mission as m using region_id) join store as s
where my_mission.status = ‘진행 중’ and my_mission.member_id = 1 
limit 10;


// 내 id가 1이고, 가게 id가 2인 경우
insert into review(member_id, store_id, score, body, created_at) values (1,2,5,’음 너무 맛있어요…’,CURRENT_TIMESTAMP);


// 내 id가 1인 경우
select s.name, m.deadline, m.point
from (region as r join store as s on s.region_id == r.id) join mission as m using region_id
where r.name = ‘안암동’ 
limit 10;

// 내 id가 1인 경우
select name, email, point, phone_number
from member  
where id = 1;