import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;

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

        Panel panelWest = new Panel();
        panelWest.setLayout(new BoxLayout(panelWest, BoxLayout.Y_AXIS));
        inventoryPanels[0] = panelWest;
        add(inventoryPanels[0], BorderLayout.WEST);

        Panel panelCenter = new Panel();
        panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.Y_AXIS));
        inventoryPanels[1] = panelCenter;
        add(inventoryPanels[1], BorderLayout.CENTER);
        
        Panel panelEast = new Panel();
        panelEast.setLayout(new BoxLayout(panelEast, BoxLayout.Y_AXIS));
        inventoryPanels[2] = panelEast;
        add(inventoryPanels[2], BorderLayout.EAST);

        // Align text to left and allow text box to stretch.
        Panel inputPanel = new Panel(new BorderLayout());
        inputPanel.add(new Label("Add New Item: "), BorderLayout.WEST);
        itemInput = new TextField(25);
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
            // Check if the item already exists
            if (!inventory.stream().anyMatch(item -> item.getName().equals(itemName))) {
                inventory.add(new InventoryItem(itemName, 0));
                //TODO REMOVE
                inventoryPanels[0].add(new Label("0"));
                inventoryPanels[1].add(new Label(itemName));
                System.out.println(itemName);
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
        private int quantity;

        public InventoryItem(String name, int quantity) {
            this.name = name;
            this.quantity = quantity;
        }

        public String getName() {
            return name;
        }

        public int getQuantity() {
            return quantity;
        }

        private class DecrementListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (quantity > 0) {quantity--;}
            }
        }
    
        private class IncrementListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent event) {
                quantity++;
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
