import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class ElevatorSystem {

    private int elevatorsNumber;
    private int floorsNumber;
    private Elevator[] elevators;

    ElevatorSystem(int elevatorsNr, int floorsNr, boolean flag){
        this.elevatorsNumber = elevatorsNr;
        this.floorsNumber = floorsNr;
        this.elevators = new Elevator[elevatorsNr];
        initializeElevators(flag);
    }

    // initialize elevators status (flag true if random numbers)
    private void initializeElevators(boolean flag){
        Random generator = new Random();
        int floor, dir;
        for (int i=0; i< this.elevatorsNumber; i++) {
            if (flag) {
                floor = generator.nextInt(this.floorsNumber);
                dir = generator.nextInt(this.floorsNumber);
            }else{
                floor = 0;
                dir = 0;
            }
            this.elevators[i] = new Elevator(i+1, floor, dir);
        }
    }

    // elevator pickup
    void pickup(int floor, char direction, int destFloor) {
        System.out.print("Pickup [" + floor + "," + direction + ',' + destFloor+"]:");
        int[] distances = new int[elevatorsNumber];
        int i = 0;

        // check distance to floor
        for (Elevator elevator: this.elevators){
            distances[i] = floor - elevator.getCurrentFloor();
            i++;
        }

        // check if direction to passenger floor is compatible with elevators
        boolean[] compatibleDirections = checkDirections(distances, direction);
        while (compatibleDirections.length == 0){
            compatibleDirections = checkDirections(distances, direction);
        }

        int selectedElevator = selectElevator(distances, compatibleDirections)+1;
        System.out.println(" assigned elevator "+selectedElevator);

        update(selectedElevator-1, floor, direction, destFloor);
        step();
        status();
    }

    private boolean[] checkDirections(int[] distances, int direction){
        boolean[] directions = new boolean[this.elevatorsNumber];
        char dir, elDir;
        int i = 0;
        for (Elevator elevator: this.elevators){
            if (distances[i] < 0){
                dir = '-';
            }else if (distances[i] > 0){
                dir = '+';
            }else{
                dir = '0';
            }
            elDir = elevator.getDirection();
            directions[i] = ((dir == elDir | elDir == '0' | (dir == '0' && direction==elDir)) && !elevator.getBusyFlag());
            i++;
        }
        return directions;
    }

    private int selectElevator(int[] distances, boolean[] directions){
        List<List<Integer>> compatible = new ArrayList<List<Integer>>();
        for (int i = 0; i < this.elevatorsNumber; i++){
            if (directions[i]){
                List <Integer> temp = new ArrayList<Integer>(2);
                temp.add(distances[i]);
                temp.add(i);
                compatible.add(temp);
            }
        }
        List<Integer> min = compatible.get(0);
        for(List<Integer> el: compatible){
            if (Math.abs(el.get(0)) < Math.abs(min.get(0))){
                min = el;
            }
        }
        return(min.get(1));
    }

    //update state of one selected elevator
    private void update(int id, int passengerFloor, char direction, int passengerDestination){
        Elevator elevator = this.elevators[id];
        int elevatorFloor = elevator.getCurrentFloor();
        int elevatorDestination = elevator.getDestination();
        int elevatorDirection = elevator.getDirection();
        if (direction == '-' && elevatorDirection == '-' && passengerFloor >= elevatorDestination ) {
            if (passengerDestination < elevatorDestination) {
                elevator.updateDestination(passengerDestination, -1);
            }
        }else if (direction == '-' && elevatorDirection == '+' ){
            if (passengerDestination < elevatorDestination) {
                if (passengerFloor >= elevatorDestination) {
                    elevator.updateDestination(passengerFloor, passengerDestination);
                }else{
                    elevator.updateDestination(elevatorDestination, passengerDestination);
                }
            }
        }else if (direction == '+' && elevatorDirection == '-' && passengerFloor <= elevatorDestination ){
            if (passengerDestination > elevatorDestination){
                elevator.updateDestination(passengerDestination, -1);
            }
        }else if (direction == '+' && elevatorDirection == '+' && passengerFloor <= elevatorDestination ){
            if (passengerDestination > elevatorDestination){
                elevator.updateDestination(elevatorDestination, passengerDestination);
            }
        }else if (elevatorDirection == '0' && passengerFloor != elevatorFloor){
            elevator.updateDestination(passengerFloor, passengerDestination);
        }else if (elevatorDirection == '0' ){
            elevator.updateDestination(passengerDestination,-1);
        }
    }

    // one step of simulation
    void step(){
        System.out.println("STEP");
        for(Elevator elevator : this.elevators){
            elevator.step();
        }
    }


    // print elevators status
    void status() {
        System.out.print("Elevator system status:");
        System.out.print("[");
        for( Elevator elevator : this.elevators){
            int[] status = elevator.getStatus();
            System.out.print("["+status[0]+","+status[1]+","+status[2]+"]");
        }
        System.out.println("]");
    }

    // check if any elevator is still running
    boolean checkStatus() {
        boolean flag = false;
        for (Elevator elevator : this.elevators) {
            if (elevator.getDirection() != '0') {
                flag = true;
                break;
            }
        }
        return flag;
    }
}
