// Description: This program is a tic tac toe game, where users must answer a basic math problem within 10s before choosing a spot. The board is increased in size to ensure little draws occur. The program applies concepts learned throughout this course, and demonstrates my understanding of IPO, variables & selection, repetitions & loops, functions & methods, and strings & arrays.

import java.util.Scanner; // Import the Scanner class

class Main {
  // Void function (no parameters, returns nothing) - print game instructions (goal, how to make a move, etc)
  public static void printWelcome() {
    System.out.println("Tick Tock Toe");
    System.out.println("Let's practice math while playing tic tac toe! The goal is to make 3 in a row on a 4x3 board.");
    System.out.println("To make a move, correctly answer an addition, subtraction, or multiplication question first. Then, choose a row (0,1,2,3) and column (0,1,2) to place your 'X'! Answer incorrectly, and you lose your turn. Your opponent is the computer.");
    System.out.println("But be careful! Tick, tock, the clock is ticking! Fail to correctly answer the question in 10 seconds, and you also lose your turn!");
  }

  // Void function (takes 2D string array as parameter, returns nothing) - prints out each element in array, and concatenates brackets around it to create a 4x3 gameboard
  public static void printBoard(String[][] gameBoard) {    
    System.out.println("Board:");
    
    // Outer for loop counts # rows 
    // Nested for loop counts # columns 
    for (int i = 0; i < gameBoard.length; i++) {
      for (int j = 0; j < gameBoard[0].length; j++) {
        System.out.print("[" + gameBoard[i][j] + "]");
        // Prints element from row i, and column j, and adds '[' & ']' around to enclose the spot
        // Each row is printed on the same line
      }

      System.out.println();
      // New line is created (so next row isn't on same line)
    }
  }

  // Fruitful function - determines the row # to mark player's character
  // Takes a string variable as its parameter (depending on which player's turn it is, method of determining row # varies)
  public static int rowNum(String player) {
    int rowNumber; // Declare int variable for the row #

    Scanner In = new Scanner(System.in); //Create a scanner object

    // If string in parameter is 'user', retrieve row # from user 
    if (player.equals("user")) {
      System.out.println("Which row would you like to put your piece in? (0, 1, 2, 3)"); 
      // Prompt user input which row (row # ranges from 0-3)

      // Repeats do-while loop if user enters row # out of range (less than 0, greater than 3)
      do {  
        System.out.println("Please enter a positive number within range!:");

        // Nested while loop - user has to retry if they enter anything other than an int (eg. string, double, etc)
        while (!In.hasNextInt()) {
          System.out.println("Invalid. Try again!:");
          In.next(); // Re-read user input for row #
        }
      
        rowNumber = In.nextInt(); // Read user input for row #
      }
       
      while ((rowNumber < 0) || (rowNumber > 3));
    }
    
    // If parameter isn't user (meaning it's the computer's move), generate random # between 0-3 (computer's 'move')
    else {
      rowNumber = (int)(3 * Math.random());
    }

    return rowNumber; // Returns int value for row #
  }

  // Fruitful function - determines the col # to mark player's character
  // Takes a string variable as its parameters (depending on which player's turn it is, method of determining col # varies)
  public static int colNum(String player) {
    int colNumber; // Declare int variable for col #

    Scanner In = new Scanner(System.in); //Create a scanner object

    // If string in parameter is 'user', retrieve col # from user
    if (player.equals("user")) {
      System.out.println("Which column would you like to put your piece in? (0, 1, 2)");
      // Prompt user input which col (# ranges from 0-2)

      // Repeats do-while loop if user enters col # out of range (less than 0, greater than 2)
      do {    
        System.out.println("Please enter a positive number within range!:");

        // Nested while loop - user has to retry if they enter anything other than an int (eg. string, double, etc)
        while (!In.hasNextInt()) {
          System.out.println("Invalid. Try again!:");
          In.next(); // Re-read user input for col #
        }
      
        colNumber = In.nextInt(); // Read user input for col #
      }

      while ((colNumber < 0) || (colNumber > 2));
    }

    // If parameter isn't user (meaning it's the computer's move), generate random # between 0-2 (computer's 'move')
    else {
      colNumber = (int)(2 * Math.random());
    }    

    return colNumber; // Returns int value for col #
  }

