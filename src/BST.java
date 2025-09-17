/**
 * A minimal implementation of a binary search tree. See the python version for
 * additional documentation.
 * You can also see <a href="https://www.teach.cs.toronto.edu/~csc148h/notes/binary-search-trees/bst_implementation.html">
 *     CSC148 Course Notes Section 8.5 BST Implementation and Search</a>
 * if you want a refresher on BSTs, but it is not required to complete this assignment.
 */
public class BST {
    // we use Integer here so that we can set the root to null. This is the same idea as
    // how the Python code uses None in the BST implementation.
    private Integer root;

    private BST left;
    private BST right;

    public BST(int root) {
        this.root = root;
        this.left = new BST();
        this.right = new BST();
    }

    /**
     * Alternate constructor, so we don't have to explicitly pass in null.
     */
    public BST() {
        root = null;
        this.left = null;
        this.right = null;
    }


    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(int item) {
        // provided as an example
        if (this.isEmpty()) {
            return false;
        } else if (item == this.root) {
            return true;
        } else if (item < this.root) {
            return this.left.contains(item);
        }
        return this.right.contains(item);

    }


    public void insert(int item) {
        if (this.isEmpty()) {
            this.root = item;
            this.left = new BST();
            this.right = new BST();
        }
        else if (item >= this.root) {
            this.right.insert(item);
        }
        else  {
            this.left.insert(item);
        }
    }


    public void delete(int item) {
        if (!this.isEmpty()) {
            if (this.root.equals(item)) {
                this.deleteRoot();
            }
            else if (item < this.root) {
                this.left.delete(item);
            }
            else {
                this.right.delete(item);
            }
        }
    }
    private void deleteRoot() {
        if (this.left.isEmpty() && this.right.isEmpty()) {
            this.root = null;
            this.left = null;
            this.right = null;
        }
        else if  (this.left.isEmpty()) {
            this.root = this.right.root;
            this.left = this.right.left;
            this.right = this.right.right;
        }
        else if (this.right.isEmpty()) {
            this.root = this.left.root;
            this.right = this.left.right;
            this.left =  this.left.left;
        }
        else {
            this.root = this.left.extractMax();


        }
    }


    private int extractMax() {
        int num;
        if (this.right.isEmpty()) {
            num = this.root;
            this.root = this.left.root;
            this.right = this.left.right;
            this.left = this.left.left;
        }
        else{
            num = this.right.extractMax();
        }
        return num;
    }

    public int height() {
        if (this.isEmpty()) {
            return 0;
        }
        return 1 + Math.max(this.left.height(), this.right.height());
    }

    public int count(int item) {
        if (this.isEmpty()) {
            return 0;
        }
        int total;
        if (this.root.equals(item)) {
            total = 1 + this.left.count(item) + this.right.count(item);
        }
        else if  (this.root < item) {
            total = this.right.count(item);
        }
        else {
            total = this.left.count(item);
        }
        return total;
    }

    public int getSize() {
        if (this.isEmpty()) {
            return 0;
        }
        return 1 + this.left.getSize() + this.right.getSize();
    }

    public static void main(String[] args) {
        // You can also add code here to do some basic testing if you want,
        // but make sure it doesn't contain syntax errors
        // or else we won't be able to run your code on MarkUs since the file won't
        // compile. Always make sure to run the self tests on MarkUs after you update your code.
        BST bst = new BST();
        int a = 1;
        bst.insert(a);
    }

}
