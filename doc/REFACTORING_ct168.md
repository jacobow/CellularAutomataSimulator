Cell Society Refactoring
===================

	I chose to refactor the instantiation of my DisplayCell classes. I was using an enumerated type before to link each simulation to the correct class, but this wasn't particularly flexible since you would have to edit this type when adding a new simulation (which defeats the purpose). So, I created a properties file in resources instead to link the simulation type to the correct class, and instantiated the class from name. I then also created a SimulationInstantiationException class to be thrown in the event that this fails, dramatically improving my error handling. Finally, I added an alert in MainWindow to notify the user that instantiated had failed.

	This edit makes the GUI significantly more flexible--adding new simulations is mush simpler from the GUI perspective. Additionally, this deals with Exceptions well, delivering the exception all the way back up to the MainWindow class (and, if needed, could easily be altered to propogate all the way up to the controller). This makes exception handling much more intuitive and flexible.

> Here is a link to the commit reflecting this change: 
[Refactored DisplayCell Instantiation](https://git.cs.duke.edu/CompSci308_2016Fall/cellsociety_team11/commit/5c6bf84ab26fd4bcdcbf63b38a5a66474034bd8b)
