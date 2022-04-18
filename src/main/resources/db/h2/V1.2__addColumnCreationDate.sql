alter table country add creation_date timestamp;

update country set creation_date = CURRENT_TIMESTAMP();

alter table country alter creation_date set not null;
