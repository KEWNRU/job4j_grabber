CREATE TABLE company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);
CREATE TABLE person
(
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

INSERT INTO company (id, name) values (1, 'LG');
INSERT INTO company (id, name) values (2, 'samsung');
INSERT INTO company (id, name) values (3, 'apple');
INSERT INTO company (id, name) values (4, 'lenovo');
INSERT INTO company (id, name) values (5, 'yadmilk');

INSERT INTO person (id, name, company_id) values (1, 'Сергей', 1);
INSERT INTO person (id, name, company_id) values (2, 'Ваня', 2);
INSERT INTO person (id, name, company_id) values (3, 'Вова', 3);
INSERT INTO person (id, name, company_id) values (4, 'Наташа', 4);
INSERT INTO person (id, name, company_id) values (5, 'Кирилл', 4);

select p.name as "Имя человека",
c.name as "Название компании" from person as p
    inner join company as c
    on p.company_id = c.id
    where c.id != 5;

select  c.name as "Название компании",
count(p.name) as "Кол-во человек"
from company c inner join person p
on p.company_id = c.id
group by c.name, p.company_id
having count(p.company_id) in (select  max(n)
from (select p.company_id, count (p.company_id) as n
from person p group by p.company_id));