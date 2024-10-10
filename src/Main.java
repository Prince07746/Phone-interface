import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Phone iphone = new PrinceIphone("0777777777", "Iphone 14");
        iphone.powerOn();

        Scanner input = new Scanner(System.in);
        int choice = -1; // Initialize choice to enter the loop

        while (choice != 0) {
            System.out.println("\t === Phone Menu ===");
            System.out.println("\t 1. Call");
            System.out.println("\t 2. Answer");
            System.out.println("\t 3. Send Text");
            System.out.println("\t 4. Show Text History");
            System.out.println("\t 5. Show Call History");
            System.out.println("\t 6. Power Off");
            System.out.println("\t 0. Exit");
            System.out.print("Enter your choice: ");
            choice = input.nextInt();
            input.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    iphone.call();
                    break;
                case 2:
                    iphone.answer();
                    break;
                case 3:
                    iphone.text();
                    break;
                case 4:
                    iphone.showTextHistory();
                    break;
                case 5:
                    iphone.showCallHistory();
                    break;
                case 6:
                    System.out.println("Powering off...");
                    // Here you can add any logic if needed when powering off
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
}

interface Phone {
    void powerOn();
    void call();
    void answer();
    void text();
    void showTextHistory();
    void showCallHistory();
}

abstract class PhoneImpl implements Phone {
    protected String myNumber;
    protected String model;
    protected boolean isRinging;
    protected boolean isPowerOn;
    protected ArrayList<String> textHistory = new ArrayList<>();
    protected ArrayList<String> callHistory = new ArrayList<>();
    protected Scanner input = new Scanner(System.in);

    public PhoneImpl(String myNumber, String model) {
        this.myNumber = myNumber;
        this.model = model;
        this.isRinging = false;
        this.isPowerOn = false;
    }

    @Override
    public void powerOn() {
        isPowerOn = true;
        System.out.println(model + " is powered on.");
    }

    @Override
    public void call() {
        System.out.print("\t Enter phone number to call: ");
        String phoneNumber = input.nextLine();
        System.out.println("\t Now ringing " + phoneNumber + " on " + model);
        isRinging = isPowerOn;
        callHistory.add(phoneNumber + (isRinging ? " [ picked ]" : " [ missed ]"));
        System.out.println(isRinging ? "\t Phone is ringing" : "\t Phone is not ringing");
    }

    @Override
    public void answer() {
        if (isRinging && isPowerOn) {
            System.out.println("\t Answering... Hello!");
            isRinging = false;
        } else {
            System.out.println("\t Phone is not ringing or powered off.");
        }
    }

    @Override
    public void text() {
        System.out.print("\t Enter message: ");
        String message = input.nextLine();
        System.out.print("\t Enter number: ");
        String number = input.nextLine();
        System.out.println("\t Message sent to " + number + ": " + message);
        textHistory.add(message + " [ " + number + " ]");
    }

    @Override
    public void showTextHistory() {
        System.out.println("\t Text History:");
        for (String text : textHistory) {
            System.out.println("\t" + text);
        }
        System.out.println("\t --------------------------------");
    }

    @Override
    public void showCallHistory() {
        System.out.println("\t Call History:");
        for (String call : callHistory) {
            System.out.println(call);
        }
        System.out.println("\t --------------------------------");
    }
}





class PrinceIphone extends PhoneImpl {
    public PrinceIphone(String myNumber, String model) {
        super(myNumber, model);
    }

    @Override
    public String toString() {
        return "\n\t===========================" +
                "\n\tMy Number: " + myNumber +
                "\n\tModel: " + model +
                "\n\tIs Ringing: " + isRinging +
                "\n\tIs Powered On: " + isPowerOn;
    }
}
