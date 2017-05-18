package com.CSV.writer;

import com.CSV.Constant;
import com.CSV.core.CSVRow;
import com.CSV.reader.CSVReader;
import com.CSV.reader.SimpleCSVReader;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @since 1.0
 * @version 1.0
 * @author Nazar Ivanchuk
 */
public class CSVUtilsTest {
    private FileWriter fileWriter;

    @Before
    public void setUP() throws IOException {
        File test = new File("test/resources/write/test.csv");
        test.delete();
        test.createNewFile();
        fileWriter = new FileWriter(test);
    }

    @Test
    public void writeSingleValue() throws Exception {
        List<String> valuesToWrite = new ArrayList<String>() {{add("Nazar"); add("Roman"); add("Mask");}};
        CSVWriterUtility d = new CSVWriterUtility(fileWriter, Constant.FIELD_SEPARATOR, Constant.TEXT_DELIMITER);
        d.writeLines(valuesToWrite.toArray(new String[0]));
        d.flush();
        d.close();
        CSVReader csvReader = new SimpleCSVReader("test/resources/write/test.csv");
        assertEquals(csvReader.getRows().get(0).getFields(), valuesToWrite);
    }

    @Test
    public void writeEmptyValue() throws Exception {
        CSVWriterUtility d = new CSVWriterUtility(fileWriter, Constant.FIELD_SEPARATOR, Constant.TEXT_DELIMITER);
        d.writeLines();
        d.flush();
        d.close();
        CSVReader csvReader = new SimpleCSVReader("test/resources/write/test.csv");
        assertEquals(csvReader.getRows().size(), 0);
    }

    @Test
    public void writeSingleValueWithNull() throws Exception {
        List<String> valuesToWrite = new ArrayList<String>() {{add("Nazar"); add(null); add("Mask");}};
        CSVWriterUtility d = new CSVWriterUtility(fileWriter, Constant.FIELD_SEPARATOR, Constant.TEXT_DELIMITER);
        d.writeLines(valuesToWrite.toArray(new String[0]));
        d.flush();
        d.close();
        CSVReader csvReader = new SimpleCSVReader("test/resources/write/test.csv");
        List<CSVRow> csvRows  = csvReader.getRows();
        assertEquals(csvRows.size(), 1);
        assertEquals(csvRows.get(0).size(), valuesToWrite.size());
        for (int i = 0; i < csvRows.get(0).getFields().size(); i++) {
            String value = csvRows.get(0).getFields().get(i);
            if(value.equals("")) {
                assertEquals(valuesToWrite.get(i),null);
            } else {
                assertEquals(valuesToWrite.get(i),value);
            }
        }
    }

    @Test
    public void writeSingleValueWithDelimiterInText() throws Exception {
        List<String> valuesToWrite = new ArrayList<String>() {{add("Na\"zar"); add("Roman"); add("Mask");}};
        CSVWriterUtility d = new CSVWriterUtility(fileWriter, Constant.FIELD_SEPARATOR, Constant.TEXT_DELIMITER);
        d.writeLines(valuesToWrite.toArray(new String[0]));
        d.flush();
        d.close();
        CSVReader csvReader = new SimpleCSVReader("test/resources/write/test.csv");
        assertEquals(csvReader.getRows().get(0).getFields(), valuesToWrite);
    }

    @Test
    public void writeSingleValueWithCustomDelimiter() throws Exception {
        List<String> valuesToWrite = new ArrayList<String>() {{add("Na\"zar"); add("Roman"); add("Mask");}};
        CSVWriterUtility d = new CSVWriterUtility(fileWriter, Constant.FIELD_SEPARATOR, '@');
        d.writeLines(valuesToWrite.toArray(new String[0]));
        d.flush();
        d.close();
        CSVReader csvReader = new SimpleCSVReader("test/resources/write/test.csv");
        csvReader.setTextDelimiter('@');
        assertEquals(csvReader.getRows().get(0).getFields(), valuesToWrite);
    }

    @Test
    public void writeSingleValueWithCustomSeparator() throws Exception {
        List<String> valuesToWrite = new ArrayList<String>() {{add("Na\"zar"); add("Roman"); add("Mask");}};
        CSVWriterUtility d = new CSVWriterUtility(fileWriter, '^', Constant.TEXT_DELIMITER);
        d.writeLines(valuesToWrite.toArray(new String[0]));
        d.flush();
        d.close();
        CSVReader csvReader = new SimpleCSVReader("test/resources/write/test.csv");
        csvReader.setFieldSeparator('^');
        assertEquals(csvReader.getRows().get(0).getFields(), valuesToWrite);
    }

    @Test
    public void writeSingleValueWithCustomDelimiterAndSeparator() throws Exception {
        List<String> valuesToWrite = new ArrayList<String>() {{add("Na\"zar"); add("Roman"); add("Mask");}};
        CSVWriterUtility d = new CSVWriterUtility(fileWriter, '^','@');
        d.writeLines(valuesToWrite.toArray(new String[0]));
        d.flush();
        d.close();
        CSVReader csvReader = new SimpleCSVReader("test/resources/write/test.csv");
        csvReader.setFieldSeparator('^');
        csvReader.setTextDelimiter('@');
        assertEquals(csvReader.getRows().get(0).getFields(), valuesToWrite);
    }

    @Test
    public void writeArrayValue() throws Exception {
        CSVWriterUtility d = new CSVWriterUtility(fileWriter, Constant.FIELD_SEPARATOR, Constant.TEXT_DELIMITER);
        List<String[]> valuesToWrite = new ArrayList<>();
        valuesToWrite.add(new String[]{"Nazar","Roman","Mask"});
        valuesToWrite.add(new String[]{"Nazar1","Roman1","Mask1"});
        valuesToWrite.add(new String[]{"Nazar2","Roman2","Mask1"});
        d.writeLines(valuesToWrite);
        d.flush();
        d.close();
        CSVReader csvReader = new SimpleCSVReader("test/resources/write/test.csv");
        List<CSVRow> readRows = csvReader.getRows();
        assertEquals(readRows.size(), valuesToWrite.size());
        for (int i = 0; i < readRows.size(); i++) {
            CSVRow row = readRows.get(i);
            for (int j = 0; j < row.getFields().size(); j++) {
                assertEquals(row.getFields().get(j), valuesToWrite.get(i)[j]);
            }
        }
    }
}