package in.gov.vocport.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
public class CtTdDirectServiceChgId implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name = "CHIT_NO", length = 25)
    private String chitNo;

    @Column(name = "SRL_NO")
    private Long srlNo;

    // equals & hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CtTdDirectServiceChgId)) return false;
        CtTdDirectServiceChgId that = (CtTdDirectServiceChgId) o;
        return Objects.equals(chitNo, that.chitNo) &&
               Objects.equals(srlNo, that.srlNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chitNo, srlNo);
    }
}
