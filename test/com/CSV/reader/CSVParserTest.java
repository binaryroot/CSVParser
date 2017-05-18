package com.CSV.reader;

import com.CSV.Constant;
import org.hamcrest.core.IsNull;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @since 1.0
 * @version 1.0
 * @author Nazar Ivanchuk
 */
public class CSVParserTest {

    @Test
    public void testRowSize() throws FileNotFoundException {
        CSVParser parser = new CSVParser(new File("test/resources/essential-mac.csv"), Constant.FIELD_SEPARATOR, Constant.TEXT_DELIMITER, false);
        assertEquals(parser.getRows().size(), 8);
    }


    @Test
    public void testRowSizeWithSkipEmptyRow() throws FileNotFoundException {
        CSVParser parser = new CSVParser(new File("test/resources/essential-mac.csv"), Constant.FIELD_SEPARATOR, Constant.TEXT_DELIMITER, true);
        assertEquals(parser.getRows().size(), 8);
    }

    @Test
    public void testRowSizeWithSkipEmptyRow1() throws FileNotFoundException {
        CSVParser parser = new CSVParser(new File("test/resources/with_empty_rows.csv"), Constant.FIELD_SEPARATOR, Constant.TEXT_DELIMITER, true);
        assertEquals(parser.getRows().size(), 3);
    }

    @Test
    public void testRowSizeWithSkipEmptyRow2() throws FileNotFoundException {
        CSVParser parser = new CSVParser(new File("test/resources/with_empty_rows.csv"), Constant.FIELD_SEPARATOR, Constant.TEXT_DELIMITER, false);
        assertEquals(parser.getRows().size(), 4);
    }

    @Test
    public void testRowSizeWithDoubleDelimiter() throws FileNotFoundException {
        CSVParser parser = new CSVParser(new File("test/resources/country.csv"), Constant.FIELD_SEPARATOR, Constant.TEXT_DELIMITER, false);
        assertEquals(parser.getRows().size(), 6);
    }

    @Test
    public void testParseLineNoQuote()  throws FileNotFoundException {
        CSVParser parser = new CSVParser(new File("test/resources/country.csv"), Constant.FIELD_SEPARATOR, Constant.TEXT_DELIMITER, false);
        String line = "Nazar,Roman,Mask";
        List<String> result = parser.parseLine(line);

        assertThat(result, IsNull.notNullValue());
        assertThat(result.size(), is(3));
        assertThat(result.get(0), is("Nazar"));
        assertThat(result.get(1), is("Roman"));
        assertThat(result.get(2), is("Mask"));
    }

    @Test
    public void testDoubleQuotes() throws Exception {
        CSVParser parser = new CSVParser(new File("test/resources/country.csv"), Constant.FIELD_SEPARATOR, Constant.TEXT_DELIMITER, false);
        String line = "\"Nazar\",\"Roman\",\"Roman\"\"Mask\"";
        List<String> result = parser.parseLine(line);

        assertThat(result, IsNull.notNullValue());
        assertThat(result.size(), is(3));
        assertThat(result.get(0), is("Nazar"));
        assertThat(result.get(1), is("Roman"));
        assertThat(result.get(2), is("Roman\"Mask"));
    }

    @Test
    public void testDoubleQuotesWithComma() throws Exception  {
        CSVParser parser = new CSVParser(new File("test/resources/country.csv"), Constant.FIELD_SEPARATOR, Constant.TEXT_DELIMITER, false);
        String line = "\"Nazar\",\"Roman\",\"Ma,sk\"";
        List<String> result = parser.parseLine(line);
        assertThat(result, IsNull.notNullValue());
        assertThat(result.size(), is(3));
        assertThat(result.get(0), is("Nazar"));
        assertThat(result.get(1), is("Roman"));
        assertThat(result.get(2), is("Ma,sk"));

    }
}