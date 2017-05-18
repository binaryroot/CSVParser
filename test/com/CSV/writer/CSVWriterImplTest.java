package com.CSV.writer;

import com.CSV.reader.CSVReader;
import com.CSV.reader.SimpleCSVReader;
import org.junit.Test;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;

/**
 * @since 1.0
 * @version 1.0
 * @author Nazar Ivanchuk
 */
public class CSVWriterImplTest {

    @Test(expected = NullPointerException.class)
    public void checkEmptyConstructor() throws IOException {
        CSVWriter csvReader = new SimpleCSVWriter();
        csvReader.write(new String[]{});
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkConstructorWithNotExistFile() {
        new SimpleCSVWriter(new File("notExistingFile"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkConstructorWithWrongPathToFile() {
        new SimpleCSVWriter("notExistingFile");
    }

    @Test
    public void checkWriteData() throws IOException {
        CSVWriter d = new SimpleCSVWriter(createTestFile());
        d.write(getTestData());
        CSVReader csvReader = new SimpleCSVReader("test/resources/write/test.csv");
        assertEquals(csvReader.getRows().size(), getTestData().size());
    }

    @Test
    public void checkAppendModeDisable() {
        CSVWriter d =new SimpleCSVWriter();
        assertEquals(d.isAppend(), false);
    }

    @Test
    public void checkAppendModeEnable() {
        CSVWriter d =new SimpleCSVWriter();
        d.setAppend(true);
        assertEquals(d.isAppend(), true);
    }

    @Test
    public void checkWriteDataByPathCollection() throws IOException {
        File test = new File("test/resources/write/test.csv");
        test.delete();
        CSVWriter d = new SimpleCSVWriter();
        d.write("test/resources/write/test.csv",getTestData());
        CSVReader csvReader = new SimpleCSVReader("test/resources/write/test.csv");
        assertEquals(csvReader.getRows().size(), getTestData().size());
    }

    @Test
    public void checkWriteDataByPathArray() throws IOException {
        File test = new File("test/resources/write/test.csv");
        test.delete();
        String[] values = new String[]{"Nazar","Mask","Roman"};
        CSVWriter d = new SimpleCSVWriter();
        d.write("test/resources/write/test.csv",values);
        CSVReader csvReader = new SimpleCSVReader("test/resources/write/test.csv");
        assertEquals(csvReader.getRows().size(), 1);
        assertEquals(csvReader.getRows().get(0).size(), 3);

        for (int i = 0; i < values.length; i++) {
            String template = values[i];
            String readValue = csvReader.getRows().get(0).getFields().get(i);
            assertEquals(template,readValue);
        }
    }

    @Test
    public void checkConstructorWithPathToFile() throws IOException {
        List<String> valuesToWrite = new ArrayList<String>() {{add("Nazar"); add("Roman"); add("Mask");}};
        CSVWriter d = new SimpleCSVWriter("test/resources/write/test.csv");
        d.write(valuesToWrite.toArray(new String[0]));
        CSVReader csvReader = new SimpleCSVReader("test/resources/write/test.csv");
        assertEquals(csvReader.getRows().get(0).getFields(), valuesToWrite);
    }

    @Test
    public void checkConstructorWithFile() throws IOException {
        List<String> valuesToWrite = new ArrayList<String>() {{add("Nazar"); add("Roman"); add("Mask");}};
        CSVWriter d = new SimpleCSVWriter(createTestFile());
        d.write(valuesToWrite.toArray(new String[0]));
        CSVReader csvReader = new SimpleCSVReader("test/resources/write/test.csv");
        assertEquals(csvReader.getRows().get(0).getFields(), valuesToWrite);
    }


    @Test
    public void checkWriteDataByPathCollectionWithAppendMode() throws IOException {
        List<String> valuesToWrite = new ArrayList<String>() {{add("Nazar"); add("Roman"); add("Mask");}};
        File testFile = createTestFile();
        CSVWriter d = new SimpleCSVWriter(testFile);
        d.write(valuesToWrite.toArray(new String[0]));
        CSVWriter dd = new SimpleCSVWriter(testFile);
        dd.setAppend(true);
        dd.write(valuesToWrite.toArray(new String[0]));
        CSVReader csvReader = new SimpleCSVReader("test/resources/write/test.csv");
        assertEquals(csvReader.getRows().size(), 2);
    }


    private File createTestFile() throws IOException {
        File test = new File("test/resources/write/test.csv");
        test.delete();
        test.createNewFile();
        return test;
    }

    private List<String[]> getTestData() {
        String[] values = new String[]{"Nazar","Mask","Roman"};
        List<String[]> testCollection = new ArrayList<>();
        testCollection.add(values);
        testCollection.add(values);
        testCollection.add(values);

        return testCollection;
    }
}