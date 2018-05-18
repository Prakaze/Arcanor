package joueur;

import plateau.Plateau;
import plateau.Piece;
import java.util.Scanner;

public class Humain extends Joueur{

  /**
   * Le scanner permettant la saisie depuis le terminal.
   */
  private Scanner sc;


  /**
   * Constructeur de la classe.
   * @param equipe l'équipe du joueur.
   */
  public Humain(int equipe){

    super(equipe);
    sc = new Scanner(System.in);
  }
  

  /**
   * Fonction demandant à un joueur dont l'équipe est précisée d'entrer un
   * mouvement dans le terminal sous la forme
   * "[posX pièce] [posY pièce] [posX destination] [posY destination] [découvre]".
   * Par exemple, un mouvement de la pièce en 0 0 vers la case 0 1 sans
   * découvrir la pièce couverte donnera: "0 0 0 1 false". Si l'utilisateur
   * souhaite quitter, il devra écrire "quit" dans le terminal. Si la commande
   * ne contient pas le bon nombre de paramètres, le programme redemande une
   * saisie. Si le mouvement saisi est impossible, le programme redemande une
   * saisie. Cette méthode retourne un boolean pour permettre l'arrêt de la
   * partie en cours dans la classe Arcanor quand le joueur saisit "quit" dans
   * le terminal.
   * @return vrai si le joueur a saisi "quit" et veut quitter la partie.
   */
  public boolean joue(){

    System.out.println("Joueur " + this.EQUIPE + ", saisissez votre mouvement:\n");

    boolean continuer = true;
    boolean jeuFini = false;

    do{

      String[] parts;

      do{

        String saisie = sc.nextLine();
        parts = saisie.split(" ");

        if(saisie.equals("quit")){

          jeuFini = true;

        } else if(parts.length == 5){

          continuer = true;

        } else {

          System.out.println("\nLigne invalide, veuillez re-saisir la ligne:\n");
          continuer = false;
        }

      } while(!continuer && !jeuFini);

      if(!jeuFini){

        int x = Integer.parseInt(parts[0]);
        int y = Integer.parseInt(parts[1]);
        int autreX = Integer.parseInt(parts[2]);
        int autreY = Integer.parseInt(parts[3]);
        boolean decouvre = Boolean.parseBoolean(parts[4]);

        int mouv = Plateau.typeMouvement(x, y, autreX, autreY);

        if(mouv == Plateau.MOUV_IMPOSSIBLE){

          System.out.println("\nMouvement impossible, veuillez re-saisir la ligne:\n");
          continuer = false;
        } else {

          Plateau.faireMouvement(x, y, autreX, autreY, decouvre);
          continuer = true;
        }
      }

    } while(!continuer && !jeuFini);

    return jeuFini;
  }
}
