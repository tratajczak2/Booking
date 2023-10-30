package booking;

import java.util.*;
import java.util.stream.Collectors;

public class Booker {

    private List<BookEntry> bookEntries = new ArrayList<>();

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
        };
    }

    private void bookEconomy(double bid) {
        if (bookedCount(BookEntry.Kind.ECONOMY) < economyCapacity) {
            bookEntries.add(new BookEntry(bid, BookEntry.Kind.ECONOMY));
        } else bookPremium(bid);
    }

    private boolean isUpgrade(double bid) {
        Optional<Double> max = bookEntries
                .stream()
                .filter(be -> be.getKind() == BookEntry.Kind.ECONOMY)
                .map(be -> be.getPrice())
                .max(Comparator.naturalOrder());
        return false; //max.isPresent() ? max.get() < bid : true;
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
                .map(be -> be.getPrice())
                .reduce(0.0, (a, b) -> a + b);
    }
}
