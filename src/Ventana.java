import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.net.URL;
import java.util.Random;

public class Ventana extends JFrame {
    String USUARIO;
    private Clip clip;

    private JPanel panelVerde;
    private JCheckBox confirmacion;
    private JButton girar;
    private JButton reiniciar;
    private boolean apostoelusuario = false;
    private double dinero = 3000; // dinero del usuario
    private int apuesta = 0; // cantidad actual de la ficha para apostar
    private int numeroAleatorio;
    private double dineroganado=0;
    
    private JLabel labeldinero;
    private JLabel labelapuesta;

    private JButton[] botonesindividuales=new JButton[37];
    private JButton[] botondocena=new JButton[3];
    private JButton[] botoncolor=new JButton[2];
    private JButton[] botonmitad=new JButton[2];
    private JButton[] botonpar=new JButton[2];
    private JButton[] botonfila=new JButton[3];
    
    private JButton[] botonesFichas;
    private int[] valoresFichas = {1, 5, 10, 25, 50, 100, 500, 1000}; // Valores de las fichas
    
    private int[] fichasIndividuales = new int[37];
    private int[] docena = new int[3];
    private int[] color = new int[2];
    private int[] fila = new int[3];
    private int[] mitad = new int[2];
    private int[] par = new int[2];
    
    private JLabel[] labelsCasillas;
    private JLabel[] labelsdocena;
    private JLabel[] labelcolor = new JLabel[2];
    private JLabel[] labelfila= new JLabel[3];
    private JLabel[] labelmitad= new JLabel[2];
    private JLabel[] labelpar= new JLabel[2];

    public void funcioncasillanumero(int numero){
        if (dinero>apuesta){
            apostoelusuario=true;
            fichasIndividuales[numero] = fichasIndividuales[numero] + apuesta;
            dinero-=apuesta;
            actualizarFichas();
            actualizarLabels();
        }
        else if (dinero==apuesta){
            apostoelusuario=true;
            fichasIndividuales[numero] = fichasIndividuales[numero] + apuesta;
            dinero=0;
            apuesta=0;
            actualizarApuesta();
            actualizarFichas();
            actualizarLabels();
        }
        else{
            apuesta=0;
            actualizarApuesta();
        }
    }

    public void funcioncasilladocena(int numero){
        if (dinero>apuesta){
            apostoelusuario=true;
            docena[numero] = docena[numero] + apuesta;
            dinero-=apuesta;
            actualizarFichas();
            actualizarLabels();
        }
        else if (dinero==apuesta){
            apostoelusuario=true;
            docena[numero] = docena[numero] + apuesta;
            dinero=0;
            apuesta=0;
            actualizarApuesta();
            actualizarFichas();
            actualizarLabels();
            }
            else{
                apuesta=0;
                actualizarApuesta();
                }
    }

    public void funcioncasillacolor(int numero){
        if (dinero>apuesta){
            apostoelusuario=true;
            color[numero] = color[numero] + apuesta;
            dinero-=apuesta;
            actualizarFichas();
            actualizarLabels();
        }
        else if (dinero==apuesta){
            apostoelusuario=true;
            color[numero] = color[numero] + apuesta;
            dinero=0;
            apuesta=0;
            actualizarApuesta();
            actualizarFichas();
            actualizarLabels();
        }
        else{
            apuesta=0;
            actualizarApuesta();
        }
    }

    public void funcioncasillafila(int numero){
        if (dinero>apuesta){
            apostoelusuario=true;
            fila[numero] = fila[numero] + apuesta;
            dinero-=apuesta;
            actualizarFichas();
            actualizarLabels();
        }
        else if (dinero==apuesta){
            apostoelusuario=true;
            fila[numero] = fila[numero] + apuesta;
            dinero=0;
            apuesta=0;
            actualizarApuesta();
            actualizarFichas();
            actualizarLabels();
        }
        else{
            apuesta=0;
            actualizarApuesta();
        }
    }
    
