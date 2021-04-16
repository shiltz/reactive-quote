package za.co.shilton.reactivequote.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController("/quote-service")
public class QuoteController {

  @GetMapping("/hello")
  public Mono<String> helloWorld(@RequestParam String name) {
    return Mono.defer(() -> Mono.just("Hello world!" + name));
  }
}
