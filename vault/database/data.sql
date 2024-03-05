INSERT INTO users (username,password_hash,role) VALUES ('admin1', 'bbb', 'ROLE_ADMIN'); --1001

INSERT INTO users (username, password_hash,role) VALUES ('tanea', 'bbb', 'ROLE_USER'); --1002
INSERT INTO users (username, password_hash,role) VALUES ('sandy', 'bbb', 'ROLE_USER'); --1003
INSERT INTO users (username, password_hash,role) VALUES ('spongebob', 'bbb', 'ROLE_USER') --1004
INSERT INTO users (username, password_hash,role) VALUES ('pearl', 'bbb', 'ROLE_USER'); --1005
INSERT INTO users (username, password_hash,role) VALUES ('larry', 'bbb', 'ROLE_USER'); --1006
INSERT INTO users (username, password_hash,role) VALUES ('patrick', 'bbb', 'ROLE_USER'); --1007
INSERT INTO users (username, password_hash,role) VALUES ('planktin', 'bbb', 'ROLE_USER'); --1008
INSERT INTO users (username, password_hash,role) VALUES ('squidward', 'bbb', 'ROLE_USER'); --1009
INSERT INTO users (username, password_hash,role) VALUES ('gary', 'bbb', 'ROLE_USER'); --1010

INSERT INTO accounts (user_id, name, balance, debt_account, notes) VALUES (1002, 'Checkings', 1000.00, false,); --2001
INSERT INTO accounts (user_id, name, balance, debt_account, notes) VALUES (1002, 'Car Savings', 500.00, false,); --2002
INSERT INTO accounts (user_id, name, balance, debt_account, notes) VALUES (1002, 'Discover CC', 200.00, true,); --2003
INSERT INTO accounts (user_id, name, balance, debt_account, notes) VALUES (1002, 'Chase CC', 300.00, true,); --2004
INSERT INTO accounts (user_id, name, balance, debt_account, notes) VALUES (1003, 'Rainy Day', 2000.00, false,); --2005
INSERT INTO accounts (user_id, name, balance, debt_account, notes) VALUES (1003, 'VISA CC', 1000.00, true,); --2006
INSERT INTO accounts (user_id, name, balance, debt_account, notes) VALUES (1004, 'Vacation', 700.00, false,); --2007
INSERT INTO accounts (user_id, name, balance, debt_account, notes) VALUES (1004, 'House Remodel', 600.00, false,); --2008
INSERT INTO accounts (user_id, name, balance, debt_account, notes) VALUES (1005, 'New car', 2000.00, false,); --2009
INSERT INTO accounts (user_id, name, balance, debt_account, notes) VALUES (1005, 'Nail Savings', 90.00, false,); --2010

INSERT INTO budget_categories (name) VALUES ('Salary'); --3001
INSERT INTO budget_categories (name) VALUES ('Gym'); --3002
INSERT INTO budget_categories (name) VALUES ('Restaurant'); --3003
INSERT INTO budget_categories (name) VALUES ('Groceries'); --3004
INSERT INTO budget_categories (name) VALUES ('Rent'); --3005
INSERT INTO budget_categories (name) VALUES ('Gas'); --3006
INSERT INTO budget_categories (name) VALUES ('Nails'); --3007
INSERT INTO budget_categories (name) VALUES ('Vacation'); --3008
INSERT INTO budget_categories (name) VALUES ('Streaming Services'); --3009
INSERT INTO budget_categories (name) VALUES ('Amazon'); --3010

INSERT INTO transactions (user_id, budget_category_id, date, account_id, notes, type, is_fixed) VALUES (1002, 3001, '2024-02-20', 'TYPE_INCOME', false); --4001
INSERT INTO transactions (user_id, budget_category_id, date, account_id, notes, type, is_fixed) VALUES (1002, 3005, '2024-02-23', 'TYPE_EXPENSE', true); --4002
INSERT INTO transactions (user_id, budget_category_id, date, account_id, notes, type, is_fixed) VALUES (1002, 3009, '2024-02-24', 'TYPE_EXPENSE', true); --4003
INSERT INTO transactions (user_id, budget_category_id, date, account_id, notes, type, is_fixed) VALUES (1003, 3001, '2024-02-18', 'TYPE_INCOME', true); --4004
INSERT INTO transactions (user_id, budget_category_id, date, account_id, notes, type, is_fixed) VALUES (1003, 3002, '2024-02-20', 'TYPE_EXPENSE', false); --4005
INSERT INTO transactions (user_id, budget_category_id, date, account_id, notes, type, is_fixed) VALUES (1004, 3001, '2024-02-22', 'TYPE_INCOME', false); --4006
INSERT INTO transactions (user_id, budget_category_id, date, account_id, notes, type, is_fixed) VALUES (1004, 3008, '2024-02-25', 'TYPE_EXPENSE', false); --4007
INSERT INTO transactions (user_id, budget_category_id, date, account_id, notes, type, is_fixed) VALUES (1005, 3001, '2024-02-20', 'TYPE_INCOME', false); --4008
INSERT INTO transactions (user_id, budget_category_id, date, account_id, notes, type, is_fixed) VALUES (1005, 3004, '2024-02-22', 'TYPE_EXPENSE', false); --4009
INSERT INTO transactions (user_id, budget_category_id, date, account_id, notes, type, is_fixed) VALUES (1005, 3008, '2024-02-24', 'TYPE_EXPENSE', false); --4010ee