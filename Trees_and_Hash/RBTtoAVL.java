public class RBTtoAVL {
    
    public AVLTree convert(RBTree t){
        AVLTree tree = new AVLTree<>();
        int arr[] = t.getRBT();
        /*store the array with rb tree keys and values in avl tree*/; 
        for(int i = 0 ; i < arr.length ; i = i + 2){
            tree.put(arr[i], arr[i+1]);
        }
        return tree;
    }
}
