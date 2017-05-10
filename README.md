To run from command line
-----------------
gradle clean fatJar - builds jar with dependencies

java -jar -Dprinter.type=basic build/libs/dragons-of-mugloar-all-1.0-SNAPSHOT.jar 100

where printer.type can be 'basic' or 'detailed' and 100 is number of games to play.

Main Arguments
--------------
args[0] = games to play

Environment variables
------------------
printer.type=basic or detailed

Basic shows a simple output with games played and achieved winrate

Detailed shows output with games played and achieved winrate. 
And a detailed log of dragon stats, knight stats and game result.



