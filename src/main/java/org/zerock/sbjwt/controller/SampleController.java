package org.zerock.sbjwt.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sample")
@Log4j2
public class SampleController {


    @GetMapping("/doAll")
    public ResponseEntity<String[]> doAll() {

        log.info("doAll...........");

        return ResponseEntity.ok(new String[]{"AAA","BBB","CCC"});
    }

    @GetMapping("/doMod")
    public ResponseEntity<String[]> doMod() {

        log.info("doMod...........");

        return ResponseEntity.ok(new String[]{"MAAA","MBBB","MCCC"});
    }

    @GetMapping("/doAdmin")
    public ResponseEntity<String[]> doAdmin() {

        log.info("doAdmin...........");

        return ResponseEntity.ok(new String[]{"AAAA","ABBB","ACCC"});
    }
}
