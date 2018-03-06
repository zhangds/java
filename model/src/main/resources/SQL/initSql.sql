create table user_user
(
	login_id nvarchar(32) not null,
	login_name nvarchar(128) not null,
	login_password nvarchar(128) not null,
	login_status nvarchar(2),
	mobile nvarchar(16),
	email nvarchar(64),
	sex nvarchar(2),
  	create_user_id nvarchar(32),
	CREATE_time timestamp
)ENGINE=InnoDB DEFAULT CHARSET=utf8;;

CREATE TABLE USER_ROLE(
  ROLE_ID NVARCHAR(32) NOT NULL,
  PARENT_ROLE_ID NVARCHAR(32),
  ROLE_NAME NVARCHAR(128),
  CREATE_USER_ID NVARCHAR(32),
  CREATE_TIME timestamp,
  ROLE_INDEX int DEFAULT 1
)ENGINE=InnoDB DEFAULT CHARSET=utf8;;

CREATE TABLE USER_ROLE_MAP(
  ROLE_ID NVARCHAR(32),
  USER_ID NVARCHAR(32),
  CREATE_USER_ID NVARCHAR(32),
  CREATE_TIME timestamp
)ENGINE=InnoDB DEFAULT CHARSET=utf8;;