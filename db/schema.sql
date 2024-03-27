create table rabbit (
id serial primary key,
created_date timestamp
);

create table post (
id serial primary key,
name varchar (255),
text text,
link varchar (255) UNIQUE,
created timestamp
);