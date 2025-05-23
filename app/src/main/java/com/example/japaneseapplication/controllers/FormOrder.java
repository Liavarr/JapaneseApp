package com.example.japaneseapplication.controllers;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

// Definimos la anotación
@Retention(RetentionPolicy.RUNTIME)  // Disponible en tiempo de ejecución
@Target(ElementType.FIELD)           // Solo aplicable a campos
public @interface FormOrder {
    int value();  // Valor que indica el orden
}

