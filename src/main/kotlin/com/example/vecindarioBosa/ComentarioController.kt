package com.example.vecindarioBosa

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/comentarios")
class ComentarioController(private val comentarioService: ComentarioService) {

    @PostMapping
    fun crearComentario(@RequestBody comentario: Comentario): String {
        comentarioService.crear(comentario)
        return "Comentario creado correctamente"
    }

    @GetMapping
    fun listarComentarios(): List<Comentario> {
        return comentarioService.listar()
    }

    @GetMapping("/{id}")
    fun obtenerComentario(@PathVariable id: Int): Comentario? {
        return comentarioService.obtenerPorId(id)
    }

    @PutMapping("/{id}")
    fun actualizarComentario(@PathVariable id: Int, @RequestBody comentario: Comentario): String {
        val filas = comentarioService.actualizar(id, comentario)
        return if (filas > 0) "Comentario actualizado correctamente"
        else "No se encontró el comentario con id $id"
    }

    @DeleteMapping("/{id}")
    fun eliminarComentario(@PathVariable id: Int): String {
        val filas = comentarioService.eliminar(id)
        return if (filas > 0) "Comentario eliminado correctamente"
        else "No se encontró el comentario con id $id"
    }
}
