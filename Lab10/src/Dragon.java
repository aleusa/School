public class Dragon extends Cow {

    // Constructor, it passes the value of the name to its parent constructor
    // and assign the image to its parent setImage method.
    public Dragon(String name,String image){
        super(name);
        super.setImage(image);
    }

    // This dragon can breathe fire.
    public boolean canBreatheFire(){
        return true;
    }
}
