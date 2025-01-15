package com.generation.backend_01.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.backend_01.model.Colaboradores;
import com.generation.backend_01.repository.ColaboradoresRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/colaboradores")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ColaboradoresController {

	@Autowired
	private ColaboradoresRepository colaboradoresRepository;

	@GetMapping
	public ResponseEntity<List<Colaboradores>> getAll() {
		return ResponseEntity.ok(colaboradoresRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Colaboradores> getById(@PathVariable Long id) {
		return colaboradoresRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Colaboradores>> getByNome(@PathVariable String nome) {
		return ResponseEntity.ok(colaboradoresRepository.findAllByNomeContainingIgnoreCase(nome));
	}

	@PostMapping
	public ResponseEntity<Colaboradores> post(@Valid @RequestBody Colaboradores colaboradores) {
		// Salva a postagem no banco e retorna status 201 (Created)
		return ResponseEntity.status(HttpStatus.CREATED).body(colaboradoresRepository.save(colaboradores));
	}

	@PutMapping
	public ResponseEntity<Colaboradores> put(@Valid @RequestBody Colaboradores colaboradores) {
		// Verifica se a postagem com o ID fornecido existe
		return colaboradoresRepository.findById(colaboradores.getId())
				.map(reposta -> ResponseEntity.status(HttpStatus.OK).body(colaboradoresRepository.save(colaboradores))) // Atualiza
																														// e
																														// retorna
																														// a
																														// postagem
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build()); // Retorna status 404 se não encontrar
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Colaboradores> colaboradores = colaboradoresRepository.findById(id);

		if (colaboradores.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);

		colaboradoresRepository.deleteById(id);
	}
}
