package com.example.vecindarioBosa.service

import com.example.vecindarioBosa.models.Usuario
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Service

@Service
class UsuariosService {

    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate

    private val usuarioRowMapper = RowMapper { rs, _ ->
        Usuario(
            id = rs.getInt("id"),
            nombre = rs.getString("nombre"),
            email = rs.getString("email"),
            password = rs.getString("password")
        )
    }

    fun registrarUsuario(nombre: String, email: String, password: String) {
        val sql = "INSERT INTO usuarios (nombre, email, password) VALUES (?, ?, ?)"
        jdbcTemplate.update(sql, nombre, email, password)
    }

    fun login(email: String, password: String): Boolean {
        val sql = "SELECT COUNT(*) FROM usuarios WHERE email = ? AND password = ?"
        val count = jdbcTemplate.queryForObject(sql, Int::class.java, email, password)
        return count != null && count > 0
    }

    fun obtenerUsuarios(): List<Usuario> {
        val sql = "SELECT * FROM usuarios"
        return jdbcTemplate.query(sql, usuarioRowMapper)
    }
}