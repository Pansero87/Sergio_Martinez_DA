INSERT INTO `BDJocs`.`Joc` (`id`, `nom`, `descripció`, `Genere_id`) VALUES (1, 'Juego de Aventuras', 'Un emocionante juego de aventuras en un mundo mágico.', 1);
INSERT INTO `BDJocs`.`Joc` (`id`, `nom`, `descripció`, `Genere_id`) VALUES (2, 'Carrera Extrema', 'Participa en emocionantes carreras de coches a toda velocidad.', 2);
INSERT INTO `BDJocs`.`Joc` (`id`, `nom`, `descripció`, `Genere_id`) VALUES (3, 'Rompecabezas Desafiantes', 'Resuelve rompecabezas desafiantes y pone a prueba tu mente.', 3);


INSERT INTO `BDJocs`.`Genere` (`id`, `nom`, `descripció`) VALUES (1,"Arcade", "Es caracteritzen per la jugabilitat simple, repetitiva i d\'acció ràpida.");
INSERT INTO `BDJocs`.`Genere` (`id`, `nom`, `descripció`) VALUES (2,"Plataformes","Es controla a un personatge que ha d\'avançar per un escenari evitant obstacles");
INSERT INTO `BDJocs`.`Genere` (`id`, `nom`, `descripció`) VALUES (3,"Shoot\'em up","Jocs de dispars amb perspectiva en dues dimensions, caracteritzats per l\'ús continu de dispars, la millora de les armes, l\'avança automàtic i l\'enfrontament amb enemics de grantamany al final de cada missió.");
INSERT INTO `BDJocs`.`Genere` (`id`, `nom`, `descripció`) VALUES (4,"Agilitat mental", "Posen a prova la intel·ligéncia del jugador per a la resolució de problemes, bé de caràcter matemàtic, espacial o lògic");


INSERT INTO `BDJocs`.`Jugador` (`id`, `nick`, `dataregistre`) VALUES (1, 'Jugador1', '2023-11-08');
INSERT INTO `BDJocs`.`Jugador` (`id`, `nick`, `dataregistre`) VALUES (2, 'Gamer2', '2023-11-07');
INSERT INTO `BDJocs`.`Jugador` (`id`, `nick`, `dataregistre`) VALUES (3, 'MaestroGamer', '2023-11-06');


INSERT INTO `BDJocs`.`Puntuacions` (`jugador_id`, `Joc_id`, `puntuacio`) VALUES (1, 1, 100);
INSERT INTO `BDJocs`.`Puntuacions` (`jugador_id`, `Joc_id`, `puntuacio`) VALUES (2, 2, 85);
INSERT INTO `BDJocs`.`Puntuacions` (`jugador_id`, `Joc_id`, `puntuacio`) VALUES (3, 3, 120);
