import java.util.Random;

public class ElevatorSystemSimulation {

    private static void simulation(ElevatorSystem system, int[][] pickups){
        system.status();
        for(int[] pickup : pickups){
            char dir = getDirection(pickup[0],pickup[1]);
            system.pickup(pickup[0],dir,pickup[1]);
        }
        while (system.checkStatus()) {
            system.step();
            system.status();
        }

    }

    private static char getDirection(int floor1, int floor2){
        char direction;
        int floorDifference = floor2 - floor1;
        if (floorDifference < 0) {
            direction = '-';
        } else{
            direction = '+';
        }
        return direction;
    }

    private static int[][] generatePickups(int number, int floorsNumber){
        Random generator = new Random();
        int[][] pickups = new int[number][2];
        for(int[] pickup : pickups){
            pickup[0] = generator.nextInt(floorsNumber);
            pickup[1] = generator.nextInt(floorsNumber);
            while (pickup[0] == pickup[1]){
                pickup[1] = generator.nextInt(floorsNumber);
            }
        }
        return pickups;
    }


    public static void main(String[] args) {
        int elevatorsNumber,floorsNumber,pickupsNumber;
        boolean randomInitialState;

        if (args.length > 0){
            elevatorsNumber = Integer.parseInt(args[0]);
        }else {
            elevatorsNumber = 3;
        }
        if (args.length > 1){
            floorsNumber = Integer.parseInt(args[1]);
        }else {
            floorsNumber = 4;
        }
        if (args.length > 2){
            pickupsNumber = Integer.parseInt(args[2]);
        }else {
            pickupsNumber = 5;
        }
        if (args.length > 3){
            randomInitialState = Boolean.valueOf(args[3]);
        }else {
            randomInitialState = false;
        }



        System.out.println("Elevator System Simulation");


        ElevatorSystem system = new ElevatorSystem(elevatorsNumber,floorsNumber,randomInitialState);
        int[][] pickups = generatePickups(pickupsNumber, floorsNumber);
        simulation(system,pickups);

        System.out.println("End");
    }
}
