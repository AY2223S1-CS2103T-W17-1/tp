package seedu.travelr.logic.parser;

import seedu.travelr.logic.commands.AddEventToTripCommand;
import seedu.travelr.logic.parser.exceptions.ParseException;
import seedu.travelr.model.trip.Title;

import java.util.stream.Stream;

import static seedu.travelr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.travelr.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.travelr.logic.parser.CliSyntax.PREFIX_TRIP;

/**
 * Represents the AddEventToTripCommandParser.
 */
public class AddEventToTripCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddEventToTripCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TITLE, PREFIX_TRIP);

        if (!arePrefixesPresent(argMultimap, PREFIX_TITLE, PREFIX_TRIP)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddEventToTripCommand.MESSAGE_USAGE));
        }

        Title title = ParserUtil.parseTitle(argMultimap.getValue(PREFIX_TITLE).get());
        Title trip = ParserUtil.parseTitle(argMultimap.getValue(PREFIX_TRIP).get());

        return new AddEventToTripCommand(title, trip);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
