-- This script is used to populate the database with initial data

-- Create a database
-- CREATE DATABASE db;

-- Use the database
-- USE db;

-- Create a table
-- CREATE TABLE admin (
--     id BIGINT AUTO_INCREMENT PRIMARY KEY,
--     name VARCHAR(255),
--     email VARCHAR(255),
--     password VARCHAR(255),
--     username VARCHAR(255)
-- );

-- Insert data into the table
-- INSERT INTO admin (id, email, name, password, username) VALUES (1, 'admin@tremorx.com', 'admin', 'password', 'admin');

-- Select all data from the table
-- SELECT * FROM admin;

-- Update data in the table
-- UPDATE admin SET email = 'john.doe@example.com' WHERE id = 1;

-- Delete data from the table
-- DELETE FROM admin WHERE id = 1;

-- Drop the table
-- DROP TABLE admin;

INSERT INTO admin (id, email, name, password, username) VALUES (1, 'admin@tremorx.com', 'admin', 'password', 'admin');

INSERT INTO doctor (id, document_id, email, lastname, name, password) VALUES (2, '1208851296', 'pperez@tremorx.com', 'Perez', 'Pedro', 'Sav3Life5');

INSERT INTO patient (id, consultation_date, document_id, name, doctorid) VALUES (1, '2024-03-22', '9818103492', 'John Doe', 2);

INSERT INTO register (id, author, test_date, patientid) VALUES (1, 'Pedro Perez', '2024-03-22', 1);

INSERT INTO test (id, time, x_position, y_position, z_position, registerid) VALUES (1, 145560, -4.1419, 7.0341, -6.4044, 1);

INSERT INTO test (id, time, x_position, y_position, z_position, registerid) VALUES (2, 145594, -4.676, 7.659, -6.954, 1);

INSERT INTO test (id, time, x_position, y_position, z_position, registerid) VALUES (2, 145621, -4.3457, 6.459, -7.354, 1);

INSERT INTO annotation (id, annotation, registerid) VALUES (1, 'Temblores leves', 1);