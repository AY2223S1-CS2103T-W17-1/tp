package seedu.travelr.logic.commands;

import static seedu.travelr.logic.parser.CliSyntax.PREFIX_REVERSE_ORDER;
import static seedu.travelr.logic.parser.CliSyntax.PREFIX_SORTBY;

import java.util.Comparator;

import seedu.travelr.model.Model;
import seedu.travelr.model.trip.Trip;

/**
 * Sorts the trips within Travelr.
 */
public class SortTripsCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts trips within Travelr.\n"
            + "Parameters (Optional): "
            + PREFIX_SORTBY + "FACTOR " + PREFIX_REVERSE_ORDER + "\n"
            + "By default this command will sort by titles alphabetically if there's no given factor.\n"
            + "Available factors: \n" 
            + "title : sorts by trip titles alphabetically" + "\n"
            + "time : sorts by trip time" + "\n"
            + "location : sorts by trip locations alphabetically" + "\n"
            + "eventcount : sorts by number of events within each trip" + "\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_SORTBY + "title " + PREFIX_REVERSE_ORDER;

    public static final String SORT_SUCCESS = "Trips have been sorted.";

    private final Comparator<Trip> comparator;

    public SortTripsCommand(Comparator<Trip> comparator) {
        this.comparator = comparator;
    }

    @Override
    public CommandResult execute(Model model) {
        model.sortTripsByComparator(comparator);
        return new CommandResult(SORT_SUCCESS);
    }
}
