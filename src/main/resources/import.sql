insert into kitchen (name) values ('Brasileira');
insert into kitchen (name) values ('Japonesa');

insert into restaurant (name, freight_rate, kitchen_id) values ('House 630', 10, 1);
insert into restaurant (name, freight_rate, kitchen_id) values ('Ravenna', 9.5, 1);
insert into restaurant (name, freight_rate, kitchen_id) values ('Kyodai', 15, 2);

insert into payment_method (description) values ('Cartão de crédito');
insert into payment_method (description) values ('Cartão de débito');
insert into payment_method (description) values ('PIX');
insert into payment_method (description) values ('Dinheiro');

insert into permission (name, description) values ('Permissão 1', 'Descrição da permissão 1');
insert into permission (name, description) values ('Permissão 2', 'Descrição da permissão 2');

insert into state (name) values ('São Paulo');
insert into state (name) values ('Paraná');

insert into city (name, state_id) values ('Marília', 1);
insert into city (name, state_id) values ('Londrina', 2);