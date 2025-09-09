package com.example.vecindarioBosa.controllers

import com.example.vecindarioBosa.models.Aviso
import com.example.vecindarioBosa.service.AvisosService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/avisos")
class AvisosController {

    @Autowired
    lateinit var avisosService: AvisosService

    @GetMapping
    fun obtenerAvisos(): List<Aviso> {
        return avisosService.obtenerAvisos()
    }

    @PostMapping
    fun crearAviso(@RequestBody aviso: Aviso): String {
        avisosService.crearAviso(aviso.titulo, aviso.descripcion, aviso.categoria, aviso.usuario_id)
        return "Aviso publicado correctamente"
    }

    @PutMapping("/{id}")
    fun actualizarAviso(@PathVariable id: Int, @RequestBody aviso: Aviso): String {
        avisosService.actualizarAviso(id, aviso.titulo, aviso.descripcion, aviso.categoria, aviso.estado)
        return "Aviso actualizado correctamente"
    }

    @PutMapping("/{id}/atender")
    fun marcarComoAtendido(@PathVariable id: Int): String {
        avisosService.marcarComoAtendido(id)
        return "Aviso marcado como atendido"
    }

    @DeleteMapping("/{id}")
    fun eliminarAviso(@PathVariable id: Int): String {
        avisosService.eliminarAviso(id)
        return "Aviso eliminado correctamente"
    }
}