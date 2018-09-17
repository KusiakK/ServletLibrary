use library;
insert into authors (birth_place, first_name, last_name)
VALUES ('Wachock', 'Stefan', 'Kraweznik');
insert into authors (birth_place, first_name, last_name)
VALUES ('', 'J.R.R', 'Tolkien');
insert into books (category, isbn, pages, release_date, title, author_id_author, summary)
VALUES ('Fantasy',
        9780261102385,
        1216,
        '2008-04-01',
        'The Lord of the Rings',
        2,
        'A fellowship of hobbits, elves, dwarfs, and men is formed to destroy the ring by casting it into the volcanic fires of the Crack of Doom, where it was forged. They are opposed on their harrowing mission by the evil Sauron and his Black Riders.');
insert into books (category, isbn, pages, release_date, title, author_id_author, summary)
VALUES ('Fantasy',
        9780261102217,
        400,
        '1991-03-20',
        'The Hobbit : or There and Back Again',
        2,
        'The Hobbit is a tale of high adventure.');