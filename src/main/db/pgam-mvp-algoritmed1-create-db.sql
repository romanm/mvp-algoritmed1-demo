/* БД pg_mvp_algoritmed1 */

CREATE SEQUENCE IF NOT EXISTS dbid;
ALTER SEQUENCE dbid RESTART WITH 1;
DROP TABLE IF EXISTS medic.patient;
DROP TABLE IF EXISTS uuid;

CREATE TABLE uuid (
	uuid_dbid INT DEFAULT NEXTVAL('dbid') PRIMARY KEY,
	uuid_uuid VARCHAR(36) NOT NULL,
	UNIQUE (uuid_uuid)
);

CREATE SCHEMA IF NOT EXISTS medic;

CREATE TABLE medic.patient (
	patient_id INT PRIMARY KEY,
	patient_pib VARCHAR(200),
	patient_address VARCHAR(200),
	patient_dob DATE,
	FOREIGN KEY (patient_id) REFERENCES uuid(uuid_dbid)
);

CREATE SCHEMA IF NOT EXISTS finding;
CREATE SCHEMA IF NOT EXISTS patient;
CREATE SCHEMA IF NOT EXISTS insuranceall;
CREATE SCHEMA IF NOT EXISTS apotheke;

