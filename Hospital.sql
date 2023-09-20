update user_role set roles='ADMIN' where user_id=1

select * from bed;
select * from palat;
select * from hospital;
select * from laboratory;
select * from policlinic;

insert into bed(number_bed) VALUES
(11),
(22),
(33);

insert into palat(number_palat,bed_id)VALUES
(11,1),
(22, 2),
(33, 3);