  // Fruitful function (no parameters) - generates math question for user, takes user input for it, and determines if they are eligible to make a move
  public static boolean mathQ() {
    int num1, num2, type, ans, solution;
    // Declare int variables for #s used in math question, type of problem, the user's answer, and the correct solution

    double start, end, time, roundedTime;
    // Declare double variables for....
    // 'start' & 'end'- time when q is given & when user answers
    // 'time' & 'roundedTime'- overall time it took to answer (rounded and unrounded)

    boolean isCorrect, overTime, canMove; 
    // Declare boolean variables for....
    // 'isCorrect' - if user got the right solution
    // 'overTime' - if user took 10+ sec to answer
    // 'canMove' - if user is eligible to make a move (conditions listed below)

    Scanner In = new Scanner(System.in); //Create a scanner object

    num1 = (int)(12 * Math.random()) + 1;
    num2 = (int)(12 * Math.random()) + 1;
    type = (int)(3 * Math.random()) + 1;
    // Generate random #s for....
    // num1 and num2 - used in math problem (between 1-12)
    // type - add., sub., or mult. problem (1-3 corresponds to each, respectively)
  
    if (type == 1) {
      System.out.println("What is " + num1 + " + " + num2 + "?");
      solution = num1 + num2;
    }
    // Prints add. problem if type = 1, and calculates solution

    else if (type == 2) {
      System.out.println("What is " + num1 + " - " + num2 + "?");
      solution = num1 - num2;
    }
    // Prints sub. problem if type = 2, and calculates solution

    else {
      System.out.println("What is " + num1 + " x " + num2 + "?");
      solution = num1 * num2;
    }
    // Prints mult. problem if type = 3, and calculates solution

    start = System.currentTimeMillis();
    // Gets current time (in milliseconds)
    
    ans = In.nextInt();
    // Reads user input for answer to math problem

    end = System.currentTimeMillis();
    // Gets current time again after user finishes answering

    time = (end - start)/1000;
    roundedTime = Math.round(time*10)/10.0;
    // Calculate total elapsed time it took user to answer (diff of end and start time), & multiply by 1000 to convert to secs
    // Round to 1 decimal place

    isCorrect = (ans == solution);
    overTime = (roundedTime >= 10);
    // Check if user answer matches solution
    // Check if user went over time limit 

    // If user answered correctly...
    if (isCorrect) {
      // ... but they went over time
      if (overTime) {
        System.out.println("Correct! But you took " + roundedTime + " seconds, so try to be faster next time :)");
        // Praise user, but tell them how many sec they took and remind them to be faster
      }

      /// ... and they're not over time
      else {
        System.out.println("Correct! Good job :D");
        // Praise user
      }
    }

    // If user answered incorrectly, encourage them to continue
    else {
      System.out.println("Uh oh, that's not right! Let's practice some more :)");
    }

    canMove = ((isCorrect) && (overTime == false));
    // Users can only mark a spot if they answered math q correctly AND took <10 sec 

    return canMove; // Return true or false (if true, user can move, if false, can't move)
  }

