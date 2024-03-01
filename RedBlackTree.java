public class RedBlackTree
{
	public RBNode root;//root node

  WriteOutput out = new WriteOutput();
  String filename = "output_file.txt";

	public RedBlackTree()
	{
		root = null;
	}

    // Function 1 - Performs RR rotation
    private RBNode RR(RBNode node)
	  {
		RBNode temp = node.right;
        node.right = temp.left;
        temp.left = node;

		// resetting parent
        node.parent = temp; 
		if(node.right!=null)
            node.right.parent = node;
		return(temp);
	}

  // Function 2 - Performs LL rotation
	private RBNode LL(RBNode node)
	{
		RBNode temp = node.left;
		node.left = temp.right;
		temp.right = node;

    // resetting parent
		node.parent = temp;
		if(node.left!=null)
            node.left.parent = node;
		return(temp);
	}

    // Function 3 - To insert a node into the RBTree
    public void Insert(Node data, int rideNumber) {
        root = insertData(root, data, rideNumber);
    }
    
    // Function 3 - To insert a node into the RBTree
    private RBNode insertData(RBNode pos, Node data, int rideNumber) 
    {
      // Return a new node if the tree is empty
      if (pos == null)
        pos = new RBNode(data, rideNumber);

      // Traverse to the right place and insert the node
      if (rideNumber < pos.rideNumber)
        pos.left = insertData(pos.left, data, rideNumber);
      else if (rideNumber > pos.rideNumber)
        pos.right = insertData(pos.right, data, rideNumber);

      return pos;
    }

    // Function 4 - Remove Arbitrary from the heap
    public void CancelRide(int rideNumber) {
      root = CancelRideData(root, rideNumber);
    }
  
    // Function 4 - Remove Arbitrary from the heap
    private RBNode CancelRideData(RBNode root, int rideNumber) 
    {  
      // Find the node to be deleted
      if (rideNumber < root.rideNumber)
        root.left = CancelRideData(root.left, rideNumber);
      else if (rideNumber > root.rideNumber)
        root.right = CancelRideData(root.right, rideNumber);
      else {
        // If the node is with only one child or no child
        if (root.left == null)
          return root.right;
        else if (root.right == null)
          return root.left;
  
        // If the node has two children
        // Find the minimum from right subtree to replace the node
        root.data = minRight(root.right);
        root.rideNumber = root.data.rideNumber;
  
        // Delete the minimum from right subtree
        root.right = CancelRideData(root.right, root.rideNumber);
      }

      return root;
    }

    // Finds the minimum from right subtree
    private Node minRight(RBNode root) 
    {  
      while (root.left != null)
        root = root.left;

      return root.data;
    }

    // Function 5 - To get position of node from RBTree
    public int getPos(int rideNumber) {
      int pos = getPosData(rideNumber, root);
      return pos;
    }

    // Function 5 - To get position of node from RBTree
    private int getPosData(int rideNumber, RBNode root) {
      int pos;
      if (root != null) {
        if(root.rideNumber == rideNumber)
          return root.data.position;
        else if (rideNumber < root.rideNumber)
          pos = getPosData(rideNumber, root.left);
        else if (rideNumber > root.rideNumber)
          pos = getPosData(rideNumber, root.right);
        else return -3;
      }
      else return 0;
      return pos;
    }

    // Function 6 - To print a node
    public void Print(int rideNumber) {
      Print(rideNumber, root);
    }

    // Function 6 - To print a node
    public void Print(int rideNumber, RBNode root)
    {
      if (root != null)
      {
        if(root.rideNumber == rideNumber){
          System.out.println("(" + root.rideNumber + "," + root.data.rideCost +  "," + root.data.tripDuration + ")");
          String output = "(" + root.rideNumber + "," + root.data.rideCost +  "," + root.data.tripDuration + ")\n";
          out.writeOutputToFile(output, filename);
        }
        else if (rideNumber < root.rideNumber && root.left != null)
          Print(rideNumber, root.left);
        else if (rideNumber > root.rideNumber && root.right != null)
          Print(rideNumber, root.right);
        else{
          System.out.println("(0,0,0)");
          String output = "(0,0,0)\n";
          out.writeOutputToFile(output, filename);
        }
      }
      else{
        System.out.println("(0,0,0)");
        String output = "(0,0,0)\n";
        out.writeOutputToFile(output, filename);
      }
  }

  // Function 7 - To print a range of nodes
  public void Print(int rideNumber1, int rideNumber2) {
    int counter = 0;
    counter = printRange(rideNumber1, rideNumber2, root, counter);
    if(counter == 0){
        System.out.print("(0,0,0)");
        String output = "(0,0,0)";
        out.writeOutputToFile(output, filename);
    }
  }  

  // Function 7 - To print a range of nodes
  private int printRange(int rideNumber1, int rideNumber2, RBNode root, int counter)
  {  
    if (root != null)
    {
      if(root.rideNumber > rideNumber1)
        counter = printRange(rideNumber1, rideNumber2, root.left, counter);

      if(root.rideNumber >= rideNumber1 && root.rideNumber <= rideNumber2){
        if(counter > 0){
          System.out.print(",");
          String output = ",";
          out.writeOutputToFile(output, filename);
        }
        System.out.print("(" + root.rideNumber + "," + root.data.rideCost +  "," + root.data.tripDuration + ")");
        String output = "(" + root.rideNumber + "," + root.data.rideCost +  "," + root.data.tripDuration + ")";
        out.writeOutputToFile(output, filename);
        counter++;
        //System.out.println("counter" + counter);
      }

      if(root.rideNumber < rideNumber2)
        counter = printRange(rideNumber1, rideNumber2, root.right, counter);
    
    }
    return counter;
  }

}