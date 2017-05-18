package com.CSV.core;

import com.CSV.Constant;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * @since 1.0
 * @version 1.0
 * @author Nazar Ivanchuk
 */
public class BaseCSVTest {

    @Test
    public void checkDefaultSeparator() {
        BaseCSV baseCSV = new TestBaseCSVImpl();
        assertEquals(Constant.FIELD_SEPARATOR, baseCSV.separator);
    }

    @Test
    public void checkDefaultDelimiter() {
        BaseCSV baseCSV = new TestBaseCSVImpl();
        assertEquals(Constant.TEXT_DELIMITER, baseCSV.delimiter);
    }

    @Test
    public void checkCustomSeparatorViaMethod() {
        BaseCSV baseCSV = new TestBaseCSVImpl();
        baseCSV.setFieldSeparator('#');
        assertEquals('#', baseCSV.separator);
    }

    @Test
    public void checkCustomDelimiterViaMethod() {
        BaseCSV baseCSV = new TestBaseCSVImpl();
        baseCSV.setTextDelimiter('&');
        assertEquals('&', baseCSV.delimiter);
    }

    @Test
    public void checkCustomSeparatorViaParameter() {
        BaseCSV baseCSV = new TestBaseCSVImpl();
        baseCSV.separator = '#';
        assertEquals('#', baseCSV.separator);
    }

    @Test
    public void checkCustomDelimiterViaParameter() {
        BaseCSV baseCSV = new TestBaseCSVImpl();
        baseCSV.delimiter = '&';
        assertEquals('&', baseCSV.delimiter);
    }

    @Test(expected = NullPointerException.class)
    public void checkIsFileNull() {
        BaseCSV baseCSV = new TestBaseCSVImpl();
        baseCSV.checkIsFileNull(null);
    }

    @Test
    public void checkIsFileNotNull() {
        BaseCSV baseCSV = new TestBaseCSVImpl();
        baseCSV.checkIsFileNull(new File("test/resources/with_empty_rows.csv"));
    }

    @Test(expected = NullPointerException.class)
    public void checkIsPathNull() {
        BaseCSV baseCSV = new TestBaseCSVImpl();
        baseCSV.checkIsPathNull(null);
    }

    @Test
    public void checkIsPathNotNull() {
        BaseCSV baseCSV = new TestBaseCSVImpl();
        baseCSV.checkIsPathNull("test/resources/with_empty_rows.csv");
    }
}