import java.util.*;

 /**
 * @author Santaella Marín Héctor.
 *Implementación de un Semaforo
 */
public class Semaforo{
	//En la práctica no se meciona que sea necesario una cola
	//por lo que la omitiré

	//Se guarda el orden en que los hilos requerian el acceso al recurso.
	//se ejecutará el hilo cuando este disponible.
	//private Queue<Thread> proceso= new LinkedList<>();;
	private int contador;
	/*Constructor del Semáforo donde recibe el contador*/
	public Semaforo(int contador){
		this.contador=contador;
	}


/*Si  el  semáforo  no  es  nulo  (abierto)  decrementa  en  uno  el  
valor  del  semáforo.  Si  el  valor  del  semáforo  es  nulo  (cerrado),  el  
thread que lo ejecuta se suspende y se encola en la lista de procesos en 
espera del semáforo. */

public synchronized boolean acquire(){

	/*try{

		if (this.contador == 0) {
			proceso.add(Thread.currentThread());
			wait();
		} else {
			this.contador--;
		}
	}catch (InterruptedException e) {	
	}*/
	if (contador==0) 				
				return false;			
										
			contador--;					
			return true;	
}

/*
 Si  hay  algún  proceso  en  la  lista  de  procesos  del  semáforo,  
activa uno de ellos para que ejecute la sentencia que sigue al wait que 
lo suspendió. Si no hay procesos en espera en la lista  incrementa en 1 
el valor del semáforo
*/
public void release(){
	contador++;
	/*if (!proceso.isEmpty()) {
		proceso.remove().notify();
	} else {
		this.contador++;
	}*/
}


public int getContador() {
	return contador;
}

}

