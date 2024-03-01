public class Ride
{
    MinHeap heap;
    RedBlackTree tree;

    //Output file
    WriteOutput out = new WriteOutput();
    String filename = "output_file.txt";

    // Defining the constructor
    public Ride()
    {
        heap = new MinHeap();
        tree = new RedBlackTree();
    }

    // Function 1 - To get position of node from RBTree
    private int getPos(int rideNumber)
    {
        int pos = tree.getPos(rideNumber);
        return pos;
    }

    // Function 2 - To insert into the Min Heap and RBTree
    public void Insert(int rideNumber, int rideCost, int tripDuration)
    {
        //Get pos of node in min_heap
        int pos = getPos(rideNumber);

        if(pos <= 0) {

            //insert node in min_heap
            Node data = heap.Insert(rideNumber, rideCost, tripDuration, pos);

            //insert node in rbtree
            if(data != null)
                tree.Insert(data, rideNumber);
            else
                System.out.print("Max Active Rides reached");
        }
        else {
            String output = "Duplicate RideNumber";
            out.writeOutputToFile(output, filename);
            throw new IllegalStateException("Duplicate RideNumber");
        }
    }

    // Function 3 - To update trip details in the Min Heap and RBTree
    public void UpdateTrip(int rideNumber, int newTripDuration)
    {
        //Get pos of node in min_heap
        int pos = getPos(rideNumber);
        
        //Update the duration and cost as per requirement
        if(pos > 0){
            //System.out.println("newTripDuration " + newTripDuration + "rideNumber " + rideNumber + "Heap[pos].tripDuration " + heap.Heap[pos].tripDuration);
            if(newTripDuration <= heap.Heap[pos].tripDuration) {
                int rideCost = heap.Heap[pos].rideCost;
                //heap.Heap[pos].tripDuration = newTripDuration;
                CancelRide(rideNumber);
                Insert(rideNumber, rideCost, newTripDuration);
                //System.out.print("Trip duration updated for Ride Number " + rideNumber);
            }
            else if(heap.Heap[pos].tripDuration < newTripDuration && newTripDuration <= (heap.Heap[pos].tripDuration*2)) {
                int rideCost = heap.Heap[pos].rideCost;
                CancelRide(rideNumber);
                Insert(rideNumber, rideCost + 10, newTripDuration);
                //System.out.println("Ride Number " + rideNumber + " has been updated");
            }
            else if(newTripDuration > (heap.Heap[pos].tripDuration*2)) {
                CancelRide(rideNumber);
                //System.out.println("Ride Number " + rideNumber + " has been declined and removed from heap");
            }
        }
    }

    // Function 4 - To delete a ride from the Min Heap and RBTree
    public void CancelRide(int rideNumber)
    {
        //Get pos of node in min_heap
        int pos = getPos(rideNumber);

        //Delete from min_heap and rbtree
        if(pos > 0) {
            heap.CancelRide(rideNumber, pos);
            tree.CancelRide(rideNumber);
        }
    }

    // Function 5 - To delete the lowest cost ride from the Min Heap and RBTree
    public void GetNextRide()
    {
        //Deletes root node from min_heap and rbtree
        int rideNumber = heap.GetNextRide();
        if(rideNumber != -1)
            tree.CancelRide(rideNumber);
    }

    // Function 6 - To print a ride from the RBTree
    public void Print(int rideNumber)
    {
        //Prints ride details
        tree.Print(rideNumber);
    }

    // Function 5 - To print a range of rides from the RBTree
    public void Print(int rideNumber1, int rideNumber2)
    {
        //Prints ride details
        tree.Print(rideNumber1, rideNumber2);
        System.out.println("");
        String output = "\n";
        out.writeOutputToFile(output, filename);
    }

    public void printHeap()
    {
        //heap.printHeap();
        //tree.traversal();
        System.out.println();
    }
    
    public static void main(String[] args) {
        Ride taxi = new Ride();
        //taxi.Insert(25,98,46);
        //taxi.GetNextRide();
        //taxi.GetNextRide();
        //taxi.Insert(42,17,89);
        //taxi.Insert(9,76,31);
        //taxi.Insert(53,97,22);
        //taxi.GetNextRide();
        //taxi.Insert(68,40,51);
        //taxi.GetNextRide();
        //taxi.Print(1,100);
        //taxi.UpdateTrip(53,15);
        //taxi.Insert(96,28,82);
        //taxi.Insert(73,28,56);
        //taxi.UpdateTrip(9,88);
        //taxi.GetNextRide();
        //taxi.Insert(20,49,59);
        //taxi.Insert(62,7,10);   
        //taxi.CancelRide(20);
        //taxi.Insert(25,49,46);
        //taxi.UpdateTrip(62,15);
        //taxi.GetNextRide();
        //taxi.Print(1,100);
        //taxi.Insert(53,28,19);
        //taxi.Print(1,100);
//
        



        taxi.Insert(5,50,120);
        taxi.Insert(4,30,60);
        taxi.Insert(7,40,90);
        taxi.Insert(3,20,40);
        taxi.Insert(1,10,20);
        taxi.Print(2);
        taxi.Insert(6,35,70);
        taxi.Insert(8,45,100);
        taxi.Print(3);
        taxi.Print(1,6);
        taxi.UpdateTrip(6,75);
        taxi.Insert(10,60,150);
        taxi.GetNextRide();
        taxi.CancelRide(5);
        taxi.UpdateTrip(3,22);
        taxi.Insert(9,55,110);
        taxi.GetNextRide();
        taxi.UpdateTrip(6,95);
        taxi.Print(6);
        taxi.Print(5,9);
        taxi.GetNextRide();
        taxi.CancelRide(7);
        taxi.Print(7);
        taxi.Insert(11,70,170);
        taxi.GetNextRide();
        taxi.Insert(12,80,200);
        taxi.Print(12);
        taxi.UpdateTrip(11,210);
        taxi.GetNextRide();
        taxi.CancelRide(14);
        taxi.UpdateTrip(12,190);
        taxi.Insert(13,70,220);
        taxi.GetNextRide();
        taxi.Insert(14,100,40);
        taxi.UpdateTrip(14,100);
        taxi.CancelRide(12);
        taxi.Print(11,14);
        taxi.GetNextRide();
        taxi.Insert(15,20,35);
        taxi.Print(14);
        taxi.Print(10,16);
        taxi.GetNextRide();
        taxi.UpdateTrip(13,30);
        taxi.Print(13);
        taxi.GetNextRide();
        taxi.Print(12);
        taxi.CancelRide(19);
        taxi.Insert(16,60,45);
        taxi.Insert(17,70,25);
        taxi.UpdateTrip(16,60);
        taxi.GetNextRide();
        taxi.Print(11);
        taxi.Print(16,18);
        taxi.Insert(18,65,130);
        taxi.Insert(12,40,30);
        taxi.Insert(8,60,97);
        taxi.UpdateTrip(16,82);
        taxi.Insert(20,16,75);
        taxi.UpdateTrip(18,300);
        taxi.Print(23);
        taxi.Print(12,21);
        taxi.CancelRide(12);
        taxi.GetNextRide();
        taxi.CancelRide(25);
        taxi.Print(20,26);
        taxi.GetNextRide();
        taxi.UpdateTrip(16,124);
        taxi.Insert(7,125,54);
        taxi.GetNextRide();
        taxi.Print(16);
        taxi.Insert(22,80,85);
        taxi.Insert(15,90,85);
        taxi.UpdateTrip(22,195);
        taxi.GetNextRide();
        taxi.Insert(23,49,46);
        taxi.Insert(1,56,85);
        taxi.UpdateTrip(16,300);
        taxi.GetNextRide();
        taxi.Print(1,30);
        taxi.CancelRide(1);
        taxi.GetNextRide();
        taxi.GetNextRide();
        taxi.Insert(24,21,46);
        taxi.Insert(17,12,37);
        taxi.GetNextRide();
        taxi.Print(16);
        taxi.Insert(24,80,85);
        taxi.Insert(15,90,85);
        taxi.CancelRide(28);
        taxi.UpdateTrip(23,450);
        taxi.GetNextRide();
        taxi.Print(24);
        taxi.Print(22,26);
        taxi.CancelRide(29);
        taxi.Print(28);

        taxi.printHeap();
    }
}