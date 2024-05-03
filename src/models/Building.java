package models;

public class Building {
  
  private String image;
  private boolean desplegado;
  private Pokemon trabajador;
  private String clase;

  public Building(String image, String clase) {
    this.image = image;
    this.clase = clase;
    this.desplegado = false;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public boolean isDesplegado() {
    return desplegado;
  }

  public void setDesplegado(boolean desplegado) {
    this.desplegado = desplegado;
  }

  public Pokemon getTrabajador() {
    return trabajador;
  }

  public void setTrabajador(Pokemon trabajador) {
    this.trabajador = trabajador;
  }

  public String getClase() {
    return clase;
  }

  public void setClase(String clase) {
    this.clase = clase;
  }

}
