package py.com.adrianamabel.imagestorage.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the resultados database table.
 * 
 */
@Entity
@Table(name="resultados")
@NamedQuery(name="Resultado.findAll", query="SELECT r FROM Resultado r")
public class Resultado implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ResultadoPK id;

	private String comp1;

	private String comp2;

	private String comp3;

	@Column(name="\"compReducido\"")
	private double compReducido;

	@Column(name="mae_marginal")
	private double maeMarginal;

	@Column(name="\"maeEuclidean\"")
	private double maeEuclidean;

	@Column(name="\"metricOfSimilarityM1\"")
	private double metricOfSimilarityM1;

	@Column(name="\"metricOfSimilarityM2\"")
	private double metricOfSimilarityM2;

	private double ncd;

	public Resultado() {
	}

	public ResultadoPK getId() {
		return this.id;
	}

	public void setId(ResultadoPK id) {
		this.id = id;
	}

	public String getComp1() {
		return this.comp1;
	}

	public void setComp1(String comp1) {
		this.comp1 = comp1;
	}

	public String getComp2() {
		return this.comp2;
	}

	public void setComp2(String comp2) {
		this.comp2 = comp2;
	}

	public String getComp3() {
		return this.comp3;
	}

	public void setComp3(String comp3) {
		this.comp3 = comp3;
	}

	public double getCompReducido() {
		return this.compReducido;
	}

	public void setCompReducido(double compReducido) {
		this.compReducido = compReducido;
	}

	public double getMaeMarginal() {
		return this.maeMarginal;
	}

	public void setMaeMarginal(double maeMarginal) {
		this.maeMarginal = maeMarginal;
	}

	public double getMaeEuclidean() {
		return this.maeEuclidean;
	}

	public void setMaeEuclidean(double maeEuclidean) {
		this.maeEuclidean = maeEuclidean;
	}

	public double getMetricOfSimilarityM1() {
		return this.metricOfSimilarityM1;
	}

	public void setMetricOfSimilarityM1(double metricOfSimilarityM1) {
		this.metricOfSimilarityM1 = metricOfSimilarityM1;
	}

	public double getMetricOfSimilarityM2() {
		return this.metricOfSimilarityM2;
	}

	public void setMetricOfSimilarityM2(double metricOfSimilarityM2) {
		this.metricOfSimilarityM2 = metricOfSimilarityM2;
	}

	public double getNcd() {
		return this.ncd;
	}

	public void setNcd(double ncd) {
		this.ncd = ncd;
	}

}