package com.example.vecindarioBosa

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service

@Service
class ComentarioService(private val jdbcTemplate: JdbcTemplate) {

    fun crear(comentario: Comentario): Int {
        val sql = """
            INSERT INTO comentarios (texto, creado_en, autor_id, aviso_id) 
            VALUES (?, NOW(), ?, ?)
        """
        return jdbcTemplate.update(sql, comentario.texto, comentario.autor_id, comentario.aviso_id)
    }

    fun listar(): List<Comentario> {
        val sql = "SELECT id, texto, creado_en, autor_id, aviso_id FROM comentarios"
        return jdbcTemplate.query(sql) { rs, _ ->
            Comentario(
                id = rs.getInt("id"),
                texto = rs.getString("texto"),
                creado_en = rs.getString("creado_en"),
                autor_id = rs.getInt("autor_id"),
                aviso_id = rs.getInt("aviso_id")
            )
        }
    }

    fun obtenerPorId(id: Int): Comentario? {
        val sql = "SELECT id, texto, creado_en, autor_id, aviso_id FROM comentarios WHERE id = ?"
        return jdbcTemplate.query(sql, arrayOf(id)) { rs, _ ->
            Comentario(
                id = rs.getInt("id"),
                texto = rs.getString("texto"),
                creado_en = rs.getString("creado_en"),
                autor_id = rs.getInt("autor_id"),
                aviso_id = rs.getInt("aviso_id")
            )
        }.firstOrNull()
    }

    fun actualizar(id: Int, comentario: Comentario): Int {
        val sql = "UPDATE comentarios SET texto = ?, autor_id = ?, aviso_id = ? WHERE id = ?"
        return jdbcTemplate.update(sql, comentario.texto, comentario.autor_id, comentario.aviso_id, id)
    }

    fun eliminar(id: Int): Int {
        val sql = "DELETE FROM comentarios WHERE id = ?"
        return jdbcTemplate.update(sql, id)
    }
}
