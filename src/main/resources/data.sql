INSERT INTO appuser (user_id, role_id, first_name, last_name, email, password, date_created, last_login_date, admin_comments, is_active, is_locked, is_verified) VALUES 
(1, 1, 'Lorraine', 'Baines', 'parti@vlol.gov', '$2a$10$wbBBPoyyiMNr5sUbInHCM.rrXk8PfbfLTkWJ9KEglBynBFaNSmjsS', '2020-05-20 16:30:00', '2020-06-23 15:10:00', 'Lorraine is a program participant.', true, false, true),
(2, 2, 'Marty', 'McFly', 'provi@vlol.gov', '$2a$10$wbBBPoyyiMNr5sUbInHCM.rrXk8PfbfLTkWJ9KEglBynBFaNSmjsS', '2020-05-20 18:30:00', '2020-06-23 15:10:00', 'Marty is an emergency medical provider.', true, false, true),
(3, 1, 'Emmett', 'Brown', 'agent@vlol.gov', '$2a$10$wbBBPoyyiMNr5sUbInHCM.rrXk8PfbfLTkWJ9KEglBynBFaNSmjsS', '2020-05-20 19:30:00', '2020-06-23 15:20:00', 'Emmett is a Lorraine''s agent.', true, false, true),
(4, 3, 'Seamus', 'McFly', 'admin@vlol.gov', '$2a$10$wbBBPoyyiMNr5sUbInHCM.rrXk8PfbfLTkWJ9KEglBynBFaNSmjsS', '2020-05-20 20:30:00', '2020-06-23 15:30:00', 'Seamus is a system administrator.', true, false, true);

INSERT INTO user_info (user_id, dob, ssn, street_address, city, us_state, zipcode, phone, ins_company, ins_policy_no, poc_name, poc_phone, doctor_name, doctor_phone, user_comments, is_deceased) VALUES 
(1, '2015-10-21', '123456789', '1711 Bushnell Ave', 'South Pasadena', 'CA', '91030', '9165555555', 'Aetna', '123456789', 'Marty McFly', '9168423138',  'Dr. Brown', '9165551234', '', false),
(2, '1985-10-26', '456789123', '9303 Roslyndale Ave', 'Arleta', 'CA', '91331', '9168423138', 'Aetna', '456789123', 'Lorraine Baines', '9165555555', 'Dr. Brown', '9165551234', '', false),
(3, '1955-11-05', '147258369', '4 Westmoreland Pl', 'Pasadena', 'CA', '91103', '9168675309', 'CareFirst', '147258369', 'Marty McFly', '9168423138', 'Dr. Tannen', '9165551234', '', false),
(4, '1885-09-02', '258369147', '1640 Riverside Drive', 'Hill Valley', 'CA', '95420', '9165554385', 'TRICARE', '258369147', 'George McFly', '9168423138', 'Dr. Brown', '9165551234', '', false);


INSERT INTO authorized_user (authorized_user_id, user_id, authorized_email, authorized_name) VALUES
(1, 1, 'agent@vlol.gov', 'Dr Johnson');
