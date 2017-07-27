INSERT OR IGNORE INTO role (role_id, name, role) VALUES("1", "Administrador", "ADMIN");
INSERT OR IGNORE INTO role (role_id, name, role) VALUES("2", "Usuário", "USER");
INSERT OR IGNORE INTO user (user_id, active, email, name, password) VALUES("1", "True", "admin@admin.com", "Tiago", "123");
INSERT OR IGNORE INTO user_role (user_id, role_id) VALUES("1", "1");