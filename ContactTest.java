package contact;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ContactTest {

    // ---------- Valid creation ----------

    @Test
    void testValidContactCreation() {
        Contact contact = new Contact("1234567890", "John", "Smith", "1234567890", "123 Main Street");
        assertEquals("1234567890", contact.getContactID());
        assertEquals("John", contact.getFirstName());
        assertEquals("Smith", contact.getLastName());
        assertEquals("1234567890", contact.getPhone());
        assertEquals("123 Main Street", contact.getAddress());
    }

    // ---------- Requirement 1: contact ID (required, max 10 characters, not updatable) ----------

    @Test
    void testContactIDNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact(null, "John", "Smith", "1234567890", "123 Main Street"));
    }

    @Test
    void testContactIDTooLong() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("12345678901", "John", "Smith", "1234567890", "123 Main Street"));
    }

    @Test
    void testContactIDNotUpdatable() {
        // Contact has no setContactID method and the field is final, so the ID cannot change.
        // This test confirms the ID is unchanged after every updatable field is modified.
        Contact contact = new Contact("1", "John", "Smith", "1234567890", "123 Main Street");
        contact.setFirstName("Robert");
        contact.setLastName("Jones");
        contact.setPhone("5551234567");
        contact.setAddress("789 Pine Road");
        assertEquals("1", contact.getContactID());
    }

    // ---------- Requirement 2: first name (required, max 10 characters) ----------

    @Test
    void testFirstNameNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", null, "Smith", "1234567890", "123 Main Street"));
    }

    @Test
    void testFirstNameTooLong() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "ThisNameIsTooLong", "Smith", "1234567890", "123 Main Street"));
    }

    @Test
    void testUpdateFirstName() {
        Contact contact = new Contact("1", "John", "Smith", "1234567890", "123 Main Street");
        contact.setFirstName("Robert");
        assertEquals("Robert", contact.getFirstName());
    }

    // ---------- Requirement 3: last name (required, max 10 characters) ----------

    @Test
    void testLastNameNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "John", null, "1234567890", "123 Main Street"));
    }

    @Test
    void testLastNameTooLong() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "John", "ThisNameIsTooLong", "1234567890", "123 Main Street"));
    }

    @Test
    void testUpdateLastName() {
        Contact contact = new Contact("1", "John", "Smith", "1234567890", "123 Main Street");
        contact.setLastName("Jones");
        assertEquals("Jones", contact.getLastName());
    }

    // ---------- Requirement 4: phone (required, exactly 10 digits) ----------

    @Test
    void testPhoneNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "John", "Smith", null, "123 Main Street"));
    }

    @Test
    void testPhoneWrongLength() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "John", "Smith", "12345", "123 Main Street"));
    }

    @Test
    void testPhoneNonDigits() {
        // Exactly 10 characters, but not all digits
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "John", "Smith", "12345abcde", "123 Main Street"));
    }

    @Test
    void testUpdatePhone() {
        Contact contact = new Contact("1", "John", "Smith", "1234567890", "123 Main Street");
        contact.setPhone("5551234567");
        assertEquals("5551234567", contact.getPhone());
    }

    @Test
    void testUpdatePhoneNonDigits() {
        Contact contact = new Contact("1", "John", "Smith", "1234567890", "123 Main Street");
        assertThrows(IllegalArgumentException.class, () -> contact.setPhone("12345abcde"));
        assertEquals("1234567890", contact.getPhone());
    }

    // ---------- Requirement 5: address (required, max 30 characters) ----------

    @Test
    void testAddressNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "John", "Smith", "1234567890", null));
    }

    @Test
    void testAddressTooLong() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "John", "Smith", "1234567890", "1234567890123456789012345678901"));
    }

    @Test
    void testUpdateAddress() {
        Contact contact = new Contact("1", "John", "Smith", "1234567890", "123 Main Street");
        contact.setAddress("789 Pine Road");
        assertEquals("789 Pine Road", contact.getAddress());
    }
}