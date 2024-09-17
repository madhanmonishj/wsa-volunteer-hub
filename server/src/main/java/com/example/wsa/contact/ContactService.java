package com.example.wsa.contact;

import java.util.List;

// Declare an interface for managing contact-related services
public interface ContactService {

    // Method to retrieve a list of contacts
    List<Contact> getContactList();

    // Method to retrieve a specific contact by ID
    Contact getContact(Integer id);

    void saveContact(Contact contact);
}
