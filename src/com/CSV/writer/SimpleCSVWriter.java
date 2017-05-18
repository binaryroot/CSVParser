package com.CSV.writer;

import com.CSV.core.BaseCSV;
import java.io.*;
import java.util.Collection;

/**
 * {@link CSVWriter} implementation for writing data to CSV files.
 *
 * @since 1.0
 * @version 1.0
 * @author Nazar Ivanchuk
 */
public class SimpleCSVWriter extends BaseCSV implements CSVWriter {

    private File file;

    /**
     * {@code true} if the file is opened for append.
     */
    private boolean append;

    //region Constructors
    public SimpleCSVWriter() {
        /* empty constructor */
    }

    public SimpleCSVWriter(String pathToFile) {
        this(new File(pathToFile));
    }

    public SimpleCSVWriter(File file) {
        checkIsFileNull(file);
        this.file = file;
        if(!file.exists()) {
            throw new IllegalArgumentException("Wrong path to CSV file.");
        }
    }
    //endregion


    //region CSVWriter
    @Override
    public void setAppend(boolean append) {
        this.append = append;
    }

    @Override
    public boolean isAppend() {
        return append;
    }

    @Override
    public void write(Collection<String[]> data) throws IOException {
        write(file, data);
    }

    @Override
    public void write(String[] data) throws IOException {
        write(file, data);
    }

    @Override
    public void write(String path, Collection<String[]> data) throws IOException {
        write(new File(path), data);
    }

    @Override
    public void write(String path, String[] data) throws IOException {
        write(new File(path), data);
    }

    @Override
    public void write(File file, Collection<String[]> data) throws IOException {
        CSVWriterUtility csvWriterUtility = null;
        try {
            csvWriterUtility = createCSVWriterUtility(createWriter(file));
            csvWriterUtility.writeLines(data);
        } finally {
            flushAndCloseWriter(csvWriterUtility);
        }
    }

    @Override
    public void write(File file, String[] data) throws IOException {
        CSVWriterUtility csvWriterUtility = null;
        try {
            csvWriterUtility = createCSVWriterUtility(createWriter(file));
            csvWriterUtility.writeLines(data);
        } finally {
            flushAndCloseWriter(csvWriterUtility);
        }
    }
    //endregion

    //region Utility API
    private Writer createWriter(final File file) throws IOException {
        return new FileWriter(file, append);
    }

    private CSVWriterUtility createCSVWriterUtility(Writer writer) {
        return new CSVWriterUtility(writer, separator, delimiter);
    }

    private void flushAndCloseWriter(CSVWriterUtility csvWriterUtility) throws IOException {
        if(csvWriterUtility == null) {
            return;
        }
        csvWriterUtility.flush();
        csvWriterUtility.close();
    }

    //endregion
}
