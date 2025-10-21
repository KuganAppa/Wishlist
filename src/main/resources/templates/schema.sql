-- CREATE TABLE IF NOT EXISTS tag (
--     id INT AUTO_INCREMENT PRIMARY KEY,
--     name VARCHAR(50) UNIQUE NOT NULL
-- );
--
-- CREATE TABLE IF NOT EXISTS tourist_attraction (
--     id INT AUTO_INCREMENT PRIMARY KEY,
--     name VARCHAR(100) NOT NULL,
--     description TEXT,
--     city VARCHAR(100)
-- );
--
-- CREATE TABLE IF NOT EXISTS attraction_tag (
--     attraction_id INT NOT NULL,
--     tag_id INT NOT NULL,
--     PRIMARY KEY (attraction_id, tag_id),
--     FOREIGN KEY (attraction_id) REFERENCES tourist_attraction(id) ON DELETE CASCADE,
--     FOREIGN KEY (tag_id) REFERENCES tag(id)
-- );


CREATE TABLE IF NOT EXIST wish (
    wishId INT AUTO_INCREMENT PRIMARY KEY,
    wishName VARCHAR(50) UNIQUE NOT NULL,
    description TEXT,
    price FLOAT(1000,2),
    pictureLink TEXT,
    purchaselink TEXT
);

CREATE TABLE IF NOT EXIST wishlist(

);

CREATE TABLE IF NOT EXIST user (

);