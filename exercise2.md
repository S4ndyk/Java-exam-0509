You have the following exception at your use. Insert it into the exercise template.

```
public class PasswordException extends Exception {
    public PasswordException(String message) {
        super(message);
    }
}
```

Additionally you have the following interface at your use. Insert it into the exercise template.

```
interface Saver {
    void addNote(String note) throws PasswordException; // Adds a note to the diary
    void printNote(int index) throws PasswordException; // Prints the note in a given index
    ArrayList<String> allNotes() throws PasswordException; // Return a list of all notes
    int noteCount() throws PasswordException; // Returns the count of all notes added
}
```

Create a class `SecretDiary` that implements the interface `Saver`. The class must have a constructor that receives a string-type password as a parameter. Additionally the diary must have a method `void checkPassword(String attempt)` which the user can call to attempt to unlock the password. The method in itself does not print out anything.

If a password attempt matches with the password given in the constructor the user can call all of the methods provided by the saver interface. After a correct attemp the user can call the methods as many times as he/she wants without attempting the password again.

If a password attempt fails or a password has not been entered, any operation provided by the `Saver` interface throws a `PasswordException`. The message of the exception does not matter.
