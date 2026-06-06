-- for person

INSERT INTO person (name, email, password, pin, created_at, created_at_d)
VALUES ('John Doe', 'john@example.com', 'plain_text_pass', '1234', CURRENT_TIME, CURRENT_DATE);

INSERT INTO person (name, email, password, pin, created_at, created_at_d)
VALUES ('Jane Smith', 'jane@example.com', 'my_secret_123', '9876', CURRENT_TIME, CURRENT_DATE);

INSERT INTO person (name, email, password, pin, created_at, created_at_d)
VALUES ('Alice Jones', 'alice@example.com', 'hello_world', '5555', CURRENT_TIME, CURRENT_DATE);
--for account
INSERT INTO account (account_id, "phone no.", person_id, balance, created_at)
VALUES (1001, '1234567890', 1, 5000, CURRENT_TIME);

INSERT INTO account (account_id, "phone no.", person_id, balance, created_at)
VALUES (1002, '2345678901', 1, 15000, CURRENT_TIME);

INSERT INTO account (account_id, "phone no.", person_id, balance, created_at)
VALUES (1003, '3456789012', 2, 250, CURRENT_TIME);

INSERT INTO account (account_id, "phone no.", person_id, balance, created_at)
VALUES (1004, '4567890123', 2, 99999, CURRENT_TIME);

INSERT INTO account (account_id, "phone no.", person_id, balance, created_at)
VALUES (1005, '5678901234', 3, 1200, CURRENT_TIME);

-- LOAN SCHEMES

-- HOME LOANS
INSERT INTO loan_schema
(loan_type, provider_type, max_amount, interest_rate, tenure_months, active)
VALUES
('HOME', 'BANK', 5000000, 8.5, 240, true);

INSERT INTO loan_schema
(loan_type, provider_type, max_amount, interest_rate, tenure_months, active)
VALUES
('HOME', 'NBFC', 4000000, 9.2, 180, true);


-- PERSONAL LOANS
INSERT INTO loan_schema
(loan_type, provider_type, max_amount, interest_rate, tenure_months, active)
VALUES
('PERSONAL', 'BANK', 1000000, 10.5, 60, true);

INSERT INTO loan_schema
(loan_type, provider_type, max_amount, interest_rate, tenure_months, active)
VALUES
('PERSONAL', 'NBFC', 1500000, 12.5, 72, true);


-- VEHICLE LOANS
INSERT INTO loan_schema
(loan_type, provider_type, max_amount, interest_rate, tenure_months, active)
VALUES
('VEHICLE', 'BANK', 2000000, 9.0, 84, true);

INSERT INTO loan_schema
(loan_type, provider_type, max_amount, interest_rate, tenure_months, active)
VALUES
('VEHICLE', 'NBFC', 1800000, 10.0, 84, true);


-- EDUCATION LOANS
INSERT INTO loan_schema
(loan_type, provider_type, max_amount, interest_rate, tenure_months, active)
VALUES
('EDUCATION', 'GOVERNMENT', 3000000, 7.0, 120, true);

INSERT INTO loan_schema
(loan_type, provider_type, max_amount, interest_rate, tenure_months, active)
VALUES
('EDUCATION', 'BANK', 2500000, 8.0, 120, true);


-- BUSINESS LOANS
INSERT INTO loan_schema
(loan_type, provider_type, max_amount, interest_rate, tenure_months, active)
VALUES
('BUSINESS', 'BANK', 10000000, 11.0, 180, true);

INSERT INTO loan_schema
(loan_type, provider_type, max_amount, interest_rate, tenure_months, active)
VALUES
('BUSINESS', 'NBFC', 8000000, 13.0, 120, true);


-- GOLD LOANS
INSERT INTO loan_schema
(loan_type, provider_type, max_amount, interest_rate, tenure_months, active)
VALUES
('GOLD', 'BANK', 500000, 8.0, 24, true);

INSERT INTO loan_schema
(loan_type, provider_type, max_amount, interest_rate, tenure_months, active)
VALUES
('GOLD', 'NBFC', 700000, 9.0, 36, true);
-- LOAN
-- account_id links the loan to an account

INSERT INTO loan
(amount, interest_rate, tenure_months, type, provider_type, status, account_id)
VALUES
(500000, 8.5, 120, 'HOME', 'BANK', 'APPROVED', 1001);

INSERT INTO loan
(amount, interest_rate, tenure_months, type, provider_type, status, account_id)
VALUES
(100000, 10.5, 36, 'PERSONAL', 'NBFC', 'APPROVED', 1002);

INSERT INTO loan
(amount, interest_rate, tenure_months, type, provider_type, status, account_id)
VALUES
(750000, 7.2, 180, 'EDUCATION', 'GOVERNMENT', 'APPLIED', 1004);

-- INSURANCE SCHEMAS

INSERT INTO insurance_schema
(coverage_amount, premium_amount, type, status)
VALUES
(500000, 5000, 'HEALTH', 'ACTIVE');

INSERT INTO insurance_schema
(coverage_amount, premium_amount, type, status)
VALUES
(1000000, 12000, 'LIFE', 'ACTIVE');

INSERT INTO insurance_schema
(coverage_amount, premium_amount, type, status)
VALUES
(300000, 2500, 'VEHICLE', 'ACTIVE');

INSERT INTO insurance_schema
(coverage_amount, premium_amount, type, status)
VALUES
(1500000, 8000, 'HOME', 'ACTIVE');

INSERT INTO insurance_schema
(coverage_amount, premium_amount, type, status)
VALUES
(200000, 1500, 'TRAVEL', 'ACTIVE');


-- INSURANCE
-- account_id links the insurance to an account

INSERT INTO insurance
(coverage_amount, premium_amount, type, status, account_id)
VALUES
(500000, 5000, 'HEALTH', 'ACTIVE', 1001);

INSERT INTO insurance
(coverage_amount, premium_amount, type, status, account_id)
VALUES
(1000000, 12000, 'LIFE', 'ACTIVE', 1003);

INSERT INTO insurance
(coverage_amount, premium_amount, type, status, account_id)
VALUES
(300000, 2500, 'VEHICLE', 'EXPIRED', 1005);

