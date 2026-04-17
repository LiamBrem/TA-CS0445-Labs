import java.util.*;

public class StringHelp
{
     static Random R = new Random();

     public static String randWord(int maxSize)
     {
          int length = R.nextInt(maxSize) + 1; // Length between 1 and maxSize
          char [] letters = new char[length];
          for (int i = 0; i < letters.length; i++)
          {
               int val = R.nextInt(26) + 65;
               char ch = (char) val;
               letters[i] = ch;
          }
          return new String(letters);
     }
}
