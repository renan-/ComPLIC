package complic.analyse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import complic.definitions.Lexique;

import complic.erreurs.FichierInexistant;

/**
 * @author Renan Strauss
 * L'analyseur lexical utilise la
 * classe Scanner
*/
public class AnalyseurLexical {
	/**
	 * Le Scanner associé
	*/
	public Scanner scan;

	/**
	 * Constructeur
	 * @param le nom du fichier à analyser
	*/
	public AnalyseurLexical(String nomFichier) throws FichierInexistant{
		try {
			this.scan = new Scanner(new File(nomFichier).toPath());
		} catch(IOException e) {
			this.scan = null;
			throw new FichierInexistant(nomFichier);
		}
	}

	/**
	 * @return true si et seulement si next() != EOF
	*/
	public boolean hasNext() {
		return this.scan.hasNext();
	}

	/**
	 * @return le prochain bloc lexical lu
	*/
	public String next() {
		String next = this.scan.next();

		/**
		 * Une ligne de commentaire
		*/
		if(next.startsWith("#")) {
			this.scan.nextLine();
		}
		/**
		 * Pas de commentaire(s)
		*/
		else {
			return next;
		}

		return this.hasNext() ? this.next() : next;
	}

	/**
	 * @param word Le mot a tester
	 * @return Vrai ssi word est un identificateur
	*/
	public boolean estUnIdentificateur(String word) {
		return word.matches("[a-zA-Z]+") && !Arrays.asList(Lexique.MOTS_CLES).contains(word);
	}

	/**
	 * @param word Le mot a tester
	 * @return Vrai ssi word est une contante entiere
	*/
	public boolean estUneConstanteEntiere(String word) {
		boolean ok = true;
		try {
			Integer.parseInt(word);
		} catch(NumberFormatException e) {
			ok = false;
		}

		return ok;
	}

	public boolean estUneConstanteBooleenne(String word) {
		return word.equals(Lexique.VRAI) || word.equals(Lexique.FAUX);
	}

	public boolean estUnOperateur(String word) {
		return Arrays.asList(Lexique.OPERATEURS).contains(word);
	}
}