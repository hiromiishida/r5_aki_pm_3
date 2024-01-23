public class Main {
    public static void main(String[] args) {
        Node node1 = insertB(null, 6);
        Node node2 = insertB(node1, 3);
        Node node3 = insertB(node1, 9);
        Node node4 = insertB(node2, 1);
        Node node5 = insertB(node2, 5);

        Node r = insertB(insertB(node1, 4), 8);

        System.out.println(r);
    }

    private Node search(Node t, int k){
        if(t == null){
            return null;
        }else if(t.key == k){
            return t;
        }else if(t.key > k){
            return search(t.left, k);
        }else{
            return search(t.right, k);
        }
    }

    private Node insert(Node t, int k){
        if(t == null){
            t = new Node(k);
        }else if(t.key > k){
            t.left = insert(t.left, k);
        }else if(t.key < k){
            t.left = insert(t.right, k);
        }
        return t;
    }

    private static Node rotateR(Node t){
        Node a = t.left;
        Node b = a.right;
        a.right = t;
        t.left = b;
        return a;
    }
    private static Node rotateL(Node t){
        Node a = t.right;
        Node b = a.left;
        a.left = t;
        t.right = b;
        return a;
    }

    private static Node balance(Node t){
        int h1 = height(t.left) - height(t.right);
        if(h1 == 2){
            int h2 = height(t.left.right) - height(t.left.left);
            if(h2 > 0){
                t.left = rotateL(t.left);
            }
            t = rotateR(t);
        }else if(h1 == -2){
            int h3 = height(t.right.left) - height(t.right.right);
            if(h3 > 0){
                t.right = rotateR(t.right);
            }
            t = rotateL(t);
        }
        return t;
    }

    private static Node insertB(Node t, int k){
        if(t == null){
            t = new Node(k);
        }else if(t.key > k){
            t.left = insertB(t.left, k);
        }else if(t.key < k){
            t.right = insertB(t.right, k);
        }
        t = balance(t);
        return t;
    }

    private static int height(Node node){
        if (node == null) {
            return 0;
        } else {
            int leftHeight = height(node.left);
            int rightHeight = height(node.right);

            return Math.max(leftHeight, rightHeight) + 1;
        }
    }
}