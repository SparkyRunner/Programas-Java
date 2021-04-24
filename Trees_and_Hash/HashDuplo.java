import java.util.LinkedList;

public class HashDuplo<Key, Value> {
	private int N; // numero de pares de chaves na tabela
	private int M = 16; // Tamanho da tabela hash com tratamento linear
	private Key[] keys; // the keys
	private Value[] vals; // the values

	public HashDuplo() {
		keys = (Key[]) new Object[M];
		vals = (Value[]) new Object[M];
	}
	
	public HashDuplo(int cap) {
		keys = (Key[]) new Object[cap];
		vals = (Value[]) new Object[cap];
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
		
		HashDuplo<Key, Value> t;
		t = new HashDuplo<Key, Value> (cap);
		
		for (int i = 0; i < keys.length; i++)
			if (keys[i] != null)
				t.put(keys[i], vals[i]);
		keys = t.keys;
		vals = t.vals;
		M = t.M;
		
	}
	private void resize_duplo(int cap) {
		
		HashDuplo<Key, Value> t;
		t = new HashDuplo<Key, Value> (cap);
		
		for (int i = 0; i < keys.length; i++)
			if (keys[i] != null)
				t.put_duplo(keys[i], vals[i]);
		keys = t.keys;
		vals = t.vals;
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
		if (N >= M/2) 
			resize(2*M); // double M 
		
		for (i = hash(key); keys[i] != null; i = (i + 1) % M)
			if (keys[i].equals(key)) { 
				vals[i] = val; 
				return; 
				}
		keys[i] = key;
		vals[i] = val;
		N++;
	}
	
	
	/**
	 * Remove um objeto do Hash
	 * @param key
	 */
	public void delete(Key key)
	{
		if (key == null) 
			throw new IllegalArgumentException("Argument to delete() cannot be null");
		
		if (!contains(key))
			return;
			
		int i = hash(key);
		while (!key.equals(keys[i]))
			i = (i + 1) % M;
		
		keys[i] = null;
		vals[i] = null;
		i = (i + 1) % M;
		
		while (keys[i] != null){
			Key keyToRedo = keys[i];
			Value valToRedo = vals[i];
			keys[i] = null;
			vals[i] = null;
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
		for (int i = hash(key); keys[i] != null; i = (i + 1) % M)
			if (keys[i].equals(key))
				return vals[i];
		return null;
	}
	/*------------------ Alterações -------------------- */
	private boolean is_base2(int N){
		double result = Math.log10(N)/Math.log10(2);
		int comp = (int)result;
		return result == comp;
	}
	public int hash_duplo(Key key, int index){
		/*Checa se M é base 2, soma h2 com 31 se for impar, 32 se for par e h1 com um numero primo*/
		int value = (int)key;
		
		if(is_base2(M)){

			int h, h1, h2;
			h2 = value + 31;
			if(h2 % 2 == 0){
				h2 += 1;
			}
			
			h1 = value + 37;

			h = (h1 + (index * h2)) % M;
					
			return h;
		}
		return 0;
	}
	public void put_duplo(Key key, Value val) {
		int i, res;
		i = 0;
		if (N >= M/2) 
			resize_duplo(2*M); // double M 
			
		res = hash_duplo(key, i);

		while(keys[res] != null){
			
			if (keys[res].equals(key)) { 
				vals[res] = val; /*Se as chaves derem pro mesmo hash, se o valor for diferente, ele sera atualizado */
				return; 
			}
			i++;
			res = hash_duplo(key, i);
		}
		
		keys[res] = key;
		vals[res] = val;
		N++;
	}
	public int get_duplo(Key key){
		int i, res;
		i = 0;	
		res = hash_duplo(key, i);

		while(keys[res] != null){

			if (keys[res].equals(key)) { 
				System.out.print("[" + res + "] ");
				return (int)vals[res];
			}
			i++;
			res = hash_duplo(key, i);
		}
		return -1;
	}

	
}
