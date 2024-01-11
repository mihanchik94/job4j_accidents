create table users (
    username VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    enabled boolean default true,
    primary key(username)
);