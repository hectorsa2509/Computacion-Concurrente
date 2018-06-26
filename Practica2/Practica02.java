
/*
Clase Principal.
Se utilizan los tres algoritmos de exclusión mutua para dos procesos vistos
en clase: Dekker, Peterson y Kessels.
@author Santaella Marín Héctor
*/

public  class Practica02{

	static Dekker d= new Dekker();
	static Peterson p= new Peterson();
	static Kessels k= new Kessels();

	public static void main(String[] args) throws InterruptedException{
		
		//Dekker
		//Hilos aplicando exclución mutua.
		Thread h1= new Thread(new Runnable(){
			@Override
			public void run(){				
				d.comienzaDekker(0);				
			}
		});		
		h1.start();
		//Hilos aplicando exclución mutua.
		Thread h2= new Thread(new Runnable(){
			@Override
			public void run(){				
				d.comienzaDekker(1);
			}			
		});
		h2.start();
		
		h1.join();
		h2.join();

        //Peterson
        //Hilos aplicando exclución mutua.
		Thread h3= new Thread(new Runnable(){
			@Override
			public void run(){
					p.comienzaPeterson(0);
			}

		});
		h3.start();
		//Hilos aplicando exclución mutua.
		Thread h4= new Thread(new Runnable(){
			@Override
			public void run(){
				
					p.comienzaPeterson(1);
				
			}

		});
		h4.start();
		
		h3.join();
		h4.join();

        //Kessels
        //Hilos aplicando exclución mutua.
		Thread h5= new Thread(new Runnable(){
			@Override
			public void run(){

				k.comienzaKessels(0);
			}
		});
		h5.start();
		//Hilos aplicando exclución mutua.
		Thread h6= new Thread(new Runnable(){
			@Override
			public void run(){
				k.comienzaKessels(1);				
			}
		});
		h6.start();
		
		h5.join();
		h6.join();
	}
}