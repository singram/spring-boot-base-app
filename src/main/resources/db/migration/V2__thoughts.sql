CREATE TABLE IF NOT EXISTS thoughts (
	id         BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	person_id  BIGINT UNSIGNED,
	data       varchar(255) not null,
	creation_date DATE
);