    public void funcioncasillamitad(int numero){
        if (dinero>apuesta){
            apostoelusuario=true;
            mitad[numero] = mitad[numero] + apuesta;
            dinero-=apuesta;
            actualizarFichas();
            actualizarLabels();
        }
        else if (dinero==apuesta){
            apostoelusuario=true;
            mitad[numero] = mitad[numero] + apuesta;
            dinero=0;
            apuesta=0;
            actualizarApuesta();
            actualizarFichas();
            actualizarLabels();
        }
        else{
            apuesta=0;
            actualizarApuesta();
        }
    }

    public void funcioncasillapar(int numero){
        if (dinero>apuesta){
            apostoelusuario=true;
            par[numero] = par[numero] + apuesta;
            dinero-=apuesta;
            actualizarFichas();
            actualizarLabels();
        }
        else if (dinero==apuesta){
            apostoelusuario=true;
            par[numero] = par[numero] + apuesta;
            dinero=0;
            apuesta=0;
            actualizarApuesta();
            actualizarFichas();
            actualizarLabels();
        }
        else{
            apuesta=0;
            actualizarApuesta();
        }
    }

    public void funcionfichas(int valorFicha){
        apuesta = valorFicha;
        seleccionarfichas();
        actualizarApuesta();
    }
    
    public void actualizarApuesta(){
        labelapuesta.setText("Ficha seleccionada: "+apuesta);
    }
    
    public void actualizarLabels() {
        labeldinero.setText("Dinero actual: "+dinero);
        for (int i = 0; i <= 36; i++) {
            labelsCasillas[i].setText("Apuesta en " + i + " es: " + fichasIndividuales[i]);
        }
        for (int i = 0; i <3; i++){
            labelsdocena[i].setText("Apuesta en la docena " + (i+1) + " es: " + docena[i]);
        }
        for (int i = 0; i <2; i++){
            String coloractual;
            if (i ==0){
                coloractual = "rojo";
            }
            else{
                coloractual = "negro";
            }
            labelcolor[i].setText("Apuesta en el color " + coloractual + " es: " + color[i]);
        }

        for (int i = 0; i <3; i++){
            labelfila[i].setText("Apuesta en la fila " + (i+1) + " es: " + fila[i]);
        }
        for (int i = 0; i <2; i++){
            labelmitad[i].setText("Apuesta en la mitad " + (i+1) + " es: " + mitad[i]);
        }
        for (int i = 0; i <2; i++){
            String paractual;
            if (i ==0){
                paractual = "pares";
            }
            else{
                paractual = "impares";
            }
            labelpar[i].setText("Apuesta en los " + paractual + " es: " + par[i]);
        }
    }
    public void seleccionarfichas(){
        for(int i =0;i<8;i++){
            if(apuesta==valoresFichas[i]){
                botonesFichas[i].setBackground(Color.green);
                botonesFichas[i].setBorderPainted(true);
                botonesFichas[i].setContentAreaFilled(true);
            }
            else{
                botonesFichas[i].setBackground(null);
                botonesFichas[i].setBorderPainted(false);
                botonesFichas[i].setContentAreaFilled(false);
            }
        }
    }
    
    public void actualizarFichas() {
        for (int i = 0; i < 8; i++) {
            if(valoresFichas[i]>dinero){
                botonesFichas[i].setEnabled(false);
            }
            else{
                botonesFichas[i].setEnabled(true);
            }
        }
        seleccionarfichas();
    }

    public void apagarcomponentes(){
        reiniciar.setEnabled(false);
        confirmacion.setEnabled(false);
        for (int i = 0; i < 8; i++) {
            botonesFichas[i].setEnabled(false);
        }
        for (int i = 0; i < 37; i++) {
            botonesindividuales[i].setEnabled(false);
        }
        for (int i = 0; i < 3; i++) {
            botondocena[i].setEnabled(false);
        }
        for (int i = 0; i < 2; i++) {
            botoncolor[i].setEnabled(false);
        }
        for (int i = 0; i < 2; i++) {
            botonmitad[i].setEnabled(false);
        }
        for (int i = 0; i < 2; i++) {
            botonpar[i].setEnabled(false);
        }
        for (int i = 0; i < 3; i++) {
            botonfila[i].setEnabled(false);
        }
    }

