package commandes;

import plateau.Plateau;
import java.io.FileNotFoundException;
import plateau.InvalidMoveException;

public class CommandeExecuteur{

  static String[] listeCommandes = {"deplacer", "quitter", "sauvegarder", "charger"};


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
