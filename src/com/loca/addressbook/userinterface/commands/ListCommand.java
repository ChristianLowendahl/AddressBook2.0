package com.loca.addressbook.userinterface.commands;

import java.util.List;

import com.loca.addressbook.exceptions.InvalidCommandParameterException;
import com.loca.addressbook.registry.Contact;
import com.loca.addressbook.registry.Registry;
import com.loca.addressbook.userinterface.ConsolePrinter;
import com.loca.addressbook.userinterface.ContactFormatter;
import com.loca.addressbook.userinterface.ContactListSorter;

public class ListCommand implements Command {
	
	private CommandType commandType = CommandType.LIST;
	private ConsolePrinter consolePrinter;
	private Registry registry;
	private List<String> parameters;
	
	public ListCommand (ConsolePrinter consolePrinter, Registry registry, List<String> parameters) {
		this.consolePrinter = consolePrinter;
		this.registry = registry;
		this.parameters = parameters;
	}

    @Override
    public void execute() throws InvalidCommandParameterException {
    	if (parameters.size() != commandType.getArgumentCount()) {
            throw new InvalidCommandParameterException();
        }
    	listContacts();
    }

	private void listContacts() {
		String output = "";
		List<Contact> contacts = registry.getContacts();
		if (contacts.isEmpty()) {
			output = commandType.getFailureMessage();
		} else {
			List<Contact> sortedContacts = ContactListSorter.sort(contacts);
			output = makeOutput(sortedContacts);
		}
		consolePrinter.print(output);
	}

	private String makeOutput(List<Contact> contacts) {
		StringBuilder output = new StringBuilder(1000);
		for(Contact contact : contacts) {
			String formattedContact = ContactFormatter.format(contact);
			output.append(formattedContact);
		}
		return output.toString();
	}
   
}