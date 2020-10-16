package partage;

import java.io.Serializable;
import java.util.Date;

/**
 * Classe utilitaire qui permet de transférer le solde et la date de dernière opération
 * d'un compte
 * @author torguet
 *
 */
public class Position implements Serializable {
	/**
	 * solde du compte
	 */
	private double solde;

	/**
	 * date de dernière opération
	 */
    private Date derniereOperation;

    
    /**
     * constructeur qui affecte automatiquement la date
     * @param solde : le solde
     */
    public Position(double solde) {
         this.solde = solde; 
         this.derniereOperation = new Date();
    }
    
    /**
     * constructeur qui permet d'affecter les deux attributs
     * @param solde : le solde
     * @param derniereOperation : la date de dernière opération
     */
	public Position(double solde, Date derniereOperation) {
		super();
		this.solde = solde;
		this.derniereOperation = derniereOperation;
	}

	/**
	 * Permet de récupérer le solde
	 * @return le solde
	 */
	public double getSolde() {
		return solde;
	}

	/**
	 * Permet de changer le solde (attention ça ne marche qu'en local)
	 * @param solde : nouveau solde
	 */
	public void setSolde(double solde) {
		this.solde = solde;
	}

	/**
	 * Permet de récupérer la date de dernière opération
	 * @return la date
	 */
	public Date getDerniereOperation() {
		return derniereOperation;
	}

	/**
	 * Permet de changer la date (attention ça ne marche qu'en local)
	 * @param derniereOperation : la nouvelle date
	 */
	public void setDerniereOperation(Date derniereOperation) {
		this.derniereOperation = derniereOperation;
	}

	/**
	 * Méthode permettant d'afficher les informations
	 */
	@Override
	public String toString() {
		return "Position [solde=" + solde + ", derniereOperation=" + derniereOperation + "]";
	}

    
    
}
