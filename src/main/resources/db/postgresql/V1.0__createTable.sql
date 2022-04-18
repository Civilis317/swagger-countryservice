
create table country
(
    alpha2code   varchar(2)   not null,
    active       boolean      not null,
    dutch_name   varchar(255) not null,
    english_name varchar(255) not null,
    french_name  varchar(255) not null,
    primary key (alpha2code)
)