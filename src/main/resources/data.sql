INSERT INTO account (account_id, "phone no.", user_id, balance, created_at)
VALUES (1001, '1234567890', 501, 5000, CURRENT_TIME);

INSERT INTO account (account_id, "phone no.", user_id, balance, created_at)
VALUES (1002, '2345678901', 502, 15000, CURRENT_TIME);

INSERT INTO account (account_id, "phone no.", user_id, balance, created_at)
VALUES (1003, '3456789012', 503, 250, CURRENT_TIME);

INSERT INTO account (account_id, "phone no.", user_id, balance, created_at)
VALUES (1004, '4567890123', 504, 99999, CURRENT_TIME);

INSERT INTO account (account_id, "phone no.", user_id, balance, created_at)
VALUES (1005, '5678901234', 505, 1200, CURRENT_TIME);

-- for person

INSERT INTO person (name, email, password, pin, created_at, created_at_d)
VALUES ('John Doe', 'john@example.com', 'plain_text_pass', '1234', CURRENT_TIME, CURRENT_DATE);

INSERT INTO person (name, email, password, pin, created_at, created_at_d)
VALUES ('Jane Smith', 'jane@example.com', 'my_secret_123', '9876', CURRENT_TIME, CURRENT_DATE);

INSERT INTO person (name, email, password, pin, created_at, created_at_d)
VALUES ('Alice Jones', 'alice@example.com', 'hello_world', '5555', CURRENT_TIME, CURRENT_DATE);