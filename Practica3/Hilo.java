/**
 * Hilo que tendrá la  tarea acceder al recurso compartido usando un
 * semáforo.
 * @author Santaella Marín Héctor.
 */
public class Hilo  extends Thread{
	static Semaforo semaforo;
	private boolean candado;	
	public Hilo( Semaforo semaforo){
			this.semaforo = semaforo;
		}

	 /**
     * Cada hilo entrará 10 veces, para evitar conflictos
     * se usa un semáforo.
     */

	@Override
	public void run() {

	    /*Iteraciones que cada hilo intentara acceder al recurso compartido.*/
		for (int i = 0; i < 10; i++) {
			candado = false;
			while(!candado)				//el candado sea true no trenda  acceso
					candado = semaforo.acquire();
	        //Inicio de sección critica
			System.out.println("Empieza SC, el hilo con id: "+Thread.currentThread().getName());
			try{
					Thread.sleep(100); 		//duerme 1/10
				}catch(InterruptedException e){
					System.out.println(e);
				}

			System.out.println("Id Thread: " +  Thread.currentThread().getName()+ ", el contador del semáforo  es: " + semaforo.getContador());  
			
			semaforo.release();
			System.out.println("Termina la SC, id del Thread: "+Thread.currentThread().getName());
	        //Final de sección critica.
		}
	}

}