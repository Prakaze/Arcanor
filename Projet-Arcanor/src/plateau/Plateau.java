package plateau;

import java.util.ArrayList;
import java.util.Iterator;
import java.io.FileNotFoundException;
import outils.RWFile;

public class Plateau{

  /**
   * Le tableau de piece représentant le damier de jeu. Chaque case contient une
   * pièce ou bien un null si la case est vide.
   */
  private static Piece[][] damier;

  /**
   * Le numéro de l'équipe qui joue.
   */
  private static int equipeQuiJoue;

  /**
   * La taille en x du damier
   */
  public static int tailleX;
  /**
   * La taille en y du damier
   */
  public static int tailleY;

  /**
   * Constante statique correspondant à un mouvement impossible retourné par la
   * méthode typeMouvement()
   */
  public static final int MOUV_IMPOSSIBLE = 0;
  /**
   * Constante statique correspondant à un déplacement simple retourné par la
   * méthode typeMouvement()
   */
  public static final int MOUV_SIMPLE = 1;
  /**
   * Constante statique correspondant à un déplacement couvrant une pièce
   * retourné par la méthode typeMouvement()
   */
  public static final int MOUV_COUVRE = 2;


  /**
   * Méthode permettant la sauvegarde d'une partie dans un fichier texte dont le
   * nom est donné.
   * @param nomSauvegarde le nom du fichier texte utilisé pour la sauvegarde. Il
   * sera automatiquement stocké dans /data, et l'extension .txt sera elle aussi
   * rajoutée. Par exemple, pour créer le fichier /data/exemple.txt, le
   * paramètre nomSauvegarde devra prendre la valeur "exemple". On considère que
   * le nom ne recevera pas de caractères 'interdits' pour le nommage de
   * fichiers ni de '/' qui vont créer une erreur.
   * @throws FileNotFoundException lorsque le nom de fichier spécifié n'existe
   * pas
   */
  public static void sauvePartie(String nomSauvegarde) throws FileNotFoundException{

    ArrayList<String> fichier = new ArrayList<String>();

    fichier.add(Integer.toString(equipeQuiJoue));
    fichier.add(Integer.toString(tailleX) + "," + Integer.toString(tailleY));

    for(int y = 0; y < tailleY; y++){

      for(int x = 0; x < tailleX; x++){

        if(damier[x][y] != null){

          fichier.add(Integer.toString(x) + "," + Integer.toString(y) + " " + damier[x][y].toString());
        }
      }
    }

    RWFile.writeFile(fichier, "../data/" + nomSauvegarde + ".txt");
  }

  /**
   * Cette fonction permet de passer le tour d'une équipe à l'autre.
   */
  public static void passeLeTour(){

    equipeQuiJoue++;

    if(equipeQuiJoue >= 2){

      equipeQuiJoue = 0;
    }
  }

  /**
   * Constructeur de la classe, initialise le plateau avec un tableau de String
   * indiquant la position des Pieces sur le plateau. Permet le chargement/la
   * reprise de parties sauvegardées.
   * @param nomSauvegarde le nom du fichier texte utilisé pour le chargement. Il
   * sera automatiquement stocké dans /data, et l'extension .txt sera elle aussi
   * rajoutée. Par exemple, pour charger le fichier /data/exemple.txt, le
   * paramètre nomSauvegarde devra prendre la valeur "exemple".
   * @throws FileNotFoundException lorsque le nom de fichier spécifié n'existe
   * pas
   */
  public static void chargePartie(String nomSauvegarde) throws FileNotFoundException{

    ArrayList<String> fichier = RWFile.readFile("../data/" + nomSauvegarde + ".txt");

    Iterator<String> it = fichier.iterator();

    equipeQuiJoue = Integer.parseInt(it.next());

    String[] dimensions = it.next().split(",");

    tailleX = Integer.parseInt(dimensions[0]);
    tailleY = Integer.parseInt(dimensions[1]);

    damier = new Piece[tailleX][tailleY];

    while(it.hasNext()){

      ajoutePiece(it.next());
    }
  }


