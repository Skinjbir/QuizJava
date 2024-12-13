-- Création de la base de données
CREATE DATABASE IF NOT EXISTS quiz_db;

-- Utilisation de la base de données
USE quiz_db;

-- Création de la table 'questions'
CREATE TABLE IF NOT EXISTS questions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    question_text VARCHAR(255) NOT NULL,
    option1 VARCHAR(100) NOT NULL,
    option2 VARCHAR(100) NOT NULL,
    option3 VARCHAR(100) NOT NULL,
    option4 VARCHAR(100) NOT NULL,
    correct_option INT NOT NULL CHECK (correct_option BETWEEN 1 AND 4)
);

-- Insertion de 10 questions dans la table 'questions'
INSERT INTO questions (question_text, option1, option2, option3, option4, correct_option) VALUES
('Quel mot-clé permet de créer une classe en Java ?', 'class', 'public', 'void', 'static', 1),
('Quel mot-clé est utilisé pour hériter d’une classe en Java ?', 'implements', 'inherit', 'extends', 'super', 3),
('Laquelle des propositions suivantes n\'est pas un type primitif en Java ?', 'int', 'boolean', 'String', 'double', 3),
('Quelle est la portée d\'une variable déclarée avec "public" dans une classe ?', 'Seule la classe', 'Seul le package', 'Toutes les classes du package', 'Toutes les classes de l’application', 4),
('Quelle méthode est appelée au démarrage d\'un programme Java ?', 'constructor', 'main', 'init', 'start', 2),
('Lequel de ces types de données a la plus grande capacité de stockage ?', 'int', 'long', 'short', 'byte', 2),
('Quel mot-clé permet de déclarer une constante en Java ?', 'const', 'final', 'static', 'val', 2),
('Comment appelle-t-on le processus de convertir un objet en un flux d\'octets ?', 'Compaction', 'Serialization', 'Encapsulation', 'Abstraction', 2),
('Quel est le résultat de l’opération 5 / 2 en Java (avec des types entiers) ?', '2.5', '2', '3', 'Erreur de compilation', 2),
('Quelle exception est levée lorsqu\'un tableau est accédé avec un indice invalide ?', 'NullPointerException', 'IndexOutOfBoundsException', 'IOException', 'ArrayIndexOutOfBoundsException', 4);
