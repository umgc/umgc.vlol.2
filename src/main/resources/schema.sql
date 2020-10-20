
/******************************************************************************************************************************** 
* File:        LetterOfLifeCreateDatabase_DDL.sql
* Version:     1.0 
* Created:     June 17, 2020
* Description: This is the database creation script for the Letter of Life application.
*			   The script first drops then create database tables and populates them with some sample data. 
* Author:	   Augustin Mwamba & Team EMSPlus
* 			   SWEN670, Summer 2020
*			   University of Maryland Global Campus (UMGC) 
*              ******************************************************************************************************************
* Instructions:
* 1. First, create the letteroflife schema in mysql by either issuing the following SQL statement : CREATE SCHEMA 'letteroflife'; 
*    or Open the MySQL Workbench and Right-click on the list of existing Schemas and 
*    select Create Schema... to create the database schema and enter "letteroflife" for the schema name.
* 2. Once the letteroflife schema is created, select it and run this script to create and populate the different database objects.
*********************************************************************************************************************************
*/
-- DROP ALL OBJECTS;
-- DROP TABLE IF EXISTS datasets;
-- DROP TABLE IF EXISTS user_condition;
-- DROP TABLE IF EXISTS user_medication;
-- DROP TABLE IF EXISTS user_allergy;
-- DROP TABLE IF EXISTS user_vaccine;
-- DROP TABLE IF EXISTS condition;
-- DROP TABLE IF EXISTS medication;
-- DROP TABLE IF EXISTS allergy;
-- DROP TABLE IF EXISTS vaccine;
-- DROP TABLE IF EXISTS authorized_user;
-- DROP TABLE IF EXISTS user_info;
-- DROP TABLE IF EXISTS appuser;
-- DROP TABLE IF EXISTS approle;
-- DROP TABLE IF EXISTS persistent_logins;

CREATE TABLE datasets(
    name VARCHAR(64) NOT NULL COMMENT 'The unique ID for the dataset.', 
    last_updated DATE COMMENT 'The dataset''s last updated date.',
    --COMMENT 'The information table for databases.'
    PRIMARY KEY(name)
);

CREATE TABLE api_keys(
    api_key VARCHAR(36) NOT NULL COMMENT 'The unique api key.', 
    create_date DATE DEFAULT CURRENT_TIMESTAMP COMMENT 'The key''s entry date.',
    user_ref VARCHAR(256) NOT NULL COMMENT 'The user who will be using the key.', 
    --COMMENT 'The information table for api keys.'
    PRIMARY KEY(api_key)
);

CREATE TABLE allergy(
    allergy_id BIGINT auto_increment COMMENT 'The unique ID for an allergy.', 
    allergy_name VARCHAR(256) NOT NULL COMMENT 'The allergy''s name.',
    ref_id VARCHAR(64) COMMENT 'The reference ID from the dataset.'
); -- COMMENT='The information table for allergies.';

ALTER TABLE allergy ADD CONSTRAINT allergy_pk PRIMARY KEY(allergy_id);
ALTER TABLE allergy ADD CONSTRAINT alergy_uq_name UNIQUE(allergy_name);

CREATE TABLE vaccine(
    vaccine_id BIGINT auto_increment COMMENT 'The unique ID for an vaccine.', 
    vaccine_name VARCHAR(256) NOT NULL COMMENT 'The vaccine''s name.',
    ref_id VARCHAR(64) COMMENT 'The reference ID from the dataset.'
); -- COMMENT='The information table for allergies.';

ALTER TABLE vaccine ADD CONSTRAINT vaccine_pk PRIMARY KEY(vaccine_id);
ALTER TABLE vaccine ADD CONSTRAINT vaccine_uq_name UNIQUE(vaccine_name);

CREATE TABLE condition(
    condition_id BIGINT auto_increment COMMENT 'The unique ID for an condition.', 
    condition_name VARCHAR(256) NOT NULL COMMENT 'The condition'' name.',
    ref_id VARCHAR(64) COMMENT 'The reference ID from the dataset.'
); -- COMMENT = 'The information table for conditiones.';

