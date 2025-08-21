package contactservice;


public class Contact {
	
	/*
	 * The contact object shall have a required unique contact ID string that cannot be longer than 10 characters. The contact ID shall not be null and shall not be updatable.
     * The contact object shall have a required firstName String field that cannot be longer than 10 characters. The firstName field shall not be null.
     * The contact object shall have a required lastName String field that cannot be longer than 10 characters. The lastName field shall not be null.
     * The contact object shall have a required phone String field that must be exactly 10 digits. The phone field shall not be null.
     * The contact object shall have a required address field that must be no longer than 30 characters. The address field shall not be null.
	 * 
	 */
	
	private static final int idWidth = 10;
	private static final int nameWidth = 10;
	private static final int phoneLength = 10;
	private static final int addressWidth = 30;
	
	private final String id;
	private String firstName;
	private String lastName;
	private String phone;
	private String address;
	
	
	// Creation Factory
	public static Result<Contact> Create(String id, String firstName, String lastName, String phone, String address) {
		// Create new result
		Result<Contact> result = new Result<Contact>();
		
		/*
		 * Validate user fields.
		 * If any field is invalid, exit contact creation
		 */
		result.valid = id != null && id.length() <= idWidth;
		if (!result.valid) return result; else result.valid = firstName != null && firstName.length() <= nameWidth;
		if (!result.valid) return result; else result.valid = lastName != null && lastName.length() <= nameWidth;
		if (!result.valid) return result; else result.valid = phone != null && phone.length() == phoneLength;
		if (!result.valid) return result; else result.valid = address != null && address.length() <= addressWidth;
		
		if (result.valid) {
			result.object = new Contact(id, firstName, lastName, phone, address);
		}
		
		return result;
	}
	
	// Private Constructor
	private Contact(String id, String firstName, String lastName, String phone, String address) {
		this.id = id;
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setPhone(phone);
		this.setAddress(address);
		
	}
	
	// Mutators	
	public boolean setFirstName(String firstName) {
		if (firstName != null && firstName.length() <= nameWidth) {
			this.firstName = firstName;
			return true;
		}
		return false;		
	}
	
	public boolean setLastName(String lastName) {
		if (lastName != null && lastName.length() <= nameWidth) {
			this.lastName = lastName;
			return true;
		}
		return false;		
	}
	
	public boolean setPhone(String phone) {
		if (phone != null && phone.length() == phoneLength) {
			this.phone = phone;
			return true;
		}
		return false;
	}
	
	public boolean setAddress(String address) {
		if (address != null && address.length() <= addressWidth) {
			this.address = address;
			return true;
		}
		return false;		
	}
	
	// Accessors
	public String getId() {
		return this.id;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public String getPhone() {
		return this.phone;
	}
	
	public String getAddress() {
		return this.address;
	}
}

