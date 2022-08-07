# hashmap-with-max-size

It is a small project showing how to limit a HashMap size.
As an example, two classes were proposed to simulate a cache working (using a LinkedHashMap).

## Operation ##

Consider a LinkedHashMap with a maximum size equal to five elements (having keys numbered from 1 to 5).
In this case, the eldest element is at position 0 (head of the list), and the newest is at position 4 (tail of the list).

`head (eldest) --> |1|2|3|4|5| <-- tail (newest)`

Inserting a new element (for example, 6) will cause the removal of the eldest (1). The result LinkedHashMap will be:

`head (eldest) --> |2|3|4|5|6| <-- tail (newest)`

## Proposed Classes ##

Two implementations were proposed in the project. Follows a brief description:

 - **AllOperations**: All the operations (*GET*, *PUT*, and *REPLACE*) over an element
will move it to the tail of the queue. I.e., any access to a given element renews its
importance inside the cache.
 

 - **InsertUpdate**: Only the *PUT* and *REPLACE* operations over an element will
move it to the tail of the queue. Accessing (*GET* method) does not
change its position inside the LinkedHashMap.

*REPLACE* operation can be performed by:
 - explicitly calling the *replace*(key, value) method or;
 - calling the *put*(key, value) method passing an existing key as a param.

p.s.: both classes have a method called *getMostRecent*() which returns the most recent element of the cache.

## Unit Testing ##

You can find inside the project tests for both classes considering all of the allowed operations.

## Acknowledgments ##

Thank you very much for your time in evaluating my project. Feel free to contact me and share your thoughts!

