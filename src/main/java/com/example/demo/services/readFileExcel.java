package com.example.demo.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.demo.model.tablaprueba;



public class readFileExcel {

    public List<tablaprueba> readExcel(File file)  {
        try (FileInputStream fis = new FileInputStream(file); Workbook wb = new XSSFWorkbook(fis)) {
            Sheet hoja = wb.getSheetAt(0);
            DataFormatter dataFormatter = new DataFormatter();
            Iterator<Row> rowIterator = hoja.iterator();
            List<tablaprueba> lista = new ArrayList<>(); 
            boolean primeraFila = true;

  
            while (rowIterator.hasNext()) {
                Row fila = rowIterator.next();
                if (primeraFila) {
                    primeraFila = false;
                    continue;
                }
               
                Iterator<Cell> cellIterator = fila.iterator();

                tablaprueba tp = new tablaprueba();
                
                while (cellIterator.hasNext()) {
                    
                    Cell celda = cellIterator.next();
                    String cellValue = dataFormatter.formatCellValue(celda); // Obtén el contenido de la celda formateado según su tipo de datos
                    System.out.print(cellValue + "\t"); // Imprime el valor de la celda en la consola
                    switch (celda.getColumnIndex()) {
                        case 0:
                            tp.setNombre(cleanText(cellValue));
                            break;
                        case 1:
                            tp.setApellido(cellValue);
                            break;
                        case 2:
                            tp.setEdad(Integer.parseInt(cellValue));
                            break;
                        case 3:
                        String fechaTexto = cellValue;
                            tp.setFecha(fechaTexto);
                            break;
                        default:
                            // Ignorar celdas adicionales si las hay
                            break;
                    }
                }
                System.out.println("");
                lista.add(tp);
            }
            return lista;
        }catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String cleanText (String text){
        if(!text.isEmpty()){
            text=text.replaceAll("[^a-zA-Z0-9\\s]+", "");
            return text;
        }
        return null;
    }
}