  /**
   *  Ajoute une pièce dans le damier a partir d'une ligne de texte tirée d'un
   * fichier texte de sauvegarde de partie.
   * @param ligne La ligne de texte précisant les paramètres de la pièce a créer
   * et sa position dans le damier. Ses informations sont stockées dans la ligne
   * sous la forme: "posX,posY paramPiece".'paramPiece' correspond au résultat
   * de la méthode toString() de la classe pièce, pour plus d'informations, voir
   * la méthode toString() de la classe pièce.
   */
  private static void ajoutePiece(String ligne){

    String[] parties = ligne.split(" ");

    String[] dimensions = parties[0].split(",");

    int posX = Integer.parseInt(dimensions[0]);
    int posY = Integer.parseInt(dimensions[1]);

    damier[posX][posY] = new Piece(parties[1]);
  }


  /**
   * Méthode permettant d'acceder à une case du damier. Les coordonnées de la
   * case doivent être comprises dans les bornes du damier.
   * @param x La position en x de la case dans le damier
   * @param y La position en y de la case dans le damier
   * @return La piece sur la case, null si la case est vide
   */
  public static Piece pieceEn(int x, int y){

    return damier[x][y];
  }


  /**
   * Cette méthode permet de déduire si un mouvement entre deux cases est
   * possible ou non. Si le mouvement est possible, elle indiquera le type de
   * mouvement provoqué.
   * @param x la position en x de la piece que l'on souhaite déplacer, ne doit
   * pas être Null
   * @param y la position en y de la piece que l'on souhaite déplacer, ne doit
   * pas être Null
   * @param autreX la position en x de la case du damier sur laquelle on
   * souhaite déplacer la pièce
   * @param autreY la position en y de la case du damier sur laquelle on
   * souhaite déplacer la pièce
   * @return 0 si le mouvement est impossible (déplacement en dehors du damier,
   * partant d'une case sans pièce, ), 1 s'il s'agit d'un déplacement simple ou
   * 2 s'il va falloir couvrir la piece de la case destination.
   */
  public static int typeMouvement(int x, int y, int autreX, int autreY){

    int mouvement = Plateau.MOUV_IMPOSSIBLE;

    if(estDansDamier(x, y) && estDansDamier(autreX, autreY) && sontVoisines(x, y, autreX, autreY)){

      if(damier[x][y] != null){

        if(!damier[x][y].getInnactive() && damier[x][y].getEquipe() == equipeQuiJoue){

          if(damier[autreX][autreY] == null){

            mouvement = Plateau.MOUV_SIMPLE;

          } else if(damier[x][y].peutCouvrir(damier[autreX][autreY])){

            mouvement = Plateau.MOUV_COUVRE;
          }
        }
      }
    }

    return mouvement;
  }



  /**
   * Méthode vérifiant que deux positions sont voisines.
   * @param x la position en x de la premiere pièce
   * @param y la position en y de la premiere pièce
   * @param autreX la position en x de la seconde pièce
   * @param autreY la position en y de la seconde pièce
   * @return vrai si les pièces sont voisines
   */
  private static boolean sontVoisines(int x, int y, int autreX, int autreY){

    int deltaX = Math.abs(x - autreX);
    int deltaY = Math.abs(y - autreY);

    return (deltaX == 1 && deltaY <= 1) || (deltaY == 1 && deltaX <= 1);
  }


  /**
   * Méthode comptant les points de l'équipe précisée.
   * @param equipe l'équipe dont on veut compter les points
   * @return le compte des points de l'équipe
   */
  public static int points(int equipe){

    int y = rangeeEnnemie(equipe);

    int compte = 0;
    for(int x = 0; x < tailleX; x++){

      Piece p = damier[x][y];
      if(p != null){

        if(p.getEquipe() == equipe) compte += p.getTaille();
      }
    }

    return compte;
  }


  /**
   * Méthode retournant le numéro de l'équipe qui joue.
   * @return L'équipe dont c'est le tour.
   */
  public static int getEquipeQuiJoue(){

    return equipeQuiJoue;
  }


