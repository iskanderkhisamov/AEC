package ru.kstu.aec.models;

public enum UserRole {
    ADMIN, USER, TEACHER
    // Enum для создания Authorities, в бд отображаются как числа, начинающиеся с 0
    // например, номер USER это 1
}
