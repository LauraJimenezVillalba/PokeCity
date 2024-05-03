package models;

public class Pokemon {
  
  private String image;
  private String like;
  private String dislike;
  private boolean trabaja;

  public Pokemon(String image, String like, String dislike) {
    this.image = image;
    this.like = like;
    this.dislike = dislike;
    this.trabaja = false;
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

}
