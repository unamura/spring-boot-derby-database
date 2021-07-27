DROP Table user_entity;

CREATE TABLE user_entity ( id INT not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	first_name varchar(50),
	last_name varchar(50)
	);