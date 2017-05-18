package com.CSV;

/**
 * Contains variables which used in the library.
 *
 * @since 1.0
 * @version 1.0
 * @author Nazar Ivanchuk
 */
public final class Constant {

    /**
     * Default separator value for CSV format by RFC4180.
     */
    public static final char FIELD_SEPARATOR = ',';

    /**
     * Default delimiter value for CSV format by RFC4180.
     */
    public static final char TEXT_DELIMITER = '"';

    /**
     * The line delimiter character(s) to be used (default: {@link System#lineSeparator()}).
     */
    public static final char[] LINE_DELIMITER = System.lineSeparator().toCharArray();
}
