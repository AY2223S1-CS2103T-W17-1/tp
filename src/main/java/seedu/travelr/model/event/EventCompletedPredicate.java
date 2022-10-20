package seedu.travelr.model.event;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.travelr.commons.core.index.Index;
import seedu.travelr.model.list.Itineraries;
import seedu.travelr.model.trip.Trip;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class EventCompletedPredicate implements Predicate<Event> {

    private FilteredList<Trip> completedTrips;
    List<Event> completedEvents;

    /**
     * Creates a new EventInItineraryPredicate.
     */
    public EventCompletedPredicate(FilteredList<Trip> completedTrips) {
        this.completedTrips = completedTrips;
        completedEvents = new ArrayList<>();
        for (Trip t : completedTrips) {
            Itineraries currItinerary = t.getItinerary();
            for (Event e : currItinerary) {
                completedEvents.add(e);
            }
        }
    }


    @Override
    public boolean test(Event event) {
        return completedEvents.contains(event);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EventCompletedPredicate // instanceof handles nulls
                && completedEvents.equals(((EventCompletedPredicate) other).completedEvents)); // state check
    }
}
