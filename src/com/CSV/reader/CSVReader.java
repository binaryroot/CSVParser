package com.CSV.reader;

import com.CSV.core.CSV;
import com.CSV.core.CSVRow;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * {@link CSV} implementation for reading CSV file.
 *
 *
 * @since 1.0
 * @version 1.0
 * @author Nazar Ivanchuk
 */
public interface CSVReader extends CSV {

    /**
     * If {@code true} empty rows will be skipped during parsing, otherwise will be added.
     *
     * @param isNeedSkip is need skip empty rows
     */
    void setSkipEmptyRows(boolean isNeedSkip);

    /**
     * Returns {@link List<CSVRow>}, from {@link java.io.File} which was set in constructor.
     *
     * @return list of rows
     */
    List<CSVRow> getRows() throws FileNotFoundException;

    /**
     * Returns {@link List<CSVRow>}, from specific {@link java.io.File}.
     *
     * @return list of rows
     */
    List<CSVRow> getRows(File file) throws FileNotFoundException;


    /**
     * Returns {@link List<CSVRow>}, from path.
     *
     * @return list of rows
     */
    List<CSVRow> getRows(String filePath) throws FileNotFoundException;
}
