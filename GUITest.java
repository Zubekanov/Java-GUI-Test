import java.awt.*;
import java.awt.event.*;

public class GUITest extends Frame {

    private String frameInitialTitle;
    private int frameWidth;
    private int frameHeight;

    public GUITest(String title, int width, int height) {
        setLayout(new FlowLayout());
        addWindowListener(new FrameListener());
        this.frameInitialTitle = title;
        setTitle(this.frameInitialTitle);
        this.frameWidth = width;
        this.frameHeight = height;
        setSize(this.frameWidth, this.frameHeight);
        setVisible(true);
    }
    
    public static void main(String[] args) {
        GUITest GUIFrame = new GUITest("Test Frame", 500, 200);
        GUIFrame.addListenerButton("Click Me!");
    }

    public void addListenerButton(String text) {
        Button button = new Button(text);
        button.addActionListener(new TestListener());
        add(button);
    }

    private class TestListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            System.out.println("Button was pressed");
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

        @Override 
        public void windowIconified(WindowEvent event) {
            System.out.println("Window Iconified"); 
        }

        @Override 
        public void windowDeiconified(WindowEvent event) {
            System.out.println("Window Deiconified"); 
        }

        @Override 
        public void windowActivated(WindowEvent event) {
            System.out.println("Window Activated"); 
        }

        @Override public void windowDeactivated(WindowEvent event) {
            System.out.println("Window Deactivated"); 
        }
    }

   private class DragListener implements MouseListener, MouseMotionListener {

        private int initialX;
        private int initialY;
        private boolean pressed = false;

        @Override
        public void mouseClicked(MouseEvent event) {

        }

        @Override 
        public void mousePressed(MouseEvent event) {
            this.initialX = event.getX();
            this.initialY = event.getY();
            this.pressed = true;
        }

        @Override 
        public void mouseReleased(MouseEvent event) {

        }

        @Override 
        public void mouseEntered(MouseEvent event) {

        }

        @Override 
        public void mouseExited(MouseEvent event) {

        }

        @Override
        public void mouseDragged(MouseEvent event) {
            
        }

        @Override
        public void mouseMoved(MouseEvent event) {
            
        }
 }
}
