public class Cowsay {

    public static void main(String args[]) {

        // Create two Cow array and it will equal the getCow() array and to the getFileCow().
        Cow[] myCow = HeiferGenerator.getCows();
        Cow[] fileCows = HeiferGenerator.getFileCows();
        // If the command line is -1, it display the list of cows.
        if (args[0].equals("-l")) {
            listCows(myCow);
            System.out.println();
            listFileCows(fileCows);
            System.out.println();
            System.out.println();
        } else if (args[0].equals("-n")) {      // In case the command line start with -n.
            Cow current = findCow(args[1], myCow);

            if (current == null){   // In case the second word is not a type of Cow.
                System.out.println("Could not find " + args[1] + " cow!");
                System.out.println();
            }
            else{           // Display the Message with the specific Cow.
                System.out.println();
                for (int i = 2; i < args.length; i++) {
                    System.out.print(args[i] + " ");
                }
                System.out.println();
                System.out.println(current.getImage());
                // In case the Cow object is an instance of Dragon.
                if(current instanceof Dragon){
                    if(((Dragon) current).canBreatheFire()){
                        System.out.println("This  dragon  can  breathe  fire.");
                    }
                    else{           // In case it comes from IceDragon.
                        System.out.println("This  dragon  cannot  breathe  fire.");
                    }
                    System.out.println();
                }
            }
        }
        else if(args[0].equals("-f")){           // In case the command line start with -f.
            Cow current = findCow(args[1], fileCows);
            if (current == null){   // In case the second word is not a type of Cow.
                System.out.println("Could not find " + args[1] + " cow!");
                System.out.println();
            }
            else {
                System.out.println();
                for (int i = 2; i < args.length; i++) {
                    System.out.print(args[i] + " ");
                }
                System.out.println();
                System.out.println(current.getImage());
            }
        }
        else {            // Display the Message with the default Cow.
            System.out.println();
            for (int i = 0; i < args.length; i++) {
                System.out.print(args[i] + " ");
            }
            System.out.println();
            Cow current = findCow("heifer", myCow);
            System.out.println(current.getImage());
        }
    }

    // Display the list of Cows.
    private static void listCows(Cow[] cows) {
        System.out.print("Regular cows available: ");
        for (int i = 0; i < cows.length; i++) {
            System.out.print(cows[i].getName() + " ");
        }
    }

    // Find if the String name is equal to a Cow's type.
    private static Cow findCow(String name, Cow[] cows) {
        for (int i = 0; i < cows.length; i++) {
            if (name.equals(cows[i].getName())) {
                return cows[i];
            }
        }
        return null;
    }

    // Display the list of File Cows.
    private static void listFileCows(Cow[] fileCows){
        System.out.print("File cows available: ");
        for (int i = 0; i < fileCows.length; i++) {
            System.out.print(fileCows[i].getName() + " ");
        }
    }
}
