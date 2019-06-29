
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;



public class Start extends JFrame{
    private JPanel row0,row1;
    private JButton jButton1;
    private JButton jButton2;
    private JLabel eisodos;
    static PrivateKey priv;
    static PublicKey pub;
    public Start() {
        
        JFrame frame=new JFrame("Efarmogh");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(100,100);
        row0=new JPanel();
        eisodos=new JLabel("Eisodos sthn efarmogh..");
        row1=new JPanel();
        jButton1 = new JButton("Register");
        jButton2 = new JButton("Login");
        
        Container pane=getContentPane();
        GridLayout layout=new GridLayout(2,1);
        pane.setLayout(layout);
        FlowLayout layout0=new FlowLayout();
        row0.setLayout(layout0);
        row0.add(eisodos);
        pane.add(row0);
        FlowLayout layout1=new FlowLayout();
        row1.setLayout(layout1);
        row1.add(jButton1);
        row1.add(jButton2);
        pane.add(row1);
        frame.setContentPane(pane);
        frame.pack();
        jButton1.addActionListener(new ActionListener() 
        {    
            public void actionPerformed(ActionEvent evt) 
            {
                try {
                    jButton1ActionPerformed(evt);
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NoSuchProviderException ex) {
                    Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SignatureException ex) {
                    Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvalidKeyException ex) {
                    Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        jButton2.addActionListener(new ActionListener() {    
            public void actionPerformed(ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        
    }
    private void jButton1ActionPerformed(ActionEvent evt) throws NoSuchAlgorithmException, NoSuchProviderException, SignatureException, ClassNotFoundException, InvalidKeyException, Exception {
        Register reg = new Register();  
        this.dispose(); 
    }
    private void jButton2ActionPerformed(ActionEvent evt) {
        Login log = new Login();        
        this.dispose();
    }
    public static void main(String[] args) throws NoSuchAlgorithmException, Exception {
        
        KeyPairGenerator keyGen = null;
        keyGen = KeyPairGenerator.getInstance("RSA");
        SecureRandom random  = new SecureRandom();
        keyGen.initialize(2048, random);
        KeyPair pair = keyGen.generateKeyPair();
        priv = pair.getPrivate();
        System.out.println("PrivateKey :"+priv);
        pub = pair.getPublic();
 
        
        try {
            File f=new File("Dhmosio Kleidi");
            File f1=new File("Idiwtiko Kleidi");
            if(!f.exists()){
            ObjectOutputStream fr = new ObjectOutputStream( new FileOutputStream("Dhmosio Kleidi"));
            fr.writeObject(pub);
            }
            if(!f1.exists()){
                ObjectOutputStream fr = new ObjectOutputStream( new FileOutputStream("Idiwtiko Kleidi"));
                fr.writeObject(priv);
            }
        } catch (IOException ex) {
            Logger.getLogger(Yphresies.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Start s=new Start();
    }

}

