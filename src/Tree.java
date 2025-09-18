import java.util.Objects;
import java.util.Arrays;
import java.util.Random;

public class Tree {
    // We recommend attempting this class last, as it hasn't been scaffolded for your team.
    // Even if your team doesn't have time to implement this class, it is a useful exercise
    // to think about how you might split up the work to get the Tree and TreeMultiSet
    // implemented.
    private Integer root;
    private Tree[] subtrees;
    private final Random rand = new Random();

    public Tree(Integer root, Tree[] subtrees) {
        this.root = root;
        this.subtrees = subtrees;
    }

    public Tree() {
        this.root = null;
        this.subtrees = new Tree[0];
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public int length(){
        if (this.isEmpty()){
            return 0;
        } else {
            int len = 1;
            for (Tree subtree : this.subtrees) {
                len = len + subtree.length();}
            return len;
        }
    }

    public int count(Integer item){
        if (this.isEmpty()){
            return 0;
        } else {
            int cnt = 0;
            if (Objects.equals(this.root, item)){
                cnt = 1;
            }
            for (Tree subtree: this.subtrees){
                cnt = cnt + subtree.count(item);
            }
            return cnt;
        }
    }

    public String repr(){
        return this.reprHelper(0);
    }

    private String reprHelper(int depth){
        if (this.isEmpty()){
            return " ";
        } else {
            String s = "  ".repeat(depth) + this.root + "\n";
            for (Tree subtree: this.subtrees){
                s = s + subtree.reprHelper(depth + 1);
            }
            return s;
        }
    }

    public float average(){
        if (this.isEmpty()){
            return 0;
        } else {
            int total = this.averageHelper();
            int size = this.length();
            return (float) total / size;
        }
    }

    private int averageHelper(){
        if (this.isEmpty()){
            return 0;
        } else {
            int out = this.root;
            for (Tree subtree: this.subtrees){
                out = out + subtree.averageHelper();
            }
            return out;
        }
    }

    public boolean equals(Tree other){
        if (this.isEmpty() && other.isEmpty()) return true;
        else if (this.isEmpty() || other.isEmpty()) return false;
        else {
            if (!Objects.equals(this.root, other.root)) return false;
            if (this.subtrees.length != other.subtrees.length) return false;
            for (int i = 0; i < this.subtrees.length; i++){
                if (!this.subtrees[i].equals(other.subtrees[i])) return false;
            }
            return true;
        }
    }

    public boolean contains(int value){
        if (this.isEmpty()){
            return false;
        }

        if (Objects.equals(this.root, value)){
            return true;
        } else {
            for (Tree subtree: this.subtrees){
                if (subtree.contains(value)){
                    return true;
                }
            }
            return false;
        }
    }

    public int[] leaves(){
        if (this.isEmpty()) return new int[0];
        if (this.subtrees.length == 0){
            return new int[]{this.root};
        } else {
            int[] out = new int[0];
            for (Tree subtree: this.subtrees){
                out = this.extend_int(out, subtree.leaves());
            }
            return out;
        }
    }

    private int[] append_int(int[] current, int value){
        int[] newArr = new int[current.length + 1];
        System.arraycopy(current, 0, newArr, 0, current.length);
        newArr[current.length] = value;
        return newArr;
    }

    private Tree[] append_tree(Tree[] current, Tree value){
        Tree[] newArr = new Tree[current.length + 1];
        System.arraycopy(current, 0, newArr, 0, current.length);
        newArr[current.length] = value;
        return newArr;
    }

    private int[] extend_int(int[] arr1, int[] arr2){
        int[] newArr = new int[arr1.length + arr2.length];
        for (int val: arr2){
            arr1 = this.append_int(arr1, val);
        }
        System.arraycopy(arr1, 0, newArr, 0, arr1.length);
        return newArr;
    }

    private Tree[] extend_tree(Tree[] arr1, Tree[] arr2){
        Tree[] newArr = new Tree[arr1.length + arr2.length];
        for (Tree val: arr2){
            arr1 = this.append_tree(arr1, val);
        }
        System.arraycopy(arr1, 0, newArr, 0, arr1.length);
        return newArr;
    }

    public boolean deleteItem(int item){
        if (this.isEmpty()){
            return false;
        } else if (this.root == item) {
            this.deleteRoot();
            return true;
        } else {
            for (int i = 0; i < this.subtrees.length; i++){
                boolean deleted = this.subtrees[i].deleteItem(item);
                if (deleted && this.subtrees[i].isEmpty()){
                    this.subtrees = removeIndex(this.subtrees, i);
                    return true;
                } else if (deleted) {
                    return true;
                }
            }
            return false;
        }
    }

    private static Tree[] removeIndex(Tree[] arr, int index){
        Tree[] out = new Tree[arr.length - 1];
        for (int i = 0, k = 0; i < arr.length; i++){
            if(i!=index){
                out[k]=arr[i];
                k++;
            }
        }
        return out;
    }

    private void deleteRoot(){
        if (this.subtrees.length == 0){
            this.root = null;
        } else {
            Tree chosen_subtree = this.subtrees[this.subtrees.length - 1];
            this.subtrees = Arrays.copyOf(this.subtrees, this.subtrees.length - 1);
            this.root = chosen_subtree.root;
            this.subtrees = this.extend_tree(this.subtrees, chosen_subtree.subtrees);
        }
    }

    public void insert(int item){
        if (this.isEmpty()){
            this.root = item;
        } else if (this.subtrees.length == 0){
            this.subtrees = new Tree[]{new Tree(item, new Tree[]{})};
        } else {
            int rint = rand.nextInt(3);
            if (rint == 2){
                this.subtrees = this.append_tree(this.subtrees, new Tree(item, new Tree[]{}));
            } else {
                int subtree_index = rand.nextInt(this.subtrees.length);
                this.subtrees[subtree_index].insert(item);

            }
        }
    }

}

class Eh{
    public static void main(String[] args) {
        Tree t1 = new Tree(1, new Tree[]{new Tree(2, new Tree[]{}), new Tree(3, new Tree[]{})});
        Tree t2 = new Tree(1, new Tree[]{new Tree(2, new Tree[]{}), new Tree(3, new Tree[]{new Tree(4, new Tree[]{})})});
        Tree t3 = new Tree(null, new Tree[]{});
        for (int i = 0; i < 10; i++){
            t3.insert(i);
        }
        System.out.println(t3.repr());
    }
}

