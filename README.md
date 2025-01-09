# Ticket Scanning Application
Ticket Scanning App is a user-friendly desktop application designed to manage event tickets. This app enables event organizers and staff to validate ticket codes, determine their status, and display show-related details. It includes features to handle duplicate or invalid tickets and provides a reset functionality for managing large-scale events efficiently.

## Features
* Ticket Validation
  * Checks whether a ticket is valid, invalid, or a duplicate.
  * Provides real-time feedback on the ticket status.
* Show Information
  * Displays the type of show associated with a valid ticket.
* Reset Functionality
  * Includes a secret code (reset) to reset all ticket statuses, marking them as unredeemed.
* Sound Feedback
  * Plays specific sound effects for valid and invalid tickets to enhance usability.
* Dynamic GUI Components
  * Updates the interface with ticket validation status and show details.
  * Includes user-friendly features like auto-focusing on the input field after each action.
* Error Handling
  * Handles invalid input and provides clear visual and auditory feedback.

## How It Works

1. Input Ticket Code
  * Enter a ticket code in the input field and press the "Redeem" button.
2. Ticket Validation Process
  * The app checks the ticket code against the event's database and determines its status:
    * Valid: Updates the ticket status and displays the show type.
    * Duplicate: Notifies the user of the duplicate status.
    * Invalid: Provides feedback for tickets not purchased or incorrect codes.
3. Reset Functionality
  * Input reset to mark all tickets as unredeemed.
4. Feedback Mechanism
  * Displays real-time status in the GUI and plays corresponding sounds for user guidance.

## Technologies
* Java, JavaFX

