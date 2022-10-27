# User Guide
Travelr is a desktop app for managing trips and events, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Travelr can get your trip management tasks done faster than traditional GUI apps.

- [Quick start](#quick-start)
- [Features](#features)
  * [Adding events: `add-e`](#adding-events-add-e)
  * [Deleting events: `delete-e`](#deleting-events-delete-e)
  * [Viewing events list: `list-e`](#viewing-events-list-list-e)
  * [Adding trips: `add`](#adding-trips-add)
  * [Viewing trips list: `list`](#viewing-trips-list-list)
  * [Marking trips as done: `mark`](#marking-trips-as-done-mark)
  * [Marking trips as not done: `unmark`](#marking-trips-as-not-done-unmark)
  * [Adding events to trips: `add-et`](#adding-events-to-trips-add-et)
  * [Removing events from trips: `delete-et`](#removing-events-from-trips-delete-et)
  * [View all completed trips and events: `completed`](#view-all-completed-trips-and-events-completed)
  * [View all trips and events: `view`](#view-all-trips-and-events-view)
  * [Saving data](#saving-data)
  * [Exiting the program: `bye`](#exiting-the-program-bye)

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `travelr.jar` from [here](https://github.com/AY2223S1-CS2103T-W17-1/tp/releases).
3. Copy the file to the folder you want to use as the _home folder_ for Travelr.
4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)
5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list-e`** : Lists all events.

   * **`add-e`**`Sightseeing/20-06-2030/Portugal/Visit Mountains` : Adds an event with the respective date, location, and activity into your event list.

   * **`delete -e`**`3` : Deletes the 3rd event shown in the current event list.

   * **`exit`** : Exits the app.
6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features
**Notes about the command format:**
Words in UPPER_CASE are the parameters to be supplied by the user.
e.g. in `add -t TITLE`, TITLE is a parameter which can be used as `add -t Switzerland Trip`.

Items in square brackets are optional.
e.g NAME [t/TAG] can be used as John Doe t/Friend or as John Doe.

The relevant prefixes must be used to separate parameters supplied by the user.
E.g. in `add-e n/TITLE d/DESCRIPTION`, ‘n/’  and 'd/' are two designated used to separate the two parameters supplied which can be used as `add-e n/Sightseeing d/Visit mountains`.

Extraneous parameters for commands that do not take in parameters (such as bye) will be ignored.
e.g. if the command specifies `exit 123`, it will be interpreted as `exit`.

### Adding events: `add-e`
Adds an event to the events list.

Format: `add-e n/TITLE d/DESCRIPTION `

Examples:
- `add-e n/Skydiving d/Skydiving with crew`
- `add-e n/Sailing d/Sail in the Danube River`

### Deleting events: `delete-e`
Deletes the specified event from the bucket list.

Format: `delete-e INDEX`
- Deletes the event at the specified INDEX.
- The index refers to the index number shown in the bucket list.
- The index must be a positive integer 1, 2, 3, …

Examples:
- `list-e` followed by `delete-e 2` deletes the 2nd event in the bucket list.

### Viewing events list: `list-e`
Shows a list of all events added.

Format: `list-e`

### Adding trips: `add`
Adds a trip to the trip list.

Format: `add n/TITLE d/DESCRIPTION [l/LOCATION] [D/DATE] `
- Location and Date are optional fields.
- Date must follow the format {dd-mm-yyyy}.
- If you do not specify a location, it will be set to the value "Default".
- If you do not specify a date, it will be set to the value "01-01-0000".
- Hence, any trip with the above location or date values will be assumed to have an unspecified location or date, and it will be reflected as such in the UI.

Examples:
- `add n/Trip to Iceland d/Skiing in Iceland l/Iceland D/26-12-2023`

### Delete trips: `delete`
Deletes a trip at the specified INDEX of the displayed trip list.

Format: `delete INDEX`

Examples:
- `delete 1`

### Viewing trips list: `list`
Shows a list of all trips added.

Format: `list`

### Marking trips as done: `mark`
Mark the trip in the specified INDEX as done

Format: `mark INDEX`
- Marks the trip at the specified INDEX as done.
- The index refers to the index number shown in the trip list.
- The index must be a positive integer 1, 2, 3, …

Examples:
- `mark 1` marks the first trip in the trip list as done.

### Marking trips as not done: `unmark`
Mark the trip in the specified INDEX as not done

Format: `unmark INDEX`
- Marks the trip at the specified INDEX as not done.
- The index refers to the index number shown in the trip list.
- The index must be a positive integer 1, 2, 3, …

Examples:
- `unmark 1` marks the first trip in the trip list as not done.

### Adding events to trips: `add-et`
Adds the specified event from the bucket list to the specified trip.

Format: `add-et n/EVENT NAME T/TRIP NAME`
- Adds the event with the specified EVENT NAME
- Event is added to the trip at the specified TRIP NAME
- The TRIP NAME must exist in the trips list.
- The EVENT NAME must exist in the events list.

Examples:
- `add-et n/asd t/qwe` adds the event titled asd in the bucket list to the itinerary of the trip with the title qwe 

### Removing events from trips: `delete-et`
Remove the specified event from the specified trip to the bucket list.

Format: `delete-et n/EVENT NAME T/TRIP NAME`
- Remove the event with the specified event titled EVENT NAME from the specified trip titled TRIP NAME 
- Event is added to the bucket list.
- The TRIP NAME must exist in the trips list.
- The EVENT NAME must exist in the trip itinerary.

Examples:
- `delete-et n/asd t/qwe` remove the event titled asd from the itinerary of the trip titled qwe's and returns it to the bucket list.

### Sorting trips: `sort by/FACTOR r/`
Sorts the trips according to the provided factor.

Format: `sort by/FACTOR r/`
- Sorts the trip according to provided FACTOR.
- Order of sort reversed when the `r/` prefix is provided.
- The parameters for this command are optional. 
- The trips will be sorted by their title in alphabetical order by default.
- A valid FACTOR must be provided if the `by/` prefix is provided.
- Extraneous parameters for `r/` prefix will be ignored and treated as just `r/`.

| FACTOR | Description |
| --- | --- |
| nothing | Default sort will be used |
| `title` | Sort by trips' title in alphabetical order |
| `time` | Sort by trip's date in chronological order |
| `location` | Sort by trips' location in alphabetical order |
| `eventcount` | Sort by trips' number of events in ascending order |
| `mark` | Shift the marked trips to the bottom of unmarked trips. |

### View all completed trips and events: `completed`
Displays all completed trips and events.

Format: `completed`

### View all trips and events: `view`
Displays all trips and events

Format: `view`

### Display single trip: `display`
Displays the full title, description, location (if a location has been defined for the trip), 
date (if a date has been defined for the trip) of the trip at the specified INDEX of the displayed trip list in the command box.

Format: `display INDEX`

### Display single trip: `display-e`
Displays the full title, description of the event at the specified INDEX of the displayed events list in the command box.

Format: `display-e INDEX`

### Saving data
Travelr data are saved locally automatically after any command that changes the data. There is no need to save manually.

### Exiting the program: `bye`
Exits the program.
Format: `bye`

### Glossary

* **Bucket list**: List of events that haven't been added to any trips
* **Displayed events list**: List of events being displayed in the right panel of the UI
* **Displayed trips list**: List of trips being displayed in the left panel of the UI