  // Fruitful function (takes 2D string array as parameter) - checks the board to see if game can end
  // Game ends if there's a winner, or if there's a draw (all spaces filled up)
  public static String checkWinner(String[][] gameBoard) {
    String winner;
    int spotsTaken = 0;
    // 'winner'- declare string variable for the winner ('X'(user), 'O'(computer), draw, or none)
    // Declare int variable for how many spots on the board are occupied (initialize & assign spotsTaken a value of 0)

    // Outer for loop counts row # (4 rows), nested for loop counts col # (3 cols)
    for (int m = 0; m < gameBoard.length; m++) {
      for (int n = 0; n < gameBoard[0].length; n++) {
        // Checks each element in array - if the element is not " ", meaning it's occupied by X or O, add 1 to spotsTaken (max is 12)
        if (!gameBoard[m][n].equals(" ")) {
          spotsTaken = spotsTaken + 1;
        }
      }
    }
    
    // Declare and initialize string variables that represent all possible winning cases (3 in a row)
    // Concatenates elements into one string from combos
    // Row combos:
    String case1 = gameBoard[0][0] + gameBoard[0][1] + gameBoard[0][2];
    String case2 = gameBoard[1][0] + gameBoard[1][1] +gameBoard[1][2];
    String case3 = gameBoard[2][0] + gameBoard[2][1] +gameBoard[2][2];
    String case9 = gameBoard[3][0] + gameBoard[3][1] + gameBoard[3][2];
    // Col combos
    String case4 = gameBoard[0][0] +gameBoard[1][0] + gameBoard[2][0];
    String case5 = gameBoard[0][1] +gameBoard[1][1] + gameBoard[2][1];
    String case6 = gameBoard[0][2] +gameBoard[1][2] + gameBoard[2][2];
    String case10 = gameBoard[1][0] +gameBoard[2][0] + gameBoard[3][0];
    String case11 = gameBoard[1][1] +gameBoard[2][1] + gameBoard[3][1];
    String case12 = gameBoard[1][2] +gameBoard[2][2] + gameBoard[3][2];
    // Diagonal combos:
    String case7 = gameBoard[0][0] + gameBoard[1][1] + gameBoard[2][2];
    String case8 = gameBoard[0][2] + gameBoard[1][1] + gameBoard[2][0];
    String case13 = gameBoard[1][0] +gameBoard[2][1] + gameBoard[3][2];
    String case14 = gameBoard[1][2] +gameBoard[2][1] + gameBoard[3][0];

    // Player wins if one of the above combos create a string of 'XXX'
    boolean playerWin = ((case1.equals("XXX"))||(case2.equals("XXX"))||(case3.equals("XXX"))||(case4.equals("XXX"))||(case5.equals("XXX"))||(case6.equals("XXX"))||(case7.equals("XXX"))||(case8.equals("XXX"))||(case9.equals("XXX"))||(case10.equals("XXX"))||(case11.equals("XXX"))||(case12.equals("XXX"))||(case13.equals("XXX"))||(case14.equals("XXX")));

    // Computer wins if one of the above combos create a string of 'OOO'
    boolean computerWin = ((case1.equals("OOO"))||(case2.equals("OOO"))||(case3.equals("OOO"))||(case4.equals("OOO"))||(case5.equals("OOO"))||(case6.equals("OOO"))||(case7.equals("OOO"))||(case8.equals("OOO"))||(case9.equals("OOO"))||(case10.equals("OOO"))||(case11.equals("OOO"))||(case12.equals("OOO"))||(case13.equals("OOO"))||(case14.equals("OOO")));

    // If player has 3 in a row, winner is 'X'
    if (playerWin) {
      winner = "X";
    }

    // If computer has 3 in a row, winner is 'O'
    else if (computerWin) {
      winner = "O";
    }

    // If all spots taken and neither playerWin nor computerWin is true, it's a draw
    else if (spotsTaken == 12) {
      winner = "draw";
    }

    // If no one wins, but there's still spots left on the board, game continues
    else {
      winner = "none";
    }

    return winner; // Return string of who's the winner (if there's one), whether it's a draw, or if it's none of the above & game continues
  }

