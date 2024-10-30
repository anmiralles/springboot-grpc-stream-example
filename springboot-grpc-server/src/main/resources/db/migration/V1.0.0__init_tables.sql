drop table if exists category;

create table category
(
    id SERIAL,
    category integer,
    name  varchar(55),
    description  varchar(255),
    PRIMARY KEY (id)
);

create index category_ind_01 ON category (id);

insert into category(category, name, description)
values (1,'category_A', 'category_A');
insert into category(category, name, description)
values (2,'category_B', 'category_B');
insert into category(category, name, description)
values (3,'category_C', 'category_C');
