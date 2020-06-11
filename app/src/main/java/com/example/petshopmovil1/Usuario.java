package com.example.petshopmovil1;

public class Usuario {
    int Id;
    String Nombre,Apellidos, Usuario, Password, Password2,Correo;

    public Usuario() {
    }

    public Usuario(String nombre, String apellidos, String usuario, String password, String password2, String correo) {
        Nombre = nombre;
        Apellidos = apellidos;
        Usuario = usuario;
        Password = password;
        Password2=password2;
        Correo=correo;
    }

    public boolean isNull(){
        if(Nombre.equals("")&&Apellidos.equals("")&&Usuario.equals("")&&Password.equals("")&&Password2.equals("")&&Correo.equals("")) {
            return  false;
        }
        else
        {
            return true;
        }
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "Id=" + Id +
                ", Nombre='" + Nombre + '\'' +
                ", Apellidos='" + Apellidos + '\'' +
                ", Usuario='" + Usuario + '\'' +
                ", Password='" + Password + '\'' +
                ", Password2='" + Password2 + '\'' +
                ", Correo='" + Correo + '\'' +
                '}';
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getPassword2() {
        return Password2;
    }

    public void setPassword2(String password2) {
        Password2 = password2;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
