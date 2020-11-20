package raffleticket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.ArrayList;

public class RaffleTicket {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static Random rand = new Random();
    static ArrayList<String> raffleNames = new ArrayList<>();

    public static void main(String[] args) {

        boolean ticketLoop = true;

        while (ticketLoop == true) {
            mainMenu();
            try {
                int getIntInput = Integer.parseInt(input.readLine());
                switch (getIntInput) {
                    case (1):
                        buyingTicket();
                        break;
                    case (2):
                        checkingTicket();
                        break;
                }
            } catch (Exception e) {
                System.out.println("Please enter a valid number!\n");
            }
        }

    }

    public static void buyingTicket() {
        System.out.println("------Purchasing Ticket------");
        System.out.println("What is your name?");
        try {
            String customerName = input.readLine();

            int ticketNumber = rand.nextInt(500);
            raffleNames.add(customerName + "." + ticketNumber);
            System.out.println(raffleNames + "\n");
        } catch (Exception E) {
            System.out.println("EXCEPTION: ERROR OCCURED");
        }

    }

    public static void checkingTicket() {
        boolean congratulations = true;
        System.out.println(raffleNames.size());
        System.out.println("\n------Checking Ticket------");
        System.out.println("What is your name?");
        try {
            String enteredName = input.readLine();

            for (int x = 0; x < raffleNames.size(); x++) {
                int placeOfDot = raffleNames.get(x).indexOf(".");
                String nameBeforeDot = raffleNames.get(x).substring(0, placeOfDot);
                String numberAfterDot = raffleNames.get(x).substring(placeOfDot + 1, raffleNames.get(x).length());

                if (nameBeforeDot.equalsIgnoreCase(enteredName)) {
                    System.out.println("What is your number?");
                    ///The input.read below gets the VALUE of the entered number. So if i intered 364 it would return 51 as thats the value.
                    String enteredNumberString = input.readLine();
                    int enteredNumber = Integer.parseInt(enteredNumberString);

                    if (numberAfterDot.equalsIgnoreCase(enteredNumberString)) {
                        if (enteredNumber <= 1) {
                            System.out.println("You have lost, Sorry.\n");
                            break;
                        }
                        for (int i = 2; i < (enteredNumber / 2); i++) {
                            if (enteredNumber % i == 0) {
                                System.out.println("You have lost, Sorry.\n");
                                congratulations = false;
                                break;
                            }
                        }
                        if(congratulations==true){
                        System.out.println("You have won! Congratulations!\n");
                        raffleNames.remove(x);
                        break;
                        }
                        raffleNames.remove(x);
                        break;

                    } else {
                        System.out.println("You have not entered the number of a purchased ticket. Do not cheat!\n");
                        break;
                    }
                } else {
                    System.out.println("You have not entered a valid name. Please purchase a ticket!\n");
                    break;
                }

            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void mainMenu() {

        System.out.println("Welcome to the raffle game. \nPlease make a selection:");
        System.out.println("1: Buy A Raffle Ticket \n2: Check A Raffle Ticket");
    }

}
