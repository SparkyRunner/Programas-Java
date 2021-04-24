import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Grafo {
    int n_vertices;
    String arestas[][];
    int grau_max;
    int grau[];
    float pesos[][];
    boolean ponderado;
    String insert_order[];
    /*Recebe o caminho do arquivo do grafo e le linha por linha */
    Grafo(File file, int grau_max)throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(file)); 
	  
        String string; 
        /*A primeira linha contem o numero de vertices */
        string = br.readLine();
        /*Realiza as pre configuraçoes do grafo a partir do numero de vertices */
        pre_sets(Integer.parseInt(string), grau_max);
        set_arestas_grau();
        set_insert_order();

        /*Pega a primeira linha com informaçoes dos vertices e arestas, detecta se tem peso e aplica a devida inserçao */
        string = br.readLine();

        String str[] = string.split(" ");
        
        if(str.length == 2){
            ponderado = false;
            insert_find(str[0], str[1]);
            if(find_index(str[1]) == -1){
                boolean flag = false;
                for(int i = 0 ; i < n_vertices ; i++){
                    if(insert_order[i] == null){
                        if(!flag){
                            insert_order[i] = str[1];
                            flag = true;
                        }
                    }
                }
            }
            while ((string = br.readLine()) != null){
            
                str = string.split(" ");
    
                insert_find(str[0], str[1]);
                if(find_index(str[1]) == -1){
                    boolean flag = false;
                    for(int i = 0 ; i < n_vertices ; i++){
                        if(insert_order[i] == null){
                            if(!flag){
                                insert_order[i] = str[1];
                                flag = true;
                            }
                        }
                    }
                }
                
            }
        }else if(str.length == 3){
            set_peso();
            ponderado = true;
            insert_find_peso(str[0], str[1], str[2]);
            if(find_index(str[1]) == -1){
                boolean flag = false;
                for(int i = 0 ; i < n_vertices ; i++){
                    if(insert_order[i] == null){
                        if(!flag){
                            insert_order[i] = str[1];
                            flag = true;
                        }
                    }
                }
            }
            while ((string = br.readLine()) != null){
            
                str = string.split(" ");
    
                insert_find_peso(str[0], str[1], str[2]);
                
                if(find_index(str[1]) == -1){
                    boolean flag = false;
                    for(int i = 0 ; i < n_vertices ; i++){
                        if(insert_order[i] == null){
                            if(!flag){
                                insert_order[i] = str[1];
                                flag = true;
                            }
                        }
                    }
                }
                
            }
        }
        
    }

    /*Mostra a lista de adjacencia andando pelo array insert order, pegando o index e procurando na lista/matrix de arestas */
    public void lista_adj(){
        if(ponderado == false){
            for(int i = 0 ; i < this.n_vertices ; i++){
                for(int j = 0 ; j < this.grau[i] ; j++){
                    System.out.println(this.insert_order[i] + ' ' + this.arestas[i][j]);
                }
            }
        }else{
            for(int i = 0 ; i < this.n_vertices ; i++){
                for(int j = 0 ; j < this.grau[i] ; j++){
                    System.out.println(this.insert_order[i] + ' ' + this.arestas[i][j] + ' ' + this.pesos[i][j]);
                }
            }
        }
        
    }
    public void pre_sets(int n, int grau_max){
        this.n_vertices = n;
        this.grau_max = grau_max;
        

        insert_order = new String[n_vertices];
        for(int i = 0 ; i < n_vertices ; i++){
            insert_order[i] = "*";
        }

    }
    public void set_arestas_grau(){
        this.arestas = new String[this.n_vertices][this.grau_max];
        this.grau = new int[this.n_vertices];
        for(int i = 0 ; i < this.n_vertices ; i++){
            this.grau[i] = 0;
        }

    }
    
    public void set_insert_order(){
        insert_order = new String[n_vertices];
       
    }
    public void set_peso(){
        pesos = new float[n_vertices][grau_max];
    }
    /*Procura o valor na lista de inserçao e insere de acordo na lista */
    public void insert_find(String orig, String dest){
        int index = -1;
        boolean flag = false;
        for(int i = 0 ; i < n_vertices ; i++){
            if(!flag){
                if(insert_order[i] == null){
                    insert_order[i] = orig;
                    
                    index = i;
                    
    
                    flag = true;
                }else if(insert_order[i].equals(orig)){
                    index = i;
                    flag = true;
    
                }
            }
        }
        if(index >= 0){
            arestas[index][grau[index]] = dest;
            grau[index] += 1;
        }
    }
    /*Insere no grafo com peso */
    public void insert_find_peso(String orig, String dest, String peso){
        int index = -1;
        boolean flag = false;
        for(int i = 0 ; i < n_vertices ; i++){
           
            
            if(!flag){
                if(insert_order[i] == null){
                    insert_order[i] = orig;
                    
                    index = i;
                    
    
                    flag = true;
                }else if(insert_order[i].equals(orig)){
                    index = i;
                    flag = true;
    
                }
            }
            
        }
        if(index >= 0){
            arestas[index][grau[index]] = dest;
            pesos[index][grau[index]] = Float.parseFloat(peso);
            grau[index] = grau[index] + 1;
        }
    }
    /*Procura no vetor de inserção o indice correspondente ao valor */
    public int find_index(String valor){
        int index = -1;
        for(int i = 0 ; i < n_vertices ; i++){
            
            if(valor.equals(insert_order[i])){
                
                index = i;
                break;

            }
        }
        return index;
    }

    public void BFS(Grafo g, String ini){
        int vert, NV, cont = 1, IF =0, FF =0;
        int visitados[] = new int[g.n_vertices];
        /*Marca vertices como nao visitados */
        for(int i = 0 ; i < g.n_vertices ; i++){
            visitados[i] = 0;
        }

        NV = g.n_vertices;
        
        int fila[] = new int[NV];
        FF++;

        int index = find_index(ini);
        if(index >= 0){
            fila[FF] = index;
            visitados[index] = cont;
            int aux_cont = cont; 
            System.out.println(insert_order[index]);
            while(IF != FF){
                IF = (IF + 1) % NV;
                vert = fila[IF];
                cont++;
               
                for(int i = 0 ; i < g.grau[vert]; i++){
                    int temp = find_index(g.arestas[vert][i]);
                    if(temp >= 0){
                        
                        if(visitados[temp] == 0){
                            System.out.println(insert_order[temp]);
                            if(g.grau[temp]>0){
                                    FF = (FF + 1) %NV;
                                    fila[FF] = temp;
                            }
                            visitados[temp] = cont;
                        }
                    }
                    
                   
                }
            }

        }else{
            System.out.println("not find initial index");
        }
        
    }

    

    int procuraMenorDistancia(float dist[], int visitado[],int NV){
        int menor = -1, primeiro = 1;
        for(int i = 0 ; i < NV ; i++){
            if(dist[i] >= 0 && visitado[i] == 0){
                if(primeiro==1){
                    menor = i;
                    primeiro = 0;
                }else{
                    if(dist[menor] > dist[i]){
                        menor = i;
                    }
                }
            }
        }
        return menor; // se retorna -1 é pq o grafo é desconexo
    }

    public float arestas_existentes_Grafo(Grafo g, String ini, int ant[], float dist[], String dest){
        int cont, NV, ind, visitado[], u;
        cont = NV = g.n_vertices;
        visitado = new int[NV];
        for(int i = 0 ;i < NV ; i++){//Cria vetor auxiliar , inicializa distancias , anteriores e visitados
            ant[i] = -1;
            dist[i] = -1;
            visitado[i] = 0;
        }
    
        dist[find_index(ini)] = 0;
        while(cont > 0){//Procura vertice com menor distancia e marca como visitado
            u = procuraMenorDistancia(dist, visitado, NV);
            if(u == -1){
    
                break;
            }
            visitado[u] = 1;
            cont--;
            for(int i = 0 ; i < g.grau[u] ; i++){ //vertice vizinho
                ind = find_index(g.arestas[u][i]);
                if(dist[ind] < 0){
                    if(g.ponderado == true){
                        dist[ind] = dist[u] + g.pesos[u][i];
                        ant[ind] = u;
                    }else{
                        dist[ind] = dist[u] + 1;
                        ant[ind] = u;
                    }
    
                }else{
                    if(g.ponderado == true){
                        if(dist[ind] > dist[u] + g.pesos[u][i]){
                            dist[ind] = dist[u] + g.pesos[u][i];
                            ant[ind] = u;
                        }
                    }else{
                        if(dist[ind] > dist[u] + 1){
                        dist[ind] = dist[u] + 1;
                        ant[ind] = u;
                    }
    
                    }
                }
            }
        }
        
        
        
        
        return dist[find_index(dest)];
    
    }

    public void caminha_Grafo(Grafo g, String ini, int ant[], float dist[], String dest){
        int cont, NV, ind, visitado[], u;
        cont = NV = g.n_vertices;
        visitado = new int[NV];
        for(int i = 0 ;i < NV ; i++){//Cria vetor auxiliar , inicializa distancias , anteriores e visitados
            ant[i] = -1;
            dist[i] = -1;
            visitado[i] = 0;
        }
    
        dist[find_index(ini)] = 0;
        while(cont > 0){//Procura vertice com menor distancia e marca como visitado
            u = procuraMenorDistancia(dist, visitado, NV);
            if(u == -1){
    
                break;
            }
            visitado[u] = 1;
            cont--;
            for(int i = 0 ; i < g.grau[u] ; i++){ //vertice vizinho
                ind = find_index(g.arestas[u][i]);
                if(dist[ind] < 0){
                    if(g.ponderado == true){
                        dist[ind] = dist[u] + g.pesos[u][i];
                        ant[ind] = u;
                    }else{
                        dist[ind] = dist[u] + 1;
                        ant[ind] = u;
                    }
    
                }else{
                    if(g.ponderado == true){
                        if(dist[ind] > dist[u] + g.pesos[u][i]){
                            dist[ind] = dist[u] + g.pesos[u][i];
                            ant[ind] = u;
                        }
                    }else{
                        if(dist[ind] > dist[u] + 1){
                        dist[ind] = dist[u] + 1;
                        ant[ind] = u;
                    }
    
                    }
                }
            }
        }
        
        
        boolean flag = true;
        int state = find_index(dest);
        while(flag){

            System.out.print(insert_order[state] + " ");
            state = ant[state];
            if(insert_order[state].equals(ini)){
                System.out.print(insert_order[state] + " ");
                flag = false;
            }
        }
    
    
    }

    public void distancia_x_Grafo(Grafo g, String ini, int ant[], float dist[], int dist_aresta){
        int cont, NV, ind, visitado[], u;
        cont = NV = g.n_vertices;
        visitado = new int[NV];
        for(int i = 0 ;i < NV ; i++){//Cria vetor auxiliar , inicializa distancias , anteriores e visitados
            ant[i] = -1;
            dist[i] = -1;
            visitado[i] = 0;
        }
    
        dist[find_index(ini)] = 0;
        while(cont > 0){//Procura vertice com menor distancia e marca como visitado
            u = procuraMenorDistancia(dist, visitado, NV);
            if(u == -1){
    
                break;
            }
            visitado[u] = 1;
            cont--;
            for(int i = 0 ; i < g.grau[u] ; i++){ //vertice vizinho
                ind = find_index(g.arestas[u][i]);
                if(dist[ind] < 0){
                    if(g.ponderado == true){
                        dist[ind] = dist[u] + g.pesos[u][i];
                        ant[ind] = u;
                    }else{
                        dist[ind] = dist[u] + 1;
                        
                        ant[ind] = u;
                    }
                    
    
                }else{
                    if(g.ponderado == true){
                        if(dist[ind] > dist[u] + g.pesos[u][i]){
                            dist[ind] = dist[u] + g.pesos[u][i];
                            ant[ind] = u;
                        }
                    }else{
                        if(dist[ind] > dist[u] + 1){
                        dist[ind] = dist[u] + 1;
                        ant[ind] = u;
                    }
    
                    }
                }
                
            }
            if(dist[u] <= dist_aresta && visitado[u] != 0){
                System.out.print( insert_order[u ]+ " ");
            }
           
        }

    }
    public float circunferencia(Grafo g, String ini, int ant[], float dist[], String dest){
       
        return arestas_existentes_Grafo(g, ini, ant, dist, dest) + arestas_existentes_Grafo(g, dest, ant, dist, ini);
        
    
    }

    public void buscaProfundidade(Grafo g, String ini, int visitado[], int cont, boolean flag){
        visitado[find_index(ini)] = cont; //Marca o vertice como visitado
        for(int i = 0 ; i<g.grau[find_index(ini)] ; i++){
            if(flag){
                if(g.ponderado == true){
                    System.out.println(insert_order[find_index(ini)] + " " + g.pesos[find_index(ini)][i] + " " + g.arestas[find_index(ini)][i]);
                }else{
                    System.out.println(insert_order[find_index(ini)]+ " " + g.arestas[find_index(ini)][i]);
                }
            }
            if(visitado[find_index(g.arestas[find_index(ini)][i])] == 0){
                buscaProfundidade(g, g.arestas[find_index(ini)][i],visitado, cont+1, flag); // visita os vizinhos nao visitados
            }
        }
    }
    public void buscaProfundidade_Grafo(Grafo g, String ini, int visitado[]){
        int cont = 1;
        for(int i = 0 ; i < g.n_vertices ; i++){// marca os vertices como nao visitados
            visitado[i] = 0;
        }
        buscaProfundidade(g, ini, visitado, cont, true);
    }

    public void buscaProfundidade2(Grafo g, String ini, int visitado[], int cont, boolean flag, int skip1, int skip2){
        visitado[find_index(ini)] = cont; //Marca o vertice como visitado
        for(int i = 0 ; i<g.grau[find_index(ini)] ; i++){
            /*Nem eu entendi direito como cheguei nessa conclusao mas checa se a aresta a ser pulada é x-y ou y-x, em ambos os casos a aresta é pulada para checar se a falta dela acarreta na nao visita de algum vertice */
            if((insert_order[find_index(ini)].equals(insert_order[skip1])) && (g.arestas[skip1][skip2].equals(g.arestas[find_index(ini)][i])) || (insert_order[find_index(ini)].equals(g.arestas[skip1][skip2])) && (insert_order[skip1].equals(g.arestas[find_index(ini)][i]))){
                /*System.out.println("skipping " + insert_order[find_index(ini)] +" " + g.arestas[skip1][skip2]);*/
                
            }else{
                if(flag){
                    if(g.ponderado == true){
                        System.out.println(insert_order[find_index(ini)] + " " + g.pesos[find_index(ini)][i] + " " + g.arestas[find_index(ini)][i]);
                    }else{
                        System.out.println(insert_order[find_index(ini)]+ " " + g.arestas[find_index(ini)][i]);
                    }
                }
                if(visitado[find_index(g.arestas[find_index(ini)][i])] == 0){
                    buscaProfundidade2(g, g.arestas[find_index(ini)][i],visitado, cont+1, flag, skip1, skip2); // visita os vizinhos nao visitados
                }
                
            }
        }
    }
    public ArrayList<String> buscaPonte_Grafo(Grafo g, int visitado[]){
        ArrayList<String> result = new ArrayList<String>();
        for(int i = 0 ; i < g.n_vertices ; i++){
            
            for(int j = 0 ; j < g.grau[i] ; j++){
                int cont = 1;
                for(int l = 0 ; l < g.n_vertices ; l++){// marca os vertices como nao visitados
                    visitado[l] = 0;
                }
               
                buscaProfundidade2(g, insert_order[i], visitado, cont, false, i, j);
                boolean flag = true;
                
                for(int k = 0 ; k < g.n_vertices ; k++){// marca os vertices como nao visitados
                    if(visitado[k] == 0){
                        if(flag){
                            result.add(g.insert_order[i] + " " + g.arestas[i][j]);
                           
                            flag = false;
                        }
                    }
                  
                    
                }
             

            }
            
            
        }
        return result;
    }

    
    
    
    
    public float[] menores_caminhos_Grafo(Grafo g, String ini, int ant[], float dist[], String dest){
        int cont, NV, ind, visitado[], u;
        cont = NV = g.n_vertices;
        visitado = new int[NV];
        for(int i = 0 ;i < NV ; i++){//Cria vetor auxiliar , inicializa distancias , anteriores e visitados
            ant[i] = -1;
            dist[i] = -1;
            visitado[i] = 0;
        }
        float lower_dists[] = new float[2];
        dist[find_index(ini)] = 0;
        while(cont > 0){//Procura vertice com menor distancia e marca como visitado
            u = procuraMenorDistancia(dist, visitado, NV);
            
            
            if(u == -1){
    
                break;
            }
            visitado[u] = 1;
            cont--;
           
            for(int i = 0 ; i < g.grau[u] ; i++){ //vertice vizinho
                ind = find_index(g.arestas[u][i]);
                
                if(dist[ind] < 0){
                    if(g.ponderado == true){
                        dist[ind] = dist[u] + g.pesos[u][i];
                        ant[ind] = u;
                    }else{
                        dist[ind] = dist[u] + 1;
                        ant[ind] = u;
                    }
    
                }else{
                    if(g.ponderado == true){
                        if(dist[ind] > dist[u] + g.pesos[u][i]){
                            dist[ind] = dist[u] + g.pesos[u][i];
                            ant[ind] = u;
                        }
                    }else{
                        
                        if(dist[ind] > dist[u] + 1){
                            dist[ind] = dist[u] + 1;
                            ant[ind] = u;
                        }
    
                    }
                }
            }
        }
        
        int dest_ind = find_index(dest);
        lower_dists[0] = dist[dest_ind];
        

        float minus = -1;
        for(int i = 0 ; i < g.n_vertices ; i++){
            for(int j = 0 ; j < g.grau[i] ; j++){
                if(g.arestas[i][j].equals(dest)){
                    float dist_temp = dist[i]+1; 
                    
                    if(minus >= 0){
                        if(dist_temp > dist[dest_ind]  && dist_temp < minus){
                            minus = dist_temp;
                        }
                    }else if(dist_temp > dist[dest_ind]){
                        minus = dist_temp;
                    }
                }
            }
        }
        lower_dists[1] = minus;
        return lower_dists;
    
    }

    boolean removeAresta(Grafo g, String orig_str, String dest_str, boolean digrafo){
        int orig = find_index(orig_str);
      
        int ind = -1;
        for(int i = 0 ; i < g.grau[orig] ; i++){
            if(g.arestas[orig][i] == dest_str){
                ind = i;
            }
        }
        if(ind >= 0){
            for(int j = ind ; j < g.grau[orig] ; j++){
                g.arestas[orig][j] = g.arestas[orig][j+1];
                if(g.ponderado){
                    g.pesos[orig][j] = g.pesos[orig][j+1];
                }
            }
            g.grau[orig] = g.grau[orig] - 1;
            if(digrafo){
                removeAresta(g, dest_str, orig_str, false);
            }
        }
        return false;
    }
    
    boolean insereAresta(Grafo g, String orig_str, String dest, boolean digrafo, float peso){
        if(g != null){
           int orig = find_index(orig_str);
           g.arestas[orig][g.grau[orig]] = dest;
           if(g.ponderado){
               g.pesos[orig][g.grau[orig]] = peso;
           }
           g.grau[orig] = g.grau[orig] + 1;
           if(digrafo){
               insereAresta(g, dest, orig_str, false, peso);
           }
        }
        return false;
    }
    
}