ALTER TABLE condition ADD CONSTRAINT condition_pk PRIMARY KEY(condition_id);
ALTER TABLE condition ADD CONSTRAINT condition_uq_name UNIQUE(condition_name);

CREATE TABLE medication(
    medication_id BIGINT auto_increment COMMENT 'The unique ID for a medication.',
    blood_thinner BOOLEAN COMMENT 'Blood thinner alert flag',
    brand_name VARCHAR(256) NOT NULL COMMENT 'The medication''s brand name.',
    controlled BOOLEAN COMMENT 'Controlled substance alert flag',
    drug_action VARCHAR(1024) COMMENT 'The medication''s action on the body.',
    generic_name VARCHAR(256) COMMENT 'The medication''s generic name.',
    ref_id VARCHAR(64) COMMENT 'The reference ID from the dataset.'
); -- COMMENT='The information table for medications.';

ALTER TABLE medication ADD CONSTRAINT medication_pk PRIMARY KEY(medication_id);
ALTER TABLE medication ADD CONSTRAINT medication_uq_brand_name UNIQUE(brand_name);


CREATE TABLE approle (
    role_id BIGINT NOT NULL COMMENT 'The user''s role unique ID.',
    role_title VARCHAR(32) NOT NULL COMMENT 'The title for this database user''s role',
    role_description VARCHAR(512) COMMENT 'The description for this database user''s role'
); -- COMMENT='The database user role table';

ALTER TABLE approle ADD CONSTRAINT approle_pk PRIMARY KEY(role_id);
ALTER TABLE approle ADD CONSTRAINT approle_uq_title UNIQUE(role_title);

--  Profile information for a user.
CREATE TABLE appuser(
    user_id BIGINT auto_increment PRIMARY KEY COMMENT 'The user'' s unique ID.',
    email VARCHAR(320) NOT NULL COMMENT 'The user''s email address.',
    admin_comments VARCHAR(300) COMMENT 'System administrator comments.',
    date_created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'The creation date for this database user account',
    is_account_verified BOOLEAN COMMENT 'Is the user''s account active?',
    is_locked BOOLEAN COMMENT 'Is the user''s account locked?',
    is_email_verified BOOLEAN COMMENT 'Is the user''s account verified?',
    last_login_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'The timestamp of the user last login.',
    login_attempt INTEGER COMMENT 'The number of failed attempts to login to this account.',
    last_login_attempt TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT 'The timestamp of the last failed attempt to login to this account.',
    password VARCHAR(72) NOT NULL COMMENT 'The generated hash value of the user''s password.',
    role_id BIGINT NOT NULL COMMENT 'The role id of the user.',
    first_name VARCHAR(32) NOT NULL COMMENT 'The user''s first name.',
    last_name VARCHAR(100) NOT NULL COMMENT 'The user''s last name.',
    qr_code VARCHAR(36) COMMENT 'The use''s random number for qr code.'
    --COMMENT 'The database user profile table'
) ;

ALTER TABLE appuser ADD CONSTRAINT appuser_approle_fk FOREIGN KEY(role_id) REFERENCES approle(role_id);


