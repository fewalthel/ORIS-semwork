/*drop database oris-semwork;*/

/*create database oris-semwork;

create table users ( id serial primary key,
                     email varchar(50) not null unique ,
                     username varchar(50) not null unique ,
                     password varchar(50) not null );

create table questions ( id serial primary key,
                         title varchar(50) not null,
                         description varchar(200) not null,
                         author varchar(50) not null,
                         question_id integer,
                         foreign key (question_id) references users(id231));

create table users_questions ( user_id integer,
                               question_id integer,
                               foreign key (user_id) references users(id),
                               foreign key (question_id) references questions(id));

create table comments ( );

*/

