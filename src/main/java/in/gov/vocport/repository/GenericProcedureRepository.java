package in.gov.vocport.repository;

import in.gov.vocport.dto.ProcedureKeyValueDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public class GenericProcedureRepository {
    @Autowired
    private EntityManager entityManager;

//    @Autowired
//    public GenericProcedureRepository(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }

    public Object callStoredProcedure(String procedureName, List<ProcedureKeyValueDTO> parameters, Object returnType, String... resultSetMappingName) {
        StoredProcedureQuery storedProcedureQuery;
        if (resultSetMappingName.length == 0) {
            storedProcedureQuery = entityManager.createStoredProcedureQuery(procedureName);
        } else storedProcedureQuery = entityManager.createStoredProcedureQuery(procedureName, resultSetMappingName[0]);

        String outputParameterName = "";
        for (ProcedureKeyValueDTO procedureKeyValueDTO : parameters) {
            String keyParameter = procedureKeyValueDTO.getParameterKey();

            if (procedureKeyValueDTO.getParameterMode() == ParameterMode.IN) {
                registerOutputParameter(storedProcedureQuery, keyParameter, procedureKeyValueDTO.getParameterValueType(), ParameterMode.IN);
                storedProcedureQuery.setParameter(keyParameter, procedureKeyValueDTO.getCastedValue());
            }
            if (procedureKeyValueDTO.getParameterMode() == ParameterMode.REF_CURSOR) {
                registerOutputParameter(storedProcedureQuery, keyParameter, procedureKeyValueDTO.getParameterValueType(), ParameterMode.REF_CURSOR);
            }
            if (procedureKeyValueDTO.getParameterMode() == ParameterMode.OUT) {
                storedProcedureQuery.registerStoredProcedureParameter(keyParameter, procedureKeyValueDTO.getParameterValueType(), ParameterMode.OUT);
                outputParameterName = keyParameter;
            }
        }

        if (returnType instanceof List<?>) {
            return storedProcedureQuery.getResultList();
        } else if (returnType instanceof Number || returnType == String.class) {
            storedProcedureQuery.execute();
            return storedProcedureQuery.getOutputParameterValue(outputParameterName);
        } else return storedProcedureQuery.execute();
    }

    public void registerOutputParameter(StoredProcedureQuery storedProcedureQuery, String parameter, Class clazz, ParameterMode parameterMode) {
        if (clazz.equals(String.class)) {
            storedProcedureQuery.registerStoredProcedureParameter(parameter, String.class, parameterMode);
        } else if (clazz.equals(void.class)) {
            storedProcedureQuery.registerStoredProcedureParameter(parameter, void.class, parameterMode);
        } else if (clazz.equals(Number.class)) {
            storedProcedureQuery.registerStoredProcedureParameter(parameter, Number.class, parameterMode);
        } else if (clazz.equals(Long.class)) {
            storedProcedureQuery.registerStoredProcedureParameter(parameter, Long.class, parameterMode);
        } else if (clazz.equals(Integer.class)) {
            storedProcedureQuery.registerStoredProcedureParameter(parameter, Integer.class, parameterMode);
        } else if (clazz.equals(Date.class)) {
            storedProcedureQuery.registerStoredProcedureParameter(parameter, Date.class, parameterMode);
        } else if (clazz.equals(java.sql.Date.class)){
            storedProcedureQuery.registerStoredProcedureParameter(parameter, java.sql.Date.class, parameterMode);
        } else if (clazz.equals(LocalDate.class)){
            storedProcedureQuery.registerStoredProcedureParameter(parameter, LocalDate.class, parameterMode);
        }
    }
}

