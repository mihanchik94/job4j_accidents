insert into users (username, password, enabled, authority_id) values ('root',
        '$2a$10$q6lPp/afJFJb3cBEjU7lSetbno26Jovr2DsIkZt62So4i30nM1Ipm',
         true, (select id from authorities where authority = 'ROLE_ADMIN'));