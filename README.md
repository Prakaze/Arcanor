# Arcanor

Voici l'avancée du projet Arcanor (pour l'instant juste la version dans le terminal, sans IHM).

N'hésitez pas a jeter un coup d'oeil a la javadoc, je l'actualise a chaque modification.

Pour ce qui est de tester le jeu, il fonctionne de cette facon:

L'utilisateur devra saisir un mouvement dans le terminal sous la forme: "positionPieceX positionPieceY positionCaseDestinationX positionCaseDestinationY DecouvreSaPiece". Par exemple, pour faire un mouvement de la pièce en 4 1 vers la case 5 0 sans découvrir la pièce couverte, l'utilisateur devra saisir: "4 1 5 0 false". Si l'utilisateur souhaite quitter, il devra écrire "quit" dans le terminal.

Vous pouvez faire jouer l'IA contre elle même en modifiant dans le fichier Arcanor.java (ligne 28):

joueur0 = new Humain(0);

joueur1 = new IA(1);

par:
joueur0 = new IA(0);

joueur1 = new IA(1);

Cependant, l'IA peut toujours se bloquer en bouclant sur le même coup tout les n tours.

Vous pouvez bien entendu faire jouer deux humains entre eux sans problèmes en suivant la même logique.

Pour lancer le jeu sur windows, saisir dans le terminal:
javac -d ..\class ..\src\plateau\*.java ..\src\outils\*.java ..\src\joueur\*.java ..\src\*.java && java Arcanor

~Loïc
