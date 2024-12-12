# Best Fit Memory Allocation Algorithm

This project implements the **Best Fit Memory Allocation Algorithm** using Java Swing. The algorithm assigns processes to memory blocks in such a way that the smallest available block that can accommodate the process is used, minimizing wasted space.

## Features
- A graphical user interface (GUI) built with Java Swing.
- Users can input memory block sizes and process sizes.
- The program allocates memory using the Best Fit algorithm.
- Results are displayed in a clean HTML table format.
- Input validation and error handling for invalid inputs.

## Requirements
- Java 8 or higher.
- Frontend built using Java Swing.

## Usage
1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/best-fit-memory-allocator.git

## Navigate to the project directory:

cd best-fit-memory-allocator
Compile and run the Java application. If you are using an IDE, open the project and run the Main class. Otherwise, you can run it from the command line:

javac Main.java
java Main
Input Format
You will be prompted to enter block sizes and process sizes in a comma-separated format.

## Example Input for Block Sizes:

100, 200, 300, 400
Example Input for Process Sizes:

120, 210, 300

Example Output
The results will be displayed in a table format that shows:

The process number.
The process size.
The block allocated or a message indicating if no block was allocated.
Screenshots
Memory Allocation Interface

Allocation Results

## Algorithm Explanation
The Best Fit Algorithm works as follows:

Initialize: All processes are initially not allocated any block.
Iterate over processes: For each process, search for the smallest block that can accommodate it.
Allocate the block: If a block is found, allocate it to the process and reduce the block size.
Display results: The allocation results are displayed in an HTML table.
Error Handling

Invalid input: If the input format is incorrect (non-numeric values or empty inputs), an error message will be displayed asking the user to provide valid input.
Process allocation failure: If no suitable block is found for a process, it will be marked as "Not Allocated."

If you want to contribute to this project, feel free to fork it, make changes, and create a pull request. Your contributions are welcome!

License [Akila Dhambure Liyanage]

## Acknowledgments
Java Swing: For building the graphical user interface.
Best Fit Algorithm: Memory allocation algorithm for efficient block assignment.
Feel free to modify or improve the project, and don't forget to add your own ideas to make it even more useful!

### Notes:
- Replace `https://github.com/your-username/best-fit-memory-allocator.git` with your actual repository URL.
- Replace `path_to_your_screenshot_image_1` and `path_to_your_screenshot_image_2` with the actual paths to your images. If you don't have screenshots, you can remove that section.
