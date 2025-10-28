DROP TABLE IF EXISTS wishlist_wishes;
DROP TABLE IF EXISTS wishlist;
DROP TABLE IF EXISTS wish;
DROP TABLE IF EXISTS users;


CREATE TABLE IF NOT EXISTS users (
    userId INT AUTO_INCREMENT PRIMARY KEY,
    userName VARCHAR(50) NOT NULL,
    email VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(50) NOT NULL,
    dateOfBirth DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS wish (
    wishId INT AUTO_INCREMENT PRIMARY KEY,
    wishName VARCHAR(50) UNIQUE NOT NULL,
    description TEXT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    pictureLink TEXT,
    purchaseLink TEXT


);

CREATE TABLE IF NOT EXISTS wishlist (
    wishlistId INT AUTO_INCREMENT PRIMARY KEY,
    wishlistName VARCHAR(50) UNIQUE NOT NULL,
    description TEXT NOT NULL,
    ownerName TEXT NOT NULL,
    ownerId INT NOT NULL,
    FOREIGN KEY (ownerId) REFERENCES users(userId) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS wishlist_wishes (
    wishlistId INT NOT NULL,
    wishId INT NOT NULL,
    PRIMARY KEY (wishlistId, wishId),
    FOREIGN KEY (wishlistId) REFERENCES wishlist(wishlistId) ON DELETE CASCADE,
    FOREIGN KEY (wishId) REFERENCES wish(wishId) ON DELETE CASCADE
);
