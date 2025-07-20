import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * BookingManagementSystem -> Simulates the flight booking and holiday booking.
 * 							  Assumes that the user enters the data correctly
 */
public class BookingManagementSystem {
	private static ArrayList<Booking> bookings = new ArrayList<>();
	private static Scanner input = new Scanner(System.in);
	private static FlightRecords flightRecords = new FlightRecords();
	private static Random random = new Random();

	////////////////////////////////////////////////////////////////////////
	///////////////                 Helper Methods
	///////////////////////////////////////////////////////////////////////
	/**
	 * Loads the data from file
	 */
	private static void loadFromFile() {
		Scanner fileScanner = null;
		try {
			fileScanner = new Scanner(new FileReader("bookingManagementData.txt"));
			while (fileScanner.hasNext()) {
				String type = fileScanner.nextLine();
				if (type.equals("FlightBooking")) {
					bookings.add(new FlightBooking(fileScanner));
				} else if (type.equals("HolidayBooking")) {
					bookings.add(new HolidayBooking(fileScanner));
				}
			}
		} catch (IOException e) {
			System.out.print(e.getMessage());
		} finally {
			fileScanner.close();
		}
	}

	/**
	 * Saves the data into a file
	 */
	public static void saveFile() {
		PrintWriter printWriter = null;
		try {
			printWriter = new PrintWriter(new FileWriter("bookingManagementData.txt"));
			for (int i = 0; i < bookings.size(); i++) {
				bookings.get(i).writeDetailsToFile(printWriter);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			printWriter.close();
		}
	}

	public static String getStringInput(String message) {
		System.out.print(message);
		return input.nextLine();
	}

	public static double getDoubleInput(String message) {
		System.out.print(message);
		return input.nextDouble();
	}

	public static int getIntInput(String message) {
		System.out.print(message);
		return input.nextInt();
	}

	/**
	 * Search for a Booking using ID
	 *
	 * @param bookingID : ID to be searched
	 * @return -> Booking object if found else throws an exception
	 */
	private static Booking searchBooking(String bookingID) throws BookingException {
		for (int i = 0; i < bookings.size(); i++) {
			if (bookings.get(i).getBookingID().equalsIgnoreCase(bookingID)) {
				return bookings.get(i);
			}
		}
		throw new BookingException("Booking with given ID does not exists...");
	}

	// type means 1 -> for invoice and 2 -> for Itinerary
	private static void flightInvoiceHeader(int type) {
		if (type == 1) {
			System.out.println("------------------------------------------------------------------");
			System.out.println("Booking ID\t\t" + "Destination\t\t" + "Price Per Flight\t\t" + "Total Cost");
			System.out.println("------------------------------------------------------------------");
		} else if (type == 2) {
			System.out.println("------------------------------------------------------------------------------------------");
			System.out.println("Booking ID\t\t" + "Destination\t\t" + "No. of Passengers\t\t" + "Flight Number\t\t" + "Departure Date\t\t");
			System.out.println("------------------------------------------------------------------------------------------");
		}
	}

	// type means 1 -> for invoice and 2 -> for Itinerary
	private static void holidayInvoiceHeader(int type) {
		if (type == 1) {
			System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("Booking ID\t\t" + "Destination\t\t" + "Price Per Flight\t\t" + "Price Per Night\t\t\t" + "Hotel Name\t\t" + "Number of Nights\t\t" + "Total Cost");
			System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
		} else if (type == 2) {
			System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("Booking ID\t\t" + "Destination\t\t" + "No. of Passengers\t\t" + "Flight Number\t\t" + "Departure Date\t\t\t" + "Hotel Name\t\t" + "Total Stay\t\t" + "Check In Date");
			System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
		}
	}

	////////////////////////////////////////////////////////////////////////
	///////////////                 Required Methods
	///////////////////////////////////////////////////////////////////////
	public static void menu() {
		System.out.println("***********************************************************");
		System.out.println("*******        My Booking Management Company        *******");
		System.out.println("***********************************************************");
		System.out.println("A. Book a Flight");
		System.out.println("B. Book a Holiday");
		System.out.println("C. View Invoice");
		System.out.println("D. Print Itinerary");
		System.out.println("E. View Bookings");
		System.out.println("F. Update Check in Details");
		System.out.println("X. Exit\n");
	}

	/**
	 * Book a Flight -> throws BookingException if any error occurs during process
	 */
	private static void flightBooking() {
		// Take input from user
		String flightDestination = getStringInput("Enter Destination: ").toUpperCase();
		String departureDate = getStringInput("Enter departure date (DD/MM/YYYY): ");
		int numPassengers = getIntInput("How many passengers will be travelling? ");
		input.nextLine();

		// build the names string
		String passengerNames = "";
		for (int i = 0; i < numPassengers; i++) {
			passengerNames += getStringInput("Enter name of Passenger " + (i + 1) + ": ");
			// Validate the input to not be empty
			while (passengerNames.isEmpty()) {
				System.out.println();
				System.out.println("Error! Please enter a valid name....");
				passengerNames += getStringInput("Enter name of Passenger " + (i + 1) + ": ");
			}
			// add a separator with the passenger name
			passengerNames += ", ";
		}

		// generate a random bookingId
		String bookingID = String.valueOf(random.nextInt(32741009));

		// now create a Booking object from the data
		Booking bookingObject;
		try {
			bookingObject = new FlightBooking(bookingID, flightDestination, departureDate, passengerNames, numPassengers, 0.0, flightRecords);
			// add this to bookings
			bookings.add(bookingObject);

			// calculate booking cost
			bookingObject.calculateBookingCost(flightRecords);

			// print success message
			System.out.println("\nYour flight has been successfully booked...");
			System.out.println("Total Cost to pay -> $" + bookingObject.getTotalCost());
		} catch (BookingException e) {
			System.out.println("\n" + e.getMessage());
		}
	}

	/**
	 * Book a Holiday -> throws BookingException if any error occurs during process
	 */
	private static void holidayBooking() {
		// Take input from user
		String flightDestination = getStringInput("Enter Destination: ").toUpperCase();
		String departureDate = getStringInput("Enter departure date (DD/MM/YYYY): ");
		int numPassengers = getIntInput("How many passengers will be travelling? ");
		input.nextLine();

		// build the names string
		String passengerNames = "";
		for (int i = 0; i < numPassengers; i++) {
			passengerNames += getStringInput("Enter name of Passenger " + (i + 1) + ": ");
			// Validate the input to not be empty
			while (passengerNames.isEmpty()) {
				System.out.println();
				System.out.println("Error! Please enter a valid name....");
				passengerNames += getStringInput("Enter name of Passenger " + (i + 1) + ": ");
			}
			// add a separator with the passenger name
			passengerNames += ", ";
		}

		// generate a random bookingId
		String bookingID = String.valueOf(random.nextInt(32741009));

		// take other inputs
		String hotelName = getStringInput("Enter name of Hotel: ");
		String checkInDate = getStringInput("Enter Check in date (in DD/MM/YYYY): ");
		int numNights = getIntInput("How many nights you will be staying? ");
		input.nextLine();
		double pricePerNight = getDoubleInput("Enter rate of staying a night at hotel: ");
		input.nextLine();

		// now create a Booking object from the data
		Booking bookingObject;
		try {
			bookingObject = new HolidayBooking(bookingID, flightDestination, departureDate, passengerNames, numPassengers, 0.0, flightRecords, pricePerNight, numNights, checkInDate, hotelName);
			// add this to bookings
			bookings.add(bookingObject);

			// calculate booking cost
			bookingObject.calculateBookingCost(flightRecords);

			// print success message
			System.out.println("\nYour flight has been successfully booked...");
			System.out.println("Hotel is also booked for " + ((HolidayBooking) bookingObject).getNumNights() + " nights...");
			System.out.println("Total Cost to pay -> $" + bookingObject.getTotalCost());
		} catch (BookingException e) {
			System.out.println("\n" + e.getMessage());
		}
	}

	/**
	 * Print the invoice details to the console
	 */
	private static void viewInvoice() {
		String bookingID = getStringInput("Please enter Booking ID: ");

		// search for the object in data
		Booking bookingObject;
		try {
			bookingObject = searchBooking(bookingID);
			// if it is Flight Booking
			if(bookingObject instanceof FlightBooking) {
				System.out.println("\n\nPassenger Name(s) -> " + bookingObject.getPassengerNames());
				flightInvoiceHeader(1);
				bookingObject.printInvoiceDetails();
			} else {
				System.out.println("\n\nPassenger Name(s) -> " + bookingObject.getPassengerNames());
				holidayInvoiceHeader(1);
				bookingObject.printInvoiceDetails();
			}
		} catch (BookingException e) {
			System.out.println();
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Print the Itinerary details to the console
	 */
	private static void printItinerary() {
		String bookingID = getStringInput("Please enter Booking ID: ");

		// search for the object in data
		Booking bookingObject;
		try {
			bookingObject = searchBooking(bookingID);
			// if it is Flight Booking
			if(bookingObject instanceof FlightBooking) {
				System.out.println("\n\nPassenger Name(s) -> " + bookingObject.getPassengerNames());
				flightInvoiceHeader(2);
				bookingObject.printBookingDetails();
			}
			if (bookingObject instanceof HolidayBooking) {
				System.out.println("\n\nPassenger Name(s) -> " + bookingObject.getPassengerNames());
				holidayInvoiceHeader(2);
				bookingObject.printBookingDetails();
			}
		} catch (BookingException e) {
			System.out.println();
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Print all Booking details
	 */
	private static void viewBookings() {
		System.out.println("Flight Bookings:");
		flightInvoiceHeader(2);
		for (int i = 0; i < bookings.size(); i++) {
			if(bookings.get(i) instanceof FlightBooking) {
				bookings.get(i).printBookingDetails();
			}
		}
		System.out.println();
		System.out.println("Holiday Bookings:");
		holidayInvoiceHeader(2);
		for (int i = 0; i < bookings.size(); i++) {
			if(bookings.get(i) instanceof HolidayBooking) {
				bookings.get(i).printBookingDetails();
			}
		}
		System.out.println();
	}

	/**
	 * Updates number of nights of stay
	 */
	private static void updateCheckInDate() {
		String bookingID = getStringInput("Please enter Booking ID: ");

		// search for the object in data
		Booking bookingObject;
		try {
			bookingObject = searchBooking(bookingID);
			// if it is Flight Booking
			if(bookingObject instanceof FlightBooking) {
				System.out.println("Only allowed for Holiday Booking...");
			} else {
				int numNights = getIntInput("How many days you want to stay now? ");
				input.nextLine();
				// set the new nights
				((HolidayBooking) bookingObject).setNumNights(numNights);
				// calculate new booking cost
				bookingObject.calculateBookingCost(flightRecords);
				System.out.println("New Total Cost to pay -> $" + bookingObject.getTotalCost());
			}
		} catch (BookingException e) {
			System.out.println();
			System.out.println(e.getMessage());
		}
	}

	// Driver program for Booking Management System
	public static void main(String[] args) {
		// Load to see if there is already a saved file in system
		File file = new File("bookingManagementData.txt");
		// if file is present in system then load data from it
		if (file.exists()) {
			loadFromFile();
			System.out.println("Data loaded success!\n");
		}
		// if the file is not present
		else {
			System.out.println("No data found. Please save some data first.\n");
		}
		String choice;
		// A menu-driven system until the user exits the program
		do {
			// display menu and take input from user
			menu();
			choice = getStringInput("Enter choice: ");
			System.out.println();

			// Make sure user enters only a single length input
			if(choice.length() != 1) {
				System.out.println("Invalid input. Please try again.");
			}
			else {
				switch(choice.toUpperCase()) {
				case "A":
					flightBooking();
					break;
				case "B":
					holidayBooking();
					break;
				case "C":
					viewInvoice();
					break;
				case "D":
					printItinerary();
					break;
				case "E":
					viewBookings();
					break;
				case "F":
					updateCheckInDate();
					break;
				case "X":
					// on exit first save the data to a file
					saveFile();
					System.out.println("GoodBye! Thank you for using our System.");
					break;
				default:
					System.out.println("Invalid input. Please try again.");
				}
			}
			System.out.println();
		} while (!choice.equalsIgnoreCase("X"));
	}
}