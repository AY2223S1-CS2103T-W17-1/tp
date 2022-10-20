package seedu.travelr.logic.parser;

import static seedu.travelr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.travelr.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.travelr.logic.parser.CliSyntax.PREFIX_DESC;
import static seedu.travelr.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.travelr.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.travelr.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.Set;
import java.util.stream.Stream;

import seedu.travelr.logic.commands.AddCommand;
import seedu.travelr.logic.parser.exceptions.ParseException;
import seedu.travelr.model.component.DateField;
import seedu.travelr.model.component.Description;
import seedu.travelr.model.component.Location;
import seedu.travelr.model.component.Title;
import seedu.travelr.model.event.Event;
import seedu.travelr.model.trip.Trip;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TITLE, PREFIX_DESC, PREFIX_TAG, PREFIX_LOCATION, PREFIX_DATE);

        // Location and DateField is optional
        if (!arePrefixesPresent(argMultimap, PREFIX_TITLE, PREFIX_DESC)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Title title = ParserUtil.parseTitle(argMultimap.getValue(PREFIX_TITLE).get());
        Description description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESC).get());
        Set<Event> eventList = ParserUtil.parseEvents(argMultimap.getAllValues(PREFIX_TAG));
        Location location = ParserUtil.parseLocation(argMultimap.getValue(PREFIX_LOCATION)
                .orElse(Location.getDefaultValue()));
        DateField dateField = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE)
                .orElse(DateField.getDefaultValue()));

        Trip trip = new Trip(title, description, eventList, location, dateField);

        return new AddCommand(trip);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
