INSERT INTO organiser (name) VALUES ('Test Organiser 1');
INSERT INTO organiser (name) VALUES ('Test Organiser 2');

INSERT INTO event (title, description, dbs_required, address, city, postal_code, landmark, roles_needed, rewards_offering, image, date, start_time, end_time, accessibility_assistance_provided, organiser_id, approved)
VALUES ('Event 1', 'Description 1', TRUE, '123 Main St', 'Test City', '12345', 'Near the park', 'Role1, Role2', 'Reward1, Reward2', 'image1.png', '2024-08-19', '09:00:00', '12:00:00', 'None', 1, TRUE);

INSERT INTO event (title, description, dbs_required, address, city, postal_code, landmark, roles_needed, rewards_offering, image, date, start_time, end_time, accessibility_assistance_provided, organiser_id, approved)
VALUES ('Event 2', 'Description 2', FALSE, '456 Other St', 'Other City', '67890', 'Near the lake', 'Role3, Role4', 'Reward3, Reward4', 'image2.png', '2023-07-19', '10:00:00', '14:00:00', 'Wheelchair accessible', 2, TRUE);
