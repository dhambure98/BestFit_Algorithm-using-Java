import java.util.Scanner;

class BestFitMemoryAllocator2 {
    private int[] blockSizes;
    private int[] processSizes;

    public BestFitMemoryAllocator2(int[] blockSizes, int[] processSizes) {
        this.blockSizes = blockSizes;
        this.processSizes = processSizes;
    }

    public void allocateMemory() {
        int[] allocations = new int[processSizes.length];

        for (int i = 0; i < allocations.length; i++) {
            allocations[i] = -1;
        }

        for (int i = 0; i < processSizes.length; i++) {
            int bestIndex = -1;

            for (int j = 0; j < blockSizes.length; j++) {
                if (blockSizes[j] >= processSizes[i]) {
                    if (bestIndex == -1 || blockSizes[j] < blockSizes[bestIndex]) {
                        bestIndex = j;
                    }
                }
            }

            if (bestIndex != -1) {
                allocations[i] = bestIndex;
                blockSizes[bestIndex] -= processSizes[i];
            }
        }
        displayResults(allocations);
    }

    private void displayResults(int[] allocations) {
        System.out.println("\nProcess Allocation Results:");
        System.out.printf("%-15s %-15s %-15s\n", "Process No.", "Process Size", "Block Allocated");

        for (int i = 0; i < processSizes.length; i++) {
            String block = (allocations[i] != -1) ? "Block " + (allocations[i] + 1) : "Not Allocated";
            System.out.printf("%-15d %-15d %-15s\n", i + 1, processSizes[i], block);
        }

        System.out.println("\nRemaining Memory Blocks:");
        System.out.printf("%-15s %-15s\n", "Block No.", "Remaining Size");
        for (int i = 0; i < blockSizes.length; i++) {
            System.out.printf("%-15d %-15d\n", i + 1, blockSizes[i]);
        }
    }
}

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the sizes of memory blocks :");
        String[] blockInput = scanner.nextLine().split(",");

        System.out.println("Enter the sizes of processes :");
        String[] processInput = scanner.nextLine().split(",");

        try {
            int[] blockSizes = new int[blockInput.length];
            int[] processSizes = new int[processInput.length];

            for (int i = 0; i < blockInput.length; i++) {
                blockSizes[i] = Integer.parseInt(blockInput[i].trim());
            }
            for (int i = 0; i < processInput.length; i++) {
                processSizes[i] = Integer.parseInt(processInput[i].trim());
            }

            BestFitMemoryAllocator allocator = new BestFitMemoryAllocator(blockSizes, processSizes);
            allocator.allocateMemory();
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter valid numbers separated by commas.");
        }

        scanner.close();
    }
}
