
package modelo;

/**
 *
 * @author Bleider
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class SplashScreen extends JFrame {
    private final JPanel contentPane;
    private final JLabel lblLogo;
    private final JProgressBar progressBar;
    private final JLabel lblProgress;
    
    public SplashScreen() {
        // Configurar la ventana de la SplashScreen
        setUndecorated(true);
        setResizable(false);
        setTitle("");
        setIconImage(new ImageIcon(getClass().getResource("/images/SpSc.png")).getImage());

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(596, 422);
        //setSize(450, 300);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);
        
        // Panel principal
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        // Etiqueta del logo
        lblLogo = new JLabel();
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/images/SpSc.png"));
        Image logoImage = logoIcon.getImage().getScaledInstance(596, 422, Image.SCALE_SMOOTH);
        lblLogo.setIcon(new ImageIcon(logoImage));
        contentPane.add(lblLogo, BorderLayout.CENTER);
        
        // Barra de progreso
        progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        contentPane.add(progressBar, BorderLayout.SOUTH);
        
        // Etiqueta de progreso
        lblProgress = new JLabel("Cargando...");
        lblProgress.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblProgress.setHorizontalAlignment(JLabel.CENTER);
        contentPane.add(lblProgress, BorderLayout.NORTH);
    }
    
    public void setProgress(String message) {
        lblProgress.setText(message);
    }
    
    public void close() {
        dispose();
    }
    

}

