INSERT OR IGNORE INTO role (name, role) VALUES("Administrador", "ADMIN");
INSERT OR IGNORE INTO role (name, role) VALUES("Usuário", "USER");
INSERT OR IGNORE INTO user (active, email, name, password) VALUES("True", "admin@admin.com", "Tiago", "123");
INSERT OR IGNORE INTO user_role (user_id, role_id) VALUES("1", "1");