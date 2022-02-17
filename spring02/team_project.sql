--spring 버젼 게시판 만들기
-- cascade constraints 제약조건까지 모두 삭제
drop table review cascade constraints;



--리뷰 게시판 테이블
create table review (
review_bno number not null, --글번호
review_title varchar2(200) not null, --제목
review_content clob, --본문
review_writer varchar2(50) not null, --작성자
review_regdate date default sysdate, --작성일자
review_viewcnt number default 0, --조회수
review_show char(1) default 'Y', --삭제시 화면 표시 여부
primary key(review_bno)
);


--테이블 구조보기
desc review;

select * from review;

commit;


insert into review (review_bno,review_title,review_content,review_writer) values
(1,'제목','내용','kim');

select * from review;


--review의 시퀀스 생성(--drop sequence seq_board;--시퀀스 삭제)
drop sequence seq_review;

create sequence seq_review
start with 1
increment by 1;


--리뷰 게시판 댓글 테이블
drop table review_reply cascade constraints;


create table review_reply (
review_rno number not null primary key, --댓글번호
review_bno number default 0, --게시물 번호
review_replytext varchar2(1000) not null, -- 댓글 내용
review_replyer varchar2(50) not null, -- 댓글 작성자 아이디
review_regdate date default sysdate, -- 댓글 작성일
review_updatedate date default sysdate -- 댓글 수정일
);

select * from review_reply;


--foreign key 제약조건 추가
alter table review_reply add constraint fk_review
foreign key(review_bno) references review(review_bno);



--시퀀스 생성
drop sequence seq_review_reply;

create sequence seq_review_reply
start with 1
increment by 1;



--첨부파일 테이블
drop table review_attach cascade constraints;

create table review_attach (
review_fullName varchar2(150) not null, --첨부파일 이름
review_bno number not null, --review 테이블의 글번호
review_regdate date default sysdate, --업로드 날짜
primary key(review_fullName) --uuid적용한 파일이름
);


--review_bno 컬럼에 foreign key 설정
alter table review_attach add constraint fk_review_review_attatch
foreign key(review_bno) references review(review_bno);

commit;



--이거 준성씨랑 변수 확인후 커밋해줘야함
select review_bno,review_title,review_writer,review_content,review_regdate,review_viewcnt
from review r, member m
where r.review_writer=m.userid
order by review_bno desc;




-- 우선 아래 각 테이블의 데이터를 지운다.
delete from attach;
delete from board;




--페이지 나누기 테스트를 위해 레코드 입력
declare --선언부
    i number := 1;
begin --실행부
    while i<=991 loop
        insert into review (review_bno,review_title,review_content,review_writer)
        values
        ( (select nvl(max(review_bno)+1,1) from review)
        ,'제목'||i, '내용'||i, 'kim');
        i := i+1; --조건 변경
    end loop;
end;
/

select * from review;

--레코드 갯수 확인
select count(*) from review;

commit;














--여기서부턴 지금 프로젝트에 필요없어 보여서 안고침



-- from => where => select => order by 절 순서로 실행됨
-- rownum : 레코드의 출력 순번


--이건 지금 프로젝트에 필요없어 보여서 안고침
select *
from (
    select rownum as rn, A.*
    from (
        select bno,title,writer,name,regdate,viewcnt
        from board b, member m
        where b.writer=m.userid
        order by bno desc 
    ) A    
) where rn between 1 and 10;



drop sequence seq_board;

create sequence seq_board
start with 1000
increment by 1;

commit;


select*from reply;

--해당 글번호에 대한 댓글 개수
select count(*) from reply where bno=1001;