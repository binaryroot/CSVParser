package com.CSV.core;

import java.util.Iterator;
import java.util.List;

/**
 * {@link CSVRow} expresses row in csv file.
 *
 * @since 1.0
 * @version 1.0
 * @author Nazar Ivanchuk
 */
public class CSVRow implements Iterable<String> {

    private final long index;
    private final List<String> fields;

    public CSVRow(long index, List<String> fields) {
        this.index = index;
        this.fields = fields;
    }

    /**
     * Returns row index in csv file.
     *
     * @return index
     */
    public long getIndex() {
        return index;
    }

    /**
     * Returns list of strings, which represent csv values.
     *
     * @return row fields
     */
    public List<String> getFields() {
        return fields;
    }

    //region Iterable
    @Override
    public Iterator<String> iterator() {
        return fields.iterator();
    }
    //endregion

    /**
     * Returns size of csv row.
     *
     * @return size
     */
    public int size() {
        return fields.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CSVRow csvRow = (CSVRow) o;

        if (index != csvRow.index) return false;
        return fields != null ? fields.equals(csvRow.fields) : csvRow.fields == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (index ^ (index >>> 32));
        result = 31 * result + (fields != null ? fields.hashCode() : 0);
        return result;
    }
}
