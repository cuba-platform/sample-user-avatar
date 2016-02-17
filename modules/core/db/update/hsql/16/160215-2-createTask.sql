alter table UA_TASK add constraint FK_UA_TASK_ASSIGNEE_ID foreign key (ASSIGNEE_ID) references SEC_USER(ID);
create index IDX_UA_TASK_ASSIGNEE on UA_TASK (ASSIGNEE_ID);
