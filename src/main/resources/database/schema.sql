create sequence hibernate_sequence start with 1 increment by 1;
CREATE SEQUENCE VAS_NUMBER START WITH  1 INCREMENT BY 1;

create table product_config
(
    id         integer not null,
    amount     decimal(19, 2),
    product_id uuid,
    primary key (id)
);

create table quote
(
    id               uuid not null,
    amount           decimal(19, 2),
    currency         varchar(255),
    fee_amount       decimal(19, 2),
    product_id       uuid,
    reference_number varchar(255) not null,
    total_amount     decimal(19, 2),
    primary key (id)
);

ALTER TABLE QUOTE ALTER COLUMN REFERENCE_NUMBER SET DEFAULT 'VAS' || to_char(nextval('VAS_NUMBER'), '00000000000');