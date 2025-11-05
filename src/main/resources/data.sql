-- 1. Users
INSERT INTO users (userName, email, password, dateOfBirth)
VALUES ('Osman Git', 'osman@github.com', 'osman123', '1993-10-21'),
       ('Oskar Cola', 'oskar@cocacola.com', 'jegelskercola', '1953-05-03'),
       ('Kugan Appa', 'kugan-appa@ceomail.com', 'skejs123', '1954-02-08')
ON DUPLICATE KEY UPDATE userName    = VALUES(userName),
                        email       = VALUES(email),
                        password    = VALUES(password),
                        dateOfBirth = VALUES(dateOfBirth);

-- 2. Wishes
INSERT INTO wish (wishName, description, price, pictureLink, purchaseLink)
VALUES ('PlayStation 5', 'Spillekonsol', 4000.00, 'https://www.proshop.dk/Images/915x900/3213234_14b3a7833024.jpg', null),
       ('Barbie', 'Dukke', 200.00, 'https://m.media-amazon.com/images/I/71+I3hfh5mL._AC_UF894,1000_QL80_.jpg', null),
       ('iPhone 16', 'Smartphone', 8000.00, null, null),
       ('GitHub CoPilot', 'Coding Assistant', 100.00, null, null),
       ('Ramme Coca Cola', 'Drikkevarer', 69.00, null, null);

-- 3. Wishlist
INSERT INTO wishlist (wishlistName, description, ownerId)
VALUES ('Osmans fødselsdag', 'Osmans ønskeliste til hans fødselsdag', 1),
       ('Jul 2025 Oskar', 'Oskars ønskeliste til jul', 2);

-- 4. Wishlist wishes
INSERT INTO wishlist_wishes (wishlistId, wishId)
VALUES (1, 1)