package Semaphores;

/**
*<h1> Problema Filosofilor </h1> 
* Clasa MainClass contine metoda "main" in care sunt instantiati filosofii
*  si obiectele de tip Furculita. 
* @author  Brinzan Florinel Razvan
* @since   2019-12-07
*/

public class MainClass {
	
	static final int NR_FILOSOFI = 5;
	static final int NR_FURCULITE = 5;
	
	   /**
	   * In aceasta metoda sunt create thread-urile (filosofii) si furculitele si este apelata metoda
	   *  "start()" ce pune in executie thread-urile. Fiecarui filosof ii sunt asignate 2 furculite si un id.
	   *   Fiecare furculita din cele 5 va fi data ca parametru pentru 2 filosofi ( ex. filosoful 1 va avea 
	   *   furculitele 1 si 2 iar filosoful 2 va avea furculitele 2 si 3). Numarul filosofilor si furculitelor
	   *    este dat prin intermediul variabilelor statice - finale "NR_FILOSOFI" si "NR_FURCULITE".
	   * @param args neutilizat.
	   */

	public static void main(String[] args) {

        Filosof[] filosofi = new Filosof[NR_FILOSOFI];
        Furculita[] furculite = new Furculita[NR_FURCULITE];

        for (int iterator = 0; iterator < NR_FURCULITE; ++iterator) {
        	furculite[iterator] = new Furculita();
	    }


        for (int iterator = 0; iterator < NR_FILOSOFI; ++iterator) {
        	Furculita FurculitaStanga = furculite[iterator];
        	Furculita FurculitaDreapta = furculite[(iterator + 1) %  NR_FURCULITE];

        	filosofi[iterator] = new Filosof(FurculitaStanga, FurculitaDreapta, iterator);
	
        	filosofi[iterator].start();
	    }
	    
	}
	
}
