package com.crooks;

import java.util.HashSet;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {

    static HashSet<Card> createDeck(){
        HashSet<Card> deck = new HashSet<Card>();
        for (Card.Suit suit : Card.Suit.values()){  //Card.Suit.values() will return a collection of all 4 suites
            for(Card.Rank rank : Card.Rank.values()){ // will return all card values
                Card c = new Card(suit, rank);  //creates each card as it loops
                deck.add(c);       //adds new card to the deck Hash
            }
        }


        return deck;
    }

    static HashSet<HashSet<Card>> createHands(HashSet<Card> deck){   // all possible non-repeating hands 270725
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

    static boolean isFlush(HashSet<Card> hand){      //returns a true value if the hand is a flush
        HashSet<Card.Suit> suits = hand.stream()
                .map(card -> card.suit)               //lambda that takes a card and returns the suite
                .collect(Collectors.toCollection(HashSet<Card.Suit>::new));
        return suits.size() == 1;
    }


    public static void main(String[] args) {
        HashSet<Card> deck = createDeck();
        HashSet<HashSet<Card>> hands = createHands(deck);
        HashSet<HashSet<Card>>flushes = hands.stream()   //Create a stream to filter out anything that isn't a flush
                .filter(Main::isFlush)
                .collect(Collectors.toCollection(HashSet<HashSet<Card>>::new));

        System.out.println(hands.size());
        System.out.println(flushes.size());



    } // end main method
} // end main Class
