import java.util.Scanner;
import java.util.Arrays;

public class RleProgram {
    public static void main(String args[]) {

        String filename;
        String menuString;
        Scanner scan = new Scanner(System.in);
        int input = 0;

        System.out.println("Welcome to the RLE image encoder! \n");
        System.out.println("Displaying Spectrum Image:");

        byte[] data = ConsoleGfx.testRainbow;
        ConsoleGfx.displayImage(data);

        data = null;
        System.out.println();
        System.out.println();

        // Execute the menu one time and then do a loop if the input for the menu is not 0.
        do {
            System.out.println("RLE Menu");
            System.out.println("--------");
            System.out.println("0. Exit\n1. Load File\n2. Load Test Image\n3. Read RLE String\n4. Read RLE Hex String\n" +
                    "5. Read Data Hex String\n6. Display Image\n7. Display RLE String\n8. Display Hex RLE Data\n9. Display Hex Flat Data");
            System.out.println();

            // Execute one time and then do a loop if the input for the menu  is between 0 and 4
            System.out.print("Select a Menu Option: ");
            input = scan.nextInt();

            if (input == 0) {
            } else if (input == 1) {

                System.out.print("Enter name of file to load: ");
                filename = scan.next();
                data = ConsoleGfx.loadFile(filename);

            } else if (input == 2) {

                data = ConsoleGfx.testImage;
                System.out.println("Test image data loaded.");

            } else if (input == 3) {

                System.out.print("Enter an RLE string to be decoded: ");
                filename = scan.next();
                data = stringToRle(filename);
                /*for (int i = 0; i < data.length; i++) {
                    System.out.println(data[i]);
                }*/
                data = decodeRle(data);
                /*for (int i = 0; i < data.length; i++) {
                    System.out.println(data[i]);
                }*/

            } else if (input == 4) {

                System.out.print("Enter the hex string holding RLE data: ");
                filename = scan.next();
                data = stringToData(filename);
                data = decodeRle(data);

            } else if (input == 5) {

                System.out.print("Enter the hex string holding flat data: ");
                filename = scan.next();
                data = stringToData(filename);
                /*for (int i = 0; i < data.length; i++) {
                    System.out.println(data[i]);
                }*/

            } else if (input == 6) {

                System.out.println("Displaying image...");

                if (data == null){
                    System.out.println("(no data)");
                }
                else {
                    ConsoleGfx.displayImage(data);
                }
            } else if (input == 7) {

                if(data == null){
                    System.out.println("RLE representation: (no data)");
                }
                else {

                    data = encodeRle(data);
                    /*for (int i = 0; i < data.length; i++) {
                        System.out.println(data[i]);
                    }*/
                    menuString = toRleString(data);
                    System.out.println("RLE representation: " + menuString);
                }

            } else if (input == 8) {

                if(data == null){
                    System.out.println("RLE hex values: (no data)");
                }
                else {
                    data = encodeRle(data);
                    menuString = toHexString(data);
                    System.out.println("RLE hex values: " + menuString);
                }

            } else if (input == 9) {

                if(data == null){
                    System.out.println("Flat hex values: (no data)");
                }
                else {
                    menuString = toHexString(data);
                    System.out.println("Flat hex values: " + menuString);
                }

            } else {
                System.out.println("Error! Invalid input.");
            }   // While loops
            System.out.println();

        } while (input != 0);
    }

    public static String toHexString(byte[] data) {

        String hexa = "";

        for (int i = 0; i <= data.length - 1; i++) {

            switch (data[i]) {
                // In case is a letter of the hex.
                case 10:
                    hexa = hexa + "a";
                    break;
                case 11:
                    hexa = hexa + "b";
                    break;
                case 12:
                    hexa = hexa + "c";
                    break;
                case 13:
                    hexa = hexa + "d";
                    break;
                case 14:
                    hexa = hexa + "e";
                    break;
                case 15:
                    hexa = hexa + "f";
                    break;
                // In case is not one of those letters
                default:
                    hexa = hexa + data[i];
                    break;
            }
        }
        return hexa;
    }

    public static int countRuns(byte[] flatData) {

        int count = 1;
        int maximum = 0;

        for (int i = 1; i <= flatData.length - 1; i++) {


            if (flatData[i] != flatData[i - 1]) {
                count++;
                maximum = 0;
            }
            else {

                maximum++;
                if (maximum == 15) {
                    count++;
                    maximum = 0;
                }
            }
        }
        return count;
    }

    public static byte[] encodeRle(byte[] flatData) {

        byte[] encode = new byte[countRuns(flatData) * 2];
        int count = 1;
        int temp = 0;

        for (int counting = 0; counting <= encode.length - 1; counting += 2) {

            count = 1;

            if (counting < encode.length - 1) {
                encode[counting + 1] = flatData[temp];
            }

            for (int i = temp; i <= flatData.length - 2; i++) {
                if (flatData[i] == flatData[i + 1]) {
                    count++;
                    if(count < 15) {
                        if ((i + 1) == flatData.length - 2) {
                            encode[counting] = (byte) (count + 1);
                        } else {
                            encode[counting] = (byte) count;
                        }
                    }
                    else{
                        encode[counting] = (byte) count;
                        temp = i + 2;
                        break;
                    }
                } else {
                    encode[counting] = (byte) count;
                    temp = i + 1;
                    break;
                }
            }

            if (encode[counting] == 0) {
                encode[counting] = (byte) 1;
            }
        }
        return encode;
    }

