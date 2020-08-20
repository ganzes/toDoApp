CREATE TABLE toDo (
    id int unsigned primary key auto_increment,
    text varchar (100) not null,
    done bit
);

insert into toDo (text, done) values ('Done toDo', 1);
insert into toDo (text, done) values ('Undone toDo', 0);