package Semaphores;

import java.util.Random;

/**
*<h1> Problema Filosofilor </h1> 
* Clasa Filosof extinde clasa Thread si suprascrie metoda "run()". In aceasta clasa
*  sunt implementate metodele necesare simularii problemei filosofilor.
*  <p>
*  Pe langa metodele necesare implementarii problemei filosofilor, clasa Filosof
*  contine doua obiecte de tip Furculita ( furculita stanga si furculita dreapta)
*  cu ajutorur carora filosoful poate manca. 
*  <p>
*  Membrul privat de tip data - numarul intreg "id" este folosit pentru a identifica filosoful.
*  Fiecare filosof are propiul id.
* <p>
* @author  Brinzan Florinel Razvan
* @since   2019-12-07
*/

public class Filosof extends Thread {

	private Furculita FurculitaStanga;
	private Furculita FurculitaDreapta;
	private int id;
	
	/**
	 * Constructorul clasei Filosof. Are rolul de a asigna membrilor sai privati de tip data valorile 
	 * date ca parametri ( cele doua furculite cu ajutorul carora poate manca filosoful si id-ul unic ).
	 * @param FurculitaStanga furcuita din stanga filosofului
	 * @param FurculitaDreapta furcuita din dreapta filosofului
	 * @param id identificatorul filosofului
	 */
	
    public Filosof(Furculita FurculitaStanga, Furculita FurculitaDreapta, int id) {
        this.FurculitaStanga = FurculitaStanga;
        this.FurculitaDreapta = FurculitaDreapta;
        this.id = id;
    }
    
    
	/**
	 * Metoda Activitate are rolul de a afisa in consola activitatea curenta a firului
	 * de executie (filosofului) care o apeleaza. Prin intermediul obiectului "random"
	 * se genereaza un numar in intervalul [0, 1000) care va fi dat ca parametru functiei
	 * sleep pentru a produce o intarziere.
	 * @param activitate activitatea filosofului din momentul apelului metodei
	 * @throws InterruptedException .
	 */
    
    public void Activitate(String activitate) throws InterruptedException {
    	
    	Random random = new Random();
    	
        System.out.println(" Filosoful " + (id + 1) +" " + activitate);
        Thread.sleep(random.nextInt(1000));
    }
       
    /**
	   * Aceasta metoda suprascrie metoda "run()" din clasa Thread.
	   * Metoda "run()" din clasa Filosof contine o bucla infinita ce apeleaza la inceput 
	   * metoda "Activitate()" pentru a semnala faptul ca filosoful respectiv se gandeste
	   * apoi apeleaza metoda "mananca()". Apeland metoda "manaca()" filosoful va incerca sa
	   * manance. Daca nu va avea disponibile ambele furculite nu va manca si va trece din nou la 
	   * starea de gandire. 
	   */
    
	public void run() {
	
	    while (true) {
	    	try {
				Activitate(":  ----> Se gandeste !! ");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	    	mananca();
	    }
	}
	
    /**
	   * Aceasta metoda  testeaza initial daca filosoful pote achizitiona furculita stanga.
	   * Daca nu o poate achizitiona se va afisa mesajul corespunzator prin intermediul metodei 
	   * "Activitate()" si se va iesi din functie. 
	   * <p>
	   * Daca va achizitona furculita stanga va incerca sa o achizitioneza si pe cea dreapta. 
	   * Daca nu va reusi se va afisa mesajul corespunzator prin intermediul metodei 
	   * "Activitate()" si va elibera furculita stanga pentru a evita interblocajul (deadlock).
	   * <p>
	   * Daca filosoful va reusi sa achizitioneze albele furculite atunci va manca. Se va afisa 
	   * lucrul acesta prin intermediul metodei "Activitate()" apoi va elibera ambele furculite.
	   */
	
	public void mananca(){
		try {
		
		    if(FurculitaStanga.ridicaFurculita()){
		    	Activitate(": ridica furculita din Stanga.");
		   
		        if(FurculitaDreapta.ridicaFurculita()){
		        	Activitate(": ridica furculita din Dreapta.");
		            try {
		            	
		                sleep(3000); // intarziere pentru a manca
		                Activitate(":  ============== Mananca !! ============== ");
		            } catch (InterruptedException ex) { }
		            
		            Activitate(": A terminat de mancat !! Acum Pune jos furculita din Stanga si pe cea din Dreapta.");
		            FurculitaStanga.puneJosFurculita();
		            FurculitaDreapta.puneJosFurculita();	              	
		        }else{
		        	Activitate(": lasa furculita din Stanga jos din cauza ca furculita din Dreapta nu e disponibila, deci nu poate Manca.");
		        	FurculitaStanga.puneJosFurculita();
		        }
		    }else {
		    	Activitate(": a vrut sa Manance dar nu a reusit din cauza ca furculita din Stanga era luata de alt filosof.");
		    }
	    } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
	}
}