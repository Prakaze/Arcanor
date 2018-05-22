package joueur;

import plateau.Plateau;
import plateau.Piece;

abstract public class Joueur{

  /**
   * L'équipe du joueur
   */
  public final int EQUIPE;

  /**
   * Constructeur de la classe.
   * @param equipe L'équipe du joueur.
   */
  public Joueur(int equipe){

    this.EQUIPE = equipe;
  }


  /**
   * Les joueurs peuvent se bloquer, cette méthode sert a détecter quand les
   * joueurs sont bloqués.
   * @return vrai si le joueur est bloqué
   */
  public boolean estBloque(){

    boolean bloque = true;

    for(int y = 0; y < Plateau.tailleY && bloque; y++){

      for(int x = 0; x < Plateau.tailleX && bloque; x++){

        if(Plateau.pieceEn(x, y) != null){

          if(Plateau.pieceEn(x, y).getEquipe() == this.EQUIPE && !Plateau.pieceEn(x, y).getInnactive()){

            for(int autreY = y-1; autreY <= y+1 && bloque; autreY++){

              for(int autreX = x-1; autreX <= x+1 && bloque; autreX++){

                if(Plateau.typeMouvement(x, y, autreX, autreY) != Plateau.MOUV_IMPOSSIBLE){

                  bloque = false;
                }
              }
            }
          }
        }
      }
    }

    return bloque;
  }


  /**Cette méthode fait jouer le joueur et retourne un boolean pour
  * permettre l'arrêt de la partie en cours dans la classe Arcanor si le
  * joueur est un humain et saisit "quit" dans le terminal.
  */
  abstract public void joue();
}
