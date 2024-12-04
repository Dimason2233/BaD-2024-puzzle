<h1>Puzzle Solver</h1>
<p>This program written on Java, helps to build the longest puzzle from numbers, by layly connecting numbers that have same two digits at the head/tail respectively.</p>

<h1>How does this work</h1>
<p>1. Input. The program reads a file, name of which must be given in parameters.<br>
2. Creating elements of the puzzle - objects that contains head, body and tail of the element separately.<br>
3. Setting the connections between all elements in specified field in object.<br>
4. Finding longest puzzle using recursive DFS.<br>
5. Saving result in log file and printing it in console.</p>

<h1>Pros and cons</h1>
<p>+ Program can work if there given numbers with different length.<br>
- But if there less than 4 digits it doesn`t work (fixable)<br>
+ Digits can be changed to any symbols</p>
