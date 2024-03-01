public class MinHeap {

    // Creating internal variables for the min heap
    public Node[] Heap;
    private int currsize;
    private int maxsize = 200;
    private static final int ROOT = 1;

    //Output file
    WriteOutput out = new WriteOutput();
    String filename = "output_file.txt";
 
    // Defining the constructor
    public MinHeap()
    {
        this.currsize = 0;
 
        Heap = new Node[this.maxsize + 1];
        Heap[0] = new Node(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 0);
    }

    // Function 1 - Returns the position of the parent of the node at pos
    private int parent(int pos) { 
        return pos / 2; }
 
    // Function 2 - Returns the position of the left child of the node at pos
    private int leftChild(int pos) { 
        return (2 * pos); }
 
    // Function 3 - Returns the position of the right child of the node at pos
    private int rightChild(int pos) {
        return (2 * pos) + 1; }

    // Function 4 - Returns true if the node at pos is a leaf
    private boolean isLeaf(int pos) {
        if (pos > (currsize / 2))
            return true;
        return false;
    }

    // Function 5 - Returns true if the node at pos exists
    private boolean exists(int pos) {
        if(pos <= currsize && Heap[pos] != null)
            return true;
        else
            return false;
    }   

    // Function 6 - Swaps the position of 2 nodes of the heap
    private void swap(int pos1, int pos2) {

        //System.out.println("Swapping" + pos1 + " " + pos2);
        Node tmp;
        tmp = Heap[pos1];
        Heap[pos1] = Heap[pos2];
        Heap[pos2] = tmp;
        Heap[pos1].position = pos1;
        Heap[pos2].position = pos2;
    }

    // Function 7 - To heapify the node at pos
    private void minHeapify(int pos)
    {     
        if(!isLeaf(pos)) {
            int temp = pos;
            int leftChild = leftChild(pos);
            int rightChild = rightChild(pos);
       
            // Find the minimum of the two children
            if(exists(rightChild(pos)))
                if((Heap[leftChild].rideCost < Heap[rightChild].rideCost)
                    || (Heap[leftChild].rideCost == Heap[rightChild].rideCost && Heap[leftChild].tripDuration < Heap[rightChild].tripDuration))
                        temp = leftChild;
                else
                    temp = rightChild;
            else
                temp = leftChild;
        
            // If pos is larger, swap pos with the minimum of children
            if((Heap[pos].rideCost > Heap[temp].rideCost)
                || (Heap[pos].rideCost == Heap[temp].rideCost && Heap[pos].tripDuration > Heap[temp].tripDuration)) {
                swap(pos,temp);
                minHeapify(temp);
            }
        }      
    }
 
    // Function 8 - To insert a node into the heap
    public Node Insert(int rideNumber, int rideCost, int tripDuration, int pos) 
    {   
        Node temp = null;
        // To check if maxsize has been reached
        if (currsize >= maxsize)
            return temp;
        
        // Insert new node at the end and heapify with respect to parent
        if(pos <= 0) {
            currsize++;
            Heap[currsize] = new Node(rideNumber, rideCost, tripDuration, currsize);
            temp = Heap[currsize];

            int current = currsize;

            while ((Heap[current].rideCost < Heap[parent(current)].rideCost)
                || (Heap[current].rideCost == Heap[parent(current)].rideCost && Heap[current].tripDuration < Heap[parent(current)].tripDuration)) {
                swap(current, parent(current));
                current = parent(current);
              
            }
        }
        return temp;
    }

    // Function 9 - Remove Min from the heap
    public int GetNextRide()
    {
        int temp;
        if (currsize == 0) {
            
            //When heap is empty
            System.out.println("No active ride requests");
            String output = "No active ride requests\n";
            out.writeOutputToFile(output, filename);
            return -1;
        }
        else{
            System.out.println("(" + Heap[ROOT].rideNumber + "," + Heap[ROOT].rideCost +  "," + Heap[ROOT].tripDuration + ")");
            String output = "(" + Heap[ROOT].rideNumber + "," + Heap[ROOT].rideCost +  "," + Heap[ROOT].tripDuration + ")\n";
            out.writeOutputToFile(output, filename);

            //Replacing root node with last node
            temp = Heap[ROOT].rideNumber;
            Heap[ROOT] = Heap[currsize--];
            Heap[ROOT].position = ROOT;
            Heap[currsize + 1] = null;
            minHeapify(ROOT);
        }
        return temp;
    }

    // Function 10 - Remove Arbitrary from the heap
    public void CancelRide(int rideNumber, int pos) {
        //System.out.print("Ride Number " + Heap[pos].rideNumber + " has been cancelled");
        //Replace with last node
        Heap[pos] = Heap[currsize--];
        Heap[pos].position = pos; 
        Heap[currsize + 1] = null;
        minHeapify(pos);
    }
}
