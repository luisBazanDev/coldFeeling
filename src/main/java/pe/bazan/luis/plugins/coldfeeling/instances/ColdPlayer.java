package pe.bazan.luis.plugins.coldfeeling.instances;

import org.bukkit.entity.Player;

public class ColdPlayer {
  private final Player bukkitPlayer;
  private int armor = 0; // Armor equiped
  private int biome = 0; // temperature of the block
  private int floor = 0; // temperature of the block at the player's feet
  private int environment = 0; // rain / snow / dimension
  private int light = 0; // Light
  private int temperature = 0;
  private String biomeName = "";

  public ColdPlayer(Player bukkitPlayer) {
    this.bukkitPlayer = bukkitPlayer;
  }

  public int getArmor() {
    return armor;
  }

  public int getBiome() {
    return biome;
  }

  public int getFloor() {
    return floor;
  }

  public int getEnvironment() {
    return environment;
  }

  public int getLight() {
    return light;
  }

  public int getTotal() {
    return armor + biome + floor + environment + light;
  }

  public int getTemperature() {
    return temperature;
  }

  public String getBiomeName() {
    return biomeName;
  }

  public Player getBukkitPlayer() {
    return bukkitPlayer;
  }

  public void setArmor(int armor) {
    this.armor = armor;
  }

  public void setBiome(int biome) {
    this.biome = biome;
  }

  public void setFloor(int floor) {
    this.floor = floor;
  }

  public void setEnvironment(int environment) {
    this.environment = environment;
  }

  public void setLight(int light) {
    this.light = light;
  }

  public void setBiomeName(String biomeName) {
    this.biomeName = biomeName;
  }
}
