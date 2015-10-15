package uk.bellesoft;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.*;
import java.util.stream.Collectors;

public class DataFilterer8 {

    public static Collection<?> filterByCountry(Reader source, String country) {
        BufferedReader logSource = new BufferedReader(source);
        return logSource
                .lines()
                .skip(1)
                .map(line -> LogEntry.fromString(line))
                .filter(e -> e.fromCountry(country))
                .collect(Collectors.toList());
    }

    public static Collection<?> filterByCountryWithResponseTimeAboveLimit(Reader source, String country, long limit) {
        BufferedReader logSource = new BufferedReader(source);
        return logSource
                .lines()
                .skip(1)
                .map(line -> LogEntry.fromString(line))
                .filter(e -> e.fromCountry(country))
                .filter(e -> e.responseTimeGreaterThan(limit))
                .collect(Collectors.toList());
    }

    public static Collection<?> filterByResponseTimeAboveAverage(Reader source) {
        BufferedReader logSource = new BufferedReader(source);

        Collection<LogEntry> entries = logSource
                .lines()
                .skip(1)
                .map(line -> LogEntry.fromString(line))
                .collect(Collectors.toList());

        OptionalDouble average = entries.stream().mapToLong(e -> e.getResponseTime()).average();

        Collection<LogEntry> result = entries
                .stream()
                .filter(e -> e.responseTimeGreaterThan(average.getAsDouble()))
                .collect(Collectors.toList());

        return result;
    }

}