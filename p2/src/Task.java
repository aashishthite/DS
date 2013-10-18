/**
 * A class representing tasks in the queue. Each task has a name
 * and a duration value (in minutes)
 * 
 * <b>Do not modify this file in any way!</b>
 */
class Task {
	private String name;    // The name of the task
	private int duration;   // The duration of the task

	/**
	 * Constructs a new task with the given name and duration.
	 * @param name the name of the task
	 * @param duration the duration of the class (in minutes)
	 */
	public Task(String name, int duration) {
		this.name = name;
		this.duration = duration;
	}

	/**
	 * Returns the task's name
	 * @return the task's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the task's duration
	 * @return the task's duration
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * Sets the duration to the given new value.
	 * @param duration the new duration value
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**
	 * Returns a formatted string with the name and duration
         * @return a string with the name and duration
         */
	public String toString() {
		return this.name + ", " + this.duration;
	}
}
