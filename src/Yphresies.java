import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;



public class Yphresies extends JFrame implements ActionListener {
    static private final String newline = "\n";
    JButton eisagwgh,tropop,ekdosh;
    private String username;
    JTextArea log;
    private String hashXrhsth;
    Aes a=null;
    public Yphresies(String username,String hashXrhsth) throws NoSuchAlgorithmException, NoSuchProviderException, SignatureException, ClassNotFoundException, InvalidKeyException, Exception  
    {
        this.username=username;
        this.hashXrhsth=hashXrhsth;
        a=new Aes(hashXrhsth);
        JFrame frame=new JFrame("Yphresies tou xrhsth");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(100,100);
        eisagwgh = new JButton("Eisagwgh eggrafhs gia esoda h eksoda"); //Buttons
        eisagwgh.addActionListener(this);
        tropop = new JButton("Tropopoihsh stoixeiwn eggrafhs esodwn h eksodwn");
        tropop.addActionListener(this);
        ekdosh = new JButton("Ekdosh anaforas gia ta esoda kai ta eksoda ana mhna");
        ekdosh.addActionListener(this);
        log = new JTextArea(5,20);
        log.setMargin(new Insets(5,5,5,5));
        log.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(log);

        JPanel row0= new JPanel(); 
        row0.add(eisagwgh);
        JPanel row1= new JPanel(); 
        row1.add(tropop);
        JPanel row2= new JPanel(); 
        row2.add(ekdosh);
        JPanel row3=new JPanel();
        row3.add(log);
        Container pane=getContentPane();
        GridLayout layout=new GridLayout(4,1);
        pane.setLayout(layout);
        pane.add(row0);
        pane.add(row1);
        pane.add(row2);
        pane.add(row3);
        frame.setContentPane(pane);
        
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosing(java.awt.event.WindowEvent windowEvent) { 
            try {
                FileWriter sunopsh = new FileWriter( new File("Users/"+username+"/"+"Hash.txt"));
                sunopsh.write(hash());
                sunopsh.close();
            } catch (IOException | NoSuchAlgorithmException | NoSuchProviderException | SignatureException | ClassNotFoundException | InvalidKeyException ex) {
                Logger.getLogger(Yphresies.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        });
        try {
            File f=new File("Users/"+username+"/"+"Eksoda.txt");
            File f1=new File("Users/"+username+"/"+"Esoda.txt");
            if(!f.exists()){
            FileWriter eksoda = new FileWriter( new File("Users/"+username+"/"+"Eksoda.txt"));
            }
            if(!f1.exists()){
                FileWriter esoda = new FileWriter( new File("Users/"+username+"/"+"Esoda.txt"));
            }
        } catch (IOException ex) {
            Logger.getLogger(Yphresies.class.getName()).log(Level.SEVERE, null, ex);
        }

        frame.pack();
        checkHacked();
        frame.setVisible(true);
    }
    //Function that checks if the files of the user were hacked 
    public void checkHacked() throws NoSuchAlgorithmException, NoSuchProviderException, SignatureException, ClassNotFoundException, InvalidKeyException
    {
        try {
            
            BufferedReader rr = new BufferedReader(new FileReader("Users\\"+username+"\\"+"Hash.txt")); 
            String temp=rr.readLine();
            String temp2=hash();
            if(temp.equals(temp2))
            {
                JOptionPane.showMessageDialog(null,"Ta arxeia sou den tropopoihthkan!");
            }
            else
            {
                if("d".equals(temp)){
                    JOptionPane.showMessageDialog(null,"Prwto login. Neos Xrhsths!");
                }else
                    JOptionPane.showMessageDialog(null,"Ta arxeia sou exoun tropopoihthei apo mh-eksousiodothmeno proswpo!");
            }
            System.out.println("temp: "+temp+" hash:"+temp2);
            rr.close();
        } catch (Exception ex) {
            Logger.getLogger(Yphresies.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
        public String hash() throws NoSuchAlgorithmException, NoSuchProviderException, SignatureException, FileNotFoundException, IOException, ClassNotFoundException, InvalidKeyException{   
            File folder = new File("Users/"+username+"/");
            File[] listOfFiles; 
            MessageDigest md = null;
            listOfFiles = folder.listFiles();
            try {
                md = MessageDigest.getInstance("SHA-256");
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(Yphresies.class.getName()).log(Level.SEVERE, null, ex);
            }
            String hexString2="";
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()&&!listOfFiles[i].getName().equals("Hash.txt")) {
                    FileInputStream fis = null;
                    try {
                        fis = new FileInputStream("Users/"+username+"/"+listOfFiles[i].getName());
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Yphresies.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Signature sig = Signature.getInstance("SHA256withRSA");
                    ObjectInputStream br = new ObjectInputStream(new FileInputStream("Idiwtiko Kleidi"));
                    PrivateKey privateKey = (PrivateKey) br.readObject();
                    sig.initSign(privateKey);
                    byte[] dataBytes = hashXrhsth.getBytes();
                    int nread = 0; 
                    try {
                        while ((nread = fis.read(dataBytes)) != -1) {
                            sig.update(dataBytes, 0, nread);
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(Yphresies.class.getName()).log(Level.SEVERE, null, ex);
                    }
					
                    byte[] bSignedData_JAVASHA1_CAPIRSA = sig.sign();
                    hexString2=hexString2+Arrays.toString(bSignedData_JAVASHA1_CAPIRSA);
                }
            }
            return hexString2;
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == eisagwgh) 
        {
            String [] choice={"ESODA","EKSODA"};
            String display=(String)JOptionPane.showInputDialog(null,"Epilekse arxeio: ","Yphresies",JOptionPane.PLAIN_MESSAGE,null,choice,choice[1]); 
            if("ESODA".equals(display))
            {
                EisEggrafhs ee=new EisEggrafhs(a);
                ee.setUser(username, "Esoda.txt");
                log.append("H eggrafh sto arxeio esodwn egine epituxws." + newline);
            } 
            else 
            {
                EisEggrafhs ee=new EisEggrafhs(a);
                ee.setUser(username, "Eksoda.txt");
                log.append("H eggrafh sto arxeio eksodwn egine epituxws." + newline);
            }
        } 
        else if (e.getSource() ==tropop) 
        {
            log.removeAll();
            String [] choice={"ESODA","EKSODA"};
            String display=(String)JOptionPane.showInputDialog(null,"Epilekse arxeio: ","Yphresies",JOptionPane.PLAIN_MESSAGE,null,choice,choice[1]); 
            String hmer=JOptionPane.showInputDialog(null, "Dwse hmeromhnia se morfh mm/dd/yyyy: ", "mm/dd/yyyy");
            if(display.equals("ESODA"))
            {
                ArrayList<String> list=new ArrayList();
                String line;
                int i=0;
             
                    try (BufferedReader br = new BufferedReader(new FileReader("Users\\"+username+"\\"+"Esoda.txt"))) {
                        while( (line=br.readLine()) != null)
                        {
                            String str=a.decrypt(line);
                            str = new StringBuilder().append(line.charAt(0)).append(line.charAt(1)).append(line.charAt(2)).append(line.charAt(3)).append(line.charAt(4)).append(line.charAt(5)).append(line.charAt(6)).append(line.charAt(7)).append(line.charAt(8)).append(line.charAt(9)).toString();
                            if(str.equals(hmer))
                            {
                                list.add(line);
                            }
                        }
                        br.close();
                    }
                catch (IOException ex) {
                    Logger.getLogger(Yphresies.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(Yphresies.class.getName()).log(Level.SEVERE, null, ex);
                }

                    String[] sunall = new String[list.size()];
                    for(i=0;i<list.size();i++)
                    {
                        sunall[i]=list.get(i);
                    }

                String emf=(String)JOptionPane.showInputDialog(null,"Epilekse sunallagh gia tropopooihsh: ","Sunolo Sunallagwn",JOptionPane.PLAIN_MESSAGE,null,sunall,sunall[0]);
                Tropopoihsh tr=new Tropopoihsh(a);
                tr.setUser(username,"Esoda");
                tr.setEmf(emf);
                log.append("H tropopoihsh sto arxeio esodwn egine epituxws." + newline);
            } 
            else 
            {
                ArrayList<String> list=new ArrayList();
                String line;
                int i=0;
             
                    try (BufferedReader br = new BufferedReader(new FileReader("Users\\"+username+"\\"+"Eksoda.txt"))) {
                        while( (line=br.readLine()) != null)
                        {
                            line=a.decrypt(line);
                            String str = new StringBuilder().append(line.charAt(0)).append(line.charAt(1)).append(line.charAt(2)).append(line.charAt(3)).append(line.charAt(4)).append(line.charAt(5)).append(line.charAt(6)).append(line.charAt(7)).append(line.charAt(8)).append(line.charAt(9)).toString();
                            if(str.equals(hmer))
                            {
                                list.add(line);
                            }
                        }
                        br.close();
                    }
                catch (IOException ex) {
                    Logger.getLogger(Yphresies.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(Yphresies.class.getName()).log(Level.SEVERE, null, ex);
                }
                    String[] sunall = new String[list.size()];
                    for(i=0;i<list.size();i++)
                    {
                        sunall[i]=list.get(i);
                    }
                String emf=(String)JOptionPane.showInputDialog(null,"Epilekse sunallagh gia tropopooihsh: ","Sunolo Sunallagwn",JOptionPane.PLAIN_MESSAGE,null,sunall,sunall[0]);
                Tropopoihsh tr=new Tropopoihsh(a);
                tr.setUser(username,"Eksoda");
                tr.setEmf(emf);
                log.append("H tropopoihsh sto arxeio eksodwn egine epituxws." + newline);
            }
        }
        else if (e.getSource() == ekdosh) 
        {
            log.removeAll();
            String mhnas=JOptionPane.showInputDialog(null, "Dwse mhna se morfh mm: ", "mm");
            
            String line = "";
            ArrayList<String> list=new ArrayList();
            int j=0;
            try 
            {
                BufferedReader rr = new BufferedReader(new FileReader("Users\\"+username+"\\"+"Esoda.txt"));
                while((line=rr.readLine()) != null)
                {
                    String line1=a.decrypt(line);
                    String str= new StringBuilder().append(line1.charAt(0)).append(line1.charAt(1)).toString();
                    if(str.equals(mhnas))
                    {
                        list.add(line1);
                    }  
                }
                rr.close();
            } catch (IOException ex) {
                Logger.getLogger(Yphresies.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Yphresies.class.getName()).log(Level.SEVERE, null, ex);
            }
            String array[][]=new String[list.size()][3];
            String info="";
            int c=0;

            for(j=0;j<list.size();j++)
            {
                line=list.get(j);
                String temp[] = line.split(",");
                array[j][0]=temp[0];
                array[j][1]=temp[1];
                array[j][2]=temp[2];
            }      
            line = "";
            ArrayList<String> list1=new ArrayList();
            j=0;
            try 
            {
                BufferedReader rr = new BufferedReader(new FileReader("Users\\"+username+"\\"+"Eksoda.txt"));
                while((line=rr.readLine()) != null)
                {
                    String line1=a.decrypt(line);
                     System.out.println("Line2: "+line1);
                    String str= new StringBuilder().append(line1.charAt(0)).append(line1.charAt(1)).toString();
                    if(str.equals(mhnas))
                    {
                        list1.add(line1);
                    }  
                }
                rr.close();
            } catch (IOException ex) {
                Logger.getLogger(Yphresies.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Yphresies.class.getName()).log(Level.SEVERE, null, ex);
            }
            String array1[][]=new String[list1.size()][3];
            info="";
            c=0;
            for(j=0;j<list1.size();j++)
            {
                line=list1.get(j);
                System.out.println(line);
                String temp[] = line.split(",");
                array1[j][0]=temp[0];
                array1[j][1]=temp[1];
                array1[j][2]=temp[2];
            }
            System.out.println(array);
            System.out.println(array1);
            Anagnwsh an=new Anagnwsh(array,array1,array.length,array1.length);          
    }
        }
    }

