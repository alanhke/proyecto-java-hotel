package HotelProyectoFinal.vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaModificarUsuario extends JPanel {
    JTextField tfUserName;
    JTextField tfName;
    JTextField tfLastName;
    JPasswordField tfContra;
    JPasswordField tfFinalPassword;
    ButtonGroup bgGender;
    JButton btnOk;
    JButton btnCancel;
    JRadioButton rbGender1;
    JRadioButton rbGender2;
    JRadioButton rbGender3;
    JComboBox<String> Opciones;
    public VistaModificarUsuario() {
        setLayout(null);
        btnOk = new JButton("Aceptar");
        btnCancel = new JButton("Cancelar");
        JLabel lblFirstName = new JLabel("Nombre(s): ");
        JLabel lblLastName = new JLabel("Apellido(s): ");
        JLabel lblName = new JLabel("Nombre de usuario: ");
        JLabel lblContra = new JLabel("Contraseña: ");
        JLabel lblFinalPassword = new JLabel("Confirmar contraseña: ");

        tfUserName = new JTextField(10);
        tfName = new JTextField(10);
        tfLastName = new JTextField(10);
        tfContra = new JPasswordField(10);
        tfFinalPassword = new JPasswordField(10);

        String[] Opcion = {"Personal","Empresa"};
        Opciones = new JComboBox<String>(Opcion);
        rbGender1 = new JRadioButton("Mujer");
        rbGender2 = new JRadioButton("Hombre");
        rbGender3 = new JRadioButton("Otro");
        bgGender = new ButtonGroup();
        bgGender.add(rbGender1);
        bgGender.add(rbGender2);
        bgGender.add(rbGender3);

        lblFirstName.setBounds(10, 10, 200, 30);
        lblLastName.setBounds(10, 40, 200, 30);
        tfName.setBounds(80, 10, 200, 30);
        tfLastName.setBounds(80, 40, 200, 30);
        add(lblFirstName);
        add(lblLastName);
        add(tfName);
        add(tfLastName);

        lblName.setBounds(10,70,200,30);
        add(lblName);
        tfUserName.setBounds(135,70,200,30);
        add(tfUserName);

        lblContra.setBounds(10,100,200,30);
        add(lblContra);
        tfContra.setBounds(90,100,200,30);
        add(tfContra);
        lblFinalPassword.setBounds(10,130,200,30);
        add(lblFinalPassword);
        tfFinalPassword.setBounds(150,130,200,30);
        add(tfFinalPassword);

        rbGender1.setBounds(215,160,200,30);
        rbGender2.setBounds(215,180,200,30);
        rbGender3.setBounds(215,200,200,30);
        add(rbGender1);
        add(rbGender2);
        add(rbGender3);

        Opciones.setBounds(300,160,200,30);
        add(Opciones);

        btnOk.setBounds(5,250,100,30);
        btnOk.setBorder(BorderFactory.createDashedBorder(Color.black,1,5,1,true));
        add(btnOk);
        btnCancel.setBounds(110,250,100,30);
        btnCancel.setBorder(BorderFactory.createDashedBorder(Color.black,1,5,1,true));
        add(btnCancel);
    }
    public void setListeners(ActionListener listener){
        btnOk.addActionListener(listener);
        btnCancel.addActionListener(listener);
    }

    public String getBotonPresionado(JButton boton) {
        if (boton == btnOk) {
            return "Aceptar";
        }else if (boton == btnCancel) {
            return "Cancelar";
        }
        return null;
    }

    public String getNombre(){
        return tfName.getText();
    }
    public String getApellido(){
        return tfLastName.getText();
    }
    public String getNombreUsuario(){
        return tfUserName.getText();
    }
    public String getPassword(){
        return new String(tfContra.getPassword());
    }
    public String getFinalPassword(){
        return tfFinalPassword.getText();
    }
    public String getGender(){
        if (rbGender1.isSelected()){
            return "Mujer";
        }else if (rbGender2.isSelected()){
            return "Hombre";
        }else if (rbGender3.isSelected()){
            return "Otro";
        }
        return "";
    }
    public String getOpcion(){
        return (String) Opciones.getSelectedItem();
    }

    public JRadioButton getGender1(){
        return rbGender1;
    }
    public JRadioButton getGender2(){
        return rbGender2;
    }
    public JRadioButton getGender3(){
        return rbGender3;
    }
    public JTextField getTfUserName(){
        return tfUserName;
    }
    public JTextField getTfName(){
        return tfName;
    }
    public JTextField getTfLastName(){
        return tfLastName;
    }
    public JTextField getTfFinalPassword(){
        return tfFinalPassword;
    }
    public JTextField getTfContra(){
        return tfContra;
    }

    public String getTextVacio(String nombre, String contra, String finalPassword, String firstName, String lastName) {
        if (nombre.isBlank()){
            return "Nombre de usuario";
        }else if(contra.isBlank()){
            return "Contra";
        }else if(finalPassword.isBlank()){
            return "Confirmar contraseña";
        }else if(firstName.isBlank()){
            return "Nombres";
        }else if(lastName.isBlank()){
            return "Apellidos";
        } else if (!rbGender1.isSelected() && !rbGender2.isSelected() && !rbGender3.isSelected()) {
            return "Genero";
        }
        return null;
    }

    public void setNombreUsuario(String tfUserName) {
        this.tfUserName.setText(tfUserName);
    }

    public void setNombre(String tfName) {
        this.tfName.setText(tfName);
    }

    public void setApellido(String tfLastName) {
        this.tfLastName.setText(tfLastName);
    }

    public void setPassword(String tfContra) {
        this.tfContra.setText(tfContra);
    }

    public void setFinalPassword(String tfFinalPassword) {
        this.tfFinalPassword.setText(tfFinalPassword);
    }


    public void setBgGender(String bgGender) {
        if (bgGender.equals("Mujer")){
            this.rbGender1.setSelected(true);
        }else if (bgGender.equals("Hombre")){
            this.rbGender2.setSelected(true);
        }else if (bgGender.equals("Otro")){
            this.rbGender3.setSelected(true);
        }
    }

    public void setOpcion(String opcion) {
        if (opcion.equals("Personal")){
            this.Opciones.setSelectedItem("Personal");
        }else if (opcion.equals("Empresa")){
            this.Opciones.setSelectedItem("Empresa");
        }
    }
}
