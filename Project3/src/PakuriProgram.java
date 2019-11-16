import java.util.Scanner;

public class PakuriProgram {

    public static void main(String args[]) {
        // Declare all the variables that are going to be use during the program.
        int input = 0;
        int maximum = 0;
        int count = 0;
        String inputString;
        boolean check = false;
        Scanner scanner = new Scanner(System.in);
        Pakudex pakudex;

        System.out.println("Welcome to Pakudex: Tracker Extraordinaire!");
        // While check is false keep running, while user don't input a valid size.
        while (!check) {
            System.out.print("Enter max capacity of the Pakudex: ");
            try {
                maximum = Integer.parseInt(scanner.next());
            } catch (Exception ie) {            // In case it is not a valid size.
                maximum = -1;
            }
            if (maximum > 0) {                  // In case it is a valid size.
                System.out.println("The Pakudex can hold " + maximum + " species of Pakuri.");
                check = true;
            } else {
                System.out.println("Please enter a valid size.");
            }
        }
        // Instantiate the Pakudex.
        pakudex = new Pakudex(maximum);
        System.out.println();

        do {        // Display the menu.
            System.out.println("Pakudex Main Menu");
            System.out.println("-----------------");
            System.out.println("1.  List Pakuri\n2.  Show Pakuri\n3.  Add Pakuri\n4.  Evolve Pakuri\n5.  Sort Pakuri\n6.  Exit");
            System.out.println();
            System.out.print("What would you like to do? ");
            try {
                input = Integer.parseInt(scanner.next());
            } catch (Exception ie) {        // In case is not an appropiate input.
                input = -1;
            }

            if (input == 1) {       // In case input is 1.
                String[] speciesArray = pakudex.getSpeciesArray();
                if (speciesArray == null) {     // In case there is not a Pakuri.
                    System.out.println("No Pakuri in Pakudex yet!");
                } else {        // Print the Pakuris.
                    System.out.println("Pakuri In Pakudex:");
                    for (int i = 0; i < speciesArray.length; i++) {
                        System.out.println((i + 1) + ".  " + speciesArray[i]);
                    }
                }
                System.out.println();

            } else if (input == 2) {       // In case input is 2.
                System.out.print("Enter the name of the species to display: ");
                inputString = scanner.next();

                int[] stats = pakudex.getStats(inputString);

                if (stats == null) {        // In case there is not a Pakuri with that name.
                    System.out.println("Error: No such Pakuri!");
                } else {            // Display the stats of the specific Pakuri.
                    System.out.println("\nSpecies: " + inputString +
                            "\nAttack: " + stats[0] +
                            "\nDefense: " + stats[1] +
                            "\nSpeed: " + stats[2]);
                }
                System.out.println();

            } else if (input == 3) {       // In case input is 3.
                if (count < maximum) {
                    System.out.print("Enter the name of the species to add: ");
                    inputString = scanner.next();

                    if (pakudex.addPakuri(inputString)) {       // If there is enough space.
                        System.out.println("Pakuri species " + inputString + " successfully added!");
                        count++;
                    } else {            // In case there are two Pakuris with the same name.
                        System.out.println("Error: Pakudex already contains this species!");
                    }
                }
                else{           // In case the capacity of Pakuri is full.
                    System.out.println("Error: Pakudex is full!");
                }
                System.out.println();

            } else if (input == 4) {       // In case input is 4.
                System.out.print("Enter the name of the species to evolve: ");
                inputString = scanner.next();
                if (pakudex.evolveSpecies(inputString)) {       // Evolve the specific Pakuri.
                    System.out.println(inputString + " has evolved!");
                } else {            // In case there is not a Pakuri with that name.
                    System.out.println("Error: No such Pakuri!");
                }
                System.out.println();

            } else if (input == 5) {       // In case input is 5.
                pakudex.sortPakuri();
                System.out.println("Pakuri have been sorted!");         // The Pakuris are now sort.
                System.out.println();

            } else if (input == 6) {       // In case input is 6.
                System.out.println("Thanks for using Pakudex! Bye!");           // Display bye message.
            } else {       // In case input is not appropiate.
                System.out.println("Unrecognized menu selection!");
                System.out.println();
            }
        } while (input != 6);       // Get out of the do-while if the input is 6.
    }
}
