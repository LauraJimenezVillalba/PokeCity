package models;

import java.io.Serializable;
import java.util.ArrayList;

public class Partida implements Serializable {
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private ArrayList<Pokemon> pokemones;
  private ArrayList<Building> buildings;
  private Building selectedBuilding;
  private int day;
  private String hora;
  private int pendiente;
  private int dinero;
  private boolean intercalado;
  private boolean newDecision;
  private boolean empezado;
  
  public Partida(ArrayList<Pokemon> pokemones, ArrayList<Building> buildings,
      int day, int pendiente, int dinero,
      boolean intercalado, boolean newDecision, String hora) {
    this.pokemones = pokemones;
    this.buildings = buildings;
    this.day = day;
    this.pendiente = pendiente;
    this.dinero = dinero;
    this.intercalado = intercalado;
    this.newDecision = newDecision;
    this.hora = hora;
    this.empezado = false;
  }

  public ArrayList<Pokemon> getPokemones() {
    return pokemones;
  }

  public void setPokemones(ArrayList<Pokemon> pokemones) {
    this.pokemones = pokemones;
  }

  public ArrayList<Building> getBuildings() {
    return buildings;
  }

  public void setBuildings(ArrayList<Building> buildings) {
    this.buildings = buildings;
  }

  public Building getSelectedBuilding() {
    return selectedBuilding;
  }

  public void setSelectedBuilding(Building selectedBuilding) {
    this.selectedBuilding = selectedBuilding;
  }

  public int getDay() {
    return day;
  }

  public void setDay(int day) {
    this.day = day;
  }

  public int getPendiente() {
    return pendiente;
  }

  public void setPendiente(int pendiente) {
    this.pendiente = pendiente;
  }

  public int getDinero() {
    return dinero;
  }

  public void setDinero(int dinero) {
    this.dinero = dinero;
  }

  public boolean isIntercalado() {
    return intercalado;
  }

  public void setIntercalado(boolean intercalado) {
    this.intercalado = intercalado;
  }

  public boolean isNewDecision() {
    return newDecision;
  }

  public void setNewDecision(boolean newDecision) {
    this.newDecision = newDecision;
  }

  public String getHora() {
    return hora;
  }

  public void setHora(String hora) {
    this.hora = hora;
  }

  public boolean isEmpezado() {
    return empezado;
  }

  public void setEmpezado(boolean empezado) {
    this.empezado = empezado;
  }

}