CREATE TABLE user_info(
    info_id BIGINT auto_increment COMMENT 'The unique ID for a user.',
    user_id BIGINT NOT NULL COMMENT 'The user'' s unique ID foreign key.',
    dob DATE COMMENT 'The user''s date of birth.',
    ssn VARCHAR(9) COMMENT 'The user''s social security number.', 
    city VARCHAR(64) COMMENT 'The user''s city of residence.',
    doctor_name VARCHAR(100) COMMENT 'The user''s primary care physician.',
    doctor_phone VARCHAR(32) COMMENT 'The primary care physician''s phone number.',
    ins_company VARCHAR(64) COMMENT 'The user''s medical insurance company name.',
    ins_policy_no VARCHAR(32) COMMENT 'The user''s medical insurance policy number.',
    npi VARCHAR(10) COMMENT 'The user''s NPI.',
    phone VARCHAR(10) COMMENT 'The user''s phone number.',
    poc_name VARCHAR(100) COMMENT 'A point of contact for the user.',
    poc_phone VARCHAR(32) COMMENT 'The phone number for the user''s point of contact.',
    us_state VARCHAR(2) COMMENT 'The user''s state of residence.',
    street_address VARCHAR(100) COMMENT 'The user''s street address.',
    user_agent_id BIGINT COMMENT 'The User ID of the user''s agent.',
    user_comments VARCHAR(300) COMMENT 'User additional comments.',
    zipcode VARCHAR(5) COMMENT 'The user''s zip code number.',
    is_deceased BOOLEAN DEFAULT false
    --COMMENT 'The database user profile table'
) ;

ALTER TABLE user_info ADD CONSTRAINT user_info_pk PRIMARY KEY(info_id);
ALTER TABLE user_info ADD CONSTRAINT user_info_uq_user_id UNIQUE(user_id);
ALTER TABLE user_info ADD CONSTRAINT user_info_user_fk FOREIGN KEY(user_id) REFERENCES appuser(user_id) ON DELETE CASCADE;

CREATE TABLE advance_directive (
    advance_directive_id BIGINT auto_increment COMMENT 'Id for table',
    user_id BIGINT NOT NULL COMMENT 'The unique ID for a user.',
    advance_directive_file BLOB COMMENT 'The compressed file.',
    advance_directive_content_type VARCHAR(128) COMMENT 'The advance directive type.',
    advance_directive_filename VARCHAR(256) COMMENT 'The advance directive type.',
    advance_directive_type VARCHAR(64) NOT NULL COMMENT 'The advance directive type.'
); -- COMMENT='Table for storing advance directive files and their type';

ALTER TABLE advance_directive ADD CONSTRAINT advance_directive_pk PRIMARY KEY(user_id, advance_directive_id);
ALTER TABLE advance_directive ADD CONSTRAINT advance_directive_user_fk FOREIGN KEY(user_id) REFERENCES appuser(user_id) ON DELETE CASCADE;

CREATE TABLE authorized_user (
    authorized_user_id BIGINT auto_increment COMMENT 'Id for table',
    user_id BIGINT NOT NULL COMMENT 'The unique ID for a user.',
    authorized_email VARCHAR(320) NOT NULL COMMENT 'The authorized user''s email address.',
    authorized_name VARCHAR(128) NOT NULL COMMENT 'The authorized user''s name.'
); -- COMMENT='Stores users authorized to modify another's account';

ALTER TABLE authorized_user ADD CONSTRAINT authorized_pk PRIMARY KEY(user_id, authorized_user_id);
ALTER TABLE authorized_user ADD CONSTRAINT authorized_user_fk FOREIGN KEY(user_id) REFERENCES appuser(user_id) ON DELETE CASCADE;
CREATE INDEX authorized_user_email_idx ON authorized_user(authorized_email);

CREATE TABLE user_allergy(
    allergy_id BIGINT auto_increment COMMENT 'The unique ID for an allergy.',
    allergy_name VARCHAR(256) COMMENT 'The allergy''s name.',
    user_id BIGINT NOT NULL COMMENT 'The unique ID for a patient.', 
    ref_id VARCHAR(64) COMMENT 'The reference ID from the dataset.'
); -- COMMENT = 'The information table for the patient''s allergies.';

ALTER TABLE user_allergy ADD CONSTRAINT user_allergy_pk PRIMARY KEY(user_id, allergy_id);
ALTER TABLE user_allergy ADD CONSTRAINT user_allergy_uq_name UNIQUE(user_id, allergy_name);
ALTER TABLE user_allergy ADD CONSTRAINT user_allergy_user_fk FOREIGN KEY(user_id) REFERENCES appuser(user_id) ON DELETE CASCADE;

