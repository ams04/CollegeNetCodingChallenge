import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

import Model.Person;
import Service.PersonService;

public class Main {

	public static void main(String[] args) {

		PersonService service = new PersonService();

		String command;
		Scanner src = new Scanner(System.in);
		System.out.println("Enter your command");

		CollectData: while (true) {
			String[] input = src.nextLine().trim().split(" ", 2);
			if (input.length < 2) {
				System.out.println("Enter following data: First Name, Last Name, Phone Numbers and Email Ids");
				System.out.println("Enter Another command");
			} else if (Arrays.copyOfRange(input, 1, input.length)[0].split(",").length < 4
					&& input[0].equalsIgnoreCase("add")) {
				System.out.println("Invalid format");
				System.out.println(
						"Please enter data in following format: First Name, Last Name, Phone Numbers and Email Ids");
				System.out.println("Enter Another command");
			} else {
				String data = Arrays.copyOfRange(input, 1, input.length)[0];
				String[] personData = data.split(",");
				command = input[0];

				switch (command.toLowerCase()) {

				case "add":
					service.addPersonToDirectory(personData);
					if (personData.length < 4)
						System.out.println(
								"Enter following data to add a contact:/n First Name, Last Name, Phone Numbers and Email Ids");
					else {
						System.out.println("Added " + personData[0] + " " + personData[1]);
						break;
					}

				case "find":
					Map<Person, String> result = service.findPersonByFirstAndLastName(personData);
					if (result != null) {
						result.forEach((k, v) -> System.out.println(k.getFirstName() + " " + k.getLastName()
								+ " - Found by " + v.toUpperCase() + " name in directory."));

					} else
						System.out.println("Sorry, no records with name: " + personData[0]);

					break;

				case "quit":
					System.out.println("Application Terminated!!");
					break CollectData;

				default:
					System.out.println("Invalid Input!! Please indicate if you wish to add or search a contact");
					System.out.println("Enter Another command");
					break;
				}
			}
		}
		src.close();
	}

}
