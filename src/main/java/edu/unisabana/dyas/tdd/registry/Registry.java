package edu.unisabana.dyas.tdd.registry;

import java.util.ArrayList;

public class Registry {

    private ArrayList<Integer> ids = new ArrayList<Integer>();

    public RegisterResult registerVoter(Person p) {
        if (!p.isAlive()) {
            return RegisterResult.DEAD;
        } else if (this.ids.contains(p.getId())) {
            return RegisterResult.DUPLICATED;
        } else if (p.getAge() < 0 || p.getAge() > 122) { //Edad de la persona más vieja del mundo
            return RegisterResult.INVALID_AGE;
        } else if (p.getAge() < 18) {
            return RegisterResult.UNDERAGE;
        } else {
            this.ids.add(p.getId());
            return RegisterResult.VALID;
        }


    }

    public String genderComprobation(Person p) {
        try {
            Gender result = p.getGender();
            if (result != Gender.MALE && result != Gender.FEMALE && result != Gender.UNIDENTIFIED) {
                return "El género no es válido";
            }
            return result.toString();
        } catch (IllegalArgumentException e) {
            return "El género no es válido";
        }
    }

    public String nombreVacio(Person p){
        String nombre = p.getName();
        if(nombre.equals("")){
            return "Este es un nombre inválido";
        }else{
            return "Nombre aceptado";
        }
    }
}