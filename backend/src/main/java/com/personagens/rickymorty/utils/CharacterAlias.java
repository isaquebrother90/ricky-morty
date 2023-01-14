package com.personagens.rickymorty.utils;

public class CharacterAlias {
    public static String aliasConversor(String name) {
        String corretName = "-";

        if (name.matches("(?i)(rick|ric).*")) corretName = "Rick Sanchez";
        if (name.matches("(?i)(mort|morti).*")) corretName = "Morty";
        if (name.matches("(?i)(sum|summer).*")) corretName = "Summer Smith";

        return corretName;
    }
}
