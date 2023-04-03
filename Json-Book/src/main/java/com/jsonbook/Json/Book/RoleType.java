package com.jsonbook.Json.Book;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLBoundOperation;

public enum RoleType {
    ADMIN,
    USER,
    ANONYMOUS;

    public static RoleType fromString(String role) {
        try {
            return RoleType.valueOf(role);
        } catch (Exception e) {
            return ANONYMOUS;
        }
    }
}
