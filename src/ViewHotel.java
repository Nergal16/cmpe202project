//add view hotel function with GUI to display pictures of the hotel

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class ViewHotel1 extends JPanel {
    private JButton button2;
    private JButton button1;

    public ViewHotel1() {
        this.initComponents();
    }

    private void initComponents() {
        Spacer vSpacer1 = new Spacer();
        this.button2 = new JButton();
        this.button1 = new JButton();
        this.setBorder(new CompoundBorder(new TitledBorder(new EmptyBorder(0, 0, 0, 0), "JFormDesigner Evaluation", 2, 5, new Font("Dialog", 1, 12), Color.red), this.getBorder()));
        this.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent e) {
                if("border".equals(e.getPropertyName())) {
                    throw new RuntimeException();
                }
            }
        });
        this.setLayout(new GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
        this.add(vSpacer1, new GridConstraints(1, 0, 1, 1, 0, 2, 1, 6, (Dimension)null, (Dimension)null, (Dimension)null));
        this.button2.setText("Button");
        this.add(this.button2, new GridConstraints(2, 0, 1, 1, 0, 1, 3, 0, (Dimension)null, (Dimension)null, (Dimension)null));
        this.button1.setText("Button");
        this.add(this.button1, new GridConstraints(3, 0, 1, 1, 0, 1, 3, 0, (Dimension)null, (Dimension)null, (Dimension)null));
    }
}
