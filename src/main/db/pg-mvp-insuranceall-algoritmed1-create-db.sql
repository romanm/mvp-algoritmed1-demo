/* БД pg_mvp_insurance_algoritmed1 */

CREATE SEQUENCE IF NOT EXISTS dbid;
ALTER SEQUENCE dbid RESTART WITH 1;
DROP TABLE IF EXISTS insurance.patient;
DROP TABLE IF EXISTS uuid;

CREATE TABLE uuid (
	uuid_dbid INT DEFAULT NEXTVAL('dbid') PRIMARY KEY,
	uuid_uuid VARCHAR(36) NOT NULL,
	UNIQUE (uuid_uuid)
);

CREATE TABLE address(
	address_id INT PRIMARY KEY,
	address_address VARCHAR(200),
	FOREIGN KEY (address_id) REFERENCES uuid(uuid_dbid)
);

/* паціент на страховці */
CREATE TABLE patient (
	patient_id INT PRIMARY KEY,
	patient_pib VARCHAR(200),
	patient_address_id INT,
	patient_dob DATE,
	FOREIGN KEY (patient_address_id) REFERENCES address(address_id),
	FOREIGN KEY (patient_id) REFERENCES uuid(uuid_dbid)
);

/* МЕДИЧНЕ СТРАХУВАННЯ */
CREATE SCHEMA IF NOT EXISTS insurance;
/* Страхові компанії або види страховки */
CREATE TABLE insurance.facility (
	facility_id INT PRIMARY KEY,
	facility_name VARCHAR(200),
	facility_shortname VARCHAR(20),
	facility_address_id INT,
	FOREIGN KEY (facility_address_id) REFERENCES address(address_id),
	FOREIGN KEY (facility_id) REFERENCES uuid(uuid_dbid)
);
/* страховка паціента */
CREATE TABLE insurance.i_facility_patient (
	ifp_facility_id INT NOT NULL,
	ifp_patient_id INT NOT NULL ,
	FOREIGN KEY (ifp_patient_id) REFERENCES patient(patient_id),
	FOREIGN KEY (ifp_facility_id) REFERENCES facility(facility_id)
);

/* МЕДИК (ЧАСНА ПРАКТИКА / ЛІКУВАЛЬНИЙ ЗАКЛАД) */
CREATE SCHEMA IF NOT EXISTS medical;

/*  лікувальні заклади */
CREATE TABLE medical.facility (
	facility_id INT PRIMARY KEY,
	facility_name VARCHAR(200),
	facility_shortname VARCHAR(20),
	facility_address_id INT,
	FOREIGN KEY (facility_address_id) REFERENCES address(address_id),
	FOREIGN KEY (facility_id) REFERENCES uuid(uuid_dbid)
);

CREATE SCHEMA IF NOT EXISTS finding;
CREATE SCHEMA IF NOT EXISTS patient;
CREATE SCHEMA IF NOT EXISTS apotheke;

