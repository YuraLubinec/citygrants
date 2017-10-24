package com.warmcity.citygrants.enums;

public enum CostItemCategory {

  FEE("fee"), 
  TRANSPORT("transport"), 
  NUTRITION("nutrition"), 
  RENT("rent"), 
  ADMINISTRATIVE("administrative"), 
  ADVERTISING("advertising"), 
  MATERIALS("materials"),
  OTHER("other");

  private String name;

  private CostItemCategory(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
