package models;

import java.io.Serializable;
import java.util.ArrayList;

public class Building implements Serializable {
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private String image;
  private boolean desplegado;
  private Pokemon trabajador;
  private Pokemon trabajador2;
  private Pokemon trabajador3;
  private Pokemon trabajador4;
  private ArrayList<Pokemon> viviendo;
  private ArrayList<Huevo> incubando;
  private Pokemon selectedViviendo;
  private String clase;
  int col;
  int row;
  int numMejoras;

  public Building(String image, String clase) {
    this.image = image;
    this.clase = clase;
    this.desplegado = false;
    this.col = -1;
    this.row = -1;
    this.numMejoras = 0;
    this.viviendo = new ArrayList<Pokemon>();
    this.incubando = new ArrayList<Huevo>();
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

  public ArrayList<Pokemon> getViviendo() {
    return viviendo;
  }
  
  public void addViviendo(Pokemon pokemon) {
    viviendo.add(pokemon);
  }
  
  public void removeViviendo(Pokemon pokemon) {
    viviendo.remove(pokemon);
  }

  public void setViviendo(ArrayList<Pokemon> viviendo) {
    this.viviendo = viviendo;
  }
  
  public ArrayList<Huevo> getIncubando() {
    return incubando;
  }
  
  public void addIncubando(Huevo huevo) {
    incubando.add(huevo);
  }
  
  public void removeIncubando(Huevo huevo) {
    incubando.remove(huevo);
  }

  public void setIncubando(ArrayList<Huevo> huevoList) {
    this.incubando = huevoList;
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

  public Pokemon getTrabajador2() {
    return trabajador2;
  }

  public void setTrabajador2(Pokemon trabajador3) {
    this.trabajador2 = trabajador3;
  }
  
  public Pokemon getTrabajador3() {
    return trabajador3;
  }

  public void setTrabajador3(Pokemon trabajador3) {
    this.trabajador3 = trabajador3;
  }
  
  public Pokemon getTrabajador4() {
    return trabajador4;
  }

  public void setTrabajador4(Pokemon trabajador4) {
    this.trabajador4 = trabajador4;
  }

  public int getNumMejoras() {
    return numMejoras;
  }

  public void setNumMejoras(int numMejoras) {
    this.numMejoras = numMejoras;
  }

  public Pokemon getSelectedViviendo() {
    return selectedViviendo;
  }

  public void setSelectedViviendo(Pokemon selectedViviendo) {
    this.selectedViviendo = selectedViviendo;
  }

}
