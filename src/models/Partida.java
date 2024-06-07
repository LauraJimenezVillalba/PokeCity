package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
  private boolean proximoReset;
  private int regalosCombate;
  private int regalosMedicina;
  private int regalosServicios;
  private int regalosEspectaculos;
  private int regalosComida;
  private int regalosVentas;
  private int huevos;
  
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
    this.proximoReset = false;
    this.regalosCombate = 0;
    this.regalosMedicina = 0;
    this.regalosServicios = 0;
    this.regalosEspectaculos = 0;
    this.regalosComida = 0;
    this.regalosVentas = 0;
    this.huevos = 0;
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

  public boolean isProximoReset() {
    return proximoReset;
  }

  public void setProximoReset(boolean proximoReset) {
    this.proximoReset = proximoReset;
  }

  public int getRegalosCombate() {
    return regalosCombate;
  }

  public void setRegalosCombate(int regalosCombate) {
    this.regalosCombate = regalosCombate;
  }

  public int getRegalosMedicina() {
    return regalosMedicina;
  }

  public void setRegalosMedicina(int regalosMedicina) {
    this.regalosMedicina = regalosMedicina;
  }

  public int getRegalosServicios() {
    return regalosServicios;
  }

  public void setRegalosServicios(int regalosServicios) {
    this.regalosServicios = regalosServicios;
  }

  public int getRegalosEspectaculos() {
    return regalosEspectaculos;
  }

  public void setRegalosEspectaculos(int regalosEspectaculos) {
    this.regalosEspectaculos = regalosEspectaculos;
  }

  public int getRegalosComida() {
    return regalosComida;
  }

  public void setRegalosComida(int regalosComida) {
    this.regalosComida = regalosComida;
  }

  public int getRegalosVentas() {
    return regalosVentas;
  }

  public void setRegalosVentas(int regalosVentas) {
    this.regalosVentas = regalosVentas;
  }
  
  public Pokemon[] noCasados() {
    List<Pokemon> strings = new ArrayList<>();
    for (Pokemon pokemon : pokemones) {
	    if (!pokemon.isCasado() && !pokemon.isHijo() && pokemon.isCasa()) {
	      strings.add(pokemon);
	    }
    }
    Collections.shuffle(strings);
    Pokemon[] selectedStrings = new Pokemon[2];
    selectedStrings[0] = strings.get(0);
    selectedStrings[1] = strings.get(1);
    return selectedStrings;
  }

  public int getHuevos() {
    return huevos;
  }

  public void setHuevos(int huevos) {
    this.huevos = huevos;
  }

}
