package uk.bellesoft;

import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DataFilter7 {
    public static Collection<?> filterByCountry(Reader source, String country) {
        BufferedReader logSource = new BufferedReader(source);

        Iterable<String> stringIterable = new Iterable<String>() {
            @Override
            public Iterator<String> iterator() {
                return new Iterator<String>() {
                    @Override
                    public boolean hasNext() {
                        try {
                            return logSource.ready();
                        }
                        catch (Throwable e) {
                            return false;
                        }
                    }

                    @Override
                    public String next()  {
                        try {
                            return logSource.readLine();
                        }
                        catch (Throwable e) {
                            return "<error>";
                        }
                    }
                };
            }
        };

        List<LogEntry> logEntries = FluentIterable
                .from(stringIterable)
                .skip(1)
                .transform(new com.google.common.base.Function<String, LogEntry>() {
                    @Override
                    public LogEntry apply(String s) {
                        return LogEntry.fromString(s);
                    }
                })
                .toList();

        List<LogEntry> filteredLogEntries = FluentIterable.from(logEntries)
                .filter(new Predicate<LogEntry>() {
                    @Override
                    public boolean apply(LogEntry logEntry) {
                        return logEntry.fromCountry(country);
                    }
                })
                .toList();

        return filteredLogEntries;
    }


}
