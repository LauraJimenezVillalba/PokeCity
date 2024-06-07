package models;

import java.io.Serializable;

public class Pokemon implements Serializable {
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private String nombre;
  private String image;
  private String like;
  private String dislike;
  private boolean trabaja;
  private boolean casa;
  private int amistad;
  private double xp;
  private boolean casado;
  private boolean hijo;

  public Pokemon(String nombre, String image, String like, String dislike) {
    this.nombre = nombre;
    this.image = image;
    this.like = like;
    this.dislike = dislike;
    this.trabaja = false;
    this.casa = false;
    this.amistad = 0;
    this.xp = 0;
    this.casado = false;
    this.hijo = false;
  }

  public String getLike() {
    return like;
  }

  public void setLike(String like) {
    this.like = like;
  }

  public String getDislike() {
    return dislike;
  }

  public void setDislike(String dislike) {
    this.dislike = dislike;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public boolean isTrabaja() {
    return trabaja;
  }

  public void setTrabaja(boolean trabaja) {
    this.trabaja = trabaja;
  }

  public boolean isCasa() {
    return casa;
  }

  public void setCasa(boolean casa) {
    this.casa = casa;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public int getAmistad() {
    return amistad;
  }

  public void setAmistad(int amistad) {
    this.amistad = amistad;
  }

  public double getXp() {
    return xp;
  }

  public void setXp(double xp) {
    this.xp = xp;
  }

  public boolean isCasado() {
    return casado;
  }

  public void setCasado(boolean casado) {
    this.casado = casado;
  }

  public boolean isHijo() {
    return hijo;
  }

  public void setHijo(boolean hijo) {
    this.hijo = hijo;
  }

}
