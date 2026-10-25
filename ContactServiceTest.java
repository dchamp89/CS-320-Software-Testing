package contact;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ContactServiceTest {

    private ContactService service;
    private Contact contact;

    @BeforeEach
    void setUp() {
        service = new ContactService();
        contact = new Contact("1", "John", "Smith", "1234567890", "123 Main Street");
    }

    // ---------- Requirement: add contacts with a unique ID ----------

    @Test
    void testAddContact() {
        assertDoesNotThrow(() -> service.addContact(contact));
    }

    @Test
    void testAddDuplicateContactID() {
        service.addContact(contact);
        Contact duplicate = new Contact("1", "Jane", "Doe", "0987654321", "456 Oak Avenue");
        assertThrows(IllegalArgumentException.class, () -> service.addContact(duplicate));
    }

    @Test
    void testAddMultipleContactsWithUniqueIDs() {
        service.addContact(contact);
        Contact second = new Contact("2", "Jane", "Doe", "0987654321", "456 Oak Avenue");
        assertDoesNotThrow(() -> service.addContact(second));
    }

    @Test
    void testAddNullContact() {
        assertThrows(IllegalArgumentException.class, () -> service.addContact(null));
    }

    // ---------- Requirement: delete contacts per contact ID ----------

    @Test
    void testDeleteContact() {
        service.addContact(contact);
        assertDoesNotThrow(() -> service.deleteContact("1"));
        // Contact is gone: it can't be deleted or updated again
        assertThrows(IllegalArgumentException.class, () -> service.deleteContact("1"));
        assertThrows(IllegalArgumentException.class, () -> service.updateFirstName("1", "Bob"));
    }

    @Test
    void testDeleteFreesIDForReuse() {
        service.addContact(contact);
        service.deleteContact("1");
        Contact replacement = new Contact("1", "Jane", "Doe", "0987654321", "456 Oak Avenue");
        assertDoesNotThrow(() -> service.addContact(replacement));
    }

    @Test
    void testDeleteNonexistentContact() {
        assertThrows(IllegalArgumentException.class, () -> service.deleteContact("999"));
    }

    // ---------- Requirement: update fields per contact ID ----------

    @Test
    void testUpdateFirstName() {
        service.addContact(contact);
        service.updateFirstName("1", "Robert");
        assertEquals("Robert", contact.getFirstName());
    }

    @Test
    void testUpdateLastName() {
        service.addContact(contact);
        service.updateLastName("1", "Jones");
        assertEquals("Jones", contact.getLastName());
    }

    @Test
    void testUpdatePhone() {
        service.addContact(contact);
        service.updatePhone("1", "5551234567");
        assertEquals("5551234567", contact.getPhone());
    }

    @Test
    void testUpdateAddress() {
        service.addContact(contact);
        service.updateAddress("1", "789 Pine Road");
        assertEquals("789 Pine Road", contact.getAddress());
    }

    @Test
    void testUpdateOnlyChangesTargetContact() {
        service.addContact(contact);
        Contact other = new Contact("2", "Jane", "Doe", "0987654321", "456 Oak Avenue");
        service.addContact(other);
        service.updateFirstName("1", "Robert");
        assertEquals("Robert", contact.getFirstName());
        assertEquals("Jane", other.getFirstName());
    }

    // ---------- Updates on a missing contact ----------

    @Test
    void testUpdateFirstNameNonexistentContact() {
        assertThrows(IllegalArgumentException.class, () -> service.updateFirstName("999", "Robert"));
    }

    @Test
    void testUpdateLastNameNonexistentContact() {
        assertThrows(IllegalArgumentException.class, () -> service.updateLastName("999", "Jones"));
    }

    @Test
    void testUpdatePhoneNonexistentContact() {
        assertThrows(IllegalArgumentException.class, () -> service.updatePhone("999", "5551234567"));
    }

    @Test
    void testUpdateAddressNonexistentContact() {
        assertThrows(IllegalArgumentException.class, () -> service.updateAddress("999", "789 Pine Road"));
    }

    // ---------- Invalid update values are rejected and leave the contact unchanged ----------

    @Test
    void testUpdateFirstNameTooLong() {
        service.addContact(contact);
        assertThrows(IllegalArgumentException.class, () -> service.updateFirstName("1", "ThisNameIsTooLong"));
        assertEquals("John", contact.getFirstName());
    }

    @Test
    void testUpdateLastNameNull() {
        service.addContact(contact);
        assertThrows(IllegalArgumentException.class, () -> service.updateLastName("1", null));
        assertEquals("Smith", contact.getLastName());
    }

    @Test
    void testUpdatePhoneWrongLength() {
        service.addContact(contact);
        assertThrows(IllegalArgumentException.class, () -> service.updatePhone("1", "12345"));
        assertEquals("1234567890", contact.getPhone());
    }

    @Test
    void testUpdateAddressTooLong() {
        service.addContact(contact);
        String longAddress = "1234567890123456789012345678901"; // 31 characters
        assertThrows(IllegalArgumentException.class, () -> service.updateAddress("1", longAddress));
        assertEquals("123 Main Street", contact.getAddress());
    }
}