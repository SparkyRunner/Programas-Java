import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
public class main
{

	public static void main(String[] args)throws Exception { 
	  
	
	
	boolean opt = true;
    char opt2;
    Scanner input = new Scanner(System.in);
    /*Insere a inforamçao contida no txt e cria uma estrutura grafo */
	File file = new File("C:\\Grafos\\grafo.txt");
	Grafo grafo = new Grafo(file, 3);

	int ant[] = new int[8];
	float dist[] = new float[8];
	int visitado[] = new int[8];
      while(opt){
        
        
        System.out.println("a - Mostra grafo");
        System.out.println("b - Arestas existentes entre vertices");
        System.out.println("c - Mostra caminho");
        System.out.println("d - Vertices distancia D");
        System.out.println("e - Circunferencia");
        System.out.println("f - Detecta ponte");
		System.out.println("g - 2 menores caminhos");
        System.out.println("h - Exit");
        opt2 = input.next().charAt(0);

        if(opt2 == 'a'){
			System.out.print("inicio : ");
			String inicio = input.next();
			System.out.println("mostrando grafo...");
            grafo.BFS(grafo, inicio);
        }
        if(opt2 == 'b'){
			System.out.print("inicio : ");
			String inicio = input.next();
			System.out.print('\n'+"destino : ");
			String destino = input.next();
        	System.out.println(grafo.arestas_existentes_Grafo(grafo, inicio,ant,  dist, destino));
        }
        if(opt2 == 'c'){
			System.out.print("inicio : ");
			String inicio = input.next();
			System.out.print('\n'+"destino : ");
			String destino = input.next();
          grafo.caminha_Grafo(grafo, inicio, ant, dist, destino);
        }
        
        if(opt2 == 'd'){
			System.out.print("inicio : ");
			String inicio = input.next();
			System.out.print("distancia : ");
			int distancia = input.nextInt();
        	grafo.distancia_x_Grafo(grafo, inicio, ant, dist, distancia);
        }

        if(opt2 == 'e'){
			System.out.print("inicio : ");
			String inicio = input.next();
			System.out.print('\n'+"destino : ");
			String destino = input.next();
        	System.out.println(grafo.circunferencia(grafo, inicio, ant, dist, destino));
        }
        if(opt2 == 'f'){
          
		/*Se retornar -1 no segundo indice é porque nao existe outro caminho ou é o mesmo tamanho do menor */
			ArrayList<String> result;
			result = grafo.buscaPonte_Grafo(grafo,visitado);
			if(result.size()> 0){
				System.out.println("Pontes : ");
				for(int i = 0 ; i < result.size() ; i++){
					System.out.println(result.get(i));
				}
			}else{
				System.out.println("none");
			}
		}
		if(opt2 == 'g'){
			System.out.print("inicio : ");
			String inicio = input.next();
			System.out.print('\n'+"destino : ");
			String destino = input.next();
			float temp[] = grafo.menores_caminhos_Grafo(grafo, inicio, ant, dist, destino);
			System.out.println(temp[0] + " " + temp[1]);
		} 
		if(opt2 == 'h'){
			opt = false;
  
		}
		  opt = false;
		
		input.close();
		
    }
        
    
  }
	  
	  
	  

	    

	    

		

		

		

		/*grafo.buscaProfundidade_Grafo(grafo, "d", visitado);*/

		

		
		
		
		
}