package Controllers;

import Model.*;

import java.util.*;

public class CardController {

    public Combination result(Player player)
    {
        return evaluateHand(player.getCards()).getCombination();
    }

    public WinSituation andTheWinnerIs(Player p1, Player p2) {
        HandValue h1 = evaluateHand(p1.getCards());
        HandValue h2 = evaluateHand(p2.getCards());

        int cmp = compare(h1, h2);

        if (cmp > 0) return new WinSituation(Win.PLAYER1, h1.getCombination());
        if (cmp < 0) return new WinSituation(Win.PLAYER2, h2.getCombination());
        return new WinSituation(Win.DRAW);
    }

    private int compare(HandValue h1, HandValue h2) {
        if (h1.getCombination().getValue() != h2.getCombination().getValue()) {
            return h1.getCombination().getValue() - h2.getCombination().getValue();
        }

        List<Integer> r1 = h1.getRanks();
        List<Integer> r2 = h2.getRanks();

        for (int i = 0; i < Math.min(r1.size(), r2.size()); i++) {
            if (!r1.get(i).equals(r2.get(i))) {
                return r1.get(i) - r2.get(i);
            }
        }
        return 0;
    }

    private HandValue evaluateHand(TreeSet<Card> cards) {

        List<Integer> ranks = new ArrayList<>();
        Map<Integer, Integer> count = new HashMap<>();
        Map<Integer, Integer> suitCount = new HashMap<>();

        for (Card c : cards) {
            int r = c.getCardRank().getValue();
            int s = c.getCardSuit().getValue();

            ranks.add(r);
            count.put(r, count.getOrDefault(r, 0) + 1);
            suitCount.put(s, suitCount.getOrDefault(s, 0) + 1);
        }

        ranks.sort(Collections.reverseOrder());

        boolean isFlush = suitCount.containsValue(5);
        boolean isStraight = isStraight(ranks);

        if (isFlush && isStraight && ranks.getFirst() == 14) {
            return new HandValue(Combination.ROYALFLUSH, List.of());
        }

        if (isFlush && isStraight) {
            return new HandValue(Combination.STRAIGHTFLUSH, List.of(ranks.get(0)));
        }

        List<Integer> fours = new ArrayList<>();
        List<Integer> threes = new ArrayList<>();
        List<Integer> pairs = new ArrayList<>();
        List<Integer> singles = new ArrayList<>();

        for (int r : count.keySet()) {
            int c = count.get(r);
            if (c == 4) fours.add(r);
            else if (c == 3) threes.add(r);
            else if (c == 2) pairs.add(r);
            else singles.add(r);
        }

        fours.sort(Collections.reverseOrder());
        threes.sort(Collections.reverseOrder());
        pairs.sort(Collections.reverseOrder());
        singles.sort(Collections.reverseOrder());

        if (!fours.isEmpty()) {
            List<Integer> res = new ArrayList<>();
            res.add(fours.getFirst());
            res.addAll(singles);
            return new HandValue(Combination.FOUR, res);
        }

        if (!threes.isEmpty() && !pairs.isEmpty()) {
            return new HandValue(Combination.FULLHOUSE,
                    List.of(threes.getFirst(), pairs.getFirst()));
        }

        if (isFlush) {
            return new HandValue(Combination.FLUSH, ranks);
        }

        if (isStraight) {
            return new HandValue(Combination.STRAIGHT, List.of(ranks.getFirst()));
        }

        if (!threes.isEmpty()) {
            List<Integer> res = new ArrayList<>();
            res.add(threes.get(0));
            res.addAll(singles);
            return new HandValue(Combination.THREE, res);
        }

        if (pairs.size() >= 2) {
            List<Integer> res = new ArrayList<>();
            res.add(pairs.get(0));
            res.add(pairs.get(1));
            res.addAll(singles);
            return new HandValue(Combination.TWOPAIR, res);
        }

        if (pairs.size() == 1) {
            List<Integer> res = new ArrayList<>();
            res.add(pairs.get(0));
            res.addAll(singles);
            return new HandValue(Combination.ONEPAIR, res);
        }

        return new HandValue(Combination.HIGHCARD, ranks);
    }

    private boolean isStraight(List<Integer> ranks) {

        for (int i = 0; i < ranks.size() - 1; i++) {
            if (ranks.get(i) - 1 != ranks.get(i + 1)) {
                return ranks.equals(List.of(14, 5, 4, 3, 2));
            }
        }
        return true;
    }
}