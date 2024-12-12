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
        JFrame frame = new JFrame("Best Fit Memory Allocator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Best Fit Memory Allocation Algorithm", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel blockLabel = new JLabel("Enter Block Sizes (comma-separated):");
        JTextField blockField = new JTextField();

        JLabel processLabel = new JLabel("Enter Process Sizes (comma-separated):");
        JTextField processField = new JTextField();

        JButton allocateButton = new JButton("Allocate Memory");

        inputPanel.add(blockLabel);
        inputPanel.add(blockField);
        inputPanel.add(processLabel);
        inputPanel.add(processField);
        inputPanel.add(new JLabel());
        inputPanel.add(allocateButton);

        JLabel resultArea = new JLabel("", JLabel.CENTER);
        resultArea.setVerticalAlignment(SwingConstants.TOP);
        resultArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        allocateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String[] blockInput = blockField.getText().split(",");
                    String[] processInput = processField.getText().split(",");

                    int[] blockSizes = new int[blockInput.length];
                    int[] processSizes = new int[processInput.length];

                    for (int i = 0; i < blockInput.length; i++) {
                        blockSizes[i] = Integer.parseInt(blockInput[i].trim());
                    }
                    for (int i = 0; i < processInput.length; i++) {
                        processSizes[i] = Integer.parseInt(processInput[i].trim());
                    }

                    BestFitMemoryAllocator allocator = new BestFitMemoryAllocator(blockSizes, processSizes);
                    String results = allocator.allocateMemory();
                    resultArea.setText(results);
                } catch (Exception ex) {
                    resultArea.setText("<html><p style='text-align: center;'>Invalid input. Please enter valid numbers separated by commas.</p></html>");
                }
            }
        });

        frame.add(titleLabel, BorderLayout.NORTH);
        frame.add(inputPanel, BorderLayout.CENTER);
        frame.add(resultArea, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}
