-- begin UA_TASK
create table UA_TASK (
    ID varchar(36) not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    VERSION integer,
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    DESCRIPTION varchar(255),
    ASSIGNEE_ID varchar(36) not null,
    --
    primary key (ID)
)^
-- end UA_TASK
-- begin SEC_USER
alter table SEC_USER add column IMAGE_ID varchar(36) ^
alter table SEC_USER add column DTYPE varchar(100) ^
update SEC_USER set DTYPE = 'ua$UserExt' where DTYPE is null ^
-- end SEC_USER
