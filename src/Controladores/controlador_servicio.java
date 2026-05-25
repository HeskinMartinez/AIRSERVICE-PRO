package Controladores;

import Modelos.Tecnicos_admin;
import Modelos.Servicios;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel; // 🌟 IMPORTANTE para poder armar la estructura de la tabla

/**
 * Controlador para la gestión de servicios y mantenimientos en AirService-Pro.
 * @author EUMAPE
 */
public class controlador_servicio {
    
    // --- MÉTODOS DE LECTURA DE DATOS (ENCAPSULAMIENTO DE MVC) ---

    /**
     * Obtiene la cantidad actual de servicios agendados en el sistema.
     * @return int con el total de registros.
     */
    public static int getContadorServicios() {
        return Modelos.Servicios.contadorServicios;
    }

    /**
     * Obtiene el arreglo global de objetos Servicios.
     * @return Modelos.Servicios[] Arreglo rígido de servicios en memoria.
     */
    public static Modelos.Servicios[] obtenerListaServicios() {
        return Modelos.Servicios.listaServicios;
    }

    /**
     * Extrae los servicios en memoria y los mapea a un DefaultTableModel 
     * adaptado de forma idéntica a las 4 columnas de la interfaz visual.
     * @return DefaultTableModel listo para ser inyectado en el JTable de la pestaña Listar.
     */
    public static DefaultTableModel listarServicios() {
        // 1. Definimos los encabezados EXACTAMENTE en el orden de tu diseño visual
        String[] columnas = {"Tipo Servicio", "Direccion", "Nombre Cliente", "Fecha"};
        
        // 2. Creamos el modelo deshabilitando la edición de celdas por doble clic
        DefaultTableModel modeloTabla = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Solo lectura
            }
        };

        // 3. Recorremos el arreglo global basándonos exclusivamente en los guardados reales
        for (int i = 0; i < Modelos.Servicios.contadorServicios; i++) {
            if (Modelos.Servicios.listaServicios[i] != null) {
                
                // 4. Mapeamos cada objeto del arreglo a la columna correspondiente de tu captura
                Object[] fila = new Object[4];
                fila[0] = Modelos.Servicios.listaServicios[i].getTipoServicio(); // Revisa si en tu Modelo es getTipoServicio o getTipoService
                fila[1] = Modelos.Servicios.listaServicios[i].getDireccionCliente();
                fila[2] = Modelos.Servicios.listaServicios[i].getNombreCliente();
                fila[3] = Modelos.Servicios.listaServicios[i].getFechaServicio();

                // 5. Insertamos la fila en el contenedor de datos
                modeloTabla.addRow(fila);
            }
        }
        return modeloTabla;
    }

    // --- LÓGICA DE NEGOCIO: BUSCAR SERVICIOS POR CLIENTE (RETORNA ARREGLO) ---
    
    /**
     * Busca todos los servicios asociados a la cédula de un cliente y los devuelve en un arreglo.
     * @param documentoCliente Cédula a buscar en el sistema.
     * @return Modelos.Servicios[] Arreglo temporal con las coincidencias encontradas.
     */
    public static Modelos.Servicios[] buscarServiciosPorCliente(String documentoCliente) {
        // Creamos un arreglo temporal del mismo tamaño que la lista global para guardar las coincidencias
        Modelos.Servicios[] encontrados = new Modelos.Servicios[Modelos.Servicios.listaServicios.length];
        int posicion = 0;

        if (documentoCliente == null || documentoCliente.trim().isEmpty()) {
            return encontrados; // Retorna el arreglo vacío si el parámetro no es válido
        }

        // Recorremos la lista global buscando coincidencias
        for (int i = 0; i < Modelos.Servicios.contadorServicios; i++) {
            if (Modelos.Servicios.listaServicios[i] != null && 
                Modelos.Servicios.listaServicios[i].getDocumentoCliente().equals(documentoCliente.trim())) {
                
                // Guardamos el servicio en el contenedor temporal y avanzamos el índice
                encontrados[posicion] = Modelos.Servicios.listaServicios[i];
                posicion++;
            }
        }
        
        return encontrados; // Retornamos el arreglo (la Vista podrá leer la posición [0] sin problemas)
    }

    // --- MÉTODOS DE VALIDACIÓN CON EXPRESIONES REGULARES (REUTILIZABLES) ---
    
    public static boolean validarNombreFormato(String nombre) {
        // Permite letras (con acentos), eñes y espacios. No permite números ni símbolos.
        return nombre.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$");
    }

    public static boolean validarTelefonoFormato(String telefono) {
        // Permite únicamente números que tengan entre 7 y 12 dígitos
        return telefono.matches("\\d{7,12}");
    }

    // --- LÓGICA DE NEGOCIO: REGISTRAR NUEVO SERVICIO (C - CREATE) ---
    
    public static boolean registrarNuevoServicio(String nombreCliente, String documentoCliente, String telefonoCliente, 
                                                 String direccionCliente, String fechaServicio, String nombreTecnico, 
                                                 String tipoServicio, String descripcionServicio) {
        
        // 1. Validar que los campos de texto obligatorios de la interfaz no estén vacíos
        if (nombreCliente.isEmpty() || documentoCliente.isEmpty() || telefonoCliente.isEmpty() 
            || direccionCliente.isEmpty() || fechaServicio.isEmpty() || nombreTecnico.isEmpty() 
            || descripcionServicio.isEmpty()) {
            
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos del formulario de servicio.", "AirService-Pro", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // 2. Validar que se haya seleccionado un tipo de servicio real en el JComboBox
        if (tipoServicio.contains("Seleccione") || tipoServicio.equalsIgnoreCase("Selecciona una opcion")) {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione un tipo de servicio válido.", "AirService-Pro", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // 3. Validar formatos del Cliente (Nombre, Documento y Teléfono)
        if (!validarNombreFormato(nombreCliente)) {
            JOptionPane.showMessageDialog(null, "El nombre del cliente no puede contener números ni caracteres especiales.", "AirService-Pro", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (!documentoCliente.matches("\\d{6,12}")) {
            JOptionPane.showMessageDialog(null, "El documento del cliente debe ser puramente numérico (6-12 dígitos).", "AirService-Pro", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (!validarTelefonoFormato(telefonoCliente)) {
            JOptionPane.showMessageDialog(null, "El teléfono del cliente debe contener entre 7 y 12 números.", "AirService-Pro", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // 4. INTERCONEXIÓN DE MÓDULOS: Validar si el Técnico ingresado existe en el sistema
        boolean tecnicoExiste = false;

        // Recorremos el arreglo estático del modelo Tecnicos_admin
        for (int i = 0; i < Tecnicos_admin.contadorTecnicos; i++) {
            if (Tecnicos_admin.tecnicos[i] != null) {
                // Comparamos ignorando mayúsculas/minúsculas para evitar errores tipográficos
                if (Tecnicos_admin.tecnicos[i].getNombre().equalsIgnoreCase(nombreTecnico.trim())) {
                    tecnicoExiste = true;
                    break; // Cortamos el bucle inmediatamente al encontrar coincidencia
                }
            }
        }

        // Si el ciclo for termina y la bandera sigue en false, el técnico no existe
        if (!tecnicoExiste) {
            JOptionPane.showMessageDialog(null, 
                "Error: El técnico '" + nombreTecnico + "' no se encuentra registrado en el módulo de Técnicos.\nVerifique el nombre o regístrelo primero.", 
                "AirService-Pro", JOptionPane.ERROR_MESSAGE);
            return false; // Bloquea de inmediato el agendamiento
        }

        // 5. Validar el límite de almacenamiento físico del arreglo de servicios en el MODELO
        if (Modelos.Servicios.contadorServicios >= Modelos.Servicios.listaServicios.length) {
            JOptionPane.showMessageDialog(null, "El sistema no tiene espacio en memoria para agendar más servicios.", "AirService-Pro", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // 6. ALMACENAMIENTO EN MEMORIA ORIENTADO A OBJETOS
        // Instanciamos el objeto con los parámetros limpios en el orden exacto de su constructor
        Servicios nuevoServicio = new Servicios(
            nombreCliente, documentoCliente, telefonoCliente, direccionCliente, 
            fechaServicio, nombreTecnico, tipoServicio, descripcionServicio
        );
        
        // Guardamos el objeto dentro del arreglo físico que está en la clase Servicio
        Modelos.Servicios.listaServicios[Modelos.Servicios.contadorServicios] = nuevoServicio;
        Modelos.Servicios.contadorServicios++;

        // 7. Notificación de éxito al Administrador
        JOptionPane.showMessageDialog(null, "¡Servicio agendado y asignado con éxito al técnico " + nombreTecnico + "!", "AirService-Pro", JOptionPane.INFORMATION_MESSAGE);
        return true; 
    }
}