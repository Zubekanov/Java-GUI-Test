import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ScrollPaneLayout;

public class AWTTest extends Frame {

    private String frameInitialTitle = "AWTTest Frame";
    private int frameWidth = 400;
    private int frameHeight = 200;

    private TextField itemInput;
    private ArrayList<InventoryItem> inventory = new ArrayList<InventoryItem>();
    private Panel[] inventoryPanels = {null, null, null};

    public AWTTest() {
        setLayout(new BorderLayout());
        addWindowListener(new FrameListener());
        setTitle(this.frameInitialTitle);
        setSize(this.frameWidth, this.frameHeight);

        // Panel to contain inventory items
        Panel inventoryPanel = new Panel();
        add(inventoryPanel, BorderLayout.CENTER);

        // Input box for new items 
        Panel inputPanel = new Panel(new BorderLayout());
        inputPanel.add(new Label("Add New Item: "), BorderLayout.WEST);
        itemInput = new TextField();
        itemInput.addActionListener(new ItemInputListener());
        inputPanel.add(itemInput, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
    
    public static void main(String[] args) {
        AWTTest TestFrame = new AWTTest();
    }

    private class ItemInputListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String itemName = itemInput.getText();
            itemInput.setText("");
            // Check if the item name exists
            if (!inventory.stream().anyMatch(item -> item.getName().equals(itemName))) {
                InventoryItem newItem = new InventoryItem(itemName);
                inventory.add(newItem);
                // TODO: Handle different organisation schemes for names or quantity

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

        private Panel panelRep = null;
        private TextField quantityField;
        private TextField nameField;

        public InventoryItem(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public Panel getPanelRep() {
            // Generate a panel representation if it does not exist
            if (panelRep == null) {
                panelRep = new Panel(new BoxLayout(panelRep, BoxLayout.X_AXIS));

                // Editable field for quantity
                quantityField = new TextField();
                quantityField.addActionListener(new QuantityFieldListener());
                // Editable field for name
                nameField = new TextField();
                nameField.addActionListener(new NameFieldListener());
                // Various buttons
                Button incButton = new Button("+");
                incButton.addActionListener(new IncrementListener());
                Button decButton = new Button("-");
                decButton.addActionListener(new DecrementListener());
                Button remButton = new Button("x");
                remButton.addActionListener(new RemoveListener());

                // Add generated elements to the panel
                panelRep.add(quantityField);
                panelRep.add(nameField);
                panelRep.add(Box.createVerticalGlue());
                panelRep.add(incButton);
                panelRep.add(decButton);
                panelRep.add(remButton);
            }

            return panelRep;
        }

        private class QuantityFieldListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent event) {
                // TODO Auto-generated method stub
                
            }
        }

        private class NameFieldListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent event) {
                // TODO Auto-generated method stub
                
            }
        }

        private class IncrementListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent event) {
                quantity++;
            }
        }

        private class DecrementListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (quantity > 0) {quantity--;}
            }
        }

        private class RemoveListener implements ActionListener {
            @Override 
            public void actionPerformed(ActionEvent event) {
                //  TODO
            }
        }
    }
}
