import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Interfaz{

    private JTabbedPane tabbedPane1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton ingresarUsuarioButton;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JButton ingresarViviendaButton;
    private JTextField textField8;
    private JTextField textField9;
    private JButton ingresarButton;
    private JButton verDatosButton;
    private JTextArea textArea1;
    private JTextField textField10;
    private JButton entrarAlSistemaButton;
    private JPanel panel;
    private JTextField textField11;
    private JTextField textField12;
    private JTextField textField13;
    private JTextField textField14;
    private JButton ingresarPorViviendaButton;
    private JTextArea textArea2;
    private JButton imprimirFacturaButton;
    private JTextField textField15;
    private JButton eliminarButton;
    private JTextField textField16;
    private JTextField textField17;
    private JTextField textField18;
    private JTextField textField19;
    private JTextField textField20;
    private JButton actualizarButton;
    private static ArrayList<Usuario> usuarios = new ArrayList<>();
    private static ArrayList<Electrodomestico> electrodomesticos = new ArrayList<>();
    private static ArrayList<Vivienda> viviendas = new ArrayList<>();
    private static Fecha fecha1 = new Fecha();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Interfaz");
        frame.setContentPane(new Interfaz().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public Interfaz() {
    ingresarUsuarioButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                validarCedula(textField2.getText());
                if(usuarios.isEmpty()){
                    Usuario usuario = new Usuario(textField1.getText(), textField3.getText(),textField2.getText());
                    usuarios.add(usuario);
                    JOptionPane.showMessageDialog(null, "El usuario ha sido registrado");
                }else{
                    for (Usuario user : usuarios) {
                        if (user.getCedula().equals(textField2.getText())) {
                            JOptionPane.showMessageDialog(null,"Ya existe un usuario con esa cédula." );
                        }else{
                            Usuario usuario = new Usuario(textField1.getText(), textField3.getText(),textField2.getText());
                            usuarios.add(usuario);
                            JOptionPane.showMessageDialog(null, "El usuario ha sido registrado");
                        }
                    }
                }
            }catch (Exception ex){
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }

        }
    });
    ingresarViviendaButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Vivienda vivienda = new Vivienda(textField4.getText(), textField5.getText(), textField6.getText(), textField7.getText());
            for(Usuario u :usuarios){
                if(u.getCedula().equals(textField10.getText())){
                    u.agregarVivienda(vivienda);
                }
            }
           JOptionPane.showMessageDialog(null, "La vivienda ha sido registrada");
        }
    });
    ingresarButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(electrodomesticos.isEmpty()){
                Electrodomestico nuevoElectrodomestico = new Electrodomestico(textField8.getText(), Integer.parseInt(textField9.getText()));
                electrodomesticos.add(nuevoElectrodomestico);
                JOptionPane.showMessageDialog(null, "El electrodomestico ha sido ingresado");
            }else{
                for(Electrodomestico electrodomestico: electrodomesticos){
                    if(electrodomestico.getNombre().equals(textField8.getText())){
                        JOptionPane.showMessageDialog(null, "Electrodomestico con el mismo nombre ya ingresado");
                    }else{
                        Electrodomestico nuevoElectrodomestico = new Electrodomestico(textField8.getText(), Integer.parseInt(textField9.getText()));
                        electrodomesticos.add(nuevoElectrodomestico);
                        JOptionPane.showMessageDialog(null, "El electrodomestico ha sido ingresado");
                    }
                }
            }
        }
    });
    verDatosButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                textArea1.setText(imprimirInfoU(textField10.getText()));
            }catch (Exception ex){
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    });
    entrarAlSistemaButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                validarCedula(textField10.getText());
                buscarUsuario(textField10.getText());
            }catch (Exception ex){
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }

        }
    });
        ingresarPorViviendaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               for(Usuario u: usuarios){
                   if(u.getCedula().equals(textField10.getText())){
                       for (int i = 0; i < electrodomesticos.size(); i++) {
                           System.out.println((i + 1) + ") " + electrodomesticos.get(i).getNombre());
                       }
                       int opcion = Integer.parseInt(textField11.getText());
                       if (opcion > 0 && opcion <= electrodomesticos.size()) {
                           Electrodomestico electrodomestico = electrodomesticos.get(opcion - 1);
                           for (int i = 0; i < u.getViviendas().size(); i++) {
                               System.out.println((i + 1) + ") " + u.getViviendas().get(i).getSector());
                           }
                           int opcionVivienda = Integer.parseInt(textField12.getText());
                           if (opcionVivienda > 0 && opcionVivienda <= u.getViviendas().size()) {
                               Vivienda vivienda = u.getViviendas().get(opcionVivienda - 1);
                               int cantidad = Integer.parseInt(textField13.getText());
                               double tiempo = Integer.parseInt(textField14.getText());
                               Consumo nuevoConsumo = new Consumo(electrodomestico, cantidad, tiempo);
                               vivienda.agregarConsumo(nuevoConsumo);
                               JOptionPane.showMessageDialog(null, "Agregado");
                           } else {
                               JOptionPane.showMessageDialog(null, "Opción de vivienda no válida");
                           }
                       } else {
                           JOptionPane.showMessageDialog(null, "Opción de electrodomestico no válida");
                       }
                   }
               }
            }
        });
        imprimirFacturaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(Usuario u : usuarios){
                    if(u.getCedula().equals(textField10.getText())){
                        try{
                            textArea2.setText(imprimirInfo(textField10.getText()));
                        }catch (Exception ex){
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        }

                    }
                }
            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(Usuario u : usuarios){
                    if(u.getCedula().equals(textField10.getText())){
                        for (int i = 0; i < u.getViviendas().size(); i++) {
                            System.out.println((i + 1) + ") " + u.getViviendas().get(i).getSector());
                        }
                        int opcionVivienda = Integer.parseInt(textField15.getText());
                        if (opcionVivienda > 0 && opcionVivienda <= u.getViviendas().size()) {
                            u.getViviendas().remove(opcionVivienda - 1);
                            JOptionPane.showMessageDialog(null, "Vivienda eliminada");

                        } else {
                            JOptionPane.showMessageDialog(null, "Error");

                        }
                    }
                }
            }
        });
        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for ( Usuario u: usuarios){
                    if(u.getCedula().equals(textField10.getText())){
                        for (int i = 0; i < u.getViviendas().size(); i++) {
                            System.out.println((i + 1) + ") " + u.getViviendas().get(i).getSector());
                        }
                        int opcionVivienda = Integer.parseInt(textField16.getText());
                        if (opcionVivienda > 0 && opcionVivienda <= u.getViviendas().size()) {
                            Vivienda vivienda = u.getViviendas().get(opcionVivienda - 1);
                            vivienda.setSector(textField17.getText());
                            vivienda.setCallePrincipal(textField18.getText());
                            vivienda.setCalleSecundaria(textField19.getText());
                            vivienda.setCodigoPostal(textField20.getText());
                            JOptionPane.showMessageDialog(null, "datos actualizados con exito");
                        } else {
                            JOptionPane.showMessageDialog(null, "error en actualizar datos");
                        }
                    }
                }
            }
        });
        textField2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char inputChar=e.getKeyChar();
                if(Character.isLetter(inputChar)){
                    e.consume();
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        });
        textField10.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char inputChar=e.getKeyChar();
                if(Character.isLetter(inputChar)){
                    e.consume();
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        });
        textField20.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char inputChar=e.getKeyChar();
                if(Character.isLetter(inputChar)){
                    e.consume();
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        });
        textField9.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char inputChar=e.getKeyChar();
                if(Character.isLetter(inputChar)){
                    e.consume();
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        });
        textField11.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char inputChar=e.getKeyChar();
                if(Character.isLetter(inputChar)){
                    e.consume();
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        });
        textField12.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char inputChar=e.getKeyChar();
                if(Character.isLetter(inputChar)){
                    e.consume();
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        });
        textField13.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char inputChar=e.getKeyChar();
                if(Character.isLetter(inputChar)){
                    e.consume();
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        });
        textField14.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char inputChar=e.getKeyChar();
                if(Character.isLetter(inputChar)){
                    e.consume();
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        });
        textField7.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char inputChar=e.getKeyChar();
                if(Character.isLetter(inputChar)){
                    e.consume();
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        });
        textField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char inputChar=e.getKeyChar();
                if(Character.isDigit(inputChar)){
                    e.consume();
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        });
        textField3.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char inputChar=e.getKeyChar();
                if(Character.isDigit(inputChar)){
                    e.consume();
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        });
        textField15.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char inputChar=e.getKeyChar();
                if(Character.isLetter(inputChar)){
                    e.consume();
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        });
        textField16.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char inputChar=e.getKeyChar();
                if(Character.isLetter(inputChar)){
                    e.consume();
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        });

    }
    private static Usuario buscarUsuario(String cedula) {
        for (Usuario usuario : usuarios) {
            if (usuario.getCedula().equals(cedula)) {
                JOptionPane.showMessageDialog(null, "Bienvenido"+ usuario.getNombre() + " " + usuario.getApellido());
                return usuario;
            }
        }
        return null;
    }

    private static String imprimirInfo(String cedula)throws Exception{
        for (Usuario u : usuarios){
            if(u.getCedula().equals(cedula)){
                for(Vivienda v: u.getViviendas()){
                    for(Consumo c : v.getConsumos()){
                        Electrodomestico electrodomestico = c.getElectrodomestico();
                        Factura factura = u.generarFactura();
                        return u.toString()+"\n"+v.toString()+"\n"+electrodomestico.getNombre()+" "+ electrodomestico.getConsumoEnWatts()+"\n"+"Watts: "+factura.toString()+"\n"+"Fecha: "+fecha1.toString();
                    }
                }
            }
        }
        throw new Exception("Error");
    }


      private static String imprimirInfoU(String cedula)throws Exception{
        String msj = "";
        for (Usuario u : usuarios){
            if(u.getCedula().equals(cedula)){
                for(Vivienda v: u.getViviendas()){
                    for(Consumo c : v.getConsumos()){
                        Electrodomestico electrodomestico = c.getElectrodomestico();
                        return msj+= u.toString()+"\n"+v.toString()+"\n"+electrodomestico.getNombre();
                    }
                }
            }
        }
        throw new Exception("Error");
    }
    public static  boolean validarCedula(String cedula) throws Exception {
        if (!cedula.matches("\\d{10}")) {
            throw new Exception("Su cédula no ha sido ingresada correctamente, ingrese de nuevo");
        }
        int suma = 0;
        for (int i = 0; i < 9; i++) {
            int digito = Character.getNumericValue(cedula.charAt(i));
            digito *= (i % 2 == 0) ? 2 : 1;
            if (digito > 9) {
                digito -= 9;
            }
            suma += digito;
        }

        int verificador = Character.getNumericValue(cedula.charAt(9));
        int resultado = 10 - (suma % 10);

        if (resultado != verificador) {
            throw new Exception("Su cédula no ha sido ingresada correctamente, ingrese de nuevo");
        }

        return true;
    }
}
