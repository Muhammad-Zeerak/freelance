import java.io.PrintWriter;
import java.util.Scanner;

/**
 * FlightBooking extends Booking class
 * Does not have any extra members
 */
public class FlightBooking extends Booking {
	/**
	 * Constructor -> calls the Booking constructor
	 *
	 * @param bookingID : booking id
	 * @param flightDestination : flightDestination of flight
	 * @param departureDate : date of departure
	 * @param passengerNames : name of passengers separated by ,
	 * @param numPassengers : total number of passengers
	 * @param totalCost : total cost of booking
	 * @param flightRecords : FlightRecords object
	 */
	public FlightBooking (String bookingID, String flightDestination, String departureDate, String passengerNames, int numPassengers, double totalCost, FlightRecords flightRecords) throws BookingException {
		super(bookingID, flightDestination,  departureDate, passengerNames, numPassengers, totalCost, flightRecords);
	}


	/**
	 * Constructor -> calls the Booking constructor
	 *
	 * @param input : Scanner object
	 */
	public FlightBooking (Scanner input) {
		super(input);
	}

	@Override
	public void printBookingDetails() {
		// call the Booking method
		super.printBookingDetails();
		System.out.println();
	}

	@Override
	public void printInvoiceDetails() {
		// call the Booking method
		super.printInvoiceDetails();
		// add extra details from this class
		System.out.printf("%21s%18s", ("$" + super.getPricePerPerson()), ("$" + super.getTotalCost()));
		System.out.println();
	}

	@Override
	public double calculateBookingCost(FlightRecords flightRecords) {
		// get Flight price
		double flightPrice = flightRecords.getFlightCost(super.getFlightDestination());
		// calculate booking price
		double totalBookingCost = (flightPrice * super.getNumPassengers());
		// set booking price and total cost
		super.setPricePerPerson(flightPrice);
		super.setTotalCost(totalBookingCost);
		// return the booking cost
		return totalBookingCost;
	}

	@Override
	public void writeDetailsToFile(PrintWriter pw) {
		// call the Booking class writeDetailsToFile method
		super.writeDetailsToFile(pw);
	}

}
