package ehb.finalwork.manager.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RethinkReturnObject {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private Long inserted;
    private Long replaced;
    private Long unchanged;
    private Long skipped;
    private Long errors;
    private Long deleted;
    private List<HashMap> changes;

    public RethinkReturnObject() {
    }

    public RethinkReturnObject(Long inserted, Long replaced, Long unchanged, Long skipped, Long errors, Long deleted, List<HashMap> changes) {
        this.inserted = inserted;
        this.replaced = replaced;
        this.unchanged = unchanged;
        this.skipped = skipped;
        this.errors = errors;
        this.deleted = deleted;
        this.changes = changes;
    }

    public Long getInserted() {
        return inserted;
    }

    public void setInserted(Long inserted) {
        this.inserted = inserted;
    }

    public Long getReplaced() {
        return replaced;
    }

    public void setReplaced(Long replaced) {
        this.replaced = replaced;
    }

    public Long getUnchanged() {
        return unchanged;
    }

    public void setUnchanged(Long unchanged) {
        this.unchanged = unchanged;
    }

    public Long getSkipped() {
        return skipped;
    }

    public void setSkipped(Long skipped) {
        this.skipped = skipped;
    }

    public Long getErrors() {
        return errors;
    }

    public void setErrors(Long errors) {
        this.errors = errors;
    }

    public Long getDeleted() {
        return deleted;
    }

    public void setDeleted(Long deleted) {
        this.deleted = deleted;
    }

    public List<RethinkChangesReturn> getChanges() {

        List<RethinkChangesReturn> returnObjects = new ArrayList<RethinkChangesReturn>();
        for (HashMap map: this.changes) {
            returnObjects.add((RethinkChangesReturn) asMapped(map, new RethinkChangesReturn()));
        }

        return returnObjects;
    }

    public void setChanges(List<HashMap> changes) {
        this.changes = changes;
        Boolean e = false;
    }

    public Object asMapped(final HashMap map, final Object toObject) {
        try {
            log.info(toObject.getClass().toString());
            return new ObjectMapper().convertValue(map, toObject.getClass());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
