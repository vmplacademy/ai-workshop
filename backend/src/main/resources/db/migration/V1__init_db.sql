-- backend/src/main/resources/db/migration/V1__init_db.sql
CREATE DATABASE todolist_db;

CREATE USER main_user WITH ENCRYPTED PASSWORD 'main_user_pasword';

GRANT ALL PRIVILEGES ON DATABASE todolist_db TO main_user;