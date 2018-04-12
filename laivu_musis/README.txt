This is a battleship game where one can:
•Login with his/her name and email address
•One can set enter the ship coordinates (4x1, 3x2, 2x3, 1x4) - main rule is that there has to be a distance from another ship by at least one cell;
•Another player has to join the game as well in order to play by connecting to a server http://miskoverslas.lt/laivu_musis/
•Once both players send the ship location to a server, the game can start;

My main idea was to put the entire game logic under the do while loop where the game lasts as long as there is no winner in the game (until all the ships are sunk);
As each shot triggers a new event I was linking everything to the new events by checking their dates (of long type) which were unique in a sense and adding them to a Hashset which
does not allow do add already existent dates.

My added extra features were as follows:
•The game controls that one would enter a certain size of the ship;
•I have created a log which controls that one would not make a shot to the same target twice;


