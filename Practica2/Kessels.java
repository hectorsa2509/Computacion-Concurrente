
/*Algoritmo de exclusión mutua Kessels.
Este algoritmo es para 2 hilos.
Cada hilo se iteran 20 veces.
@author Santaella Marín Héctor
*/
public class Kessels{

		private volatile int turno [] = {0,0};
		private volatile int turno1 [] = {1,0};

		public Kessels(){
		this.turno=turno;
		this.turno1=turno1;		
		}
 /**
 * @param id Identificador del hilo, solo puede ser 0 o 1.
 */

		public void comienzaKessels(int id){
			 int local [] = {0,0};
	
			 for (int i=0;i<20 ; i++) {
			 if(id==0){
			 turno1[id]= 1;
			 local[id]=turno[1];
			 turno[0]=local[id];
			  while (!(turno1[1]==0 || local[id] != this.turno[1])){
			 	//Se espera al otro hilo
			 }
			}
			else {
			 turno1[id]=1;
			 local[id]=1-turno[0];
			 turno[id]=local[id];
			  while (!(turno1[0]==0 || local[id] == this.turno[0])){
			 	//Se espera al otro hilo
			 }
			}
			
			int suma=0;			 
			System.out.println("Comenzo sección critica del algoritmo de Kessels "
            + "del hilo: " + id);
    
            //Suma de los primero 1000 numeros
			for (int j = 0; j < 1000; j++) {
           	    suma = suma + j;	
			}
			System.out.println("Suma los primeros 1000 números naturales con 20 iteraciones: "+suma);
			System.out.println("Finalizo sección critica del algoritmo de Kessels  en el Hilo: "+id);

			turno1[id]=0;

		}

		}
}