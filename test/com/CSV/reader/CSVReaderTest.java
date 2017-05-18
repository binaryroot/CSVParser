package com.CSV.reader;

import com.CSV.Constant;
import com.CSV.core.CSVRow;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @since 1.0
 * @version 1.0
 * @author Nazar Ivanchuk
 */
public class CSVReaderTest {

    @Test(expected = NullPointerException.class)
    public void checkEmptyConstructor() throws FileNotFoundException {
        CSVReader csvReader = new SimpleCSVReader();
        csvReader.getRows();
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkConstructorWithNotExistFile() {
        new SimpleCSVReader(new File("notExistingFile"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkConstructorWithWrongPathToFile() {
        new SimpleCSVReader("notExistingFile");
    }

    @Test
    public void checkConstructorWithPathToFile() throws FileNotFoundException {
        CSVReader csvReader = new SimpleCSVReader("test/resources/essential-mac.csv");
        assertEquals(8, csvReader.getRows().size());
    }

    @Test
    public void parseCVSByPathWithEmptyConstructor() throws FileNotFoundException {
        CSVReader csvReader = new SimpleCSVReader();
        assertEquals(8, csvReader.getRows("test/resources/essential-mac.csv").size());
    }

    @Test(expected = FileNotFoundException.class)
    public void parseCVSByWrongPathWithEmptyConstructor() throws FileNotFoundException {
        CSVReader csvReader = new SimpleCSVReader();
        assertEquals(8, csvReader.getRows("/essential-mac.csv").size());
    }

    @Test
    public void parseCVSByFileWithEmptyConstructor() throws FileNotFoundException {
        CSVReader csvReader = new SimpleCSVReader();
        assertEquals(8, csvReader.getRows(new File("test/resources/essential-mac.csv")).size());
    }

    @Test
    public void checkItemSize() throws Exception {
        List<CSVRow> rows = createCSVReader("test/resources/essential-mac.csv").getRows();
        assertEquals(8, rows.size());
    }

    @Test
    public void checkItemSizeEmptyFile() throws Exception {
        List<CSVRow> rows = createCSVReader("test/resources/empty.csv").getRows();
        assertEquals(0, rows.size());
    }

    @Test
    public void checkFileSizeWithOneItem() throws Exception {
        List<CSVRow> rows = createCSVReader("test/resources/test.csv").getRows();
        assertEquals(1, rows.size());
    }


    @Test
    public void checkFirstItemIndex() throws Exception{
        List<CSVRow> rows = createCSVReader("test/resources/essential-mac.csv").getRows();
        assertEquals(0, rows.get(0).getIndex());
    }

    @Test
    public void checkLastItemIndex() throws Exception{
        List<CSVRow> rows = createCSVReader("test/resources/essential-mac.csv").getRows();
        assertEquals(7, rows.get(rows.size()-1).getIndex());
    }

    @Test
    public void checkRowSize() throws Exception {
        List<CSVRow> rows = createCSVReader("test/resources/test.csv").getRows();
        assertEquals(2, rows.get(0).size());
    }

    @Test
    public void checkRowSizeWithEmptyValue() throws Exception {
        List<CSVRow> rows = createCSVReader("test/resources/empty_value.csv").getRows();
        assertEquals(3, rows.get(0).size());
    }

    @Test
    public void checkRowSizeWithDelimiter() throws Exception {
        CSVReader csvReader = createCSVReader("test/resources/iterating_test.csv");
        csvReader.setSkipEmptyRows(false);
        List<CSVRow> rows = csvReader.getRows();

        assertEquals(3, rows.get(0).size());
    }

    @Test
    public void testRowSizeWithSkipEmptyRow() throws FileNotFoundException {
        CSVReader csvReader = createCSVReader("test/resources/with_empty_rows.csv");
        csvReader.setSkipEmptyRows(true);
        assertEquals(csvReader.getRows().size(), 3);
    }

    private CSVReader createCSVReader(String path) {
        File file = new File(path);
        return new SimpleCSVReader(file);
    }
}