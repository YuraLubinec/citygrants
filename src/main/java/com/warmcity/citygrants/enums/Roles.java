package com.warmcity.citygrants.enums;

public enum Roles {
  
  ADMIN("адміністратор"), JURYMEMBER("оператор");
  
  private String role;
  
  private Roles(String role){
    this.role = role;
  }
  
  public String getRole(){
    return this.role;
  }

}