    public void prendercomponentes(){
        reiniciar.setEnabled(true);
        confirmacion.setEnabled(true);
        for (int i = 0; i < 8; i++) {
            botonesFichas[i].setEnabled(true);
        }
        for (int i = 0; i < 37; i++) {
            botonesindividuales[i].setEnabled(true);
        }
        for (int i = 0; i < 3; i++) {
            botondocena[i].setEnabled(true);
        }
        for (int i = 0; i < 2; i++) {
            botoncolor[i].setEnabled(true);
        }
        for (int i = 0; i < 2; i++) {
            botonmitad[i].setEnabled(true);
        }
        for (int i = 0; i < 2; i++) {
            botonpar[i].setEnabled(true);
        }
        for (int i = 0; i < 3; i++) {
            botonfila[i].setEnabled(true);
        }
    }
    public void funcionreiniciar(){
        confirmacion.setSelected(false);
        for(int i=0;i<37;i++){
            dinero+=fichasIndividuales[i];
        }
        for(int i = 0;i<3;i++){
            dinero+=docena[i];
            dinero+=fila[i];
        }
        for(int i = 0;i<2;i++){
            dinero+=color[i];
            dinero+=mitad[i];
            dinero+=par[i];
        }
        limpiar();
        apuesta=0;
        actualizarFichas();
        actualizarLabels();
        actualizarApuesta();
    }
    public void funcioncheck(){
        if (confirmacion.isSelected()) {
            girar.setEnabled(true);
        } else {
            girar.setEnabled(false);
        }
    }

