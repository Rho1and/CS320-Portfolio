package contactservice;

import java.util.HashMap;
import java.util.Map;

public class ContactService {

	/*
	 * The contact service shall be able to add contacts with a unique ID.
	 * The contact service shall be able to delete contacts per contact ID.
	 * The contact service shall be able to update contact fields per contact ID. The following fields are updatable:
		* firstName
		* astName
		* Number
		* Address
	 */
	
	private static Map<String, Contact> contactList;
	private static boolean initialized;
	
	// Initializer
	private static void initializeClass () {
		contactList = new HashMap<String, Contact>();
		initialized = true;
	}
	
	// Constructor
	public ContactService() {
		if (!ContactService.initialized) 
			initializeClass();		
		
	}
	
	/*
	 * Adds a contact with a unique id
	 * If id is not unique, contact is not created
	 */
	public boolean addContact(String id, String firstName, String lastName, String number, String address) {
		// if id is not unique, do not create a new contact
		if (contactList.containsKey(id)) {
			return false;
		}
			
		
		Result<Contact> result = Contact.Create(id, firstName, lastName, number, address);
		if (result.valid) {
			contactList.put(id, result.object);
		}
		
		return result.valid;
	}
	
	/*
	 * Removes a contact by looking up contact id
	 * does nothing if id does not exist
	 */
	public boolean removeContact(String id) {
		if (contactList.containsKey(id)) {
			contactList.remove(id);
			return true;
		}
		return false;
	}
	
	/*
	 * Updates contact firstName if contact exists and data is valid
	 */
	public boolean updateFirstName(String id, String name) {
		if (contactList.containsKey(id)) {
			return contactList.get(id).setFirstName(name);
		}
		return false;
	}
	
	/*
	 * Updates contact lastName if contact exists and data is valid
	 */
	public boolean updateLastName(String id, String name) {
		if (contactList.containsKey(id)) {
			return contactList.get(id).setLastName(name);
		}
		return false;
	}
	
	/*
	 * Updates contact phone if contact exists and data is valid
	 */
	public boolean updateNumber(String id, String number) {
		if (contactList.containsKey(id)) {
			return contactList.get(id).setPhone(number);			
		}
		return false;
	}
	
	/*
	 * Updates contact address if contact exists and data is valid
	 */
	public boolean updateAddress(String id, String address) {
		if (contactList.containsKey(id)) {
			 return contactList.get(id).setAddress(address);			 
		}
		return false;
	}
}
