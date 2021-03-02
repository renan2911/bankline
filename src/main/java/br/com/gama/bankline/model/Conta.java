package br.com.gama.bankline.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.gama.bankline.enums.ContaTipo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private Double saldo = 0.0;
	
	@Column(nullable = false,length = 20)
	private String numero;
	
	//@Column(nullable = false)
	//private ContaTipo contaTipo;
	
	//@JsonIgnore
	@OneToOne
	private Usuario usuario;
	
	
	
	
	public Conta(String numero, Usuario usuario) {
		this.numero = numero;
		this.usuario = usuario;
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
		Conta other = (Conta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Conta(Long id, Double saldo, String numero, Usuario usuario) {
		super();
		this.id = id;
		this.saldo = saldo;
		this.numero = numero;
		this.usuario = usuario;
	}

	
}
