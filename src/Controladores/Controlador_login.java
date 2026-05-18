package Controladores;

import javax.swing.*;

public class Controlador_login implements IValidacion {

    // Componentes recibidos de la vista
    private JTextField login_usuario;
    private JTextField login_contraseña;
    private JCheckBox chkTecnico;
    private JCheckBox chkUsuario;
    private JCheckBox chkAdmin;
    private JFrame ventana;

    // Constructor que recibe los componentes - tema nuevo POO
    public Controlador_login(JTextField login_usuario, JTextField login_contraseña,
            JCheckBox chkTecnico, JCheckBox chkUsuario, JCheckBox chkAdmin, JFrame ventana) {
        this.login_usuario = login_usuario;
        this.login_contraseña = login_contraseña;
        this.chkTecnico = chkTecnico;
        this.chkUsuario = chkUsuario;
        this.chkAdmin = chkAdmin;
        this.ventana = ventana;
    }

    // Metodo principal del boton Ingresar
    public void ingresar() {

        String email = login_usuario.getText().trim();
        String contraseña = login_contraseña.getText().trim();

        // Validacion campos vacios
        if (!validarCamposVacios()) {
            return;
        }

        // Validacion checkbox - igual que tu compañero
        if (!chkUsuario.isSelected() && !chkAdmin.isSelected() && !chkTecnico.isSelected()) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un tipo de usuario para ingresar");
            return;
        }

        // Busca en los arreglos - llama al validarLogin del ControladorRegistro
        String rolEncontrado = Controlador_registro.validarLogin(email, contraseña);

        if (rolEncontrado == null) {
            JOptionPane.showMessageDialog(null, "Correo o contraseña incorrectos");
            return;
        }

        // Polimorfismo - redirige segun el rol, igual que tu compañero pero con Tecnico
        redirigirPorRol(rolEncontrado);
    }

    // Polimorfismo - comportamiento distinto segun el rol
    private void redirigirPorRol(String rolEncontrado) {
    if (chkUsuario.isSelected() && rolEncontrado.equals("Usuario")) {
        Modelos.Usuarios.correoUsuarioActual = login_usuario.getText().trim();
        Vistas.Usuario vista = new Vistas.Usuario();
        vista.setVisible(true);
        vista.setLocationRelativeTo(null);
        ventana.dispose();

    } else if (chkAdmin.isSelected() && rolEncontrado.equals("Admin")) {
        Modelos.Usuarios.correoUsuarioActual = login_usuario.getText().trim();
        Vistas.vista_admin vista = new Vistas.vista_admin();
        vista.setVisible(true);
        vista.setLocationRelativeTo(null);
        ventana.dispose();

    } else if (chkTecnico.isSelected() && rolEncontrado.equals("Tecnico")) {
        Modelos.Usuarios.correoUsuarioActual = login_usuario.getText().trim();
        Vistas.Tecnico_2 vista = new Vistas.Tecnico_2();
        vista.setVisible(true);
        vista.setLocationRelativeTo(null);
        ventana.dispose();

    } else {
        JOptionPane.showMessageDialog(null,
            "El rol seleccionado no coincide con sus credenciales.\nUsted esta registrado como: " + rolEncontrado);
    }
}

    // Override - Interface IValidacion
    @Override
    public boolean validarCamposVacios() {
        if (login_usuario.getText().trim().isEmpty()) {
            mostrarMensaje("El campo Usuario no puede estar vacio.");
            return false;
        }
        if (login_contraseña.getText().trim().isEmpty()) {
            mostrarMensaje("El campo Contraseña no puede estar vacio.");
            return false;
        }
        return true;
    }

    // Override - Interface IValidacion
    @Override
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "AirService-Pro", JOptionPane.WARNING_MESSAGE);
    }

    // Sobrecarga de mostrarMensaje - tema nuevo POO
    public void mostrarMensaje(String mensaje, String titulo) {
        JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
    }

    // Override - Interface IValidacion
    @Override
    public void limpiarCampos() {
        login_usuario.setText("");
        login_contraseña.setText("");
        chkTecnico.setSelected(false);
        chkUsuario.setSelected(false);
        chkAdmin.setSelected(false);
    }
}
