USE todo;
create table todo (
    todo_id varchar(36) primary key,
    todo_title varchar(30),
    finished boolean,
    created_at timestamp
)
