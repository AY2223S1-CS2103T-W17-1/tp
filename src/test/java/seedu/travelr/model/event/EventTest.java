package seedu.travelr.model.event;

import org.junit.jupiter.api.Test;

import static seedu.travelr.logic.parser.ParserUtil.EVENT_DESCRIPTION_PLACEHOLDER;
import static seedu.travelr.testutil.Assert.assertThrows;
import seedu.travelr.model.trip.Title;
import seedu.travelr.model.trip.Description;

public class EventTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Event(null));
    }

    @Test
    public void constructor_invalidEventTitle_throwsIllegalArgumentException() {
        String invalidEventTitle = "";
        assertThrows(IllegalArgumentException.class, () -> new Event(new Title(invalidEventTitle), new Description(EVENT_DESCRIPTION_PLACEHOLDER)));
    }

    @Test
    public void isValidEventTitle() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Event.isValidEventTitle(null));
    }

}