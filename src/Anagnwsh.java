
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.*;


public class Anagnwsh extends JFrame{
    private JTextArea log;
    private String esoda[][];
    private String eksoda[][];
    private int j,k;
    private float sumEs,sumEks;
    public Anagnwsh(String esoda[][],String eksoda[][],int j,int k)
    {
        this.esoda=esoda;
        this.eksoda=eksoda;
        this.j=j;
        this.k=k;
        sumEs=0;
        sumEks=0;
        JFrame frame=new JFrame("Anagnwsh arxeiwn esodwn eksodwn..");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(100,100);
        log = new JTextArea(5,20);
        log.setMargin(new Insets(5,5,5,5));
        log.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(log);
        log.append(" Arxeio Esodwn :\n");
        for(int i=0;i<j;i++)
        {
            log.append("Hmeromhnia: "+ esoda[i][0]+"\n");
            log.append("Poso: "+ esoda[i][1]+"\n");
            sumEs= sumEs+Float.parseFloat(esoda[i][1]);
            log.append("Perigrafh: "+ esoda[i][2]+"\n");
        }
        log.append("Athroisma Esodwn: "+ sumEs);
        log.append("\n\nArxeio Eksodwn :\n");
        for(int i=0;i<k;i++)
        {
            log.append("Hmeromhnia: "+ eksoda[i][0]+"\n");
            log.append("Poso: "+ eksoda[i][1]+"\n");
            sumEks= sumEks+Float.parseFloat(eksoda[i][1]);
            log.append("Perigrafh: "+ eksoda[i][2]+"\n");
        }
        log.append("Athroisma Eksodwn: "+ sumEks);
        JPanel panel=new JPanel();
        panel.add(log);
        Container pane=getContentPane();
        pane.add(panel);

        GridLayout layout=new GridLayout(1,1);
        pane.setLayout(layout);
        frame.setContentPane(pane);

        frame.pack();
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) { 
                frame.dispose();
            }
        });
    }
}
