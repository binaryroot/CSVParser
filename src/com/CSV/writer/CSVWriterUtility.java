package com.CSV.writer;

import com.CSV.Constant;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;
import java.util.Collection;

/**
 * Utility class for writing to CSV file via {@link Writer}.
 *
 * @since 1.0
 * @version 1.0
 * @author Nazar Ivanchuk
 */
final class CSVWriterUtility implements Closeable, Flushable {

    private final Writer writer;
    private final char fieldSeparator;
    private final char textDelimiter;
    private boolean newLine = true;


    CSVWriterUtility(final Writer writer, final char fieldSeparator, final char textDelimiter) {
        this.writer = writer;
        this.fieldSeparator = fieldSeparator;
        this.textDelimiter = textDelimiter;
    }

    //region Closeable
    /**
     * {@inheritDoc}
     */
    @Override
    public void close() throws IOException {
        writer.close();
    }
    //endregion

    //region Flushable
    /**
     * {@inheritDoc}
     */
    @Override
    public void flush() throws IOException {
        writer.flush();
    }
    //endregion

    /**
     * Writes data to CSV file.
     *
     * @param values to write
     * @throws IOException if write error
     */
    void writeLines(final Collection<String[]> values) throws IOException {
        for (String[] value : values) {
            writeLines(value);
        }
        endLine();
    }

    /**
     * Writes data to CSV file.
     *
     * @param values to write
     * @throws IOException if a write error
     */
    void writeLines(final String... values) throws IOException {
        for (final String value : values) {
            appendLine(value);
        }
        endLine();
    }

    //region Utility API
    private void appendLine(final String value) throws IOException {
        if (!newLine) {
            writer.write(fieldSeparator);
        } else {
            newLine = false;
        }
        if (value == null) {
            return;
        }
        final char[] valueChars = value.toCharArray();
        boolean containsTextDelimiter = false;
        for (final char c : valueChars) {
            if (c == textDelimiter) {
                containsTextDelimiter  = true;
                break;
            }
        }
        if (containsTextDelimiter) {
            for (final char c : valueChars) {
                if (c == textDelimiter) {
                    writer.write(textDelimiter);
                }
                writer.write(c);
            }
        } else {
            writer.write(valueChars);
        }
    }

    /**
     * Appends new line character(s) to the current line.
     *
     * @throws IOException if a write error occurs
     */
    private void endLine() throws IOException {
        writer.write(Constant.LINE_DELIMITER);
        newLine = true;
    }
    //endregion
}
