package joueur;

import plateau.Plateau;
import plateau.Piece;
import java.util.ArrayList;
import plateau.InvalidMoveException;

public class IA extends Joueur{


  /**
   * Constructeur de la classe, créé une nouvelle IA.
   * @param equipe L'équipe de l'IA.
   */
  public IA(int equipe){

    super(equipe);
  }

  /**
   * Fait jouer l'IA sur le plateau.
   * L'IA repère tout les mouvements possibles avec ses pièces et joue un
   * mouvement aléatoire parmis eux, en favorisant la couverture de pièces
   * ennemies.
   */
  public void joue(){

    if(this.estBloque()){

      System.out.println("L'IA est bloquée, passe son tour.");

    } else{

      ArrayList<Mouvement> mouvSimples = new ArrayList<Mouvement>();
      ArrayList<Mouvement> mouvCouvres = new ArrayList<Mouvement>();

      for(int y = 0; y < Plateau.tailleY; y++){

        for(int x = 0; x < Plateau.tailleX; x++){

          if(Plateau.pieceEn(x, y) != null){

            if(Plateau.pieceEn(x, y).getEquipe() == this.EQUIPE && !Plateau.pieceEn(x, y).getInnactive()){

              for(int autreY = y-1; autreY <= y+1; autreY++){

                for(int autreX = x-1; autreX <= x+1; autreX++){

                  if(Plateau.typeMouvement(x, y, autreX, autreY) == Plateau.MOUV_SIMPLE){

                    mouvSimples.add(new Mouvement(x, y, autreX, autreY, true));
                    mouvSimples.add(new Mouvement(x, y, autreX, autreY, false));

                  } else if (Plateau.typeMouvement(x, y, autreX, autreY) == Plateau.MOUV_COUVRE){

                      mouvCouvres.add(new Mouvement(x, y, autreX, autreY, true));
                  }
                }
              }
            }
          }
        }
      }

      try{
        if(mouvCouvres.size() > 0 && Math.random() > .95){

          int index = (int)(Math.random() * mouvCouvres.size());
          mouvCouvres.get(index).effectuer();
          System.out.println("L'IA " + this.EQUIPE + " joue:");

        } else if(mouvSimples.size() > 0){

          int index = (int)(Math.random() * mouvSimples.size());
          mouvSimples.get(index).effectuer();
          System.out.println("L'IA " + this.EQUIPE + " joue:");

        } else  if(mouvCouvres.size() > 0){

    	  int index = (int)(Math.random() * mouvCouvres.size());
          mouvCouvres.get(index).effectuer();
          System.out.println("L'IA " + this.EQUIPE + " joue:");

        } else {

          System.out.println("L'IA " + this.EQUIPE + " est bloquée, passe son tour");
        }
      } catch(InvalidMoveException e){

        e.printStackTrace();
      }
    }
  }
}
