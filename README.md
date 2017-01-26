Nonogram
======

## Dictionary
* **Block**: the set of numbers determinig the number of filled tiles for each row and columns
* **Picture**: the configuration of black and white tiles arising from the given blocks

## Specifications

* Core functionality
  * The finished app should be able to...
    * present the user with a GUI
    * Read files with block and picture information
    * measure solution time
    * compare finished solution with picture information from the file
    * score the user based on time and differences between user and actual solution
    
* Optional functionality
  * Life system
  * Giving hints to partial solutions in exchange for lives/points
  * Printing blocks generated from pictures to a pdf ('borrowed' from BGU app)
  
  
* Gameplay specifics
  * User can place different kinds of tiles with clicks
  * This should be similar to minesweeper in that...
    * 'Certain' tiles (white or black) should be placed with left click
    * 'Helper' tiles (probably white/black)should be placed with right click
  * Starting grid should be slightly off-white to differentiate from solved white tiles
