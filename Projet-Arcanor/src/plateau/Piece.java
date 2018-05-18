package plateau;

public class Piece{

  /**
   * La taille de la pièce
   */
  private final int TAILLE;
  /**
   * L'équipe de la pièce
   */
  private final int EQUIPE;
  /**
   * Une autre pièce que couvre la pièce, vaut null si la pièce est vide
   */
  private Piece pieceDessous;
  /**
   * Indique quand la piece est définitivement imobilisée et invulnérable, quand
   * elle est sur la ligne adverse et marque des points
   */
   private boolean innactive;


  /**
   * Constructeur de la classe pour créer une piece simple
   * @param taille La taille de la pièce
   * @param equipe L'équipe de la pièce
   */
  public Piece(int taille, int equipe){

    this.TAILLE = taille;
    this.EQUIPE = equipe;
    this.innactive = false;
  }


  /**
   * Constructeur de la classe pour créer une piece a partir d'une ligne de
   * texte de la forme "TAILLE,EQUIPE,innactive". Si la pièce en couvre une
   * autre, le paramètres de la pièce couverte sont mis sur la même ligne après
   * un '/'. La validité des pièces n'est pas vérifiée (une piece pourrait en
   * couvrir une autre bien plus grosse qu'elle, elle pourrait être en dehors du
   * damier etc).
   * @param ligne Paramètres enregistrés dans un String sous la forme donnée par
   * la mérhode toString(), voir la javaDoc de la méthode toString() pour plus
   * d'information.
   */
  public Piece(String ligne){

    String[] pieces = ligne.split("/");

    String[] param =  pieces[0].split(",");

    this.TAILLE = Integer.parseInt(param[0]);
    this.EQUIPE = Integer.parseInt(param[1]);
    this.innactive = Boolean.parseBoolean(param[2]);

    if(pieces.length > 1){

      String pieceParam = pieces[1];
      for(int i = 2; i < pieces.length; i++){

        pieceParam += "/" + pieces[i];
      }

      this.pieceDessous = new Piece(pieceParam);
    }

  }


  /**
   * Constructeur de la classe pour créer une pièce couvrant une autre pièce, sa
   * taille sera déduite de celle de la pièce couverte, qui ne doit donc pas
   * être null.
   * @param pieceDessous la pièce qui sera couverte, ne doit pas valloir
   * NullPointerExeption
   * @param equipe L'équipe de la pièce
   */
  public Piece(Piece pieceDessous, int equipe){


    this.EQUIPE = equipe;
    this.pieceDessous = pieceDessous;
    this.TAILLE = pieceDessous.getTaille() - 1;
    this.innactive = false;
  }

  public boolean peutCouvrir(Piece p){

    return !this.estAlliee(p) && !p.getInnactive() && !this.innactive && this.TAILLE == p.getTaille()-1;
  }


  /**
   * Accesseur de l'attribut TAILLE
   * @return La taille de la pièce
   */
  public int getTaille(){

    return this.TAILLE;
  }


  /**
   * Accesseur de l'attribut EQUIPE
   * @return L'équipe de la pièce
   */
  public int getEquipe(){

    return this.EQUIPE;
  }


  /**
   * Accesseur de l'attribut pieceDessous
   * @return La piece couverte par la pièce (null si la piece est vide)
   */
  public Piece getPieceDessous(){

    return this.pieceDessous;
  }


  /**
   * Accesseur de l'attribut innactive
   * @return vrai si la piece est innactive
   */
  public boolean getInnactive(){

    return this.innactive;
  }


  /**
   * Modifie la valeur de l'attribut innactive
   * @param innactive La nouvelle valeur de innactive
   */
  public void setInnactive(boolean innactive){

    this.innactive = innactive;
  }


  /**
   * Modifie la valeur de l'attribut pieceDessous
   * @param pieceDessous La nouvelle valeur de pieceDessous
   */
  public void setPieceDessous(Piece pieceDessous){

    this.pieceDessous = pieceDessous;
  }


  /**
   * Méthode verifiant si deux pièces sont alliées ou non.
   * @param piece La pièce avec laquelle le test va s'effectuer. Elle se doit de
   * ne pas être null.
   * @return vrai si les pieces sont alliées
   */
  public boolean estAlliee(Piece piece){

    return this.EQUIPE == piece.getEquipe();
  }


  /**
   * Méthode indiquant si la pièce est vide ou non.
   * @return vrai si la pièce est vide
   */
  public boolean estVide(){

    return this.pieceDessous == null;
  }


  /**
   * Méthode retournant une version String de la pièce, notamment utilisée pour
   * sauvegarder l'état d'une partie dans un fichier texte. Elle indique aussi
   * les paramètres de la pièce couverte s'il y en a une.
   * @return Une version String de la pièce, sous la forme
   * "TAILLE,EQUIPE,innactive". Si la pièce en couvre une autre, ls paramètres
   * de la pièce couverte seront affichés après un '/'. Par exemple une pièce de
   * taille 1, de l'équipe 0 et active couvrant une pièce de taille 2 de
   * l'équipe 0 et active donnera: "1,0,false/2,0,false"
   */
  public String toString(){

    String ret = Integer.toString(this.TAILLE) + "," + Integer.toString(this.EQUIPE) + "," + Boolean.toString(this.innactive);

    if(!this.estVide()){
      ret += "/" + this.pieceDessous.toString();

    }

    return ret;
  }
}
