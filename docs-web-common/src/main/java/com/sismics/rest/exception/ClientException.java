package com.sismics.rest.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.json.Json;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * Jersey exception encapsulating an error from the client (BAD_REQUEST).
 *
 * @author jtremeaux
 */
public class ClientException extends WebApplicationException {
    /**
     * Serial UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Logger.
     */
    private static final Logger log = LoggerFactory.getLogger(ClientException.class);

    /**
     * Constructor of ClientException.
     * 
     * @param type Error type (e.g. AlreadyExistingEmail, ValidationError)
     * @param message Human readable error message
     * @param e Readable error message
     */
    public ClientException(String type, String message, Exception e) {
        this(type, message);
        log.error(type + ": " + message, e);
    }

    /**
     * Constructor of ClientException.
     * 
     * @param type Error type (e.g. AlreadyExistingEmail, ValidationError)
     * @param message Human readable error message
     */
    public ClientException(String type, String message) {
        super(Response.status(Status.BAD_REQUEST).entity(Json.createObjectBuilder()
            .add("type", type)
            .add("message", message).build()).build());
    }
}
