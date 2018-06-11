package Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Model.Person;
import RepositoryStub.PersonRepository;
import Util.Constants;

/**
 * @author Akshay
 * 
 * This class serves as the service layer for the application, processing all the input data.
 *
 */
public class PersonService {

	private PersonRepository perRepo = new PersonRepository();

	/**
	 * @param data the data entered by the user.
	 * @return return a string indicating the result of this method. "" string indicates the data is erroneous.
	 */
	public String addPersonToDirectory(String[] data) {

		List<Person> people = perRepo.getPeople();
		Person p = new Person();

		p.setFirstName(data[0].trim());
		
		p.setLastName(data[1].trim());

		if(setPhoneNumber(data).size() ==0)
			return Constants.INVALID_PHONE_NUMBER;
		p.setPhoneNumbers(setPhoneNumber(data));
		
		if(setEmailIds(data).size()==0)
			return Constants.INVALID_EMAIL;
		p.setEmailIds(setEmailIds(data));

		people.add(p);

		return "";
	}

	/**
	 * @param data the data entered by the user.
	 * @return processes the data and returns the corresponding result.
	 */
	private List<String> setEmailIds(String[] data) {

		String emailRegExStr = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";

		List<String> emailIds = new ArrayList<>();

		for (String s : data) {
			if (s.trim().matches(emailRegExStr))
				emailIds.add(s.trim());
		}

		return emailIds;
	}

	/**
	 * @param data the data entered by the user.
	 * @return processes the data and returns the corresponding result.
	 */
	private List<String> setPhoneNumber(String[] data) {

		String regexStr = "\\d{3}-\\d{3}-\\d{4}";
		List<String> phoneNumbers = new ArrayList<>();

		for (String s : data) {
			if (s.trim().matches(regexStr))
				phoneNumbers.add(s.trim());
		}

		return phoneNumbers;
	}

	/**
	 * @param data the data entered by the user.
	 * @return a {@Map} of Person and String to indicate which object has been found and whether it is found by first name or last name.
	 */
	public Map<Person, String> findPersonByFirstAndLastName(String[] data) {

		Map<Person, String> map = new HashMap<>();
		List<Person> people = perRepo.getPeople();

		for (Person person : people) {
			if (person.getFirstName().equalsIgnoreCase(data[0]))
				map.put(person, "first");
			else if (person.getLastName().equalsIgnoreCase(data[0]))
				map.put(person, "last");
			else
				continue;
		}

		if (map.size() == 0)
			return null;
		else
			return map;

	}

}
