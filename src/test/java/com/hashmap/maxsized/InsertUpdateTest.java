package com.hashmap.maxsized;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InsertUpdateTest {
    private InsertUpdate<Integer, String> insertUpdate;
    private Set<Integer> keys;

    @BeforeEach
    public void setUp() throws Exception {
        insertUpdate = new InsertUpdate<>(5);
        insertUpdate.put(1, "1");
        insertUpdate.put(2, "2");
        insertUpdate.put(3, "3");
        insertUpdate.put(4, "4");
        insertUpdate.put(5, "5");

        keys = insertUpdate.keySet();
    }

    @Test
    public void testInitialOrder() {
        //checking the elements order
        assertEquals("[1, 2, 3, 4, 5]", keys.toString());

        //checking the most recent element
        assertEquals("5", insertUpdate.getMostRecent());
    }

    @Test
    public void testGetOperation() {
        //Accessing the element does not change its position.
        insertUpdate.get(3);
        assertEquals("[1, 2, 3, 4, 5]", keys.toString());

        //checking the most recent element
        assertEquals("5", insertUpdate.getMostRecent());
    }

    @Test
    public void testReplaceOperation() {
        //After replacing the element, it must be moved to the last position.
        insertUpdate.replace(1, "one");
        assertEquals("[2, 3, 4, 5, 1]", keys.toString());
        assertEquals("one", insertUpdate.getMostRecent());
    }

    @Test
    public void testReplaceNonExistingElement() {
        //Trying to replace an invalid element does not modify the list.
        Set<Integer> keys = insertUpdate.keySet();
        insertUpdate.replace(7, "7");
        assertEquals("[1, 2, 3, 4, 5]", keys.toString());
        assertEquals("5", insertUpdate.getMostRecent());
    }

    @Test
    public void testPutOperation() {
        //When inserting a new element, since the maximum HashMap
        //size has been exceeded, the eldest element must be removed.
        insertUpdate.put(6, "6");
        assertEquals("[2, 3, 4, 5, 6]", keys.toString());
        assertEquals("6", insertUpdate.getMostRecent());

        //When replacing an element using put,
        //it must be moved to the last list position.
        insertUpdate.put(4, "4");
        assertEquals("[2, 3, 5, 6, 4]", keys.toString());
        assertEquals("4", insertUpdate.getMostRecent());
    }
}