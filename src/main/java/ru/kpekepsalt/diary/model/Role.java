package ru.kpekepsalt.diary.model;

import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum Role {

    AUTHOR(Set.of(Permission.TASK_ADD, Permission.TASK_GET_SELF, Permission.PLAN_GET_NOW, Permission.PLAN_GET_DATE,
            Permission.TASK_REMOVE_SELF, Permission.TASK_CHANGE_STATUS_SELF, Permission.USER_VIEW_SELF)),

    ADMIN(Set.of(Permission.TASK_ADD, Permission.TASK_GET_OTHERS, Permission.TASK_GET_PRIVATE,
            Permission.PLAN_GET_NOW, Permission.PLAN_GET_DATE, Permission.TASK_REMOVE_OTHERS,
            Permission.TASK_CHANGE_STATUS_OTHERS, Permission.USER_VIEW_SELF, Permission.USER_VIEW_OTHERS));

    private Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
