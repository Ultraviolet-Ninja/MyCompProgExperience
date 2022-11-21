package competition.kattis.the_class.notes.week8;

import javax.swing.text.Segment;

public class SegmentTree {

    private SegmentTreeNode root;

    private static class SegmentTreeNode {
        public SegmentTreeNode left;
        public SegmentTreeNode right;
        public int from;
        public int to;
        public int value;

        public SegmentTreeNode(int f, int t) {
            from = f;
            to = t;
            value = 0;
            left = null;
            right = null;
        }
    }

    public SegmentTree(int[] values) {
        root = build(values, 0, values.length-1);
    }

    private SegmentTreeNode build(int[] values, int l, int r) {
        if (l > r) return null;
        SegmentTreeNode node = new SegmentTreeNode(l, r);
        if (l == r) {
            node.value = values[l];
        } else {
            int m = (l + r) / 2;
            node.left = build(values, l, m);
            node.right = build(values, m + 1, r);
            if(node.left != null) {
                node.value += node.left.value;
            }
            if(node.right != null) {
                node.value += node.right.value;
            }
        }
        return node;
    }

    public int query(int l, int r) {
        return queryRecursive(root, l, r);
    }
    public int queryRecursive(SegmentTreeNode node, int l, int r) {
        if(node == null) {
            return 0;
        }
        // Range matches the node
        if(l <= node.from && node.to <= r) {
            return node.value;
        }
        // Range is outside the node value
        if(node.to < l || node.from > r) {
            return 0;
        }
        return queryRecursive(node.left, l, r) + queryRecursive(node.right, l, r);
    }

    public void update(int i, int value) {
        updateRecursive(root, i, value);
    }
    public int updateRecursive(SegmentTreeNode node, int i, int value) {
        if(node == null) {
            return 0;
        }
        // Range is outside the node value
        if(node.to < i || node.from > i) {
            return node.value;
        }
        // Range matches the node
        if(node.from == node.to) {
            node.value = value;
        } else {
            node.value = updateRecursive(node.left, i, value) + updateRecursive(node.right, i, value);
        }
        return node.value;
    }

    public void print() {
        printInorder(root, "");
    }

    public void printInorder(SegmentTreeNode node, String padding) {
        if(node.left != null) {
            printInorder(node.left, padding + "        ");
        }
        System.out.println(padding + "[" + node.from + "," + node.to + "," + node.value + "]");
        if(node.right != null) {
            printInorder(node.right, padding + "        ");
        }
    }

    public static void main(String[] args) {
        int[] values = {1,6,3,3,5,2,11,0};

        long start = System.nanoTime();
        SegmentTree thisTree = new SegmentTree(values);
        thisTree.print();
        System.out.println(thisTree.query(2, 5));

        thisTree.update(5, 5);
        thisTree.print();
        System.out.println(thisTree.query(2, 5));
        long end = System.nanoTime();

        System.out.println("TIME: " + (end - start));
    }
}
