package competition.kattis.the_class.lab8;

import competition.annotations.Incorrect;
import competition.annotations.SiteType;
import competition.annotations.Website;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

@SiteType(type = Website.KATTIS)
@Incorrect(url = "https://open.kattis.com/problems/allfaxnoprinter")
public class allfaxnoprinter2 {
    /*
     * Efficient writing to the builder.
     * Builder starts with 0 as the first number because no optimizations can be made on the first paper inserted
     */
    private static final StringBuilder BUFFER = new StringBuilder("0\n");
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private static final OutputStream OUT = new BufferedOutputStream(System.out);

    private static int[] optimalFaxMachines, persistentFaxMachines;

    public static void main(String[] args) throws IOException {
        //Grab and parse the first line of input into the necessary details
        String[] line = IN.readLine().split(" ");
        int jobs = Integer.parseInt(line[0]) - 1;
        int faxMachines = Integer.parseInt(line[1]);

        //Create fax machine sets
        optimalFaxMachines = new int[faxMachines];
        persistentFaxMachines = new int[faxMachines];

        //Retrieve the first job to place into the fax machines by default
        line = IN.readLine().split(" ");
        int previousTimeInterval = Integer.parseInt(line[0]);
        optimalFaxMachines[0] = Integer.parseInt(line[1]);
        persistentFaxMachines[0] = Integer.parseInt(line[1]);

        //Create the variables important for the loop
        int timeInterval;
        int optimizationCount = 0;
        int persistentCount = 0;

        for (int i = 0; i < jobs; i++) {
            line = IN.readLine().split(" ");
            timeInterval = Integer.parseInt(line[0]);

            //If there's another paper that comes in at the same time, it'll either get loaded into
            // an available machine, take over an existing machine or be disregarded
            if (timeInterval == previousTimeInterval) {
                int index = getFirstAvailable(optimalFaxMachines);
                if (index != -1) {
                    optimalFaxMachines[index] = Integer.parseInt(line[1]);
                } else {
                    stealFax(Integer.parseInt(line[1]));
                }

                index = getFirstAvailable(persistentFaxMachines);
                if (index != -1) {
                    persistentFaxMachines[index] = Integer.parseInt(line[1]);
                }
            } else {
                //If we're at the next time interval, then we decrement the occupied fax machines by one
                //Then, we check to see if there's a machine that's open for new work
                decrementFax(optimalFaxMachines);
                decrementFax(persistentFaxMachines);

                int index = getFirstAvailable(persistentFaxMachines);
                if (index != -1) {
                    persistentFaxMachines[index] = Integer.parseInt(line[1]);
                    persistentCount++;
                }

                index = getFirstAvailable(optimalFaxMachines);
                if (index != -1) {
                    optimalFaxMachines[index] = Integer.parseInt(line[1]);
                    optimizationCount++;
                }

                //Setting the new previousTimeInterval to the current
                previousTimeInterval = timeInterval;
            }
            //Calculating the difference between the optimal and persistent fax machine strategies
            BUFFER.append(Math.abs(optimizationCount - persistentCount)).append('\n');
        }

        //Printing out to the console
        OUT.write(BUFFER.toString().getBytes());
        OUT.flush();
    }

    /**
     * Used to see if the optimalFaxMachines have any work that can be overridden with work that doesn't take as long
     *
     * @param incomingTask - The task to compare to all the current fax machine work
     */
    private static void stealFax(int incomingTask) {
        for (int i = 0; i < optimalFaxMachines.length; i++) {
            int value = optimalFaxMachines[i];
            if (value > incomingTask) {
                optimalFaxMachines[i] = incomingTask;
                return;
            }
        }
    }

    /**
     * Moving forward in time will decrement all the current fax machines by 1
     *
     * @param faxMachines The array of fax machines that need to progress in time
     */
    private static void decrementFax(int[] faxMachines) {
        for (int i = 0; i < faxMachines.length; i++) {
            int value = faxMachines[i];
            if (value != 0)
                faxMachines[i] = value - 1;
        }
    }

    /**
     * Checks to see if there are any fax machines that are available to take more work
     *
     * @param faxMachines The array of fax machines that need to be checked
     * @return The index of the first available fax machine or -1 if all are occupied
     */
    private static int getFirstAvailable(int[] faxMachines) {
        for (int i = 0; i < faxMachines.length; i++) {
            if (faxMachines[i] == 0) {
                return i;
            }
        }
        return -1;
    }
}
