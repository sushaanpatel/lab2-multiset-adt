import java.util.Objects;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Tree {
    // We recommend attempting this class last, as it hasn't been scaffolded for your team.
    // Even if your team doesn't have time to implement this class, it is a useful exercise
    // to think about how you might split up the work to get the Tree and TreeMultiSet
    // implemented.
    private Integer root;
    private Tree[] subtrees;

    public Tree(int root, Tree[] subtrees) {
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
            return "";
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
                out = this.extend(out, subtree.leaves());
            }
            return out;
        }
    }

    private int[] append(int[] current, int value){
        int[] newArr = new int[current.length + 1];
        System.arraycopy(current, 0, newArr, 0, current.length);
        newArr[current.length] = value;
        return newArr;
    }

    private int[] extend(int[] arr1, int[] arr2){
        int[] newArr = new int[arr1.length + arr2.length];
        for (int val: arr2){
            arr1 = this.append(arr1, val);
        }
        System.arraycopy(arr1, 0, newArr, 0, arr1.length);
        return newArr;
    }

}

class Eh{
    public static void main(String[] args) {
        Tree t1 = new Tree(1, new Tree[]{new Tree(2, new Tree[]{}), new Tree(3, new Tree[]{})});
        Tree t2 = new Tree(1, new Tree[]{new Tree(2, new Tree[]{}), new Tree(3, new Tree[]{new Tree(4, new Tree[]{})})});
        System.out.println(Arrays.toString(t1.leaves()));
    }
}

