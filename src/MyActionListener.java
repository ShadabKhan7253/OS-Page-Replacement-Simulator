
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Riddhesh Shah
 */
public class MyActionListener implements ActionListener{
    PageReplacementUI refObj;
    MyActionListener(PageReplacementUI obj){
        refObj = obj;
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == refObj.getBtnSubmit()){
            
            
            int numFrames = (int)refObj.getPageSpinner().getValue();
            
            String pageString[] = refObj.getPageArea().getText().split("\\n");
            int pages[] = new int[pageString.length];
            
            for(int i=0;i<pageString.length;i++){
                pages[i] = Integer.parseInt(pageString[i]);
            }
            
            //Code for valiadtion - is remaining
            
            int numPages = pages.length;
            int gridCols = 4;
            int gridRows = (int)Math.ceil(numPages/gridCols);
            
            //System.out.println("Grid Cloumns : " + gridCols);
            //System.out.println("Grid Rows : " + gridRows);
            
            //refObj.setDisplayPanel(new JPanel());
            refObj.getDisplayPanel().removeAll();
            JPanel panel = refObj.getDisplayPanel();
            //JPanel panel = new JPanel();
            //panel.setPreferredSize(new Dimension(gridRows*50,gridCols*50*numFrames));
            panel.setLayout(new GridLayout(gridRows,gridCols));
            
            //FIRST IN FIRST OUT
            if(refObj.getAlgorithmComboBox().getSelectedIndex() == 0){
                /*First In First Out*/
                int changeIndex = 0;
                int colorIndex = 0;
                int frames[] = new int[numFrames];
                int present = 0;
                int pageHits = 0;
                int setIndex = 0;
                
                //Setting Frames to -1
                for(int j=0;j<frames.length;j++){
                    frames[j] = -1;
                }
                
//                for(int i=0;i<numFrames;i++){
//                    frames[i] = pages[i];
//                }
                
                for(int i=0;i<numPages;i++){
                    
                    JPanel refPanel = new JPanel();
                    refPanel.setLayout(new FlowLayout());
                    //refPanel.setPreferredSize(new Dimension(gridRows*50,gridCols*50*numFrames));
                    
                    //System.out.println("Num Pages : " + numPages);
                    //System.out.println("Num Frames : " + numFrames);
                    //System.out.println("i : " + i);
                    int element = pages[i];
                    for(int j=0;j<numFrames;j++){
                        if(frames[j] == element){
                            present = 1;
                            colorIndex = j;
                            break;
                        }
                    }
                    if(present != 1){
                        colorIndex = changeIndex;
                        frames[changeIndex] = element;
                        refPanel.add(FrameSetCreator.getFrameSet(frames, changeIndex, colorIndex, Color.RED));
                        changeIndex++;
                        changeIndex = changeIndex % numFrames;
                    }else{
                        refPanel.add(FrameSetCreator.getFrameSet(frames, -1, colorIndex, Color.GREEN));
                        pageHits++;
                    }
                    
                    //System.out.println("Front : " + (changeIndex-1));
                 
                    //refPanel.setBackground(Color.red);
                    //refPanel.setPreferredSize(new Dimension(500, 500));
                    panel.add(refPanel);
                    //panel.setBackground(Color.BLUE);
                    panel.setVisible(true);
                    panel.validate();
                    
                    present = 0;
                    
                }
            }
            
            //LEAST RECENTLY USED
            if(refObj.getAlgorithmComboBox().getSelectedIndex() == 1){
                /*First In First Out*/
                int changeIndex = 0;
                int colorIndex = 0;
                int frames[] = new int[numFrames];
                //System.out.println("NUMBER =============> " + frames[1]);
                int present = 0;
                int pageHits = 0;
                int previousIndex = -1;
                int setIndex = 0;
                
                //Setting Frames to -1
                for(int j=0;j<frames.length;j++){
                    frames[j] = -1;
                }
                
//                for(int i=0;i<numFrames;i++){
//                    frames[i] = pages[i];
//                }
                
                for(int i=0;i<numPages;i++){
                    
                    JPanel refPanel = new JPanel();
                    refPanel.setLayout(new FlowLayout());
                    //refPanel.setPreferredSize(new Dimension(gridRows*50,gridCols*50*numFrames));
                    
                    //System.out.println("Num Pages : " + numPages);
                    //System.out.println("Num Frames : " + numFrames);
                    //System.out.println("i : " + i);
                    int element = pages[i];
                    for(int j=0;j<numFrames;j++){
                        if(frames[j] == element){
                            present = 1;
                            colorIndex = j;
                            setIndex++;
                            break;
                        }
                    }
                    
                    if(present != 1){
                        //System.out.println("Inside if(present != 1)");
                        int reversedNumbers[] = new int[i];
                        int indexes[] = new int[numFrames];
                        if(numFrames > reversedNumbers.length - setIndex){
                            //System.out.println("Inside if(reversedNumbers.length <= i)");
                            changeIndex = reversedNumbers.length - setIndex;
                        }else{
                            //System.out.println("Inside if(reversedNumbers.length <= i) its ELSE");
                            for(int j=0,k=reversedNumbers.length-1;j<reversedNumbers.length;j++,k--){
                                reversedNumbers[j] = pages[k];
                            }
                            int arrayIndex = 0;
                            for(int k=0;k<numFrames;k++){
                                for(int j=0;j<reversedNumbers.length;j++){
                                    if(reversedNumbers[j] == frames[k]){
                                        indexes[arrayIndex++] = j;
                                        break;
                                    }
                                }
                            }
                            int biggest = indexes[0];
                            for(int j=1;j<indexes.length;j++){
                                if(biggest < indexes[j]){
                                    biggest = indexes[j];
                                    System.out.println("biggest : " + biggest);
                                }
                            }
                            for(int j=0;j<numFrames;j++){
                                if(frames[j] == reversedNumbers[biggest]){
                                    changeIndex = j;
                                    //System.out.println("Number : " + reversedNumbers[biggest]);
                                    //System.out.println("Change Index : " + changeIndex);
                                    break;
                                }
                            }
                        }
                        changeIndex = changeIndex % numFrames;
                        colorIndex = changeIndex;

                        frames[changeIndex] = element;
                        refPanel.add(FrameSetCreator.getFrameSet(frames, changeIndex, colorIndex, Color.RED));
                        
                    }else{
                        System.out.println("Inside if(present != 1) its ELSE");
                        refPanel.add(FrameSetCreator.getFrameSet(frames, -1, colorIndex, Color.GREEN));
                        pageHits++;
                    }
                    
//                    if(present != 1){   
//                        if(i == 0 || i == 1){
//                            changeIndex = i;
//                        }else{
//                            for(int j=0;j<numFrames;j++){
//                                if(frames[j] != pages[i-1] && frames[j] != pages[i-2]){
//                                    changeIndex = j;
//                                }
//                            }
//                        }
//                        
//                        changeIndex = changeIndex % numFrames;
//
//                        frames[changeIndex] = element;
//                        refPanel.add(FrameSetCreator.getFrameSet(frames, changeIndex, colorIndex, Color.RED));
//                    }else{
//                        refPanel.add(FrameSetCreator.getFrameSet(frames, changeIndex, colorIndex, Color.GREEN));
//                        pageHits++;
//                    }
                    //changeIndex = changeIndex % numFrames;
                    //System.out.println("Front : " + (changeIndex-1));
                 
                    //refPanel.setBackground(Color.red);
                    //refPanel.setPreferredSize(new Dimension(500, 500));
                    panel.add(refPanel);
                    //panel.setBackground(Color.BLUE);
                    panel.setVisible(true);
                    panel.validate();
                    
                    present = 0;
                    
                }
            }
            
            //OPTIMAL
            if(refObj.getAlgorithmComboBox().getSelectedIndex() == 2){
                /*First In First Out*/
                int changeIndex = 0;
                int colorIndex = 0;
                int frames[] = new int[numFrames];
                //System.out.println("NUMBER =============> " + frames[1]);
                int present = 0;
                int pageHits = 0;
                int previousIndex = -1;
                int setIndex = 0;
                
                //Setting Frames to -1
                for(int j=0;j<frames.length;j++){
                    frames[j] = -1;
                }
                
//                for(int i=0;i<numFrames;i++){
//                    frames[i] = pages[i];
//                }
                
                for(int i=0;i<numPages;i++){
                    
                    JPanel refPanel = new JPanel();
                    refPanel.setLayout(new FlowLayout());
                    //refPanel.setPreferredSize(new Dimension(gridRows*50,gridCols*50*numFrames));
                    
                    //System.out.println("Num Pages : " + numPages);
                    //System.out.println("Num Frames : " + numFrames);
                    //System.out.println("i : " + i);
                    int element = pages[i];
                    for(int j=0;j<numFrames;j++){
                        if(frames[j] == element){
                            present = 1;
                            colorIndex = j;
                            setIndex++;
                            break;
                        }
                    }
                    
                    if(present != 1){
                        //System.out.println("Inside if(present != 1)");
                        //setIndex++;
                        //int pagesLength = pages.length - i;
                        int indexes[] = new int[numFrames];
                        int numbers[] = new int[pages.length - i];
                        if(numFrames > i - setIndex){
                            //System.out.println("Inside if(reversedNumbers.length <= i)");
                            changeIndex = i - setIndex;
                        }else{
                            boolean found = false;
                            //System.out.println("Inside if(reversedNumbers.length <= i) its ELSE");
                            for(int j=i+1,k=0;j<pages.length;j++,k++){
                                numbers[k] = pages[j];
                                System.out.println("pages[j] : " + pages[j]);
                            }
                            int arrayIndex = 0;
                            int l = 0;
                            for(int k=0;k<numFrames;k++){
                                for(l=0;l<numbers.length;l++){
                                    if(numbers[l] == frames[k]){
                                        indexes[arrayIndex++] = l;
                                        break;
                                    }
                                    System.out.println("Frames[k] : " + frames[k]);
                                    System.out.println("index[k] : " + indexes[k]);
                                }
                                if(l == numbers.length){
                                    changeIndex = k;
                                    found = true;
                                    
                                }
                            }
                            if(!found){
                                int biggest = indexes[0];
                                for(int j=1;j<indexes.length;j++){
                                    if(indexes[j] == -1){
                                        biggest = j;
                                        break;
                                    }
                                    if(biggest < indexes[j]){
                                        biggest = indexes[j];
                                        System.out.println("biggest : " + biggest);
                                    }
                                }
                                for(int j=0;j<numFrames;j++){
                                    if(frames[j] == numbers[biggest]){
                                        changeIndex = j;
                                        System.out.println("Number : " + numbers[biggest]);
                                        System.out.println("Change Index : " + changeIndex);
                                        break;
                                    }
                                }
                            }
                        }
                        //setIndex++;
                        changeIndex = changeIndex % numFrames;
                        colorIndex = changeIndex;

                        frames[changeIndex] = element;
                        refPanel.add(FrameSetCreator.getFrameSet(frames, changeIndex, colorIndex, Color.RED));
                        
                    }else{
                        System.out.println("Inside if(present != 1) its ELSE");
                        refPanel.add(FrameSetCreator.getFrameSet(frames, -1, colorIndex, Color.GREEN));
                        refPanel.validate();
                        pageHits++;
                    }
                    
//                    if(present != 1){   
//                        if(i == 0 || i == 1){
//                            changeIndex = i;
//                        }else{
//                            for(int j=0;j<numFrames;j++){
//                                if(frames[j] != pages[i-1] && frames[j] != pages[i-2]){
//                                    changeIndex = j;
//                                }
//                            }
//                        }
//                        
//                        changeIndex = changeIndex % numFrames;
//
//                        frames[changeIndex] = element;
//                        refPanel.add(FrameSetCreator.getFrameSet(frames, changeIndex, colorIndex, Color.RED));
//                    }else{
//                        refPanel.add(FrameSetCreator.getFrameSet(frames, changeIndex, colorIndex, Color.GREEN));
//                        pageHits++;
//                    }
                    //changeIndex = changeIndex % numFrames;
                    //System.out.println("Front : " + (changeIndex-1));
                 
                    //refPanel.setBackground(Color.red);
                    //refPanel.setPreferredSize(new Dimension(500, 500));
                    refPanel.validate();
                    panel.add(refPanel);
                    //panel.setBackground(Color.BLUE);
                    panel.setVisible(true);
                    panel.validate();
                    
                    present = 0;
                    
                }
            }
            
            panel.validate();
            JPanel tempPanel = new JPanel();
            tempPanel.add(new JLabel("Hello World"));
            refObj.getScrollPane().add(tempPanel);
            System.out.println("Performed");
            //refObj.getDisplayScrollPane().add(panel);
            //refObj.getDisplayScrollPane().validate();
            //refObj.getContentPane().add(refObj.getDisplayScrollPane());
            //refObj.getDisplayPanel().add(refObj.getContentPane());
            //System.out.println("Action Listener Completed");
            //System.out.println(panel);
            //refObj.getDisplayScrollPane().add((new JPanel()).add((new JLabel("OK"))));
            //refObj.removeAll();
            //System.out.println("Removed!");
            //refObj.add(panel);
        }
    }   
}
