CREATE TABLE IF NOT EXISTS user(
	id int not null auto_increment,
    permission int not null,
    name varchar(255) not null,
    password varchar(255) not null,
    primary key (id)
);

CREATE TABLE IF NOT EXISTS permission(
	type int not null auto_increment,
    primary key (type)
);

CREATE TABLE IF NOT EXISTS user_achievement(
	id int not null auto_increment,
    user int not null,
    points int,
    description text,
    primary key (id)
);

CREATE TABLE IF NOT EXISTS profile_link(
	id int not null auto_increment,
    user int not null,
    type varchar(255),
    url varchar(255),
    primary key (id)
);

CREATE TABLE IF NOT EXISTS project_to_user(
	id int not null auto_increment,
    user int not null,
    project int not null,
    primary key (id)
);

CREATE TABLE IF NOT EXISTS project(
	id int not null auto_increment,
    name varchar(255) not null,
    description text,
    primary key (id)
);

CREATE TABLE IF NOT EXISTS task(
	id int not null auto_increment,
    project int not null,
    points int,
    description text,
    due_date date,
    primary key (id)
);

CREATE TABLE IF NOT EXISTS project_achievement(
	id int not null auto_increment,
    project int not null,
    points int,
    description text,
    primary key (id)
);

ALTER TABLE user
ADD FOREIGN KEY fk_permission(permission)
REFERENCES permission(type)
ON DELETE NO ACTION
ON UPDATE CASCADE;

ALTER TABLE user_achievement
ADD FOREIGN KEY fk_user(user)
REFERENCES user(id)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE profile_link
ADD FOREIGN KEY fk_user(user)
REFERENCES user(id)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE project_to_user
ADD FOREIGN KEY fk_user(user)
REFERENCES user(id)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE project_to_user
ADD FOREIGN KEY fk_project(project)
REFERENCES project(id)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE task
ADD FOREIGN KEY fk_project(project)
REFERENCES project(id)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE project_achievement
ADD FOREIGN KEY fk_project(project)
REFERENCES project(id)
ON DELETE CASCADE
ON UPDATE CASCADE;