    public static int getDecodedLength(byte[] rleData) {

        int total = 0;

        for (int i = 0; i <= rleData.length - 1; i += 2) {
            total += rleData[i];
        }
        return total;
    }

    public static byte[] decodeRle(byte[] rleData) {

        byte[] decode = new byte[getDecodedLength(rleData)];
        int count = 0;

        for (int i = 0; i <= rleData.length - 1; i += 2) {
            for (int j = 0; j < rleData[i]; j++) {
                if((i + 1) < rleData.length) {
                    decode[count] = rleData[i + 1];
                    count++;
                }
            }
        }
        return decode;
    }

    public static byte[] stringToData(String dataString) {

        byte[] data = new byte[dataString.length()];

        for (int i = 0; i <= data.length - 1; i++) {
            if (dataString.charAt(i) == 'A' || dataString.charAt(i) == 'a') {
                data[i] = (byte) 10;
            }
            // In case is B
            else if (dataString.charAt(i) == 'B' || dataString.charAt(i) == 'b') {
                data[i] = (byte) 11;
            }
            // In case is C
            else if (dataString.charAt(i) == 'C' || dataString.charAt(i) == 'c') {
                data[i] = (byte) 12;
            }
            // In case is D
            else if (dataString.charAt(i) == 'D' || dataString.charAt(i) == 'd') {
                data[i] = (byte) 13;
            }
            // In case is E
            else if (dataString.charAt(i) == 'E' || dataString.charAt(i) == 'e') {
                data[i] = (byte) 14;
            }
            // In case is F
            else if (dataString.charAt(i) == 'F' || dataString.charAt(i) == 'f') {
                data[i] = (byte) 15;
            }
            // In case is a number
            else {
                data[i] = (byte) Character.getNumericValue(dataString.charAt(i));
            }
        }
        return data;
    }

    public static String toRleString(byte[] rleData) {

        String rleString = "";

        for (int i = 0; i <= rleData.length - 1; i += 2) {

            rleString = rleString + rleData[i];

            if (i == (rleData.length - 1)) {
                break;
            }

            switch (rleData[i + 1]) {
                // In case is a letter of the hex.
                case 10:
                    rleString = rleString + "a";
                    break;
                case 11:
                    rleString = rleString + "b";
                    break;
                case 12:
                    rleString = rleString + "c";
                    break;
                case 13:
                    rleString = rleString + "d";
                    break;
                case 14:
                    rleString = rleString + "e";
                    break;
                case 15:
                    rleString = rleString + "f";
                    break;
                // In case is not one of those letters
                default:
                    rleString = rleString + rleData[i + 1];
                    break;
            }

            if ((i + 1) != (rleData.length - 1)) {
                rleString = rleString + ":";
            } else {
                break;
            }
        }
        return rleString;
    }

    public static byte[] stringToRle(String rleString) {

        byte[] toRle;
        int length = 2;
        int index = 0;
        String temp;

        for (int i = 0; i <= rleString.length() - 1; i++) {
            if (rleString.charAt(i) == ':') {
                length += 2;
            }
        }
        /*if((rleString.length() - 3) >= 0) {
            if (rleString.charAt(rleString.length() - 2) == ':' || (rleString.charAt(rleString.length() - 3) == ':' && rleString.charAt(rleString.length() - 2) == '1')) {
                length--;
            }
        }*/

        toRle = new byte[length];

        for (int elements = 0; elements <= length - 1; elements++) {

            if ((index + 2) < rleString.length() && rleString.charAt(index) == '1' && Character.isDigit(rleString.charAt(index + 1))) {
                if (Character.isLetter(rleString.charAt(index + 2)) || Character.isDigit(rleString.charAt(index + 2))) {

                    temp = String.format("%s%s", rleString.charAt(index), rleString.charAt(index + 1));
                    toRle[elements] = Byte.valueOf(temp);
                    index += 2;
                } else {
                    toRle[elements] = (byte) Character.getNumericValue(rleString.charAt(index));
                    index++;
                }
            } else if (rleString.charAt(index) == ':') {
                index++;
                elements--;
            } else {

                if (rleString.charAt(index) == 'A' || rleString.charAt(index) == 'a') {
                    toRle[elements] = (byte) 10;
                }
                // In case is B
                else if (rleString.charAt(index) == 'B' || rleString.charAt(index) == 'b') {
                    toRle[elements] = (byte) 11;
                }
                // In case is C
                else if (rleString.charAt(index) == 'C' || rleString.charAt(index) == 'c') {
                    toRle[elements] = (byte) 12;
                }
                // In case is D
                else if (rleString.charAt(index) == 'D' || rleString.charAt(index) == 'd') {
                    toRle[elements] = (byte) 13;
                }
                // In case is E
                else if (rleString.charAt(index) == 'E' || rleString.charAt(index) == 'e') {
                    toRle[elements] = (byte) 14;
                }
                // In case is F
                else if (rleString.charAt(index) == 'F' || rleString.charAt(index) == 'f') {
                    toRle[elements] = (byte) 15;
                }
                // In case is a number
                else {
                    toRle[elements] = (byte) Character.getNumericValue(rleString.charAt(index));
                }
                index++;
            }
        }
        return toRle;
    }
}
