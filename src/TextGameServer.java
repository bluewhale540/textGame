import java.util.ArrayList;
import java.util.Scanner;

public class TextGameServer {
    String[] directions = {"NORTH", "EAST", "SOUTH", "WEST"}; //possible directions
    ArrayList<String> possibleDirections = new ArrayList<>();
    ArrayList<String>[] roomContents = new ArrayList[45]; //contents of rooms
    int[][] gameArray = new int[][] { //map array
                    {-1,2,10,-1}, //Space1
                    {-1,3,11,1}, //Space2
                    {-1,4,12,2}, //Space3
                    {-1,5,13,3}, //Space4
                    {-1,6,14,4}, //Space5
                    {-1,7,15,5}, //Jupiter6
                    {-1,8,16,6}, //Space7
                    {-1,9,17,7}, //Space8
                    {-1,-1,18,8}, //Space9
                    {1,11,19,-1}, //Space10
                    {2,12,20,10}, //Space11
                    {3,13,21,11}, //Space12
                    {4,14,22,12}, //Space13
                    {5,15,23,13}, //Mars14
                    {6,16,24,14}, //Space15
                    {7,17,25,15}, //Space16
                    {8,18,26,16}, //Space17
                    {9,-1,27,17}, //Space18
                    {10,20,28,-1}, //Sol19
                    {11,21,29,19}, //Mercury20
                    {12,22,30,20}, //Space21
                    {13,23,31,21}, //Space22
                    {14,24,32,22}, //Space23
                    {15,25,33,23}, //Space24
                    {16,26,34,24}, //Saturn25
                    {17,27,35,25}, //Space26
                    {18,-1,36,26}, //Space27
                    {19,29,37,-1}, //Space28
                    {20,30,38,28}, //Space29
                    {21,31,39,29}, //Venus30
                    {22,32,40,30}, //Earth31
                    {23,33,41,31}, //Space32
                    {24,34,42,32}, //Space33
                    {25,35,43,33}, //Space34
                    {26,36,44,34}, //Space35
                    {27,-1,45,35}, //Neptune36
                    {28,38,-1,-1}, //Space37
                    {29,39,-1,37}, //Space38
                    {30,40,-1,38}, //Space39
                    {31,41,-1,39}, //Space40
                    {32,42,-1,40}, //Space41
                    {33,42,-1,41}, //Space42
                    {34,44,-1,42}, //Space43
                    {35,45,-1,43}, //Uranus44
                    {36,-1,-1,44}, //Space45
            };
    ArrayList<String> inventory = new ArrayList<>(); //collected items

    public void gameInitializer() { //sets initial game state
        for (int i = 0; i < roomContents.length; i++) {
            roomContents[i] = new ArrayList<>();
        }
        int improvedArmorLocation = (int) (Math.random() * 3 + 1); //randomly generates location of IMPROVED ARMOR from MERCURY to MARS
        switch (improvedArmorLocation) {
            case 1: roomContents[20].add("IMPROVED ARMOR"); break;
            case 2: roomContents[30].add("IMPROVED ARMOR"); break;
            case 3: roomContents[14].add("IMPROVED ARMOR"); break;
        }
        int warpDriveLocation = (int) (Math.random() * 7 + 1); //randomly generates location of WARP DRIVE
        switch (warpDriveLocation) {
            case 1: roomContents[20].add("WARP DRIVE"); break;
            case 2: roomContents[30].add("WARP DRIVE"); break;
            case 3: roomContents[14].add("WARP DRIVE"); break;
            case 4: roomContents[6].add("WARP DRIVE"); break;
            case 5: roomContents[25].add("WARP DRIVE"); break;
            case 6: roomContents[44].add("WARP DRIVE"); break;
            case 7: roomContents[36].add("WARP DRIVE"); break;
        }
        roomContents[19].add("FUSION GUN");
        gameIntroduction();
    }

    public void gameIntroduction() {
        System.out.println("Welcome to my game.");
        System.out.println("In order to win, you must collect all ship enhancements found on various planets in order to beat the EVIL MAN and get to NEPTUNE.");
        System.out.println("Watch out for any vulnerabilities of your ship.");
        System.out.println("Useful commands: NORTH EAST SOUTH WEST INVENTORY MAP");
        System.out.println("Commands can be entered as just the first letter.");
        printLocation(31);
    }

