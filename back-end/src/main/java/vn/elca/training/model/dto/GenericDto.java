package vn.elca.training.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author dtx
 *
 */
@Getter
@Setter
@NoArgsConstructor
public abstract class GenericDto {

    private Long id;
    private Long[] deletedIds;
    private Integer version;

    public GenericDto(Long id, Integer version) {
        this.id = id;
        this.version = version;
    }
}
