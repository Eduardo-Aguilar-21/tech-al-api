package com.ast.tech_al_api.enums;

public enum TaskStatus {
    OPEN,          // Tarea creada, pero no iniciada
    IN_PROGRESS,   // Tarea en curso
    REVIEW,        // Pendiente de revisión
    BLOCKED,       // No puede avanzar por algún motivo
    DONE,          // Tarea completada
    CANCELED       // Tarea cancelada
}
