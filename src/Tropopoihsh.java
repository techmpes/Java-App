
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;


public class Tropopoihsh extends JFrame{
    private JPanel row0,row1,row2,row3;
    private JButton ok;
    private JLabel perigrafh,poso,hmerom;
    private JTextField perigrafhTxt,posoTxt,hmeromTxt;
    private String username;
    private String arxeio;
    private String eggrafh;
    private String emf;
    Aes a;
    public Tropopoihsh(Aes a)
    {
        this.a=a;
        JFrame frame=new JFrame("Eisagwgh Tropopoihmenes Eggrafhs");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Kanoume to plaisio orato
        frame.setVisible(true);
        frame.setSize(100,100);
        row0=new JPanel();
        perigrafh = new JLabel("Perigrafh: ");
        perigrafhTxt = new JTextField(50);
        row0.add(perigrafh);
        row0.add(perigrafhTxt);
        row1=new JPanel();
        poso = new JLabel("Poso: ");
        posoTxt = new JTextField(5);
        row1.add(poso);
        row1.add(posoTxt);
        row2=new JPanel();
        hmerom= new JLabel("Hmeromhnia se morfh mm/dd/yyyy: ");
        hmeromTxt = new JTextField(10);
        row2.add(hmerom);
        row2.add(hmeromTxt);
        row3=new JPanel();
        ok=new JButton("OK");
        row3.add(ok);
        Container pane=getContentPane();
        GridLayout layout=new GridLayout(4,1);
        pane.setLayout(layout);
        pane.add(row0);
        pane.add(row1);
        pane.add(row2);
        pane.add(row3);
        frame.setContentPane(pane);
        frame.pack();
        ok.addActionListener(new ActionListener() 
        {   
            public void actionPerformed(ActionEvent evt) 
            {
                if(" ".equals(perigrafhTxt.getText()) || " ".equals(posoTxt.getText()) || " ".equals(hmeromTxt.getText()))
                {
                    JOptionPane.showMessageDialog(null,"Den dothhkan ta stoixeia!");
                }
                else
                {
                        
                    eggrafh=hmeromTxt.getText()+","+posoTxt.getText()+","+perigrafhTxt.getText();
                    ArrayList <String> list=new ArrayList(); 
                    String line;
                        try (BufferedReader rr = new BufferedReader(new FileReader("Users\\"+username+"\\"+arxeio+".txt"))) {
                            while((line=rr.readLine()) != null )
                            {
                                list.add(a.decrypt(line));
                            }
                            rr.close();
                        }
                        catch (IOException ex) {
                    Logger.getLogger(Yphresies.class.getName()).log(Level.SEVERE, null, ex);
                }   catch (Exception ex) {
                        Logger.getLogger(Tropopoihsh.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        
                    try (PrintWriter fr = new PrintWriter(new BufferedWriter(new FileWriter("Users\\"+username+"\\"+arxeio+".txt", true))))
                    {
                        int i=0,c=0; 
                        while(i<list.size() )
                        {
                            if(list.get(i).equals(emf))
                            {
                                
                                list.set(i,eggrafh);
                                c=1;
                            }                            
                            i++;
                        }
                        fr.close();
                         } catch (IOException ex) {
                        Logger.getLogger(Tropopoihsh.class.getName()).log(Level.SEVERE, null, ex);
                    }
					
                    try (PrintWriter fr = new PrintWriter(new BufferedWriter(new FileWriter("Users\\"+username+"\\"+arxeio+".txt", false)))) {
                        int i=0;
                        while(i<list.size())
                        {
                            if(i==list.size())
                            {
                                fr.write(a.encrypt(list.get(i)));
                            }
                            else{
                                fr.write(a.encrypt(list.get(i)+"\n"));
                                i++;
                            }
                        }
                        fr.close();
                    } catch (IOException ex) {
                        Logger.getLogger(Tropopoihsh.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        Logger.getLogger(Tropopoihsh.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   
                    } 
                    frame.dispose();
                }
            
            
        });
    }
    public String EggrafhArxeio()
    {
        return eggrafh;
    }
    public void setUser(String username,String arxeio)
    {
        this.username=username;
        this.arxeio=arxeio;
    }
    public void setEmf(String emf)
    {
        this.emf=emf;
    }
}
