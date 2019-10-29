package com.company.odataapp.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum Role {

    ADMIN("ROLE_ADMIN"), DEALER("ROLE_DEALER"), GUEST("ROLE_GUEST");
    String role;
}