    public void ruleta() {            
            confirmacion.setSelected(false);
            apagarcomponentes();
            // Crear y mostrar la ventana Ruleta
            Ruleta ruleta = new Ruleta();
            setExtendedState(JFrame.MAXIMIZED_BOTH);
            ruleta.mostrar();
            // Configurar temporizador para que se ejecute después de 3 segundos
            Timer temporizador = new Timer(3000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setExtendedState(JFrame.MAXIMIZED_BOTH);
                    // Obtener un número aleatorio
                    Random random = new Random();
                    numeroAleatorio = random.nextInt(37);
                    JOptionPane.showMessageDialog(null, "Gano el numero: " + numeroAleatorio);
                    ganador(numeroAleatorio);  
                }
            });
            temporizador.setRepeats(false); 
            temporizador.start();
    }
    
    public void limpiar(){
        for(int i=0;i<37;i++){
            fichasIndividuales[i]=0;
        }
        for(int i = 0;i<3;i++){
            docena[i]=0;
            fila[i]=0;
        }
        for(int i = 0;i<2;i++){
            color[i]=0;
            mitad[i]=0;
            par[i]=0;
        }
    }
    
    public void individual(int numeroAleatorio){
        dineroganado+=fichasIndividuales[numeroAleatorio]*36;
    }
    
    public void par(){
        dineroganado+=par[0]*2;
    }
    
    public void impar(){
        dineroganado+=par[1]*2;
    }
    
    public void rojo(){
        dineroganado+=color[0]*2;
    }
    
    public void negro(){
        dineroganado+=color[1]*2;
    }
    
    public void docena1(){
        dineroganado+=docena[0]*3;
    }
    
    public void docena2(){
        dineroganado+=docena[1]*3;
    }
    
    public void docena3(){
        dineroganado+=docena[2]*3;
    }
    
    public void fila1(){
        dineroganado+=fila[0]*3;
    }
    
    public void fila2(){
        dineroganado+=fila[1]*3;
    }
    
    public void fila3(){
        dineroganado+=fila[2]*3;
    }
    
    public void mitad1(){
        dineroganado+=mitad[0]*2;
    }
    
    public void mitad2(){
        dineroganado+=mitad[1]*2;
    }
    
    public void ganador(int numeroAleatorio){
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        switch (numeroAleatorio) {
            case 0:
                individual(numeroAleatorio);
                break;
            case 1:
                individual(numeroAleatorio);
                docena1();
                fila1();
                mitad1();
                impar();
                rojo();
                break;
            case 2:
                individual(numeroAleatorio);
                fila2();
                docena1();
                mitad1();
                par();
                negro();
                break;
            case 3:
                individual(numeroAleatorio);
                docena1();
                fila3();
                mitad1();
                impar();
                rojo();
                break;
            case 4:
                individual(numeroAleatorio);
                docena1();
                fila1();
                mitad1();
                par();  
                negro();
                break;
            case 5:
                individual(numeroAleatorio);
                docena1();
                fila2();
                mitad1();
                impar();
                rojo();
                break;
            case 6:
                individual(numeroAleatorio);
                docena1();
                fila3();
                mitad1();
                par();  
                negro();
                break;
            case 7:
                individual(numeroAleatorio);
                docena1();
                fila1();
                mitad1();
                impar();
                rojo();
                break;
            case 8:
                individual(numeroAleatorio);
                docena1();
                fila2();
                mitad1();
                par();
                negro();
                break;
            case 9:
                individual(numeroAleatorio);
                docena1();
                fila3();
                impar();
                mitad1();
                rojo();
                break;
            case 10:
                individual(numeroAleatorio);
                docena1();
                fila1();
                mitad1();
                par();
                negro();
                break;
            case 11:
                individual(numeroAleatorio);
                docena1();
                fila2();
                mitad1();
                impar();
                negro();
                break;
            case 12:
                individual(numeroAleatorio);
                docena1();
                fila3();
                mitad1();
                par();
                rojo();
                break;
            case 13:
                individual(numeroAleatorio);
                docena2();
                fila1();
                mitad1();
                impar();
                negro();
                break;
            case 14:
                individual(numeroAleatorio);
                docena2();
                fila2();
                mitad1();
                par();
                rojo();
                break;
            case 15:
                individual(numeroAleatorio);
                docena2();
                fila3();
                mitad1();
                impar();
                negro();
                break;
            case 16:
                individual(numeroAleatorio);
                docena2();
                fila1();
                mitad1();
                par();
                rojo();
                break;
            case 17:
                individual(numeroAleatorio);
                docena2();
                fila2();
                mitad1();
                impar();
                negro();
                break;
            case 18:
                individual(numeroAleatorio);
                docena2();
                fila3();
                mitad1();
                par();
                rojo();
                break;
            case 19:
                individual(numeroAleatorio);
                docena2();
                fila1();
                mitad2();
                impar();
                rojo();
                break;
            case 20:
                individual(numeroAleatorio);
                docena2();
                fila2();
                mitad2();
                par();
                negro();
                break;
            case 21:
                individual(numeroAleatorio);
                docena2();
                fila3();
                impar();
                mitad2();
                rojo();
                break;
            case 22:
                individual(numeroAleatorio);
                docena2();
                fila1();
                mitad2();
                par();
                negro();
                break;
            case 23:
                individual(numeroAleatorio);
                docena2();
                fila2();
                mitad2();
                impar();
                rojo();
                break;
            case 24:
                individual(numeroAleatorio);
                docena2();
                fila3();
                mitad2();
                par();
                negro();
                break;
            case 25:
                individual(numeroAleatorio);
                docena3();
                fila1();
                impar();
                mitad2();
                rojo();
                break;
            case 26:
                individual(numeroAleatorio);
                docena3();
                fila2();
                mitad2();
                par();
                negro();
                break;
            case 27:
                individual(numeroAleatorio);
                docena3();
                fila3();
                mitad2();
                impar();
                rojo();
                break;
            case 28:
                individual(numeroAleatorio);
                docena3();
                fila1();
                mitad2();
                par();
                negro();
                break;
            case 29:
                individual(numeroAleatorio);
                docena3();
                fila2();
                mitad2();
                impar();
                negro();
                break;
            case 30:
                individual(numeroAleatorio);
                docena3();
                fila3();
                mitad2();
                par();
                rojo();
                break;
            case 31:
                individual(numeroAleatorio);
                docena3();
                fila1();
                mitad2();
                impar();
                negro();
                break;
            case 32:
                individual(numeroAleatorio);
                docena3();
                fila2();
                mitad2();
                par();
                rojo();
                break;
            case 33:
                individual(numeroAleatorio);
                docena3();
                fila3();
                mitad2();
                impar();
                negro();
                break;
            case 34:
                individual(numeroAleatorio);
                docena3();
                fila1();
                mitad2();
                par();
                rojo();
                break;
            case 35:
                individual(numeroAleatorio);
                docena3();
                fila2();
                mitad2();
                impar();
                negro();
                break;
            case 36:
                individual(numeroAleatorio);
                docena3();
                fila3();
                mitad2();
                par();
                rojo();
                break;
        }
        limpiar();
        if(apostoelusuario){
            if (dineroganado>0){
                JOptionPane.showMessageDialog(null, "Usted ha ganado: "+dineroganado);
            }
            else{
                JOptionPane.showMessageDialog(null, "Usted ha perdido");
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "No se realizó apuesta");
        }
        dinero=dinero+dineroganado;
        dineroganado=0;
        actualizarLabels();
        actualizarFichas();
        prendercomponentes();
        apostoelusuario = false;
        actualizarFichas();
    }

    public Ventana(String usuario) {
        USUARIO=usuario;
        // Configura el JFrame para ajustarse automáticamente al tamaño de la pantalla
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Establece el icono de la ventana
        ImageIcon esponja = new ImageIcon("src/gangster.png");
        setIconImage(esponja.getImage());

        // Inicializa los componentes
        initComponents();
        try {
            URL url = new File("src\\CasinoJazz.wav").toURI().toURL();
            clip = AudioSystem.getClip();
            AudioInputStream ais = AudioSystem.getAudioInputStream(url);
            clip.open(ais);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void initComponents() {
        initPaneles();
        labels(USUARIO);
        initBotones();

        checkbox();
    }

    private void initPaneles() {
        panelVerde = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(new ImageIcon("src/tela.jpg").getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        getContentPane().add(panelVerde);
    }

    private void initBotones() {
        botonesindividuales[0] = new JButton("0");
        botonesindividuales[0].setBounds(230, 100, 50, 240);
        botonesindividuales[0].setEnabled(true);
        botonesindividuales[0].setBackground(Color.green);
        botonesindividuales[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                funcioncasillanumero(0);
            }
        });
        panelVerde.add(botonesindividuales[0]);

        // Crear botones del 1 al 36
        int x = 280; // Posición inicial en x
        int y = 100; // Posición inicial en y
        int width = 70; // Ancho de los botones
        int height = 80; // Alto de los botones
        Color[] colors = {Color.red, Color.black, Color.red, Color.black}; // Colores alternados
        int colorIndex = 0; // Índice de color
        for (int i = 1; i <= 36; i++) {
            JButton boton = new JButton(Integer.toString(i));
            boton.setBounds(x, y, width, height);
            boton.setEnabled(true);
            boton.setBackground(colors[colorIndex]);
            if (colorIndex == 1 || colorIndex == 3) {
                boton.setForeground(Color.WHITE);
            } else {
                boton.setForeground(Color.BLACK);
            }
            // Agregar evento al botón según su valor
            final int numero = i;
            boton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    funcioncasillanumero(numero);
                }
            });
            botonesindividuales[i]=boton;
            panelVerde.add(botonesindividuales[i]);
            colorIndex = (colorIndex + 1) % colors.length;
            if(i==10||i==28){
                colorIndex=1;
            }
            if(i==18){
                colorIndex=0;
            }
            // Actualizar posición y color
            if (i % 3 == 0) {
                x += width;
                y = 100;
            } else {
                y += height;
            }
        }

        // Crear botones docenas
        int xdocena = 280; // Posición inicial en x
        int ydocena = 340; // Posición inicial en y
        int widthdocena = 280; // Ancho de los botones
        int heightdocena = 50; // Alto de los botones
        String[]docenas={"1-12","13-24","25-36"};
        for (int i =0;i<3;i++){
            JButton boton = new JButton(docenas[i]);
            boton.setBounds(xdocena, ydocena, widthdocena, heightdocena);
            boton.setEnabled(true);
            final int numero = i;
            boton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    funcioncasilladocena(numero);
                }
            });
            xdocena+=widthdocena;
            botondocena[i]=boton;
            panelVerde.add(botondocena[i]);
        }

        //color
        int xcolor = 280; 
        int ycolor = 390;
        int widthcolor = 140; 
        int heightcolor = 50;
        Color []colores ={Color.RED,Color.BLACK};
        for (int i =0;i<2;i++){
            JButton boton = new JButton();
            boton.setBackground(colores[i]);
            boton.setBounds(xcolor,ycolor,widthcolor,heightcolor);
            boton.setEnabled(true);
            final int numero = i;
            boton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    funcioncasillacolor(numero);
                }
            });
            xcolor+=widthcolor;
            botoncolor[i]=boton;
            panelVerde.add(botoncolor[i]);
        }
        //mitad
        int xmitad = 560; 
        int ymitad = 390;
        int widthmitad = 140; 
        int heightmitad = 50;
        String []mitades = {"1-18","19-36"};
        for (int i =0;i<2;i++){
            botonmitad[i]=new JButton(mitades[i]);
            botonmitad[i].setBounds(xmitad,ymitad,widthmitad,heightmitad);
            botonmitad[i].setEnabled(true);
            final int numero = i;
            botonmitad[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    funcioncasillamitad(numero);
                }
            });
            xmitad+=widthmitad;
            panelVerde.add(botonmitad[i]);
        }
        //fila
        int xfila = 1120; 
        int yfila = 100;
        int widthfila = 60; 
        int heightfila = 80;
        for (int i =0;i<3;i++){
            JButton boton = new JButton("2:1");
            boton.setBounds(xfila,yfila,widthfila,heightfila);
            boton.setEnabled(true);
            final int numero = i;
            boton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    funcioncasillafila(numero);
                }
            });
            yfila+=heightfila;
            botonfila[i]=boton;
            panelVerde.add(botonfila[i]);
        }
        //par
        int xpar = 840; 
        ymitad = 390;
        widthmitad = 140; 
        heightmitad = 50;
        String []pares = {"PAR","IMPAR"};
        for (int i =0;i<2;i++){
            JButton boton = new JButton(pares[i]);
            boton.setBounds(xpar,ymitad,widthmitad,heightmitad);
            boton.setEnabled(true);
            final int numero = i;
            boton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    funcioncasillapar(numero);
                }
            });
            xpar+=widthmitad;
            botonpar[i]=boton;
            panelVerde.add(botonpar[i]);
        }
        //fichas
        int xFicha = 300; // Posición inicial en x de las fichas
        int yFicha = 550; // Posición en y de las fichas
        int fichaWidth = 60; // Ancho de las fichas
        int fichaHeight = 60; // Alto de las fichas
        botonesFichas=new JButton[8];
        for (int i = 0; i < valoresFichas.length; i++) {
            JButton ficha = new JButton(new ImageIcon("src/" + (i + 1) + ".png"));
            ficha.setBounds(xFicha, yFicha, fichaWidth, fichaHeight);
            ficha.setBackground(null);
            ficha.setBorderPainted(false);
            ficha.setContentAreaFilled(false); // Establecer el fondo del botón como transparente
            final int valorFicha = valoresFichas[i];
            ficha.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    funcionfichas(valorFicha);
                }
            });
            botonesFichas[i]=ficha;
            panelVerde.add(botonesFichas[i]);
            xFicha += fichaWidth + 30; // Espacio entre cada ficha
        }
            ImageIcon Girar = new ImageIcon("src/girar.png");
            girar = new JButton(Girar);
            girar.setEnabled(false);
            girar.setBounds(1050, 500, 200, 150);
            girar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ruleta();
                }
            });
            panelVerde.add(girar);

            reiniciar=new JButton("Reiniciar");
            reiniciar.setBounds(1150, 420, 100, 30);
            reiniciar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    funcionreiniciar();
                }
            });
            panelVerde.add(reiniciar);
    }

    public void checkbox() {
        confirmacion = new JCheckBox("Confirmar apuesta");
        int x = girar.getX(); // Obtener la posición x del botón girar
        int y = girar.getY() - 50; // Obtener la posición y del botón girar y restarle una distancia para colocar el JCheckBox arriba
        confirmacion.setBounds(x, y, 200, 50); // Ajustar las coordenadas para que esté arriba del botón girar
        confirmacion.setBackground(null);
        confirmacion.setBorderPainted(false);
        //confirmacion.setContentAreaFilled(false);
        confirmacion.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                funcioncheck();
            }
        });
        panelVerde.add(confirmacion);
    }

    public void labels(String usuario) {
        JLabel labelusuario = new JLabel(usuario);
        labelusuario.setForeground(Color.white);
        labelusuario.setBounds(1000,10,500,50);
        panelVerde.add(labelusuario);

        labeldinero = new JLabel("Dinero actual: "+dinero);
        labeldinero.setForeground(Color.white);
        labeldinero.setBounds(1000,20,500,50);
        panelVerde.add(labeldinero);

        labelapuesta = new JLabel("Ficha seleccionada: "+apuesta);
        labelapuesta.setForeground(Color.white);
        labelapuesta.setBounds(1000,30,500,50);
        panelVerde.add(labelapuesta);

        labelsCasillas = new JLabel[37]; // Hay 37 casillas del 0 al 36
        int x = 30;
        int y = 30;
        for (int i = 0; i <= 36; i++) {
            JLabel labelCasilla = new JLabel("Apuesta en " + i + " es: "+fichasIndividuales[i]);
            labelCasilla.setBounds(x, y, 300, 15);
            labelCasilla.setForeground(Color.WHITE);
            labelsCasillas[i] = labelCasilla;
            labelsCasillas[i].setFont(new Font("Arial", Font.PLAIN, 10));
            panelVerde.add(labelsCasillas[i]);
            // Actualiza las posiciones para el próximo label
            y+=10;
        }
        labelsdocena = new JLabel[3];
        for(int i = 0; i <3; i++){
            JLabel labeldocena = new JLabel("Apuesta en la docena " + (i+1) + " es: " + docena[i]);
            labeldocena.setBounds(x, y, 300, 15);
            labelsdocena[i] = labeldocena;
            labelsdocena[i].setFont(new Font("Arial", Font.PLAIN, 10));
            labelsdocena[i].setForeground(Color.WHITE);
            panelVerde.add(labelsdocena[i]);
            y+=10;
        }
        for(int i = 0; i <2; i++){
            String coloractual;
            if (i ==0){
                coloractual = "rojo";
            }
            else{
                coloractual = "negro";
            }
            labelcolor[i] = new JLabel("Apuesta en el color " + coloractual + " es: " + color[i]);
            labelcolor[i].setBounds(x, y, 300, 15);
            labelcolor[i].setFont(new Font("Arial", Font.PLAIN, 10));
            labelcolor[i].setForeground(Color.WHITE);
            panelVerde.add(labelcolor[i]);
            y+=10;
        }
        for(int i = 0; i <3; i++){
            labelfila[i] = new JLabel("Apuesta en la fila " + (i+1)+ " es: " + fila[i]);
            labelfila[i].setBounds(x, y, 300, 15);
            labelfila[i].setFont(new Font("Arial", Font.PLAIN, 10));
            labelfila[i].setForeground(Color.WHITE);
            panelVerde.add(labelfila[i]);
            y+=10;
        }
        for(int i = 0; i <2; i++){
            labelmitad[i] = new JLabel("Apuesta en la mitad " + (i+1) + " es: " + mitad[i]);
            labelmitad[i].setBounds(x, y, 300, 15);
            labelmitad[i].setFont(new Font("Arial", Font.PLAIN, 10));
            labelmitad[i].setForeground(Color.WHITE);
            panelVerde.add(labelmitad[i]);
            y+=10;
        }
        for(int i = 0; i <2; i++){
            String paractual;
            if (i ==0){
                paractual = "pares";
            }
            else{
                paractual = "impares";
            }
            labelpar[i] = new JLabel("Apuesta en los " + paractual + " es: " + par[i]);
            labelpar[i].setBounds(x, y, 300, 15);
            labelpar[i].setFont(new Font("Arial", Font.PLAIN, 10));
            labelpar[i].setForeground(Color.WHITE);
            panelVerde.add(labelpar[i]);
            y+=10;
        }
    }

    public static void main(String[] args) {
        String usuario = "Hola Mundo!";
        Ventana v1 = new Ventana(usuario);
        v1.setVisible(true);
    }
}