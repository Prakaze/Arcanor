package joueur;

import commandes.CommandeExecuteur;
import commandes.InvalidCommandException;
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
   */
  public void joue(){

    if(this.estBloque()){

      System.out.println("Le joueur est bloqué, passe son tour.");

    } else{
      System.out.println("Joueur " + this.EQUIPE + ", saisissez votre mouvement:\n");

      boolean aJoue = false;

      while(!aJoue){

        try{
          CommandeExecuteur.execute(sc.nextLine());
          aJoue = true;
        } catch(InvalidCommandException e){
          System.out.println(e.getMessage() + ", veuillez resaisir la ligne:");
          aJoue = false;
        }
      }
    }
  }
}
