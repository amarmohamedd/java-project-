import java.util.Scanner;

// O(n)
public class Task4TextEditor {
    private StringBuilder text;
    private int cursorPosition;

    public Task4TextEditor() {
        // create the empty string and initialize the cursor at 0
        text = new StringBuilder();
        cursorPosition = 0;
    }

    public void left() {
        // moves the cursor left and if at the beginning does nothing
        if (cursorPosition > 0) {
            cursorPosition--;
        }
    }

    public void right() {
        // moves the cursor right and if at the end does nothing
        if (cursorPosition < text.length()) {
            cursorPosition++;
        }
    }

    public void insert(char c) {
        // adds the new letter at the position of the cursor
        text.insert(cursorPosition, c);
        cursorPosition++;
    }

    public void delete() {
        // deletes the letter at the position of the cursor that is if the cursor is standing on a letter
        if (cursorPosition < text.length()) {
            text.deleteCharAt(cursorPosition);
        }
    }

    public void display() {
        // displays both the text and the cursor it is standing on
        System.out.println("Text:    " + text.toString());
        System.out.print("Cursor: ");
        for (int i = 0; i < cursorPosition; i++) {
            System.out.print(" ");
        }
        System.out.println("^");
    }

    public static void main(String[] args) {
        // create the text editor object and the scanner to take input
        Task4TextEditor editor = new Task4TextEditor();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // shows the user all the valid commands and reads the input of the user
            System.out.println("Enter a command (left, right, insert c, delete, exit):");
            String command = scanner.nextLine();

            // checks for each command
            if (command.equals("left")) {
                editor.left();
                editor.display();
            } else if (command.equals("right")) {
                editor.right();
                editor.display();
            } else if (command.startsWith("insert ")) {
                char c = command.charAt(command.length() - 1);
                editor.insert(c);
                editor.display();
            } else if (command.equals("delete")) {
                editor.delete();
                editor.display();

            // exit ends the application
            } else if (command.equals("exit")) {
                break;
            // if the user didn't enter any of the valid commands
            } else {
                System.out.println("Please enter a valid command!");
            }
        }
        // close the scanner at the end of the code
        scanner.close();
    }
}
