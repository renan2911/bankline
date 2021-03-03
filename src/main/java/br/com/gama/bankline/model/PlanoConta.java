package br.com.gama.bankline.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.gama.bankline.enums.TipoPlanoConta;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PlanoConta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(length = 350, nullable = false)
	private String descricao;
	
	@Column(nullable = false)
	private TipoPlanoConta tipoPlanoConta;
	
	@ManyToOne
	private Usuario usuario;
	
	
	
	public PlanoConta() {
	}
	
	public PlanoConta(String descricao, TipoPlanoConta tipoPlanoConta, Usuario usuario) {
		this.descricao = descricao;
		this.tipoPlanoConta = tipoPlanoConta;
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
		PlanoConta other = (PlanoConta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
