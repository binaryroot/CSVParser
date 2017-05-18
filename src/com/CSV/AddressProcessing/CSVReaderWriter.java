package com.CSV.AddressProcessing;

import com.CSV.core.CSVRow;
import com.CSV.reader.CSVReader;
import com.CSV.reader.SimpleCSVReader;
import com.CSV.writer.CSVWriter;
import com.CSV.writer.SimpleCSVWriter;
import java.io.IOException;
import java.util.List;

/**
 * Deprecated implementation of CSV parser.
 *
 * For CSV reader use {@link CSVReader}.
 * For CSV writer use {@link CSVWriter}.
 * Use new implementation, this one will be removed in future.
 *
 * @since 1.0
 * @version 1.0
 * @author Nazar Ivanchuk
 */
@Deprecated
public class CSVReaderWriter {

    private static final char SEPARATOR = '\t';

    private CSVReader csvReader;
    private CSVWriter csvWriter;
    private String fileName;

    @Deprecated
    public void open(String fileName, Mode mode) throws Exception {
        this.fileName = fileName;
    }
    /**
     * This method does not supported anymore.
     * Use {@link CSVWriter#write(String[])} class.
     */
    @Deprecated
    public void write(String... columns) throws IOException {
        csvWriter = new SimpleCSVWriter(fileName);
        csvWriter.setFieldSeparator(SEPARATOR);
        csvWriter.write(columns);
    }

    /**
     * This method does not supported anymore.
     * Use {@link CSVReader} class.
     */
    @Deprecated
    public boolean read(String[] columns) throws IOException {
        if(columns == null) {
            return false;
        }
        csvReader = new SimpleCSVReader(fileName);
        csvReader.setFieldSeparator(SEPARATOR);
        List<CSVRow> row  = csvReader.getRows();
        if(row.size() == 0) {
            return false;
        } else {
            String source[] = row.get(0).getFields().toArray(new String[0]);
            System.arraycopy(source,0,columns,0,Math.min(source.length,columns.length));
        }
        return true;
    }

    @Deprecated
    public void close() throws IOException {
        csvReader = null;
        csvWriter = null;
    }

    /**
     * This method doesn't work. Because strings can not be passed be reference.
     * By documentation string is immutable object.
     *
     * Use {@link CSVReader} class.
     */
    @Deprecated
    public boolean read(String column1, String column2) throws IOException {
        return false;
    }

    @Deprecated
    public enum Mode {

        Read (1), Write(2);

        private final int _mode;
        Mode(int mode) {
            this._mode = mode;
        }
        public int getMode() {
            return _mode;
        }
    }
}