    public void printLocation(int roomNO) { //prints location and win/loss conditions
        System.out.print("You are at: ");
        switch (roomNO) {
            case 19:
                System.out.println("SOL");
                if (!inventory.contains("IMPROVED ARMOR")) {
                    System.out.println("Your ship could not withstand the heat of the sun. You crashed and died.");
                    System.exit(0);
                }
                break;
            case 20:
                System.out.println("MERCURY");
                break;
            case 30:
                System.out.println("VENUS");
                break;
            case 31:
                System.out.println("EARTH");
                break;
            case 14:
                System.out.println("MARS");
                break;
            case 6:
                System.out.println("JUPITER");
                if (!inventory.contains("IMPROVED ARMOR")) {
                    System.out.println("Your ship could not withstand the pressure on this planet. You crashed and died.");
                    System.exit(0);
                }
                break;
            case 25:
                System.out.println("SATURN");
                if (!inventory.contains("IMPROVED ARMOR")) {
                    System.out.println("Your ship could not withstand the pressure on this planet. You crashed and died.");
                    System.exit(0);
                }
                break;
            case 44:
                System.out.println("URANUS");
                if (!inventory.contains("IMPROVED ARMOR")) {
                    System.out.println("Your ship could not withstand the pressure on this planet. You crashed and died.");
                    System.exit(0);
                }
                break;
            case 36:
                System.out.println("NEPTUNE");
                if (!inventory.contains("IMPROVED ARMOR")) {
                    System.out.println("Your ship could not withstand the pressure on this planet. You crashed and died.");
                    System.exit(0);
                }
                if (inventory.contains("FUSION GUN")) {
                    System.out.println("You used the FUSION GUN to instantly vaporize the EVIL MAN and his ship.");
                }
                else {
                    System.out.println("The EVIL MAN attacks your ship. With no way to fight back, you crash and die.");
                    System.exit(0);
                }
                if (inventory.contains("WARP DRIVE")) {
                    System.out.println("You used the WARP DRIVE to exit the Solar System into a new frontier.");
                    System.out.println("You have won!");
                    System.exit(0);
                }
                break;
            default:
                System.out.println("SPACE"); break;
        }
        printRoomContents(roomNO);
    }

    public void printRoomContents(int roomNO) { //prints what was found in room and adds it to inventory
        ArrayList<String> contents = roomContents[roomNO];
        if (contents.size() != 0)
            System.out.print("You found: ");
        for (String thing : contents) {
            System.out.print(thing +" ");
            inventory.add(thing);
        }
        contents.clear();
        printPossibleDirections(roomNO);
    }

    public void printPossibleDirections(int roomNO) { //prints available directions
        int[] roomArr = gameArray[roomNO -1];
        System.out.print("You can go: ");
        for (int i = 0; i < roomArr.length; i++) {
            if (roomArr[i] != -1) {
                possibleDirections.add(directions[i]);
                System.out.print(directions[i] + " ");
            }
        }
        System.out.println();
        getNextAction(roomNO);
    }

    public void getNextAction(int roomNO) { //scans for desired action
        System.out.print("What would you like to do? ");
        Scanner scan = new Scanner(System.in);
        String action = scan.next();
        action = action.toLowerCase();
        switch (action) {
            default:
                System.out.println("Not a valid action.");
                getNextAction(roomNO);
                break;
            case "n": case "north":
            move(roomNO,0);
            break;
            case "e": case "east":
            move(roomNO,1);
            break;
            case "s": case "south":
            move(roomNO,2);
            break;
            case "w":case "west":
            move(roomNO,3);
            break;
            case "i": case "inventory":
            System.out.print("You have: ");
            for (String item : inventory)
                System.out.print(item +" ");
            getNextAction(roomNO);
            break;
            case "m": case "map": {
            System.out.println("0 0 0 0 0 J 0 0 0");
            System.out.println("0 0 0 0 M 0 0 0 0");
            System.out.println("S M 0 0 0 0 S 0 0");
            System.out.println("0 0 V E 0 0 0 0 N");
            System.out.println("0 0 0 0 0 0 0 U 0");
            getNextAction(roomNO);
            }
        }
    }

    public int move(int currentRoom, int intendedDirection) { //moves player to newLocation given currentRoom and intendedDirection
        int newLocation;
        newLocation = gameArray[currentRoom - 1][intendedDirection];
        if (newLocation == -1) {
            System.out.println("You cannot go that way.");
            getNextAction(currentRoom);
        }
        printLocation(newLocation);
        return newLocation;
    }
}
