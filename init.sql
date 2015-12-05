CREATE TABLE IF NOT EXISTS Utilisateur (
    idUtilisateur INT UNSIGNED NOT NULL AUTO_INCREMENT,
    email VARCHAR(100) UNIQUE,
    motDePasse VARCHAR(100),
    PRIMARY KEY (idUtilisateur)
);

CREATE TABLE IF NOT EXISTS Labo (
    idLabo INT UNSIGNED NOT NULL AUTO_INCREMENT,
    idUtilisateur INT UNSIGNED NOT NULL,
    nom VARCHAR(100),
    PRIMARY KEY (idLabo),
    CONSTRAINT fk_labo_utilisateur -- On donne un nom à notre clé
    FOREIGN KEY (idUtilisateur) -- Colonne sur laquelle on crée la clé
    REFERENCES Utilisateur(idUtilisateur) 
);

CREATE TABLE IF NOT EXISTS Atelier (
    idAtelier INT UNSIGNED NOT NULL AUTO_INCREMENT,
    idLabo INT UNSIGNED NOT NULL,
    titre VARCHAR(100),
    themes VARCHAR(100),
    zone VARCHAR(100),
    adresse VARCHAR(100),
    orateurs VARCHAR(100),
    partenaires VARCHAR(100),
    cible VARCHAR(100),
    remarques VARCHAR(100),
    statut VARCHAR(100),
    PRIMARY KEY (idAtelier),
    CONSTRAINT fk_atelier_labo -- On donne un nom à notre clé
    FOREIGN KEY (idLabo) -- Colonne sur laquelle on crée la clé
    REFERENCES Labo(idLabo) 
);

CREATE TABLE IF NOT EXISTS Creneau (
    idCreneau INT UNSIGNED NOT NULL AUTO_INCREMENT,
    idAtelier INT UNSIGNED NOT NULL,
    jour DATE,
    heure TIME,
    capacite INT,
    PRIMARY KEY (idCreneau),
    CONSTRAINT fk_creneau_atelier -- On donne un nom à notre clé
    FOREIGN KEY (idAtelier) -- Colonne sur laquelle on crée la clé
    REFERENCES Atelier(idAtelier) 
);

CREATE TABLE IF NOT EXISTS Enseignant (
    idEnseignant INT UNSIGNED NOT NULL AUTO_INCREMENT,
    idUtilisateur INT UNSIGNED NOT NULL,
    nom VARCHAR(100),
    prenom VARCHAR(100),
    PRIMARY KEY (idEnseignant),
    CONSTRAINT fk_enseignant_utilisateur -- On donne un nom à notre clé
    FOREIGN KEY (idUtilisateur) -- Colonne sur laquelle on crée la clé
    REFERENCES Utilisateur(idUtilisateur) 
);

CREATE TABLE IF NOT EXISTS Enregistrement (
    idEnregistrement INT UNSIGNED NOT NULL AUTO_INCREMENT,
    idEnseignant INT UNSIGNED NOT NULL,
    idCreneau INT UNSIGNED NOT NULL,
    nbInscrits INT,
    PRIMARY KEY (idEnregistrement),
    CONSTRAINT fk_enregistrement_enseignant -- On donne un nom à notre clé
    FOREIGN KEY (idEnseignant) -- Colonne sur laquelle on crée la clé
    REFERENCES Enseignant(idEnseignant), 
    CONSTRAINT fk_enregistrement_creneau -- On donne un nom à notre clé
    FOREIGN KEY (idCreneau) -- Colonne sur laquelle on crée la clé
    REFERENCES Creneau(idCreneau)
);

CREATE TABLE IF NOT EXISTS CNRS (
    idCNRS INT UNSIGNED NOT NULL AUTO_INCREMENT,
    idUtilisateur INT UNSIGNED NOT NULL,
    PRIMARY KEY (idCNRS),
    CONSTRAINT fk_CNRS_utilisateur -- On donne un nom à notre clé
    FOREIGN KEY (idUtilisateur) -- Colonne sur laquelle on crée la clé
    REFERENCES Utilisateur(idUtilisateur) 
);