create table student (
	id   bigint primary key generated always as identity (start with 1, increment by 1),  
	name varchar(1000),  
	age  integer
)