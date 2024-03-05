BEGIN TRANSACTION;
ROLLBACK;
DROP TABLE IF EXISTS users, accounts, transactions, budgets, budget_categories;
DROP SEQUENCE IF EXISTS seq_user_id, seq_account_id, seq_transaction_id, seq_budget_id,
 seq_budget_category_id;

 CREATE SEQUENCE seq_user_id
 INCREMENT by 1
 START WITH 1001
 NO MAXVALUE;


 CREATE TABLE users (
 user_id int NOT NULL DEFAULT nextval('seq_user_id'),
  username varchar(50) NOT NULL UNIQUE,
  password_hash varchar(50) NOT NULL,
  role varchar(50) NOT NULL,
  CONSTRAINT PK_users PRIMARY KEY (user_id),
  CONSTRAINT UQ_username UNIQUE (username),
  CONSTRAINT CK_role CHECK (role IN ('ROLE_ADMIN', 'ROLE_USER'))
  );

    CREATE SEQUENCE seq_account_id
  INCREMENT by 1
  START WITH 2001
  NO MAXVALUE;

  CREATE TABLE accounts (
  account_id int NOT NULL DEFAULT nextval('seq_account_id'),
  user_id int NOT NULL,
  name varchar(50) NOT NULL UNIQUE,
  balance DECIMAL(10,2) NOT NULL,
  is_debt DECIMAL(10,2) NOT NULL,
  notes varchar(200),
  CONSTRAINT PK_accounts PRIMARY KEY (account_id),
  CONSTRAINT FK_accounts_users FOREIGN KEY (user_id) REFERENCES users (user_id),
   CONSTRAINT UQ_name UNIQUE (name)
  );

    CREATE SEQUENCE seq_budget_category_id
  INCREMENT by 1
  START WITH 3001
  NO MAXVALUE;

  CREATE TABLE budget_categories (
  budget_category_id int NOT NULL DEFAULT nextval('seq_budget_category_id'),
  name varchar(50) NOT NULL,
  CONSTRAINT PK_budget_categories PRIMARY KEY (budget_category_id)
  );

  CREATE SEQUENCE seq_transaction_id
  INCREMENT by 1
  START WITH 4001
  NO MAXVALUE;

  CREATE TABLE transactions (
  transaction_id int NOT NULL DEFAULT nextval('seq_transaction_id'),
  user_id int NOT NULL,
  account_id int NOT NULL,
  category varchar(50) NOT NULL,
  date DATE NOT NULL,
  amount DECIMAL (10,2) NOT NULL,
  notes varchar(200),
  type varchar(30) NOT NULL,
  is_fixed boolean NOT NULL,
  CONSTRAINT PK_transactions PRIMARY KEY (transaction_id),
  CONSTRAINT FK_transaction_user FOREIGN KEY (user_id) REFERENCES users (user_id),
  CONSTRAINT FK_transactions_account FOREIGN KEY (account_id) REFERENCES accounts (account_id),
  CONSTRAINT CK_type CHECK (type IN ('TYPE_EXPENSE', 'TYPE_INCOME', 'TYPE_TRANSFER'))
  );

 COMMIT;
