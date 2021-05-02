/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prerequisites;

import java.awt.Component;
import javax.swing.JFileChooser;

/**
 *
 * @author godofwarop
 */
public class Path {
    
    public String path(Component eve){
         JFileChooser chooser;
        
        chooser = new JFileChooser(); 
    chooser.setCurrentDirectory(new java.io.File("."));
    chooser.setDialogTitle("Choose Path");
    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    chooser.setAcceptAllFileFilterUsed(false);
    
    if (chooser.showOpenDialog(eve) == JFileChooser.APPROVE_OPTION) { 
      String path = chooser.getSelectedFile().toString();
      return path;
    }else{
        return "No Selection";
    }
   }
}
