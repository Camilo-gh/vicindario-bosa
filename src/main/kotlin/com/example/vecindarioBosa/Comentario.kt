package com.example.vecindarioBosa

data class Comentario(
    val id: Int? = null,
    val texto: String,
    val creado_en: String? = null,
    val autor_id: Int,
    val aviso_id: Int
)
