--�Խñ� ��� ��ȸ
select * from notices order by regdate desc;

--�Խñ� ��ȸ
select * from notices where code = '300';

--���� �������׿��� code�� �ִ밪�� �������� ����
select nvl(max(to_number(code)),0)+1 from notices;

--20���� ���� ���ڵ带 ������ ������ �ۼ��Ͻÿ�.
select * from (select * from notices order by regdate desc) where rownum between 1 and 20;

--orcl
select * from (select rownum num, NOTICES.* from notices order by regdate desc) where num between 10 and 20;
--sqlserver
select (row_number() over (order by regdate desc)) num, notices.* from notices;

select * from (select (row_number() over (order by REGDATE desc)) num, notices.* from notices) n where n.num between 1 and 20;




select * from (select rownum num, N.* from (select * from notices order by regdate desc) N) where num between 11 and 20;

select * from NOTICES where title like '%%';

insert into notices(code, title, writer, content, regdate, hit) values('151','DUST','SUNGWAN','������ �Ǿ�~~~',SYSDATE,0);
		
delete from NOTICES where code = '120';

select count(*) from (select rownum num, NOTICES.* from notices order by regdate desc);

SELECT count(*) FROM (SELECT ROWNUM NUM, N.* FROM (SELECT * FROM NOTICES WHERE TITLE like '%%' ORDER BY REGDATE DESC) N);

SELECT NVL(MAX(TO_NUMBER(CODE)),0)+1 CODE FROM NOTICES;

SELECT * FROM NOTICEFILES;

SELECT sysdate FROM dual;

select cast(CODE as int)+1 from noticefiles;

SELECT isnull(MAX(cast(CODE as int)),0)+1 CODE FROM NOTICEs;


select * from (select (row_number() over (order by REGDATE desc)) num, notices.* from notices) n where n.num between 1 and 20;

--����
SELECT TOP 1 * FROM NOTICES WHERE REGDATE > (SELECT REGDATE FROM NOTICES WHERE CODE = '242') ORDER BY REGDATE ASC;
--����
SELECT TOP 1 * FROM NOTICES WHERE REGDATE < (SELECT REGDATE FROM NOTICES WHERE CODE = '242') ORDER BY REGDATE DESC;

SELECT * FROM MEMBERS;

INSERT INTO MEMBERS(MID, NAME, PWD, BIRTH) VALUES('KENNY','�����','COMPUTER','1989-09-16');

select * from members where mid = 'kenny';