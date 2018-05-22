# Arcanor

Voici l'avancée du projet Arcanor (pour l'instant juste la version dans le terminal, sans IHM).

N'hésitez pas a jeter un coup d'oeil a la javadoc, je l'actualise a chaque modification.

Pour ce qui est de tester le jeu, il fonctionne de cette facon:

l'utilisateur pourra saisir différentes commandes dans le terminal:
  - deplacer <x:int> <y:int> <autreX:int> <autreY:int> <decouvrir:boolean>
      pour déplacer une pièce
  - quitter
      pour quitter le jeu, utilizez cette commande (ne surtout pas faire un CTL+C, ca fait planter le processus)
  - charger <nomSauvegarde>
      pour charger une sauvegarde
  - sauvegarder <nomSauvegarde>
      pour sauvegarder une partie

Vous pouvez faire jouer l'IA contre elle même en modifiant dans le fichier Arcanor.java (ligne 28):

joueur0 = new Humain(0);

joueur1 = new IA(1);

par:

joueur0 = new IA(0);

joueur1 = new IA(1);

Vous pouvez bien entendu faire jouer deux humains entre eux sans problèmes en suivant la même logique.

Pour lancer le jeu sur windows, saisir dans le terminal:
javac -d ..\class ..\src\plateau\*.java ..\src\outils\*.java ..\src\joueur\*.java ..\src\*.java && java Arcanor

~Loïc
