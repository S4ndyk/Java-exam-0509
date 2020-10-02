Write a program for storage management. The program offers the ability to add items to a storage, removing items from a storage, printing out the items of a storage, and item lookup from a storage.
 
The program accepts the follwing commands:
 
`add (storage) (item)` -- add item to a storage
`list (storage)` -- lists the items of a given storage
`search (storage) (item)` -- tells wether a item is in a given storage
`remove (storage) (item)` -- removes one item from a given storage
`stop` -- ends the program

In the example above (storage) and (item) are user inputs withouth whitespace. You may assume that the user never misstypes (storage) or (item). If a command is not found the program prints an error message.
 
Example run:
 
```
Storage management!
 
add (storage) (item) -- add item to a storage
list (storage) -- lists the items of a given storage
search (storage) (item) -- tells wether a item is in a given storage
remove (storage) (item) -- removes one item from a given storage
stop -- ends the program
 
Input command
add attic flour
 
Input command
add attic flour
 
Input command
list attic
Listing contents of attic
flour
flour
 
Input command
add attic carrot
 
Input command
list attic
Listing contents of attic
flour
flour
carrot

Input command
search attic apple
Item apple could not be found in storage attic

Input command
search attic carrot
Item apple found in storage attic 

Input command
search basement carrot
Item carrot could not be found in storage basement

Input command
remove attic flour

Input command
list attic
Listing contents of attic
flour
carrot

Input command
seach place
Unknown comman seach

Input command
stop
Thank you!
```
 
Notice! The following command might be of use:

```java 
String input = "string another";
String[] parts = input.split(" ");
// parts[0] contains "string"
// parts[1] contains "another"
```
 