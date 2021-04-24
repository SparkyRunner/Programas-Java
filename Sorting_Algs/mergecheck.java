/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/
import java.util.Random;
public class mergecheck
{
	public static void main(String[] args) {
	    Random rd = new Random(); // creating Random object
	    int[] arr = new int[5000000];
	    
        for (int i = 0; i < arr.length; i++) {
            int temp = rd.nextInt();
            if(temp < 0 ){ // if number generated is less than zero then multiply by -1
                arr[i] = temp*(-1);
            }else{
                arr[i] = temp; // storing random integers in an array
            }
        }
        
		
		/* Test Array to know if the functions are really sorting correctly*/
		/*
		int [] arr = {7, 5, 4, 3, 1, 20, 10, 15, 9, 22, 23,21, 27, 32, 2, 14, 25 };
		*/
		
	    long tempoInicial = System.currentTimeMillis();
	    
	    int [] res = MergeSort(arr, 0);
	    
	    long tempoFinal = System.currentTimeMillis();
	   
	    System.out.printf("\n%.3f ms%n", (tempoFinal - tempoInicial) / 1000d);
	    /*
	    for(int i = 0; i < res.length ; i++){
	        System.out.printf("%d ", res[i]);
	    }
	    System.out.printf("\n");
	    */
	   
	 /*------------------------------------------------------------------------*/   
	    long tempoInicial2 = System.currentTimeMillis();
	    
	    int [] res2 = MergeSort(arr, 1);
	    
	    long tempoFinal2 = System.currentTimeMillis();
	   
	    System.out.printf("\n%.3f ms%n", (tempoFinal2 - tempoInicial2) / 1000d);
	   /* 
	     for(int i = 0; i < res2.length ; i++){
	        System.out.printf("%d ", res2[i]);
	    }
	    */

	}
	

public static int[] MergeSort(int [] A, int opt){
    int [] Temp = new int[A.length];
    if(opt == 0){
        return MergeMain(A, Temp, 0, A.length-1);
    }else if(opt == 1){
        return MergeCheckMain(A, Temp, 0, A.length-1);
    }else{
        System.out.printf("No valid option, running normal Merge");
        return MergeMain(A, Temp, 0, A.length-1);
    }
}

public static int [] MergeMain(int [] A, int [] T, int esq, int dir){
  int meio;
  if (esq < dir){
    
        meio = (esq + dir) / 2;
        MergeMain(A, T, esq, meio);
        MergeMain(A, T, meio + 1, dir);
        Merge(A, T, esq, meio+1, dir);
    
  }
  return A;
}
public static int [] MergeCheckMain(int [] A, int [] T, int esq, int dir){
  int meio;
  if (esq < dir){
    
        meio = (esq + dir) / 2;
        MergeCheckMain(A, T, esq, meio);
        MergeCheckMain(A, T, meio + 1, dir);
        if(A[meio] > A[meio + 1]){
            Merge(A, T, esq, meio+1, dir);
        }
  }
  return A;
}
private static void Merge (int []A, int [] T, int esqPos, int dirPos, int dirFim){
  int esqFim = dirPos -1;
  int tempPos = esqPos;
  int numElem = dirFim - esqPos + 1;

  while(esqPos <= esqFim && dirPos <= dirFim) {
    if(A[esqPos] <= A[dirPos]){
      T[tempPos++] = A[esqPos++];
    }else{
      T[tempPos++] = A[dirPos++];
    }
  }
  // copia o resto da primeira parte
  while(esqPos <= esqFim){
    T[tempPos++] = A[esqPos++];
  }
  // copia o resto da primeira parte
   while(dirPos <= dirFim){
     T[tempPos++] = A[dirPos++];
   }
  // copia o vetor
  for(int i = 0 ; i < numElem; i++, dirFim--){
    A[dirFim] = T[dirFim];
  }
}
    public static int[] InsertionSort(int [] A) {
        int i, j, chave;
        for(j = 1; j < A.length; j++){
            chave = A[j];
            i = j - 1;
        
            while(i>= 0 && A[i] > chave){
                A[i+1] = A[i];
                i--;
            }
            A[i+1] = chave;
        }
        return A;
    }
}

