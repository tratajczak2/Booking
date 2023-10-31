package booking;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookerTest {
    private final Double[] bids = new Double []{
            23d, 45d, 155d, 374d, 22d, 99.99d, 100d, 101d, 115d, 209d
    };

    @Test
    public void testOne() {
        Booker booker = new Booker(3, 3);

        booker.book(bids);

        assertEquals(3, booker.bookedCount(BookEntry.Kind.PREMIUM));
        assertEquals(738, booker.bookedTotal(BookEntry.Kind.PREMIUM));
        assertEquals(3, booker.bookedCount(BookEntry.Kind.ECONOMY));
        assertEquals(167.99, booker.bookedTotal(BookEntry.Kind.ECONOMY));
    }

    @Test
    public void testTwo() {
        Booker booker = new Booker(7, 5);

        booker.book(bids);

        assertEquals(6, booker.bookedCount(BookEntry.Kind.PREMIUM));
        assertEquals(1054, booker.bookedTotal(BookEntry.Kind.PREMIUM));
        assertEquals(4, booker.bookedCount(BookEntry.Kind.ECONOMY));
        assertEquals(189.99, booker.bookedTotal(BookEntry.Kind.ECONOMY));
    }

    @Test
    public void testThree() {
        Booker booker = new Booker(2, 7);

        booker.book(bids);

        assertEquals(2, booker.bookedCount(BookEntry.Kind.PREMIUM));
        assertEquals(583, booker.bookedTotal(BookEntry.Kind.PREMIUM));
        assertEquals(4, booker.bookedCount(BookEntry.Kind.ECONOMY));
        assertEquals(189.99, booker.bookedTotal(BookEntry.Kind.ECONOMY));
    }

    @Test
    public void testFour() {
        Booker booker = new Booker(7, 1);

        booker.book(bids);

        assertEquals(7, booker.bookedCount(BookEntry.Kind.PREMIUM));
        assertEquals(1099, booker.bookedTotal(BookEntry.Kind.PREMIUM));
        assertEquals(1, booker.bookedCount(BookEntry.Kind.ECONOMY));
        assertEquals(99.99, booker.bookedTotal(BookEntry.Kind.ECONOMY));
    }

}
