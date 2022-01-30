create table t_department
(
    id   integer auto_increment,
    code varchar(10),
    name varchar(10),
    primary key (id)
);

create table t_employee
(
    id             integer auto_increment,
    firstname      varchar(50),
    lastname       varchar(50),
    monthly_salary integer,
    id_department  integer,
    primary key (id),
    foreign key (id_department) references t_department (id)
);