  /**
   * Methode vérifiant que les coordonnées passées en paramètre pointent bien
   * une case du damier
   * @param x coordonnée en x de la case du damier
   * @param y coordonnée en y de la case du damier
   * @return vrai si les coordonnées passées en paramètre pointent bien une case
   * du damier
   */
  private static boolean estDansDamier(int x, int y){

    return (x >= 0 && x < tailleX) && (y >= 0 && y < tailleY);
  }


  /**
   * Cette méthode permet d'effectuer un mouvement d'une case a une autre sur
   * une piece. Le mouvement devra être vérifié possible avec la fonction
   * typeMouvement() avant d'appeller la fonction. Si la piece atteind la ligne
   * ennemie, elle sera alors immobilisée.
   * @param x la position en x de la piece que l'on souhaite déplacer
   * @param y la position en y de la piece que l'on souhaite déplacer
   * @param autreX la position en x de la case du damier sur laquelle on
   * souhaite déplacer la pièce
   * @param autreY la position en y de la case du damier sur laquelle on
   * souhaite déplacer la pièce
   * @param decouvre boolean valant vrai si l'on veut découvrir la potentielle
   * piece cachée au prochain coup
   * @throws InvalidMoveException lorsque le mouvement donné est invalide
   */
  public static void faireMouvement(int x, int y, int autreX, int autreY, boolean decouvre) throws InvalidMoveException{

    int mouvement = typeMouvement(x, y, autreX, autreY);

    if(mouvement != Plateau.MOUV_IMPOSSIBLE){

      Piece pieceQuiBouge = damier[x][y];
      Piece pieceDestination = damier[autreX][autreY];
      Piece pieceDessous = pieceQuiBouge.getPieceDessous();

      if(mouvement == Plateau.MOUV_SIMPLE){

        damier[autreX][autreY] = pieceQuiBouge;

        if(decouvre){

          damier[x][y] = pieceDessous;
          pieceQuiBouge.setPieceDessous(null);

        } else {

          damier[x][y] = null;
        }

      } else if(mouvement == Plateau.MOUV_COUVRE) {

        damier[x][y] = pieceDessous;
        damier[autreX][autreY] = pieceQuiBouge;
        pieceQuiBouge.setPieceDessous(pieceDestination);
      }

      if(autreY == rangeeEnnemie(damier[autreX][autreY].getEquipe())){

        damier[autreX][autreY].setInnactive(true);
      }
    } else {

      throw new InvalidMoveException("Mouvement invalide saisi");
    }
  }


  /**
   * Méthode retournant, pour une équipe donnée, l'indice de la rangée ennemie
   * sur laquelle elle doit marquer des points
   * @param equipe L'équipe donnée
   * @return Le numéro de la rangée ennemie
   */
  private static int rangeeEnnemie(int equipe){

    int rangee = 0;
    if(equipe == 0) rangee = tailleY - 1;

    return rangee;
  }


  /**
   * Méthode permettant d'afficher le damier dans le terminal. Les
   * pièces de l'équipe 0 sont affichées entre parentèses, et celles
   * de l'équipe 1 sont affichées entre crochets.
   */
  static public void print(){

    String texte = "\n-----------------------------------\n  ";

    for(int x = 0; x < tailleX; x++){

      texte += "   " + Integer.toString(x);
    }
    texte += "\n";

    for(int y = 0; y < tailleY; y++){

      texte += "\n" + Integer.toString(y) + "  ";
      for(int x = 0; x < tailleX; x++){

        if(damier[x][y] == null){

          texte += "  . ";
        } else {

          if(damier[x][y].getEquipe() == 0){

            texte += " (" + Integer.toString(damier[x][y].getTaille()) + ")";

          } else if(damier[x][y].getEquipe() == 1){

            texte += " [" + Integer.toString(damier[x][y].getTaille()) + "]";
          } else {

			texte += "    ";
		  }
        }
      }
    }

    texte += "\n-----------------------------------";

    System.out.println(texte);
  }
}
