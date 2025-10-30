insert into cozinha (id, nome) values (1, 'Tailandesa');
insert into cozinha (id, nome) values (2, 'Indiana');

insert into restaurante (id, nome, taxa_frete, cozinha_id) values (1, 'Comida Caseira', '10.00', 1)
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (2, 'Fast Food', '20.00', 1)
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (3, 'Pizzaria', '40.00', 2)
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (4, 'Comida Italiana', '30.00', 2)

insert into estado (id, nome) values (1, 'Paraná');
insert into estado (id, nome) values (2, 'São Paulo');
insert into estado (id, nome) values (3, 'Minas Gerais');

insert into cidade (id, nome, estado_id) values (1, 'Curitiba', 1);
insert into cidade (id, nome, estado_id) values (2, 'Londrina', 1);
insert into cidade (id, nome, estado_id) values (3, 'Maringá', 1);
insert into cidade (id, nome, estado_id) values (4, 'São Paulo', 2);
insert into cidade (id, nome, estado_id) values (5, 'Campinas', 2);
insert into cidade (id, nome, estado_id) values (6, 'Belo Horizonte', 3);
insert into cidade (id, nome, estado_id) values (7, 'Uberlândia', 3);

insert into forma_pagamento (id, descricao) values (1, 'Cartão de crédito');
insert into forma_pagamento (id, descricao) values (2, 'Cartão de débito');
insert into forma_pagamento (id, descricao) values (3, 'Dinheiro');

insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissao (id, nome, descricao) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');
