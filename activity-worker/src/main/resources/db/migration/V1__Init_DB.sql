create sequence hibernate_sequence start 1 increment 1;

create table weather (
    id int8 not null,
    city_name varchar(255) not null,
    temperature float8 not null,
    creation_date varchar(255) not null,
    primary key (id)
);
