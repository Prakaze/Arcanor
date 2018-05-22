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
   * Fonction demandant à un joueur humain de jouer. Jouer consiste à saisir une
   * commande qui sera executée par la classe CommandeExecuteur. Voir la liste
   * des commandes disponibles dans la classe CommandeExecuteur pour plus
   * d'informations.
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
