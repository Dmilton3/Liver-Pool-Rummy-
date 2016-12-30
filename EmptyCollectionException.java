/**
 * Created by ASUS on 3/2/2016.
 */

public class EmptyCollectionException extends RuntimeException {

         public EmptyCollectionException(String collection)
         {
             super("The " + collection + " is empty.");
         }
}
