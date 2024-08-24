CREATE DATABASE ChatApplication

USE ChatApplication

Create table UserAccount
(
	phoneNumber varchar(10) primary key,
	password varchar(50),
	name nvarchar(50),
	imageUrl varchar(255) default 'icons8-account-50.png',
	status int default 0,
)

CREATE TABLE Chat
(
	sender_id varchar(10), 
	receiver_id varchar(10), 
	timestamp datetime default getdate(),
	message ntext,
	constraint pk_chat primary key (sender_id, receiver_id, timestamp),
	constraint fk_sender_user foreign key (sender_id) references UserAccount(phoneNumber),
	constraint fk_receiver_chat foreign key (receiver_id) references UserAccount(phoneNumber)
)

insert into UserAccount (phoneNumber, password, name, imageUrl)
values 
('0773812345','123456', N'Trương Ngọc Ánh', 'Truong_Ngoc_Anh_1.jpg'),
('0908123456','123456', N'Lệ Hoa', 'Anh_h4.jpg'),
('0908789123','123456', N'Lan Anh', 'bhlc.jpg')

select * from UserAccount

insert into Chat(sender_id, receiver_id, message)
values ('0773812345','0908123456', N'Lorem ipsum dolor sit amet, consectetur adipiscing elit.');
insert into Chat(sender_id, receiver_id, message)
values ('0773812345','0908123456', N'Mauris feugiat felis vel commodo congue.');
insert into Chat(sender_id, receiver_id, message)
values ('0908123456','0773812345', N'Quisque tortor velit, aliquet eget neque ut, ornare posuere est.');
insert into Chat(sender_id, receiver_id, message)
values ('0773812345','0908123456', N'Pellentesque vitae finibus felis, eu cursus lectus.');
insert into Chat(sender_id, receiver_id, message)
values ('0908123456','0773812345', N'Vestibulum venenatis vitae libero non facilisis.');
insert into Chat(sender_id, receiver_id, message)
values ('0908123456','0908789123', N'Nam gravida sem nulla, non tincidunt nunc tristique at.');

select * from Chat

update UserAccount
set status = 0

--DELETE Chat
--DELETE UserAccount

--DROP TABLE Chat
--DROP TABLE UserAccount