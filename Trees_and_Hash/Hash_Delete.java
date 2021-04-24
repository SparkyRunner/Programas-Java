import java.util.LinkedList;

public class Hash_Delete<Key, Value> {
	private int N; // numero de pares de chaves na tabela
	private int M = 16; // Tamanho da tabela hash com tratamento linear
	private Key[] keys; // the keys
	private Value[] vals; // the values
	private boolean[] flag; // flag para saber se a posição chave valor está disponivel

	public Hash_Delete() {
		keys = (Key[]) new Object[M];
		vals = (Value[]) new Object[M];
		flag = (boolean[]) new boolean[M];
		for(int i = 0 ; i < M ; i++){
			flag[i] = true;
		}
	}
	
	public Hash_Delete(int cap) {
		keys = (Key[]) new Object[cap];
		vals = (Value[]) new Object[cap];
		flag = (boolean[]) new boolean[cap];
		for(int i = 0 ; i < cap ; i++){
			flag[i] = true;
		}
		M = cap;
	}
	
	/**
	 * Calcula o Hash
	 * @param key
	 * @return
	 */
	private int hash(Key key){ 
		return (key.hashCode() & 0x7fffffff) % M; 
	}
	
	/**
	 * Redimensiona a tabela de acordo com a quantidade de chaves.
	 * @param cap
	 */
	/* */
	/*Agora cria-se tambem uma flag com o tamanho do resizee e apos a recolocação a nova flag recebe os novos reposicionamentos */
	private void resize(int cap) {
		
		Hash_Delete<Key, Value> t;
		boolean[]t2;

		t = new Hash_Delete<Key, Value> (cap);
		t2 = (boolean[]) new boolean[cap];
		int temp_hash;
		for(int i = 0 ; i < cap ; i++){
			t2[i] = true;
		}
		
		for (int i = 0; i < keys.length; i++)
			if (flag[i] != true){
				t.put(keys[i], vals[i]);
				/*pega o hash do vetor de hash e recalcula para colocar na nova flag */
				temp_hash =  t.hash(keys[i]); 
				while(keys[temp_hash] != null){
					temp_hash = (temp_hash + 1) % cap;
				}
				t2[temp_hash] = false;
			}
		
		
		
		keys = t.keys;
		vals = t.vals;
		flag = t2;
		M = t.M;
		
	}
	
	 public boolean contains(Key key) {
	        if (key == null) {
	            throw new IllegalArgumentException("Argument to contains() cannot be null");
	        }
			
	        return get(key) != null && flag[hash(key)] == false;
	 }
	
	/**
	 * Insere um novo objeto no Hash 
	 * @param key
	 * @param val
	 */
	public void put(Key key, Value val) {
		int i;
		if (N >= M/2) 
			resize(2*M); // double M 
		
		for (i = hash(key); flag[i] != true; i = (i + 1) % M)
			if (keys[i].equals(key)) { 
				vals[i] = val; 
				return; 
				}
		keys[i] = key;
		vals[i] = val;
		flag[i] = false;
		N++;
	}
	
	
	/**
	 * Remove um objeto do Hash
	 * @param key
	 */
	/*Em vez de checar a posição da chave e valor, checa o array flag para saber se a posição esta ocupada ou nao, sobreescrevendo em vez de remover */
	public void delete(Key key)
	{
		
		if (key == null) 
			throw new IllegalArgumentException("Argument to delete() cannot be null");
			
		if (!contains(key))
			return;
	
		int i = hash(key);
		while (!key.equals(keys[i]))
			i = (i + 1) % M;
		
			
		flag[i] = true;
		i = (i + 1) % M;
		
		while (flag[i] != true){
			
			Key keyToRedo = keys[i];
			Value valToRedo = vals[i];
			flag[i] = true;
			N--;
			
			put(keyToRedo, valToRedo);
			i = (i + 1) % M;
		}
		
		N--;
		if (N > 0 && N == M/8) 
			
			resize(M/2);
			
	}
	
	/**
	 * Busca um objeto no Hash
	 * @param key
	 * @return
	 */
	public Value get(Key key) {
		
		for (int i = hash(key); flag[i] != true; i = (i + 1) % M)
			if (keys[i].equals(key))
				return vals[i];
		return null;
	}

	public void show_flag(){
		for(int i = 0 ; i < flag.length ; i++){
			System.out.print(flag[i]+" ");
		}
		System.out.println("");
		for(int i = 0 ; i < flag.length ; i++){
			System.out.print(vals[i]+" ");
		}
		System.out.println("");
		
		/*for(int i = 0 ; i < vals.length ; i++){
			System.out.print(vals[i]+" ");
		}

		*/
		
	}

	
}
