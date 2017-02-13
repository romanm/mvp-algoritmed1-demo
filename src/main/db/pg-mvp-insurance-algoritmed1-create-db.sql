/* БД pg_mvp_insurance_algoritmed1 */

CREATE SEQUENCE IF NOT EXISTS dbid;
ALTER SEQUENCE dbid RESTART WITH 1;
DROP TABLE IF EXISTS medical_facility_patient;
DROP TABLE IF EXISTS medical_facility;
DROP TABLE IF EXISTS insurance_facility_patient;
DROP TABLE IF EXISTS insurance_facility;
DROP TABLE IF EXISTS patient;
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

/* паціент на лікуванні */
CREATE TABLE patient (
	patient_id INT PRIMARY KEY,
	patient_pib VARCHAR(200),
	patient_address_id INT,
	patient_dob DATE,
	FOREIGN KEY (patient_address_id) REFERENCES address(address_id),
	FOREIGN KEY (patient_id) REFERENCES uuid(uuid_dbid)
);

/* МЕДИК (ЧАСНА ПРАКТИКА / ЛІКУВАЛЬНИЙ ЗАКЛАД) */
/*  лікувальні заклади */
CREATE TABLE medical_facility (
	medical_facility_id INT PRIMARY KEY,
	medical_facility_name VARCHAR(200),
	medical_facility_shortname VARCHAR(20),
	medical_facility_address_id INT,
	FOREIGN KEY (medical_facility_address_id) REFERENCES address(address_id),
	FOREIGN KEY (medical_facility_id) REFERENCES uuid(uuid_dbid)
);
/* паціент в лікувальному закладі */
CREATE TABLE medical_facility_patient (
	medical_facility_id INT NOT NULL,
	medical_patient_id INT NOT NULL ,
	FOREIGN KEY (medical_patient_id) REFERENCES patient(patient_id),
	FOREIGN KEY (medical_facility_id) REFERENCES medical_facility(medical_facility_id)
);

/* МЕДИЧНЕ СТРАХУВАННЯ */
/* Страхові компанії або види страховки */
CREATE TABLE insurance_facility (
	insurance_facility_id INT PRIMARY KEY,
	insurance_facility_name VARCHAR(200),
	insurance_facility_shortname VARCHAR(20),
	insurance_facility_address_id INT,
	FOREIGN KEY (insurance_facility_address_id) REFERENCES address(address_id),
	FOREIGN KEY (insurance_facility_id) REFERENCES uuid(uuid_dbid)
);
/* страховка паціента */
CREATE TABLE insurance_facility_patient (
	insurance_facility_id INT NOT NULL,
	insurance_patient_id INT NOT NULL ,
	FOREIGN KEY (insurance_patient_id) REFERENCES patient(patient_id),
	FOREIGN KEY (insurance_facility_id) REFERENCES insurance_facility(insurance_facility_id)
);

