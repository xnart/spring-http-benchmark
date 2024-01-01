package com.loom.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@SpringBootApplication
public class WebfluxApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebfluxApplication.class, args);
    }

}

@RestController
@RequestMapping("/")
class TestController {

    private final MemberRepository repo;

    public TestController(MemberRepository memberRepository) {
        this.repo = memberRepository;
    }

    @PostMapping
    public Mono<Member> test() {
        return repo.save(new Member(null, "test")).delayElement(Duration.ofMillis(50));
    }
}

interface MemberRepository extends ReactiveCrudRepository<Member, Integer> {
}

@Table("member")
record Member(@Id Integer id, String name) {
}
