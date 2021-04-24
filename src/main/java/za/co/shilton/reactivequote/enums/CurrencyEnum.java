package za.co.shilton.reactivequote.enums;

public enum CurrencyEnum {
  ZAR("R");

  private final String name;

  CurrencyEnum(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }


}
