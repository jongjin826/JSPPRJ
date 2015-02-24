--게시글 목록 조회
select * from notices order by regdate desc;

--게시글 조회
select * from notices where code = '300';

--현재 공지사항에서 code의 최대값을 가져오는 쿼리
select nvl(max(to_number(code)),0)+1 from notices;

--20개씩 나눈 레코드를 얻어오는 쿼리를 작성하시오.
select * from (select * from notices order by regdate desc) where rownum between 1 and 20;

--orcl
select * from (select rownum num, NOTICES.* from notices order by regdate desc) where num between 10 and 20;
--sqlserver
select (row_number() over (order by regdate desc)) num, notices.* from notices;

select * from (select (row_number() over (order by REGDATE desc)) num, notices.* from notices) n where n.num between 1 and 20;




select * from (select rownum num, N.* from (select * from notices order by regdate desc) N) where num between 11 and 20;

select * from NOTICES where title like '%%';

insert into notices(code, title, writer, content, regdate, hit) values('151','DUST','SUNGWAN','먼지가 되어~~~',SYSDATE,0);
		
delete from NOTICES where code = '120';

select count(*) from (select rownum num, NOTICES.* from notices order by regdate desc);

SELECT count(*) FROM (SELECT ROWNUM NUM, N.* FROM (SELECT * FROM NOTICES WHERE TITLE like '%%' ORDER BY REGDATE DESC) N);

SELECT NVL(MAX(TO_NUMBER(CODE)),0)+1 CODE FROM NOTICES;

SELECT * FROM NOTICEFILES;

SELECT sysdate FROM dual;

select cast(CODE as int)+1 from noticefiles;

SELECT isnull(MAX(cast(CODE as int)),0)+1 CODE FROM NOTICEs;


select * from (select (row_number() over (order by REGDATE desc)) num, notices.* from notices) n where n.num between 1 and 20;

--이전
SELECT TOP 1 * FROM NOTICES WHERE REGDATE > (SELECT REGDATE FROM NOTICES WHERE CODE = '242') ORDER BY REGDATE ASC;
--다음
SELECT TOP 1 * FROM NOTICES WHERE REGDATE < (SELECT REGDATE FROM NOTICES WHERE CODE = '242') ORDER BY REGDATE DESC;

SELECT * FROM MEMBERS;

INSERT INTO MEMBERS(MID, NAME, PWD, BIRTH) VALUES('KENNY','김경현','COMPUTER','1989-09-16');

select * from members where mid = 'kenny';