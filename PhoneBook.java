
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PhoneBook {
	// Data fields
	public Map<Character, BSFamilyTree> directory;
	
	
    /**
     	* Creates a new phone book with an empty directory.
     	*/
	public PhoneBook() {
	    directory = new HashMap<Character, BSFamilyTree>();
		String alphabet  = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    for (int i=0; i<26; i++) {
	    	BSFamilyTree empty = new BSFamilyTree();
	    	directory.put(alphabet.charAt(i), empty);
	    }
	}

	/*
	 * Returns the instance of BSFamilyTree at the indicated letter
	 * Must accept lowercase letters as well as uppercase letters
	 */
	public BSFamilyTree getFamilyTree(char letter) {
		if (Character.isLetter(letter)) {
			char keyy = Character.toUpperCase(letter);
			return directory.get(keyy);	
		}
		else {
			throw new IllegalArgumentException();
		}
	}

	/*
	 * Adds a FamilyTreeNode to the PhoneBook
	 */
	public void addFamily(String lastName) {
		directory.get(lastName.charAt(0)).addFamilyTreeNode(lastName);
	}

	/*
	 * Adds a Person to the PhoneBook
	 * If a FamilyTreeNode with the given last name doesn't currently exist, create the FamilyTreeNode
	 */
	public void addPerson(String lastName, String firstName, String phoneNumber) {
		if (directory.get(lastName.charAt(0)).doesFamilyExist(lastName) == false) {
			addFamily(lastName);
		}
		for (char i = 'A'; i<'['; i++) {
			if (directory.get(i).doesNumberExist(phoneNumber)) {
				throw new IllegalArgumentException("Number already exists");
			}
		}
		directory.get(lastName.charAt(0)).getFamilyTreeNode(lastName).addFamilyMember(lastName, firstName, phoneNumber);
	}

	/*
	 * Finds the phone number of a person
	 * Returns 'Does not exist.' if not found.
	 */
	public String getPhoneNumber(String lastName, String firstName) {
		if (directory.get(lastName.charAt(0)).doesFamilyExist(lastName)) {
			return directory.get(lastName.charAt(0)).getFamilyTreeNode(lastName).getPhoneNumberOfFamilyMember(lastName, firstName);
		}
		else {
			return "Does not exist.";
		}
	}

    	/**
     	* String representation of PhoneBook
     	*/
	public String toString() {
		StringBuilder s = new StringBuilder();
		ArrayList<Character> keys = new ArrayList<Character>(directory.keySet());
		for (int i=0; i<26; i++) {
			s.append(keys.get(i));
			s.append("\n");
			s.append(directory.get(keys.get(i)));
		}
		return s.toString();
	}
	
	public static void main(String[] args) {
		PhoneBook pbook = new PhoneBook();
		pbook.addFamily("Smith");
		pbook.addPerson("Smith", "Steves", "1234567890");
		pbook.addFamily("Samuel");
		pbook.addPerson("Samuel", "Steve", "1234567891");
		pbook.addPerson("Samuel", "Hi", "1234567892");
		pbook.addFamily("Son");
		pbook.addPerson("Son", "John", "1234567893");
		pbook.addFamily("Hello");
		pbook.addPerson("Hello", "Hi", "1234567894");
		pbook.addPerson("Son", "Sn", "1234567895");
		
		
		System.out.println(pbook);
		System.out.println(pbook.getPhoneNumber("Smiths", "Person"));
		
	}
}
