package com.CSV.reader;

import com.CSV.core.BaseCSV;
import com.CSV.core.CSVRow;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * {@link CSVReader} implementation for reading CSV files.
 *
 * @since 1.0
 * @version 1.0
 * @author Nazar Ivanchuk
 */
public class SimpleCSVReader extends BaseCSV implements CSVReader {

    private File file;
    private boolean isNeedSkipEmptyRows;

    //region Constructors
    public SimpleCSVReader() {
        /* empty constructor */
    }

    public SimpleCSVReader(String pathToFile) {
        this(new File(pathToFile));
    }

    public SimpleCSVReader(File file) {
        checkIsFileNull(file);
        this.file = file;
        if(!file.exists()) {
            throw new IllegalArgumentException("Wrong path to CSV file.");
        }
    }
    //endregion

    //region CSVReader
    @Override
    public void setSkipEmptyRows(boolean isNeedSkip) {
        isNeedSkipEmptyRows = isNeedSkip;
    }

    @Override
    public List<CSVRow> getRows() throws FileNotFoundException {
        return readCSVRows(file);
    }

    @Override
    public List<CSVRow> getRows(File file) throws FileNotFoundException {
        checkIsFileNull(file);
        return readCSVRows(file);
    }

    @Override
    public List<CSVRow> getRows(String filePath) throws FileNotFoundException {
        checkIsPathNull(filePath);
        return readCSVRows(new File(filePath));
    }
    //endregion

    //region Utility API
    private List<CSVRow> readCSVRows(File file) throws FileNotFoundException {
        CSVParser parser = new CSVParser(file, separator, delimiter, isNeedSkipEmptyRows);
        List<CSVRow> result;
        try {
             result = parser.getRows();
        } finally {
            parser.close();
        }
        return result;
    }
    //endregion
}
