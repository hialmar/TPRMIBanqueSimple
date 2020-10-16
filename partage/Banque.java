package partage;

import java.rmi.Remote;
import java.rmi.RemoteException;

import exceptions.CompteExistant;
import exceptions.CompteInexistant;

/**
 * Interface de l'objet Serveur RMI Banque
 * @author torguet
 *
 */
public interface Banque extends Remote {
	/**
	 * Demande la création d'un compte
	 * @param id : identifiant du futur compte
	 * @param sommeInitiale : somme initiale du compte
	 * @throws RemoteException : s'il y a un problème avec RMI
	 * @throws CompteExistant : s'il y a déjà un compte avec le même identifiant
	 */
	public void creerCompte(String id, double sommeInitiale) throws RemoteException, CompteExistant;

	/**
	 * Demande l'ajout d'une somme à un compte
	 * @param id : identifiant du compte
	 * @param somme : somme à ajouter
	 * @throws RemoteException : s'il y a un problème avec RMI
	 * @throws CompteInexistant: s'il n'y a pas de compte avec cet identifiant
	 */
	public void ajouter(String id, double somme) throws RemoteException, CompteInexistant;

	/**
	 * Demande le retrait d'une somme à un compte
	 * @param id : identifiant du compte
	 * @param somme : somme à retirer
	 * @throws RemoteException : s'il y a un problème avec RMI
	 * @throws CompteInexistant : s'il n'y a pas de compte avec cet identifiant
	 */
	public void retirer(String id, double somme) throws RemoteException, CompteInexistant;
	
	/**
	 * Récupère la position d'un compte
	 * @param id : identifiant du compte
	 * @return un objet Position contenant le solde et la date de dernière opération
	 * @throws RemoteException : s'il y a un problème avec RMI
	 * @throws CompteInexistant : s'il n'y a pas de compte avec cet identifiant
	 */
	public Position position(String id) throws RemoteException, CompteInexistant;
	
}
