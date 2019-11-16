public class Pakudex {

    // Declare the variables.
    private int size;
    private int capacity;
    private Pakuri[] pakuris;

    // Constructor without argument.
    public Pakudex(){
        capacity = 20;
        pakuris = new Pakuri[capacity];
    }

    // Constructor with argument.
    public Pakudex(int capacity){
        this.capacity = capacity;
        pakuris = new Pakuri[capacity];
    }

    // Return the size.
    public int getSize() {
        return size;
    }

    // Return the capacity.
    public int getCapacity() {
        return capacity;
    }

    // Return an array of the Pakuris's species.
    public String[] getSpeciesArray(){
        String[] speciesArray = new String[size];

        if(size == 0){      // If there is not a Pakuri return null.
            return null;
        }
        for (int i = 0; i < size; i++) {        // Assign the elements of the array.
            speciesArray[i] = pakuris[i].getSpecies();
        }
        return speciesArray;
    }

    // Return an array of the Pakuri's stats.
    public int[] getStats(String species){
        int[] stats = new int[3];
        boolean check = false;

        for (int i = 0; i < size; i++) {            // Assign the values of the elements of the array.
            if (pakuris[i].getSpecies().equals(species)){
                stats[0] = pakuris[i].getAttack();
                stats[1] = pakuris[i].getDefense();
                stats[2] = pakuris[i].getSpeed();
                check = true;
                break;
            }
        }
        if (!check){    // In case there is not a Pakuri with that species's name.
            return null;
        }

        return stats;
    }

    // Sort the Pakuris by their species's name using lexicographical.
    public void sortPakuri(){
        Pakuri temp;
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                if (pakuris[i].compareTo(pakuris[j]) > 0){      // If the second one is bigger that the first one, swap them.
                    temp = pakuris[i];
                    pakuris[i] = pakuris[j];
                    pakuris[j] = temp;
                }
            }
        }
    }

    // Add a Pakuri, assigning an element of the Pakuri array.
    public boolean addPakuri(String species){
        for (int i = 0; i < pakuris.length; i++) {
            if(pakuris[i] == null){         // Check if the array have something in that element, if it doesn't, add it.
                pakuris[i] = new Pakuri(species);
                size++;
                return true;
            }
            else{           // In case two Pakuris are equal.
                if(pakuris[i].getSpecies().equals(species)){
                    break;
                }
            }
        }
        return false;       // In case they are equal return false.
    }

    // Evolve the specific Pakuri.
    public boolean evolveSpecies(String species){
        for (int i = 0; i < size; i++) {
            if (pakuris[i].getSpecies().equals(species)){           // Check for the species's name.
                pakuris[i].evolve();
                return true;
            }
        }
        return false;       // In case there is not a Pakuri with the specific name.
    }
}
