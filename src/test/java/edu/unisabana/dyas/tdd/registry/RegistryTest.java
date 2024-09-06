package edu.unisabana.dyas.tdd.registry;

import org.testng.AssertJUnit;

public class RegistryTest {
    private Registry registry = new Registry();
    private AssertJUnit Assert;

    @org.testng.annotations.Test
    public void validateRegistryResult() {
        Person person = new Person();
        RegisterResult result = registry.registerVoter(person);
        Assert.assertEquals(RegisterResult.VALID, result);
    }
    // TODO Complete with more test cases
}
