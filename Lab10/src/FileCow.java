import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;
import java.util.Scanner;

public class FileCow extends Cow{
    private String name;
    private String file;
    private String data = "";

    public FileCow(String name,String filename){
        super(name);
        this.name = name;
        try {           // Create a File object
            File inputStream = new File(filename);
            Scanner sc = new Scanner(inputStream);
            while(sc.hasNextLine()){            // Save the new String into my data String
                data = data + "\n" + sc.nextLine();
            }
        }catch (FileNotFoundException e){           // In case it does not find it
            throw new RuntimeException("MOOOOO!!!!!!");
        }
        super.setImage(data);
    }
    public void setImage(){         // If someone uses this setimage, it give you an exception.
        throw new RuntimeException("Cannot reset FileCow Image");
    }
}
// "C:\\Users\\aleus\\IdeaProjects\\Lab10\\" +