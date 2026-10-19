//--------------------------------------------------------------------
// HigherLower.java         by Dale/Joyce/Weems              Chapter 6
//
// Plays Higher or Lower? with user through command line interface. 
//--------------------------------------------------------------------
import java.util.InputMismatchException;
import java.util.Scanner;

public class HigherLower 
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to HigherLower! Here are the rules of the game: ");
        System.out.println();
        System.out.println("You start with a certain amount of money.\nYou must risk an amount of money each round before seeing any cards\nAs long as there are still 3 cards or more in the deck (enough for a hand) the game continues\nTwo cards will be dealt face up, and one face down.\nIf the two cards are the same, the player automatically wins.\nIf the two cards are 1 apart, the player automatically loses. ");
        System.out.println("You then guess if the 3rd card is inside or outside of the 2 cards.\nIf you guess correctly, you win the round and the bet");
        System.out.println("If your 3rd card is the same as the first or second, you automatically lose");
        System.out.println();
        boolean worked = false;
        boolean wantToPlay = true;
        while(!worked)
        {
            System.out.println("Ready to play? (True or False): ");
            try
            {
                wantToPlay = sc.nextBoolean();
                worked = true;
            }
            catch (InputMismatchException e)
            {
                System.out.println("Enter True or False: ");
                worked = false;
                sc.next();
            }
        }
        double bank = 0;
        if(wantToPlay == true)
        {
            worked = false;
            while(!worked)
            {
                try
                {
                    System.out.println("Enter the amount of money you want to have in your bank: ");
                    bank = sc.nextDouble();
                    worked = true;
                }
                catch(InputMismatchException e)
                {
                    System.out.println("That is not a number");
                    worked = false;
                    sc.next();
                }
            }
        }
            
        while(wantToPlay)
        {
            int cardsDealt = 0;
            worked = false;
            double bet = 0;
            while(!worked)
            {
                try
                {
                    System.out.println("Bet Amount: ");
                    bet = sc.nextDouble();
                    worked = true;
                }
                catch(InputMismatchException e)
                {
                    System.out.println("That is not a number");
                    worked = false;
                    sc.next();
                }
            }

            while(bank < bet)
            {
                System.out.println("You cannot bet more than you have");
                System.out.println("Bet Amount: ");
                bet = sc.nextDouble();
            }

            CardDeck c = new CardDeck();
            c.shuffle();
            System.out.println("Card 1: ");
            if(cardsDealt == 10)
            {
                c = new CardDeck();
                c.shuffle();
                cardsDealt = 0;
            }
            Card c1 = c.nextCard();
            cardsDealt++;
            System.out.println(c1.getRank());
            if(cardsDealt == 10)
            {
                c = new CardDeck();
                c.shuffle();
                cardsDealt = 0;
            }
            Card c2 = c.nextCard();
            cardsDealt++;
            System.out.println("Card 2: ");
            System.out.println(c2.getRank());
            System.out.println();
            if(c2.compareTo(c1) == 0)
            {
                System.out.println("Card 1 and Card 2 are the same. You win!");
                bank = bank + bet;
                System.out.println("Current bank: " + bank);
            }
            else if(c2.compareTo(c1) == -1 || c2.compareTo(c1) == 1)
            {
                System.out.println("You hit the post. You lose!");
                bank = bank - bet;
                System.out.println("Current bank: " + bank);
                 
            }
            if(bank <= 0)
            {
                System.out.println("You ran out of money. The game is over. Goodbye!");
                break;
            }
            else
            {
                if(bet*2 <= bank)
                {
                    boolean a = false;
                    worked = false;
                    while(!worked)
                    {
                        try
                        {
                            System.out.println("Do you want to double your bet? (True or False): ");
                            a = sc.nextBoolean();
                            worked = true;
                        }
                        catch (InputMismatchException e)
                        {
                            System.out.println("Enter True or False: ");
                            worked = false;
                            sc.next();
                        }
                    }
                    if(a)
                    {
                        bet = bet*2;
                    }

                }
                    String choice = "";
                    boolean b = false;
                    while(!b)
                    {
                        try
                        {
                            System.out.println("Inside or outside? ");
                            String response = sc.next();
                            if(response.equalsIgnoreCase("inside") || response.equalsIgnoreCase("outside"))
                            {
                                b = true;
                                choice = response;
                            }
                            else
                            {
                                System.out.println("That wasn't inside or outside. Try again.");
                            }
                        }
                        catch (InputMismatchException e)
                        {
                            System.out.println("That is not a valid input. Try again");
                            b = false;
                            sc.next();
                        }
                    }
                    System.out.println();
                    System.out.println("Card 3 is: ");
                    if(c.hasNextCard() == false)
                    {
                        c = new CardDeck();
                    }
                    Card c3 = c.nextCard();
                    System.out.println(c3);
                    if(c3.compareTo(c1) == 0 || c3.compareTo(c2) == 0)
                    {
                        bank = bank - bet;
                        System.out.println("The card was " + c3.getRank());
                        System.out.println("Sorry! You hit the post. Your bank is now " + bank);
                    }
                    else if(choice.equalsIgnoreCase("inside"))
                    {
                        if ((c3.compareTo(c1) > 0 && c3.compareTo(c2) < 0) || (c3.compareTo(c2) > 0 && c3.compareTo(c1) < 0))
                        {
                            bank = bank + bet;
                            System.out.println("Good job! You guessed inside and card 3 was inside. Your bank is now " + bank);
                        }
                        else
                        {
                            bank = bank - bet;
                            System.out.println("Sorry! You guessed inside and card 3 was outside. Your bank is now " + bank);
                        }
                    }
                    else
                    {
                        if ((c3.compareTo(c1) > 0 && c3.compareTo(c2) < 0) || (c3.compareTo(c2) > 0 && c3.compareTo(c1) < 0))
                        {
                            bank = bank - bet;
                            System.out.println("Sorry! You guessed outside and card 3 was inside. Your bank is now " + bank);
                        }
                        else
                        {
                            bank = bank + bet;
                            System.out.println("Good job! You guessed outside and card 3 was outside. Your bank is now " + bank);
                        }
                    }
                    if(bank <= 0)
                    {
                        System.out.println("You ran out of money. The game is over. Goodbye!");
                        break;
                    }
                    else
                    {
                        worked = false;
                        while(!worked)
                        {
                            try
                            {
                                System.out.println("Would you like to play again? (True or False):");
                                wantToPlay = sc.nextBoolean();
                                worked = true;
                            }
                            catch (InputMismatchException e)
                            {
                                System.out.println("Enter True or False: ");
                                worked = false;
                                sc.next();
                            }
                        }
                        if(!wantToPlay)
                        {
                            System.out.println("Goodbye!");
                        }
                    }
                }
            }
        }
    }