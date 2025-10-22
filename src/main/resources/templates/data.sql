INSERT INTO wish (wishName, description, price, pictureLink, purchaseLink)
VALUES ('PlayStation 5', 'Spillekonsol', '4000', null, null),
       ('Barbie', 'Dukke', '200', null, null),
       ('iPhone 16', 'Smartphone', '8000', null, null),
       ('GitHub CoPilot', 'Coding Assistant', '100', null, null),
       ('Ramme Coca Cola', 'Drikkevarer', '69', null, null)

INSERT INTO wishList (wishListName, ownerName, description, wishes)
VALUES ('Fødselsdagsønsker', 'Osman Git', 'Osmans ønskeliste til hans fødselsdag', 'GitHub CoPilot, PlayStation 5, Barbie'),
       ('Juleønsker', 'Oskar Cola', 'Ved i hvad farve julemanden er? Samme farve som coca cola', 'Ramme Coca Cola'),
       ('Mor og far skilsmisse ønsker', 'Osman daddy', 'Mette Minkdraeber', '')

INSERT INTO user (name, email, password, dateOfBirth)
VALUES ('Osman Git', 'osman@github.com', 'osman123', 21-10-1993),
       ('Oskar Cola', 'oskar@cocacola.com', 'jegelskercola', 05-03-1953),
       ('Kugan Appa', 'kugan-appa@ceomail.com', 'skejs123', 02-08-1954)