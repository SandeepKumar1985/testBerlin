import java.util.Arrays;
import java.util.Collections;

/**
 * Berlin Clock.
 *
 * Creates a representation of a berlin clock from a String and provides
 * a way of printing this.
 */
public class BerlinClockImpl implements TimeConverter {

    private String formattedTime;
    private static final String NEW_LINE = System.getProperty("line.separator");
    private static final String NO_TIME_ERROR = "No time provided";
    private static final String INVALID_TIME_ERROR = "Invalid time provided.";
    private static final String NUMERIC_TIME_ERROR = "Time values must be numeric.";

    /**
     * Create a new BerlinClock instance with a string representing time.
     *
     * @param aTime - The 24 hour time in the format of HH:MM:SS
     */
    public String convertTime(String aTime) {

        if(aTime == null) throw new IllegalArgumentException(NO_TIME_ERROR);

        String[] times = aTime.split(":", 3);

        if(times.length != 3) throw new IllegalArgumentException(INVALID_TIME_ERROR);

        int hours, minutes, seconds = 0;
        
        try {
            hours = Integer.parseInt(times[0]);
            minutes = Integer.parseInt(times[1]);
            seconds = Integer.parseInt(times[2]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NUMERIC_TIME_ERROR);
        }

        if (hours < 0 || hours > 23) throw new IllegalArgumentException("Hours out of bounds.");
        if (minutes < 0 || minutes > 59) throw new IllegalArgumentException("Minutes out of bounds.");
        if (seconds < 0 || seconds > 59) throw new IllegalArgumentException("Seconds out of bounds.");

        this.formattedTime = processTime(hours, minutes, seconds);
		 return formattedTime;
    }

    /**
     * Convert individual hours, minutes and seconds into a BerlinTime object.
     *
     * @param hours - an int representing Hours
     * @param minutes - an int representing Minutes
     * @param seconds - an int representing Seconds
     *
     * @return BerlinTime object created using the parameters.
     */
	private String processTime(int hours, int minutes, int seconds) {

		String line1 = (seconds % 2 == 0) ? "Y" : "0";
		String line2 = rowString(hours / 5, 4, "R");
		String line3 = rowString(hours % 5, 4, "R");
		String line4 = rowString(minutes / 5, 11, "Y").replaceAll("YYY", "YYR");
		String line5 = rowString(minutes % 5, 4, "Y");
		return String.join(NEW_LINE, Arrays.asList(line1, line2, line3, line4, line5));
		
	}

    /**
     * Creates a string for each row of the berlin clock.
     * @param litLights
     * @param lightsInRow
     * @param lampType
     * @returnn A string representing a single row of the clock.
     */
    private String rowString(int litLights, int lightsInRow, String lampType) {

        int unlitLights = lightsInRow - litLights;
        StringBuilder str = new StringBuilder();
        /*str.append("");
        str.append(Collections.nCopies(litLights, lampType));
        String lit = str.toString();*/
        String lit = String.join("", Collections.nCopies(litLights, lampType));
       /* str = new StringBuilder();
        str.append("");
        str.append(Collections.nCopies(unlitLights, "0"));*/
        String unlit = String.join("", Collections.nCopies(unlitLights, "0"));
        //String unlit = str.toString();

        return lit + unlit;
    }

    /**
     * Print a representation of the berlin time.
     */
    public void printClock() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return this.formattedTime;
    }


}
