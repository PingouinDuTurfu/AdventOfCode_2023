package day_07;

import common.ReadFile;
import day_07.module.Card;
import day_07.module.Hand;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Part2 {

    public static final List<Character> CARDS = List.of('A', 'K', 'Q', 'T', '9', '8', '7', '6', '5', '4', '3', '2', 'J');

    public static void main(String[] args) {
        String[] input;
        try {
            input = ReadFile.read("day_07.txt");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }

        List<Hand> hands = new ArrayList<>();
        for (String line : input) {
            String[] split = line.split(" ");
            Hand hand = new Hand(split[0], Integer.parseInt(split[1]));
            hands.add(hand);
        }

        for (Hand hand : hands) {
            hand.orderCards(CARDS);
            hand.convertJ();
        }

        hands.sort((o1, o2) -> {
            List<Card> o1Cards = o1.getOrderedCards();
            List<Card> o2Cards = o2.getOrderedCards();

            if (o1Cards.get(0).getRepetitions() > o2Cards.get(0).getRepetitions())
                return -1;
            else if (o1Cards.get(0).getRepetitions() < o2Cards.get(0).getRepetitions())
                return 1;
            else {
                if(o1Cards.get(0).getRepetitions() == 3 && o2Cards.get(0).getRepetitions()  == 3)
                    if(o1Cards.get(1).getRepetitions() > o2Cards.get(1).getRepetitions())
                        return -1;
                    else if(o1Cards.get(1).getRepetitions() < o2Cards.get(1).getRepetitions())
                        return 1;
                    else {
                        for (int i = 0; i < 5; i++)
                            if (o1.getCards().get(i) != o2.getCards().get(i))
                                return CARDS.indexOf(o1.getCards().get(i)) - CARDS.indexOf(o2.getCards().get(i));
                        return 0;
                    }
                else if(o1Cards.get(0).getRepetitions() == 2 && o2Cards.get(0).getRepetitions() == 2)
                    if(o1Cards.get(1).getRepetitions() > o2Cards.get(1).getRepetitions())
                        return -1;
                    else if(o1Cards.get(1).getRepetitions() < o2Cards.get(1).getRepetitions())
                        return 1;
                    else {
                        for (int i = 0; i < 5; i++)
                            if (o1.getCards().get(i) != o2.getCards().get(i))
                                return CARDS.indexOf(o1.getCards().get(i)) - CARDS.indexOf(o2.getCards().get(i));
                        return 0;
                    }
                else
                    for (int i = 0; i < 5; i++)
                        if (o1.getCards().get(i) != o2.getCards().get(i))
                            return CARDS.indexOf(o1.getCards().get(i)) - CARDS.indexOf(o2.getCards().get(i));
                return 0;
            }
        });

        int result = 0;
        for (int i = 0; i < hands.size(); i++)
            result += hands.get(i).getBid() * (hands.size() - i);

        System.out.println(result);
    }
}
