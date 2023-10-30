package booking;

import java.util.*;
import java.util.stream.Collectors;

public class Booker {

    private final List<BookEntry> bookEntries = new ArrayList<>();

    private final int premiumCapacity;
    private final int economyCapacity;

    public Booker(int premiumCapacity, int economyCapacity) {
        this.premiumCapacity = premiumCapacity;
        this.economyCapacity = economyCapacity;
    }

    public void book(Double[] bids) {
        List<Double> bidList = Arrays.asList(bids)
                .stream()
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toList());
        for (double bid: bidList) {
            BookEntry.Kind kind = kind(bid);
            if (kind == BookEntry.Kind.ECONOMY) {
                bookEconomy(bid);
            } else {
                bookPremium(bid);
            }
        }
    }

    private void bookPremium(double bid) {
        if (bookedCount(BookEntry.Kind.PREMIUM) < premiumCapacity) {
            bookEntries.add(new BookEntry(bid, BookEntry.Kind.PREMIUM));
        }
    }

    private void bookEconomy(double bid) {
        if (bookedCount(BookEntry.Kind.ECONOMY) < economyCapacity) {
            if (bookedCount(BookEntry.Kind.PREMIUM) < premiumCapacity) {
                bookEntries.add(new BookEntry(bid, BookEntry.Kind.PREMIUM));
            } else {
                bookEntries.add(new BookEntry(bid, BookEntry.Kind.ECONOMY));
            }
        }
    }

    private BookEntry.Kind kind(double bid) {
        return bid < 100 ? BookEntry.Kind.ECONOMY : BookEntry.Kind.PREMIUM;
    }

    public long bookedCount(BookEntry.Kind kind) {
        return bookEntries
                .stream()
                .filter(be -> be.getKind() == kind)
                .count();
    }

    public double bookedTotal(BookEntry.Kind kind) {
        return bookEntries
                .stream()
                .filter(be -> be.getKind() == kind)
                .map(BookEntry::getPrice)
                .reduce(0.0, (a, b) -> a + b);
    }
}