  // Main Program
  public static void main(String[] args) {
    int begin, row, col; 
    // Declare int variables for....
    // 'begin' - for starting game
    // 'row' & 'col' - for storing row and col # to place piece

    String user = "user";
    String computer = "computer";
    String repeat; 
    // Declare string variables for....
    // 'user' & 'computer' - for parameter in rowNum() and colNum() (determine method of picking row & col #)
    // 'repeat' - for whether users want to play again

    boolean stopPicking;
    // Decalre boolean variable that stops computer's spot picking loop when true

    String[][] board = new String[4][3];
    // Declare 2 dimensional array to represent 4x3 game board

    Scanner In = new Scanner(System.in); //Create a scanner object

    // Do-while loop for whole game
    do {
      // Intialize the board with empty spaces using for loop and nested for loop (one loop for row #, one loop for col #)      
      for (int i = 0; i < 4; i++) {
        for (int j = 0; j < 3; j++) {
          board[i][j] = " ";
        }
      }

      printWelcome(); // Call on function to print game instructions
       
      // Once users are ready to begin game, they enter a 1
      // Do-while loop repeats if they enter int other than 1
      do {    
        System.out.println("Press 1 to begin.");
        
        // Nested while loop to ensure users don't enter doubles, strings, etc
        while (!In.hasNextInt()) {
          System.out.println("Invalid. Please try again.");
          In.next();
        }
      
        begin = In.nextInt();
      }

      while (begin != 1);
    
      // Once users enter one, game begins
      if (begin == 1) {
        // Do-while loop that keeps game going if there's no winner & there's open spots
        do {
          // Call on mathQ() function and runs it
          // If function returns true (user meets all requirements), user can make a move
          if (mathQ()) {
            // Calls on rowNum() and colNum() functions to let user pick where to place 'X' ('user' variable in parameter)
            do  {
              row = rowNum(user);
              col = colNum(user);
              
              // If user picked occupied spot (spot that's not " "), tell them to pick again
              if (!board[row][col].equals(" ")) {
                System.out.println("Sorry, this spot is already taken. Try again.");
              }
            }

            while (!board[row][col].equals(" "));
            // Do-while loop repeats and makes user choose again if the spot they chose is already occupied

            board[row][col] = "X";
            printBoard(board);
            // Change corresponding spot in array to 'X' & print the array using printBoard() function (array is in parameter)
          }

          // If mathQ() returns false (user doesn't meet requirements), tell them they can't move
          else {
            System.out.println("You can't make a move unfortunately.");
          }

          checkWinner(board); // Call on checkWinner() to see if user has won 

          // If checkWinner doesn't return 'X'(user hasn't won yet), let computer go
          if (!checkWinner(board).equals("X")) {
            System.out.println("Computer moves"); 
            
            // Calls on rowNum() and colNum() functions in loop to generate where to place 'O' ('computer' variable in parameter)
            // Do-while loop repeats while stopPicking is false
            do {
              row = rowNum(computer);
              col = colNum(computer);

              // If computer generates a spot that's already taken 1-3 times consecutively, retry
              if (!board[row][col].equals(" ")) {
                row = rowNum(computer);
                col = colNum(computer);
                stopPicking = false;

                if (!board[row][col].equals(" ")) {
                  row = rowNum(computer);
                  col = colNum(computer);
                  stopPicking = false;

                  // On the 4th consecutive time it picks taken spot, stop loop (computer makes no move this turn)
                  if (!board[row][col].equals(" ")) {
                    System.out.println("Oh no, looks like the computer answered a math question wrong and lost their turn!");
                    // Tell user computer answered a math problem wrong and can't go

                    stopPicking = true; // Ends loop
                  } 
                }
                else {
                  board[row][col] = "O"; 
                  stopPicking = true;
                }
              }
              else  {
                board[row][col] = "O"; 
                stopPicking = true;
              }
            }
            // If computer generates unoccupied spot before 4th try, change corresponding spot in array to 'O', & set stopPicking to true to end loop

            while (stopPicking == false);
            
            printBoard(board); 
            checkWinner(board); 
            // Print board using printBoard()
            // Call on checkWinner() to see if computer has won  
          }   
        }

        while (checkWinner(board).equals("none"));
        // Once checkWinner returns string other than "none", stop loop & end game

        // If checkWinner returns "X", print user has won      
        if (checkWinner(board).equals("X")) {
          System.out.println("Woohoo! You won :)");
        }

        // If checkWinner returns "O", print user has lost
        else if (checkWinner(board).equals("O")) {
          System.out.println("Aw man, looks like you lost. Better luck next time :)");
        }

        // If checkWinner returns "draw", print it's a draw
        else if (checkWinner(board).equals("draw")) {
          System.out.println("Looks like it's a draw! Great effort :)");
        }
      }

      // Ask user if they want to play again
      System.out.println("Would you like to play again? Enter YES or NO:");
      repeat = In.next(); // Read user input for if they want to repeat
    }

    while (repeat.equals("YES"));
    // Restart game loop if user enters "YES"

    // If user doesn't enter "YES", thank user for playing
    if (!repeat.equals("YES")) {
      System.out.println("Thank you for playing Tick Tock Toe!");
    }    
  }
}
