import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Iniciosesion extends JFrame {
    private JTextField userField;
    private JPasswordField passField;

    private String[] usuarios = {" "," "," "," "," "};
    private int[] contraseñas = {0,0,0,0,0};
    private int contador=0;

    public Iniciosesion() {
        setTitle("Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel userLabel = new JLabel("Usuario:");
        JLabel passLabel = new JLabel("Contraseña:");
        userField = new JTextField();
        passField = new JPasswordField();

        JButton loginButton = new JButton("Iniciar sesión");
        JButton createButton = new JButton("Crear cuenta");

        panel.add(userLabel);
        panel.add(userField);
        panel.add(passLabel);
        panel.add(passField);
        panel.add(loginButton);
        panel.add(createButton);

        add(panel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Entrar();
            }
        });

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearCuenta();
            }
        });
    }

    private void Entrar() {
        String usuario = userField.getText();
        int contraseña;
        try {
            contraseña = Integer.parseInt(new String(passField.getPassword()));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Contraseña inválida. Debe ser un número.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        for (int i = 0; i < usuarios.length; i++) {
            if (usuarios[i].equals(usuario) && contraseñas[i] == contraseña) {
                JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso!", "Bienvenido", JOptionPane.INFORMATION_MESSAGE);
                JOptionPane.showMessageDialog(this, "1.-Selecciona una Ficha: Haz clic en el valor de ficha deseado.\n2.-Realiza una Apuesta: Número individual, docena, color, fila, mitad o paridad. \n3.-Confirma y Gira: Marca la casilla de confirmación. Haz clic en GIRAR para iniciar la ruleta.\n4.-DIVIÉRTETE!", "Instrucciones", JOptionPane.INFORMATION_MESSAGE);
                Ventana v1 = new Ventana(usuario);
                v1.setResizable(true);
                v1.setVisible(true);
                dispose(); 
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void crearCuenta() {
        if (contador<6){
        String usuario = JOptionPane.showInputDialog("Ingresa tu nombre de usuario para registrarte");
        boolean bandera = false;
        do {
            try {
                int contraseña = Integer.parseInt(JOptionPane.showInputDialog("Ingresa una contraseña numérica"));
                int contraseña2 = Integer.parseInt(JOptionPane.showInputDialog("Vuelve a ingresar la contraseña"));
                if (contraseña == contraseña2) {
                    bandera = true;
                    usuarios[contador]=usuario;
                    contraseñas[contador]=contraseña;
                    contador++;
                } else {
                    JOptionPane.showMessageDialog(null, "La contraseña no coincide");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "La contraseña es inválida");
                bandera=true;
            }
        } while (!bandera);
    }
    else{
        JOptionPane.showMessageDialog(null, "Límite de usuarios alcanzado");
    }
    }
}
