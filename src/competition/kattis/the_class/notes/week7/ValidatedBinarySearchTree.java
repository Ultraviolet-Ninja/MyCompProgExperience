package competition.kattis.the_class.notes.week7;

public class ValidatedBinarySearchTree {

    private TreeNode root;

    private static class TreeNode {
        public int value;
        public TreeNode leftChild;
        public TreeNode rightChild;

        public TreeNode(int i) {
            value = i;
            leftChild = null;
            rightChild = null;
        }

        /**
         * Inserts a value into the binary search tree
         * @param i the value to insert
         */
        public void insert(int i) {
            if(i < value) {
                if(leftChild == null) {
                    leftChild = new TreeNode(i);
                } else {
                    leftChild.insert(i);
                }
            } else {
                if(rightChild == null) {
                    rightChild = new TreeNode(i);
                } else {
                    rightChild.insert(i);
                }
            }
        }

        /**
         * Search the tree for a value
         * @param i the value to find
         * @return - the node containing the value or null if not found
         */
        public TreeNode searchRecursive(int i) {
            if(i == value) {
                return this;
            }
            if(i < value && leftChild != null) {
                return leftChild.searchRecursive(i);
            } else if(i > value && rightChild != null) {
                return rightChild.searchRecursive(i);
            } else {
                return null;
            }
        }

        private boolean checkValid(long lowerBound, long upperBound) {

            // The key with check valid - keep track of running upper and lower bound
            //   at each level of the tree
            // If the value of the current node is below the lower bound or above the upper bound
            //    then the tree is not valid
            if (value <= lowerBound || value >= upperBound) {
                return false;
            }
            // Check the left and right subtrees adjusting the bounds appropriately
            boolean leftChildValid = leftChild == null || leftChild.checkValid(lowerBound, value);
            boolean rightChildValid = rightChild == null || rightChild.checkValid(value, upperBound);
            return leftChildValid && rightChildValid;
        }

        /**
         * Prints the tree using the inorder traversal
         * @param spacing the spacing between tree levels
         */
        public void printRecursive(String spacing) {
            if(rightChild != null) {
                rightChild.printRecursive(spacing + "   ");
            }
            System.out.println(spacing + value);
            if(leftChild != null) {
                leftChild.printRecursive(spacing + "   ");
            }
        }
    }

    public ValidatedBinarySearchTree() {
        root = null;
    }

    public TreeNode findNode(int i) {
        if(root == null) {
            return null;
        }
        return root.searchRecursive(i);
    }

    public void insert(int i) {
        if(root == null) {
            root = new TreeNode(i);
        } else {
            root.insert(i);
        }
    }

    public boolean isValid() {
        if(root == null) {
            return true;
        } else {
            return root.checkValid(-1L + Integer.MIN_VALUE, 1L + Integer.MAX_VALUE);
        }
    }

    public void print() {
        if(root != null) {
            root.printRecursive("");
        }
    }

    public static void main(String[] args) {

        // Construct a valid tree
        ValidatedBinarySearchTree myValidTree = new ValidatedBinarySearchTree();
        int[] values1 = {10, 8, 12, 6, 9, 14, 13, 16};
        for(int value : values1) {
            myValidTree.insert(value);
        }
        myValidTree.print();
        System.out.println(myValidTree.isValid());

        // Construct an invalid tree
        ValidatedBinarySearchTree myInvalidTree = new ValidatedBinarySearchTree();
        int[] values2 = {10, 8, 12, 6, 14, 13, 16};
        for(int value : values2) {
            myInvalidTree.insert(value);
        }
        // Insert 15 in a bad place
        TreeNode eightNode = myInvalidTree.findNode(8);
        eightNode.rightChild = new TreeNode(15);
        myInvalidTree.print();
        System.out.println(myInvalidTree.isValid());
    }
}
