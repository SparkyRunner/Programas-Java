
public class RBTree<Key extends Comparable<Key>, Value>
{	
	
	protected static final boolean RED = true;
    protected static final boolean BLACK = false;

    protected class Node {
        public Key chave;
        public Value valor;
        public Node esq, dir;

        boolean cor;
        int size;

        Node(Key key, Value value, int size, boolean color) {
            this.chave = key;
            this.valor = value;

            this.size = size;
            this.cor = color;
        }
    }

    protected Node raiz;

	private boolean isRed(Node h) {
		if (h == null) return false;
        return (h.cor == RED);
	}
	
	private boolean isBlack(Node h) {
		// Implementar método que verifica se o nó é preto
		if (h == null) return false;
        return (h.cor == BLACK);
	}
	
	 public int size() {
        return size(raiz);
    }

    protected int size(Node no) {
        if (no == null) {
            return 0;
        }

        return no.size;
    }

    public boolean isEmpty() {
        return size(raiz) == 0;
    }
	
	 /**
	  * Rotação à equerda
	  * @param node
	  * @return
	  */
	 protected Node rotacaoEsquerda(Node no) {
        if (no == null || no.dir == null) {
            return no;
        }

        Node novaRaiz = no.dir;

        no.dir = novaRaiz.esq;
        novaRaiz.esq = no;

        novaRaiz.cor = no.cor;
        novaRaiz.cor = RED;

        novaRaiz.size = no.size;
        novaRaiz.size = size(no.esq) + 1 + size(no.dir);

        return novaRaiz;
    }
		
	/**
	 * Implementar o esse método
	 * @param h
	 * @return
	 */
	private Node rotacaoDireita(Node h) {
		
		if (h == null || h.dir == null) {
            return h;
        }
        
        Node novaRaiz = h.esq;

        h.esq = novaRaiz.dir;
        novaRaiz.dir = h;

        novaRaiz.cor = novaRaiz.dir.cor;
        novaRaiz.cor = RED;

        novaRaiz.size = h.size;
        novaRaiz.size = size(h.esq) + 1 + size(h.dir);
		h.size = size(h.esq) + size(h.dir) + 1;

        return novaRaiz;
	}
	private void trocaCor(Node h) {
		// o h tem que ter a cor contraria a seus filhos
		// 
		if(h != null)
		    h.cor = !h.cor;
		if(h.esq != null)
		    h.esq.cor = !h.esq.cor;
        if(h.dir != null)
		    h.dir.cor = !h.dir.cor;
	}
	
	
	/**
	 * Insere um novo nó
	 * @param key
	 * @param val
	 */
	public void insere(Key key, Value val){ 
		raiz = insere(raiz, key, val);
		raiz.cor = BLACK;
	}
	
	private Node insere(Node h, Key key, Value val)
	{
		if (h == null){ // Do standard insert, with red link to parent.	
			
			return new Node(key, val, 1, RED);
		}
		
		int cmp = key.compareTo(h.chave);
		if (cmp < 0) 
			h.esq = insere(h.esq, key, val);
		else if (cmp > 0) 
			h.dir = insere(h.dir, key, val);
		else h.valor = val;
		
	
        //Rotaçao
        if(isRed(h.dir) && !isRed(h.esq))
        {
            h = rotacaoEsquerda(h);
        }
        
        if(isRed(h.dir) && isRed(h.dir.dir))
        {
            h = rotacaoDireita(h);
        }
        
        if(isRed(h.esq) && isRed(h.dir)){
            trocaCor(h);
        }
            
		
		h.size = size(h.esq) + size(h.dir) + 1;
		return h;
	}
	/*printar a arvore */
	private void inOrder(Node tree) {
        if(tree != null) {
            
            inOrder(tree.esq);
            System.out.println(toString(tree));
            inOrder(tree.dir);
        }
    }
    public void inOrder(){
        inOrder(raiz);
    }

    public String toString(Node h) {

        return "Node{" +
                "color=" + h.cor +
                ", key=" + h.chave +
                '}';
    }
	/*faz pos ordem e retorna a soma e for vermelho, no final calcula o percentual  */
	private float countRed(Node tree) {
        if(tree != null) {
			float l = 0;
			float r = 0;
			int this_node = 0;
			if(tree.cor == RED){
				this_node = 1;
			}
            l = l + countRed(tree.esq);
            r = r + countRed(tree.dir);
			return l + r + this_node;
        }

		return 0;
    }
    public float countRed(){
        return (countRed(raiz) * 100 /raiz.size);
    }
	
/* GET ELEMENTS FROM A RBT TREE AND STORE IN A ARRAY   (KEYS IN ODD POSITION EVEN) */
	private void getRBT(Node tree, int [] arr, int cont) {
        if(tree != null) {
            
            arr[cont] = (int)tree.chave;
            arr[cont+1] = (int)tree.valor;
            cont = cont + 2;
            getRBT(tree.esq, arr, cont);
            
            cont = cont + 2;
            
            getRBT(tree.dir, arr, cont);
            
            
        }
        
    }
    public int [] getRBT(){
        int[] arr = new int[(raiz.size)*2];
        int cont = 0;
        getRBT(raiz, arr, cont);
        return arr;
    }

    public Value get(Key chave) {
        if (chave == null) {
            return null;
        }

        return get(raiz, chave);
    }

    private Value get(Node no, Key chave) {
        if (no == null) {
            return null;
        }

        int compare = chave.compareTo(no.chave);
        if (compare < 0) {
            return get(no.esq, chave);
        } else if (compare > 0) {
            return get(no.dir, chave);
        } else {
            return no.valor;
        }
    }

 
}
