INSERT INTO users (username, email, password, role) VALUES ('admin', 'admin@example.com', 'adminpassword', 'ADMIN');
INSERT INTO genres (name) VALUES ('Science Fiction'), ('Fantasy'), ('Mystery');
INSERT INTO books (title, author, price, published_date) VALUES
('The Great Gatsby', 'F. Scott Fitzgerald', 10.99, '1925-04-10'),
('To Kill a Mockingbird', 'Harper Lee', 7.99, '1960-07-11'),
('1984', 'George Orwell', 8.99, '1949-06-08'),
('Moby Dick', 'Herman Melville', 11.99, '1851-10-18'),
('Pride and Prejudice', 'Jane Austen', 9.99, '1813-01-28');
INSERT INTO book_genres (book_id, genre_id) VALUES
('1','1'),
('2','2'),
('3','3'),
('4','1'),
('5','2');