package uk.bellesoft;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Collection;

import static org.junit.Assert.*;

public class DataFilter7Test {
    @Test
    public void whenEmpty() throws FileNotFoundException {

        assertTrue(DataFilterer8.filterByCountry(openFile("src/test/resources/empty"), "GB").isEmpty());
    }

    private FileReader openFile(String filename) throws FileNotFoundException {
        return new FileReader(new File(filename));
    }

    @Test
    public void shouldFilterByCountry() throws FileNotFoundException {
        Collection<?> result = DataFilter7.filterByCountry(openFile("src/test/resources/multi-lines"), "US");
        assertEquals(3, result.size());
    }

}