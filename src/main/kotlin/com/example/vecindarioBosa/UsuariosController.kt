package com.example.vecindarioBosa.Usuario

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

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
        return if (autenticado) "Login exitoso" else "Datos inválidos"
    }

    @GetMapping
    fun obtenerUsuarios(): List<Usuario> {
        return usuariosService.obtenerUsuarios()
    }
}