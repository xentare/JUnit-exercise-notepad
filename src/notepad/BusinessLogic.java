package notepad;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author Juha
 */
public class BusinessLogic {

    private Frame frame;
    private File currentFile;

    public BusinessLogic(Frame frame) {
        this.frame = frame;
        New();
    }

    public void SaveAs() {
        final JFileChooser fc = new JFileChooser();
        int returnValue = fc.showSaveDialog(fc);
        File file = fc.getSelectedFile();

        switch (returnValue) {
            case JFileChooser.CANCEL_OPTION:
                System.out.println("Cancelled");
                break;
            case JFileChooser.APPROVE_OPTION:
                Save(file);
                break;
            case JFileChooser.ERROR_OPTION:
                break;
        }
    }
    
    public void Save(File file) {
        try {
            PrintWriter pw = new PrintWriter(file);
            System.out.println(frame.getInputJTextArea().getText());
            pw.println(frame.getInputJTextArea().getText());
            pw.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BusinessLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void New() {
        frame.setTitle("Untitled");
        frame.getInputJTextArea().setText("");
        frame.getToolBar().setText("");
        currentFile = null;
    }

    public void Open() {
        final JFileChooser fc = new JFileChooser();
        int returnValue = fc.showOpenDialog(fc);
        File file = fc.getSelectedFile();
        switch (returnValue) {
            case JFileChooser.CANCEL_OPTION:
                System.out.println("Cancelled");
                break;
            case JFileChooser.APPROVE_OPTION:
                frame.setTitle(file.getName());
                currentFile = file;
                frame.getToolBar().setText(file.getAbsolutePath());
                frame.getInputJTextArea().setText(Read(file));
                break;
            case JFileChooser.ERROR_OPTION:
                break;
        }
    }

    public String Read(File file) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        try {
            Charset charset = Charset.defaultCharset();
            Path path = Paths.get(file.getAbsolutePath());
            br = Files.newBufferedReader(path, charset);
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException ex) {
            Logger.getLogger(BusinessLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sb.toString();
    }

    public File getCurrentFile() {
        return currentFile;
    }  
   
}
