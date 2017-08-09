INSERT OR IGNORE INTO role (role_id, name, role) VALUES("1", "Administrador", "ADMIN");
INSERT OR IGNORE INTO role (role_id, name, role) VALUES("2", "Usuário", "USER");
INSERT OR IGNORE INTO user (user_id, is_login, email, name, password) VALUES("1", "False", "admin@admin.com", "Tiago", "123");
INSERT OR IGNORE INTO user_role (user_id, role_id) VALUES("1", "1");
INSERT OR IGNORE INTO language (language_id, language_name, language_code, country_code, is_default) VALUES("1", "Português Brasil", "pt", "BR", "true");
INSERT OR IGNORE INTO language (language_id, language_name, language_code, country_code, is_default) VALUES("2", "Ingês", "en", "US", "false");