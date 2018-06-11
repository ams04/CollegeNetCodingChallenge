import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

import Model.Person;
import Service.PersonService;
import Util.Constants;

public class Main {

	public static void main(String[] args) {

		PersonService service = new PersonService();

		String command;
		Scanner src = new Scanner(System.in);
		System.out.println("Format for adding contact: add First Name, LastName, Phone Number, Email ID");
		System.out.println("Format for finding contact: find First Name/Last Name");
		System.out.println("Quiting the application: Enter quit");
		System.out.println("Enter your command");

		CollectData: while (true) {
			String[] input = src.nextLine().trim().split(" ", 2);
			/*
			 * General logic flow: Whenever a command is entered, it is checked whether if it's for adding a contact or finding a contact.
			 * Invalid data gets automatically handled and asks the user to enter data again. Once data entered is in a valid format, it is passed
			 * on to the service layer where it is validated and added to the data stub. Once everything is successful the user gets the
			 * corresponding message saying whether the data is successfully processed or needs to be entered again.
			 */
			if (input[0].equals(Constants.ADD_LABEL) || input[0].equals(Constants.FIND_LABEL)) {
				if (input.length < 2) {
					System.out.println("Enter following data to add: First Name, Last Name, Phone Numbers and Email Ids");
					System.out.println("Enter Another command");

				} else if (Arrays.copyOfRange(input, 1, input.length)[0].split(",").length < 4 && input[0].equalsIgnoreCase("add")) {
					System.out.println("Invalid format");
					System.out.println("Please enter data in following format: First Name, Last Name, Phone Numbers and Email Ids");

					System.out.println("Enter Another command");

				} else {
					String data = Arrays.copyOfRange(input, 1, input.length)[0];
					String[] personData = data.split(",");
					command = input[0];

					switch (command.toLowerCase()) {

					case "add":
						if (personData.length < 4)
							System.out.println(
									"Enter data in following format to add a contact:\n First Name, Last Name, Phone Numbers and Email Ids");
						else {
							String status = service.addPersonToDirectory(personData);
							if (status.equals(Constants.INVALID_EMAIL)) {

								System.out.println(status + " .Please enter a valid email id");
								System.out.println("Enter Another command");
								break;
							} else if (status.equals(Constants.INVALID_PHONE_NUMBER)) {
								System.out.println(status + " .Please enter phone number in format XXX-XXX-XXXX");
								System.out.println("Enter Another command\n");
								break;
							} else {
								System.out.println("Added " + personData[0] + " " + personData[1]);
								System.out.println("Enter Another command\n");

								break;}
						}

					case "find":
						Map<Person, String> result = service.findPersonByFirstAndLastName(personData);
						if (result != null) {
							result.forEach((k, v) -> System.out.println(k.getFirstName() + " " + k.getLastName()
									+ " - Found by " + v.toUpperCase() + " name in directory."));
							System.out.println("Enter another command:");

						} else
							System.out.println("Sorry, no records with name: " + personData[0]);

						break;

					default:
						System.out.println(
								"\nInvalid Input!! Please indicate if you wish to add or search a contact\nEnter Another command");
						break;
					}
				}
			}
			else if(input[0].equals(Constants.QUIT_LABEL))
				break CollectData;
			else {
				System.out.println(
						"\nInvalid Input!! Please indicate if you wish to add or search a contact\nEnter Another command");
			
			}

		}
		src.close();
		System.out.println("Application Terminated");
	}
}
