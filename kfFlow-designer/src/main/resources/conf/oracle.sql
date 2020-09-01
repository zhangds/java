create table T_WORKFLOW
(
	WK_ID NVARCHAR2(20) not null
		constraint PK_T_WORKFLOW
			primary key,
	WK_NAME NVARCHAR2(100),
	WK_REMARK NVARCHAR2(2000),
	CREATER_ID NVARCHAR2(20),
	CREATE_DT DATE,
	CURR_VERSION NVARCHAR2(10) default 'V1.0',
	DEF_FORM NVARCHAR2(100) default '',
	WK_STATE NVARCHAR2(2) default 'U',
	UPDATER_ID NVARCHAR2(20)
);
comment on column T_WORKFLOW.WK_ID is '流程编号';
comment on column T_WORKFLOW.WK_NAME is '流程名称';
comment on column T_WORKFLOW.WK_REMARK is '描述';
comment on column T_WORKFLOW.CREATER_ID is '创建者';
comment on column T_WORKFLOW.CREATE_DT is '创建时间';
comment on column T_WORKFLOW.CURR_VERSION is '版本号';
comment on column T_WORKFLOW.DEF_FORM is '默认绑定表单';
comment on column T_WORKFLOW.WK_STATE is '工作流状态，U修改，D发布，A新增';
comment on column T_WORKFLOW.UPDATER_ID is '修改者';

create table T_WORKFLOW_HIS
(
	WK_ID NVARCHAR2(20) not null,
	WK_NAME NVARCHAR2(100),
	WK_REMARK NVARCHAR2(2000),
	CREATER_ID NVARCHAR2(20),
	CREATE_DT DATE,
	CURR_VERSION NVARCHAR2(10) default 'V1.0',
	DEF_FORM NVARCHAR2(100) default '',
	WK_STATE NVARCHAR2(2) default 'U',
	UPDATER_ID NVARCHAR2(20)
);
comment on column T_WORKFLOW_HIS.WK_ID is '流程编号';
comment on column T_WORKFLOW_HIS.WK_NAME is '流程名称';
comment on column T_WORKFLOW_HIS.WK_REMARK is '描述';
comment on column T_WORKFLOW_HIS.CREATER_ID is '创建者';
comment on column T_WORKFLOW_HIS.CREATE_DT is '创建时间';
comment on column T_WORKFLOW_HIS.CURR_VERSION is '版本号';
comment on column T_WORKFLOW_HIS.DEF_FORM is '默认绑定表单';
comment on column T_WORKFLOW_HIS.WK_STATE is '工作流状态，U修改，D发布，A新增';
comment on column T_WORKFLOW_HIS.UPDATER_ID is '修改者';

