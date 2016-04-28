package src;

//Calculator function

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculator1 extends JFrame {
    public static final int WIDTH = 500;
    public static final int HEIGHT = 400;
    private JTextField result;
    private JPanel mainPane;
    private JPanel numPane;
    private JPanel operatePane;
    private JPanel showPane;
    private JButton[] numB;
    private JButton[] operateB;

    public Calculator1(String title) {
        this.setTitle(title);
        this.result = new JTextField("0.0", 22);
        this.result.setEditable(false);
        this.mainPane = new JPanel();
        this.numPane = new JPanel();
        this.operatePane = new JPanel();
        this.showPane = new JPanel();
        this.numB = new JButton[12];

        int too;
        for(too = 0; too < 9; ++too) {
            this.numB[too] = new JButton((new Integer(too + 1)).toString());
        }

        this.numB[9] = new JButton("0");
        this.numB[10] = new JButton("cls");
        this.numB[11] = new JButton(".");
        this.numPane.setLayout(new GridLayout(4, 3, 1, 1));

        for(too = 0; too < 12; ++too) {
            this.numPane.add(this.numB[too]);
        }

        this.operateB = new JButton[8];
        this.operateB[0] = new JButton("+");
        this.operateB[1] = new JButton("-");
        this.operateB[2] = new JButton("*");
        this.operateB[3] = new JButton("/");
        this.operateB[4] = new JButton("pow");
        this.operateB[5] = new JButton("sqrt");
        this.operateB[6] = new JButton("+/-");
        this.operateB[7] = new JButton("=");
        this.operatePane.setLayout(new GridLayout(4, 2, 1, 1));

        for(too = 0; too < 8; ++too) {
            this.operatePane.add(this.operateB[too]);
        }

        this.showPane.setLayout(new BorderLayout());
        this.showPane.add(this.result, "North");
        this.mainPane.setLayout(new BorderLayout());
        this.mainPane.add(this.showPane, "North");
        this.mainPane.add(this.numPane, "West");
        this.mainPane.add(this.operatePane, "East");
        this.add(this.mainPane, "Center");
        this.setSize(500, 400);
        Toolkit var6 = Toolkit.getDefaultToolkit();
        Dimension screenSize = var6.getScreenSize();
        this.setLocation((screenSize.width - 500) / 2, (screenSize.height - 400) / 2);
        this.setDefaultCloseOperation(3);
        this.setResizable(false);
        this.pack();
        Calculator1.ButtonListener button = new Calculator1.ButtonListener();

        int i;
        for(i = 0; i < this.numB.length; ++i) {
            this.numB[i].addActionListener(button);
        }

        for(i = 0; i < this.operateB.length; ++i) {
            this.operateB[i].addActionListener(button);
        }

    }

    public static void main(String[] args) {
        Calculator1 c = new Calculator1("Calculator");
        c.setVisible(true);
    }

    class ButtonListener implements ActionListener {
        private String last;
        private String strVal = "";
        private double total;
        private double number;
        private boolean firsttime = true;
        private boolean operatorPressed;

        public ButtonListener() {
        }

        public void actionPerformed(ActionEvent e) {
            String str = ((JButton)e.getSource()).getText().trim();
            if(Character.isDigit(str.charAt(0))) {
                this.handleNumber(str);
            } else {
                this.calculate(str);
            }

        }

        public void calculate(String op) {
            this.operatorPressed = true;
            if(this.firsttime && !this.isUnary(op)) {
                this.total = this.getNumberOnDisplay();
                this.firsttime = false;
            }

            if(this.isUnary(op)) {
                this.handleUnaryOp(op);
            } else if(this.last != null) {
                this.handleBinaryOp(this.last);
            }

            if(!this.isUnary(op)) {
                this.last = op;
            }

        }

        public boolean isUnary(String s) {
            return s.equals("=") || s.equals("cls") || s.equals("sqrt") || s.equals("+/-") || s.equals(".");
        }

        public void handleUnaryOp(String op) {
            if(op.equals("+/-")) {
                this.number = this.negate(this.getNumberOnDisplay() + "");
                Calculator1.this.result.setText("");
                Calculator1.this.result.setText(this.number + "");
            } else if(op.equals(".")) {
                this.handleDecPoint();
            } else if(op.equals("sqrt")) {
                this.number = Math.sqrt(this.getNumberOnDisplay());
                Calculator1.this.result.setText("");
                Calculator1.this.result.setText(this.number + "");
            } else if(op.equals("=")) {
                if(this.last != null && !this.isUnary(this.last)) {
                    this.handleBinaryOp(this.last);
                }

                this.last = null;
                this.firsttime = true;
            } else {
                this.clear();
            }
        }

        public void handleBinaryOp(String op) {
            if(op.equals("+")) {
                this.total += this.number;
            } else if(op.equals("-")) {
                this.total -= this.number;
            } else if(op.equals("*")) {
                this.total *= this.number;
            } else if(op.equals("/")) {
                try {
                    this.total /= this.number;
                } catch (ArithmeticException var3) {
                    ;
                }
            } else if(op.equals("pow")) {
                this.total = Math.pow(this.total, this.number);
            }

            this.last = null;
            this.number = 0.0D;
            Calculator1.this.result.setText(this.total + "");
        }

        public void handleNumber(String s) {
            if(!this.operatorPressed) {
                this.strVal = this.strVal + s;
            } else {
                this.operatorPressed = false;
                this.strVal = s;
            }

            this.number = (new Double(this.strVal)).doubleValue();
            Calculator1.this.result.setText("");
            Calculator1.this.result.setText(this.strVal);
        }

        public void handleDecPoint() {
            this.operatorPressed = false;
            if(this.strVal.indexOf(".") < 0) {
                this.strVal = this.strVal + ".";
            }

            Calculator1.this.result.setText("");
            Calculator1.this.result.setText(this.strVal);
        }

        public double negate(String s) {
            this.operatorPressed = false;
            if(this.number == (double)((int)this.number)) {
                s = s.substring(0, s.indexOf("."));
            }

            if(s.indexOf("-") < 0) {
                this.strVal = "-" + s;
            } else {
                this.strVal = s.substring(1);
            }

            return (new Double(this.strVal)).doubleValue();
        }

        public double getNumberOnDisplay() {
            return (new Double(Calculator1.this.result.getText())).doubleValue();
        }

        public void clear() {
            this.firsttime = true;
            this.last = null;
            this.strVal = "";
            this.total = 0.0D;
            this.number = 0.0D;
            Calculator1.this.result.setText("0");
        }
    }
}
