Caltech CS2 Assignment 9: Othello

I implemented my code in java - so please run the Java makefile and run the Java program with
Java TestGame [AI 1 NAME] [AI 2 NAME]

To improve my code from week 1 of the Othello Project, I implemented an AlphaBetaPlayer that uses minimax with alpha beta pruning up to a depth of 4. (I worked alone, so I did all of the coding for my project). I attempted to combine the heuristic algorithm of marking certain board squares as particularly valualbe and others as not so valuable with the minimax algorithm to get the AI tournament-worthy, and I had mixed success with this approach and had to abandon it. Alpha beta pruning allowed me to extend the depth of my minimax search as well, so I got results much faster.

TestPlayer is the first part of the assignment that implements a randomly-playing AI.
ThinkingPlayer implements the Heuristic algorithm.
MinimaxPlayer implements a 2-ply minimax algorithm.
AlphaBetaPlayer implements minimax with Alpha-Beta Pruning, along with a heuristic algorithm.

See [assignment9_part1.html](http://htmlpreview.github.io/?https://github.com/caltechcs2/othello/blob/master/assignment9_part1.html) and [assignment9_part2.html](http://htmlpreview.github.io/?https://github.com/caltechcs2/othello/blob/master/assignment9_part2.html)
