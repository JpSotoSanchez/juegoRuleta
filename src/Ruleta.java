import javax.swing.*;
import java.awt.*;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class Ruleta extends JFrame {

    private Clip clip; 

    public void mostrar() {
        setVisible(true);
    }

    public Ruleta() {
        setTitle("Ruleta");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); 
        try {
            URL url = new File("src\\SonidoRuleta.wav").toURI().toURL();
            clip = AudioSystem.getClip();
            AudioInputStream ais = AudioSystem.getAudioInputStream(url);
            clip.open(ais);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                if (clip != null && clip.isRunning()) {
                    clip.stop(); 
                }
            }
        });

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BorderLayout());

        ImageIcon imagen = new ImageIcon("src/ruleta1.gif"); 
        JLabel label = new JLabel(imagen);
        label.setHorizontalAlignment(SwingConstants.CENTER); 
        panel.add(label, BorderLayout.CENTER);

        getContentPane().add(panel);

        Timer temporizador = new Timer(3000, e -> dispose());
        temporizador.setRepeats(false); 
        temporizador.start();
    }
}
