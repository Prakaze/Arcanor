package interaction;

import joueur.Humain;
import commandes.CommandeExecuteur;
import commandes.InvalidCommandException;
import java.util.Scanner;

class InteractionTerminal implements Interaction{

  /**
   * Le joueur associé
   */
  private Humain joueur;

  /**
   * Le scanner permettant la saisie depuis le terminal.
   */
  private Scanner sc;


  /**
   * @param joueur le joueur associé à l'objet.
   */
  public InteractionTerminal(Humain joueur){

    this.joueur = joueur;
    this.sc = new Scanner(System.in);
  }


  /**
   * Cette méthode demande au joueur de saisir une commande, puis la transmet à
   * l'éxécuteur de commande. Le programme redemande de saisir la commande tant
   * qu'elle n'est pas valide. Voir la liste des commandes disponibles dans la
   * classe CommandeExecuteur pour plus d'informations.
   */
  public void demandeInteration(){

    System.out.println("Joueur " + this.joueur.EQUIPE + ", saisissez votre mouvement:\n");

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
