package serveur;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * Classe de démarrage du Serveur
 * @author torguet
 *
 */
public class MainServeur {

	/**
	 * Méthode principale
	 * @param args arguments du programme (inutilisés ici)
	 * @throws RemoteException : s'il y a un problème avec RMI
	 * @throws MalformedURLException : si le nom de l'objet serveur n'est pas correct
	 * @throws AlreadyBoundException : si un objet du même nom existe
	 */
	public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException {
		// Création Annuaire RMI
		LocateRegistry.createRegistry(1099);
		// Création de l'objet Serveur
		BanqueImpl banque = new BanqueImpl();
		// Nommage de l'objet Serveur
		Naming.bind("Banque", banque);
	}

}
