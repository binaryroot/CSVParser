package com.CSV.reader;

import com.CSV.Constant;
import org.hamcrest.core.IsNull;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @since 1.0
 * @version 1.0
 * @author Nazar Ivanchuk
 */
public class CSVParserCustomSeparatorAndDelimiterTest {
    @Test
    public void testCustomParserSeparator() throws FileNotFoundException {
        CSVParser parser = new CSVParser(new File("test/resources/essential-mac.csv"), '|', Constant.TEXT_DELIMITER, false);
        String line = "Nazar|Roman|Mask";
        List<String> result = parser.parseLine(line);
        assertThat(result, IsNull.notNullValue());
        assertThat(result.size(), is(3));
        assertThat(result.get(0), is("Nazar"));
        assertThat(result.get(1), is("Roman"));
        assertThat(result.get(2), is("Mask"));
    }

    @Test
    public void testCustomSeparatorWithCustomDelimiter() throws FileNotFoundException {
        CSVParser parser = new CSVParser(new File("test/resources/essential-mac.csv"), '|', '\'', false);
        String line = "Nazar|Roman|Mask";
        List<String> result = parser.parseLine(line);
        assertThat(result, IsNull.notNullValue());
        assertThat(result.size(), is(3));
        assertThat(result.get(0), is("Nazar"));
        assertThat(result.get(1), is("Roman"));
        assertThat(result.get(2), is("Mask"));
    }

    @Test
    public void testCustomSeparatorWithDelimiterButCustomQuoteInColumn() throws FileNotFoundException {
        CSVParser parser = new CSVParser(new File("test/resources/essential-mac.csv"), '|', '\'', false);
        String line = "'Nazar'|'Roman'|'Ma|sk'";
        List<String> result = parser.parseLine(line);
        assertThat(result, IsNull.notNullValue());
        assertThat(result.size(), is(3));
        assertThat(result.get(0), is("Nazar"));
        assertThat(result.get(1), is("Roman"));
        assertThat(result.get(2), is("Ma|sk"));

    }

    @Test
    public void test_custom_separator_and_quote_but_double_quotes_in_column() throws FileNotFoundException {
        CSVParser parser = new CSVParser(new File("test/resources/essential-mac.csv"), '|', '\'', false);
        String line = "'Nazar'|'Roman'|'Ma\"\"sk'";
        List<String> result = parser.parseLine(line);

        assertThat(result, IsNull.notNullValue());
        assertThat(result.size(), is(3));
        assertThat(result.get(0), is("Nazar"));
        assertThat(result.get(1), is("Roman"));
        assertThat(result.get(2), is("Ma\"sk"));

    }
}