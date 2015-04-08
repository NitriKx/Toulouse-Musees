package com.tblabsau.universite.s8.amc.projet
/**
 * Created by benoit on 08/04/15.
 */
enum TypeVisiteEnum  {
    EN_COURS_DE_TRAITEMENT("EN_COURS_DE_TRAITEMENT"),
    CONFIRMEE("CONFIRMEE"),
    REFUSEE("REFUSEE")

    final String value

    TypeVisiteEnum(String value) { this.value = value }

    String toString() { value }
    String getKey() { name() }
}