package com.meutime.manager.entity;

import java.time.LocalDate;

import com.meutime.manager.entity.enums.Resultado;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Partida {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDate data;

	@ManyToOne
	private Campeonato campeonato;

	@ManyToOne
	@JoinColumn(name = "clube_id")
	private Clube clube;

	@ManyToOne
	@JoinColumn(name = "adversario_id")
	private Clube adversario;

	private boolean emCasa;

	private int golsClube;
	private int golsAdversario;

	@Enumerated(EnumType.STRING)
	private Resultado resultado;

	private Boolean tevePenaltis;
	private Integer golsPenaltisClube;
	private Integer golsPenaltisAdversario;

	public void definirResultado() {
		if (golsClube > golsAdversario) {
			resultado = Resultado.VITORIA;
		} else if (golsClube < golsAdversario) {
			resultado = Resultado.DERROTA;
		} else {
			if (tevePenaltis && golsPenaltisClube != null && golsPenaltisAdversario != null) {
				if (golsPenaltisClube > golsPenaltisAdversario) {
					resultado = Resultado.VITORIA;
				} else {
					resultado = Resultado.DERROTA;
				}
			} else {
				resultado = Resultado.EMPATE;
			}
		}

	}
}
