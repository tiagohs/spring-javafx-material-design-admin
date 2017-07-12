INSERT INTO role(role_id, name, role) VALUES ('1', 'Administrador', 'ADMIN');
INSERT INTO role(role_id, name, role) VALUES ('2', 'USER', 'Usuário');
insert into user(user_id, active, email, password) values ('1', true, 'admin@admin.com', '$2a$06$mZ.3ieIoN2NArVuwbNmwjenNil3RlLgTnvgPZxdgIIsEbsBlcFOPK');
insert into user_role(user_id, role_id) values ('1', '1');