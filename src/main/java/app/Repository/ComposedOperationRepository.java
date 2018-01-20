package app.Repository;

import app.Model.ComposedOperation;
import org.springframework.data.repository.CrudRepository;

public interface ComposedOperationRepository extends CrudRepository<ComposedOperation, Long> {

    ComposedOperation findFirstByParentIsNull();
}
