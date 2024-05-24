package models;

import java.io.Serializable;

public class Huevo implements Serializable {
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private Pokemon futuroHijo;
  private String tiempoEclosion;
  
  public Huevo(Pokemon futuroHijo, String tiempoEclosion) {
    this.futuroHijo = futuroHijo;
    this.tiempoEclosion = tiempoEclosion;
  }

  public Pokemon getFuturoHijo() {
    return futuroHijo;
  }

  public void setFuturoHijo(Pokemon futuroHijo) {
    this.futuroHijo = futuroHijo;
  }

  public String getTiempoEclosion() {
    return tiempoEclosion;
  }

  public void setTiempoEclosion(String tiempoEclosion) {
    this.tiempoEclosion = tiempoEclosion;
  }
  
}
