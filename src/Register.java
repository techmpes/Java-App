import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64.Encoder;
import static java.util.Base64.getEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import sun.misc.BASE64Encoder;


public class Register extends JFrame{
    private JPanel row0;
    private JButton eggrafh;
    private JLabel onoma,login,password;
    private JTextField onomaTxt,loginTxt;
    JPasswordField passwordTxt;
    private static String salt;
    private static String hash;
    
    public Register() throws NoSuchProviderException, SignatureException, ClassNotFoundException, InvalidKeyException, Exception {
        JFrame frame=new JFrame("Eggrafh xrhsth");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(100,100);
        
        onoma = new JLabel("Onomateponumo: ");
        onomaTxt = new JTextField(15);
        login = new JLabel("login name: ");        
        loginTxt = new JTextField(15);
        password = new JLabel("Sunthumatiko: ");
        passwordTxt = new JPasswordField(15);
        passwordTxt.setEchoChar('*');
        eggrafh = new JButton("Eggrafh");
        Container pane=getContentPane();
        GridLayout layout=new GridLayout(4,1);
        pane.setLayout(layout);
        pane.add(onoma);
        pane.add(onomaTxt);
        pane.add(login);
        pane.add(loginTxt);
        pane.add(password);
        pane.add(passwordTxt);
        pane.add(eggrafh);
        frame.setContentPane(pane);
        frame.pack();
        eggrafh.addActionListener(new ActionListener() 
        {   
            public void actionPerformed(ActionEvent evt) 
            {
                try {
                    jButton1ActionPerformed(evt);
                } catch (InvalidKeySpecException ex) {
                    Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NoSuchPaddingException ex) {
                    Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }
    private static String generateStorngPasswordHash(String password) throws NoSuchAlgorithmException, InvalidKeySpecException{
        byte saltArr[]=generateSalt();
        Encoder enc = getEncoder();
        salt=enc.encodeToString(saltArr);
        
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(password.toCharArray(), saltArr, 65536, 128);
        SecretKey tmp = factory.generateSecret(spec);
        SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");
        byte[] hashArr = factory.generateSecret(spec).getEncoded();
        
        hash=enc.encodeToString(secret.getEncoded());
        return enc.encodeToString(secret.getEncoded());
    }
    
    public byte[] encrypt(PublicKey key, byte[] plaintext) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException
{
    Cipher c = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(plaintext);
        String encryptedValue = new BASE64Encoder().encode(encVal);
        return encryptedValue.getBytes();
         
   
}

    public static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[20];
        random.nextBytes(bytes);
        return bytes;
    }

    private void jButton1ActionPerformed(ActionEvent evt) throws InvalidKeySpecException, NoSuchPaddingException, Exception {

            String name = onomaTxt.getText();
            String password = passwordTxt.getText();
            String username = loginTxt.getText();
            hash=generateStorngPasswordHash(password); 

            ObjectInputStream b = new ObjectInputStream(new FileInputStream("Dhmosio Kleidi"));
                
            PublicKey pubKey = (PublicKey) b.readObject();
            byte hashEncrypt[]=encrypt(pubKey,  hash.getBytes());
            
            Encoder enc = getEncoder();
            String hashEncr=enc.encodeToString(hashEncrypt);
            File dir2 = new File("Users"); 
            if(!dir2.exists()){
                //Create file Users
                dir2.mkdir(); 
            }
            File dir = new File("Users/"+username); 
            //No users with the same name
            if(!dir.exists()){
                //Create file username
                dir.mkdir(); 
            
           FileWriter fr;
            try { 
                    //Create file Hash
                    try (FileWriter sunopsh = new FileWriter( new File("Users/"+username+"/"+"Hash.txt"))) {
                        sunopsh.write("d");
                    }
                //Create file Stoixeia
                fr = new FileWriter( new File("Users/"+username+"/"+"Stoixeia.txt")); 
                fr.write(name+"\n");
                fr.write(username+"\n");
                fr.write(salt+"\n");
                fr.write(hashEncr);
                fr.close();
                Yphresies yp=new Yphresies(username,hash);
                Start a = new Start();
                this.dispose();
        } catch (IOException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Yparxon xrhsths!");
            }
    }
}


