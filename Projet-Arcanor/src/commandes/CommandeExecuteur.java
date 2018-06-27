package commandes;

import plateau.Plateau;
import java.io.FileNotFoundException;
import plateau.InvalidMoveException;

public class CommandeExecuteur{

  /**
   * La liste des commandes reconnues par la classe
   *  - deplacer [x : int] [y : int] [autreX : int] [autreY : int] [decouvre : boolean]
   *  - quitter
   *  - sauvegarder [nomSauvegarde]
   *  - charger [nomSauvegarde]
   */
  static String[] listeCommandes = {"deplacer", "quitter", "sauvegarder", "charger"};

  /**
   * Cette fonction retourne l'indice de la commande correspondant à la String
   * passée en paramètre. S'il n'y a pas de correspondances, elle renvoie -1.
   * @param commande La commande dont on veut savoir la correspondance
   * @return L'indice de la commande dans le tableau de String listeCommandes,
   * -1 s'il n'y a pas de correspondance
   */
  private static int indiceCommande(String commande){

    int i = 0;
    int indice = -1;
    boolean trouve = false;

    while(!trouve && i < listeCommandes.length){

      if(listeCommandes[i].equals(commande)){

        trouve = true;
        indice = i;
      }

      i++;
    }
    return indice;
  }


  /**
   * @param indiceCommande le numéro de la commande que l'on veut executer
   * (indice dans la liste listeCommandes)
   * @param argument L'argument de la commande (les paramètres), ignorés si non
   * besoin
   * @throws InvalidCommandException lorsque la commande saisie n'est pas valide
   */
  private static void executeCommande(int indiceCommande, String argument) throws InvalidCommandException{

    switch (indiceCommande){

      case 0:

        String[] parts = argument.split(" ");

        try{

          int x = Integer.parseInt(parts[0]);
          int y = Integer.parseInt(parts[1]);
          int autreX = Integer.parseInt(parts[2]);
          int autreY = Integer.parseInt(parts[3]);
          boolean decouvre = Boolean.parseBoolean(parts[4]);

          Plateau.faireMouvement(x, y, autreX, autreY, decouvre);

        } catch(ArrayIndexOutOfBoundsException e){

          throw new InvalidCommandException("Mauvais nombre d'argument");
        } catch(NumberFormatException e){

          throw new InvalidCommandException("Arguments invalides: les 4 premier paramètres doivent être des chiffres entiers");
        } catch(InvalidMoveException e){

          throw new InvalidCommandException("Mouvement impossible");
        }

        break;

      case 1:
        System.out.println("partie quittée par l'utilisateur");
        System.exit(0);
        break;

      case 2:
        try{
          Plateau.sauvePartie(argument);
        } catch(FileNotFoundException e){

          throw new InvalidCommandException("Fichier innexistant");
        }
        break;

      case 3:
      try{
        Plateau.chargePartie(argument);
        Plateau.passeLeTour();
      } catch(FileNotFoundException e){

        throw new InvalidCommandException("Fichier innexistant");
      }
        break;
      default:
        throw new InvalidCommandException("Commande non reconnue");
    }
  }

  /**
   * Execute une ligne de commande sous la forme "nomCommande arguments".
   * Par exemple la commande "déplace" demande 5 arguments: la position de la
   * case d'origine en x puis y, la position de la case de destination en x puis
   * y et un boolean indiquant si l'on veut découvrir la pièce ou non.
   * Quelques exemples de commande:
   *  - deplacer 0 1 0 2 false
   *  - quitter
   *  - sauvegarder testSauvegarde
   *
   * @param line la ligne de commande qui sera exécutée
   * @throws InvalidCommandException lorsque la commande saisie n'est pas valide
   */
  public static void execute(String line) throws InvalidCommandException{

    String[] parts = line.split(" ");
    int indiceCommande = indiceCommande(parts[0]);
    String argument = "";

    for(int i = 1; i < parts.length; i++){

      if(i > 1){

        argument += " ";
      }
      argument += parts[i];
    }

    executeCommande(indiceCommande, argument);

  }
}
