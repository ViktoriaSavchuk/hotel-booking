CREATE TABLE IF NOT EXISTS rooms  (
	room_number INT NOT NULL PRIMARY KEY ,
	category VARCHAR(100)NOT NULL ,
	price FLOAT NOT NULL
);

CREATE TABLE IF NOT EXISTS users  (
	user_id INT AUTO_INCREMENT NOT NULL PRIMARY KEY ,
	name VARCHAR(100) NOT NULL,
	surname VARCHAR(100) NOT NULL,
	phone VARCHAR(20) NOT NULL
) ;

CREATE TABLE IF NOT EXISTS reservations  (
        reserve_id INT AUTO_INCREMENT NOT NULL PRIMARY KEY ,
        user_id INT NOT NULL ,
        room_number INT NOT NULL,
        reserve_date DATE NOT NULL,
        breakfast BOOLEAN NOT NULL,
	cleaning BOOLEAN NOT NULL,
        order_price FLOAT NOT NULL,
        constraint user_fk foreign key (user_id)
	references users(user_id),
        constraint room_fk foreign key (room_number)
	references rooms (room_number)

) ;


INSERT INTO rooms (room_number,category,price) VALUES (1,'econom', 400.0);  
INSERT INTO rooms (room_number,category,price) VALUES (2,'standart', 600.0); 
INSERT INTO rooms (room_number,category,price) VALUES (3,'econom', 400.0); 
INSERT INTO rooms (room_number,category,price) VALUES (4,'econom', 400.0); 
INSERT INTO rooms (room_number,category,price) VALUES (5,'vip', 1000.0); 

INSERT INTO users (user_id,name,surname,phone)VALUES (1,'name1', 'surname1', '067 067 067 67'); 
INSERT INTO users (user_id,name,surname,phone)VALUES (2,'name2', 'surname2', '068 068 068 68');


INSERT INTO RESERVATIONS (reserve_id, user_id,room_number,reserve_date,breakfast, cleaning, order_price)VALUES (1,1,2,'2018-06-06', true, true, 900.0)


