package com.example.vecindarioBosa.controllers

import com.example.vecindarioBosa.models.Usuario
import com.example.vecindarioBosa.service.UsuariosService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/usuarios")
class UsuariosController {

    @Autowired
    lateinit var usuariosService: UsuariosService

    @PostMapping("/registro")
    fun registrarUsuario(@RequestBody usuario: Usuario): String {
        usuariosService.registrarUsuario(usuario.nombre, usuario.email, usuario.password)
        return "Usuario registrado correctamente"
    }

    @PostMapping("/login")
    fun login(@RequestBody usuario: Usuario): String {
        val autenticado = usuariosService.login(usuario.email, usuario.password)
        return if (autenticado) "Login exitoso" else "Credenciales inválidas"
    }

    @GetMapping
    fun obtenerUsuarios(): List<Usuario> {
        return usuariosService.obtenerUsuarios()
    }
}