package py.com.adrianamabel.imagestorage.models;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the resultados database table.
 * 
 */
@Embeddable
public class ResultadoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="numero_imagen")
	private double numeroImagen;

	private double probabilidad;

	@Column(name="nombre_metodo")
	private String nombreMetodo;

	private String combinacion;

	@Column(name="dimension_es")
	private double dimensionEs;

	private double ventanas;

	@Column(name="\"refHue\"")
	private double refHue;

	private String ruido;

	public ResultadoPK() {
	}
	public double getNumeroImagen() {
		return this.numeroImagen;
	}
	public void setNumeroImagen(double numeroImagen) {
		this.numeroImagen = numeroImagen;
	}
	public double getProbabilidad() {
		return this.probabilidad;
	}
	public void setProbabilidad(double probabilidad) {
		this.probabilidad = probabilidad;
	}
	public String getNombreMetodo() {
		return this.nombreMetodo;
	}
	public void setNombreMetodo(String nombreMetodo) {
		this.nombreMetodo = nombreMetodo;
	}
	public String getCombinacion() {
		return this.combinacion;
	}
	public void setCombinacion(String combinacion) {
		this.combinacion = combinacion;
	}
	public double getDimensionEs() {
		return this.dimensionEs;
	}
	public void setDimensionEs(double dimensionEs) {
		this.dimensionEs = dimensionEs;
	}
	public double getVentanas() {
		return this.ventanas;
	}
	public void setVentanas(double ventanas) {
		this.ventanas = ventanas;
	}
	public double getRefHue() {
		return this.refHue;
	}
	public void setRefHue(double refHue) {
		this.refHue = refHue;
	}
	public String getRuido() {
		return this.ruido;
	}
	public void setRuido(String ruido) {
		this.ruido = ruido;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ResultadoPK)) {
			return false;
		}
		ResultadoPK castOther = (ResultadoPK)other;
		return 
			(this.numeroImagen == castOther.numeroImagen)
			&& (this.probabilidad == castOther.probabilidad)
			&& this.nombreMetodo.equals(castOther.nombreMetodo)
			&& this.combinacion.equals(castOther.combinacion)
			&& (this.dimensionEs == castOther.dimensionEs)
			&& (this.ventanas == castOther.ventanas)
			&& (this.refHue == castOther.refHue)
			&& this.ruido.equals(castOther.ruido);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (java.lang.Double.doubleToLongBits(this.numeroImagen) ^ (java.lang.Double.doubleToLongBits(this.numeroImagen) >>> 32)));
		hash = hash * prime + ((int) (java.lang.Double.doubleToLongBits(this.probabilidad) ^ (java.lang.Double.doubleToLongBits(this.probabilidad) >>> 32)));
		hash = hash * prime + this.nombreMetodo.hashCode();
		hash = hash * prime + this.combinacion.hashCode();
		hash = hash * prime + ((int) (java.lang.Double.doubleToLongBits(this.dimensionEs) ^ (java.lang.Double.doubleToLongBits(this.dimensionEs) >>> 32)));
		hash = hash * prime + ((int) (java.lang.Double.doubleToLongBits(this.ventanas) ^ (java.lang.Double.doubleToLongBits(this.ventanas) >>> 32)));
		hash = hash * prime + ((int) (java.lang.Double.doubleToLongBits(this.refHue) ^ (java.lang.Double.doubleToLongBits(this.refHue) >>> 32)));
		hash = hash * prime + this.ruido.hashCode();
		
		return hash;
	}
}