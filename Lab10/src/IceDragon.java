public class IceDragon extends Dragon{

    // The constructor, it passes the value to its parent constructor.
    public IceDragon(String name,String image){
        super(name, image);
    }

    // This dragon cannot breathe fire.
    public boolean canBreatheFire(){
        return false;
    }
}
