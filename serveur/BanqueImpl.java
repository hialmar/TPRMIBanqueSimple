package serveur;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

import banque.BanqueSimple;
import exceptions.CompteExistant;
import exceptions.CompteInexistant;
import partage.Banque;
import partage.Position;

/**
 * Classe d'impléntation de l'objet Serveur RMI Banque
 * @author torguet
 *
 */
public class BanqueImpl extends UnicastRemoteObject implements Banque {
	
	/**
	 * Composant métier
	 */
	private BanqueSimple banque;

	/**
	 * constructeur
	 * @throws RemoteException : s'il y a un problème de configuration RMI
	 */
	protected BanqueImpl() throws RemoteException {
		super();
		banque = new BanqueSimple();
	}

	/**
	 * Demande la création d'un compte
	 * @param id : identifiant du futur compte
	 * @param sommeInitiale : somme initiale du compte
	 * @throws RemoteException : s'il y a un problème avec RMI
	 * @throws CompteExistant : s'il y a déjà un compte avec le même identifiant
	 */
	@Override
	public void creerCompte(String id, double somme_initiale) throws RemoteException, CompteExistant {
		if(banque.compteExiste(id)) {
			throw new CompteExistant();
		}
		banque.creerCompte(id, somme_initiale);
	}

	/**
	 * Demande l'ajout d'une somme à un compte
	 * @param id : identifiant du compte
	 * @param somme : somme à ajouter
	 * @throws RemoteException : s'il y a un problème avec RMI
	 * @throws CompteInexistant: s'il n'y a pas de compte avec cet identifiant
	 */
	@Override
	public void ajouter(String id, double somme) throws RemoteException, CompteInexistant {
		if(!banque.compteExiste(id)) {
			throw new CompteInexistant();
		}
		banque.ajouter(id, somme);
	}

	/**
	 * Demande le retrait d'une somme à un compte
	 * @param id : identifiant du compte
	 * @param somme : somme à retirer
	 * @throws RemoteException : s'il y a un problème avec RMI
	 * @throws CompteInexistant : s'il n'y a pas de compte avec cet identifiant
	 */
	@Override
	public void retirer(String id, double somme) throws RemoteException, CompteInexistant {
		if(!banque.compteExiste(id)) {
			throw new CompteInexistant();
		}
		banque.retirer(id, somme);
	}

	/**
	 * Récupère la position d'un compte
	 * @param id : identifiant du compte
	 * @return un objet Position contenant le solde et la date de dernière opération
	 * @throws RemoteException : s'il y a un problème avec RMI
	 * @throws CompteInexistant : s'il n'y a pas de compte avec cet identifiant
	 */
	@Override
	public Position position(String id) throws RemoteException, CompteInexistant {
		if(!banque.compteExiste(id)) {
			throw new CompteInexistant();
		}
		double solde = banque.getSolde(id);
		Date date = banque.getDerniereOperation(id);
		Position pos = new Position(solde, date);
		return pos;
	}


}
