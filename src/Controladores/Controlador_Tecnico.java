package Controladores;

import Modelos.Tecnico;
import javax.swing.*;

public class Controlador_Tecnico implements IValidacion {

    private JLabel nombre_tecnico;
    private JLabel rol;
    private JLabel Id;
    private JLabel correo;
    private JLabel numero;

    public Controlador_Tecnico(JLabel nombre_tecnico, JLabel rol, JLabel Id,
            JLabel correo, JLabel numero) {
        this.nombre_tecnico = nombre_tecnico;
        this.rol = rol;
        this.Id = Id;
        this.correo = correo;
        this.numero = numero;
    }

    public void mostrarPerfil() {
        String correoActual = Modelos.Usuarios.correoUsuarioActual;

        for (int i = 0; i < Controlador_registro.contadorTecnicos; i++) {
            String[] datos = Controlador_registro.tecnicos[i].split("\\|");
            if (datos[1].equals(correoActual)) {
                Id.setText(Modelos.Tecnico.tecnicos[i].getId());
                nombre_tecnico.setText(datos[0]);
                correo.setText(datos[1]);
                numero.setText(datos[3]);
                rol.setText("Tecnico");
                return;
            }
        }
        mostrarMensaje("No se encontraron datos del tecnico.");
    }

    public static void cargarConfiguracion(JTextField Nombre_c, JTextField Telefono,
            JTextField Fecha_N, JTextField Especialidad_1, JTextField email,
            JTextField n_documento, JTextField direccion, JTextField año_e,
            JTextField Descripcion_1) {

        String correoActual = Modelos.Usuarios.correoUsuarioActual;

        for (int i = 0; i < Controlador_registro.contadorTecnicos; i++) {
            String[] datos = Controlador_registro.tecnicos[i].split("\\|");
            if (datos[1].equals(correoActual)) {
                Nombre_c.setText(datos[0]);
                email.setText(datos[1]);
                Telefono.setText(datos[3]);
                return;
            }
        }
    }

    public static void guardarCambios(JTextField Nombre_c, JTextField Telefono,
            JTextField Fecha_N, JTextField Especialidad_1, JTextField email,
            JTextField n_documento, JTextField direccion, JTextField año_e, JTextField Descripcion_1,
            JLabel Nombre_Completo, JLabel Fecha_Nacimiento, JLabel Direccion,
            JLabel N_Documento, JLabel Especialidad, JLabel Años_Experiencia,
            JLabel Descripcion) {

        Nombre_Completo.setText(Nombre_c.getText().trim());
        Fecha_Nacimiento.setText(Fecha_N.getText().trim());
        Direccion.setText(direccion.getText().trim());
        N_Documento.setText(n_documento.getText().trim());
        Especialidad.setText(Especialidad_1.getText().trim());
        Años_Experiencia.setText(año_e.getText().trim());
        Descripcion.setText(Descripcion_1.getText().trim());

        JOptionPane.showMessageDialog(null, "Cambios guardados exitosamente.",
                "AirService-Pro", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void cargarClientes(javax.swing.JTable jTable3) {
        javax.swing.table.DefaultTableModel modelo
                = (javax.swing.table.DefaultTableModel) jTable3.getModel();
        modelo.setRowCount(0);

        for (int i = 0; i < Controladores.Controlador_registro.contadorUsuarios; i++) {
            String[] datos = Controladores.Controlador_registro.usuarios[i].split("\\|");
            modelo.addRow(new Object[]{
                datos[0],
                "Usuario",
                datos[1],
                datos[4],
                "N/A",
                "Activo"
            });
        }
    }

    public static Tecnico buscarPorId(String id) {
        for (int i = 0; i < Tecnico.contadorTecnicos; i++) {
            String[] datos = Tecnico.tecnicos[i].toString().split("\\|");
            if (datos[0].equals(id)) {
                return Tecnico.tecnicos[i];
            }
        }
        return null;
    }

    public static Tecnico buscarPorCorreo(String correo) {
        for (int i = 0; i < Tecnico.contadorTecnicos; i++) {
            String[] datos = Tecnico.tecnicos[i].toString().split("\\|");
            if (datos[2].equals(correo)) {
                return Tecnico.tecnicos[i];
            }
        }
        return null;
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
        nombre_tecnico.setText("");
        Id.setText("");
        correo.setText("");
        numero.setText("");
        rol.setText("");
    }
}
