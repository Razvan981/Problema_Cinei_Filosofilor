package Semaphores;

import java.util.concurrent.Semaphore;

/**
*<h1> Problema Filosofilor </h1> 
* Clasa Furculita contine un semafor si doua metode: una prin care se incearca 
*dobandirea permisiunii semaforului si cealalta elibereaza un permis, returnandu-l semaforului.
* <p>
* @author  Brinzan Florinel Razvan
* @since   2019-12-07
*/

public class Furculita {
	
	public Semaphore semafor = new Semaphore(1);
	
	  /**
	   * Aceasta metoda incearca dobandirea permisiunii semaforului. Daca va reusi va returna
	   * True iar daca nu va reusi va returna False.
	   * @return boolean True daca va obtine permisul si False in caz contrar
	  */
	
	public boolean ridicaFurculita() {
	    return semafor.tryAcquire();
	}

	  /**
	   * Aceasta metoda elibereaza un permis, returnandu-l semaforului. Dupa apelarea acestei  metode,
	   * semaforul poate acorda permisul altui fir de executie (daca i se va solicita acest lucru).
	  */
	
	public void puneJosFurculita() {
		semafor.release();
	}
	
}