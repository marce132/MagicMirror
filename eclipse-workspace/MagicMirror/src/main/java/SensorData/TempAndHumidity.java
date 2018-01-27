package SensorData;

import java.time.Instant;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name ="TempAndHumidity")
public class TempAndHumidity {

	Long id;
	Double temperature;
	Integer humidity;
	Instant measureDate;

	
	public TempAndHumidity() {
		super();
	}
	
	
	public TempAndHumidity(Long id, Double temperature, Integer humidity, Instant measureDate) {
		super();
		this.id = id;
		this.temperature = temperature;
		this.humidity = humidity;
		this.measureDate = measureDate;
	}
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "temp")
	public Double getTemperature() {
		return temperature;
	}
	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}
	
	@Column(name = "humidity")
	public Integer getHumidity() {
		return humidity;
	}
	public void setHumidity(Integer humidity) {
		this.humidity = humidity;
	}
	
	@Column(name = "measureDate")
	public Instant getMeasureDate() {
		return measureDate;
	}
	public void setMeasureDate(Instant machineTimestamp) {
		this.measureDate = machineTimestamp;
	}
	
}
