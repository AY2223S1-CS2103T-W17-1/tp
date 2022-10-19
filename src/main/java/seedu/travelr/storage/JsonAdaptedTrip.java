package seedu.travelr.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.travelr.commons.exceptions.IllegalValueException;
import seedu.travelr.model.component.Description;
import seedu.travelr.model.component.Location;
import seedu.travelr.model.component.Title;
import seedu.travelr.model.event.Event;
import seedu.travelr.model.trip.Trip;

/**
 * Jackson-friendly version of {@link Trip}.
 */
class JsonAdaptedTrip {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Trip's %s field is missing!";

    private final String title;
    private final String description;
    private final List<JsonAdaptedEvent> events = new ArrayList<>();
    private final boolean done;
    private final String location;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedTrip(@JsonProperty("title") String title, @JsonProperty("description") String description,
                           @JsonProperty("done") boolean done,
                           @JsonProperty("location") String location,
                           @JsonProperty("events") List<JsonAdaptedEvent> events) {
        this.title = title;
        this.description = description;
        this.done = done;
        this.location = location;
        if (events != null) {
            this.events.addAll(events);
        }
    }

    /**
     * Converts a given {@code Trip} into this class for Jackson use.
     */
    public JsonAdaptedTrip(Trip source) {
        title = source.getTitle().fullTitle;
        description = source.getDescription().value;
        done = source.isDone();
        location = source.getLocation().locationName;
        events.addAll(source.getEvents().stream()
                .map(JsonAdaptedEvent::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Trip toModelType() throws IllegalValueException {
        final List<Event> tripEvents = new ArrayList<>();
        for (JsonAdaptedEvent event : events) {
            tripEvents.add(event.toModelType());
        }

        if (title == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName()));
        }
        if (!Title.isValidTitle(title)) {
            throw new IllegalValueException(Title.MESSAGE_CONSTRAINTS);
        }

        if (description == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName()));
        }
        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }

        if (location == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Location.class.getSimpleName()));
        }

        if (!Location.isValidLocation(location)) {
            throw new IllegalValueException(Location.MESSAGE_CONSTRAINTS);
        }

        final Title modelTitle = new Title(title);

        final Description modelDescription = new Description(description);

        final Set<Event> modelEvents = new HashSet<>(tripEvents);

        final boolean done = this.done;

        final Location location = new Location(this.location);

        return new Trip(modelTitle, modelDescription, modelEvents, done, location);
    }

}
