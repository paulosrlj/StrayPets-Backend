CREATE TABLE users
(
    id       bigint       not null auto_increment,
    email    varchar(100) NOT NULL,
    password varchar(100) NOT NULL,
    role     varchar(50)  NOT NULL,
    deactivated boolean DEFAULT FALSE,

    PRIMARY key (id)
);