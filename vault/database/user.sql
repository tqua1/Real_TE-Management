CREATE USER vault_owner
WITH PASSWORD 'financetracker';

GRANT ALL
ON ALL TABLES IN SCHEMA public
TO vault_owner;

GRANT ALL
ON ALL SEQUENCES IN SCHEMA public
TO vault_owner;

CREATE USER vault_appuser
WITH PASSWORD 'financetracker';

GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA public
TO vault_appuser;

GRANT USAGE, SELECT
ON ALL SEQUENCES IN SCHEMA public
TO vault_appuser;