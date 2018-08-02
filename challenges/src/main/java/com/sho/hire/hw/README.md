# Analysis:
The implementation of Stack provided uses a Dynamic Array.
The underlying array's size, is doubled every time when the array reaches full
capacity.(Well, doubled + 1. For our conversation sake let's just say, we are
only doubling it. It makes calculations easy to follow.)
Using dynamic array is pretty standard practice in java. Collections framework,
uses very similar technique to ensure capacity.


## Amortized Analysis of dynamic array:
 <p>
 Inserts into array are O(1) operations. Except those few times, when capacity
 doubles, where we are creating a new array of size 2X and copying all of X items
 into new array which takes about O(X).
 </p>
 <p>
 Let's look at the average time it takes. By the end of all insertions, say our array size is N.
 Last time the array must have doubled when capacity was at N/2. And it would have gotten to N/2 from N/4. <br>

 Summing it all up:
 <br> ` .. + N/16 + N/8 + N/4 + N/2   = N` <br>
 Since, this is an average, we are talking about `O(1)` additional time when we spread the doubling time across all insertions.
 </p>

## Problem with current approach.
 <p>
 In the given stack implementation, we can take out data from stack (pop operations).
 There is a good chance that we consume a lot of memory even when we are not using all of it.
 <br>For instance: </br> Let's just say that our stack grew from 0 to N (an arbitrary large number).
 But, a series of pop operations occurred on it to remove most of the data and our stack has very
 few elements left in it for rest of its life. But, with current implementation provided we have N
 space allocated to us that is not being used anymore.

 <br>We probably should downsize our array, when our usage of stack drops below a threshold ( a number that is determined after some production analysis).
 </p>

 ## Possible alternatives:
 We need to make decisions based on how and where this stack is being used. But here are other alternatives that we can look into.

 * Linked List: We can look into using linked list, where we create nodes when needed and remove nodes when not needed.
