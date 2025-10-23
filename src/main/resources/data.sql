-- 1. Users først
INSERT INTO users (userName, email, password, dateOfBirth)
VALUES ('Osman Git', 'osman@github.com', 'osman123', '1993-10-21'),
       ('Oskar Cola', 'oskar@cocacola.com', 'jegelskercola', '1953-05-03'),
       ('Kugan Appa', 'kugan-appa@ceomail.com', 'skejs123', '1954-02-08')
        ON DUPLICATE KEY UPDATE userName=userName;

-- 2. Wishes
INSERT INTO wish (wishName, description, price, pictureLink, purchaseLink)
VALUES ('PlayStation 5', 'Spillekonsol', 4000.00, null, null),
       ('Barbie', 'Dukke', 200.00, null, null),
       ('iPhone 16', 'Smartphone', 8000.00, null, null),
       ('GitHub CoPilot', 'Coding Assistant', 100.00, null, null),
       ('Ramme Coca Cola', 'Drikkevarer', 69.00, null, null);

-- 3. Wishlist hej test
INSERT INTO wishlist (wishlistName, description, ownerName, ownerId, wishes)
VALUES ('Fødselsdagsønsker','Osmans ønskeliste til hans fødselsdag', 'Osman Git', 1,'GitHub CoPilot,PlayStation 5,Barbie');
