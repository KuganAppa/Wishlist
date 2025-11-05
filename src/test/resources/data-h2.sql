MERGE INTO users (userName, email, password, dateOfBirth) KEY(email)
VALUES ('Osman Git', 'osman@github.com', 'osman123', '1993-10-21');

MERGE INTO users (userName, email, password, dateOfBirth) KEY(email)
VALUES ('Oskar Cola', 'oskar@cocacola.com', 'jegelskercola', '1953-05-03');

MERGE INTO users (userName, email, password, dateOfBirth) KEY(email)
VALUES ('Kugan Appa', 'kugan-appa@ceomail.com', 'skejs123', '1954-02-08');
