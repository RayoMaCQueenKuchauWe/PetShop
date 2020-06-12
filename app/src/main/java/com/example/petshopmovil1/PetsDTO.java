package com.example.petshopmovil1;

public class PetsDTO {
    private int id;
    private String nombre;
    private String tipo;
    private String raza;
    private int edad;
    private String genero;
    private String foto;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    String getNombre() {
        return nombre;
    }
    void setNombre(String nombre) {
        this.nombre = nombre;
    }

    String getTipo() {
        return tipo;
    }
    void setTipo(String tipo) {
        this.tipo = tipo;
    }

    String getRaza() {
        return raza;
    }
    void setRaza(String raza) {
        this.raza = raza;
    }

    int getEdad() {
        return edad;
    }
    void setEdad(int edad) {
        this.edad = edad;
    }

    String getGenero(){
        return genero;
    }
    void setGenero(String genero){
        this.genero=genero;
    }

    String getFoto(){
        return foto;
    }
    void setFoto(String foto){
        this.foto=foto;
    }

    public PetsDTO(int id,String nombre,String tipo, String raza, String genero, int edad,String foto) {
        this.id=id;
        this.nombre=nombre;
        this.tipo = tipo;
        this.raza = raza;
        this.genero = genero;
        this.edad = edad;
        this.foto=foto;
    }
    PetsDTO(){}
}
