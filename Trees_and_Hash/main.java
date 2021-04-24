import java.util.LinkedList;
import java.util.Scanner;

class main {  
    public static void main(String args[]) {
      boolean opt = true;
      char opt2;
      Scanner input = new Scanner(System.in);
      
      while(opt){
        
        
        System.out.println("a - Hash duplo");
        System.out.println("b - Hash Linked list");
        System.out.println("c - Hash Delete");
        System.out.println("d - RBT");
        System.out.println("e - RBT to AVL");
        System.out.println("f - Tests");
        System.out.println("g - Exit");
        opt2 = input.next().charAt(0);

        if(opt2 == 'a'){
          HashDuplo hash_obj = new HashDuplo<>();
          /*
          Adiciona dois elementos que colidem e são tratados com hash duplo  
          hash_obj.put_duplo(23, 20);
          System.out.println(hash_obj.get_duplo(23));
          hash_obj.put_duplo(39, 50);
          System.out.println(hash_obj.get_duplo(39));
          */
          int elms= getRandomNumber(10, 20);

          for(int i = 0 ; i < elms ; i++){
            hash_obj.put_duplo(getRandomNumber(0, 100), getRandomNumber(0, 50));
          }
          for(int i = 0 ; i < 100 ; i++){
            int temp_result = hash_obj.get_duplo(i);
            if(temp_result != -1){
              System.out.println(temp_result);
            }else{
              
            }
          }
        }
        if(opt2 == 'b'){
          /*Uma quantidade X de pares (chave Y, valor Z) todos os 3 gerados aleatoriamente e colocados com tratamento de lista encadeada */
          HashLinked obj = new HashLinked<>();
          int elms= getRandomNumber(15, 20);

          for(int i = 0 ; i < elms ; i++){
            obj.put(getRandomNumber(0, 100), getRandomNumber(0, 50));
          }
          for(int i = 0 ; i < elms ; i++){
            System.out.println(obj.get(i));
            
          }
        }
        if(opt2 == 'c'){
          /*Insere 3 elementos que colidem, deleta sem remoção fisica e o resize acontece, preservando a nova ordem */
          Hash_Delete hash_obj = new Hash_Delete<>(10);
          hash_obj.put(1, 7);
          hash_obj.put(11,5);
          hash_obj.put(111,9);
          hash_obj.show_flag();
          
          hash_obj.delete(1);
          hash_obj.show_flag();
          
        }
        
        if(opt2 == 'd'){
          /*Insere nos na arvore e calcula a quantidade de nos vermelhos*/

          RBTree t = new RBTree<>();
          int tree_size = getRandomNumber(30, 70);

          for(int i = 0 ; i < tree_size ; i++){
            t.insere(getRandomNumber(0, 100), getRandomNumber(0, 500));
          }
        
          System.out.println(t.countRed());
         
        }

        if(opt2 == 'e'){
          /* arvores instanciadas */
          RBTtoAVL conv = new RBTtoAVL();

          AVLTree av_tree = new AVLTree<>();
          RBTree rbtree = new RBTree<>();

          /*cria um tamanho aleatorio para a arvore e adiciona chaves e valores aleatorios */
          int tree_size = getRandomNumber(30, 70);

          for(int i = 0 ; i < tree_size ; i++){
            rbtree.insere(getRandomNumber(0, 100), getRandomNumber(0, 500));
          }
          
          /*converte a rbtree em avltree */
          av_tree = conv.convert(rbtree);

          rbtree.inOrder();
          System.out.println(" ");
          av_tree.inOrder();
        }
        if(opt2 == 'f'){
          Tests trigger = new Tests();

            trigger.insert_search_test(getRandomNumber(1000, 10000));
            trigger.insert_search_test(getRandomNumber(50000, 500000));
            trigger.insert_search_test(getRandomNumber(1000000, 5000000));
        }
        if(opt2 == 'g'){
          opt = false;
        } 
        if(opt2 == 'h'){
          RBTree r = new RBTree<>();
          r.insere(3, 5);
          r.insere(4, 8);
          r.insere(9, 10);

          r.inOrder();

        }
        opt = false;
      }
      input.close();
    
  }
  static int getRandomNumber(int min, int max) {
    return (int) ((Math.random() * (max - min)) + min);
}
}