import java.util.*;
/**
 *El programa deberá recibir del usuario el número n de hilos a ejecutar, para luego lanzar n hilos
 *iguales que iteren 10 veces, utilizando en cada iteración el semáforo implementado por ustedes
 *para administrar la entrada a una sección especial del código, de forma que solo un conjunto
 *reducido de hilos puedan ejecutarla a la vez.
 *
 * @author Santaella Marín Héctor.
 */

public class Practica3{

			
	public static final int contador_inicial= 2;
	public static void main(String[] args) throws InterruptedException {

		Semaforo semaforo = new Semaforo(contador_inicial);		
		System.out.println('\n');
		System.out.println("-------------------------------------------------");
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingresa el numero de threads que van a ejecutarse:");
		System.out.println("--------------------------------------------------");
		int num_hilos = scanner.nextInt();
		Thread[] hilos = new Thread[num_hilos];				
		for(int i=0; i<num_hilos; i++){						
			hilos[i] = new Thread(new Hilo(semaforo));		
			hilos[i].start();								
		}

		try{
			for(int i=0; i<num_hilos; i++){					
				hilos[i].join();							
			}
		}catch(InterruptedException e){						
			System.out.println(e);							
		}

	}
}

