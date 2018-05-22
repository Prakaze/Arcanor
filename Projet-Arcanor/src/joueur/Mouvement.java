package joueur;

import plateau.InvalidMoveException;
import plateau.Plateau;

/**
 * Classe permettant à l'IA de stocker des mouvements et de les trier.
 */
class Mouvement{

  /**
   * la position en x de la piece que l'on souhaite déplacer
   */
  protected int x;
  /**
   * la position en y de la piece que l'on souhaite déplacer
   */
  protected int y;
  /**
   * la position en x de la case du damier sur laquelle on
   * souhaite déplacer la pièce
   */
  protected int autreX;
  /**
   * la position en y de la case du damier sur laquelle on
   * souhaite déplacer la pièce
   */
  protected int autreY;
  /**
   * boolean valant vrai si l'on veut découvrir la potentielle
   * piece cachée au prochain coup
   */
  public boolean decouvre;

  /**
  * Constructeur de la classe.
  * @param x la position en x de la piece que l'on souhaite déplacer
  * @param y la position en y de la piece que l'on souhaite déplacer
  * @param autreX la position en x de la case du damier sur laquelle on
  * souhaite déplacer la pièce
  * @param autreY la position en y de la case du damier sur laquelle on
  * souhaite déplacer la pièce
  * @param decouvre boolean valant vrai si l'on veut découvrir la potentielle
  * piece cachée au prochain coup
  */
  public Mouvement(int x, int y, int autreX, int autreY, boolean decouvre){

    this.x = x;
    this.y = y;
    this.autreX = autreX;
    this.autreY = autreY;
    this.decouvre = decouvre;
  }

  /**
   * Effectue le mouvement enregistré.
   */
  public void effectuer() throws InvalidMoveException{
    
    Plateau.faireMouvement(this.x, this.y, this.autreX, this.autreY, this.decouvre);
  }
}
