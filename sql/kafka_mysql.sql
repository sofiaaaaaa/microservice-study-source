create table users (
	id int auto_increment primary key,
	user_id varchar(20),
	pwd varchar(20),
	name varchar(20),
	created_at datetime default NOW()
)

select * from users;


insert into users(user_id, pwd, name) values ('users4', 'test111', 'aaa');


select * from orders;


select * from my_topic_users ;


create table orders (
	id int auto_increment primary key,
	product_id varchar(20) not null,
	qty int default 0,
	unit_price int default 0,
	total_price int default 0,
	user_id varchar(50) not null,
	order_id varchar(50) not null,
	created_at datetime default NOW());
)