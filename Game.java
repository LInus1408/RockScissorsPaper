import java.util.Random;
import java.util.Scanner;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.*;  
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.SignatureException;
import java.util.Base64;
import java.util.Scanner;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.Formatter;
import java.math.*;


public class Game {
  
  public static int Youwin = 0;
  public static int Computerwin = 0;
  public static int Draw = 0;
  public static int num;
  private Scanner inputScanner;

  
  public static String[] values;

  public static void main(String[] args) throws Exception{
    
    
    Game game = new Game();
    if ((args.length < 3) || (args.length  % 2 == 0)) {   
      System.out.println("Please enter a valid value");
      System.exit(0);
    } 

    for (int i = 0; i < args.length; i++) { 
    for (int j = i + 1 ; j < args.length; j++) { if (args[i].equals(args[j])) { 
      System.out.println("Please enter a valid value");
      System.exit(0);
    }
  }

} 
    values = args;

    while(true){

    String key =  game.getAlphaNumericString();

    int computer = 1 + (int) (Math.random() * values.length);
    String hmac = calculateHMAC((values[computer - 1]), key);
    System.out.println();
    System.out.println("HMAC: \n" + hmac); // hmac выводит
    game.startGame();
    game.Inputvalue();
    

    if (num == 0) {
      System.out.println("The end");
      game.printGameStats();
      System.exit(0);
    }
    System.out.println("Your move: " + values[num - 1]);

    
    System.out.println("Computer move: " + values[computer - 1]);

    int result = (values.length + num - computer) % values.length;

    if (result == 1) {
      System.out.println("You win");
      Youwin++;
      System.out.println("HMAC key:" + key);
    } else if (result == 2) {
      System.out.println("Computer win");
      Computerwin++;
      System.out.println("HMAC key:" + key);
    } else {
      System.out.println("Draw");
      Draw++;
      System.out.println("HMAC key: " + key);
    }
  }
}
static String getAlphaNumericString()
{
int n = 16;
      // length is bounded by 256 Character
      byte[] array = new byte[256];
      new Random().nextBytes(array);

      String randomString
          = new String(array, Charset.forName("UTF-8"));

      // Create a StringBuffer to store the result
      StringBuffer r = new StringBuffer();

      // remove all spacial char
      String  AlphaNumericString
          = randomString
                .replaceAll("[^A-Za-z0-9]", "");

      // Append first 20 alphanumeric characters
      // from the generated random String into the result
      for (int k = 0; k < AlphaNumericString.length(); k++) {

          if (Character.isLetter(AlphaNumericString.charAt(k))
                  && (n > 0)
              || Character.isDigit(AlphaNumericString.charAt(k))
                     && (n > 0)) {

              r.append(AlphaNumericString.charAt(k));
              n--;
          }
      }

      // return the resultant string
      return r.toString();
}




 private void startGame() {


    for (int i = 0; i < values.length; i++){
      System.out.println((i + 1)+ " - " + values[i]);
  }
  System.out.println("0 - exit");
  System.out.println("? - help");

  }
  
  private void Inputvalue() { 
    int i3 = -1;

      inputScanner = new Scanner(System.in);
    do {
      System.out.print("Enter your move: ");
      String userInput = inputScanner.nextLine();
      char firstLetter = userInput.charAt(0);
      if (firstLetter == '?') {
        table();
      }
      try {
        i3 = Integer.parseInt(userInput);
    } catch (NumberFormatException e) {  
    } 
      num = i3;
        } while (num < 0 || num > values.length);
  }

  private static final String HMAC_SHA512 = "HmacSHA512";

  private static String toHexString(byte[] bytes) {
      Formatter formatter = new Formatter();
      for (byte b : bytes) {
          formatter.format("%02x", b);
      }
      return formatter.toString();
  }
  
  public static String calculateHMAC(String  moveComputer, String key)
      throws SignatureException, NoSuchAlgorithmException, InvalidKeyException
  {
      SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), HMAC_SHA512);
      Mac mac = Mac.getInstance(HMAC_SHA512);
      mac.init(secretKeySpec);
      return toHexString(mac.doFinal( moveComputer.getBytes()));
  }
  private void table() {
            // Вывод линии
            System.out.print("+");
            printDashes(100);
            System.out.println("+");
        
            // Вывод заголовков таблицы
            System.out.printf("|  %8s  |  %12s  |  %12s  |  %12s  |  %12s  |  %15s  |\n",
                    "   ", "Rock", "Paper", "Scissors", "lizard", "Spock");
        
            // Вывод линии
            System.out.print("|");
            printDashes(100);
            System.out.println("|");
        
            // Вывод значений
            System.out.printf("|  %8s  |  %12s  |  %12s  |  %12s  |  %12s  |  %15s  |\n",
                    "Rock", "Tie", "Rock win", "Rock win",  "lizard win",  "Spock win");
            // Вывод линии
            System.out.print("|");
            printDashes(100);
            System.out.println("|");           
           System.out.printf("|  %8s  |  %12s  |  %12s  |  %12s  |  %12s  |  %15s  |\n",
                    "Paper", "Rock win", "Tie", "Paper win", "Paper win", "Spock win"); 
            // Вывод линии
            System.out.print("|");
            printDashes(100);
            System.out.println("|");                            
            System.out.printf("|  %8s  |  %12s  |  %12s  |  %12s  |  %12s  |  %15s  |\n",
                    "Scissors", "Rock win", "Paper win", "Tie", "Scissors win", "Scissors win");    
            // Вывод линии
            System.out.print("+");
            printDashes(100);
            System.out.println("+");    
            System.out.printf("|  %8s  |  %12s  |  %12s  |  %12s  |  %12s  |  %15s  |\n",
                    "lizard", "lizard win", "Paper win", "Scissors win", "Tie" ,"lizard win" );    
            // Вывод линии
            System.out.print("+");
            printDashes(100);
            System.out.println("+");   
            System.out.printf("|  %8s  |  %12s  |  %12s  |  %12s  |  %12s  |  %15s  |\n",
                      "Spock", "Spock win", "Spock win", "Scissors win", "lizard win"  , "Tie" );    
            // Вывод линии
            System.out.print("+");
            printDashes(100);
            System.out.println("+");      
  }
  

  private void printGameStats() {
    int wins = Youwin;
    int losses = Computerwin;
    int ties = Draw;
    double percentageWon = (wins + ((double) ties) / 2) / (wins + losses + ties);          

    // Вывод линии
    System.out.print("+");
    printDashes(68);
    System.out.println("+");

    // Вывод заголовков таблицы
    System.out.printf("|  %6s  |  %6s  |  %6s  |  %12s  |  %14s  |\n",
            "WINS", "LOSSES", "TIES", "GAMES PLAYED", "PERCENTAGE WON");

    // Вывод линии
    System.out.print("|");
    printDashes(10);
    System.out.print("+");
    printDashes(10);
    System.out.print("+");
    printDashes(10);
    System.out.print("+");
    printDashes(16);
    System.out.print("+");
    printDashes(18);
    System.out.println("|");

    // Вывод значений
    System.out.printf("|  %6d  |  %6d  |  %6d  |  %12d  |  %13.2f%%  |\n",
            wins, losses, ties, (wins + losses + ties), percentageWon * 100);

    // Вывод линии
    System.out.print("+");
    printDashes(68);
    System.out.println("+");
}

private void printDashes(int numberOfDashes) {
    for (int i = 0; i < numberOfDashes; i++) {
        System.out.print("-");
    }
}

}


