package com.example.vecindarioBosa.service

import com.example.vecindarioBosa.models.Aviso
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Service

@Service
class AvisosService {

    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate

    private val avisoRowMapper = RowMapper { rs, _ ->
        Aviso(
            id = rs.getInt("id"),
            titulo = rs.getString("titulo"),
            descripcion = rs.getString("descripcion"),
            categoria = rs.getString("categoria"),
            estado = rs.getString("estado"),
            usuario_id = rs.getInt("usuario_id")
        )
    }

    fun obtenerAvisos(): List<Aviso> {
        val sql = "SELECT * FROM avisos"
        return jdbcTemplate.query(sql, avisoRowMapper)
    }

    fun crearAviso(titulo: String, descripcion: String, categoria: String, usuario_id: Int) {
        val sql = """
            INSERT INTO avisos (titulo, descripcion, categoria, estado, usuario_id, created_at, updated_at)
            VALUES (?, ?, ?, 'pendiente', ?, NOW(), NOW())
        """
        jdbcTemplate.update(sql, titulo, descripcion, categoria, usuario_id)
    }

    fun actualizarAviso(id: Int, titulo: String, descripcion: String, categoria: String, estado: String) {
        val sql = """
            UPDATE avisos 
            SET titulo = ?, descripcion = ?, categoria = ?, estado = ?, updated_at = NOW()
            WHERE id = ?
        """
        jdbcTemplate.update(sql, titulo, descripcion, categoria, estado, id)
    }

    fun marcarComoAtendido(id: Int) {
        val sql = "UPDATE avisos SET estado = 'atendido', updated_at = NOW() WHERE id = ?"
        jdbcTemplate.update(sql, id)
    }

    fun eliminarAviso(id: Int) {
        val sql = "DELETE FROM avisos WHERE id = ?"
        jdbcTemplate.update(sql, id)
    }
}