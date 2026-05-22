package Controladores;

import javax.swing.*;
import Modelos.Usuarios;
import Modelos.Tecnico;

public class Controlador_registro implements IValidacion {

    public static String[] admins = new String[50];
    public static String[] tecnicos = new String[50];
    public static String[] usuarios = new String[50];

    public static int contadorAdmins = 0;
    public static int contadorTecnicos = 0;
    public static int contadorUsuarios = 0;

    public static String emailUsuarioActual = "";

    public static void registrarUsuario(JTextField nombre_completo, JTextField correo,
            JTextField telefono_1, JTextField contraseña,
            JComboBox<String> roles, JTextField ciudad_r) {

        String nombre = nombre_completo.getText().trim();
        String email = correo.getText().trim();
        String password = contraseña.getText().trim();
        String rol = (String) roles.getSelectedItem();
        String numero = telefono_1.getText().trim();
        String ciudad = ciudad_r.getText().trim();

        if (nombre.isEmpty() || email.isEmpty() || password.isEmpty() || numero.isEmpty() || ciudad.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Por favor, complete todos los campos",
                    "Campos Vacios",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (rol == null || rol.equals("Seleccionar") || rol.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Por favor, seleccione un rol valido",
                    "Rol No Seleccionado",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!email.contains("@") || !email.contains(".")) {
            JOptionPane.showMessageDialog(null,
                    "El correo no tiene un formato valido.\nEjemplo: usuario@correo.com",
                    "Correo Invalido",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!numero.matches("[0-9]+") || numero.length() != 10) {
            JOptionPane.showMessageDialog(null,
                    "El numero telefonico solo debe contener 10 digitos.",
                    "Numero Invalido",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (password.length() < 6) {
            JOptionPane.showMessageDialog(null,
                    "La contraseña debe tener minimo 6 caracteres.",
                    "Contraseña Invalida",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (usuarioExiste(email)) {
            JOptionPane.showMessageDialog(null,
                    "El email ya esta registrado",
                    "Usuario Existente",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (numeroExiste(numero)) {
            JOptionPane.showMessageDialog(null,
                    "El numero ya esta registrado",
                    "Numero Existente",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        Usuarios nuevoUsuario = new Usuarios(email, password, ciudad, numero, rol, nombre);

        String datosUsuario = nuevoUsuario.toString();

        boolean registroExitoso = false;

        switch (rol) {
            case "Admin" -> {
                if (contadorAdmins < admins.length) {
                    admins[contadorAdmins] = datosUsuario;
                    contadorAdmins++;
                    registroExitoso = true;
                    mostrarMensajeExito("Administrador");
                } else {
                    mostrarMensajeCapacidad();
                }
            }

            case "Tecnico" -> {
                if (contadorTecnicos < tecnicos.length) {
                    tecnicos[contadorTecnicos] = datosUsuario;
                    contadorTecnicos++;
                    registroExitoso = true;
                    mostrarMensajeExito("Tecnico");
                    
                    Tecnico nuevoTecnico = new Tecnico(nombre, email, numero, ciudad, password);
                    Tecnico.tecnicos[Tecnico.contadorTecnicos++] = nuevoTecnico;
                }
                break;
            }

            case "Usuario" -> {
                if (contadorUsuarios < usuarios.length) {
                    usuarios[contadorUsuarios] = datosUsuario;
                    contadorUsuarios++;
                    registroExitoso = true;
                    mostrarMensajeExito("Usuario");
                } else {
                    mostrarMensajeCapacidad();
                }
            }
        }

        if (registroExitoso) {
            limpiarCampos(nombre_completo, correo,
                    contraseña, roles,
                    telefono_1, ciudad_r);
        }
    }

    public static boolean usuarioExiste(String email) {
        for (int i = 0; i < contadorAdmins; i++) {
            String[] datos = admins[i].split("\\|");
            if (datos[1].equals(email)) {
                return true;
            }
        }
        for (int i = 0; i < contadorTecnicos; i++) {
            String[] datos = tecnicos[i].split("\\|");
            if (datos[1].equals(email)) {
                return true;
            }
        }
        for (int i = 0; i < contadorUsuarios; i++) {
            String[] datos = usuarios[i].split("\\|");
            if (datos[1].equals(email)) {
                return true;
            }
        }
        return false;
    }

    public static boolean numeroExiste(String numero) {
        for (int i = 0; i < contadorAdmins; i++) {
            String[] datos = admins[i].split("\\|");
            if (datos[3].equals(numero)) {
                return true;
            }
        }
        for (int i = 0; i < contadorTecnicos; i++) {
            String[] datos = tecnicos[i].split("\\|");
            if (datos[3].equals(numero)) {
                return true;
            }
        }
        for (int i = 0; i < contadorUsuarios; i++) {
            String[] datos = usuarios[i].split("\\|");
            if (datos[3].equals(numero)) {
                return true;
            }
        }
        return false;
    }

    public static void mostrarMensajeExito(String rol) {
        JOptionPane.showMessageDialog(null,
                rol + " registrado exitosamente",
                "Registro Exitoso",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void mostrarMensajeCapacidad() {
        JOptionPane.showMessageDialog(null,
                "No hay espacio disponible para mas registros de este tipo",
                "Capacidad Maxima",
                JOptionPane.ERROR_MESSAGE);
    }

    public static void limpiarCampos(JTextField txtNombre, JTextField txtEmail,
            JTextField txtPassword, JComboBox<String> comboRol,
            JTextField txtNumero, JTextField txtCiudad) {
        txtNombre.setText("");
        txtEmail.setText("");
        txtPassword.setText("");
        txtNumero.setText("");
        txtCiudad.setText("");
        comboRol.setSelectedIndex(0);
    }

    public static String[] getAdmins() {
        return admins;
    }

    public static String[] getTecnicos() {
        return tecnicos;
    }

    public static String[] getUsuarios() {
        return usuarios;
    }

    public static int getContadorAdmins() {
        return contadorAdmins;
    }

    public static int getContadorTecnicos() {
        return contadorTecnicos;
    }

    public static int getContadorUsuarios() {
        return contadorUsuarios;
    }

    public static String validarLogin(String email, String password) {
        for (int i = 0; i < contadorAdmins; i++) {
            String[] datos = admins[i].split("\\|");
            if (datos[1].equals(email) && datos[2].equals(password)) {
                emailUsuarioActual = email;
                return "Admin";
            }
        }
        for (int i = 0; i < contadorTecnicos; i++) {
            String[] datos = tecnicos[i].split("\\|");
            if (datos[1].equals(email) && datos[2].equals(password)) {
                emailUsuarioActual = email;
                return "Tecnico";
            }
        }
        for (int i = 0; i < contadorUsuarios; i++) {
            String[] datos = usuarios[i].split("\\|");
            if (datos[1].equals(email) && datos[2].equals(password)) {
                emailUsuarioActual = email;
                return "Usuario";
            }
        }
        return null;
    }

    public static void mostrarDatosUsuario(JTextField txtNombre, JTextField txtEmail,
            JTextField txtCiudad, JTextField txtTelefono) {
        for (int i = 0; i < contadorAdmins; i++) {
            String[] datos = admins[i].split("\\|");
            if (datos[1].equals(emailUsuarioActual)) {
                txtNombre.setText(datos[0]);
                txtEmail.setText(datos[1]);
                txtTelefono.setText(datos[3]);
                txtCiudad.setText(datos[4]);
                return;
            }
        }
        for (int i = 0; i < contadorTecnicos; i++) {
            String[] datos = tecnicos[i].split("\\|");
            if (datos[1].equals(emailUsuarioActual)) {
                txtNombre.setText(datos[0]);
                txtEmail.setText(datos[1]);
                txtTelefono.setText(datos[3]);
                txtCiudad.setText(datos[4]);
                return;
            }
        }
        for (int i = 0; i < contadorUsuarios; i++) {
            String[] datos = usuarios[i].split("\\|");
            if (datos[1].equals(emailUsuarioActual)) {
                txtNombre.setText(datos[0]);
                txtEmail.setText(datos[1]);
                txtTelefono.setText(datos[3]);
                txtCiudad.setText(datos[4]);
                return;
            }
        }
    }

    @Override
    public boolean validarCamposVacios() {
        return true;
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "AirService-Pro", JOptionPane.WARNING_MESSAGE);
    }

    public void mostrarMensaje(String mensaje, String titulo) {
        JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
    }
    
    @Override
    public void limpiarCampos() {
    }
}
