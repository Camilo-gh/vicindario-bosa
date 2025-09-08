package com.example.vecindarioBosa.models

data class Aviso(
    val id: Int,
    val titulo: String,
    val descripcion: String,
    val categoria: String,
    val estado: String,
    val usuario_id: Int
)