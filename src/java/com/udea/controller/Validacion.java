/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.controller;

/**
 *
 * @author usuario
 */
public class Validacion {

    public static boolean validarNumero(String dato) {
        char c;
        if (dato != null && !dato.equals("")) {
            for (int i = 0; i < dato.length(); i++) {
                c = dato.charAt(i);
                if (Character.isDigit(c) == false) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public static boolean validarString(String dato) {
        char c;
        if (dato != null && !dato.equals("")) {
            for (int i = 0; i < dato.length(); i++) {
                c = dato.charAt(i);
                if (((Character.isLetter(c) || " ".equals("" + c))) == false) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public static boolean validarPrecio(String dato) {
        char c;
        boolean b = false;
        if (dato != null && !dato.equals("")) {
            String nDato = dato.replaceAll(",", ".");
            for (int i = 0; i < nDato.length(); i++) {
                c = nDato.charAt(i);
                if ((Character.isDigit(c)) == false) {
                    if ((".".equals("" + c)) && (b == false)) {
                        b = true;
                    } else {
                        return false;
                    }
                }
            }
            return true;
        } else {
            return false;
        }

    }
}
