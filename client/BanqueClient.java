package client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.InputMismatchException;
import java.util.Scanner;

import exceptions.CompteExistant;
import exceptions.CompteInexistant;
import partage.Banque;

/**
 * Classe de démarrage du client
 * @author torguet
 *
 */
public class BanqueClient {

	/**
	 * Méthode principale
	 * @param args : arguments du programme (inutilisé ici)
	 * @throws MalformedURLException : s'il y a une erreur dans l'URL de l'objet Serveur Banque
	 * @throws RemoteException : s'il y a un problème avec RMI
	 * @throws NotBoundException : si le serveur n'est pas lancé
	 */
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		// Sert à récupérer les entrées clavier de l'utilisateur
		Scanner scan = new Scanner(System.in);
		
		// Récupère la souche de l'objet serveur RMI Banque
		Banque soucheBanque = (Banque) Naming.lookup("rmi://localhost/Banque");
		
		// Tant que l'utilisateur ne choisit pas quitter
		boolean nonFini = true;
		while(nonFini) {
			// affiche le menu
			System.out.println("1- créer un compte");
			System.out.println("2- position du compte");
			System.out.println("3- ajouter au compte");
			System.out.println("4- retirer du compte");
			System.out.println("0- quitter");

			// paramètres des requêtes
			String id;
			double somme;
			try {
				// on attend un entier de l'utilisateur
				int menu = scan.nextInt();
				// on saute le retour à la ligne
				scan.nextLine();
				
				// en fonction de ce qu'a tapé l'utilisateur
				switch(menu) {
				case 0:
					nonFini = false;
					break;
				case 1:
					// création d'un compte
					// il nous faut l'id
					System.out.println("Quel est l'id :");
					id = scan.nextLine();
					// et la somme
					System.out.println("Quel est la somme initiale :");
					somme = scan.nextDouble();
					// on tente de créer le compte
					try {
						soucheBanque.creerCompte(id, somme);
					} catch (CompteExistant e) {
						System.out.println("Le compte existe déjà");
					}
					break;
				case 2:
					// position d'un compte
					// il nous faut l'id
					System.out.println("Quel est l'id :");
					id = scan.nextLine();
					// on tente de récupérer la position et on l'affiche
					try {
						System.out.println(soucheBanque.position(id));
					} catch (CompteInexistant e) {
						System.out.println("Le compte n'existe pas");
					}
					break;
				case 3:
					// ajout d'une somme
					// il nous faut l'id
					System.out.println("Quel est l'id :");
					id = scan.nextLine();
					// et la somme
					System.out.println("Quel est la somme :");
					somme = scan.nextDouble();
					// on tente d'ajouter au compte
					try {
						soucheBanque.ajouter(id, somme);
					} catch (CompteInexistant e) {
						System.out.println("Le compte n'existe pas");
					}
					break;
				case 4:
					// retrait d'une somme
					// il nous faut l'id
					System.out.println("Quel est l'id :");
					id = scan.nextLine();
					// et la somme
					System.out.println("Quel est la somme :");
					somme = scan.nextDouble();
					// on tente de retirer du compte
					try {
						soucheBanque.retirer(id, somme);
					} catch (CompteInexistant e) {
						System.out.println("Le compte n'existe pas");
					}
					break;
				}
			} catch(InputMismatchException ex) {
				// L'utilisateur a tapé quelque chose d'incorrect
				System.err.println("Erreur de type.");
				// on saute ce qu'il a tapé avant de revenir au menu
				scan.nextLine();
			}
		}
		// on ferme l'objet qui permet de récupérer les saisies utilisateur
		scan.close();
	}

}
