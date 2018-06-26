/**
*Programa para la primer practica de Computacion Concurrente. 
*Recibe de la consola el numero de hilos a lanzar, los utiliza 
*para realizar la suma de los primeros 1000000000 numeros naturales 
*y reporta el tiempo que tardo la ejecucion del proceso.
*@author Daniel Michel Tavera
*@version febrero 2018
**/
public class Practica01{

	public static final int MAX_SUMA = 1000000000;	//la cantidad de numeros a sumar
	public static volatile int total = 0;			//el resultado de la suma

/**
*clase auxiliar que realiza parte de la suma
*/
	private class Hilo implements Runnable{
		private int id;			//el indice del hilo
		private int num_hilos;	//el numero de hilos entre los que se reparte el trabajo
		private int max_suma;		//la cantidad de numeros a sumar entre todos los hilos

/**
*Constructor que recibe la informacion necesaria para determinar 
*la parte del trabajo que le corresponde a este hilo
*@param id el indice del hilo
*@param num_hilos el numero de hilos entre los que se reparte el trabajo
*/
		public Hilo(int id, int num_hilos){
			this.id = id;
			this.num_hilos = num_hilos;
		}

/**
*Realiza la parte de la suma que le corresponde a este hilo y 
*la agrega a la suma total
*/
		public void run(){
			int suma_parcial=0;					//el subtotal de la parte de la suma que le corresponde a este hilo
			for(int i=id; i< MAX_SUMA; i+=num_hilos){	//toma uno de cada nom_hilos numeros a sumar
				suma_parcial+=i;				//lo agrega al subtotal
			}
			total+=suma_parcial;					//agrega el subtotal calculado a la suma total
		}
	}

/**
*Lanza una numero de hilos que en conjunto realizan la suma, 
*luego calcula y reporta el tiempo que tardo en hacer la suma.
*@param num_hilos el numero de hilos a lanzar
*/
	public Practica01(int num_hilos){
		long tiempo_inicio, tiempo_fin;		//momento en el que inicia y termina de sumar, respectivamente
		tiempo_inicio = System.currentTimeMillis();	//registra el momento en el que se empieza a sumar
		Thread[] hilos = new Thread[num_hilos];		//crea el arreglo de hilos
		for(int i=0; i<num_hilos; i++){				//para cada hilo a lanzar
			hilos[i] = new Thread(new Hilo(i, num_hilos));	//crea el hilo con los datos necesarios
			hilos[i].start();						//lanza el hilo
		}
		try{
			for(int i=0; i<num_hilos; i++){			//para cada hilo lanzado
				hilos[i].join();				//espera a que el hilo termine su ejecucion
			}
		}catch(InterruptedException e){		//si hay algun problema en la ejecucion
			System.err.println(e);		//lo reporta
			System.exit(-1);			//y termina el programa
		}
		tiempo_fin = System.currentTimeMillis();	//tegistra el momento en que termina de sumar
		System.out.println("tiempo: "+(tiempo_fin - tiempo_inicio)+" milisegundos.");	//reporta el tiempo que tardo en hacer la suma
	}

/**
*Permite probar el programa con el numero de hilos recibido de la consola
*@param args debe contener un numero entero positivo
*/
	public static void main(String[] args){
		int num_hilos=0;	//el numero de hilos a lanzar
		try{
			num_hilos = Integer.parseInt(args[0]);	//lee el numero de hilos del argumento recibido
			if(num_hilos<1){				//verifica que sea positivo
				throw(new RuntimeException());	//y de lo contrario lanza una excepcion
			}
		}catch(Exception e){		//si no se lee un entero positivo
			System.err.println("argumento incorrecto, debe ser un entero positivo");	//lo reporta
		}
		if(num_hilos >0){		//si el numero de hilos a lanzar es valido
			new Practica01(num_hilos);		//ejecuta el programa con la cantidad correcta de hilos.
		}
	}
}