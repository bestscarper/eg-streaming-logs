package uk.bellesoft;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class DataFilterer8Test {
    @Test
    public void whenEmpty() throws FileNotFoundException {

        assertTrue(DataFilterer8.filterByCountry(openFile("src/test/resources/empty"), "GB").isEmpty());
    }

    private FileReader openFile(String filename) throws FileNotFoundException {
        return new FileReader(new File(filename));
    }

    @Test
    public void shouldFilterByCountry() throws FileNotFoundException {
        Collection<?> result = DataFilterer8.filterByCountry(openFile("src/test/resources/multi-lines"), "US");
        assertEquals(3, result.size());
    }

    @Test
    public void shouldfilterByCountryWithResponseTimeAboveLimit() throws FileNotFoundException {
        Collection<?> result = DataFilterer8.filterByCountryWithResponseTimeAboveLimit(openFile("src/test/resources/multi-lines"), "US", 600);
        assertEquals(2, result.size());
    }

    @Test
    public void filterByResponseTimeAboveAverage() throws FileNotFoundException {
        Collection<LogEntry> logEntries = (Collection<LogEntry>)
                (DataFilterer8.filterByResponseTimeAboveAverage(openFile("src/test/resources/multi-lines")));

        assertEquals(3, logEntries.size());
        assertTrue(logEntries.stream().allMatch(e -> e.responseTimeGreaterThan(526)));
    }

    @Test
    public void filterByResponseTimeAboveAverageSingle() throws FileNotFoundException {
        Collection<LogEntry> logEntries = (Collection<LogEntry>)
                (DataFilterer8.filterByResponseTimeAboveAverage(openFile("src/test/resources/single-line")));
        assertEquals(0, logEntries.size());
        assertTrue(logEntries.stream().allMatch(e -> e.responseTimeGreaterThan(526)));
    }
}
