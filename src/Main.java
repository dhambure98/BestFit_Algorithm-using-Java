import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BestFitMemoryAllocator {
    private int[] blockSizes;    // Memory block sizes
    private int[] processSizes;  // Process sizes

    public BestFitMemoryAllocator(int[] blockSizes, int[] processSizes) {
        this.blockSizes = blockSizes;
        this.processSizes = processSizes;
    }

    public String allocateMemory() {
        int[] allocations = new int[processSizes.length];

        // Initialize all allocations to -1 (not allocated)
        for (int i = 0; i < allocations.length; i++) {
            allocations[i] = -1;
        }

        // Loop through each process
        for (int i = 0; i < processSizes.length; i++) {
            int bestIndex = -1; // Track the best block for the process

            // Find the smallest block that fits the process
            for (int j = 0; j < blockSizes.length; j++) {
                if (blockSizes[j] >= processSizes[i]) {
                    if (bestIndex == -1 || blockSizes[j] < blockSizes[bestIndex]) {
                        bestIndex = j;
                    }
                }
            }

            // If a suitable block is found, allocate it
            if (bestIndex != -1) {
                allocations[i] = bestIndex;
                blockSizes[bestIndex] -= processSizes[i];
            }
        }

        return generateResults(allocations);
    }

    private String generateResults(int[] allocations) {
        StringBuilder result = new StringBuilder();
        result.append("<html><div style='text-align: center;'><table style='margin: auto; text-align: center; width: 80%; border-collapse: collapse;' border='1'>");
        result.append("<tr><th>Process No.</th><th>Process Size</th><th>Block Allocated</th></tr>");
        for (int i = 0; i < processSizes.length; i++) {
            String block = (allocations[i] != -1) ? "Block " + (allocations[i] + 1) : "Not Allocated";
            result.append(String.format("<tr><td>%d</td><td>%d</td><td>%s</td></tr>", i + 1, processSizes[i], block));
        }
        result.append("</table></div></html>");
        return result.toString();
    }
}

public class Main {
    public static void main(String[] args) {

    }
}
