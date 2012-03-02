package ru.smyt.jitgxt.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.smyt.jitgxt.client.SourceService;

/**
 *
 * @author Hp
 */
public class SourceServiceImpl extends RemoteServiceServlet implements SourceService {

    public String getSource(String s) {
        try {
            s = "/" + s;
            StringBuilder sb = new StringBuilder();
            FileInputStream is = new FileInputStream(new File(s));
            Scanner scanner = new Scanner(is);
            while(scanner.hasNextLine())
                sb.append(scanner.nextLine()).append("<br/>");
            return sb.toString();
        } catch(FileNotFoundException ex) {
            Writer out;
            try {
                out = new OutputStreamWriter(new FileOutputStream(s));
                out.write("test");
                out.close();
            } catch (Exception ex1) {
                Logger.getLogger(SourceServiceImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            return "<h1>Source file not found</h1>";
        }
    }
}
