package Controladores;

import Modelos.Tecnicos_admin;
import javax.swing.*;
import javax.swing.table.DefaultTableModel; //  IMPORTANTE para poder manejar las tablas

/**
 * Controlador para la gestión y lógica de negocio del módulo de Técnicos en AirService-Pro.
 * @author EUMAPE
 */
public class Controlador_AdminTecnicos {
    
    // --- MÉTODOS DE LECTURA DE DATOS (ENCAPSULAMIENTO DE MVC) ---

    /**
     * Extrae los técnicos en memoria y los organiza en un DefaultTableModel para el JTable.
     * @return DefaultTableModel con las columnas de Tipo Doc, Número, Nombre y Nivel.
     */
    public static DefaultTableModel listarTecnicos() {
        // 1. Definimos los encabezados de las columnas de la tabla exactamente como los necesitas
        String[] columnas = {"Tipo Documento", "Número Documento", "Nombre", "Nivel"};
        
        // 2. Instanciamos el modelo de la tabla desactivando la edición directa de celdas
        DefaultTableModel modeloTabla = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hace que las celdas sean de solo lectura para el usuario
            }
        };

        // 3. Recorremos el arreglo estático de técnicos usando el contador de su modelo
        for (int i = 0; i < Tecnicos_admin.contadorTecnicos; i++) {
            if (Tecnicos_admin.tecnicos[i] != null) {
                
                // 4. Creamos una fila de objetos para vaciar los atributos del técnico actual
                Object[] fila = new Object[4];
                fila[0] = Tecnicos_admin.tecnicos[i].getTipoDocumento(); 
                fila[1] = Tecnicos_admin.tecnicos[i].getDocumento();     
                fila[2] = Tecnicos_admin.tecnicos[i].getNombre();        
                fila[3] = Tecnicos_admin.tecnicos[i].getNivel();         

                // 5. Agregamos la fila armada al modelo contenedor
                modeloTabla.addRow(fila);
            }
        }
        
        // Retornamos el modelo lleno y listo a la Vista
        return modeloTabla;
    }
    
    // --- MÉTODOS DE VALIDACIÓN DE FORMATO (EXPRESIONES REGULARES) ---
    
    private static boolean validarNombre(String nombre) {
        return controlador_servicio.validarNombreFormato(nombre);
    }

    private static boolean validarTelefono(String telefono) {
        return controlador_servicio.validarTelefonoFormato(telefono);
    }

    private static boolean validarDocumento(String documento) {
        // Valida que el documento contenga únicamente números y tenga entre 6 y 12 dígitos
        return documento.matches("\\d{6,12}");
    }

    // --- LÓGICA DE NEGOCIO: BUSCAR TÉCNICO POR DOCUMENTO (R - READ) ---
    
    public static Tecnicos_admin buscarTecnicoPorDocumento(String documento) {
        if (documento == null || documento.trim().isEmpty()) {
            return null;
        }
        
        // Recorremos el arreglo estático del modelo Tecnicos_admin
        for (int i = 0; i < Tecnicos_admin.contadorTecnicos; i++) {
            // Evaluamos directamente usando POO y los getters del objeto limpio
            if (Tecnicos_admin.tecnicos[i] != null && Tecnicos_admin.tecnicos[i].getDocumento().equals(documento.trim())) {
                return Tecnicos_admin.tecnicos[i]; // Retorna el objeto técnico completo
            }
        }
        return null; // Si no encuentra concordancia
    }

    // --- LÓGICA DE NEGOCIO: REGISTRAR NUEVO TÉCNICO (C - CREATE) ---

    public static boolean registrarNuevoTecnico(String nombre, String correo, String telefono, String documento, String ciudad, 
                                                String nivel, String tipoDocumento, String contra, String repetirContra) {
        
        // 1. Validar campos vacíos
        if (nombre.isEmpty() || correo.isEmpty() || telefono.isEmpty() || documento.isEmpty() 
            || ciudad.isEmpty() || contra.isEmpty() || repetirContra.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, llene todos los campos obligatorios.", "AirService-Pro", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // 2. Validar que los JComboBox no tengan la opción por defecto
        if (nivel.contains("Seleccione") || tipoDocumento.contains("Seleccione")) {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione un tipo de documento y nivel válidos.", "AirService-Pro", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // 3. Validar que las contraseñas coincidan
        if (!contra.equals(repetirContra)) {
            JOptionPane.showMessageDialog(null, "Las contraseñas ingresadas no coinciden. Por favor, verifique.", "AirService-Pro", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // 4. Validar estructura básica del correo electrónico
        if (!correo.contains("@") || !correo.contains(".")) {
            JOptionPane.showMessageDialog(null, "El formato del correo electrónico es inválido (ejemplo@correo.com).", "AirService-Pro", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // 5. Validar formatos de texto con Expresiones Regulares
        if (!validarNombre(nombre)) {
            JOptionPane.showMessageDialog(null, "El nombre no puede contener números ni caracteres especiales.", "AirService-Pro", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (!validarDocumento(documento)) {
            JOptionPane.showMessageDialog(null, "El documento debe contener únicamente números (entre 6 y 12 dígitos).", "AirService-Pro", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (!validarTelefono(telefono)) {
            JOptionPane.showMessageDialog(null, "El teléfono debe contener únicamente números (7-12 dígitos).", "AirService-Pro", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // 6. Validar Cédula Duplicada para evitar registros repetidos
        if (buscarTecnicoPorDocumento(documento) != null) {
            JOptionPane.showMessageDialog(null, "Error: Ya existe un técnico registrado con este número de documento.", "AirService-Pro", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // 7. Validar límite físico del arreglo estático
        if (Tecnicos_admin.contadorTecnicos >= Tecnicos_admin.tecnicos.length) {
            JOptionPane.showMessageDialog(null, "Límite de almacenamiento de técnicos alcanzado en el sistema.", "AirService-Pro", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // 8. Crear el objeto pasándole los parámetros en el orden exacto de su constructor
        Tecnicos_admin nuevoTecnico = new Tecnicos_admin(tipoDocumento, documento, nombre, correo, telefono, ciudad, nivel, contra);
        
        Tecnicos_admin.tecnicos[Tecnicos_admin.contadorTecnicos] = nuevoTecnico;
        Tecnicos_admin.contadorTecnicos++;

        JOptionPane.showMessageDialog(null, "Técnico registrado con éxito en el sistema.", "AirService-Pro", JOptionPane.INFORMATION_MESSAGE);
        return true;
    }

    // --- LÓGICA DE NEGOCIO: ACTUALIZAR TÉCNICO (U - UPDATE) ---

    /**
     * 🌟 NUEVO MÉTODO: Modifica al técnico evaluando campo por campo.
     * Si un campo viene vacío o el combo está en la opción 0, conserva el valor anterior.
     */
    public static boolean actualizarTecnicoInteligente(String documento, String nombre, String correo, 
                                                       String clave, String direccion, String tipoDoc, String nivel,
                                                       int idxTipoDoc, int idxNivel) {
        
        // Usamos el método de búsqueda que ya tienes arriba
        Tecnicos_admin tecnico = buscarTecnicoPorDocumento(documento);
        
        if (tecnico != null) {
            // Evaluamos las cajas de texto: si no están vacías, actualizamos el atributo
            if (!nombre.isEmpty())    tecnico.setNombre(nombre);
            if (!correo.isEmpty())    tecnico.setCorreo(correo);
            if (!clave.isEmpty())     tecnico.setContrasena(clave); // Usa setContrasena(clave) si así se llama en tu modelo
            if (!direccion.isEmpty()) tecnico.setDireccion(direccion);
            
            // Evaluamos los Combos usando su índice de selección (si es 0, no seleccionó nada nuevo)
            if (idxTipoDoc != 0) tecnico.setTipoDocumento(tipoDoc);
            if (idxNivel != 0)   tecnico.setNivel(nivel);
            
            return true; // Se logró actualizar en memoria
        }
        
        return false; // No se encontró el técnico
    }

    // --- LÓGICA DE NEGOCIO: ELIMINAR TÉCNICO (D - DELETE) ---
    
    public static boolean eliminarTecnicoPorDocumento(String documento) {
        if (documento == null || documento.trim().isEmpty()) {
            return false;
        }

        // Recorremos el arreglo buscando el técnico por coincidencia de documento
        for (int i = 0; i < Tecnicos_admin.contadorTecnicos; i++) {
            if (Tecnicos_admin.tecnicos[i] != null && Tecnicos_admin.tecnicos[i].getDocumento().equals(documento.trim())) {
                
                // Desplazamos los elementos siguientes hacia la izquierda para evitar huecos intermedios (null)
                for (int j = i; j < Tecnicos_admin.contadorTecnicos - 1; j++) {
                    Tecnicos_admin.tecnicos[j] = Tecnicos_admin.tecnicos[j + 1];
                }
                
                // Limpiamos la última posición que ahora queda duplicada y decrementamos el contador general
                Tecnicos_admin.tecnicos[Tecnicos_admin.contadorTecnicos - 1] = null;
                Tecnicos_admin.contadorTecnicos--;
                
                return true; // Eliminación exitosa
            }
        }
        return false; // No se encontró ningún técnico con ese documento
    }
}