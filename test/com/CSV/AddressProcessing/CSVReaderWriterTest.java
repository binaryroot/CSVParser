package com.CSV.AddressProcessing;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * @since 1.0
 * @version 1.0
 * @author Nazar Ivanchuk
 */
public class CSVReaderWriterTest {


    @Test
    public void write() throws Exception {
        CSVReaderWriter csvReaderWriter = new CSVReaderWriter();
        csvReaderWriter.open("test/resources/write/test_support.csv", CSVReaderWriter.Mode.Write);
        String[] values = new String[]{"Nazar","Mask","Roman"};
        csvReaderWriter.write(values);
        String[] valuesToRead = new String[3];
        csvReaderWriter.read(valuesToRead);
        for (int i = 0; i < values.length; i++) {
            assertEquals(values[i], valuesToRead[i]);
        }
    }

    @Test
    public void readWithNullArray() throws IOException {
        CSVReaderWriter csvReaderWriter = new CSVReaderWriter();
        assertEquals(false,csvReaderWriter.read(null));
    }

    @Test
    public void readEmptyFileArray() throws Exception {
        CSVReaderWriter csvReaderWriter = new CSVReaderWriter();
        csvReaderWriter.open("test/resources/empty.csv", CSVReaderWriter.Mode.Read);
        assertEquals(false,csvReaderWriter.read(new String[]{}));
    }

    @Test
    public void readWithTwoParameters() throws IOException {
        CSVReaderWriter csvReaderWriter = new CSVReaderWriter();
        assertEquals(false, csvReaderWriter.read(null,null));
    }

    @Test
    public void testReaderWriterMode()  {
        CSVReaderWriter.Mode mode = CSVReaderWriter.Mode.Read;
        assertEquals(mode.getMode(), CSVReaderWriter.Mode.Read.getMode());
    }

    @Test
    public void close() throws Exception {
        CSVReaderWriter csvReaderWriter = new CSVReaderWriter();
        csvReaderWriter.close();
    }
}