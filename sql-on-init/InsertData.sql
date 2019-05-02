INSERT INTO permission
VALUES
	(),
    (),
    ();
    
INSERT INTO user (permission, name, password)
VALUES
	(3, 'Admin', sha1('pass')),
    (2, 'Jan', sha1('kowal')),
    (1, 'Elon Musk', sha1('tesla')),
    (1, 'Einstein', sha1('e=mc^2')),
    (1, 'Austin Powers', sha1('1 mil. $'));
    
INSERT INTO user_achievement (user, points, description)
VALUES
	(1, 5, 'Bardzo ladny projekt zrobil'),
    (3, 20, 'Napisal Paypala');
    
INSERT INTO profile_link (user, type, url)
VALUES
	(2, 'image', 'https://images.emojiterra.com/google/android-nougat/512px/1f44c.png'),
    (3, 'twitter', 'https://twitter.com/mrevilmusk');
    
INSERT INTO project (name, description)
VALUES
	('taki Facebook, ale lepszy', 'hej, mam taki nowy pomysl na strone...'),
    ('aplikacja do grywalizacji', 'Aplikacja do grywalizacji dla Kola Naukowego');
    
INSERT INTO project_achievement (project, points, description)
VALUES
	(1, 50, 'na pewno sie sprzeda'),
    (1, 10, 'xD');
    
INSERT INTO task (project, points, description, due_date)
VALUES
	(1, 100, 'inwigilacja uzytkownikow', '2020-02-10'),
    (1, 10, 'messenger', '2019-06-30'),
    (2, 9999, 'deadline', '2019-05-29');
    
INSERT INTO project_to_user (user, project)
VALUES
	(3, 1),
    (2, 2),
    (4, 1),
    (5, 1),
    (4, 2);
    
