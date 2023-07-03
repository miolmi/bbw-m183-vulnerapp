package ch.bbw.m183.vulnerapp.datamodel;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "blogs")
public class BlogEntity {

	@Id
	UUID id;

	@Column
	@CreationTimestamp
	@Pattern(regexp = "^[^;<>&|]*$", message = "Invalid input")
	@NotBlank(message = "A title is needed")
	LocalDateTime createdAt;

	@Column(columnDefinition = "text")
	String title;

	@Column(columnDefinition = "text")
	@Pattern(regexp = "^[^;<>&|]*$", message = "Invalid input")
	String body;
}
