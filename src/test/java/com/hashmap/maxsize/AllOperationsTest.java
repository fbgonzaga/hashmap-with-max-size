package com.hashmap.maxsize;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AllOperationsTest {
    private AllOperations<Integer, String> allOperations;
    private Set<Integer> keys;

    @BeforeEach
    public void setUp() throws Exception {
        allOperations = new AllOperations<>(5);
        allOperations.put(1, "1");
        allOperations.put(2, "2");
        allOperations.put(3, "3");
        allOperations.put(4, "4");
        allOperations.put(5, "5");

        keys = allOperations.keySet();
    }

    @Test
    public void testInitialOrder() {
        //checking the elements order
        assertEquals("[1, 2, 3, 4, 5]", keys.toString());

        //checking the most recent element
        assertEquals("5", allOperations.getMostRecent());
    }

    @Test
    public void testGetOperation() {
        //After accessing the element, it must be moved to the last list position.
        allOperations.get(3);
        assertEquals("[1, 2, 4, 5, 3]", keys.toString());

        //checking the most recent element
        assertEquals("3", allOperations.getMostRecent());
    }

    @Test
    public void testReplaceOperation() {
        //After replacing the element, it must be moved to the last list position.
        allOperations.replace(1, "one");
        assertEquals("[2, 3, 4, 5, 1]", keys.toString());
        assertEquals("one", allOperations.getMostRecent());
    }

    @Test
    public void testReplaceNonExistingElement() {
        //Trying to replace an invalid element does not modify the list.
        Set<Integer> keys = allOperations.keySet();
        allOperations.replace(7, "7");
        assertEquals("[1, 2, 3, 4, 5]", keys.toString());
        assertEquals("5", allOperations.getMostRecent());
    }

    @Test
    public void testPutOperation() {
        //When inserting a new element, since the maximum HashMap
        //size has been exceeded, the eldest element must be removed.
        allOperations.put(6, "6");
        assertEquals("[2, 3, 4, 5, 6]", keys.toString());
        assertEquals("6", allOperations.getMostRecent());

        //When replacing an element using put,
        //it must be moved to the last list position.
        allOperations.put(4, "4");
        assertEquals("[2, 3, 5, 6, 4]", keys.toString());
        assertEquals("4", allOperations.getMostRecent());
    }
}