package RepositoryStub;

import java.util.ArrayList;
import java.util.List;

import Model.Person;

/**
 * @author Akshay
 *
 *This class acts as the data stub for the application
 */
public class PersonRepository {

	private List<Person> people = new ArrayList<>();

	public List<Person> getPeople() {
		return people;
	}
}
