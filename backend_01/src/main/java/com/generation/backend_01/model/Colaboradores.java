package com.generation.backend_01.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_colaboradores")
public class Colaboradores {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Esse campo é obrigatório")
	@Size(min = 3, max = 100, message = "Digite nome e sobrenome")
	private String nome;

	@NotBlank(message = "Esse campo é obrigatório")
	@Size(min = 3, max = 100, message = "Digite o cargo")
	private String cargo;

	@NotBlank(message = "Esse campo é obrigatório")
	@Size(min = 2, max = 100, message = "Digite o departamento")
	private String departamento;

	@DecimalMin(value = "0.0", inclusive = false, message = "O salário deve ser maior que zero.")
	@Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal salario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

}
