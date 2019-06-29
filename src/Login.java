import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.io.IOException;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64.Encoder;
import static java.util.Base64.getEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;


public class Login extends JFrame {
    private JPanel row0;
    private JButton eisodos;
    private JLabel login,password;
    private JTextField loginTxt;
    JPasswordField passwordTxt;
    private static String salt;
    private static String hash;
    public Login()
    {
       JFrame frame=new JFrame("Eggrafh xrhsth");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(100,100);

        login = new JLabel("login name: ");        
        loginTxt = new JTextField(15);
        password = new JLabel("Sunthumatiko: ");
        passwordTxt = new JPasswordField(15);
        passwordTxt.setEchoChar('*');
        eisodos = new JButton("Eisodos");
        Container pane=getContentPane();

        GridLayout layout=new GridLayout(4,1);
        pane.setLayout(layout);
        pane.add(login);
        pane.add(loginTxt);
        pane.add(password);
        pane.add(passwordTxt);
        pane.add(eisodos);
        frame.setContentPane(pane);
        frame.pack();
        eisodos.addActionListener(new ActionListener() 
        {    
            public void actionPerformed(ActionEvent evt) 
            {
                try {
                    jButton1ActionPerformed(evt);
                } catch (InvalidKeySpecException ex) {
                    Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NoSuchPaddingException ex) {
                    Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private static String generateStorngPasswordHash(String password) throws NoSuchAlgorithmException, InvalidKeySpecException{

        byte saltArr[]=generateSalt();
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(password.toCharArray(), saltArr, 65536, 256);
        SecretKey tmp = factory.generateSecret(spec);
        SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");
        byte[] hashArr = factory.generateSecret(spec).getEncoded();
        Encoder enc = getEncoder();
        String hashP=enc.encodeToString(secret.getEncoded());
        return hashP;
    }
    public static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[20];
        random.nextBytes(bytes);
        return bytes;
    }
    //RSA/ECB/PKCS1Padding
    public String decrypt(PrivateKey key, byte[] ciphertext) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException
    {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");   
        cipher.init(Cipher.DECRYPT_MODE, key);  
        byte arr[]=cipher.doFinal(ciphertext);
        return new String(arr,"UTF8");
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) throws InvalidKeySpecException, NoSuchAlgorithmException, FileNotFoundException, Exception {
        try {
            String password = passwordTxt.getText();
            String username = loginTxt.getText();
            String line = null;
            File f=new File("Users/"+username+"/"+"Stoixeia.txt");
  
            hash=generateStorngPasswordHash(password);
            int i=0;

            try (BufferedReader br = new BufferedReader(new FileReader("Users/"+username+"/"+"Stoixeia.txt"))) {    
                while(i<4)
                {
                    line = br.readLine();   
                    i++;
                }
            }
            
            ObjectInputStream br = new ObjectInputStream(new FileInputStream("Idiwtiko Kleidi")); 
                
            PrivateKey privateKey = (PrivateKey) br.readObject();
            String hashDecrypt=decrypt(privateKey,  line.getBytes());            
        
            if(hashDecrypt.equals(hash)){  
                line="";
                this.dispose();
                System.out.println("Login epituxws");
                Yphresies yp=new Yphresies(username,hash);
                
            }else{ 
                passwordTxt.setBackground(Color.red);

            }
        }  catch (IOException | NoSuchAlgorithmException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
                           }        
    }
                    
}
