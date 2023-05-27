CREATE TABLE IF NOT EXISTS Incomes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    value DECIMAL(10, 2) NOT NULL,
    date DATE NOT NULL
);