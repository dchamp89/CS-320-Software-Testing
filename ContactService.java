package contact;

import java.util.HashMap;
import java.util.Map;

public class ContactService {
    private Map<String, Contact> contacts = new HashMap<>();

    public void addContact(Contact contact) {
        if (contact == null) {
            throw new IllegalArgumentException("Contact cannot be null");
        }
        if (contacts.containsKey(contact.getContactID())) {
            throw new IllegalArgumentException("Contact ID already exists");
        }
        contacts.put(contact.getContactID(), contact);
    }

    public void deleteContact(String contactID) {
        if (!contacts.containsKey(contactID)) {
            throw new IllegalArgumentException("Contact ID does not exist");
        }
        contacts.remove(contactID);
    }

    public void updateFirstName(String contactID, String firstName) {
        Contact contact = getContact(contactID);
        contact.setFirstName(firstName);
    }

    public void updateLastName(String contactID, String lastName) {
        Contact contact = getContact(contactID);
        contact.setLastName(lastName);
    }

    public void updatePhone(String contactID, String phone) {
        Contact contact = getContact(contactID);
        contact.setPhone(phone);
    }

    public void updateAddress(String contactID, String address) {
        Contact contact = getContact(contactID);
        contact.setAddress(address);
    }

    private Contact getContact(String contactID) {
        Contact contact = contacts.get(contactID);
        if (contact == null) {
            throw new IllegalArgumentException("Contact ID does not exist");
        }
        return contact;
    }
}