create table T_WORKFLOW_ACT
(
	ACT_ID NVARCHAR2(20) not null,
	WK_ID NVARCHAR2(20) not null,
	ACT_NAME NVARCHAR2(200),
	PRE_NODE_ID NVARCHAR2(20),
	NEXT_NODE_ID NVARCHAR2(20),
	TYPE NVARCHAR2(10),
	constraint PK_T_WORKFLOW_ACT
		primary key (ACT_ID, WK_ID)
);
comment on column T_WORKFLOW_ACT.ACT_ID is '行为编号';
comment on column T_WORKFLOW_ACT.WK_ID is '流程编号';
comment on column T_WORKFLOW_ACT.ACT_NAME is '行为名称';
comment on column T_WORKFLOW_ACT.PRE_NODE_ID is '前节点编号';
comment on column T_WORKFLOW_ACT.NEXT_NODE_ID is '指向节点编号';
comment on column T_WORKFLOW_ACT.TYPE is '指向类型';
create table T_WORKFLOW_NODES
(
	NODE_ID NVARCHAR2(20) not null,
	WK_ID NVARCHAR2(20) not null,
	NODE_NAME NVARCHAR2(200),
	NODE_TYPE NVARCHAR2(30),
	TOP NUMBER(8),
	LEFT NUMBER(8),
	WIDTH NUMBER(8),
	HEIGHT NUMBER(8),
	constraint PK_T_WORKFLOW_NODES
		primary key (NODE_ID, WK_ID)
);
comment on table T_WORKFLOW_NODES is '工作流节点表';
comment on column T_WORKFLOW_NODES.NODE_ID is '节点编号';
comment on column T_WORKFLOW_NODES.WK_ID is '流程编号';
comment on column T_WORKFLOW_NODES.NODE_NAME is '节点名称';
comment on column T_WORKFLOW_NODES.NODE_TYPE is '节点类型';
comment on column T_WORKFLOW_NODES.TOP is '上距离';
comment on column T_WORKFLOW_NODES.LEFT is '左距离';
comment on column T_WORKFLOW_NODES.WIDTH is '节点宽度';
comment on column T_WORKFLOW_NODES.HEIGHT is '节点高度';
create table T_WORKFLOW_AREA
(
	AREA_ID NVARCHAR2(20) not null,
	WK_ID NVARCHAR2(20) not null,
	AREA_NAME NVARCHAR2(200),
	COLOR NVARCHAR2(20),
	TOP NUMBER(8),
	LEFT NUMBER(8),
	WIDTH NUMBER(8),
	HEIGHT NUMBER(8),
	constraint PK_T_WORKFLOW_AREA
		primary key (AREA_ID, WK_ID)
);
comment on table T_WORKFLOW_AREA is '工作流区域表';
create table T_WORKFLOW_WORKGROUPSET_NEW
(
	WK_ID NVARCHAR2(20) not null,
	NODE_ID NVARCHAR2(20) not null,
	GROUP_ID NVARCHAR2(32) not null,
	CREATER NVARCHAR2(32),
	CREATE_DATE DATE default sysdate not null,
	STATES NVARCHAR2(5) default '00A',
	constraint T_WORKFLOW_WORKGROUPSET_NEW_PK
		primary key (WK_ID, NODE_ID, GROUP_ID)
);
comment on table T_WORKFLOW_WORKGROUPSET_NEW is '节点工作组信息表。';
comment on column T_WORKFLOW_WORKGROUPSET_NEW.WK_ID is '流程id';
comment on column T_WORKFLOW_WORKGROUPSET_NEW.NODE_ID is '节点id';
comment on column T_WORKFLOW_WORKGROUPSET_NEW.GROUP_ID is '工作组id';
comment on column T_WORKFLOW_WORKGROUPSET_NEW.CREATER is '创建人';
comment on column T_WORKFLOW_WORKGROUPSET_NEW.CREATE_DATE is '创建时间';
comment on column T_WORKFLOW_WORKGROUPSET_NEW.STATES is '取值含义： 00A：正常 00X：作废';
create table T_WORKFLOW_RULE_NEW
(
	WK_ID NVARCHAR2(20) not null,
	NODE_ID NVARCHAR2(20) not null,
	OPTYPE NVARCHAR2(10) default 'ruleNode' not null,
	CREATER NVARCHAR2(32),
	CREATE_DATE DATE default sysdate not null,
	STATES NVARCHAR2(5) default '00A',
	RULEID NVARCHAR2(20) not null,
	RULEPID NVARCHAR2(20) not null,
	PARA1 NVARCHAR2(50),
	PARA2 NVARCHAR2(1024),
	PARA3 NVARCHAR2(1024),
	RULETYPE VARCHAR2(20) default 'PARAM' not null,
	ORTHER_ID VARCHAR2(70) default '' not null,
	constraint T_WORKFLOW_RULE_NEW_PK
		primary key (WK_ID, NODE_ID, OPTYPE, RULEID, RULEPID, ORTHER_ID)
);
comment on table T_WORKFLOW_RULE_NEW is '工作组规则信息表。';
comment on column T_WORKFLOW_RULE_NEW.WK_ID is '流程id';
comment on column T_WORKFLOW_RULE_NEW.NODE_ID is '节点id';
comment on column T_WORKFLOW_RULE_NEW.OPTYPE is '类型表：ruleNode:默认节点规则 ruleAct:行为规则 ';
comment on column T_WORKFLOW_RULE_NEW.CREATER is '创建人';
comment on column T_WORKFLOW_RULE_NEW.CREATE_DATE is '创建时间';
comment on column T_WORKFLOW_RULE_NEW.STATES is '取值含义： 00A：正常 00X：作废';
comment on column T_WORKFLOW_RULE_NEW.RULEID is '规则ID';
comment on column T_WORKFLOW_RULE_NEW.RULEPID is '规则PID';
comment on column T_WORKFLOW_RULE_NEW.PARA1 is '存储比较';
comment on column T_WORKFLOW_RULE_NEW.PARA2 is '存储值2';
comment on column T_WORKFLOW_RULE_NEW.PARA3 is '存储值3';
comment on column T_WORKFLOW_RULE_NEW.RULETYPE is '取值含义： PARAM：参数 CONDITION：条件  ACTION：行为';
comment on column T_WORKFLOW_RULE_NEW.ORTHER_ID is '另一个维度ID';
create table T_WORKFLOW_BACKFORMS_RULE
(
	WK_ID NVARCHAR2(20) not null,
	NODE_ID NVARCHAR2(20) not null,
	OPTYPE NVARCHAR2(10) default 'ruleBF' not null,
	BACKFORM_ID NVARCHAR2(40),
	ELE_ID NVARCHAR2(70) not null,
	ELE_NAME NVARCHAR2(70),
	SEE_ABLE NVARCHAR2(2) default 'Y',
	UPDATE_ABLE NVARCHAR2(2) default 'Y',
	MUST_ABLE NVARCHAR2(2) default 'Y',
	UPDATER NVARCHAR2(32),
	UPDATE_DATE DATE default sysdate not null,
	CREATER NVARCHAR2(32),
	CREATE_DATE DATE default sysdate not null,
	STATES NVARCHAR2(5) default '00A',
	constraint T_WORKFLOW_BACKFORMS_RULE_PK
		primary key (WK_ID, NODE_ID, OPTYPE, ELE_ID)
);
comment on table T_WORKFLOW_BACKFORMS_RULE is '回单模板信息设置表';
comment on column T_WORKFLOW_BACKFORMS_RULE.WK_ID is '工作流id';
comment on column T_WORKFLOW_BACKFORMS_RULE.NODE_ID is '节点编号';
comment on column T_WORKFLOW_BACKFORMS_RULE.OPTYPE is '类型表：ruleBF:回单模板 ruleAdd:定则类型';
comment on column T_WORKFLOW_BACKFORMS_RULE.BACKFORM_ID is '模板编号';
comment on column T_WORKFLOW_BACKFORMS_RULE.ELE_ID is '元素id';
comment on column T_WORKFLOW_BACKFORMS_RULE.SEE_ABLE is '可见状态 Y可见，N不可见';
comment on column T_WORKFLOW_BACKFORMS_RULE.UPDATE_ABLE is '可修改状态 Y可见，N不可见';
comment on column T_WORKFLOW_BACKFORMS_RULE.MUST_ABLE is '必填状态 Y可见，N不可见';
create table T_WORKFLOW_INIT
(
	INIT_ID VARCHAR2(40) not null,
	WK_ID VARCHAR2(20) not null,
	FORM_ID VARCHAR2(100) not null,
	STEP_ID VARCHAR2(50) not null,
	SERVICE_ID VARCHAR2(40) not null,
	GROUP_ID VARCHAR2(50) not null,
	PARENT_WK_ID VARCHAR2(40),
	CHILD_WK_ID VARCHAR2(40),
	USER_ID VARCHAR2(50),
	WK_TIME_LIMIT NUMBER default 100,
	STEP_TIME_LIMIT NUMBER default 50,
	URGENT_COUNT NUMBER default 0,
	CREATE_DT DATE default sysdate,
	STATES VARCHAR2(2) default 1,
	STATES_DT DATE default sysdate,
	BASEFORM_ID VARCHAR2(50),
	HIGHFORM_ID VARCHAR2(50),
	constraint T_WORKFLOW_INIT_PK
		primary key (INIT_ID, WK_ID, STEP_ID, SERVICE_ID, FORM_ID)
);
comment on table T_WORKFLOW_INIT is '工作流实例表';
comment on column T_WORKFLOW_INIT.INIT_ID is '流程实例编号';
comment on column T_WORKFLOW_INIT.WK_ID is '流程编号';
comment on column T_WORKFLOW_INIT.FORM_ID is '表单实例编号';
comment on column T_WORKFLOW_INIT.STEP_ID is '步骤编号';
comment on column T_WORKFLOW_INIT.SERVICE_ID is '服务请求编号';
comment on column T_WORKFLOW_INIT.GROUP_ID is '当前处理工作组';
comment on column T_WORKFLOW_INIT.PARENT_WK_ID is '父级流程实例编号';
comment on column T_WORKFLOW_INIT.CHILD_WK_ID is '子级流程实例编号';
comment on column T_WORKFLOW_INIT.USER_ID is '当前处理人编号';
comment on column T_WORKFLOW_INIT.WK_TIME_LIMIT is '工作流整体时限';
comment on column T_WORKFLOW_INIT.STEP_TIME_LIMIT is '当前节点时限';
comment on column T_WORKFLOW_INIT.URGENT_COUNT is '催单次数';
comment on column T_WORKFLOW_INIT.CREATE_DT is '启动工作流时间';
comment on column T_WORKFLOW_INIT.STATES is '工单状态：1为活动，0为结束';
comment on column T_WORKFLOW_INIT.STATES_DT is '状态更新时间';
comment on column T_WORKFLOW_INIT.BASEFORM_ID is '基础表单编号';
comment on column T_WORKFLOW_INIT.HIGHFORM_ID is '高级表单编号';
create table T_WORKFLOW_NODE_STEPLOG
(
	INIT_ID VARCHAR2(40) not null,
	FORMID VARCHAR2(100) not null,
	STEPID VARCHAR2(50) not null,
	USERID VARCHAR2(50),
	CREATE_DT DATE default sysdate not null,
	STATE VARCHAR2(50),
	TAG VARCHAR2(50),
	GROUPID VARCHAR2(100)
);
comment on table T_WORKFLOW_NODE_STEPLOG is '工作流步骤表';
comment on column T_WORKFLOW_NODE_STEPLOG.INIT_ID is '流程实例编号';
comment on column T_WORKFLOW_NODE_STEPLOG.FORMID is '工单实例编号，冗余';
comment on column T_WORKFLOW_NODE_STEPLOG.STEPID is '步骤编号';
comment on column T_WORKFLOW_NODE_STEPLOG.USERID is '操作人编号';
comment on column T_WORKFLOW_NODE_STEPLOG.CREATE_DT is '写入时间';
comment on column T_WORKFLOW_NODE_STEPLOG.STATE is '工单状态';
comment on column T_WORKFLOW_NODE_STEPLOG.TAG is '工单标识';
comment on column T_WORKFLOW_NODE_STEPLOG.GROUPID is '工作组id';
create table T_WORKFLOW_WBEXTEND_SET
(
	WK_ID NVARCHAR2(20) not null,
	NODE_ID NVARCHAR2(20) not null,
	SYSID NVARCHAR2(8) not null,
	CLASSZ NVARCHAR2(1000) not null,
	MOTHOD NVARCHAR2(100) not null,
	CREATE_DATE DATE default sysdate not null,
	STATES NVARCHAR2(5) default '00A',
	constraint T_WORKFLOW_WBEXTEND_SET_PK
		primary key (WK_ID, NODE_ID, SYSID)
);
comment on table T_WORKFLOW_WBEXTEND_SET is '节点外部接口信息表。';
comment on column T_WORKFLOW_WBEXTEND_SET.WK_ID is '流程id';
comment on column T_WORKFLOW_WBEXTEND_SET.NODE_ID is '节点id';
comment on column T_WORKFLOW_WBEXTEND_SET.SYSID is '外部系统id';
comment on column T_WORKFLOW_WBEXTEND_SET.CLASSZ is '执行类路径及类名';
comment on column T_WORKFLOW_WBEXTEND_SET.MOTHOD is '执行方法';
comment on column T_WORKFLOW_WBEXTEND_SET.STATES is '取值含义： 00A：正常 00X：作废';
create table T_GROUP_ATTR
(
	GROUP_ATTR_ID NUMBER(12) not null
		constraint PK_GROUP_ATTR_ID
			primary key,
	GROUP_ID VARCHAR2(32),
	ATTR_ID VARCHAR2(32),
	ATTR_VALUE VARCHAR2(100),
	CREATE_USER VARCHAR2(50),
	CREATE_DATE DATE,
	UPDATE_USER VARCHAR2(50),
	UPDATE_DATE DATE
);
comment on table T_GROUP_ATTR is '工作组属性表';
comment on column T_GROUP_ATTR.GROUP_ATTR_ID is '主键序列';
comment on column T_GROUP_ATTR.GROUP_ID is '组ID';
comment on column T_GROUP_ATTR.ATTR_ID is '属性ID对应基础工单元素ID';
comment on column T_GROUP_ATTR.ATTR_VALUE is '属性值';
comment on column T_GROUP_ATTR.CREATE_USER is '添加人';
comment on column T_GROUP_ATTR.CREATE_DATE is '添加时间';
comment on column T_GROUP_ATTR.UPDATE_USER is '修改人';
comment on column T_GROUP_ATTR.UPDATE_DATE is '修改时间';
create table T_WORKFLOW_WORKGROUP
(
	GROUP_ID NVARCHAR2(32) not null
		constraint PK_T_GROUP
			primary key,
	GROUP_NAME NVARCHAR2(100) not null,
	SUPER_ID NVARCHAR2(32),
	CITY_ID NVARCHAR2(10),
	STATUS NVARCHAR2(2) not null,
	DESCRIPTION NVARCHAR2(1000),
	CREATE_TIME DATE default SYSDATE not null,
	CREATER NVARCHAR2(32),
	MODIFY_TIME DATE default SYSDATE not null,
	MODIFIER NVARCHAR2(32),
	TYPEID VARCHAR2(32),
	STARTTIME DATE,
	ENDTIME DATE,
	DIRECTOR_CONF VARCHAR2(4000),
	SORTID VARCHAR2(32),
	IS_EXTERNAL VARCHAR2(50)
);
comment on table T_WORKFLOW_WORKGROUP is '工作组信息表，用于存储工作组信息。';
comment on column T_WORKFLOW_WORKGROUP.GROUP_ID is '工作组编号';
comment on column T_WORKFLOW_WORKGROUP.GROUP_NAME is '工作组名称';
comment on column T_WORKFLOW_WORKGROUP.SUPER_ID is '父级工作组编号';
comment on column T_WORKFLOW_WORKGROUP.CITY_ID is '所属地市编号';
comment on column T_WORKFLOW_WORKGROUP.STATUS is '取值含义：01：正常 02：停用 03：作废 04：锁定 05：解锁 06：失效 07：未启用';
comment on column T_WORKFLOW_WORKGROUP.DESCRIPTION is '工作组描述信息';
comment on column T_WORKFLOW_WORKGROUP.CREATE_TIME is '创建时间';
comment on column T_WORKFLOW_WORKGROUP.CREATER is '创建人';
comment on column T_WORKFLOW_WORKGROUP.MODIFY_TIME is '修改时间';
comment on column T_WORKFLOW_WORKGROUP.MODIFIER is '修改人';
comment on column T_WORKFLOW_WORKGROUP.TYPEID is '工作组类型';
comment on column T_WORKFLOW_WORKGROUP.STARTTIME is '开始时间';
comment on column T_WORKFLOW_WORKGROUP.ENDTIME is '失效时间';
comment on column T_WORKFLOW_WORKGROUP.DIRECTOR_CONF is '负责人配置Json字符串';
comment on column T_WORKFLOW_WORKGROUP.SORTID is '权重；用作排序';
comment on column T_WORKFLOW_WORKGROUP.IS_EXTERNAL is '是否为外部系统工作组; Y:是 N:不是';





