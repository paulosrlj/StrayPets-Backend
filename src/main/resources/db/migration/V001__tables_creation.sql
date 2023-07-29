CREATE TABLE location
(
    id           bigint        not null auto_increment,
    latitude     DECIMAL(9, 6) NOT NULL,
    longitude    DECIMAL(9, 6) NOT NULL,

    cep          varchar(30)   NOT NULL,
    street       VARCHAR(150),
    sub_location varchar(150)  not null,
    city         varchar(150),

    PRIMARY key (id)
);

CREATE TABLE pet
(
    id            bigint      not null auto_increment,
    name          varchar(100),
    type          varchar(30) NOT null,
    gender        varchar(15),
    breed         varchar(30),
    adoption_date date,
    find_date     date,
    comments      varchar(250),
    missing       boolean DEFAULT FALSE,

    location_id   bigint      not null,

    PRIMARY key (id),
    CONSTRAINT FK_PET_LOCATION FOREIGN KEY (location_id) REFERENCES location (id)
);


CREATE TABLE photo
(
    id         bigint       not null auto_increment,
    photo_name varchar(100) NOT NULL,
    photo_uri  text,
    pet_id     bigint       NOT NULL,

    PRIMARY key (id),
    CONSTRAINT FK_PET_PHOTO FOREIGN KEY (pet_id) REFERENCES pet (id)
);

