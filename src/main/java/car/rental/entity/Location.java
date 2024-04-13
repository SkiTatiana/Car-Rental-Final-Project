package car.rental.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Embeddable
@Data
@NoArgsConstructor
public class Location {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long locationId;
	private String locationName;
	private String address;
	private String city;
	private String state;
	private String zip;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "location", cascade = CascadeType.REFRESH, orphanRemoval = false)
//	@JoinTable(name = "rental",
//		joinColumns = @JoinColumn(name = "car_id"),
//		inverseJoinColumns = @JoinColumn(name = "customer_id"))
	private Set<Car> cars = new HashSet<>();
	
	//TODO - location to car one to many
}
