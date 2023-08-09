package com.block13testingcrud.block13testingcrud.exception;

import org.springframework.http.HttpStatus;

/**
 * Clase de Excepción Personalizada - EntityNotFoundException.
 *
 * <p>Esta clase es una extensión de {@link ApplicationException}, diseñada específicamente para manejar
 * escenarios donde una entidad requerida no se encuentra en la base de datos o no está disponible.</p>
 *
 * <p>Cambios Recientes:
 * <ul>
 *   <li>Se modificó el constructor para que acepte directamente el ID o el nombre de usuario sin aplicar
 *       ningún formato al mensaje. Esto es para evitar la redundancia del mensaje de error.</li>
 * </ul></p>
 *
 * <p>Lógica de Gestión de Mensajes:
 * <ul>
 *   <li>La excepción {@link EntityNotFoundException} toma un ID o un nombre de usuario y lo pasa directamente
 *       al constructor de su clase padre ({@link ApplicationException}).</li>
 *   <li>La clase {@link ApplicationException} tiene una propiedad {@code externalMessage} que se utiliza para
 *       almacenar y recuperar mensajes de error personalizados.</li>
 *   <li>Al lanzar la excepción, se evita el formateo redundante del mensaje de error, asegurando así que
 *       el mensaje sea claro y conciso.</li>
 * </ul></p>
 * **/


public class EntityNotFoundException extends ApplicationException {

    private static final String EXTERNAL_MESSAGE = "La entidad con ID: %s no fue encontrada.";

    public EntityNotFoundException(int id) {
        super(Integer.toString(id), HttpStatus.NOT_FOUND);
    }

    public EntityNotFoundException(String usuario) {
        super(usuario, HttpStatus.NOT_FOUND);
    }
}
