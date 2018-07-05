package com.packtpublishing.tddjava.ch04ship;

public class Ship {

  private Location location;
  private Planet planet;

  public Ship(Location location, Planet planet) {
    this.location = location;
    this.planet = planet;
  }

  public Location getLocation() {
    return location;
  }

  public Planet getPlanet() {
    return planet;
  }

  public void moveForward() {
    location.forward(planet.getMax());
  }

  public void moveBackward() {
    location.backward(planet.getMax());
  }

  public void turnLeft() {
    location.turnLeft();
  }

  public void turnRight() {
    location.turnRight();
  }

  public void receiveCommands(String commands) {
    for (char cmd : commands.toCharArray()) {
      switch (cmd) {
        case 'l':
          turnLeft();
          break;
        case 'r':
          turnRight();
          break;
        case 'f':
          moveForward();
          break;
        case 'b':
          moveBackward();
          break;
      }
    }
  }
}
