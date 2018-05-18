import plateau.Plateau;
import plateau.Piece;
import joueur.Joueur;
import joueur.Humain;
import joueur.IA;

public class Arcanor{

  /**
   * Premier joueur
   */
  private static Joueur joueur0;
  /**
   * Second joueur
   */
  private static Joueur joueur1;


  /**
   * Point d'entrée du programme.
   * @param args Les paramètres saisis lors de l'éxecution, inutiles sur ce
   * programme.
   */
  public static void main(String[] args){

    Plateau.chargePartie("permanent_saves/classic");

    joueur0 = new Humain(0);
    joueur1 = new IA(1);

    boolean jeuFini = false;
    int gagnant = -1;

    Plateau.print();

    while(!jeuFini && !(joueur0.estBloque() && joueur1.estBloque())){

      if(Plateau.getEquipeQuiJoue() == 0){

        joueur0.joue();

      } else {

        joueur1.joue();
      }

      if(!jeuFini){

        if(Plateau.points(Plateau.getEquipeQuiJoue()) >= 12){

          gagnant = Plateau.getEquipeQuiJoue();
          jeuFini = true;
        } else {

          Plateau.passeLeTour();
        }

        Plateau.print();
      }
    }

    if(gagnant == -1){

      if(jeuFini){
        System.out.println("\nJeu quitté");
      } else {
        System.out.println("\nJeu arrêté, tout les joueurs sont bloqués");
      }
    } else {

      System.out.println("\nLe joueur " + Integer.toString(gagnant) + " a gagné!");
    }
  }
}
