import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * This is a a more complete set of tests for SinglyLinkedList.
 * This does not guarantee passing all edge cases, but it tests for a lot of them.
 *
 * @author Vadini Agrawal
 * @version 1.0
 */
public class LinkedListStudentTests {
    private LinkedListInterface<String> list;
    private String[] ideal;

    public static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        list = new SinglyLinkedList<String>();
    }

    @Test(timeout = TIMEOUT)
    public void testAddStrings() {
        assertEquals(0, list.size());
        assertNull(list.getHead());

        list.addAtIndex("0a", 0); //0a
        list.addAtIndex("1a", 1); //0a 1a
        list.addAtIndex("2a", 2); //0a 1a 2a
        list.addAtIndex("3a", 3); //0a 1a 2a 3a

        assertEquals(4, list.size());

        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertEquals("0a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("1a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("2a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("3a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertSame(list.getHead(), current);
    }

    @Test(timeout = TIMEOUT)
    public void testAddOneStringBack() {
        assertEquals(0, list.size());

        list.addToBack("0a");
        assertEquals(1, list.size());

        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertEquals("0a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("0a", current.getData());
    }

    @Test(timeout = TIMEOUT)
    public void testTwoStringBack() {
        assertEquals(0, list.size());

        list.addToBack("0a");
        list.addToBack("1a");
        assertEquals(2, list.size());

        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertEquals("0a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("1a", current.getData());
    }

    @Test(timeout = TIMEOUT)
    public void testAddOneStringFront() {
        assertEquals(0, list.size());

        list.addToFront("0a");
        assertEquals(1, list.size());

        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertEquals("0a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("0a", current.getData());
    }

    @Test(timeout = TIMEOUT)
    public void testTwoStringsFront() {
        assertEquals(0, list.size());

        list.addToFront("0a");
        list.addToFront("1a");
        assertEquals(2, list.size());

        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertEquals("1a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("0a", current.getData());
    }

    @Test(timeout = TIMEOUT)
    public void testAddStringsFront() {
        assertEquals(0, list.size());

        list.addToFront("0a");
        list.addToFront("1a");
        list.addToFront("2a");
        list.addToFront("3a");
        list.addToFront("4a");
        list.addToFront("5a"); //5a 4a 3a 2a 1a 0a

        assertEquals(6, list.size());

        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertEquals("5a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("4a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("3a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("2a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("1a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("0a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertSame(list.getHead(), current);
    }

    @Test(timeout = TIMEOUT)
    public void testAddStringsBack() {
        assertEquals(0, list.size());

        list.addToBack("0a");
        list.addToBack("1a");
        list.addToBack("2a");
        list.addToBack("3a");
        list.addToBack("4a");
        list.addToBack("5a"); //5a 4a 3a 2a 1a 0a

        assertEquals(6, list.size());

        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertEquals("0a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("1a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("2a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("3a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("4a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("5a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertSame(list.getHead(), current);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveStrings() {
        assertEquals(0, list.size());

        list.addAtIndex("0a", 0);
        list.addAtIndex("1a", 1);
        list.addAtIndex("2a", 2);
        list.addAtIndex("3a", 3);
        list.addAtIndex("4a", 4);
        list.addAtIndex("5a", 5); //0a 1a 2a 3a 4a 5a

        assertEquals(6, list.size());

        assertEquals("2a", list.removeAtIndex(2)); //0a 1a 3a 4a 5a
        assertEquals("5a", list.removeAtIndex(4));

        assertEquals(4, list.size());

        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertEquals("0a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("1a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("3a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("4a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("0a", current.getData());

        /*current = current.getNext();
        assertNotNull(current);
        assertSame(list.getHead(), current);*/
    }

    @Test(timeout = TIMEOUT)
    public void testGetGeneral() {
        list.addAtIndex("0a", 0);
        list.addAtIndex("1a", 1);
        list.addAtIndex("2a", 2);
        list.addAtIndex("3a", 3);
        list.addAtIndex("4a", 4); //0a 1a 2a 3a 4a

        assertEquals("0a", list.get(0));
        assertEquals("1a", list.get(1));
        assertEquals("2a", list.get(2));
        assertEquals("3a", list.get(3));
        assertEquals("4a", list.get(4));
    }

    @Test(timeout = TIMEOUT)
    public void testToArray() {
        String[] expectedItems = new String[10];

        for (int x = 0; x < expectedItems.length; x++) {
            expectedItems[x] = "a" + x;
            list.addToBack(expectedItems[x]);
        }

        Object[] array = list.toArray();
        assertArrayEquals(expectedItems, array);
    }

    @Test(timeout = TIMEOUT)
    public void testToArrayEmpty() {
        String[] items = new String[0];

        Object[] array = list.toArray();
        assertArrayEquals(items, array);
    }

    @Test(timeout = TIMEOUT)
    public void testToArraySingleItem() {
        String[] items = new String[1];

        list.addToFront("0a");

        items[0] = "0a";
        assertEquals("0a", list.get(0));
        Object[] array = list.toArray();
        assertArrayEquals(items, array);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void removeLastOccurrenceNullItemPassed() {
        list.removeLastOccurrence(null);
    }

    @Test(timeout = TIMEOUT)
    public void removeLastOccurrenceOneTrue() {
        list.addToFront("0a");
        assertEquals("0a", list.removeLastOccurrence("0a"));

        assertEquals(0, list.size());
    }

    @Test(timeout = TIMEOUT)
    public void removeLastOccurrenceOneFalse() {
        list.addToFront("0a");
        assertEquals(null, list.removeLastOccurrence("1a"));

        assertEquals(1, list.size());
    }

    @Test(timeout = TIMEOUT)
    public void removeLastOccurrence() {
        list.addToFront("0a");
        list.addToFront("1a");
        list.addToFront("2a");
        list.addToFront("0a");
        assertEquals("0a", list.removeLastOccurrence("0a"));

        assertEquals("0a", list.get(0));
        assertEquals("2a", list.get(1));
        assertEquals("1a", list.get(2));

        //assertEquals("0a", list.get(3));
        //assertEquals("2a", list.get(4));
        assertEquals(3, list.size());
    }

    @Test(timeout = TIMEOUT)
    public void removeLastOccurrenceHead() {
        list.addToFront("0a");
        list.addToFront("1a");
        list.addToFront("2a");
        //list.addToFront("0a");
        assertEquals("2a", list.removeLastOccurrence("2a"));

        assertEquals("1a", list.get(0));
        assertEquals("0a", list.get(1));

        //assertEquals("0a", list.get(3));
        //assertEquals("2a", list.get(4));
        assertEquals(2, list.size());
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void getIndexEmptyList() {
        assertEquals(0, list.size());
        list.get(0);

    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void getIndexAtSize() {
        list.addToFront("object");
        assertEquals(1, list.size());
        assertEquals("object", list.get(0));
        list.get(1);
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void getNegIndex() {
        list.get(-1);
    }

    @Test(timeout = TIMEOUT)
    public void getAtIndex() {
        list.addToBack("0a");
        list.addToBack("1a");
        list.addToBack("2a");

        assertEquals(3, list.size());
        assertEquals("0a", list.get(0));
        assertEquals("1a", list.get(1));
        assertEquals("2a", list.get(2));
    }

    @Test(timeout = TIMEOUT)
    public void removeFromFrontOne() {
        list.addToFront("0a");

        assertEquals(1, list.size());
        assertEquals("0a", list.removeFromFront());

        assertEquals(0, list.size());
    }

    @Test(timeout = TIMEOUT)
    public void removeFromFrontMany() {
        list.addToFront("0a");
        list.addToFront("1a");
        list.addToFront("2a");
        list.addToFront("3a");

        assertEquals("3a", list.removeFromFront());
        assertEquals("2a", list.removeFromFront());
        assertEquals("1a", list.removeFromFront());
        assertEquals("0a", list.removeFromFront());
    }

    @Test(timeout = TIMEOUT)
    public void removeFromBackMany() {
        list.addToFront("0a");
        list.addToFront("1a");
        list.addToFront("2a");
        list.addToFront("3a");

        assertEquals("0a", list.removeFromBack());
        assertEquals("1a", list.removeFromBack());
        assertEquals("2a", list.removeFromBack());
        assertEquals("3a", list.removeFromBack());
    }

    @Test(timeout = TIMEOUT)
    public void removeFromBackOne() {
        list.addToFront("0a");

        assertEquals(1, list.size());
        assertEquals("0a", list.removeFromFront());

        assertEquals(0, list.size());
    }

    @Test(timeout = TIMEOUT)
    public void isCircularFront() {
        list.addToFront("5");
        list.addToFront("4");
        list.addToFront("3");

        assertEquals("4", list.getHead().getNext().getData());
        assertEquals("3", list.getHead().getData());
        assertEquals("5", list.getHead().getNext().getNext().getData());
        assertEquals("3", list.getHead().getNext().getNext().getNext().getData());
    }

    @Test(timeout = TIMEOUT)
    public void isCircularBack() {
        list.addToBack("5");
        list.addToBack("4");
        list.addToBack("3");

        assertEquals("5", list.getHead().getData());
        assertEquals("4", list.getHead().getNext().getData());
        assertEquals("3", list.getHead().getNext().getNext().getData());
        assertEquals("5", list.getHead().getNext().getNext().getNext().getData());
    }

    @Test(timeout = TIMEOUT)
    public void addInMiddle() {
        list.addToBack("5");
        list.addToBack("4");
        list.addToBack("3");
        list.addAtIndex("!", 1);

        assertEquals("5", list.getHead().getData());
        assertEquals("!", list.getHead().getNext().getData());
        assertEquals("4", list.getHead().getNext().getNext().getData());
        assertEquals("3", list.getHead().getNext().getNext().getNext().getData());
    }

    @Test (timeout = TIMEOUT)
    public void addAtIndex() {
        ideal = new String[]{"om", "za"};
        assertNull(list.getHead());
        list.addAtIndex("za", 0);
        assertEquals(list.getHead(), list.getHead().getNext());
        list.addAtIndex("om", 0);
        assertArrayEquals(ideal, list.toArray());
        ideal = new String[]{"om", "za", "bam"};
        list.addAtIndex("bam", 2);
        assertArrayEquals(ideal, list.toArray());
    }

    @Test (timeout = TIMEOUT)
    public void addAtIndex2() {
        list.addAtIndex("a", 0);
        list.addAtIndex("b", 1);
        list.addAtIndex("c", 2);
        list.addAtIndex("d", 3);
        list.addAtIndex("e", 4);
        list.addAtIndex("f", 2);
        list.addAtIndex("g", 0);
        list.addAtIndex("h", 7);
        list.addAtIndex("i", 4);
        ideal = new String[]{"g", "a", "b", "f", "i", "c", "d", "e", "h"};
        for (int i = 0; i < 9; i++) {
            assertEquals(list.get(i), ideal[i]);
        }
        assertArrayEquals(ideal, list.toArray());
        assertEquals(9, list.size());
    }

    @Test (timeout = TIMEOUT)
    public void addAtIndexLarger() {
        ideal = new String[20];
        for (int i = 19; i >= 0; i--) {
            list.addAtIndex("" + i, 19 - i);
            ideal[19 - i] = "" + i;
        }
        assertEquals(20, list.size());
        assertArrayEquals(ideal, list.toArray());
    }

    @Test (timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void addAtIndexNull() {
        list.addAtIndex("a", 0);
        list.addAtIndex("b", 1);
        list.addAtIndex(null, 0);
    }

    @Test (timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void addAtIndexLow() {
        list.addAtIndex("a", 0);
        list.addAtIndex("b", 1);
        list.addAtIndex("c", -1);
    }

    @Test (timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void addAtIndexHigh() {
        list.addAtIndex("a", 0);
        list.addAtIndex("b", 1);
        list.addAtIndex("c", 3);
    }

    @Test (timeout = TIMEOUT)
    public void addToFront() {
        assertArrayEquals(new String[0], list.toArray());
        list.addToFront("Did");
        list.addToFront("you");
        list.addToFront("ever");
        list.addToFront("hear");
        list.addToFront("the");
        list.addToFront("tragedy");
        list.addToFront("of");
        list.addToFront("Darth");
        list.addToFront("Plagueis");
        list.addToFront("the");
        list.addToFront("Wise?");
        assertArrayEquals(new String[]{"Wise?", "the", "Plagueis", "Darth",
                        "of", "tragedy", "the", "hear", "ever", "you", "Did"},
                list.toArray());
    }

    @Test (timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void addToFrontNull() {
        list.addToFront("a");
        list.addToFront("b");
        list.addToFront(null);
    }

    @Test (timeout = TIMEOUT)
    public void addToBack() {
        assertArrayEquals(new String[0], list.toArray());
        list.addToBack("I");
        list.addToBack("don't");
        list.addToBack("like");
        list.addToBack("sand.");
        assertArrayEquals(new String[]{"I", "don't", "like", "sand."}, list
                .toArray());
    }

    @Test (timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void addToBackNull() {
        list.addToBack("a");
        list.addToBack("b");
        list.addToBack(null);
    }

    @Test (timeout = TIMEOUT)
    public void removeAtIndex() {
        assertArrayEquals(new String[0], list.toArray());
        list.addToFront("Did");
        list.addToFront("you");
        list.addToFront("ever");
        list.addToFront("hear");
        list.addToFront("the");
        list.addToFront("tragedy");
        list.addToFront("of");
        list.addToFront("Darth");
        list.addToFront("Plagueis");
        list.addToFront("the");
        list.addToFront("Wise?");

        assertEquals("tragedy", list.removeAtIndex(5));
        assertArrayEquals(new String[]{"Wise?", "the", "Plagueis", "Darth",
                "of", "the", "hear", "ever", "you", "Did"}, list.toArray());
        assertEquals("Wise?", list.removeAtIndex(0));
        assertArrayEquals(new String[]{"the", "Plagueis", "Darth",
                "of", "the", "hear", "ever", "you", "Did"}, list.toArray());
        assertEquals("of", list.removeAtIndex(3));
        assertArrayEquals(new String[]{"the", "Plagueis", "Darth",
                "the", "hear", "ever", "you", "Did"}, list.toArray());
        assertEquals("Did", list.removeAtIndex(7));
        assertArrayEquals(new String[]{"the", "Plagueis", "Darth",
                "the", "hear", "ever", "you"}, list.toArray());
        assertEquals("the", list.removeAtIndex(3));
        assertArrayEquals(new String[]{"the", "Plagueis", "Darth",
                "hear", "ever", "you"}, list.toArray());
    }

    @Test (timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void removeAtIndexLow() {
        list.addAtIndex("a", 0);
        list.addAtIndex("b", 1);
        list.addAtIndex("c", -1);
    }

    @Test (timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void removeAtIndexHigh() {
        list.addAtIndex("a", 0);
        list.addAtIndex("b", 1);
        list.removeAtIndex(2);
    }

    @Test (timeout = TIMEOUT)
    public void removeFromFront() {
        ideal = new String[30];
        for (int i = 0; i < 45; i++) {
            list.addToFront("" + i);
        }
        for (int i = 0; i < 30; i++) {
            ideal[29 - i] = "" + i;
        }
        for (int i = 44; i >= 30; i--) {
            assertEquals("" + i, list.removeFromFront());
            assertEquals(i, list.size());
        }
        assertArrayEquals(ideal, list.toArray());
    }

    @Test (timeout = TIMEOUT)
    public void removeFromFrontNull() {
        list.addToBack("a");
        assertEquals("a", list.removeFromFront());
        assertNull(list.removeFromFront());
    }

    @Test (timeout = TIMEOUT)
    public void removeFromBack() {
        list.addAtIndex("Take", 0);
        list.addAtIndex("a", 0);
        list.addAtIndex("seat", 0);
        assertEquals(3, list.size());
        assertEquals("Take", list.removeFromBack());
        assertEquals(2, list.size());
        assertEquals("a", list.removeFromBack());
        assertEquals("seat", list.removeFromBack());
        assertEquals(0, list.size());
    }

    @Test (timeout = TIMEOUT)
    public void removeFromBackNull() {
        list.addToFront("a");
        assertEquals(1, list.size());
        assertEquals("a", list.removeFromBack());
        assertNull(list.removeFromBack());
    }

    @Test (timeout = TIMEOUT)
    public void removeLastOccurrence1() {
        list.addToBack("a");
        list.addToBack("b");
        String c = "c";
        String c2 = "c";
        list.addToFront(c);
        list.addToBack(c2);
        list.addToBack("d");
        assertSame(c2, list.removeLastOccurrence("c"));
        assertArrayEquals(new String[]{"c", "a", "b", "d"}, list.toArray());
    }

    @Test (timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void removeLastOccurrenceNull() {
        list.addToBack("Hello");
        list.addToBack("There.");
        list.removeLastOccurrence(null);
    }

    @Test (timeout = TIMEOUT)
    public void removeLastOccurrenceNoOccurrence() {
        list.addToBack("Hello");
        list.addToBack("There.");
        assertNull(list.removeLastOccurrence("there."));
        list.clear();
        assertNull(list.removeLastOccurrence("whoa"));
    }

    @Test (timeout = TIMEOUT)
    public void get() {
        list.addToBack("It's");
        list.addToBack("coarse");
        list.addToBack("and");
        list.addToBack("rough");
        list.addToBack("and");
        String sand = "irritating.";
        list.addToBack(sand);
        assertEquals("It's", list.get(0));
        assertSame(sand, list.get(5));
        list.removeFromFront();
        list.removeFromFront();
        assertEquals("and", list.get(0));
    }

    @Test (timeout = TIMEOUT)
    public void getCircular() {
        list.addToBack("It's");
        list.addToBack("coarse");
        list.addToBack("and");
        list.addToBack("rough");
        list.addToBack("and");

        //testing circular linking
        LinkedListNode<String> travNode = list.getHead();
        for (int i = 0; i < 4; i++) {
            assertSame(travNode.getNext().getData(), list.get(i + 1));
            travNode = travNode.getNext();
        }
        assertSame(list.getHead(), travNode.getNext());
    }

    @Test (timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void getAtIndexLow() {
        list.addAtIndex("a", 0);
        list.addAtIndex("b", 1);
        list.get(-1);
    }

    @Test (timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void getAtIndexHigh() {
        list.addAtIndex("a", 0);
        list.addAtIndex("b", 1);
        list.get(2);
    }

    @Test (timeout = TIMEOUT)
    public void toArray() {
        assertArrayEquals(new String[0], list.toArray());
        list.addAtIndex("Yo", 0);
        assertArrayEquals(new String[]{"Yo"}, list.toArray());
        list.removeFromBack();
        assertArrayEquals(new String[0], list.toArray());
    }

    @Test (timeout = TIMEOUT)
    public void isEmpty() {
        assertTrue(list.isEmpty());
        list.addAtIndex("", 0);
        assertFalse(list.isEmpty());
    }

    @Test (timeout = TIMEOUT)
    public void clear() {
        list.addToFront("4");
        list.addToFront("8");
        list.addToFront("15");
        list.clear();
        assertNull(list.getHead());
        assertEquals(0, list.size());
    }

    @Test (timeout = TIMEOUT)
    public void size() {
        assertEquals(0, list.size());
        list.addToFront("asdf");
        assertEquals(1, list.size());
        list.addToBack("fdas");
        assertEquals(2, list.size());
        list.removeFromFront();
        assertEquals(1, list.size());
        list.removeFromBack();
        assertEquals(0, list.size());
    }

    @Test (timeout = TIMEOUT)
    public void getHead() {
        assertNull(list.getHead());
        list.addAtIndex("boop", 0);
        assertNotNull(list.getHead());
    }

    @Test(timeout = TIMEOUT)
    public void testCircularityOneElement() {
        list.addAtIndex("0a", 0);
        assertEquals(1, list.size());
        assertNotNull(list.getHead());
        assertEquals(list.getHead(), list.getHead().getNext());
    }

    // Tests that the list is circular in more general case
    @Test(timeout = TIMEOUT)
    public void testCircularityGeneral() {
        for (int i = 0; i < 10; i++) {
            list.addAtIndex(i + "a", i);
        }
        assertEquals(10, list.size());
        LinkedListNode<String> head = list.getHead();
        LinkedListNode<String> nextAfterLast = list.getHead();
        for (int i = 0; i < list.size(); i++) {
            nextAfterLast = nextAfterLast.getNext();
        }
        assertNotNull(nextAfterLast);
        assertEquals(head, nextAfterLast);
    }

    // Tests adding at specific index out of order
    @Test(timeout = TIMEOUT)
    public void testAddStringsIndexNotOrder() {
        list.addToBack("0a");
        list.addToBack("1a");
        list.addToBack("3a");

        assertEquals(3, list.size());
        list.addAtIndex("2a", 2);
        assertEquals(4, list.size());
        LinkedListNode<String> current = list.getHead();
        for (int i = 0; i < 4; i++) {
            assertNotNull(current);
            assertEquals(i + "a", current.getData());
            current = current.getNext();
        }
    }

    // Tests exception when index is negative
    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testAddNegativeIndex() {
        assertEquals(0, list.size());
        list.addAtIndex("0a", 0);
        list.addAtIndex("1a", -1);
    }

    // Tests exception when index is above size
    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testAddTooHighIndex() {
        assertEquals(0, list.size());
        list.addAtIndex("0a", 0);
        list.addAtIndex("2a", 2);
    }

    // Tests adding strings to back
    @Test(timeout = TIMEOUT)
    public void testAddStringsBack1() {
        list.addAtIndex("0a", 0);
        list.addAtIndex("1a", 1);
        list.addAtIndex("2a", 2);
        list.addAtIndex("3a", 3);
        list.addAtIndex("4a", 4);

        assertEquals(5, list.size());

        LinkedListNode<String> current = list.getHead();
        for (int i = 0; i < list.size(); i++) {
            assertNotNull(current);
            assertEquals(i + "a", current.getData());
            current = current.getNext();
        }

        assertNotNull(current);
        assertSame(list.getHead(), current);
    }

    // Tests get with no elements in list
    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testGetEmptyList() {
        assertEquals(0, list.size());
        list.get(0);
    }

    // Tests get with one element
    @Test(timeout = TIMEOUT)
    public void testGetOneElement() {
        list.addAtIndex("0a", 0);
        assertEquals("0a", list.get(0));
    }

    // Tests remove with no elements
    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testRemoveNoElements() {
        assertEquals(0, list.size());
        list.removeAtIndex(0);
    }

    // Tests remove from back with no elements
    @Test(timeout = TIMEOUT)
    public void testRemoveFromBackNoElements() {
        assertEquals(0, list.size());
        assertNull(list.removeFromBack());
    }

    // Tests remove from front with no elements
    @Test(timeout = TIMEOUT)
    public void testRemoveFromFrontNoElements() {
        assertEquals(0, list.size());
        assertNull(list.removeFromFront());
    }

    // Tests remove with one element
    @Test(timeout = TIMEOUT)
    public void testRemoveOneElement() {
        list.addAtIndex("0a", 0);
        assertEquals(1, list.size());
        assertEquals("0a", list.removeAtIndex(0));
        assertEquals(null, list.getHead());
        assertEquals(0, list.size());
    }

    // Tests remove from back with one element
    @Test(timeout = TIMEOUT)
    public void testRemoveFromBackOneElement() {
        list.addAtIndex("0a", 0);
        assertEquals(1, list.size());
        assertEquals("0a", list.removeFromBack());
        assertEquals(null, list.getHead());
        assertEquals(0, list.size());
    }

    // Tests remove from front with one element
    @Test(timeout = TIMEOUT)
    public void testRemoveFromFrontOneElement() {
        list.addAtIndex("0a", 0);
        assertEquals(1, list.size());
        assertEquals("0a", list.removeFromFront());
        assertEquals(null, list.getHead());
        assertEquals(0, list.size());
    }

    // Tests removing from back
    @Test(timeout = TIMEOUT)
    public void testRemoveFromBack() {
        list.addAtIndex("0a", 0);
        list.addAtIndex("1a", 1);
        list.addAtIndex("2a", 2);
        list.addAtIndex("3a", 3);
        list.addAtIndex("4a", 4);
        assertEquals(5, list.size());
        String removedString = list.removeFromBack();
        assertNotNull(removedString);
        assertEquals("4a", removedString);
        assertEquals(4, list.size());
        assertEquals("0a", list.getHead().getData());
    }

    // Tests removing from front
    @Test(timeout = TIMEOUT)
    public void testRemoveFromFront() {
        list.addAtIndex("0a", 0);
        list.addAtIndex("1a", 1);
        list.addAtIndex("2a", 2);
        list.addAtIndex("3a", 3);
        list.addAtIndex("4a", 4);
        assertEquals(5, list.size());
        String removedString = list.removeFromFront();
        assertNotNull(removedString);
        assertEquals("0a", removedString);
        assertEquals(4, list.size());
        assertEquals("1a", list.getHead().getData());
    }

    // Tests remove last occurrence with one copy of data in list
    @Test(timeout = TIMEOUT)
    public void testRemoveLastOccurrenceOneCopy() {
        list.addAtIndex("0a", 0);
        list.addAtIndex("1a", 1);
        list.addAtIndex("2a", 2);
        list.addAtIndex("3a", 3);
        list.addAtIndex("4a", 4);
        assertEquals(5, list.size());

        String removedStringFront = list.removeLastOccurrence("0a");
        assertNotNull(removedStringFront);
        assertEquals("0a", removedStringFront);
        assertEquals(4, list.size());

        String removedStringMiddle = list.removeLastOccurrence("2a");
        assertNotNull(removedStringMiddle);
        assertEquals("2a", removedStringMiddle);
        assertEquals(3, list.size());

        String removedStringBack = list.removeLastOccurrence("4a");
        assertNotNull(removedStringBack);
        assertEquals("4a", removedStringBack);
        assertEquals(2, list.size());

        LinkedListNode<String> head = list.getHead();
        assertNotNull(head);
        assertEquals("1a", head.getData());
        assertNotNull(head.getNext());
        assertEquals("3a", head.getNext().getData());
    }

    // Tests remove last occurrence with duplicated data in list
    @Test(timeout = TIMEOUT)
    public void testRemoveLastOccurrenceMultipleCopies() {
        list.addAtIndex("0a", 0);
        list.addAtIndex("1a", 1);
        list.addAtIndex("2a", 2);
        list.addAtIndex("3a", 3);
        list.addAtIndex("4a", 4);
        list.addAtIndex("4a", 5);
        list.addAtIndex("2a", 6);
        list.addAtIndex("3a", 7);
        list.addAtIndex("0a", 8);
        list.addAtIndex("1a", 9);
        assertEquals(10, list.size());

        String removedString0 = list.removeLastOccurrence("0a");
        assertNotNull(removedString0);
        assertEquals("0a", removedString0);
        assertEquals(9, list.size());

        String[] expectedList0 = new String[]{"0a", "1a", "2a", "3a", "4a",
                "4a", "2a", "3a", "1a"};
        LinkedListNode<String> current0 = list.getHead();
        for (int i = 0; i < list.size(); i++) {
            assertEquals(expectedList0[i], current0.getData());
            current0 = current0.getNext();
        }

        String removedString4 = list.removeLastOccurrence("4a");
        assertNotNull(removedString4);
        assertEquals("4a", removedString4);
        assertEquals(8, list.size());

        String[] expectedList4 = new String[]{"0a", "1a", "2a", "3a",
                "4a", "2a", "3a", "1a"};
        LinkedListNode<String> current4 = list.getHead();
        for (int i = 0; i < list.size(); i++) {
            assertEquals(expectedList4[i], current4.getData());
            current4 = current4.getNext();
        }

        String removedString1 = list.removeLastOccurrence("1a");
        assertNotNull(removedString1);
        assertEquals("1a", removedString1);
        assertEquals(7, list.size());

        String[] expectedList = new String[]{"0a", "1a", "2a", "3a",
                "4a", "2a", "3a"};
        LinkedListNode<String> current = list.getHead();
        for (int i = 0; i < list.size(); i++) {
            assertEquals(expectedList[i], current.getData());
            current = current.getNext();
        }

    }
    private void addAlphabet() {
        char c = 'a';
        for (int i = 0; i < 26; i++) {
            list.addAtIndex(c++ + "", i);
        }
    }
    @Test(timeout = TIMEOUT)
    public void addAtIndex1() {
        list.addAtIndex("a", 0);
        assertEquals(list.getHead().getNext(), list.getHead());
        //Making sure head points back to itself
        list.addAtIndex("b", 1);
        list.addAtIndex("c", 2);
        list.addAtIndex("d", 3);
        String[] ideal = {"a", "b", "c", "d"};
        assertArrayEquals(ideal, list.toArray());
        list.addAtIndex("A", 0);
        list.addAtIndex("B", 2);
        list.addAtIndex("C", 4);
        list.addAtIndex("D", 6);
        String[] ideal2 = {"A", "a", "B", "b", "C", "c", "D", "d"};
        assertArrayEquals(ideal2, list.toArray());
    }

    @Test(timeout = TIMEOUT)
    public void addToBack1() {
        list.addToBack("To be");
        list.addToBack("fair");
        list.addToBack("you");
        list.addToBack("have");
        list.addToBack("to");
        list.addToBack("have a");
        list.addToBack("very");
        list.addToBack("high");
        list.addToBack("IQ to");
        list.addToBack("understand");
        list.addToBack("Rick and");
        list.addToBack("Morty");
        assertEquals(12, list.size());
        String[] ideal = {"To be", "fair", "you", "have", "to", "have a",
                "very", "high", "IQ to", "understand", "Rick and",
                "Morty"};
        assertArrayEquals(ideal, list.toArray());
    }

    @Test(timeout = TIMEOUT)
    public void removeAtIndex1() {
        list.addToBack("a");
        list.removeAtIndex(0);
        assertEquals(0, list.size());
        list.addToBack("b");
        list.addToBack("c");
        list.addToBack("b");
        list.addToBack("c");
        list.removeAtIndex(2);
        list.removeAtIndex(0);
        assertArrayEquals(new Object[]{"c", "c"}, list.toArray());
        list.clear();
        addAlphabet();
        assertEquals("e", list.removeAtIndex(4));
        assertEquals("k", list.removeAtIndex(9));
        assertEquals("z", list.removeAtIndex(23));
        list.removeAtIndex(0);
        assertEquals("b", list.removeAtIndex(0));
    }

    @Test(timeout = TIMEOUT)
    public void removeFromBack1() {
        list.addToBack("a");
        assertEquals(1, list.size());
        assertEquals("a", list.removeFromBack());
        assertEquals(0, list.size());
        assertNull(list.getHead());
        addAlphabet();
        assertEquals("z", list.removeFromBack());
        assertEquals("y", list.removeFromBack());
        assertEquals(24, list.size());
        assertEquals("a", list.get(0));
    }

    @Test(timeout = TIMEOUT)
    public void removeLastOccurrence2() {
        list.addToBack("a");
        list.removeLastOccurrence("a");
        assertEquals(0, list.size());
        list.addToBack("a");
        list.addToBack("b");
        list.addToBack("a");
        list.removeLastOccurrence("a");
        assertArrayEquals(new Object[]{"a", "b"}, list.toArray());
        list.clear();
        assertNull(list.removeLastOccurrence("a"));
        list.addToBack("a");
        //Checks if list uses "==" or ".equals()"
        assertEquals("a", list.removeLastOccurrence("A".toLowerCase()));
        list.addToBack("a");
        list.addToBack("b");
        list.addToBack("c");
        list.removeLastOccurrence("a");
        assertArrayEquals(new Object[]{"b", "c"}, list.toArray());
        assertEquals(2, list.size());
    }

    @Test(timeout = TIMEOUT)
    public void get1() {
        list.addToBack("To be");
        list.addToBack("fair");
        list.addToBack("you");
        list.addToBack("have");
        list.addToBack("to");
        list.addToBack("have a");
        list.addToBack("very");
        list.addToBack("high");
        list.addToBack("IQ to");
        list.addToBack("understand");
        list.addToBack("Rick and");
        list.addToBack("Morty");
        String[] ideal = {"To be", "fair", "you", "have", "to", "have a",
                "very", "high", "IQ to", "understand", "Rick and",
                "Morty"};
        Object[] originalList = list.toArray();
        int originalSize = list.size();
        for (int i = 0; i < list.size(); i++) {
            assertEquals(ideal[i], list.get(i));
        }
        Object[] afterList = list.toArray();
        int afterSize = list.size();
        //making sure get() doesn't modify list
        assertEquals(originalSize, afterSize);
        assertArrayEquals(originalList, afterList);
    }

    @Test(timeout = TIMEOUT)
    public void isEmpty1() {
        assertTrue(list.isEmpty());
        list.clear();
        assertTrue(list.isEmpty());
        list.addToBack("a");
        assertFalse(list.isEmpty());
        list.removeFromFront();
        assertTrue(list.isEmpty());
    }

    @Test(timeout = TIMEOUT)
    public void clear1() {
        list.clear();
        assertEquals(0, list.size());
        assertArrayEquals(new Object[]{}, list.toArray());
    }

    @Test(timeout = TIMEOUT)
    public void size1() {
        assertEquals(0, list.size());
        list.clear();
        assertEquals(0, list.size());
        list.addToFront("2");
        list.addToFront("1");
        assertEquals(2, list.size());
        list.addAtIndex("3", 1);
        assertEquals(3, list.size());
        list.addToBack("4");
        assertEquals(4, list.size());
        list.removeAtIndex(2);
        assertEquals(3, list.size());
        list.removeFromFront();
        assertEquals(2, list.size());
        list.removeFromBack();
        assertEquals(1, list.size());
        list.clear();
        assertEquals(0, list.size());
    }

    @Test(timeout = TIMEOUT)
    public void testInitializingList() {
        assertEquals(0, list.size());
        assertNull(list.getHead());
    }
    // test AddAtIndex
    @Test(timeout = TIMEOUT, expected =
            IndexOutOfBoundsException.class)
    public void testInvalidIndex1() {
        list.addAtIndex("Trang", 2400);
    }

    @Test(timeout = TIMEOUT, expected =
            IndexOutOfBoundsException.class)
    public void testInvalidIndex2() {
        list.addAtIndex("Trang", Integer.MIN_VALUE);
    }

    @Test(timeout = TIMEOUT, expected =
            IndexOutOfBoundsException.class)
    public void testInvalidIndex3() {
        list.addAtIndex("Trang", Integer.MAX_VALUE);
    }

    @Test(timeout = TIMEOUT, expected =
            IllegalArgumentException.class)
    public void testNullData() {
        list.addAtIndex(null, 0);
    }

    @Test(timeout = TIMEOUT)
    public void testAddAtIndex1() {
        assertEquals(0, list.size());
        assertNull(list.getHead());

        list.addAtIndex("Andy", 0);

        assertEquals(1, list.size());

        LinkedListNode<String> current = list.getHead();

        assertNotNull(current);
        assertSame(list.getHead(), current);

        current = current.getNext();
        assertNotNull(current);
        assertSame(current, list.getHead());

        current = current.getNext();
        assertNotNull(current);
        assertSame(current, list.getHead());

    }
    @Test(timeout = TIMEOUT)
    public void testAddAtIndex2() {

        list.addAtIndex("A", 0);
        list.addAtIndex("B", 0);

        // B A
        assertEquals(2, list.size());

        LinkedListNode<String> current = list.getHead();

        assertNotNull(current);
        assertEquals("B", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("A", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertSame(current, list.getHead());

        current = current.getNext();
        assertNotNull(current);
        assertSame(current, list.getHead().getNext());
    }
    @Test(timeout = TIMEOUT)
    public void testAddAtIndex3() {

        for (int i = 0; i < 8; i++) {
            list.addAtIndex(i + "a", i);
        }
        // 0a 1a 2a 3a 4a 5a 6a 7a

        assertEquals(8, list.size());

        LinkedListNode<String> current = list.getHead();
        for (int i = 0; i < 8; i++) {
            assertNotNull(current);
            assertEquals(i + "a", current.getData());
            current = current.getNext();
        }
        assertNotNull(current);
        assertSame(list.getHead(), current);

        current = current.getNext();
        assertNotNull(current);
        assertSame(current, list.getHead().getNext());
    }

    @Test(timeout = TIMEOUT, expected =
            IndexOutOfBoundsException.class)
    public void testAddAtIndex4() {
        list.addAtIndex("An", 0);
        list.addAtIndex("Trang", 1);
        list.addAtIndex("Yes", 3);
    }

    @Test(timeout = TIMEOUT)
    public void testAddAtIndex5() {

        for (int i = 0; i < 5; i++) {
            list.addAtIndex((i * 2) + "a", i);
        } // 0a 2a 4a 6a 8a

        assertEquals(5, list.size());

        for (int i = 1; i < 10; i += 2) {
            list.addAtIndex(i + "a", i);
        } // 0a 1a 2a 3a 4a 5a 6a 7a 8a 9a

        assertEquals(10, list.size());

        LinkedListNode<String> current = list.getHead();
        for (int i = 0; i < 10; i++) {
            assertNotNull(current);
            assertEquals(i + "a", current.getData());
            current = current.getNext();
        }
    }
    //test addToFront

    @Test(timeout = TIMEOUT, expected =
            IllegalArgumentException.class)
    public void testNullAddtoFront() {
        list.addToFront(null);
    }

    @Test(timeout = TIMEOUT)
    public void testAddToFront1() {
        list.addToFront("a");
        assertSame(list.getHead().getNext(), list.getHead());
    }

    @Test(timeout = TIMEOUT)
    public void testAddToFront2() {
        for (int i = 5; i > -1; i--) {
            list.addToFront(i + "a");
        }
        // 0a 1a 2a 3a 4a 5a
        assertEquals(list.size(), 6);
        LinkedListNode<String> currentNode = list.getHead();

        for (int i = 0; i < 6; i++) {
            assertEquals(currentNode.getData(), i + "a");
            currentNode = currentNode.getNext();
        }
        assertSame(currentNode, list.getHead());
        assertEquals(currentNode.getNext().getData(), "1a");
    }

    // test addToBack
    @Test(timeout = TIMEOUT, expected =
            IllegalArgumentException.class)
    public void testNullAddtoBack() {
        list.addToFront(null);
    }

    @Test(timeout = TIMEOUT)
    public void testAddToBack1() {
        list.addToBack("a");
        assertSame(list.getHead().getNext(), list.getHead());
    }

    @Test(timeout = TIMEOUT)
    public void testAddToBack2() {
        list.addToBack("0a");
        list.addToBack("1a");
        LinkedListNode<String> current = list.getHead();

        assertEquals(current.getData(), "0a");
        current = current.getNext();
        assertEquals(current.getData(), "1a");
        current = current.getNext();
        assertEquals(current.getData(), "0a");
    }
    @Test(timeout = TIMEOUT)
    public void testAddToBack3() {
        for (int i = 0; i < 5; i++) {
            list.addToBack(i + "a");
        }
        assertEquals(list.size(), 5);

        LinkedListNode<String> currentNode = list.getHead();

        for (int i = 0; i < 5; i++) {
            assertEquals(currentNode.getData(), i + "a");
            currentNode = currentNode.getNext();
        }
        assertSame(currentNode, list.getHead());
        assertEquals(currentNode.getNext().getData(), "1a");
    }
    // test adding mixed

    @Test(timeout = TIMEOUT)
    public void testAddingMixed1() {
        list.addToFront("0a");
        list.addToBack("1a");

        assertEquals(list.getHead().getData(), "0a");
        assertEquals(list.getHead().getNext().getData(), "1a");
    }
    @Test(timeout = TIMEOUT)
    public void testAddingMixed2() {
        for (int i = 0; i < 11; i++) {
            if (i % 2 == 0) {
                list.addToFront(i + "a");
            } else {
                list.addToBack(i + "a");
            }
        }
        //  10a 8a 6a 4a 2a 0a 1a 3a 5a 7a 9a

        LinkedListNode<String> current = list.getHead();

        for (int i = 10; i > -1; i -= 2) {
            assertEquals(current.getData(), i + "a");
            current = current.getNext();
        }
        for (int i = 1; i < 10; i += 2) {
            assertEquals(current.getData(), i + "a");
            current = current.getNext();
        }
        assertSame(current, list.getHead());
    }

    //test removeAtIndex
    @Test(timeout = TIMEOUT, expected =
            IndexOutOfBoundsException.class)
    public void testRemoveAtIndex1() {
        list.addToFront("0a");
        list.removeAtIndex(1);
    }

    @Test(timeout = TIMEOUT, expected =
            IndexOutOfBoundsException.class)
    public void testRemoveAtIndex2() {
        for (int i = 5; i > -1; i--) {
            list.addToFront(i + "a");
        }
        // 0a 1a 2a 3a 4a 5a
        for (int i = 1; i < 6; i += 2) {
            list.removeAtIndex(i);
        }
        // 0a 2a 4a
        LinkedListNode<String> current = list.getHead();

        for (int i = 0; i < 5; i += 2) {
            assertEquals(current.getData(), i + "a");
            current = current.getNext();
        }

        assertSame(current, list.getHead());
        assertEquals(current.getNext().getData(), "2a");
    }
    //test removeFromFront
    @Test(timeout = TIMEOUT)
    public void testRemoveFromFront1() {
        assertEquals(null, list.removeFromFront());
    }
    @Test(timeout = TIMEOUT)
    public void testRemoveFromFront2() {
        list.addToBack("0a");
        list.addToBack("1a");
        list.addToBack("2a");
        //0a 1a 2a
        assertEquals(list.removeFromFront(), "0a");
        assertEquals(list.getHead().getData(), "1a");
        assertEquals(list.removeFromFront(), "1a");
        assertEquals(list.getHead().getData(), "2a");
        assertEquals(list.removeFromFront(), "2a");
        assertEquals(list.removeFromFront(), null);
    }
    //test removeFromBack
    @Test(timeout = TIMEOUT)
    public void testRemoveFromBack1() {
        assertEquals(null, list.removeFromBack());
    }
    @Test(timeout = TIMEOUT)
    public void testRemoveFromBack2() {
        list.addToFront("2a");
        list.addToFront("1a");
        list.addToFront("0a");
        //0a 1a 2a
        assertEquals(list.removeFromBack(), "2a");
        assertEquals(list.getHead().getData(), "0a");
        assertEquals(list.removeFromBack(), "1a");
        assertEquals(list.getHead().getData(), "0a");
        assertEquals(list.removeFromBack(), "0a");
        assertEquals(list.removeFromBack(), null);
    }
    //test removeMix
    @Test(timeout = TIMEOUT)
    public void testRemoveMixed1() {
        for (int i = 0; i < 11; i++) {
            list.addAtIndex(i + "a", i);
        } // 0a 1a 2a 3a 4a 5a 6a 7a 8a 9a 10a

        for (int i = 0; i < 5; i++) {
            assertEquals(list.removeFromBack(), 10 - i + "a");
            assertEquals(list.removeFromFront(), i + "a");
        }
        assertEquals(list.getHead().getData(), "5a");
        assertEquals(list.removeFromFront(), "5a");
        assertEquals(list.removeFromFront(), null);
    }
    //test toArray
    @Test(timeout = TIMEOUT)
    public void testToArray1() {

        for (int i = 0; i < 11; i++) {
            list.addAtIndex(i + "a", i);
        } // 0a 1a 2a 3a 4a 5a 6a 7a 8a 9a 10a

        String[] expectedArr = {"0a", "1a", "2a", "3a", "4a", "5a", "6a",
                "7a", "8a", "9a", "10a"};
        assertEquals(list.size(), expectedArr.length);
        assertArrayEquals(expectedArr, list.toArray());
    }
    @Test(timeout = TIMEOUT)
    public void testToArray2() {
        list.addToFront("0a");
        assertArrayEquals(list.toArray(), new String[]{"0a"});
    }
    //test get

    @Test(timeout = TIMEOUT)
    public void testGet1() {
        list.addAtIndex("0a", 0);
        list.addAtIndex("1a", 1);
        list.addAtIndex("2a", 2);
        list.addAtIndex("3a", 3);
        list.addAtIndex("4a", 4); //0a 1a 2a 3a 4a

        assertEquals("0a", list.get(0));
        assertEquals("1a", list.get(1));
        assertEquals("2a", list.get(2));
        assertEquals("3a", list.get(3));
        assertEquals("4a", list.get(4));
    }
    @Test(timeout = TIMEOUT, expected =
            IndexOutOfBoundsException.class)
    public void testGet2() {
        list.addToFront("0a");
        list.get(1);
    }

    @Test(timeout = TIMEOUT, expected =
            IndexOutOfBoundsException.class)
    public void testGet3() {
        list.addToFront("0a");
        list.get(-1);
    }
    //test removeLastOccurence
    @Test(timeout = TIMEOUT)
    public void testRemoveLastOccurence1() {
        for (int i = 5; i > -1; i--) {
            list.addToFront(i + "a");
        }
        list.addToBack("1a");
        // 0a 1a 2a 3a 4a 5a 1a
        assertEquals(list.size(), 7);
        list.removeLastOccurrence("0a");
        assertEquals("1a", list.getHead().getData());
        list.removeLastOccurrence("1a");
        assertArrayEquals(list.toArray(), new String[]{
                "1a", "2a", "3a", "4a", "5a"});
    }
    @Test(timeout = TIMEOUT)
    public void testRemoveLastOccurence2() {
        list.addToBack("0a");
        list.addToBack("1a");
        list.addToBack("1a");
        list.addToBack("0a");
        list.addToBack("1a");
        list.addToBack("1a");
        list.addToBack("0a");
        // 0a 1a 1a 0a 1a 1a 0a
        assertEquals(list.size(), 7);
        list.removeLastOccurrence("0a");
        assertEquals("0a", list.getHead().getData());
        assertEquals("1a", list.get(list.size() - 1));
        list.removeLastOccurrence("1a");
        assertArrayEquals(list.toArray(), new String[]{
                "0a", "1a", "1a", "0a", "1a"});
        list.removeLastOccurrence("0a");
        assertArrayEquals(list.toArray(), new String[]{"0a", "1a", "1a", "1a"});
        assertEquals(list.removeFromBack(), "1a");
    }
    @Test(timeout = TIMEOUT)
    public void testRemoveLastOccurence3() {
        list.addToBack("1a");
        assertEquals(list.size(), 1);
        list.removeLastOccurrence("1a");
        assertSame(list.getHead(), null);
        assertEquals(list.size(), 0);
    }
    @Test(timeout = TIMEOUT)
    public void testRemoveLastOccurence4() {
        list.addToBack("1a");
        list.addToFront("0a");
        //0a 1a
        assertEquals(list.size(), 2);
        list.removeLastOccurrence("0a");
        assertSame(list.getHead().getData(), "1a");
    }
    @Test(timeout = TIMEOUT)
    public void testRemoveLastOccurence5() {
        list.addToBack("1a");
        list.addToFront("0a");
        //0a 1a
        assertEquals(list.size(), 2);
        list.removeLastOccurrence("1a");
        assertSame(list.getHead().getData(), "0a");
    }

}
