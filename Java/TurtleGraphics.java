import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.Color;
import java.lang.Math.*;
import java.util.Scanner;
import uk.ac.leedsbeckett.oop.OOPGraphics;

/**
 * Concrete class, which draws on a screen with a turtle icon.
 */
public class TurtleGraphics extends OOPGraphics {
    File history = new File("history.txt");

    public static void main(String[] args) {
        new TurtleGraphics();
    }

    public TurtleGraphics() {
        JFrame MainFrame = new JFrame();                //create a frame to display the turtle panel on
        MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Make sure the app exits when closed
        MainFrame.setLayout(new FlowLayout());  //not strictly necessary
        MainFrame.add(this);                                    //"this" is this object that extends turtle graphics so we are adding a turtle graphics panel to the frame
        MainFrame.pack();                                               //set the frame to a size we can see
        MainFrame.setVisible(true);                             //now display it
        penDown = true;
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(history);
        } catch (Exception e) {
            displayMessage("Error: something went wrong...");
        }
        if (writer != null) {
            writer.print("");
            writer.close();
        }
    }

    /**
     * Processes command from predetermined commands list.
     * @param command 1 word instruction from command list.
     */
    public void processCommand(String command) {
        try (FileWriter fw = new FileWriter(history, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(command);
        } catch (IOException e) {
            displayMessage("Error: something went wrong...");
        }

        String[] parts = command.split(" ");

        if (parts.length == 1) {
            switch (command) {
                case "about":
                    about();
                    break;
                case "penup":
                    penUp();
                    break;
                case "pendown":
                    penDown();
                    break;
                case "black":
                    setPenColour(Color.black);
                    break;
                case "green":
                    setPenColour(Color.green);
                    break;
                case "red":
                    setPenColour(Color.red);
                    break;
                case "white":
                    setPenColour(Color.white);
                    break;
                case "yellow":
                    setPenColour(Color.yellow);
                    break;
                case "reset":
                    reset();
                    penDown = true;
                    setPenColour(Color.red);
                    break;
                case "clear":
                    clear();
                    break;
                case "turnleft":
                case "turnright":
                case "forward":
                case "backward":
                    displayMessage("Error: Missing parameter for command '" + command + "'");
                    break;
                default:
                    displayMessage("Invalid command");
            }
        } else if (parts.length == 2) {
            try {
                int specifier = Integer.parseInt(parts[1]);
                processCommand(parts[0], specifier);
            } catch (NumberFormatException e) {
                processCommand(parts[0], parts[1]);
            }
        } else {
            displayMessage("Invalid command format: " + command);
        }
    }

    /**
     * Processes command from predetermined commands list with an integer value for the command.
     * @param command 1 word instruction from command list.
     * @param specifier integer to pass to the selected command.
     */
    public void processCommand(String command, int specifier) {
        if (specifier < 1) {
            displayMessage("Error: second parameter must be an integer over zero");
        } else {
            switch (command) {
                case "turnleft":
                    turnLeft(specifier);
                    break;
                case "turnright":
                    turnRight(specifier);
                    break;
                case "forward":
                    forward(specifier);
                    break;
                case "backward":
                    forward(-specifier);
                    break;
                case "square":
                    square(specifier);
                    break;
                case "penwidth":
                    penWidth(specifier);
                    break;
                case "triangle":
                    triangle(specifier);
                    break;
                default:
                    displayMessage("Invalid command");
            }
        }
    }

    /**
     * Processes command from predetermined commands list with a string value for the command.
     * @param command 1 word instruction from command list.
     * @param stringSpecifier string to pass to the selected command.
     */
    public void processCommand(String command, String stringSpecifier) {
        switch (command) {
            case "save":
                File file = new File(stringSpecifier + ".png");
                BufferedImage bufImg = getBufferedImage();
                try {
                    ImageIO.write(bufImg, "png", file);
                } catch (IOException e) {
                    displayMessage("Error: something went wrong...");
                }
                displayMessage("Image saved as '" + stringSpecifier + "'");

                File savedHistory = new File(stringSpecifier + ".txt");
                try (BufferedReader readHistory = new BufferedReader(new FileReader(history));
                     BufferedWriter save = new BufferedWriter(new FileWriter(savedHistory))) {

                    String line;
                    String nextLine = readHistory.readLine();

                    while (nextLine != null) {
                        line = nextLine;
                        nextLine = readHistory.readLine();

                        if (nextLine != null) {
                            save.write(line + '\n');
                        }
                    }
                } catch (IOException e) {
                    displayMessage("Error: something went wrong...");
                }
                break;
            case "load":
                File loadedFile = new File(stringSpecifier + ".png");
                BufferedImage loadedBufImg;
                try {
                    loadedBufImg = ImageIO.read(loadedFile);
                } catch (IOException e) {
                    displayMessage("File: '" + loadedFile + "' not found");
                    throw new RuntimeException(e);
                }
                setBufferedImage(loadedBufImg);
                break;
            case "run":
                try {
                    File runFile = new File(stringSpecifier + ".txt");
                    Scanner fileReader = new Scanner(runFile);

                    while (fileReader.hasNext()) {
                        String line = fileReader.nextLine().trim();;
                        String[] parts = line.split(" ");

                        if (!parts[0].equals("save")) {
                            if (parts.length == 1) {
                                processCommand(parts[0]);
                                System.out.println(parts[0]);
                            } else if (parts.length == 2) {
                                System.out.println(parts[0] + " " + parts[1]);
                                try {
                                    int specifier = Integer.parseInt(parts[1]);
                                    processCommand(parts[0], specifier);
                                } catch (NumberFormatException e) {
                                    processCommand(parts[0], parts[1]);
                                }
                            } else {
                                displayMessage("Invalid command format: " + command);
                            }
                        }
                    }
                } catch(FileNotFoundException e){
                        displayMessage("Error: could not find file");
                }
                break;
            case "pencolour":
                penColour(stringSpecifier);
                break;
            case "triangle":
                triangle(stringSpecifier);
                break;
            case "turnleft":
            case "turnright":
            case "forward":
            case "backward":
                displayMessage("Error: Second parameter for command '" + command + "' must be an integer");
                break;
            default:
                displayMessage("Invalid command");
        }
    }

    /**
     * Draws a square.
     * @param length specified length of square's sides.
     */
    public void square(int length) {
        for (int i = 0; i < 4; i++) {
            turnRight(90);
            forward(length);
        }
    }

    /**
     * Changes pen's colour using RGB value.
     * @param rgb red,green,blue. Values must be between 0 and 255.
     */
    public void penColour(String rgb) {
        try {
            String[] parts = rgb.split(",");
            int r = Integer.parseInt(parts[0]);
            int g = Integer.parseInt(parts[1]);
            int b = Integer.parseInt(parts[2]);
            Color color = new Color(r, g, b);
            setPenColour(color);

        } catch (Exception e) {
            displayMessage("'pencolour' command requires 3 integers between 0 and 255, separated by commas");
        }
    }

    /**
     * Changes width of pen.
     * @param width new width.
     */
    public void penWidth(int width) {
        setStroke(width);
    }

    /**
     * Draws an equilateral triangle.
     * @param size length of each side of equilateral triangle.
     */
    public void triangle(int size) {
        for (int i = 0; i < 3; i++) {
            forward(size);
            turnRight(120);
        }
    }

    /**
     * Draws a triangle with 3 specified lengths.
     * @param sides length of each side. Must be separated by commas.
     */
    public void triangle(String sides) {
        try {
            String[] parts = sides.split(",");
            int a = Integer.parseInt(parts[0]);
            int b = Integer.parseInt(parts[1]);
            int c = Integer.parseInt(parts[2]);

            double A = Math.acos((b*b + c*c - a*a) / (2.0 * b * c));
            double C = Math.acos((a*a + b*b - c*c) / (2.0 * a * b));
            A = (int) Math.toDegrees(A);
            C = (int) Math.toDegrees(C);

            if (a > 0 && b > 0 && c > 0) {
                forward(a);
                turnRight((int) (180 - C));
                forward(b);
                turnRight((int) (180 - A));
                forward(c);
            }

        } catch (Exception e) {
            displayMessage("'triangle' command requires 3 positive integers, separated by commas to specify the length of 3 sides");
        }
    }
}
