package com.packtpublishing.tddjava.ch04ship;

import org.testng.annotations.*;

import static org.testng.Assert.*;

@Test
public class ShipSpec {

  private Ship ship;
  private Location location;
  private Planet planet;

  @BeforeMethod
  public void beforeTest() {
    location = new Location(new Point(21, 13), Direction.NORTH);
    Point max = new Point(50, 50);
    planet = new Planet(max);
    ship = new Ship(location, planet);
  }

  public void whenInstantiatedThenLocationIsSet() {
    assertEquals(ship.getLocation(), location);
  }

  public void whenMoveForwardThenForward() {
    Location expected = location.copy();
    expected.forward();
    ship.moveForward();
    assertEquals(ship.getLocation(), expected);
  }

  public void whenMoveBackwardThenBackward() {
    Location expected = location.copy();
    expected.backward();
    ship.moveBackward();
    assertEquals(ship.getLocation(), expected);
  }

  public void whenTurnLeftThenLeft() {
    Location expected = location.copy();
    expected.turnLeft();
    ship.turnLeft();
    assertEquals(ship.getLocation(), expected);
  }

  public void whenTurnRightThenRight() {
    Location expected = location.copy();
    expected.turnRight();
    ship.turnRight();
    assertEquals(ship.getLocation(), expected);
  }

  public void whenReceiveCommandsFThenForward() {
    Location expected = location.copy();
    expected.forward();
    ship.receiveCommands("f");
    assertEquals(ship.getLocation(), expected);
  }

  public void whenReceiveCommandsThenAllAreExecuted() {
    Location expected = location.copy();
    expected.turnRight();
    expected.forward();
    expected.turnLeft();
    expected.backward();
    ship.receiveCommands("rflb");
    assertEquals(ship.getLocation(), expected);
  }

  public void whenInstantiatedThenPlanetIsStored() {
    assertEquals(ship.getPlanet(), planet);
  }

  public void overpassEastBoundary() {
    location.setDirection(Direction.EAST);
    location.getPoint().setX(planet.getMax().getX());
    ship.receiveCommands("f");
    assertEquals(location.getX(), 1);
  }
}
