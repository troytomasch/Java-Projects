
import java.util.ArrayList;
import java.util.List;

public class FamilyTreeNode {
	// Data fields
	// TODO
	private String lastName;
	private List<Person> members;
	public FamilyTreeNode left;
	public FamilyTreeNode right;
	
	
	/**
     	* Constructor: instantializes a new FamilyTreeNode
     	* given a lastName
     	*/
	public FamilyTreeNode(String lastName) {
        this.lastName = lastName;
        members = new ArrayList<Person>();
        left = null;
        right = null;
	}

	/**
     	* Returns the last name of the FamilyTreeNode
     	*/
	public String getLastName() {
		return this.lastName;
	}

	/**
     	* Returns the arraylist of members in the FamilyTreeNode
     	*/
	public List<Person> getMembers() {
		return this.members;
	}

	/*
	 * Returns true if there is an instance of Person in the FamilyTreeNode that has
	 * the same first and last name provided Return false otherwise
	 */
	public boolean doesFamilyMemberExist(String lastName, String firstName) {
		ArrayList<Person> cmembers = new ArrayList<Person>(members);
		for (int i = 0; i<cmembers.size(); i++) {
			if (cmembers.get(i).getLastName() == lastName && cmembers.get(i).getFirstName() == firstName) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns true if there is an instance of Person in the FamilyTreeNode whose
	 * phone number matches the one provided Returns false otherwise
	 */
	public boolean doesNumberExist(String phoneNumber) {
		if (this.members.isEmpty()) {
			return false;
		}
		ArrayList<Person> cmembers = new ArrayList<Person>(members);
		for (int i = 0; i<cmembers.size(); i++) {
			if (cmembers.get(i).getPhoneNumber() == phoneNumber) {
				return true;
			}
		}
		return false;
	}

	/*
	 * Adds a Person to this FamilyTreeNode
	 * Throw an exception if the last name provided does not match the last name of the FamilyTreeNode
	 */
	public void addFamilyMember(String lastName, String firstName, String phoneNumber) {
		if (this.doesNumberExist(phoneNumber)) {
			throw new IllegalArgumentException("Number already exists");
		}
		if (this.doesFamilyMemberExist(lastName, firstName)) {
			throw new IllegalArgumentException("Family Member already exists");
		}
		if (this.getLastName() != lastName) {
			throw new IllegalArgumentException("Entering in wrong family");
		}
		else {
			Person newperson = new Person(lastName, firstName, phoneNumber);
			members.add(newperson);
		}
	}

	/**
	 * Adds a Person to this FamilyTreeNode
	 * Throw an exception if the last name provided does not match the last name of the FamilyTreeNode
	 */
	public void addFamilyMember(Person person) {
		addFamilyMember(person.getLastName(), person.getFirstName(), person.getPhoneNumber());
	}

	/*
	 * Returns the phone number of the person in the family with the given phone
	 * number Returns "Does not exist." if not found
	 */
	public String getPhoneNumberOfFamilyMember(String lastName, String firstName) {
		ArrayList<Person> cmembers = new ArrayList<Person>(members);
		for (int i = 0; i<cmembers.size(); i++) {
			if (cmembers.get(i).getLastName() == lastName && cmembers.get(i).getFirstName() == firstName) {
				return cmembers.get(i).getPhoneNumber();
			}
		}
		return "Does not exist.";
	}

	/*
	 * toString method Ex: [] [John Smith (5551234567), May Smith (5551234568),
	 * April Smith (5551234569), August Smith (5551234570)]
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("[");
		ArrayList<Person> cmembers = new ArrayList<Person>(members);
		for (int i = 0; i<cmembers.size(); i++) {
			s.append(cmembers.get(i));
			if (i != cmembers.size() - 1) {
				s.append(", ");		
			}
		}
		s.append("]");
		return s.toString();
	}
	
	public static void main(String[] args) {
//		FamilyTreeNode a = new FamilyTreeNode("Tomasch");
//		
//		a.addFamilyMember("Tomasch", "Troy", "1234567890");
//		a.addFamilyMember("Tomasch", "Mom", "1234567891");
//		a.addFamilyMember("Tomasch", "Dad", "1234567892");
//		Person jenna = new Person("Tomasch", "Jenna", "1234567895");
//		a.addFamilyMember(jenna);
//	
//		System.out.println(a);
//		System.out.println(a.doesFamilyMemberExist("Tomasch", "Troy"));
//		System.out.println(a.doesNumberExist("1234567890"));
//		System.out.println(a.doesFamilyMemberExist("Tomasch", "Jenna"));
//		System.out.println(a.doesNumberExist("1234567895"));
	}
}
