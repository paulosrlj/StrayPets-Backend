set foreign_key_checks = 0;

delete from `stray-pets`.photo;
delete from `stray-pets`.location;
delete from `stray-pets`.pet;

set foreign_key_checks = 1;

insert into location (id, latitude, longitude, cep, logradouro, bairro, cidade)
values (1, 3.7535, 4.679521, '585612332', 'Rua Quatro', 'Alameda', 'Cajazeiras');

insert into location (id, latitude, longitude, cep, logradouro, bairro, cidade)
values (2, 3.7535, 4.679521, '585612332', 'Rua Quatro', 'Cristo Rei', 'Cajazeiras');

insert into location (id, latitude, longitude, cep, logradouro, bairro, cidade)
values (3, 3.7535, 4.679521, '585612332', 'Rua Juvencio', 'Golden Park', 'Cajazeiras');

insert into location (id, latitude, longitude, cep, logradouro, bairro, cidade)
values (4, 3.7535, 4.679521, '585612332', 'Rua Quatro', 'Golden Park', 'Cajazeiras');

insert into location (id, latitude, longitude, cep, logradouro, bairro, cidade)
values (5, 3.7535, 4.679521, '585612332', 'Rua Alameda', 'Selecta', 'Cajazeiras');

insert into pet (id, name, type, gender, breed, adoption_date, comments, location_id)
values (1, 'Bolinha', 'CACHORRO', 'MACHO', 'Poddle', '2023-07-20', 'Contato: (83) 99999-9999', 1);

insert into pet (id, name, type, gender, breed, adoption_date, comments, location_id)
values (2, 'Bolinha2', 'GATO', 'MACHO', 'Persa', '2023-07-20', 'Contato: (83) 99999-9999', 2);

insert into pet (id, name, type, gender, breed, adoption_date, comments, location_id)
values (3, 'Bolinha3', 'GATO', 'FEMEA', 'Siames', '2023-05-30', 'Contato: (83) 99999-9999', 2);

insert into pet (id, name, type, gender, breed, adoption_date, comments, location_id)
values (4, 'Bolinha4', 'CACHORRO', 'FEMEA', 'Pastor alemão', '2022-07-20', 'Contato: (83) 99999-9999', 3);

insert into pet (id, name, type, gender, breed, adoption_date, comments, location_id)
values (5, 'Bolinha5', 'CACHORRO', 'MACHO', 'Poddle', '2023-07-20', 'Contato: (83) 99999-9999', 4);

insert into pet (id, name, type, gender, breed, adoption_date, comments, location_id)
values (6, 'Bolinha5', 'GATO', 'FEMEA', 'Spynx', '2023-07-20', 'Contato: (83) 99999-9999', 5);

insert into photo (id, photo_name, photo_uri, pet_id)
values (1, 'Foto 1', 'http://aws.com.br', 1);