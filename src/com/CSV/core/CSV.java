package com.CSV.core;

/**
 * Base CSV interface for read and write implementations.
 *
 * @since 1.0
 * @version 1.0
 * @author Nazar Ivanchuk
 */
public interface CSV {

    /**
     * Sets field separator value. By default used {@link com.CSV.Constant#FIELD_SEPARATOR}.
     *
     * @param fieldSeparator value
     */
    void setFieldSeparator(final char fieldSeparator);

    /**
     * Sets text delimiter. By default used {@link com.CSV.Constant#TEXT_DELIMITER}
     *
     * @param textDelimiter value
     */
    void setTextDelimiter(final char textDelimiter);

}
