package utilities;

import java.text.SimpleDateFormat;
import java.util.*;

/**
* Utilities to generate information for user data. 
*/
public class UserUtilities {
    public static final String UK_AREA_CODE = "201";

    public void createDefaultUser() {

    }

    public static String generateRandomEmail(int size, String postfix) {
        return generateRandomString(size, "abcdefghijklmnopqrstuvwxyz1234567890") + postfix;
    }

    public static String generateRandomPassword(int size) {
        return generateRandomString(size, "abcdefghijklmnopqrstuvwxyz1234567890");
    }

    public static String generateRandomName() {
        List<String> name = new ArrayList<>(Arrays.asList("Ryan", "Robertson", "Lays", "Charles", "Rebecca", "Waldo"));

        Random rand = new Random();

        return name.get(rand.nextInt(name.size()));
    }

    public static String generateRandomAddress(int size, String postfix) {
        return generateRandomString(size, "123567890") + postfix;
    }

    public static String generateRandomTexasZipCode() {

        List<String> name = new ArrayList<>(Arrays.asList("75071", "75070", "75254", "75072"));

        Random rand = new Random();

        return name.get(rand.nextInt(name.size()));
    }

    public static String getRandomDob(int minYear, int maxYear) {
        GregorianCalendar gc = new GregorianCalendar();

        int dayOfYear = randBetween(1, gc.getActualMaximum(Calendar.DAY_OF_YEAR));
        int year = randBetween(minYear, maxYear);
        gc.set(Calendar.DAY_OF_YEAR, dayOfYear);
        gc.set(Calendar.YEAR, year);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setCalendar(gc);

        return sdf.format(gc.getTime());
    }

    private static int randBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }

    private static String generateRandomString(int size, String possibleInputs) {
        StringBuilder result = new StringBuilder();
        Random rand = new Random();

        for (int i = 0; i < size; i++) {
            result.append(possibleInputs.charAt(rand.nextInt(possibleInputs.length() - 1)));
        }

        return String.valueOf(result);
    }

    /**
     * This is to generate a random phone number
     *
     * @param prefix area code. For UK use X, Mexico Y, asdlfasjdf
     * @param size size of phone number
     * @return a randomly generated phone number
     */
    public static String generateRandomPhoneNumber(String prefix, int size) {
       String ukNumber = generateRandomString(size,"1234567890");
       return prefix + ukNumber;
    }
}
