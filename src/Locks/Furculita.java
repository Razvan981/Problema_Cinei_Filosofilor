package Locks;

import java.util.concurrent.locks.ReentrantLock;

/**
*<h1> Problema Filosofilor </h1> 
* Clasa Furculita contine un obiect de tip ReentrantLock prin intermediul caruia furculita 
* este blocata cat timp se afla in posesia unui filosof si deblocata in momentul in care 
* in care filosoful o elibereaza. Metodele folosite pentru blocarea respectiv deblocarea 
* furculitei sunt: ridicaFurculita() si puneJosFurculita().
* <p>
* @author  Brinzan Florinel Razvan
* @since   2019-12-07
*/

public class Furculita {
	
	ReentrantLock lock = new ReentrantLock();
	
	  /**
	   * Aceasta metoda incearca achizitionarea furculitei. Daca furculita va fi disponibila atunci
	   * ea va fi achizitionata de filosoful care face apelul si se va bloca lacatul.
	   * @return boolean Returneaza TRUE daca reuseste sa blocheze furculita si FALSE daca nu reuseste
	   * (furculita este deja blocata)
	  */

	public boolean ridicaFurculita() {
		
		return lock.tryLock();
	}
	
	  /**
	   * Aceasta metoda elibereaza furculita deblocand lacatul.
	  */
	
	public void puneJosFurculita() {
		lock.unlock();
	}
	
}