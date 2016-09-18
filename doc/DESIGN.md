**Introduction** - *This section describes the problem your team is trying to solve by writing this program, the primary design goals of the project (i.e., where is it most flexible), and the primary architecture of the design (i.e., what is closed and what is open). This section should discuss the program at a high-level (i.e., without referencing specific classes, data structures, or code).*

Problem we’re trying to solve: We’re trying to have a program that can implement multiple CAs and be flexible enough to add more CAs to it.

Primary design goals: We want the program to be able to add more CAs easily by having several classes extending abstract classes that can deal with different states, logic, and cell movement between different CAs.

Primary architecture: We want a class that handles checking the neighbors of the cells and transitioning from state to state. There will be a class that provides the general features needed for a cell. Another class will handle the rules that are specific to the CA.
____________________________________________________________________________________

**Overview** - *This section serves as a map of your design for other programmers to gain a general understanding of how and why the program was divided up, and how the individual parts work together to provide the desired functionality. As such, it should describe specific components you intend to create, their purpose with regards to the program's functionality, and how they collaborate with each other. It should also include a picture of how the components are related (these pictures can be hand drawn and scanned in, created with a standard drawing program, or screen shots from a UML design program). This section should discuss specific classes, methods, and data structures, but not individual lines of code.
Specific components we intend to create:*

Grid abstract class: This class will serve as an abstract class to handle requiring all classes that extend it to have basic methods requiring each grid to move to its next state and evaluate each cell.

Cell abstract class: This class will give all classes which extend it the responsibility of making methods which can return the state of the cell, evaluate the cell’s next state, and commit this next state as the value of the cell.

CA rules abstract class: This class will serve as a general abstract class that will include methods for different CA states, logic, and cells.

GUI class: Responsible for handling user input and displaying a chosen CA grid.
Main class: Initializes the GUI and uses a TimeLine to loop over frames of the grid to be displayed.

Picture
____________________________________________________________________________________

**User Interface** - *This section describes how the user will interact with your program (keep it very simple to start). It should describe the overall appearance of program's user interface components and how users interact with these components (especially those specific to your program, i.e., means of input other than menus or toolbars). It should also include one or more pictures of the user interface (these pictures can be hand drawn and scanned in, created with a standard drawing program, or screen shots from a dummy program that serves as a exemplar). Finally, it should describe any erroneous situations that are reported to the user (i.e., bad input data, empty data, etc.). This section should go into as much detail as necessary to cover all your team wants to say.*

UI Buttons: Step button to increment forward by one state. Start button to start the simulation. Stop button to pause the simulation. Reset button to start the simulation over. There could be other buttons added to control the simulation that are specific to the CA being simulated. For example, in the CA in the picture, there are buttons to control the speed of the simulation, the percent of sharks, and the percent of fish.

Erroneous situations: If the data is bad or empty, there will be an error message that pops up to notify the user of the specific error.
____________________________________________________________________________________

**Design Details** - *This section describes each component introduced in the Overview in detail (as well as any other sub-components that may be needed but are not significant to include in a high-level description of the program). It should describe how each component handles specific features given in the assignment specification, what resources it might use, how it collaborates with other components, and how each could be extended to include additional requirements (from the assignment specification or discussed by your team). Include the steps needed to complete the Use Cases below to help make your descriptions more concrete. Finally, justify the decision to create each component with respect to the design's key goals, principles, and abstractions. This section should go into as much detail as necessary to cover all your team wants to say.*

Grid abstract class: This class will handle requiring its subclasses to have a method that moves the cells and checks the neighbors of the cells.

Cell abstract class:  The classes which extend this class will be used by the grid to update cells.  This will be done in a Cell class by checking a cell’s state, passing this information to the grid, receiving neighboring cell states from the grid, determining the cell’s next state for later, and then committing this state to the cell once the grid is ready to go.

CA rules abstract class:  The classes which extend this class will have all the particular rules which determine which state a cell is to move to next.  Only the Cell class will need to use these rules, as Cells are given the responsibility of evaluating themselves.  The methods in a Rules class will vary heavily depending on the CA that needs to be implemented.
____________________________________________________________________________________

**Design Considerations** - *This section describes any issues which need to be addressed or resolved before attempting to devise a complete design solution. It should include any design decisions that the group discussed at length (include pros and cons from all sides of the discussion) as well as any assumptions or dependencies regarding the program that impact the overall design. This section should go into as much detail as necessary to cover all your team wants to say.*

Having a grid class allows the program to have a class that brings all of the other classes together. It uses the cell class to identify each cell and its characteristics, and it uses the rules class to determine how each cell will move in response to its neighbors. Having generalized cell and rules classes will be very helpful for allowing the program to implement new CAs but the drawback will be that making these generalized classes will take a lot of planning in order to make sure that they are flexible enough. The rules class will need to be written flexibly enough so that the features of at least four different CAs will be able to be handled.
____________________________________________________________________________________

**Team Responsibilities** - *This section describes the program components each team member plans to take primary and secondary responsibility for and a high-level plan of how the team will complete the program.*

The Grid and Rules classes will be taken care of by Jacob.  Since game of life is an easier set of rules and good for testing, Jacob will implement this extension of the Rules class alongside whichever CA he is assigned later. The GUI class will be taken care of by Quin, and the Cell class will be taken care of by Noel.

After all the abstract classes have been completed, the team will assign one of the required CAs (segregation, predator-prey, fire) to each of the members. Individually, each member will work on taking care of the GUI, Cell, and Rules class for their respective CA using the abstract methods that the group wrote together. After three of the CAs have been written, the group will work together on adding the fourth CA while heavily assessing the overall project’s flexibility and determining what needs to be improved to improve the ease of adding more CAs.
____________________________________________________________________________________

**Use Cases**
*Apply the rules to a middle cell: set the next state of a cell to dead by counting its number of neighbors using the Game of Life rules for a cell in the middle (i.e., with all its neighbors)*

The middle cell uses the checkNeighbors() method provided by the grid class in order to determine whether it will live or die.

*Apply the rules to an edge cell: set the next state of a cell to live by counting its number of neighbors using the Game of Life rules for a cell on the edge (i.e., with some of its neighbors missing)*

Use the GameOfLifeRules subclass to implement the rules that are specific to an edge cell, and then use the Grid class to handle how the cells interact with the rules in place.

*Move to the next generation: update all cells in a simulation from their current state to their next state and display the result graphically*

Use the method provided by the Grid class that handles transitioning from state to state based on the logic provided by the Rules class. Use the GUI class to display the result graphically.

*Set a simulation parameter: set the value of a parameter, probCatch, for a simulation, Fire, based on the value given in an XML fire*

Have the main method read all of the data needed from the XML file and then pass the data on as a parameter to the method that sets probCatch. Set the value of the parameter, probCatch, of the subclass that extends the rules class. 

*Switch simulations: use the GUI to change the current simulation from Game of Life to Wator*

Have a key input on the GUI enabled that allows the esc key to be pressed to return to the home menu. From the home menu, the user can select which simulation they want to run by pressing the button corresponding to the simulation.
