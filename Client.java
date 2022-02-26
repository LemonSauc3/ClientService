/*
* FILE : Client.java
* PROJECT : SENG2040 - Assignment #3
* PROGRAMMER : Riley Boyd, Travis Fiander
* FIRST VERSION : 2022-02-15
* DESCRIPTION : The functions in this file, will allow the user to make little tests to make use of the Logger.java
*       intermediary file so that the server can be connected to, for logging to occur.
*/

// Imports
import java.io.IOException;
import java.util.Scanner;


/*
* NAME : Client
* PURPOSE : This class holds the testing, as well as the main function of the program, it 
*       calls the Logger class, and creates the object and sets the ip and port to connect to the server.
*       From there you can run an automated test, or a manual test.
*/
class Client {
    public static void main(String[] args) throws IOException, InterruptedException {
        
        // Setting up the logger to log the application.
        Logger logger = new Logger("127.0.0.1", 8080);

        // Main section for the program
        Scanner in = new Scanner(System.in);
        System.out.println("1. for automated task\n2. for manual test");
        int task = in.nextInt();
        if (task == 1) {
            automatedTasks(logger);
        } else if (task == 2) {
            manualTasks(logger);
        } else {
            System.out.println("Wrong value exiting\n");
            logger.Log(level(4), "Program exited unexpectedly");
        }

        in.close();
        // Closing out the logger when finished logging.
        logger.finalize();
         
    }

    /* 
    * Fuction: level(int id)
    * Description: Returns a string for the level type of the logger
    * Parameters: int id
    * Return: String
    */
    public static String level (int id) {
        String Level = "";
        switch (id) {
            case 1:
                Level = "LOW";
                break;
            case 2:
                Level = "MEDIUM";
                break;
            case 3:
                Level = "HIGH";
                break;
            case 4:
                Level = "CRITICAL";
                break;
            default:
                Level = "PASSIVE";
        }

        return Level;
    }

    /* 
    * Fuction: automatedTasks(Logger logger)
    * Description: runs a set of automated simple tasks that uses the logger class to log to the server
    * Parameters: Logger logger
    * Return: void
    */
    public static void automatedTasks(Logger logger) throws IOException, InterruptedException {
        // Setting up variables to test.
        String msg = "";
        String word = "Animate";
        int counter = 0;
        Float pie = (float) 3.14;
        double bigNumber = 505050.505050;

        // Creating some tasks that will pass and fail
        // adding to the counter until it hits 10
        for (int i = 0; i <= 10; i++) {
            counter++;
        }
        if (counter == 10) {
            msg = "Test completed successfully";
            logger.Log(level(1), msg);
            counter = 0;
        }
        Thread.sleep(750);
        for (int i = -1; i < 10; i++) {
            counter++;
        }
        if (counter != 10) {
            msg = "Error test is off";
            logger.Log(level(3), msg);
            counter = 0;
        }
        Thread.sleep(750);
        // Checking to see if PI is using the correct data type of float
        if (pie instanceof Float) {
            msg = "Correct Data type is used";
            logger.Log(level(5), msg);
        }
        Thread.sleep(750);
        // Checking to see if the string is empty
        if (word.length() != 0) {
            msg = "The String contains information";
            logger.Log(level(0), msg);
        } else {
            msg = "Error, the String needs information in it to proceed";
            logger.Log(level(4), msg);
        }
        Thread.sleep(750);
        // Checking to see if the word is rhino
        if (word == "rhino") {
            msg = "Incorrect word was produced";
            logger.Log(level(2), msg);
        }
        Thread.sleep(750);
        // Checking to see if the Double is bigger than 10000
        if (bigNumber > 10000) {
            msg = "The Double is of a suitable size";
            logger.Log(level(1), msg);
        }
        Thread.sleep(750);
    }

    /* 
    * Fuction: manualTasks(Logger logger)
    * Description: allows the user to run manual tasks with a little prompt so that the Logger class can log to the server
    * Parameters: Logger logger
    * Return: void
    */
    public static void manualTasks(Logger logger) throws IOException, InterruptedException {
        // Setting up some variables and the scanner to get user input
        Scanner in = new Scanner(System.in);
        int a = 0;
        int b = 0;
        int ret = 0;
        boolean cont = true;
        // running a loop for the "menu"
        while (cont) {
            System.out.println("Menu:\n1.add\n2.Failed add\n3.subtract\n4.Failed subtract\n5.quit\n6.send multiple logs\n7. Ping the logger 1000 times\n");
            int inputInt = in.nextInt();
            // Switch statement to control what the user presses, then logs based on the test ran
            switch (inputInt) {
                case 1:
                    System.out.println("Enter two numbers to test\n");
                    a = in.nextInt();
                    b = in.nextInt();
                    ret = add(a, b);
                    if (ret == (a + b)) {
                        logger.Log(level(1), "The adding function works as expected");
                    }
                    break;
                case 2:
                    System.out.println("Enter two numbers to test\n");
                    a = in.nextInt();
                    b = in.nextInt();
                    ret = addFailure(a, b);
                    if (ret != (a + b)) {
                        logger.Log(level(4), "Failure in the test needs to be fixed");
                    }
                    break;
                case 3:
                    System.out.println("Enter two numbers to test\n");
                    a = in.nextInt();
                    b = in.nextInt();
                    ret = sub(a, b);
                    if (ret == (a - b)) {
                        logger.Log(level(1), "The sub function works as expected");
                    }
                    break;
                case 4:
                    System.out.println("Enter two numbers to test\n");
                    a = in.nextInt();
                    b = in.nextInt();
                    ret = subFailure(a, b);
                    if (ret != (a - b)) {
                        logger.Log(level(4), "Failure in the test need to be fixed");
                    }
                    break;
                case 5:
                    cont = false;
                    logger.Log(level(1), "User quit the program");
                    break;
                // This test allows you to ping the server as many times with a second of sleep in between
                case 6:
                    System.out.println("How many time do you want to log? (int)");
                    int next = in.nextInt();
                    for (int i = 0; i < next; i++) {
                        logger.Log(level(5), "Sending a log for the sake of sending a log" + i);
                        Thread.sleep(1000);
                    }
                    break;
                case 7: 
                    System.out.println("Test the service with 1000 logs\n");
                    for (int i = 0; i <= 1000; i++) {
                        logger.Log(level(4), "The service is pinging the logger. " + i);
                    }
                    break;
                default:
                    System.out.println("Enter a valid command");
                    logger.Log(level(3), "Invalid command entered");
            }
        }
        in.close();
    }

    /* 
    * Fuction: add(int a, int b)
    * Description: adds two numbers together
    * Parameters: int a, int b
    * Return: int
    */
    public static int add(int a, int b) {
        return a + b;
    }

    /* 
    * Fuction: addFailure(int a, int b)
    * Description: Fails the add so that the logger can log an error
    * Parameters: int a, int b
    * Return: int
    */
    public static int addFailure(int a, int b) {
        return a * b;
    }

    /* 
    * Fuction: sub(int a, int b)
    * Description: subtracts two numbers from each other
    * Parameters: int a, int b
    * Return: int
    */
    public static int sub(int a, int b) {
        return a - b;
    }

    /* 
    * Fuction: subFailure(int a, int b)
    * Description: Fails to subtract so that the logger can log the error
    * Parameters: int a, int b
    * Return: int
    */
    public static int subFailure(int a, int b) {
        return a * b;
    }
}