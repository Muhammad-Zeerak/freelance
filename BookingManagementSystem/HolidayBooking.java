import java.io.PrintWriter;
import java.util.Scanner;

/**
 * HolidayBooking extends Booking class
 * Have 4 extra members
 */
public class HolidayBooking extends Booking {
	private double pricePerNight;			// price of place where accommodation is done per night
	private int numNights;					// number of nights to stay
	private String checkInDate;				// date to check in
	private String hotelName;				// name of hotel where stay

	/**
	 * Constructor -> calls the Booking constructor and sets the extra variables
	 *
	 * @param bookingID : booking id
	 * @param flightDestination : flightDestination of flight
	 * @param departureDate : date of departure
	 * @param passengerNames : name of passengers separated by ,
	 * @param numPassengers : total number of passengers
	 * @param totalCost : total cost of booking
	 * @param flightRecords : FlightRecords object
	 * @param pricePerNight : price per night of hotel where stay
	 * @param numNights : number of nights of stay
	 * @param checkInDate : date when checked in
	 * @param hotelName : name of hotel
	 */
	public HolidayBooking(String bookingID, String flightDestination, String departureDate, String passengerNames, int numPassengers, double totalCost, FlightRecords flightRecords,
						  double pricePerNight, int numNights, String checkInDate, String hotelName) throws BookingException {
		super(bookingID, flightDestination, departureDate, passengerNames, numPassengers, totalCost, flightRecords);
		// throw an exception if number of nights is less than 0
		if(numNights >= 1) {
			this.pricePerNight = pricePerNight;
			this.numNights = numNights;
			this.checkInDate = checkInDate;
			this.hotelName = hotelName;
		} else {
			throw new BookingException("The minimum stay limit is 1 night!");
		}
	}

	/**
	 * Constructor -> calls the Booking constructor
	 *
	 * @param input : Scanner object
	 */
	public HolidayBooking(Scanner input) {
		super(input);
		this.pricePerNight = Double.parseDouble(input.nextLine());
		this.numNights = Integer.parseInt(input.nextLine());
		this.checkInDate = input.nextLine();
		this.hotelName = input.nextLine();
	}

	/**
	 * Accessors
	 */
	public int getNumNights() {
		return this.numNights;
	}

	/**
	 * Mutators
	 */
	public void setNumNights(int numNights) {
		this.numNights = numNights;
	}

	@Override
	public void printBookingDetails() {
		// call the Booking method
		super.printBookingDetails();
		// add extra details
		System.out.printf("%20s", this.hotelName);
		System.out.printf("%16d", this.numNights);
		System.out.printf("%19s", this.checkInDate);
		System.out.println();
	}

	@Override
	public void printInvoiceDetails() {
		// call the Booking method
		super.printInvoiceDetails();
		// add extra details
		System.out.printf("%21s%23s%19s%22d%18s" , ("$" + super.getPricePerPerson()), ("$" + this.pricePerNight), this.hotelName, this.numNights , ("$" + super.getTotalCost()));
		System.out.println();
	}

	@Override
	public double calculateBookingCost(FlightRecords flightRecords) {
		// set price per person
		super.setPricePerPerson(flightRecords.getFlightCost(super.getFlightDestination()));
		// calculate booking cost
		double tempBookingCost = (flightRecords.getFlightCost(super.getFlightDestination()) * super.getNumPassengers() + (this.pricePerNight * this.numNights));
		// set booking cost and return it
		super.setTotalCost(tempBookingCost);
		return tempBookingCost;
	}

	@Override
	public void writeDetailsToFile(PrintWriter printWriter) {
		// call the Booking class method
		super.writeDetailsToFile(printWriter);
		// write extra details
		printWriter.println(this.pricePerNight);
		printWriter.println(this.numNights);
		printWriter.println(this.checkInDate);
		printWriter.println(this.hotelName);
	}
}
