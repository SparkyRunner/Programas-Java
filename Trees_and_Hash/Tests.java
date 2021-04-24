public class Tests {
    static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    
    public void insert_search_test(int elements){
        /*
          long tempoInicial = System.currentTimeMillis();
          long tempoFinal = System.currentTimeMillis();
          System.out.printf("\n%.3f ms%n", (tempoFinal - tempoInicial) / 1000d);
          */
          int elms = elements;
          int keys[] = new int[elms];
          int values[] = new int[elms];

          for(int i = 0 ; i < elms ; i++){
            keys[i] = getRandomNumber(0, elms);
            values[i] = getRandomNumber(0, elms);
          }
          HashTentativaLinear linear = new HashTentativaLinear<>();
          HashDuplo duplo = new HashDuplo<>();
          HashLinked linked = new HashLinked<>();
          RBTree rbtree = new RBTree<>();
          AVLTree avlTree = new AVLTree<>();

          System.out.println("Size : " + elms);
          System.out.println("-----INSERT-----");
          /*Insertion time */
          /*---------------------- linear --------------------*/
          long linear_temp_init = System.currentTimeMillis();
          for(int i = 0 ; i < elms ; i++){
            linear.put(keys[i], values[i]);  
          }
          long linear_temp_finish = System.currentTimeMillis();
          System.out.printf(" LINEAR - %.3f ms%n ", (linear_temp_finish - linear_temp_init) / 1000d);
          /*---------------------- double hash --------------------*/
          long double_temp_init = System.currentTimeMillis();
          for(int i = 0 ; i < elms ; i++){
            duplo.put(keys[i], values[i]);  
          }
          long double_temp_finish = System.currentTimeMillis();
          System.out.printf("DOUBLE - %.3f ms%n ", (double_temp_finish - double_temp_init) / 1000d);
          /*---------------------- linked --------------------*/
          long linked_temp_init = System.currentTimeMillis();
          for(int i = 0 ; i < elms ; i++){
            linked.put(keys[i], values[i]);  
          }
          long linked_temp_finish = System.currentTimeMillis();
          System.out.printf("LINKED - %.3f ms%n ", (linked_temp_finish - linked_temp_init) / 1000d);
          
          /*---------------------- AVL --------------------*/
          long avl_temp_init = System.currentTimeMillis();
          
          for(int i = 0 ; i < elms ; i++){
            avlTree.put(keys[i], values[i]);  
          }
          
          long avl_temp_finish = System.currentTimeMillis();
          System.out.printf("AVL - %.3f ms%n ", (avl_temp_finish - avl_temp_init) / 1000d);
          
          /*------------------------------------ search --------------------------------------------*/
          System.out.println("\n----- SEARCH ----- ");
          int key_to_get = getRandomNumber(0, elms-1);
          
          /*---------------------- linear --------------------*/
          long linear_get_temp_init = System.currentTimeMillis();
          
          linear.get(150);  
          
          long linear_get_temp_finish = System.currentTimeMillis();
          System.out.printf(" LINEAR - %.3f ms%n ", (linear_get_temp_finish - linear_get_temp_init) / 1000d);
          /*---------------------- double hash --------------------*/
          long double_get_temp_init = System.currentTimeMillis();
          
          duplo.get(150);  
          
          long double_get_temp_finish = System.currentTimeMillis();
          System.out.printf("DOUBLE - %.3f ms%n ", (double_get_temp_finish - double_get_temp_init) / 1000d);
          /*---------------------- linked --------------------*/
          long linked_get_temp_init = System.currentTimeMillis();
          
          linked.get(150);  
          
          long linked_get_temp_finish = System.currentTimeMillis();
          System.out.printf("LINKED - %.3f ms%n ", (linked_get_temp_finish - linked_get_temp_init) / 1000d);

          /*---------------------- AVL --------------------*/
          long avl_get_temp_init = System.currentTimeMillis();
          
          avlTree.get(150);  
          
          long avl_get_temp_finish = System.currentTimeMillis();
          System.out.printf("AVL - %.3f ms%n ", (avl_get_temp_finish - avl_get_temp_init) / 1000d);

          System.out.println("-----DELETE-----");
          /*Insertion time */
          /*---------------------- linear --------------------*/
          long linear_del_temp_init = System.currentTimeMillis();
          for(int i = 0 ; i < elms ; i++){
            linear.delete(keys[i]);  
          }
          long linear_del_temp_finish = System.currentTimeMillis();
          System.out.printf(" LINEAR - %.3f ms%n ", (linear_del_temp_finish - linear_del_temp_init) / 1000d);
          /*---------------------- double hash --------------------*/
          long double_del_temp_init = System.currentTimeMillis();
          for(int i = 0 ; i < elms ; i++){
            duplo.delete(keys[i]);  
          }
          long double_del_temp_finish = System.currentTimeMillis();
          System.out.printf("DOUBLE - %.3f ms%n ", (double_del_temp_finish - double_del_temp_init) / 1000d);
          /*---------------------- linked --------------------*/
          long linked_del_temp_init = System.currentTimeMillis();
          for(int i = 0 ; i < elms ; i++){
            linked.delete(keys[i], values[i]);  
          }
          long linked_del_temp_finish = System.currentTimeMillis();
          System.out.printf("LINKED - %.3f ms%n ", (linked_del_temp_finish - linked_del_temp_init) / 1000d);
          
          /*---------------------- AVL --------------------*/
          long avl_del_temp_init = System.currentTimeMillis();
          
          for(int i = 0 ; i < elms ; i++){
            avlTree.delete(keys[i]);  
          }
          
          long avl_del_temp_finish = System.currentTimeMillis();
          System.out.printf("AVL - %.3f ms%n ", (avl_del_temp_finish - avl_del_temp_init) / 1000d);

          
        
          
    }
}
