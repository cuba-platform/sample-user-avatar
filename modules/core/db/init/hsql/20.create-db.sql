-- begin UA_TASK
alter table UA_TASK add constraint FK_UA_TASK_ASSIGNEE_ID foreign key (ASSIGNEE_ID) references SEC_USER(ID)^
create index IDX_UA_TASK_ASSIGNEE on UA_TASK (ASSIGNEE_ID)^
-- end UA_TASK
-- begin SEC_USER
alter table SEC_USER add constraint FK_SEC_USER_IMAGE_ID foreign key (IMAGE_ID) references SYS_FILE(ID)^
create index IDX_SEC_USER_IMAGE on SEC_USER (IMAGE_ID)^
-- end SEC_USER
