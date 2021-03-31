class Elevator {

    private int id;
    private int currentFloor;
    private int destination;
    private boolean busyFlag;
    private int secDestination;

    Elevator(int id, int floor, int dir){
        this.id = id;
        this.currentFloor = floor;
        this.destination = dir;
        this.busyFlag = false;
    }

    int[] getStatus(){
        int[] status = new int[3];
        status[0] = id;
        status[1] = currentFloor;
        status[2] = destination;
        return status;
    }

    // Check if elevator is going up, down or is stopped
    char getDirection(){
        char direction;
        int floorDifference = this.destination - this.currentFloor;
        if (floorDifference < 0){
            direction = '-';
        }else if(floorDifference > 0){
            direction = '+';
        }
        else{
            direction = '0';
        }
        return direction;
    }

    int getCurrentFloor(){
        return this.currentFloor;
    }


    boolean getBusyFlag(){
        return this.busyFlag;
    }

    int getDestination(){
        return this.destination;
    }

    // Update destination floor if someone call the elevator
    void updateDestination(int destinationFloor, int secDestinationFloor){
        if (destinationFloor != destination) {
            destination = destinationFloor;
        }
        if (secDestinationFloor >= 0){
            busyFlag = true;
            this.secDestination = secDestinationFloor;
        }
    }

    void step(){
        char direction = getDirection();
        if (direction == '-') {
            currentFloor -= 1;
        }else if (direction == '+'){
            currentFloor += 1;
        }
        if (busyFlag) {
            if (currentFloor == destination) {
                updateDestination(this.secDestination,0);
                busyFlag = false;
            }
        }
    }

}
