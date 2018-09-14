use library;

create table if not exists author (
  id_author   bigint(20)   not null unique,
  first_name  varchar(255) not null,
  last_name   varchar(255) not null,
  birth_place varchar(255),
  primary key (id_author)
);

create table if not exists books (
id_book      bigint(20)   not null unique,
borrow       bit(1)       not null,
category     varchar(255) not null,
isbn         varchar(13),
pages        int(11),
release_date date,
summary      varchar(350),
title        varchar(255) not null,
author_id    bigint(20)   not null,
primary key (id_book)
);

create table if not exists authors_books (
  author_id bigint(20) not null,
  book_id   bigint(20) not null,
  foreign key (author_id) references author (id_author),
  foreign key (book_id) references books (id_book)
);

create table if not exists borrower_details (
  id_borrower_details bigint(20)   not null unique,
  address             varchar(255) not null,
  email               varchar(255),
  phone               varchar(255),
  primary key (id_borrower_details)
);

create table if not exists borrowers (
  id_borrower         bigint(20)   not null unique,
  first_name          varchar(255) not null,
  last_name           varchar(255) not null,
  borrower_details_id bigint(20)   not null,
  primary key (id_borrower),
  foreign key (borrower_details_id) references borrower_details (id_borrower_details)
);

create table if not exists borrow (
  id_borrow   bigint(20) not null unique,
  book_id     bigint(20) not null,
  borrower_id bigint(20) not null,
  rental_date date       not null,
  primary key (id_borrow),
  foreign key (book_id) references books (id_book),
  foreign key (borrower_id) references borrowers (id_borrower)
);