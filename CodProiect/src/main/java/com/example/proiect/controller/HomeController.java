/** Clasa pentru operarea controlerului principal Home
 * @author Ionescu Vlad-Andrei
 * @version 25 Decembrie 2024
 */
package com.example.proiect.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // marcheaza clasa ca fiind un controler pentru operatii web
public class HomeController {

    // GET: Returneaza pagina principala a aplicatiei
    @GetMapping("/")
    public String home() {
        return "proiect";
    }
}
