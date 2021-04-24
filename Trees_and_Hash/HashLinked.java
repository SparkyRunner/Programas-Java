import java.util.LinkedList;

public class HashLinked <Key, Value> {
	private int N; // numero de pares de chaves na tabela
	private int M = 16; // Tamanho da tabela hash com tratamento linear
	private Key[] keys; // the keys
	private Value[] vals; // the values
	private LinkedList[] vals2; // vetor que armazena as listas encadeadas
	private LinkedList[] keys2;

	// as listas encadeadas sao criadas no construtor
	public HashLinked() {
		keys = (Key[]) new Object[M];
		vals = (Value[]) new Object[M];
        vals2 = (LinkedList[]) new LinkedList[M];
		keys2 = (LinkedList[]) new LinkedList[M];
        for(int i = 0 ; i < M ; i++){
          vals2[i] = new LinkedList ();
		  keys2[i] = new LinkedList ();
        }
	}
	
	public HashLinked(int cap) {
		keys = (Key[]) new Object[cap];
        vals = (Value[]) new Object[cap];
        vals2 = (LinkedList[]) new LinkedList[cap];
		keys2 = (LinkedList[]) new LinkedList[cap];
        for(int i = 0 ; i < cap ; i++){
          vals2[i] = new LinkedList ();
		  keys2[i] = new LinkedList ();
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
	private void resize(int cap) {
		
		HashLinked<Key, Value> t;
		t = new HashLinked<Key, Value> (cap);
		
		for (int i = 0; i < keys2.length; i++)
			if (keys2[i].size() > 0){
				for(int j = 0 ; j < keys2[i].size() ; j++){
					t.put((Key)keys2[i].get(j), (Value)vals2[i].get(j));
				}
			}
		keys2 = t.keys2;
		vals2 = t.vals2;
		M = t.M;
		
	}
	
	 public boolean contains(Key key) {
	        if (key == null) {
	            throw new IllegalArgumentException("Argument to contains() cannot be null");
	        }

	        return get(key) != null;
	 }
	
	/**
	 * Insere um novo objeto no Hash 
	 * @param key
	 * @param val
	 */
	public void put(Key key, Value val) {
		int i;
	
		i = hash(key);
	
		keys2[i].add(key);
		vals2[i].add(val);
		N++;
	}
	
	
	/**
	 * Remove um objeto do Hash
	 * @param key
	 */
	public void delete(Key key, Value value)
	{
		if (key == null) 
			throw new IllegalArgumentException("Argument to delete() cannot be null");
		
		if (!contains(key))
			return;
		
		int res = hash(key);

		vals2[res].remove(value);
		N++;
		
	}
	
	/**
	 * Busca um objeto no Hash
	 * @param key
	 * @return
	 */
	public Value get(Key key) {
        int i = hash(key);
		
		if(keys2[i].size() > 0){

			/*System.out.print("hash = "+i +" ");

			for(int j = 0 ; j < vals2[i].size() ; j++){
				
				System.out.print(" ["+ keys2[i].get(j) + "] ");
				System.out.print(vals2[i].get(j));
			}
			System.out.println("");
			*/
			return (Value)vals2[i];
		}
		return null;
	}

	
}
