
-- Insertion dans Type_Adherent
INSERT INTO Type_Adherent (
    nom_type, quota_max_pret, duree_max_pret,
    quota_max_prolongement, duree_max_prolongement
) VALUES
    ('Etudiant', 5, 15, 2, 7),
    ('Enseignant', 10, 30, 3, 10);

-- Insertion dans Adherent
INSERT INTO Adherent (
    nom, prenom, password, date_naissance, date_inscription,
    id_type, est_abonne, est_penalise
) VALUES
    ('Rakoto', 'Jean', 'pass123', '1990-05-15', CURRENT_DATE, 1, TRUE, FALSE),
    ('Rasoa', 'Mira', 'azerty', '1985-11-22', CURRENT_DATE, 2, TRUE, FALSE),
    ('Andry', 'Nina', 'nina2024', '2000-03-10', CURRENT_DATE, 1, FALSE, TRUE);

-- Insertion dans Livre
INSERT INTO Livre (
    titre, auteur, isbn, date_publication, age_restriction, nbr_exmp, description, image_url
) VALUES
    ('Le Petit Prince', 'Antoine de Saint-Exupéry', '9782070612758', '1943-04-06', 0, 5, 'Conte philosophique', 'lepetitprince.jpg'),
    ('1984', 'George Orwell', '9780451524935', '1949-06-08', 16, 3, 'Roman dystopique', '1984.jpg');

-- Insertion dans Type_Utilisation
INSERT INTO Type_Utilisation (nom_type_utilisation) VALUES
    ('Lecture sur place'),
    ('Emprunt à domicile');

-- Insertion dans Droit_Pret
INSERT INTO Droit_Pret (id_type_adherent, id_type_utilisation, id_livre, autorise) VALUES
    (1, 1, 1, TRUE),
    (2, 2, 2, TRUE);

-- Insertion dans Statut_Pret
INSERT INTO Statut_Pret (nom_statut) VALUES
    ('disponible'),
    ('emprunté'),
    ('rendu');

-- Insertion dans Exemplaire
INSERT INTO Exemplaire (id_livre, code_exemplaire, id_status) VALUES
    (1, 'LP-001', 1),
    (1, 'LP-002', 2),
    (2, '1984-001', 1);

-- Insertion dans Type_Pret
INSERT INTO Type_Pret (nom_type_pret, duree_max) VALUES
    ('Lecture sur place', 0),
    ('Prêt à domicile', 15);
