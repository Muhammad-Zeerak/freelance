/**
 * Contains -> Flight number, cost and destination that will be available for the system
 * Can be used by other classes
 */
public class FlightRecords {
	static final String[] flightNumber = {"AB12", "CD34", "EF56", "GH78", "IJ90", "KL12", "MN34", "OP56", "QR78", "RS90"};
	private static final double[] flightCosts = {240, 340, 440, 540, 640, 740, 840, 940, 280, 230};
	private static final String[] flightDestinations = {"United States", "United Kingdom", "Dubai", "Australia", "Canada",
			"Austria", "Maldives", "France", "Brazil", "Portugal"};

	/**
	 *
	 * @param destination : the destination
	 * @return -> the index of this destination in the array
	 */
	private int getFlightIndex(String destination) {
		for(int i = 0; i < destination.length(); i++) {
			if(destination.equalsIgnoreCase(flightDestinations[i])) {
				return i;
			}
		}
		return -1;
	}

	/**
	 *
	 * @param destination : the destination
	 * @return -> the flight number if available else Not Assigned
	 */
	public String getFlightNumber(String destination) {
		int index = getFlightIndex(destination);
		return index == -1 ? "Not Assigned" : flightNumber[index];
	}

	/**
	 *
	 * @param destination : the destination
	 * @return -> cost of this flight if available else -1
	 */
	public double getFlightCost(String destination) {
		int index = getFlightIndex(destination);
		return index == -1 ? -1 : flightCosts[index];
	}

	/**
	 *
	 * @param destination : the destination
	 * @return -> true if available else false
	 */
	public boolean checkFlightDestination(String destination) {
		int index = getFlightIndex(destination);
		return index != -1;
	}
}