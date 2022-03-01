package com.example.restservice;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	@GetMapping("/spotlights")
	public Spotlight[] spotlights() {
		return new Spotlight[] {
				new Spotlight()
		};
	}
}
