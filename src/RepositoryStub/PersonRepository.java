package RepositoryStub;

import java.util.ArrayList;
import java.util.List;

import Model.Person;

public class PersonRepository {

	private List<Person> people = new ArrayList<>();

	public List<Person> getPeople() {
		return people;
	}
}
