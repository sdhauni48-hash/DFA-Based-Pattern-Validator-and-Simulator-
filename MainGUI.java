import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import dfa.starting.Starting;
import dfa.ending.Ending;
import dfa.containing.Contain;
import dfa.model.Result;

class SwingDFA extends JFrame implements ActionListener {

    JTextField tf1 = new JTextField(10);
    JTextField tf2 = new JTextField(10);
    JTextField tf3 = new JTextField(10);

    JTextArea ta = new JTextArea(10,30);
    JScrollPane sp = new JScrollPane(ta);

    JLabel resultLabel = new JLabel("", JLabel.CENTER);

    JComboBox<String> cb = new JComboBox<>(new String[]{"Starting","Ending","Containing"});

    JButton b1 = new JButton("Table");
    JButton b2 = new JButton("Diagram");
    JButton b3 = new JButton("Clear");

    DFAGUI panel = new DFAGUI();

    CardLayout card = new CardLayout();
    JPanel centerPanel = new JPanel(card);

    Result r;

    SwingDFA() {

        setTitle("DFA Simulator");
        setLayout(new BorderLayout());

        JLabel header = new JLabel("DFA Simulator", JLabel.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 28));
        header.setForeground(new Color(0,102,204));
        add(header, BorderLayout.NORTH);

        JPanel input = new JPanel();
        input.add(new JLabel("Alphabet")); input.add(tf1);
        input.add(new JLabel("Pattern")); input.add(tf2);
        input.add(new JLabel("String")); tf3.setColumns(10); input.add(tf3);
        input.add(cb);
        input.add(b1); input.add(b2); input.add(b3);

        add(input, BorderLayout.SOUTH);

        ta.setFont(new Font("Monospaced", Font.PLAIN, 14));
        centerPanel.add(sp, "TABLE");
        centerPanel.add(panel, "DIAGRAM");
        add(centerPanel, BorderLayout.CENTER);

        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(resultLabel, BorderLayout.WEST);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);

        setSize(900,600);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {

        String a = tf1.getText();
        String p = tf2.getText();
        String s = tf3.getText();
        String ch = (String)cb.getSelectedItem();

        try {

            if (r == null) {
                if (ch.equals("Starting"))
                    r = new Starting().DFA(a,p,s);
                else if (ch.equals("Ending"))
                    r = new Ending().DFA(a,p,s);
                else
                    r = new Contain().DFA(a,p,s);
                if (r.accepted) {
                    resultLabel.setText("ACCEPTED");
                    resultLabel.setForeground(Color.GREEN.darker());
                } else {
                    resultLabel.setText("REJECTED");
                    resultLabel.setForeground(Color.RED);
                }
            }

            if (e.getSource() == b1) {
                ta.setText(r.table);
                card.show(centerPanel, "TABLE");
            }

            if (e.getSource() == b2) {
                panel.setData(r.t, r.alph, r.states, r.finalState);
                card.show(centerPanel, "DIAGRAM");
            }

            if (e.getSource() == b3) {
                tf1.setText("");
                tf2.setText("");
                tf3.setText("");
                ta.setText("");
                resultLabel.setText("");
                r = null;
                card.show(centerPanel, "TABLE");
            }

        } catch (Exception ex) {
            ta.setText(ex.getMessage());
        }
    }
}

public class MainGUI {
    public static void main(String[] args) {
        new SwingDFA();
    }
}
