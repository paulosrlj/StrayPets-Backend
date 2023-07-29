set foreign_key_checks = 0;

delete from `stray-pets`.flyway_schema_history;
delete from `stray-pets`.photo;
delete from `stray-pets`.pet;
delete from `stray-pets`.location;
# delete from `stray-pets`.users;

set foreign_key_checks = 1;

insert into location (id, latitude, longitude, cep, street, sub_location, city)
values (1, -6.892701, -38.550474, '58900-000', 'Rua Julio Pajeu', 'Bairro Cristo Rei', 'Cajazeiras');

insert into location (id, latitude, longitude, cep, street, sub_location, city)
values (2, -6.892410, -38.549463, '58900-000', 'Rua Sinfrônio Gonçalves Braga', 'Bairro Cristo Rei', 'Cajazeiras');

insert into location (id, latitude, longitude, cep, street, sub_location, city)
values (3, -6.893139, -38.561127, '58900-000', 'Rua Padre José Tomás', 'Centro', 'Cajazeiras');

insert into location (id, latitude, longitude, cep, street, sub_location, city)
values (4, -6.889673, -38.545356, '58900-000', 'Rua José Leôncio da Silva', 'Loteamento Jardim Oasis', 'Cajazeiras');

insert into location (id, latitude, longitude, cep, street, sub_location, city)
values (5, -6.872976, -38.560661, '58900-000', 'Rua Raimundo Leite Rolim Sobrinho', 'Loteamento Casas Populares', 'Cajazeiras');

insert into pet (id, name, type, gender, breed, adoption_date, comments, location_id)
values (1, 'Bolinha', 'CACHORRO', 'MACHO', 'Labrador', null, '', 1);

insert into pet (id, name, type, gender, breed, adoption_date, comments, missing, location_id)
values (2, 'Peludinho', 'GATO', 'MACHO', 'Persa', null,
        'Eu perdi o meu gato dia 19/07, se alguem encontrar por favor telefonar para: (83) 99999-9999.',
        true, 2);

insert into pet (id, name, type, gender, breed, adoption_date, comments, location_id)
values (3, '', 'CACHORRO', null, 'CARAMELO', '2023-05-30', '', 3);

insert into pet (id, name, type, gender, breed, adoption_date, comments, location_id)
values (4, 'Layla', 'CACHORRO', 'FEMEA', '', null, 'Possui 5 filhotes', 4);

insert into pet (id, name, type, gender, breed, adoption_date, comments, location_id)
values (5, '', 'GATO', 'FEMEA', 'Spynx', null, '', 5);

# insert into pet (id, name, type, gender, breed, adoption_date, comments, location_id)
# values (6, 'Bolinha5', 'GATO', 'FEMEA', 'Spynx', '2023-07-20', 'Contato: (83) 99999-9999', 5);

insert into photo (id, photo_name, photo_uri, pet_id)
values (1, 'Foto 1', 'https://stray-pets.s3.sa-east-1.amazonaws.com/demo-pet-photos/labrador-caracteristicas-guia-racas.webp', 1);

insert into photo (id, photo_name, photo_uri, pet_id)
values (2, 'Foto 2', 'https://stray-pets.s3.sa-east-1.amazonaws.com/demo-pet-photos/labrador-guia-racas.webp', 1);

insert into photo (id, photo_name, photo_uri, pet_id)
values (3, 'Foto 1', 'https://stray-pets.s3.sa-east-1.amazonaws.com/demo-pet-photos/-3402.webp', 2);

insert into photo (id, photo_name, photo_uri, pet_id)
values (4, 'Foto 2', 'https://stray-pets.s3.sa-east-1.amazonaws.com/demo-pet-photos/gato-persa.jpg', 2);

insert into photo (id, photo_name, photo_uri, pet_id)
values (5, 'Foto 1', 'https://stray-pets.s3.sa-east-1.amazonaws.com/demo-pet-photos/2fymi85z5vo5pcl5rsnsr3xgi.webp', 3);

insert into photo (id, photo_name, photo_uri, pet_id)
values (6, 'Foto 1', 'https://stray-pets.s3.sa-east-1.amazonaws.com/demo-pet-photos/Cachorro_caramelo.jpg', 4);

insert into photo (id, photo_name, photo_uri, pet_id)
values (7, 'Foto 1', 'https://stray-pets.s3.sa-east-1.amazonaws.com/demo-pet-photos/download.jpg', 5);
