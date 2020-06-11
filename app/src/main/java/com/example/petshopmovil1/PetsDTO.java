package com.example.petshopmovil1;

public class PetsDTO {
    private int id;
    private String tipo;
    private String raza;
    private int edad;
    private String genero;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRaza() {
        return raza;
    }
    public void setRaza(String raza) {
        this.raza = raza;
    }

    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getGenero(){
        return genero;
    }
    public void setGenero(String genero){
        this.genero=genero;
    }

    public PetsDTO(int id,String tipo, String raza, String genero, int edad) {
        this.id=id;
        this.tipo = tipo;
        this.raza = raza;
        this.genero = genero;
        this.edad = edad;
    }
    public PetsDTO(){}
}
