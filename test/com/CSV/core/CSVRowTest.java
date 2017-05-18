package com.CSV.core;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @since 1.0
 * @version 1.0
 * @author Nazar Ivanchuk
 */
public class CSVRowTest {

    @Test
    public void testRowIndex() {
        CSVRow row = new CSVRow(12,null);
        assertEquals(row.getIndex(), 12);
    }

    @Test
    public void testValuesSize() {
        List<String> values = new ArrayList<String>(){{add("1"); add("2"); add("3");}};
        CSVRow row = new CSVRow(0,values);
        assertEquals(values.size(), row.size());
    }

    @Test
    public void testRowContent() {
        List<String> values = new ArrayList<String>(){{add("1"); add("2"); add("3");}};
        CSVRow row = new CSVRow(0,values);
        assertEquals(values, row.getFields());
    }

    @Test
    public void testRowEquals() {
        List<String> values = new ArrayList<String>(){{add("1"); add("2"); add("3");}};
        CSVRow row = new CSVRow(0,values);
        CSVRow row1 = new CSVRow(0,values);
        assertEquals(row, row1);
    }

    @Test
    public void testRowNotEquals() {
        List<String> values = new ArrayList<String>(){{add("1"); add("2"); add("3");}};
        CSVRow row = new CSVRow(0,values);
        CSVRow row1 = new CSVRow(1,values);
        assertNotEquals(row, row1);
    }

    @Test
    public void testRowHashCode() {
        List<String> values = new ArrayList<String>(){{add("1"); add("2"); add("3");}};
        CSVRow row = new CSVRow(0,values);
        CSVRow row1 = new CSVRow(0,values);
        assertEquals(row.hashCode(), row1.hashCode());
    }

    @Test
    public void testRowNotEqualsHashCode() {
        List<String> values = new ArrayList<String>(){{add("1"); add("2"); add("3");}};
        CSVRow row = new CSVRow(0,values);
        CSVRow row1 = new CSVRow(1,values);
        assertNotEquals(row.hashCode(), row1.hashCode());
    }


    @Test
    public void testCSVRowIterable() {
        List<String> values = new ArrayList<String>(){{add("1"); add("2"); add("3");}};
        CSVRow csvRow = new CSVRow(1,values);
        boolean same = true;
        Iterator<String> iterator = values.iterator();
        Iterator<String> desIterator = csvRow.iterator();
        while(iterator.hasNext()){
            if(!iterator.next().equals(desIterator.next())){
                same = false;
                break;
            }
        }
        assertEquals(same,true);
    }
}