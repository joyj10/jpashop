package jpabook.jpashop.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * HomeController
 * <pre>
 * Describe here
 * </pre>
 *
 * @version 1.0,
 */

@Controller
@Slf4j
public class HomeController {

    @RequestMapping("/")
    public String home() {
        log.info("### home contoller");
        return "home";
    }
}
