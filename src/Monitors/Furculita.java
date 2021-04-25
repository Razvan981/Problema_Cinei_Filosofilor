package Monitors;

/**
*<h1> Problema Filosofilor </h1> 
* Clasa Furculita contine o variabila booleana ce memoreaza starea furculitei
* ( false - furculita disponibila, true - furculita indisponibila) si 3 metode sincronizate : 
* achizitioanarea furculitei, eliberarea furculitei si returnarea starii furculitei.
* <p>
* @author  Brinzan Florinel Razvan
* @since   2019-12-07
*/

public class Furculita {
	
	public Boolean stare = false;
	
	  /**
	   * Aceasta metoda incearca achizitionarea furculitei. Daca furculita este indisponibila
	   * atunci firul curent de executie va astepta pana va fi trezit (notificat).
	   * Daca furculita este disponibila atunci ea va fi achizitionata de filosoful care face apelul.
	  */

	public synchronized void ridicaFurculita() {
		
		while(stare == true) {
			try {
				wait();
			} catch (InterruptedException e){}
		}

		if(stare == false) {	
			stare = true;
		} 	
	}
	
	  /**
	   * Aceasta metoda elibereaza furculita, seteaza starea pe false si trezeste 
	   * toate firele de executie care asteapta pe monitorul acestui obiect.
	  */
	
	public synchronized void puneJosFurculita() {
		stare = false;
		notifyAll();
	}
	
	  /**
	   * Aceasta metoda returneaza starea furculitei.
	   * @return boolean Returneaza TRUE daca furculita este indisponibila
	   * si FALSE in cazul in care este disponibila
	  */
	
	public synchronized Boolean getState() {
		return stare;
	}

}