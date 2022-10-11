package seedu.travelr.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.travelr.commons.exceptions.IllegalValueException;
import seedu.travelr.model.event.Event;
import seedu.travelr.model.trip.Description;
import seedu.travelr.model.trip.Title;
import seedu.travelr.model.trip.Trip;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Jackson-friendly version of {@link Trip}.
 */
class JsonAdaptedTrip {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Trip's %s field is missing!";

    private final String title;
    private final String description;
    private final List<JsonAdaptedEvent> events = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedTrip(@JsonProperty("title") String title, @JsonProperty("description") String description,
                           @JsonProperty("tagged") List<JsonAdaptedEvent> tagged) {
        this.title = title;
        this.description = description;
        if (tagged != null) {
            this.events.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedTrip(Trip source) {
        title = source.getTitle().fullTitle;
        description = source.getDescription().value;
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
        final List<Event> tripTags = new ArrayList<>();
        for (JsonAdaptedEvent tag : events) {
            tripTags.add(tag.toModelType());
        }

        if (title == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName()));
        }
        if (!Title.isValidTitle(title)) {
            throw new IllegalValueException(Title.MESSAGE_CONSTRAINTS);
        }
        final Title modelTitle = new Title(title);

        if (description == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName()));
        }
        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description modelDescription = new Description(description);

        final Set<Event> modelTags = new HashSet<>(tripTags);

        return new Trip(modelTitle, modelDescription, modelTags);
    }

}
