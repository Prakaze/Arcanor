package joueur;

public class Humain extends Joueur{

  /**
   * permet l'interaction du joueur physique avec le programme
   */
  private Interaction it;


  /**
   * Constructeur de la classe.
   * @param equipe l'équipe du joueur.
   */
  public Humain(int equipe){

    super(equipe);
    it = new InteractionTerminal(this);
  }


  /**
   * Fonction demandant à un joueur humain de jouer.
   */
  public void joue(){

    if(this.estBloque()){

      System.out.println("Le joueur est bloqué, passe son tour.");

    } else{

      it.demandeInteration();
    }
  }
}
