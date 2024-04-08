package com.ezen.management.domain;

public enum MemberRole {

    MASTER, ADMIN, TEACHER, STUDENT;

    @Override
    public String toString() {
        return "ROLE_" + this.name();
    }
}
