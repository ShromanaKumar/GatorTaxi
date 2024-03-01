public class RBNode {
    public Node data;
    public int rideNumber;
  
    public RBNode parent;
    public RBNode left;
    public RBNode right;
  
    public boolean color; // Red is false, and Black is true
  
    public RBNode(Node data, int rideNumber) {
        this.data = data;
        this.rideNumber = rideNumber;

        left = null;
        right = null;      
    }
  }