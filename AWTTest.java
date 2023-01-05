import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class AWTTest extends Frame {

    private String frameInitialTitle = "AWTTest Frame";
    private final int FRAME_WIDTH = 400;
    private final int FRAME_HEIGHT = 200;
    private final int ITEM_HEIGHT = 20;
    private final int SCROLL_SPEED = 16;

    private TextField itemInput;
    private ArrayList<InventoryItem> inventory = new ArrayList<InventoryItem>();
    private GridBagConstraints itemConstraints;
    private JPanel inventoryPanel;

    public AWTTest() {
        setLayout(new BorderLayout());
        addWindowListener(new FrameListener());
        setTitle(this.frameInitialTitle);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);

        // JPanel to contain inventory items
        inventoryPanel = new JPanel(new GridBagLayout());
        // TODO: Find a fix for scrollpane elements disappearing
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.getViewport().add(inventoryPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(SCROLL_SPEED);
        add(scrollPane, BorderLayout.CENTER);

        // Set GridBagConstraints for inventoryPanel
        itemConstraints = new GridBagConstraints();
        itemConstraints.fill = GridBagConstraints.HORIZONTAL;
        itemConstraints.weightx = 1.0;
        itemConstraints.gridx = 0;

        // Input box for new items 
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(new Label("New item: "), BorderLayout.WEST);
        itemInput = new TextField();
        itemInput.addActionListener(new ItemInputListener());
        inputPanel.add(itemInput, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
    
    public static void main(String[] args) {
        AWTTest TestFrame = new AWTTest();
    }

    private boolean itemExists(String name) {
        return inventory.stream().anyMatch(item -> item.getName().equals(name));
    }

    private class ItemInputListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String itemName = itemInput.getText();
            itemInput.setText("");
            // Create an inventory item if it does not already exist
            if (!itemExists(itemName)) {
                InventoryItem newItem = new InventoryItem(itemName);
                inventory.add(newItem);
                // TODO: Handle different organisation schemes for names or quantity
                inventoryPanel.add(newItem.getPanelRep(), itemConstraints);
                revalidate();
            }
        }
    }

    private class FrameListener implements WindowListener {

        @Override
        public void windowClosing(WindowEvent event) {
            System.out.println("Window Closed");
            System.exit(0);
        }

        @Override public void windowOpened(WindowEvent event) { }
        @Override public void windowClosed(WindowEvent event) { }
        @Override public void windowIconified(WindowEvent event) { }
        @Override public void windowDeiconified(WindowEvent event) { }
        @Override public void windowActivated(WindowEvent event) { }
        @Override public void windowDeactivated(WindowEvent event) { }
    }

    private class InventoryItem {

        private String name;
        private int quantity = 0;

        private JPanel JPanelRep = null;
        private TextField quantityField;
        private TextField nameField;

        public InventoryItem(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public JPanel getPanelRep() {
            // Generate a JPanel representation if it does not exist
            if (JPanelRep == null) {
                JPanelRep = new JPanel(new BorderLayout());
                JPanelRep.setPreferredSize(new Dimension(0, ITEM_HEIGHT));

                // Editable field for quantity
                quantityField = new TextField(3);
                quantityField.addActionListener(new QuantityFieldListener());
                quantityField.setText(quantity + "");
                // Editable field for name
                nameField = new TextField();
                nameField.addActionListener(new NameFieldListener());
                nameField.setText(name);
                // Various buttons
                JPanel buttonPanel = new JPanel(new FlowLayout());
                Button incButton = new Button("+");
                incButton.addActionListener(new IncrementListener());
                Button decButton = new Button("-");
                decButton.addActionListener(new DecrementListener());
                Button remButton = new Button("x");
                remButton.addActionListener(new RemoveListener());
                // Add buttons to buttonPanel
                buttonPanel.add(incButton);
                buttonPanel.add(decButton);
                buttonPanel.add(remButton);

                // Add generated elements to the JPanel
                JPanelRep.add(quantityField, BorderLayout.WEST);
                JPanelRep.add(nameField, BorderLayout.CENTER);
                JPanelRep.add(buttonPanel, BorderLayout.EAST);
            }

            return JPanelRep;
        }

        private class QuantityFieldListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    int value = Integer.parseInt(quantityField.getText());
                    if (value >= 0) {
                        quantity = value;
                    }
                    quantityField.setText(quantity + "");
                } catch (NumberFormatException nfe) {
                    quantityField.setText(quantity + "");
                }
            }
        }

        private class NameFieldListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent event) {
                String newName = nameField.getText();
                if (!itemExists(newName)) {
                    name = newName;
                } 
                nameField.setText(name);
            }
        }

        private class IncrementListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent event) {
                quantity++;
                quantityField.setText(quantity + "");
            }
        }

        private class DecrementListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (quantity > 0) {quantity--;}
                quantityField.setText(quantity + "");
            }
        }

        private class RemoveListener implements ActionListener {
            @Override 
            public void actionPerformed(ActionEvent event) {
                inventoryPanel.remove(JPanelRep);
                revalidate();
            }
        }
    }
}
