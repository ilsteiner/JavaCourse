package cscie55.hw1.elevator;

/**
 * @author Isaac Lebwohl-Steiner
 * @since 2015-02-09
 */
public class Elevator {
    private static final int numFloors = 7;
//    1=up, -1=dow, 0=not moving
    private int direction;
    private int currFloor;
//    Array of size numFloors containing arrays of size 2 with numPassengers and stop yes/no
    private int[][] floors;

    /**
     * Creates a new Elevator with default values
     */
    public Elevator() {
        direction = 0;
        currFloor = 1;
        /* Creates an empty array to hold the number of floors in the building
        *  and two pieces of information for each floor, the number of passengers
        *  destined for that floor and whether a stop was requested. The first
        *  element in the subarray is the number of passengers, an integer >= 0.
        *  The second element is the "stop here" flag, indicating whether the
        *  Elevator should stop (0 means no, 1 means yes).*/
        floors = new int[numFloors][2];
    }

    /**
     * Creates a new Elevator with no passengers
     * @param direction the direction of the Elevator, -1 means down, 1 means up, and 0 means it currently has no direction
     * @param currFloor the floor on which the Elevator will start
     */
    public Elevator(int direction, int currFloor) {
        this.direction = direction;
        this.currFloor = currFloor;
        floors = new int[numFloors][2];
    }

    /**
     * Creates a new Elevator with no default values.
     * @param direction the direction of the Elevator, -1 means down, 1 means up, and 0 means it currently has no direction
     * @param currFloor the floor on which the Elevator will start
     * @param floors an array of arrays each of which hold the number of passengers destined for a floor (first value) and a 0 or 1 flag to indicate whether the Elevator should stop at that floor (second value)
     */
    public Elevator(int direction, int currFloor, int[][] floors) {
        this.direction = direction;
        this.currFloor = currFloor;

        if(floors.length != numFloors){
            throw new IllegalArgumentException("The number of floors in the Elevator must match the number of floors in the building.");
        }
        else{
            this.floors = floors;
        }
    }

    /**
     * @return numFloors the number of floors in the building
     */
    public static int getNumFloors() {
        return numFloors;
    }

    /**
     *
     * @return direction the current direction of the elevator ()
     */
    public int getDirection() {
        return direction;
    }

    /**
     *
     * @return currFloor the floor the Elevator is currently on
     */
    public int getCurrFloor() {
        return currFloor;
    }

    /**
     *
     * @return floors the array containing floor and passenger information for this Elevator
     * @see Elevator#Elevator(int direction, int currFloor)
     */
    public int[][] getFloors() {
        return floors;
    }

    /**
     * Returns the number of passengers destined for a given floor
     * @param floor the floor for which you want the number of passengers
     * @return numPassengers the number of passengers destined for the specified floor
     */
    public int getNumPassengers(int floor){
        return floors[floor][0];
    }

    /**
     * Returns the total number of passengers, regardless of the floors for which they are destined
     * @return numPassengers the number of total passengers in the Elevator
     */
    public int getNumPassengers(){
        int numPassengers = 0;
        for(int i=1;i<=numFloors;i++){
            numPassengers += floors[i][0];
        }

        return numPassengers;
    }

    /**
     * Switches the direction of the Elevator. If the Elevator was going up, it will now be going down, and if it was going down, it will now be going up. If the Elevator did not have a direction, the direction will not be changed.
     * @return direction the new direction of the Elevator
     */
    private int toggleDirection(){
        if(direction == 1){
            direction = -1;
        }
        else if(direction == -1){
            direction = 1;
        }
        else{
            direction = 0;
        }

        return direction;
    }

    /**
     * Moves the Elevator, making appropriate changes to state:
     *      Change Elevator direction if necessary
     *      Change current floor
     *      Disembark all passengers on the new floor
     */
    public void move(){
        /*If the Elevator is at the top or bottom floor, switch directions*/
        if(currFloor == 1 || currFloor == numFloors){
            toggleDirection();
        }
        /*Otherwise, increase or decrease the floor as appropriate*/
        else{
            if(direction == 1){
                currFloor++;
            }
            else if(direction == -1){
                currFloor--;
            }
            else{
                throw new UnsupportedOperationException("Cannot move Elevator without a direction. Direction is currently " + direction + ".");
            }
        }

        /*Clear the passengers destined for this floor.*/
        floors[currFloor][0] = 0;

        /*Clear the "stop here" flag for this floor*/
        floors[currFloor][1] = 0;
    }

    /**
     * Adds 1 new passenger to the Elevator, destined for the specified floor
     * @param floor the floor for which the passenger is destined
     */
    public void boardPassenger(int floor){
        /*Add 1 to the number of passengers destined for the indicated floor*/
        floors[floor][0]++;
        /*Mark the floor as a stop, regardless of whether it already is one*/
        floors[floor][1] = 1;
    }

    public String toString(){

    }
}