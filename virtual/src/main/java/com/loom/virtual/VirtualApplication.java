package com.loom.virtual;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.List;

@SpringBootApplication
public class VirtualApplication {

    public static void main(String[] args) {
        SpringApplication.run(VirtualApplication.class, args);
    }

}

@RestController
@RequestMapping("/")
class TestController {

    private final MemberRepository repo;

    public TestController(MemberRepository memberRepository){
        this.repo = memberRepository;
    }

    @PostMapping
    public Member test() throws InterruptedException {
        Thread.sleep(50);
        return repo.save(new Member(null, "test"));
    }

}

interface MemberRepository extends ListCrudRepository<Member, Integer> {}

@Table("member")
record Member(@Id Integer id, String name){}
