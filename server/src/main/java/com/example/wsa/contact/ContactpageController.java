package com.example.wsa.contact;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/home/contactus")
public class ContactpageController {

    private final ContactService contactService;
    private final EmailService emailService;

    @Autowired
    public ContactpageController(ContactService contactService, EmailService emailService) {
        this.contactService = contactService;
        this.emailService = emailService;
    }

    @GetMapping("")
    public ModelAndView hostContactPage() {
        return new ModelAndView("contact/NewContactUs"); // Assuming NewContactUs.html is in src/main/resources/templates/contact
    }

    @GetMapping("/contact-details")
    public ModelAndView hostContactDetailsPage() {
        ModelAndView modelAndView = new ModelAndView("contact/new-contact-details"); // Assuming new-contact-details.html is in src/main/resources/templates/contact
        modelAndView.addObject("contactsList", contactService.getContactList());
        return modelAndView;
    }

    @GetMapping("/addContact")
    public ModelAndView hostContactForm() {
        return new ModelAndView("contact/NewContactUs"); // Assuming NewContactUs.html is in src/main/resources/templates/contact
    }

    @PostMapping("/addContact")
    public ResponseEntity<String> processContactForm(@RequestBody Contact contact) throws MessagingException {
        try {
            contactService.saveContact(contact);
            emailService.sendEmail(contact);
            return ResponseEntity.ok("Contact added and email sent successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add contact or send email.");
        }
    }
}
