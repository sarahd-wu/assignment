/**
 * <p>An object to measure the cumulative elapsed time of events, like
 * a stopwatch. The basic operations are like that of a stopwatch:
 * start, stop, and reset. The elapsed time can then be reported in
 * several units: nanoseconds, milliseconds, and seconds.</p>
 *
 * <p>Example usage:</p>
 *
 * <code>
 * RunTimer rt = new RunTimer();
 * rt.start();
 * // code block 1
 * rt.stop();
 * // code block 2
 * rt.start();
 * // code block 3
 * rt.stop();
 * long time = rt.getElapsedMillis();
 * </code>
 * 
 * <p>The code above will measure the total amount of elapsed time (in
 * milliseconds) taken to execute code blocks 1 and 3. Since the timer
 * is stopped before code block 2 and restarted after code block 2,
 * the amount of time spent executing code block 2 will not be
 * included in the measurement.</p>
 */

public class RunTimer {
    private long start = 0;
    private long end = 0;
    private long elapsed = 0;
    boolean stopped = true;

    /**
     * <p>Determine if the RunTimer is currently stopped.</p>
     *
     * @return true if and only if the RunTimer is currently stopped
     */
    public boolean isStopped() { return stopped; }

    /**
     * <p>Start the RunTimer if it is currently stopped. The method
     * has no effect if the RunTimer is already running. </p>
     *
     */
    public void start() {
	if (stopped) {
	    start = System.nanoTime();
	    stopped = false;
	}
    }

    /**
     * <p>Stop the RunTimer if it is not already stopped. The elapsed
     *time since the previous call to <code>this.start()</code> will
     *be added to the total elapsed time stored by the RunTimer.  </p>
     */
    public void stop() {
	if (!stopped) {
	    end = System.nanoTime();
	    elapsed += (end - start);
	    stopped = true;
	}
    }

    /**
     * <p>Stop and reset the RunTimer so that the elapsed time is
     * <code>0</code>.</p>
     */
    public void reset() {
	stop();
	elapsed = 0;
    }
			 
    /**
     * <p>Return the total elapsed time stored in the RunTimer
     * measured in nanoseconds. If the RunTimer is stopped, then this
     * time will be the sum of the durations of the start-stop
     * intervals since the previous <code>reset()</code> (if any). If
     * the RunTimer is not stopped, then the time since the last call
     * to <code>stop()</code> is included as well.</p>
     *
     * @return the total elapsed time in nanoseconds
     */
    public long getElapsedNanos() {
	if (stopped) {
	    return elapsed;
	}

	return System.nanoTime() - start + elapsed;
    }
    

    /**
     * <p>Return the total elapsed time stored in the RunTimer
     * measured in milliseconds.</p>
     *
     * @see getElapsedNanos
     * @return the total elapsed time in milliseconds
     */
    public long getElapsedMillis() {
	return getElapsedNanos() / 1_000_000;
    }

    /**
     * <p>Return the total elapsed time stored in the RunTimer
     * measured in seconds.</p>
     *
     * @see getElapsedNanos
     * @return the total elapsed time in seconds
     */
    public double getElapsedSecs() {
	return (double) getElapsedNanos() / 1_000_000_000;
    }
}
