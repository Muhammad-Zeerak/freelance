import java.io.PrintWriter;
import java.util.Scanner;

/**
 * An abstract class Booking
 */
public abstract class Booking {
	// instance variables
	private String bookingID;				// booking id
	private String flightDestination;		// destination of flight
	private String flightNumber;			// flight id/number
	private String departureDate;			// departure date
	private int numPassengers;				// number of passengers
	private String passengerNames;			// name of passengers separated by ,
	private double totalCost;				// total cost of booking
	private double pricePerPerson;			// price per person

	/**
	 * Constructor -> if passed destination not in FlightRecords then throw a Booking Exception
	 *
	 * @param bookingID : booking id
	 * @param flightDestination : destination of flight
	 * @param departureDate : date of departure
	 * @param passengerNames : name of passengers separated by ", "
	 * @param numPassengers : total number of passengers
	 * @param totalCost : total cost of booking
	 * @param flightRecords : FlightRecords object
	 */
	public Booking(String bookingID, String flightDestination, String departureDate, String passengerNames, int numPassengers, double totalCost, FlightRecords flightRecords) throws BookingException {
		// if destination in FlightRecords then initialize the variables
		if (flightRecords.checkFlightDestination(flightDestination)) {
			this.bookingID = bookingID;
			this.flightDestination = flightDestination;
			this.flightNumber = flightRecords.getFlightNumber(flightDestination);
			this.departureDate = departureDate;
			this.numPassengers = numPassengers;
			this.passengerNames = passengerNames;
			this.totalCost = totalCost;
		}
		// else throw a Booking Exception
		else {
			throw new BookingException("Error -> Flight Destination not valid!");
		}
	}

	/**
	 * Constructor -> object can be created from a file directly using this
	 *
	 * @param input : Scanner object
	 */
	public Booking(Scanner input) {
		this.bookingID = input.nextLine();
		this.flightDestination = input.nextLine();
		this.flightNumber = input.nextLine();
		this.departureDate = input.nextLine();
		this.passengerNames = input.nextLine();
		this.numPassengers = Integer.parseInt(input.nextLine());
		this.totalCost = Double.parseDouble(input.nextLine());
		this.pricePerPerson = Double.parseDouble(input.nextLine());
	}

	/**
	 * All Accessors
	 */
	public String getBookingID() {
		return this.bookingID;
	}
	public String getFlightDestination() {
		return this.flightDestination;
	}
	public int getNumPassengers() {
		return this.numPassengers;
	}
	public double getTotalCost() {
		return this.totalCost;
	}
	public String getPassengerNames() {
		return this.passengerNames;
	}
	public double getPricePerPerson() {
		return this.pricePerPerson;
	}

	/**
	 * All Mutators
	 */
	public void setTotalCost(double bookingCost) {
		this.totalCost = bookingCost;

	}
	public void setPricePerPerson(double pricePerPerson) {
		this.pricePerPerson = pricePerPerson;

	}

	/**
	 * A method to calculate Booking cost
	 * Will be implemented by classes which extend this class
	 */
	public abstract double calculateBookingCost(FlightRecords flightRecords);

	/**
	 * A method to print Booking details
	 */
	public void printBookingDetails() {
		System.out.printf("%10s%17s%22d%20s%21s",this.bookingID  ,this.flightDestination, this.numPassengers , this.flightNumber  , this.departureDate);
	}

	/**
	 * Helper to print invoice details
	 */
	public void printInvoiceDetails() {
		System.out.printf("%10s%17s", this.bookingID, this.flightDestination);
	}

	/**
	 * Writes the required data to a file in one line each. File can be used later to read too
	 *
	 * @param printWriter : a PrintWrite object
	 */
	public void writeDetailsToFile(PrintWriter printWriter) {
		printWriter.println(getClass().getSimpleName());
		printWriter.println(this.bookingID);
		printWriter.println(this.flightDestination);
		printWriter.println(this.flightNumber);
		printWriter.println(this.departureDate);
		printWriter.println(this.passengerNames);
		printWriter.println(this.numPassengers);
		printWriter.println(this.totalCost);
		printWriter.println(this.pricePerPerson);
	}
}