insert into T_PERMISSION(PERMISSION, DESCRIPTION) values ('INDEX_VIEW', 'Permission to VIEW the index page');
insert into T_PERMISSION(PERMISSION, DESCRIPTION) values ('USERACCOUNT_VIEW', 'Permission to VIEW the user account overview page');
insert into T_PERMISSION(PERMISSION, DESCRIPTION) values ('EMPLOYEE_VIEW', 'Permission to VIEW the employee overview page');
insert into T_PERMISSION(PERMISSION, DESCRIPTION) values ('EMPLOYEE_CREATE', 'Permission to CREATE a new employee');
insert into T_PERMISSION(PERMISSION, DESCRIPTION) values ('EMPLOYEE_EDIT', 'Permission to EDIT an employee');
insert into T_PERMISSION(PERMISSION, DESCRIPTION) values ('EMPLOYEE_DELETE', 'Permission to DELETE an employee');
insert into T_PERMISSION(PERMISSION, DESCRIPTION) values ('DEPARTMENT_VIEW', 'Permission to VIEW the department overview page');
insert into T_PERMISSION(PERMISSION, DESCRIPTION) values ('DEPARTMENT_CREATE', 'Permission to CREATE a new department');
insert into T_PERMISSION(PERMISSION, DESCRIPTION) values ('DEPARTMENT_EDIT', 'Permission to EDIT a department');
insert into T_PERMISSION(PERMISSION, DESCRIPTION) values ('DEPARTMENT_DELETE', 'Permission to DELETE a department');
insert into T_PERMISSION(PERMISSION, DESCRIPTION) values ('VIEW_PROFILE', 'Permission to VIEW the profile');

insert into T_ROLE(ROLE, NAME) values ('ROLE_ADMIN', 'Administrator');
insert into T_ROLE(ROLE, NAME) values ('ROLE_VIEWER', 'Viewer');

insert into T_ROLE_PERMISSION(ID_ROLE, ID_PERMISSION) values (1, 1);
insert into T_ROLE_PERMISSION(ID_ROLE, ID_PERMISSION) values (1, 2);
insert into T_ROLE_PERMISSION(ID_ROLE, ID_PERMISSION) values (1, 3);
insert into T_ROLE_PERMISSION(ID_ROLE, ID_PERMISSION) values (1, 4);
insert into T_ROLE_PERMISSION(ID_ROLE, ID_PERMISSION) values (1, 5);
insert into T_ROLE_PERMISSION(ID_ROLE, ID_PERMISSION) values (1, 6);
insert into T_ROLE_PERMISSION(ID_ROLE, ID_PERMISSION) values (1, 7);
insert into T_ROLE_PERMISSION(ID_ROLE, ID_PERMISSION) values (1, 8);
insert into T_ROLE_PERMISSION(ID_ROLE, ID_PERMISSION) values (1, 9);
insert into T_ROLE_PERMISSION(ID_ROLE, ID_PERMISSION) values (1, 10);
insert into T_ROLE_PERMISSION(ID_ROLE, ID_PERMISSION) values (1, 11);

insert into T_ROLE_PERMISSION(ID_ROLE, ID_PERMISSION) values (2, 1);
insert into T_ROLE_PERMISSION(ID_ROLE, ID_PERMISSION) values (2, 3);
insert into T_ROLE_PERMISSION(ID_ROLE, ID_PERMISSION) values (2, 7);
insert into T_ROLE_PERMISSION(ID_ROLE, ID_PERMISSION) values (2, 11);

insert into T_USERACCOUNT(USERNAME, PASSWORD, ACTIVE, ID_ROLE) values ('admin', 'pass', 1, 1);
insert into T_USERACCOUNT(USERNAME, PASSWORD, ACTIVE, ID_ROLE) values ('user', 'pass', 1, 2);

insert into T_USERDATA(FIRSTNAME, LASTNAME, EMAIL, ID_USERACCOUNT) values ('Max', 'Musermann', 'max.mustermann@email.com', 1);
insert into T_USERDATA(FIRSTNAME, LASTNAME, EMAIL, ID_USERACCOUNT) values ('George', 'Georgescu', 'george.georgescu@email.com', 2);