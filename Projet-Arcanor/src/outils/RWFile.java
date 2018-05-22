package outils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class RWFile {

	/**
	 * Methode convertissant un fichier texte dont le nom est donné en paramètre
	 * en une ArrayList de String
	 * @param fileName le nom du fichier
	 * @return Une ArrayList de String contenant le texte du fichier
	 */
	 public static ArrayList<String> readFile(String fileName) throws FileNotFoundException{

  	ArrayList<String> file = new ArrayList <String>();
  	Scanner in;

    in = new Scanner (new FileReader (fileName));

    while (in.hasNextLine()) {

     file.add(in.nextLine());
    }

    in.close();

     return file;
   }


	/**
	 * Méthode enregistrant une ArrayList de String dans un fichier texte
	 * @param fileName nom du fichier
	 * @param liste L'Arraylist qui va être écrite dans le fichier texte
	 */
	 public static void writeFile(ArrayList<String> liste, String fileName) throws FileNotFoundException{

     PrintWriter out = new PrintWriter (fileName);
 		for (String ligne : liste) {

 			out.println(ligne);
 		}

 		out.close();
   }
}
