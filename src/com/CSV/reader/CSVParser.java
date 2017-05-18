package com.CSV.reader;

import com.CSV.core.CSVRow;

import java.io.*;
import java.util.*;

/**
 * Utility class for parsing CSV data.
 *
 * @since 1.0
 * @version 1.0
 * @author Nazar Ivanchuk
 */
final class CSVParser implements Closeable {

    private final Scanner scanner;
    private final char fieldSeparator;
    private final char textDelimiter;
    private final boolean skipEmptyRows;

    /**
     * CSVParser constructor.
     *
     * @param file to parse
     * @param fieldSeparator csv separator
     * @param textDelimiter csv delimiter
     * @param skipEmptyRows if {@code true} empty rows will be skipped otherwise will be added
     * @throws FileNotFoundException
     */
    CSVParser(File file, char fieldSeparator, char textDelimiter, boolean skipEmptyRows) throws FileNotFoundException {
        this.scanner = new Scanner(file);
        this.fieldSeparator = fieldSeparator;
        this.textDelimiter = textDelimiter;
        this.skipEmptyRows = skipEmptyRows;
    }

    /**
     * @return list of csv rows.
     */
    List<CSVRow> getRows() {
        List<CSVRow> result = new ArrayList<>();
        int index = 0;
        while (scanner.hasNext()) {
            List<String> line = parseLine(scanner.nextLine());

            if(skipEmptyRows && line.size() == 0) {
                continue;
            }

            result.add(new CSVRow(index++, line));
        }
        return Collections.unmodifiableList(result);
    }

    //region Closeable
    @Override
    public void close() {
        scanner.close();
    }
    //endregion

    //region Utility API
    List<String> parseLine(String cvsLine) {
        List<String> result = new ArrayList<>();
        //if empty, return.
        if (cvsLine == null || cvsLine.isEmpty()) {
            return result;
        }
        StringBuffer curVal = new StringBuffer();
        boolean inQuotes = false;
        boolean startCollectChar = false;
        boolean doubleQuotesInColumn = false;

        char[] chars = cvsLine.toCharArray();
        for (char ch : chars) {
            if (inQuotes) {
                startCollectChar = true;
                if (ch == textDelimiter) {
                    inQuotes = false;
                    doubleQuotesInColumn = false;
                } else {
                    if (ch == '\"') {
                        if (!doubleQuotesInColumn) {
                            curVal.append(ch);
                            doubleQuotesInColumn = true;
                        }
                    } else {
                        curVal.append(ch);
                    }
                }
            } else {
                if (ch == textDelimiter) {
                    inQuotes = true;
                    if (chars[0] != '"' && textDelimiter == '\"') {
                        curVal.append('"');
                    }
                    if (startCollectChar) {
                        curVal.append('"');
                    }
                } else if (ch == fieldSeparator) {
                    result.add(curVal.toString());
                    curVal = new StringBuffer();
                    startCollectChar = false;
                } else {
                    curVal.append(ch);
                }
            }
        }

        result.add(curVal.toString());
        return result;
    }
    //endregion
}
