package day_07.module;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hand {

    private final List<Character> cards = new ArrayList<>(5);
    private final int bid;
    private List<Card> orderedCards = new ArrayList<>();

    public Hand(String cards, int bid) {
        for (int i = 0; i < cards.length(); i++)
            this.cards.add(cards.charAt(i));
        this.bid = bid;
    }

    public void orderCards(List<Character> cards_order) {
        Map<Character, Integer> map = new HashMap<>();
        List<Card> result = new ArrayList<>();
        for (Character card : cards) {
            if (map.containsKey(card))
                map.put(card, map.get(card) + 1);
            else
                map.put(card, 1);
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            result.add(new Card(entry.getKey(), entry.getValue()));
        }

        this.orderedCards = result.stream().sorted((o1, o2) -> {
            if (o1.getRepetitions() > o2.getRepetitions())
                return -1;
            else if (o1.getRepetitions() < o2.getRepetitions())
                return 1;
            else
                return cards_order.indexOf(o1.getValue()) - cards_order.indexOf(o2.getValue());
        }).toList();
    }

    public void convertJ() {
        int j = this.orderedCards.stream().anyMatch(c -> c.getValue() == 'J') && this.orderedCards.size() != 1 ? this.orderedCards.stream().filter(c -> c.getValue() == 'J').findFirst().get().getRepetitions() : 0;
        if (j != 0) {
            this.orderedCards = this.orderedCards.stream().filter(c -> c.getValue() != 'J').toList();
            this.orderedCards.get(0).addRepetitions(j);
        }
    }

    public List<Character> getCards() {
        return cards;
    }

    public int getBid() {
        return bid;
    }

    public List<Card> getOrderedCards() {
        return orderedCards;
    }
}
