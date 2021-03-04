package br.com.gama.bankline.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Lancamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false, length = 20)
	private LocalDate data;
	
	@Column(nullable = false)
	private Double valor;
	
	@Column(nullable = false, length = 250)
	private String descricao;
	
	@Column(nullable = false)
	private String numConta;
	
	@JsonIgnore
	@Column(nullable = true)
	private String numContaDest;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "plano_conta", referencedColumnName = "id")
	private PlanoConta planoConta;
	
	@JsonIgnore
	@ManyToOne
	private Conta conta;

	
	public Long getId() {
		return id;
	}
	
	public Lancamento(LocalDate data, Double valor, String descricao, String numConta, String numContaDest,
			PlanoConta planoConta, Conta conta) {
		this.data = data;
		this.valor = valor;
		this.descricao = descricao;
		this.numConta = numConta;
		this.numContaDest = numContaDest;
		this.planoConta = planoConta;
		this.conta = conta;
	}

	public Lancamento() {}

	public void setId(Long id) {
		this.id = id;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}


	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNumConta() {
		return numConta;
	}

	public void setNumConta(String numConta) {
		this.numConta = numConta;
	}

	public String getNumContaDest() {
		return numContaDest;
	}

	public void setNumContaDest(String numContaDest) {
		this.numContaDest = numContaDest;
	}
	
	public PlanoConta getPlanoConta() {
		return planoConta;
	}

	public void setPlanoConta(PlanoConta planoConta) {
		this.planoConta = planoConta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lancamento other = (Lancamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
}
