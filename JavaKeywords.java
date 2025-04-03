
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * This program inputs the data in the javakeyword.txt file into a bag. Then it
 * uses a command prompt to check if a value the user inputed in the bag.
 * Finally the program check all of the bagInterface implementation
 */
public class JavaKeywords {

    public static void main(String[] args) throws IOException {

        File newFile = new File("javakeywords.txt");
        BagInterface<String> bag = new ArrayBag<>();

        //inputs the data in the file into the bagInterface
        try (Scanner inputFile = new Scanner(newFile)) {
            while (inputFile.hasNext()) {
                bag.add(inputFile.nextLine());
            }
        }
        System.out.println("""
                           Java Keywords by V. Patel
                           """);

        System.out.println(args.length + " Java keyword loaded" + "\n");

        for (String val : args) {

            if (bag.contains(val)) {
                System.out.println(val + " is a Java keyword.");
            } else {
                System.out.println(val + " is not a Java keyword.");
            }
        }
        System.out.println("\nTesting all the interface in BagInterface");
        testArrayBag(bag);
    }

    //  Step 2d: Hardcoded test cases for ArrayBag
    private static void testArrayBag(BagInterface<String> bag) {

        System.out.println("\nTest for getCurrentSize() method");
        System.out.println("The current size of the bag is " + bag.getCurrentSize() + "\ngetCurrentSize() method works");

        System.out.println("\nTest for toArray() method");
        System.out.println("Bag contents: " + Arrays.toString(bag.toArray()) + "\ntoArray() method works");

        System.out.println("\nTest for contain() method");
        System.out.println("checking if the item continue is in the bag interface: " + bag.contains("continue") + "\ncontains() method works");

        System.out.println("\nTest for add() method");
        System.out.println("Adding the item phone to the bag ");
        bag.add("phone");
        System.out.println("The new current size of the bag is " + bag.getCurrentSize() + ". Check if the bag contains item phone: " + bag.contains("phone") + "\nadd() method works");

        System.out.println("\nTest for remove() method");
        System.out.println("removing the last item in the bag which is phone");
        bag.remove();
        System.out.println("The new current size of the bag is " + bag.getCurrentSize() + ". Check if the bag still contains item phone: " + bag.contains("phone") + "\nremove() method works");

        System.out.println("\nTest for remove(T anEntry)");
        System.out.println("Removing the item package from the bag");
        bag.remove("package");
        System.out.println("The new current size of the bag is " + bag.getCurrentSize() + ". Check if the bag still contains item package: " + bag.contains("package") + "\nremove(T anEntry) method works");

        System.out.println("\nTest for getFrequencyOf() method");
        System.out.println("adding item boolean to the list. This means the bag will contain the item boolean twice");
        bag.add("boolean");
        System.out.println("Testing if the the item boolean shows up two times in the bag: " + bag.getFrequencyOf("boolean") + "\ngetFrequencyOf() method works");

        System.out.println("\nTest for clear() method and isEmpty() method");
        System.out.println("Check if isEmpty() method is true without clearing the bag first: " + bag.isEmpty() + "\nThis means the bag is not empty, so I will clear it now");
        bag.clear();
        System.out.println("check if bag is now empty: " + bag.isEmpty() + ". Check the size of the bag (should be 0): " + bag.getCurrentSize() + "\nclear() method works and isEmpty() method works");

    }
}
