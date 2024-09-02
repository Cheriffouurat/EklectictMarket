package com.example.eklecticproject.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    MANAGER_READ("management:read"),
    MANAGER_UPDATE("management:update"),
    MANAGER_CREATE("management:create"),
    MANAGER_DELETE("management:delete"),
    SUPER_READ("super:read"),
    SUPER_UPDATE("super:update"),
    SUPER_DELETE("super:delete"),
    SUPER_CREATE("super:create")

    ;

    @Getter
    private final String permission;
}
