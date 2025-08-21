package contactservice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContactServiceTest {

    private ContactService service;

    @BeforeEach
    void setUp() {
        service = new ContactService();
    }

    @Test
    void testUniqueId() {
    	/*
    	 * Test to ensure contact service can add contacts with unique ids
    	 */
        assertTrue(service.addContact("1234", "John", "Doe", "1234567890", "123 Main St"));
    }

    @Test
    void testAddDuplicateId() {
    	/*
    	 * Test to ensure contact service does not add contacts with duplicate ids
    	 */
        assertTrue(service.addContact("123", "John", "Doe", "1234567890", "123 Main St"));
        assertFalse(service.addContact("123", "Jane", "Smith", "0987654321", "456 Elm St"));
    }

    @Test
    void testRemoveExistingContact() {
    	/*
    	 * Test to ensure contact service can remove existing contacts
    	 * using contact id
    	 */
        service.addContact("123", "John", "Doe", "1234567890", "123 Main St");
        assertTrue(service.removeContact("123"));
    }
    
    @Test
    void testRemoveNonExistantContact() {
    	/*
    	 * Test to ensure contact service does nothing when attempting to remove
    	 * a contact that does not exist
    	 */
    	assertFalse(service.removeContact("9999999999"));
    }    
    
    @Test
    void testUpdateFirstNameById() {
    	/*
    	 * Test to ensure contact service can update first name
    	 * using contact id
    	 */    	
    	service.addContact("12345", "John", "Doe", "1234567890", "123 Main St");
        assertTrue(service.updateFirstName("12345", "Jane"));
    }
    
    @Test
    void testUpdateLastNameById() {
    	/*
    	 * Test to ensure contact service can update last name
    	 * using contact id
    	 */
    	service.addContact("12345", "John", "Doe", "1234567890", "123 Main St");
    	assertTrue(service.updateLastName("12345", "Smith"));
    }
    
    @Test
    void testUpdateNumberById() {
    	/*
    	 * Test to ensure contact service can update Number
    	 * using contact id
    	 */
    	service.addContact("12345", "John", "Doe", "1234567890", "123 Main St");
    	assertTrue(service.updateNumber("12345", "0987654321"));
    }
    
    @Test
    void testUpdateAddressById() {
    	/*
    	 * Test to ensure contact service can update address
    	 * using contact id
    	 */
    	service.addContact("12345", "John", "Doe", "1234567890", "123 Main St");
    	assertTrue(service.updateAddress("12345", "456 Elm St"));
    }
    
    @Test
    void testUpdateNonExistentContact() {
    	/*
    	 * Test to ensure contact service does nothing when attempting
    	 * to update a non-existent contact
    	 */
        assertFalse(service.updateFirstName("999", "Test"));
        assertFalse(service.updateLastName("999", "Test"));
        assertFalse(service.updateNumber("999", "1111111111"));
        assertFalse(service.updateAddress("999", "Nowhere"));
    }
}

