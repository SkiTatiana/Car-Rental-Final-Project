package car.rental.entity;

import java.time.LocalDate;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "rental")
@IdClass(RentalId.class)

public class Rental {
	@Id
	@Column(name = "customer_id", insertable = false, updatable = false)
	private Long customerId;

	@Id
	@Column(name = "car_id", insertable = false, updatable = false)
	private Long carId;

//	@EmbeddedId
//	private RentalId rentalId;
	
	@Column(name = "start_date")
	private LocalDate startDate;
	@Column(name = "end_date")
	private LocalDate endDate;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "car_id", referencedColumnName = "car_id", insertable = false, updatable = false)
	@NotFound(action = NotFoundAction.IGNORE) 
	private Car car;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id", referencedColumnName = "customer_id", insertable = false, updatable = false)
	@NotFound(action = NotFoundAction.IGNORE) 
	private Customer customer;

}
