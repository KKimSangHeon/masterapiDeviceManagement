---- template API 관련 DDL 
---- 생성일 : 2018-11-22

-- pgAdmin Dump(Backup) 시 참고 : 
--   - Format : Plain / 인코딩 : EUC_KR / rolename : smcp_user

--------
---- 전체 Table 목록
--------
-- Table: mbr_bas

-- DROP TABLE mbr_bas;

CREATE TABLE mbr_bas
(
  mbr_seq numeric(10,0) NOT NULL, -- 회원기본 회원일련번호
  mbr_id character varying(30), -- 회원기본 회원아이디
  credential_id character varying(20), -- 회원기본 크리덴셜아이디
  cret_dt timestamp without time zone DEFAULT now(), -- 멤버기본 생성일시
  user_nm character varying(30), -- 사용자명
  tel_no character varying(20), -- 전화번호
  email character varying(30), -- 이메일
  user_tokn character varying(50), -- 사용자토큰
  mbr_pwd character varying(60), -- 회원비밀번호
  del_yn character varying(1), -- 삭제여부
  mbr_clas character varying(50) DEFAULT '0003'::character varying, -- 회원등급
  mbr_role_val character varying(500), -- 회원권한값
  dstr_cd character varying(3),
  svc_theme_cd character varying(3),
  unit_svc_cd character varying(3),
  CONSTRAINT mbr_bas_pk PRIMARY KEY (mbr_seq)
)
COMMENT ON COLUMN mbr_bas.mbr_seq IS '회원기본 회원일련번호';
COMMENT ON COLUMN mbr_bas.mbr_id IS '회원기본 회원아이디';
COMMENT ON COLUMN mbr_bas.credential_id IS '회원기본 크리덴셜아이디';
COMMENT ON COLUMN mbr_bas.cret_dt IS '멤버기본 생성일시';
COMMENT ON COLUMN mbr_bas.user_nm IS '사용자명';
COMMENT ON COLUMN mbr_bas.tel_no IS '전화번호';
COMMENT ON COLUMN mbr_bas.email IS '이메일';
COMMENT ON COLUMN mbr_bas.user_tokn IS '사용자토큰';
COMMENT ON COLUMN mbr_bas.mbr_pwd IS '회원비밀번호';
COMMENT ON COLUMN mbr_bas.del_yn IS '삭제여부';
COMMENT ON COLUMN mbr_bas.mbr_clas IS '회원등급';
COMMENT ON COLUMN mbr_bas.mbr_role_val IS '회원권한값';