CREATE TABLE user_vaccine(
    vaccine_id BIGINT auto_increment COMMENT 'The unique ID for an vaccine.',
    vaccine_name VARCHAR(256) COMMENT 'The vaccine''s name.',
    user_id BIGINT NOT NULL COMMENT 'The unique ID for a patient.', 
    ref_id VARCHAR(64) COMMENT 'The reference ID from the dataset.'
); -- COMMENT = 'The information table for the patient''s allergies.';

ALTER TABLE user_vaccine ADD CONSTRAINT user_vaccine_pk PRIMARY KEY(user_id, vaccine_id);
ALTER TABLE user_vaccine ADD CONSTRAINT user_vaccine_uq_name UNIQUE(user_id, vaccine_name);
ALTER TABLE user_vaccine ADD CONSTRAINT user_vaccine_user_fk FOREIGN KEY(user_id) REFERENCES appuser(user_id) ON DELETE CASCADE;

CREATE TABLE user_condition(
    condition_id BIGINT auto_increment COMMENT 'The medication unique ID.',
    user_id BIGINT NOT NULL COMMENT 'The patient''s unique ID.',
    condition_name VARCHAR(256) COMMENT 'The condition'' name.',
    ref_id VARCHAR(64) COMMENT 'The reference ID from the dataset.'
    
); -- COMMENT = 'Information about the patient''s conditiones.';

ALTER TABLE user_condition ADD CONSTRAINT user_condition_pk PRIMARY KEY(user_id, condition_id);
ALTER TABLE user_condition ADD CONSTRAINT user_condition_uq_name UNIQUE(user_id, condition_name);
ALTER TABLE user_condition ADD CONSTRAINT user_condition_user_fk FOREIGN KEY(user_id) REFERENCES appuser(user_id) ON DELETE CASCADE;

-- key table for user_med_list
CREATE TABLE user_medication(
    medication_id BIGINT auto_increment COMMENT 'The medication unique ID.',
    user_id BIGINT NOT NULL COMMENT 'The patient''s unique ID',
    blood_thinner BOOLEAN COMMENT 'Blood thinner alert flag',
    brand_name VARCHAR(256) COMMENT 'The medication''s brand name.',
    controlled BOOLEAN COMMENT 'Controlled substance alert flag',
    drug_action VARCHAR(1024) COMMENT 'The medication''s action on the body.',
    generic_name VARCHAR(256) COMMENT 'The medication''s generic name.',
    dosage VARCHAR(32) NOT NULL COMMENT 'The amount of medication taken.',
    frequency VARCHAR(32) NOT NULL COMMENT 'How often the medication is taken (once, twice daily, etc.)',
    ref_id VARCHAR(64) COMMENT 'The reference ID from the dataset.'
); -- COMMENT = 'The key table for the user-medication''s list.';

ALTER TABLE user_medication ADD CONSTRAINT user_med_pk PRIMARY KEY(user_id, medication_id);
ALTER TABLE user_medication ADD CONSTRAINT user_med_user_fk FOREIGN KEY(user_id) REFERENCES appuser(user_id) ON DELETE CASCADE;
ALTER TABLE user_medication ADD CONSTRAINT user_medication_uq UNIQUE(user_id, brand_name);

CREATE TABLE persistent_logins (
    username varchar(320) NOT NULL,
    series varchar(64) NOT NULL,
    token varchar(64) NOT NULL,
    last_used timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
    PRIMARY KEY (series)
);


INSERT INTO approle (role_id, role_title, role_description) VALUES (1, 'participant', 'Program Participant');
INSERT INTO approle (role_id, role_title, role_description) VALUES (2, 'provider', 'Medical Services Provider');
INSERT INTO approle (role_id, role_title, role_description) VALUES (3, 'admin', 'System Administrator');

