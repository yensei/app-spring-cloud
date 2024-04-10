package py.com.yensei.store.shopping.models;

import lombok.Data;
import py.com.yensei.store.utils.constants.Status;

@Data
public class Customer {
    private Long id;

    /**
     * cedula de identidad, pasaporte, DNI, etc ..
     */
    private String documentId;

    private String ruc;

    private String firstname;

    private String lastname;

    private String email;

    private String photoUrl;

    private Region region;

    private Status status;

}
