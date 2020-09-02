
INSERT INTO allergy (allergy_id, allergy_name) VALUES (1, 'None');
INSERT INTO allergy (allergy_id, allergy_name) VALUES (2, 'Other');
INSERT INTO allergy (allergy_id, allergy_name) VALUES (3, 'Aspirin');
INSERT INTO allergy (allergy_id, allergy_name) VALUES (4, 'Barbituates');
INSERT INTO allergy (allergy_id, allergy_name) VALUES (5, 'Codeine');
INSERT INTO allergy (allergy_id, allergy_name) VALUES (6, 'Latex');
INSERT INTO allergy (allergy_id, allergy_name) VALUES (7, 'Lidocaine');
INSERT INTO allergy (allergy_id, allergy_name) VALUES (8, 'Morphine');
INSERT INTO allergy (allergy_id, allergy_name) VALUES (9, 'Penicillin');
INSERT INTO allergy (allergy_id, allergy_name) VALUES (10, 'Sulfa');

INSERT INTO illness (illness_id, illness_name) VALUES (1, 'None');
INSERT INTO illness (illness_id, illness_name) VALUES (2, 'Other');
INSERT INTO illness (illness_id, illness_name) VALUES (3, 'Asthma');
INSERT INTO illness (illness_id, illness_name) VALUES (4, 'Bleeding Disorder');
INSERT INTO illness (illness_id, illness_name) VALUES (5, 'Cancer');
INSERT INTO illness (illness_id, illness_name) VALUES (6, 'Cardiac');
INSERT INTO illness (illness_id, illness_name) VALUES (7, 'COPD');
INSERT INTO illness (illness_id, illness_name) VALUES (8, 'Dementia');
INSERT INTO illness (illness_id, illness_name) VALUES (9, 'Diabetes (Blood Sugar)');
INSERT INTO illness (illness_id, illness_name) VALUES (10, 'Hepatitis');
INSERT INTO illness (illness_id, illness_name) VALUES (11, 'Hypertension (Blood Pressure)');
INSERT INTO illness (illness_id, illness_name) VALUES (12, 'Pacemaker');
INSERT INTO illness (illness_id, illness_name) VALUES (13, 'Seizure Disorder');
INSERT INTO illness (illness_id, illness_name) VALUES (14, 'Stroke');

INSERT INTO approle (role_id, role_level, role_title, role_description) VALUES (1, 1, 'participant', 'Program Participant (Profile Access Only)');
INSERT INTO approle (role_id, role_level, role_title, role_description) VALUES (2, 5, 'provider', 'Medical Services Provider (Read Access Only)');
INSERT INTO approle (role_id, role_level, role_title, role_description) VALUES (3, 10, 'admin', 'System Administrator (CRUD Access)');


INSERT INTO appuser (user_id, role_id, first_name, last_name, email, password, security_question, security_answer, date_created, last_login_date, admin_comments, is_active, is_locked) VALUES 
(1, 1, 'Lorraine', 'Baines', 'parti@vlol.gov', '$2a$10$wbBBPoyyiMNr5sUbInHCM.rrXk8PfbfLTkWJ9KEglBynBFaNSmjsS', 'Favorite numbers', '$2a$10$wbBBPoyyiMNr5sUbInHCM.rrXk8PfbfLTkWJ9KEglBynBFaNSmjsS', '2020-05-20 16:30:00', '2020-06-23 15:10:00', 'Lorraine is a program participant.', true, false),
(2, 2, 'Marty', 'McFly', 'provi@vlol.gov', '$2a$10$wbBBPoyyiMNr5sUbInHCM.rrXk8PfbfLTkWJ9KEglBynBFaNSmjsS', 'Favorite numbers', '$2a$10$wbBBPoyyiMNr5sUbInHCM.rrXk8PfbfLTkWJ9KEglBynBFaNSmjsS', '2020-05-20 18:30:00', '2020-06-23 15:10:00', 'Marty is an emergency medical provider.', true, false),
(3, 1, 'Emmett', 'Brown', 'agent@vlol.gov', '$2a$10$wbBBPoyyiMNr5sUbInHCM.rrXk8PfbfLTkWJ9KEglBynBFaNSmjsS', 'Favorite numbers', '$2a$10$wbBBPoyyiMNr5sUbInHCM.rrXk8PfbfLTkWJ9KEglBynBFaNSmjsS', '2020-05-20 19:30:00', '2020-06-23 15:20:00', 'Emmett is a Lorraine''s agent.', true, false),
(4, 3, 'Seamus', 'McFly', 'admin@vlol.gov', '$2a$10$wbBBPoyyiMNr5sUbInHCM.rrXk8PfbfLTkWJ9KEglBynBFaNSmjsS', 'Favorite numbers', '$2a$10$wbBBPoyyiMNr5sUbInHCM.rrXk8PfbfLTkWJ9KEglBynBFaNSmjsS', '2020-05-20 20:30:00', '2020-06-23 15:30:00', 'Seamus is a system administrator.', true, false);

INSERT INTO user_info (user_id, dob, ssn, street_address, city, us_state, zipcode, phone, ins_company, ins_policy_no, adv_directive, adv_dir_type, poc_name, poc_phone, doctor_name, doctor_phone, user_comments) VALUES 
(1, '2015-10-21', '123456789', '1711 Bushnell Ave', 'South Pasadena', 'CA', '91030', '9165555555', 'Aetna', '123456789', true, 'DNR', 'Marty McFly', '9168423138',  'Dr. Brown', '9165551234', ''),
(2, '1985-10-26', '456789123', '9303 Roslyndale Ave', 'Arleta', 'CA', '91331', '9168423138', 'Aetna', '456789123', false, '', 'Lorraine Baines', '9165555555', 'Dr. Brown', '9165551234', ''),
(3, '1955-11-05', '147258369', '4 Westmoreland Pl', 'Pasadena', 'CA', '91103', '9168675309', 'CareFirst', '147258369', true, 'MOLST', 'Marty McFly', '9168423138', 'Dr. Tannen', '9165551234', ''),
(4, '1885-09-02', '258369147', '1640 Riverside Drive', 'Hill Valley', 'CA', '95420', '9165554385', 'TRICARE', '258369147', true, 'DNR', 'George McFly', '9168423138', 'Dr. Brown', '9165551234', '');


INSERT INTO authorized_user (user_id, authorized_email, authorized_name) VALUES
(1, 'agent@vlol.gov', 'Dr Johnson');