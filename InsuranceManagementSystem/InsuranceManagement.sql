Create database java;
use  java;

CREATE TABLE User (
    userId INT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);

CREATE TABLE Client (
    clientId INT PRIMARY KEY,
    clientName VARCHAR(255) NOT NULL,
    contactInfo VARCHAR(255) NOT NULL,
    policyId INT,
    FOREIGN KEY (policyId) REFERENCES Policy(policyId)
);

CREATE TABLE Policy (
    policyId INT PRIMARY KEY,
    policyName VARCHAR(255) NOT NULL
);

CREATE TABLE Claim (
    claimId INT PRIMARY KEY,
    claimNumber VARCHAR(255) NOT NULL,
    dateFiled DATE NOT NULL,
    claimAmount DECIMAL(10, 2) NOT NULL,
    status VARCHAR(50) NOT NULL,
    policyId INT,
    clientId INT,
    FOREIGN KEY (policyId) REFERENCES Policy(policyId),
    FOREIGN KEY (clientId) REFERENCES Client(clientId)
);

CREATE TABLE Payment (
    paymentId INT PRIMARY KEY,
    paymentDate DATE NOT NULL,
    paymentAmount DECIMAL(10, 2) NOT NULL,
    clientId INT,
    FOREIGN KEY (clientId) REFERENCES Client(clientId)
);



INSERT INTO User (userId, username, password, role) VALUES
(1, 'Gaurav', 'password123', 'admin');


INSERT INTO Policy (policyId, policyName) VALUES
(1, 'ABC'),
(2, 'XYZ'),
(3, 'WOW');


INSERT INTO Client (clientId, clientName, contactInfo, policyId) VALUES
(1, 'Saurav', '9876543210', 1),
(2, 'Isha', '8765432109', 2),
(3, 'Gaurav', '7654321098', 3);


INSERT INTO Claim (claimId, claimNumber, dateFiled, claimAmount, status, policyId, clientId) VALUES
(1, 'CLM001', '2023-01-01', 1000.00, 'Pending', 1, 1),
(2, 'CLM002', '2023-02-15', 2000.00, 'Approved', 2, 2),
(3, 'CLM003', '2023-03-20', 1500.00, 'Rejected', 3, 3);


INSERT INTO Payment (paymentId, paymentDate, paymentAmount, clientId) VALUES
(1, '2023-04-05', 500.00, 1),
(2, '2023-05-10', 1000.00, 2),
(3, '2023-06-15', 800.00, 3);


























