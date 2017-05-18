package com.CSV.core;

import com.CSV.Constant;

import java.io.File;
import java.util.Objects;

/**
 * Base class for CSV parser and writer.
 *
 * @since 1.0
 * @version 1.0
 * @author Nazar Ivanchuk
 */
public abstract class BaseCSV implements CSV {

    /**
     * For javadoc see {@link Constant#FIELD_SEPARATOR}
     */
    protected char separator = Constant.FIELD_SEPARATOR;

    /**
     * For javadoc see {@link Constant#TEXT_DELIMITER}
     */
    protected char delimiter = Constant.TEXT_DELIMITER;

    //region CSV
    @Override
    public void setFieldSeparator(char fieldSeparator) {
        this.separator = fieldSeparator;
    }

    @Override
    public void setTextDelimiter(char textDelimiter) {
        this.delimiter = textDelimiter;
    }
    //endregion

    //region Utility API
    protected final void checkIsFileNull(File file) {
        Objects.requireNonNull(file, "File must not be null");
    }

    protected final void checkIsPathNull(String filePath) {
        Objects.requireNonNull(filePath, "File path must not be null");
    }
    //endregion
}
