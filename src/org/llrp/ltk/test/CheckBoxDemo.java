package org.llrp.ltk.test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
/**
 * @author Hardneedl
 */
final class CheckBoxDemo extends JFrame {
    public String getTitle() {return "CheckBoxDemo";}
    static private final Dimension size = new Dimension(600,400);
    public Dimension getPreferredSize() {return size;}
    public Dimension getMaximumSize() {return size;}
    public Dimension getMinimumSize() {return size;}
    public Dimension getSize(){return size;}
 
    //把选中的内容放进 List
    final private java.util.List<String> stringList=new ArrayList<String>(5);
 
    private class CheckAction extends AbstractAction{
 
        private CheckAction(String name) {
            super(name);
        }
 
        public void actionPerformed(ActionEvent e) {
            Object obj=e.getSource();
            if (obj instanceof JToggleButton){
                JToggleButton t = (JToggleButton)obj;
 
                if(t.isSelected())
                    stringList.add(t.getText());
                else
                    stringList.remove(t.getText());
            }
 
            //打印效果
            for (String s:stringList) {
                System.out.print(s + "\t");
            }
            System.out.println();
        }
    }
 
    CheckBoxDemo() throws HeadlessException {
        init();
        attachListeners();
        doLay();
    }
 
    private JCheckBox[] buttons=new JCheckBox[4];
    private void init(){
 
        for (int i = 0,j=buttons.length;i < j; i++) {
            buttons[i]=new JCheckBox(new CheckAction("测试"+Integer.toString(i)));
        }
 
    }
 
    private void attachListeners(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
 
    private void doLay(){
        Container container = getContentPane();
        container.setLayout(new FlowLayout());
 
        for (JCheckBox button : buttons) {
            container.add(button);
        }
 
 
        pack();
        setVisible(true);
    }
    public static void main(String...args) {
        System.setProperty("swing.defaultlaf","com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        SwingUtilities.invokeLater(
            new Runnable(){
                public void run() {
                    new CheckBoxDemo();
                }
            }
        );
    }
}