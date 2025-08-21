package contactservice;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


class ContactTest {

	@Test
    void testValidContactCreation() {
		/*
		 * Test Contact creation and ensure values are set and stored properly
		 */
        Result<Contact> result = Contact.Create("123", "John", "Doe", "1234567890", "123 Main St");
        assertTrue(result.valid);
        assertNotNull(result.object);
        assertEquals("123", result.object.getId());
        assertEquals("John", result.object.getFirstName());
        assertEquals("Doe", result.object.getLastName());
        assertEquals("1234567890", result.object.getPhone());
        assertEquals("123 Main St", result.object.getAddress());
        
        /*
         * Test edge cases with max field widths
         * (10 for id and names, 30 for address)
         */
        String tenChars = "abcdefghij";
        String thirtyChars = "X".repeat(30);
        result = Contact.Create(tenChars, tenChars, tenChars, "1234567890", thirtyChars);
        assertTrue(result.valid);
        assertNotNull(result.object);
        assertEquals(tenChars, result.object.getId());
        assertEquals(tenChars, result.object.getFirstName());
        assertEquals(tenChars, result.object.getLastName());
        assertEquals(thirtyChars, result.object.getAddress());
    }

    
    @Test
    void testInvalidId() {
    	/*
    	 *  Test to ensure the system rejects invalid ids
    	 */
    	
    	// testing null
        Result<Contact> result = Contact.Create(null, "John", "Doe", "1234567890", "123 Main St");
        assertFalse(result.valid);
        assertNull(result.object);

        // testing too long
        result = Contact.Create("X".repeat(11), "John", "Doe", "1234567890", "123 Main St");
        assertFalse(result.valid);
        assertNull(result.object);
    }

    @Test
    void testInvalidFirstName() {
    	 /*
    	  * Test to ensure the system does not create contact if firstName does not meet
    	  * the requirements
    	  */
    	
    	// testing null
        Result<Contact> result = Contact.Create("123", null, "Doe", "1234567890", "123 Main St");
        assertFalse(result.valid);
        assertNull(result.object);

        // testing too long
        result = Contact.Create("123", "X".repeat(11), "Doe", "1234567890", "123 Main St");
        assertFalse(result.valid);
        assertNull(result.object);
    }

    @Test
    void testInvalidLastName() {
    	/*
    	 * Test to ensure the system does not create contact if lastName
    	 * does not meet the requirements
    	 */
    	
    	// testing null
        Result<Contact> result = Contact.Create("123", "John", null, "1234567890", "123 Main St");
        assertFalse(result.valid);
        assertNull(result.object);

        // testing too long
        result = Contact.Create("123", "John", "X".repeat(11), "1234567890", "123 Main St");
        assertFalse(result.valid);
        assertNull(result.object);
    }

    @Test
    void testInvalidPhone() {
    	/*
    	 * Test to ensure the system does not create contact if phone
    	 * does not meet system requirements
    	 */
    	
    	// testing null
        Result<Contact> result = Contact.Create("123", "John", "Doe", null, "123 Main St");
        assertFalse(result.valid);
        assertNull(result.object);

        // testing too short
        result = Contact.Create("123", "John", "Doe", "12345", "123 Main St");
        assertFalse(result.valid);
        assertNull(result.object);

        // testing too long
        result = Contact.Create("123", "John", "Doe", "123456789012", "123 Main St");
        assertFalse(result.valid);
        assertNull(result.object);
    }

    @Test
    void testInvalidAddress() {
    	/*
    	 * Test to ensure the system does not create contact if address
    	 * does not meet system requirements
    	 */
    	
    	// testing null
        Result<Contact> result = Contact.Create("123", "John", "Doe", "1234567890", null);
        assertFalse(result.valid);
        assertNull(result.object);

        // testing too long
        result = Contact.Create("123", "John", "Doe", "1234567890", "X".repeat(31));
        assertFalse(result.valid);
        assertNull(result.object);
    }

    @Test
    void testMutators() {
    	/*
    	 * Test to ensure mutators work properly
    	 */
        Result<Contact> result = Contact.Create("123", "John", "Doe", "1234567890", "123 Main St");
        Contact contact = result.object;

        // ensure data is modified when valid
        assertTrue(contact.setFirstName("Jane"));
        assertTrue(contact.setLastName("Smith"));
        assertTrue(contact.setPhone("0987654321"));
        assertTrue(contact.setAddress("456 Elm St"));
        
        // ensure data is set and stored properly
        assertEquals("Jane", contact.getFirstName());
        assertEquals("Smith", contact.getLastName());
        assertEquals("0987654321", contact.getPhone());
        assertEquals("456 Elm St", contact.getAddress());
        
        /*
         * test edge cases with max field widths
         */
        String maxWidthName = "X".repeat(10);
        String maxWidthAddress = "X".repeat(30);
        
        // ensure data is modified when data is max length
        assertTrue(contact.setFirstName(maxWidthName));
        assertTrue(contact.setLastName(maxWidthName));
        assertTrue(contact.setAddress(maxWidthAddress));
        
        // ensure data is set and stored properly when data is max length
        assertEquals(maxWidthName, contact.getFirstName());
        assertEquals(maxWidthName, contact.getLastName());
        assertEquals(maxWidthAddress, contact.getAddress());
        
        // Ensure fields are not modified if data null or longer than max field width
        assertFalse(contact.setFirstName(null));
        assertFalse(contact.setFirstName("X".repeat(11)));
        
        assertFalse(contact.setLastName(null));
        assertFalse(contact.setLastName("X".repeat(11)));

        assertFalse(contact.setPhone(null));
        assertFalse(contact.setPhone("123")); // Too short
        assertFalse(contact.setPhone("1234567890123")); // Too long

        assertFalse(contact.setAddress(null));
        assertFalse(contact.setAddress("X".repeat(31)));
    }

    @Test
    void testIdImmutability() {
    	/*
    	 * Test to ensure id is immutable after initialization
    	 */
    	String id = "imutableID";
        Result<Contact> result = Contact.Create(id, "John", "Doe", "1234567890", "123 Main St");
        Contact contact = result.object;

        String originalId = contact.getId();

        // No setter exists - recheck the value to verify it remains unchanged
        assertEquals(id, originalId);
        assertEquals(originalId, contact.getId());
    }

}
