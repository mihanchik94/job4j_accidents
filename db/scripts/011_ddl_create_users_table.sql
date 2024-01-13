create table users (
    id serial primary key,
    username varchar(50) not null unique,
    password varchar(100) not null,
    enabled boolean default true,
    authority_id int references authorities(id)
);