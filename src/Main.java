import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BestFitMemoryAllocator {
    private int[] blockSizes;
    private int[] processSizes;

    public BestFitMemoryAllocator(int[] blockSizes, int[] processSizes) {
        this.blockSizes = blockSizes;
        this.processSizes = processSizes;
    }

    public String allocateMemory() {
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
        result.append("</table><br/><br/>");
        result.append("<h3>Remaining Memory Blocks:</h3>");
        result.append("<table style='margin: auto; text-align: center; width: 60%; border-collapse: collapse;' border='1'>");
        result.append("<tr><th>Block No.</th><th>Remaining Size</th></tr>");
        for (int i = 0; i < blockSizes.length; i++) {
            result.append(String.format("<tr><td>%d</td><td>%d</td></tr>", i + 1, blockSizes[i]));
        }
        result.append("</table></div></html>");
        return result.toString();
    }
}

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Best Fit Memory Allocator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Best Fit Memory Allocation Algorithm", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.DARK_GRAY);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        inputPanel.setBackground(new Color(245, 245, 245));

        JLabel blockLabel = new JLabel("Enter of the Block Sizes :");
        JTextField blockField = new JTextField();

        JLabel processLabel = new JLabel("Enter of the Process Sizes :");
        JTextField processField = new JTextField();

        JButton allocateButton = new JButton("Allocate Memory");
        allocateButton.setBackground(new Color(34, 167, 240));
        allocateButton.setForeground(Color.WHITE);

        inputPanel.add(blockLabel);
        inputPanel.add(blockField);
        inputPanel.add(processLabel);
        inputPanel.add(processField);
        inputPanel.add(new JLabel());
        inputPanel.add(allocateButton);

        JLabel resultArea = new JLabel("", JLabel.CENTER);
        resultArea.setVerticalAlignment(SwingConstants.TOP);
        resultArea.setFont(new Font("Arial", Font.PLAIN, 14));
        resultArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        resultArea.setPreferredSize(new Dimension(700, 300));

        // Add Action Listener for the button
        allocateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String[] blockInput = blockField.getText().split(",");
                    String[] processInput = processField.getText().split(",");

                    if (blockInput.length == 0 || processInput.length == 0) {
                        resultArea.setText("<html><p style='text-align: center; color: red;'>Please provide valid inputs for both block sizes and process sizes.</p></html>");
                        return;
                    }

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
                    resultArea.setText("<html><p style='text-align: center; color: red;'>Invalid input. Please enter valid numbers separated by commas.</p></html>");
                }
            }
        });

        frame.add(titleLabel, BorderLayout.NORTH);
        frame.add(inputPanel, BorderLayout.CENTER);
        frame.add(resultArea, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
