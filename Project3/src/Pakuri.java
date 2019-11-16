public class Pakuri {

    // Declare the variables.
    private String species;
    private int attack, defense, speed;

    // Constructor
    public Pakuri(String species){
        this.species = species;
        attack = (species.length() * 7) + 9;
        defense = (species.length() * 5) + 17;
        speed = (species.length() * 6) + 13;
    }

    // Return the String species.
    public String getSpecies(){
        return species;
    }

    // Return the int attack.
    public int getAttack(){
        return attack;
    }

    // Return the int defense.
    public int getDefense(){
        return defense;
    }

    // Return the int speed.
    public int getSpeed(){
        return speed;
    }

    // Change the attack.
    public void setAttack(int newAttack){
        attack = newAttack;
    }

    // Evolve the Pakuri by increasing its stats.
    public void evolve(){
        attack = attack * 2;
        defense = defense * 4;
        speed = speed * 3;
    }

    // Compare two Pakuris by their species's name using lexicographical.
    public int compareTo(Pakuri target){

        for (int i = 0; i < this.getSpecies().length() && i < target.getSpecies().length(); i++) {
            if ((int)this.getSpecies().charAt(i) == (int)target.getSpecies().charAt(i)) {       // In case the two char are equal.
                continue;
            }
            else {                  // In case the two char are different.
                return (int)this.getSpecies().charAt(i) - (int)target.getSpecies().charAt(i);
            }
        }

        // If the length of the second String is bigger that the first String.
        if (this.getSpecies().length() < target.getSpecies().length()) {
            return (this.getSpecies().length() - target.getSpecies().length());
        }
        // If the length of the first String is bigger that the second String.
        else if (this.getSpecies().length() > target.getSpecies().length()) {
            return (this.getSpecies().length() - target.getSpecies().length());
        }

        else {  // In case they are equal.
            return 0;
        }
    }
}
