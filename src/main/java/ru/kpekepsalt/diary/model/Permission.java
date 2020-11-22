package ru.kpekepsalt.diary.model;

public enum Permission {
    TASK_GET_SELF("task:get:self"),
    TASK_GET_OTHERS("task:get"),
    TASK_GET_PRIVATE("task:get:private"),
    TASK_ADD("task:add"),
    TASK_REMOVE_SELF("task:remove:self"),
    TASK_CHANGE_STATUS_SELF("task:status:change:self"),
    TASK_REMOVE_OTHERS("task:remove"),
    TASK_CHANGE_STATUS_OTHERS("task:status:change"),
    PLAN_GET_NOW("plan:get:now"),
    PLAN_GET_DATE("plan:get:date"),
    USER_VIEW_SELF("user:view:self"),
    USER_VIEW_OTHERS("user:view");

    private String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
