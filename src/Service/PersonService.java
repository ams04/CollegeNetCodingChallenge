package Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Model.Person;
import RepositoryStub.PersonRepository;

public class PersonService {

	private PersonRepository perRepo = new PersonRepository();

	public void addPersonToDirectory(String[] data) {

		List<Person> people = perRepo.getPeople();
		Person p = new Person();

		p.setFirstName(data[0].trim());
		;
		p.setLastName(data[1].trim());

		p.setPhoneNumbers(setPhoneNumber(data));
		p.setEmailIds(setEmailIds(data));

		people.add(p);

	}

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

	private List<String> setPhoneNumber(String[] data) {

		String regexStr = "\\d{3}-\\d{3}-\\d{4}";
		List<String> phoneNumbers = new ArrayList<>();

		for (String s : data) {
			if (s.trim().matches(regexStr))
				phoneNumbers.add(s.trim());
		}

		return phoneNumbers;
	}

	public Map<Person, String> findPersonByFirstAndLastName(String[] data) {

		Map<Person, String> map = new HashMap<>();
		List<Person> people = perRepo.getPeople();

		for (Person person : people) {
			String l = person.getLastName();
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

	public List<Person> findPersonByLastName(String[] data) {
		List<Person> people = perRepo.getPeople();
		List<Person> result = new ArrayList<>();

		for (Person person : people) {
			if (person.getFirstName().equals(data[0]))
				result.add(person);
		}

		if (result.size() == 0) {
			for (Person person : people) {
				if (person.getLastName().equals(data[0]))
					result.add(person);
			}
		}

		if (result.size() == 0)
			return null;
		else
			return result;

	}

}
