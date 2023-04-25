package com.example.demo.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.tablaprueba;
import com.example.demo.repository.tablapruebaRepository;
import com.example.demo.services.readFileExcel;

@Controller
@RequestMapping("/excel")
public class excelController {

    @Autowired
    private tablapruebaRepository repository;

    @GetMapping("/")
    public String inicio() {
        System.out.println("Inicio de la aplicacion");
        return "cargaExcel/loadExcel";
    }

    @PostMapping("/loadExcel")
    public String recibido(@RequestParam("email") String name) {
        System.out.println("Este es el nommbre " + name);
        return "cargaExcel/loadExcel";
    }

    @PostMapping("/save")
    public String save() {
        tablaprueba tabla = new tablaprueba();
        tabla.setNombre("carlos");
        tabla.setApellido("olea");
        repository.save(tabla);
        System.out.println(tabla);
        return "cargaExcel/loadExcel";
    }

    @PostMapping("/all")
    public String showAll() {
        var tabla = repository.findAll();
        System.out.println(tabla);
        return "cargaExcel/loadExcel";
    }

    @PostMapping("/saveAll")
    public void saveAll() {
        List<tablaprueba> listaTablaprueba = new ArrayList<>();
        int excel = 10;// cantidad de datos en el excel
        for (int i = 0; i < excel; i++) {
            tablaprueba instancia = new tablaprueba();
            instancia.setNombre("Nombre " + (i + 1)); // Asignar un valor al nombre
            instancia.setApellido("Apellido " + (i + 1)); // Asignar un valor al apellido
            listaTablaprueba.add(instancia);
        }
        repository.saveAll(listaTablaprueba);
    }

    @PostMapping("/readExcel")
    public String readExcel() {
        readFileExcel read = new readFileExcel();
        File file = new File("C:/Users/soporte/Desktop/leerExcel.xlsx");
        //read.readExcel(file);
            repository.saveAll(read.readExcel(file));
       
        
        return "cargaExcel/loadExcel";
    }

}
