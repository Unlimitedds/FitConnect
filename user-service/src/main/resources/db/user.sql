CREATE TABLE IF NOT EXISTS Benutzer (
	ID BIGINT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(100) NOT NULL,
	email VARCHAR(100) NOT NULL UNIQUE,
	password VARCHAR(255) NOT NULL,
);
 
INSERT INTO Benutzer (name, email, passwort) VALUES
('Max Mustermann', 'max@example.com', 'pass123'),
('Erika Musterfrau', 'erika@example.com', 'secure456'),
('John Doe', 'john.doe@example.com', 'hello789'),
('Anna Schmidt', 'anna.schmidt@example.com', 'pw12345'),
('Peter Müller', 'peter.mueller@example.com', 'peterpass');
 