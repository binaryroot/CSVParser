package com.CSV.writer;

import com.CSV.core.CSV;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

/**
 * {@link CSV} implementation for writing to CSV file.
 * Creates a output file to write the CSV data.
 * If set append mode in code {@code true}, then data will be written to the end of the file
 * rather than the beginning.
 *
 * @since 1.0
 * @version 1.0
 * @author Nazar Ivanchuk
 */
public interface CSVWriter extends CSV {

    /**
     * Sets mode to append data to written file.
     *
     * @param append boolean if {@code true}, then data will be written
     *                  to the end of the file rather than the beginning.
     */
    void setAppend(boolean append);

    /**
     * Returns boolean mode, which says whether append mode was enabled.
     *
     * @return {@code true} in case append mode otherwise {@code true}
     */
    boolean isAppend();

    /**
     * Writes data to CSV {@link java.io.File} which was set in constructor.
     *
     * @param data to save
     */
    void write(final Collection<String[]> data) throws IOException;

    /**
     * Writes data to CSV {@link java.io.File} which was set in constructor.
     *
     * @param data to save
     */
    void write(final String[] data) throws IOException;

    /**
     * Writes data to CSV file by specific path.
     *
     * @param path to file
     * @param data to save
     */
    void write(final String path, final Collection<String[]> data) throws IOException;

    /**
     * Writes data to CSV file by specific path.
     *
     * @param path to file
     * @param data to save
     */
    void write(final String path, String[] data) throws IOException;

    /**
     * Writes data to specific file.
     *
     * @param file in which data will be saved
     * @param data to save
     */
    void write(final File file, final Collection<String[]> data) throws IOException;

    /**
     * Writes data to specific file.
     *
     * @param file in which data will be saved
     * @param data to save
     */
    void write(final File file, String[] data) throws IOException;

}
