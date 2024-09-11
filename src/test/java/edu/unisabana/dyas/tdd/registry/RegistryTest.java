package edu.unisabana.dyas.tdd.registry;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static edu.unisabana.dyas.tdd.registry.Gender.valueOf;

public class RegistryTest {
    private Registry registry = new Registry();
    private AssertJUnit Assert;

    /** Clase de equivalencia 1 (Válida):
     * Identificar persona muerta
     */
    @Test
    public void stayinAlive() { //Se espera que lance muerto
        Person person = new Person("Pepe", 123, 200, Gender.MALE, false);
        RegisterResult result = registry.registerVoter(person);
        Assert.assertEquals(RegisterResult.DEAD, result);
    }

    /** Clase de equivalencia 3 (No Válida):
     * Subir personas repetidas y que no dé RegisterResult.DEAD
     */
    @Test
    public void repetidas() { //No puede haber IDs repetidos
        Person persona = new Person("Pepe", 123, 200, Gender.MALE, false);
        Person persona1 = new Person("Sierra", 123, 200, Gender.MALE, false);
        RegisterResult result = registry.registerVoter(persona);
        RegisterResult result1 = registry.registerVoter(persona1);
        AssertJUnit.assertNotSame(RegisterResult.VALID, result1);
    }

    /** Clase de equivalencia 3 (Válida):
     * Identificar persona menor de edad
     */
    @Test
    public void menorDeEdad() { //la persona no es mayor de edad
        Person person = new Person("Pepe", 123, 10, Gender.MALE, true);
        RegisterResult result = registry.registerVoter(person);
        Assert.assertEquals(RegisterResult.UNDERAGE, result);
    }

    /** Clase de equivalencia 4 (No Válida):
     * Identificar una edad negativa
     */
    @Test
    public void edadInvalida() { //la edad es menor que 0
        Person person = new Person("Pepe", 123, -1, Gender.MALE, true);
        RegisterResult result = registry.registerVoter(person);
        Assert.assertNotSame(RegisterResult.DEAD, result);
    }

    /** Clase de equivalencia 5 (No Válida):
     * Identificar un nombre vacío
     */
    @Test
    public void nombreVacio() { //el nombre es ""
        Person person = new Person("", 123, 10, Gender.MALE, true);
        Person person1 = new Person("Pepe", 456, 19, Gender.MALE, true);
        String result = registry.nombreVacio(person);
        String result1 = registry.nombreVacio(person1);
        Assert.assertNotSame(RegisterResult.INVALID_AGE, result);
        Assert.assertNotSame(RegisterResult.DEAD, result1);
    }

    /** Clase de equivalencia 6 (De Fromtera):
     * Incluir género no válido
     */
    @Test
    public void generoNoValido() { //No va a encontrar el género "Terranator"
        Person persona = null;
        String result = "";
        try {
            persona = new Person("Danna", 246, 20, Gender.valueOf("Terranator"), true);
            result = registry.genderComprobation(persona);
        } catch (IllegalArgumentException e) {
            result = "El género no es válido";
        }
        AssertJUnit.assertEquals("El género no es válido", result);
    }
}
