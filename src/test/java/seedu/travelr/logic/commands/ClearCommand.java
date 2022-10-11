package seedu.travelr.logic.commands;

import seedu.travelr.model.AddressBook;
import seedu.travelr.model.Model;

import static java.util.Objects.requireNonNull;

/**
 * Clears Travelr.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Travelr has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setAddressBook(new AddressBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
