insert into kitchen (id, name) values (1, 'Brasileira');
insert into kitchen (id, name) values (2, 'Japonesa');

insert into restaurant (id, name, freight_rate, kitchen_id) values (1, 'House 630', 10, 1);
insert into restaurant (id, name, freight_rate, kitchen_id) values (2, 'Ravenna', 9.5, 1);
insert into restaurant (id, name, freight_rate, kitchen_id) values (3, 'Kyodai', 15, 2);

insert into payment_method (id, description) values (1, 'Cartão de crédito');
insert into payment_method (id, description) values (2, 'Cartão de débito');
insert into payment_method (id, description) values (3, 'PIX');
insert into payment_method (id, description) values (4, 'Dinheiro');

insert into permission (id, name, description) values (1, 'Permissão 1', 'Descrição da permissão 1');
insert into permission (id, name, description) values (2, 'Permissão 2', 'Descrição da permissão 2');

insert into state (id, name) values (1, 'São Paulo');
insert into state (id, name) values (2, 'Paraná');

insert into city (id, name, state_id) values (1, 'Marília', 1);
insert into city (id, name, state_id) values (2, 'Londrina', 2);