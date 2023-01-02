import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class AWTTest extends Frame {

    private String frameInitialTitle = "AWTTest Frame";
    private int frameWidth = 400;
    private int frameHeight = 200;

    private TextField itemInput;
    private ArrayList<InventoryItem> Inventory = new ArrayList<InventoryItem>();

    public AWTTest() {
        setLayout(new FlowLayout());
        addWindowListener(new FrameListener());
        setTitle(this.frameInitialTitle);
        setSize(this.frameWidth, this.frameHeight);

        add(new Label("Add New Item: "));
        itemInput = new TextField(25);
        add(itemInput);

        setVisible(true);
    }
    
    public static void main(String[] args) {
        AWTTest TestFrame = new AWTTest();
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
