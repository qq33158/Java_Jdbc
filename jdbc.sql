Drop DATABASE IF EXISTS jdbc;

CREATE DATABASE IF NOT EXISTS jdbc;

use jdbc;

CREATE TABLE Users(
	id int not null primary key auto_increment,
    username varchar(50) not null,
    password varchar(50) not null
);

CREATE TABLE number(
	id int not null primary key auto_increment,
    number1 varchar(50) not null,
    number2 varchar(50) not null,
	number3 varchar(50) not null,
	number4 varchar(50) not null,
	number5 varchar(50) not null,
	number6 varchar(50) not null
);

INSERT INTO Users(username,password) VALUES ('Jdbc','test123');

SELECT * FROM Users;

SELECT * FROM number;


