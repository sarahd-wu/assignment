import java.util.Random;

/**
 * A program to measure the running times of several basic operations.
 */

public class Timing {

    /**
     * step size between tested sizes of arrays
     */
    public static final int STEP = 10_000;

    /**
     * maximum array size to consider
     */
    public static final int MAX = 1_000_000;

    public static void main(String[] args) {
        testArrayConstruction();
        testArrayAccess();
        testArithmetic();
        testLogic();
    }

    /**
     * Measure the amount of time required to initialize arrays of
     * various sizes. Record the running times in a CSV file called
     * "array-building-times.csv"
     */
    static void testArrayConstruction() {
        RunTimer rt = new RunTimer();
        CSVWriter csv = new CSVWriter("array-building-times.csv");

        // make the first row of the csv file with appropriate
        // headings
        csv.addEntry("size");
        csv.addEntry("time (ns)");
        csv.endLine();

        for (int size = STEP; size <= MAX; size += STEP) {
            // make an array with "size" elements and measure how long
            // it takes
            rt.start();
            int[] arr = new int[size];
            rt.stop();

            // record the size and elapsed times as a new row in the
            // csv file
            csv.addEntry(size);
            csv.addEntry(rt.getElapsedNanos());
            csv.endLine();

            // reset the timer to start at 0
            rt.reset();
        }

        // DON'T FORGET TO CLOSE THE FILE!!!
        csv.close();
    }

    /**
     * Measure the average amount of time needed to access individual
     * elements in arrays of various sizes. Record the running times
     * to "array-access-times.csv"
     */
    static void testArrayAccess() {
        int numAccesses = 100;

        RunTimer rt = new RunTimer();
        CSVWriter csv = new CSVWriter("array-access-times.csv");
        Random r = new Random();


        // make the first row of the csv file with appropriate
        // headings
        csv.addEntry("array size");
        csv.addEntry("index value"); // testing out index value being read
        csv.addEntry("time per access");
        csv.endLine();

        int index = 0; // initializing index to bring into scope

        for (int size = STEP; size <= MAX; size += STEP) {
            // create a new array of correct size
            int[] arr = new int[size];
            int val = 0;

            // access numAccesses random elements of arr
            for (int i = 0; i < numAccesses; i++) {
                index = r.nextInt(size);

                // measure the elapsed time to read arr[index] and
                // write the value to val
                rt.start();
                val = arr[index];
                rt.stop();
            }

            // add a new row with the size and the *average* time per
            // access
            csv.addEntry(size);
            csv.addEntry(index);
            csv.addEntry(rt.getElapsedNanos() / numAccesses);
            csv.endLine();

            rt.reset();
        }

        csv.close();
    }

    /**
     * Measure the typical time to perform arithmetic operations for
     * various dataset (array) sizes. For each array size, the method
     * creates 3 arrays of that size. The first two are initialized
     * with random values. Each entry in the third array should be the
     * sum of the corresponding entries in the first two arrays. The
     * total elapsed time to perform this sum should be measured, and
     * the *average time per operation* recorded in a csv file.
     */
    static void testArithmetic() {
        RunTimer rt = new RunTimer();
        CSVWriter csv = new CSVWriter("arithmetic-times.csv");

        // make the first row of the csv file with appropriate
        // headings
		csv.addEntry("size");
        csv.addEntry("time in total (ns)");
        csv.addEntry("average time (ns)");
		csv.addEntry("total for first value");
        csv.endLine();

        for (int size = STEP; size <= MAX; size += STEP) {
			int[] arr = new int[size];
			int[] arr1 = new int[size];
			int[] arrSum = new int[size];


			for (int i = 0; i < size; i++) {
                // make TWO arrays with "size" elements
                Random r = new Random();
                arr[i] = r.nextInt(10); // sets beginning index to random digit

                r = new Random(); // resets the random value
                arr1[i] = r.nextInt(10); // sets beginning index to random digit



                // records the time when first elements of both arrays are added together
                rt.start();

				// inputting the total value into the third array
				int valArrayThree = arr[i] + arr1[i];
				arrSum[i] = valArrayThree;

                rt.stop();
			}


            // record the size and elapsed times as a new row in the
            // csv file
            csv.addEntry(size);
            csv.addEntry(rt.getElapsedNanos());
            csv.addEntry(rt.getElapsedNanos()/size);
            csv.addEntry(arrSum[0]);
            csv.endLine();

            // reset the timer to start at 0
            rt.reset();
        }

        // DON'T FORGET TO CLOSE THE FILE!!!
        csv.close();
    }


    /**
     * Measure the typical time to perform arithmetic operations for
     * various dataset (array) sizes. For each array size, the method
     * creates 3 arrays of that size. The first two are initialized
     * with random values. Each entry in the third array should be the
     * minimum of the corresponding entries in the first two arrays. The
     * total elapsed time to perform this sum should be measured, and
     * the *average time per operation* recorded in a csv file.
     * <p>
     * Note: to find the min of two values, you should write your own
     * logical test with an "if" statement. Do *not* use Math.min()
     * (unless you wish to compare the performance of your own code to
     * Math.min()).
     */
    static void testLogic() {
        RunTimer rt = new RunTimer();
        CSVWriter csv = new CSVWriter("logic-times.csv");

        // make the first row of the csv file with appropriate
        // headings
        csv.addEntry("size");
        csv.addEntry("time in total (ns)");
        csv.addEntry("average time (ns)");
        csv.addEntry("min for first value");
        csv.endLine();

        for (int size = STEP; size <= MAX; size += STEP) {
            int[] arr = new int[size];
            int[] arr1 = new int[size];
            int[] arrSum = new int[size];


            for (int i = 0; i < size; i++) {

                // make TWO arrays with "size" elements
                Random r = new Random();
                arr[i] = r.nextInt(); // sets beginning index to random digit

                r = new Random(); // resets the random value
                arr1[i] = r.nextInt(); // sets beginning index to random digit

                rt.start();

                int valArrayThree;
                // inputting the min value into the third array
                if (arr[i] < arr1[i]) { // if arr[i] is less than arr1[i]
                    valArrayThree = arr[i];
                } else { // if arr1[i] is less than arr[i]
                    valArrayThree = arr1[i]; }
                    arrSum[i] = valArrayThree;

                rt.stop();
            }



            // record the size and elapsed times as a new row in the
            // csv file
            csv.addEntry(size);
            csv.addEntry(rt.getElapsedNanos());
            csv.addEntry(rt.getElapsedNanos()/size);
            csv.addEntry(arrSum[0]);
            csv.endLine();

            // reset the timer to start at 0
            rt.reset();
        }

        // DON'T FORGET TO CLOSE THE FILE!!!
        csv.close();
    }

    }
