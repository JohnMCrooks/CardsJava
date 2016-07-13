package com.crooks;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {

    public static HashSet<Card> createDeck(){
        HashSet<Card> deck = new HashSet<Card>();
        for (Card.Suit suit : Card.Suit.values()){  //Card.Suit.values() will return a collection of all 4 suites
            for(Card.Rank rank : Card.Rank.values()){ // will return all card values
                Card c = new Card(suit, rank);  //creates each card as it loops
                deck.add(c);       //adds new card to the deck Hash
            }
        }


        return deck;
    }

    public static HashSet<HashSet<Card>> createHands(HashSet<Card> deck){   // all possible non-repeating hands 270725
        HashSet<HashSet<Card>> hands = new HashSet<HashSet<Card>>();
        for (Card c1: deck){                                            //for each card in the deck
            HashSet<Card> deck2 = (HashSet<Card>) deck.clone();         //creating a clone so we can remove the first card  and loop over it so that the original deck isn't mutated
            deck2.remove(c1);
            for (Card c2 : deck2) {                                     ////creating a clone so we can remove the 2nd card  and loop over it
                HashSet<Card> deck3 = (HashSet<Card>) deck2.clone();
                deck3.remove(c2);
                for (Card c3 : deck3){                                  ////creating a clone so we can remove the 3rd card  and loop over it
                    HashSet<Card> deck4 = (HashSet<Card>) deck3.clone();
                    deck4.remove(c3);
                    for (Card c4 : deck4){
                        HashSet<Card> hand = new HashSet<Card>();     // Creates the final hand and place the contents into it
                        hand.add(c1);
                        hand.add(c2);
                        hand.add(c3);
                        hand.add(c4);
                        hands.add(hand);
                    }
                }
            }
        }
        return hands;
    }

    public static boolean isFlush(HashSet<Card> hand){      //returns a true value if the hand is a flush
        HashSet<Card.Suit> suits = hand.stream()
                .map(card -> card.suit)               //lambda that takes a card and returns the suite
                .collect(Collectors.toCollection(HashSet<Card.Suit>::new));
        return suits.size() == 1;
    }

    public static boolean isSameRank(HashSet<Card> hand){
        HashSet<Card.Rank> ranks = hand.stream()
                .map (card -> card.rank)
                .collect(Collectors.toCollection(HashSet<Card.Rank>::new));
        return ranks.size() == 1;
    }

    public static boolean isThreeofKind(HashSet<Card> hand){
        ArrayList<Integer> ordinalarray = hand.stream()
                .map (card -> card.rank.ordinal())
                .collect(Collectors.toCollection(ArrayList<Integer>::new));

        int [] freqList = new int[13];

        for (Integer i: ordinalarray) {
            freqList[i] = freqList[i]+ 1;
        }

        for (int i: freqList) {
            if (i ==3){
                return true;
            }
        }
        return false;
    }

    public static boolean twoPair(HashSet<Card> hand){
        ArrayList<Integer> ordinalarray = hand.stream()
                .map (card -> card.rank.ordinal())
                .collect(Collectors.toCollection(ArrayList<Integer>::new));

        int [] freqList = new int[13];
        int pair = 0;

        for (Integer i: ordinalarray) {
            freqList[i] = freqList[i]+ 1;
        }
        for (int i: freqList) {
            if (i ==2){
                pair++;
            }
        }
        if (pair==2){
            return true;
        }
        return false;
    }

    public static boolean straight(HashSet<Card> hand){
        ArrayList<Integer> sortedArray = hand.stream()
                .map (card -> card.rank.ordinal())
                .sorted((x1, x2) -> Integer.compare(x1.intValue(),x2.intValue()))
                .collect(Collectors.toCollection(ArrayList<Integer>::new));

        ArrayList<Integer> secondArray = new ArrayList<>();
        int rank = sortedArray.get(0);
        while (secondArray.size() < sortedArray.size()) {
            secondArray.add(rank);
            rank++;
        }

        return sortedArray.equals(secondArray);
    }

    public static boolean straightFlush(HashSet<Card> hand){
        if (isFlush(hand) && straight(hand)){
            return true;
        }else{
            return false;
        }
    }

    public static void main(String[] args) {
        HashSet<Card> deck = createDeck();
        HashSet<HashSet<Card>> hands = createHands(deck);

        HashSet<HashSet<Card>>flushes = hands.stream()          //Create a stream to filter out anything that isn't a flush
                .filter(Main::isFlush)
                .collect(Collectors.toCollection(HashSet<HashSet<Card>>::new));


        HashSet<HashSet<Card>>fourOfAKind = hands.stream()
               .filter(Main::isSameRank)                        // 4 of a Kind filter
               .collect(Collectors.toCollection(HashSet<HashSet<Card>>::new));

        HashSet<HashSet<Card>>threeOfAKind = hands.stream()
                .filter(Main::isThreeofKind)                        // 3 of a Kind filter
                .collect(Collectors.toCollection(HashSet<HashSet<Card>>::new));

        HashSet<HashSet<Card>>twoPairs = hands.stream()
                .filter(Main::twoPair)                        // 2 Pair filter
                .collect(Collectors.toCollection(HashSet<HashSet<Card>>::new));

        HashSet<HashSet<Card>>straight = hands.stream()
                .filter(Main::straight)                        // Straight filter
                .collect(Collectors.toCollection(HashSet<HashSet<Card>>::new));

        HashSet<HashSet<Card>>straightFlush = hands.stream()
                .filter(Main::straightFlush)                        // Straight Flush filter
                .collect(Collectors.toCollection(HashSet<HashSet<Card>>::new));



        System.out.println(" Total Hands: " + hands.size());
        System.out.println(" Total Flushes: " + flushes.size());
        System.out.println(" Total Four-of-a-Kinds: " + fourOfAKind.size());
        System.out.println(" Total Three-of-a-Kinds: " + threeOfAKind.size());
        System.out.println(" Total Two-Pairs: " + twoPairs.size());
        System.out.println(" Total Straights: " + straight.size());
        System.out.println(" Total Straight-Flushes: " + straightFlush.size());

    } // end main method
} // end main Class
