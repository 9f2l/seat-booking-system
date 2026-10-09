# Seat Booking System

A console-based seat reservation program written in Java. Seats are stored in a plain text file, so bookings persist between runs.

## Features

- **Reserve** up to 5 seats per session, choosing class (First / Standard), window or aisle, and with or without a table. The first free seat matching your choices is booked.
- **Cancel** a booking using the seat number and the email it was booked under.
- **View** every seat with its type, price and whether it is available or booked.
- Input validation: invalid menu choices and non-numeric input are rejected and re-asked.

## Run it

Requires a JDK (8 or newer). Run from the repository root so `seats.txt` is found:

```bash
javac -d bin src/Booking.java
java -cp bin Booking
```

## Seat file format

`seats.txt` has one seat per line, space-separated:

```
1A STD true false false 23.50 free
```

| Field | Meaning |
|---|---|
| `1A` | Seat number |
| `STD` / `1ST` | Standard or First class |
| `true`/`false` | Window seat |
| `true`/`false` | Aisle seat |
| `true`/`false` | Has a table |
| `23.50` | Price |
| `free` or an email | Available, or who booked it |

The program reads however many seats are in the file, so you can add or remove lines freely.

## Project layout

```
src/Booking.java        current version of the program
seats.txt               seat data
legacy/seatBooking.java earlier, first-draft version (kept for reference)
```

## Concepts used

File I/O, 2D arrays, `Scanner` input handling, and a menu-driven program structure.
