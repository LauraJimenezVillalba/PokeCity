package models;

import java.io.Serializable;

public class Building implements Serializable {
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private String image;
  private boolean desplegado;
  private Pokemon trabajador;
  private Pokemon viviendo;
  private String clase;
  int col;
  int row;

  public Building(String image, String clase) {
    this.image = image;
    this.clase = clase;
    this.desplegado = false;
    this.col = -1;
    this.row = -1;
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

  public Pokemon getViviendo() {
    return viviendo;
  }

  public void setViviendo(Pokemon viviendo) {
    this.viviendo = viviendo;
  }

  public int getCol() {
    return col;
  }

  public void setCol(int col) {
    this.col = col;
  }

  public int getRow() {
    return row;
  }

  public void setRow(int row) {
    this.row = row;
  }

}
