package za.co.wethinkcode.ticketing;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSchema {

    private DatabaseSchema() {
    }

    public static void createSchema(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            // TODO: create the `venues`, `events` and `tickets` tables described in
            // resources/erd.png, including the primary key on each table, the
            // NOT NULL, UNIQUE and CHECK constraints, and both foreign keys.
            //
            // Execute one CREATE TABLE statement per table, like this:
            //
            //     statement.execute("""
            //             CREATE TABLE ..
            //            """);
            statement.execute("""
                    CREATE TABLE IF NOT EXISTS venues (
                        id INTEGER PRIMARY KEY,
                        name TEXT NOT NULL UNIQUE,
                        city TEXT NOT NULL
                    )
        """);
            statement.execute("""
                    CREATE TABLE IF NOT EXISTS events (
                        id INTEGER PRIMARY KEY,
                        venue_id INTEGER NOT NULL,
                        title TEXT NOT NULL,
                        starts_at TEXT NOT NULL,
                        capacity INTEGER NOT NULL CHECK (capacity > 0),
                        FOREIGN KEY (venue_id) REFERENCES venues(id)
                                                    )
        """);
            statement.execute("""
                    CREATE TABLE IF NOT EXISTS tickets (
                        id INTEGER PRIMARY KEY,
                        event_id INTEGER NOT NULL,
                        seat_label TEXT NOT NULL,
                        price REAL NOT NULL,
                        scanned_at TEXT,
                        FOREIGN KEY (event_id) REFERENCES events(id),
                        UNIQUE (event_id, seat_label)
                    )

        """);

        }
    }
}
