CREATE TABLE departments (
    department_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    department_name VARCHAR(255) NOT NULL
);

CREATE TABLE employees (
    employee_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    position VARCHAR(255) NOT NULL,
    salary DOUBLE NOT NULL,
    department_id BIGINT,
    CONSTRAINT fk_department
    FOREIGN KEY (department_id)
    REFERENCES departments(department_id)
);