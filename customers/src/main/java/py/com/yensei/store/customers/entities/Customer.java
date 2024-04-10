package py.com.yensei.store.customers.entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import py.com.yensei.store.utils.constants.Status;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tl_cus_customer")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * cedula de identidad, pasaporte, DNI, etc .. 
     */
    @NotEmpty(message = "El número no puede ser vacío")
    @Size(min = 6, max=12, message = "El núrmero CI debe ser minimo 6 y hasta 12 caracteres")
    @Column(name = "document_id", unique = true, length = 12, nullable = false)
    private String documentId;

    @NotEmpty(message = "El número de RUC (Paraguay) no es válido")
    @Pattern(regexp = "^\\d{6,8}-\\d{1}")
    @Column(unique = true)
    private String ruc;

    @NotEmpty(message = "El nombre es obligatorio")
    @Column(name = "first_name", nullable = false)
    private String firstname;
    
    @NotEmpty(message = "El apellido es obligatorio")
    @Column(name = "last_name",  nullable = false)
    private String lastname;

    @NotEmpty(message = "El correo no puede ser vacío")
    @Email(message = "No es un correo válido")
    @Column(unique = true, nullable = false)
    private String email;


    @Column(name = "photo_URL")
    private String photoUrl;


    @NotNull(message = "Region es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Region region;

    @Enumerated(EnumType.STRING)
    private Status status;
}
