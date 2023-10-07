
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Riddhesh Shah
 */
public class FrameSetCreator {
    public static JPanel getFrameSet(int[] array, int changeIndex, int colorIndex, Color color){
        
        //Manipulating changeIndex
        changeIndex = array.length + changeIndex;
        changeIndex = changeIndex % array.length;
        
        JPanel mainPanel = new JPanel();
        //mainPanel.setPreferredSize(new Dimension(200,50*array.length));
        mainPanel.setBackground(Color.green);
        mainPanel.setLayout(new GridLayout(array.length+1,1));
        //mainPanel.setFont(new Font("Poppins", Font.ITALIC, 30));
        //mainPanel.setSize(500,500);
        //mainPanel.setLayout(new FlowLayout());
        for(int i=0;i<array.length+1;i++){
                JPanel panel = new JPanel();
                panel.setFont(new Font("Arial",Font.ITALIC,20));
                panel.setPreferredSize(new Dimension(100,40));
                if(i == array.length){
                    JLabel label = new JLabel();
                    label.setFont(new Font("Poppins", Font.PLAIN, 20));
                    
                    if(color == Color.GREEN){
                        label.setText("Page Hit");
                    }else{
                        label.setText("Page Fault");
                    }
                    panel.add(label);
                    panel.setAlignmentX(Component.CENTER_ALIGNMENT);
                    panel.setAlignmentY(Component.CENTER_ALIGNMENT);
                    //panel.add(new JLabel("" + array[i]));
                    mainPanel.add(panel);
                    //mainPanel.setSize(500,array.length*75);
                    mainPanel.validate();
                    break;
                }
                Border border = BorderFactory.createLineBorder(Color.black);
                panel.setBorder(border);
                //panel.setLayout(new FlowLayout());
                //System.out.println("Index In FrameSetCreator : " + changeIndex);
                if(i == colorIndex){
                    panel.setBackground(color);
                }else{
                    panel.setBackground(Color.WHITE);
                }
                //panel.setLayout(new FlowLayout());
                //panel.setAlignmentX(Component.CENTER_ALIGNMENT);
                //panel.setAlignmentY(Component.CENTER_ALIGNMENT);
                JLabel label = new JLabel("" + array[i]);
                label.setFont(new Font("Poppins", Font.PLAIN, 20));
                panel.add(label);
                panel.validate();
                mainPanel.add(panel);
                //mainPanel.setSize(500,array.length*75);
                mainPanel.validate();
                //System.out.println("Frame Set Creator Completed");
        }
        return mainPanel;
    }
}
